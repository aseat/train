package shinkansen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import connect.ConnectDatabase;

public class RideTrain {

	public static void main(String[] args) throws Exception {
		ConnectDatabase connect = new ConnectDatabase();

		Statement statement = connect.getStatement();

		String sql = "SELECT * FROM shinkansen.station";

		ResultSet stationResultSet = statement.executeQuery(sql);
		try {
			String direction = getValidatedTrainDirection();

			System.out.println("");

			while (stationResultSet.next()) {
				System.out.print(stationResultSet.getInt("id") + "." + stationResultSet.getString("name") + " ");
			}
			System.out.println("");
			System.out.print("出発地を番号で選んでてください。:");
			int inputDepartureStation = getValidatedStationNumber();
			stationResultSet.absolute(inputDepartureStation);
			String departureStation = stationResultSet.getString("name");
			String departureStationRome = stationResultSet.getString("name_rome");

			System.out.println("");
			stationResultSet.absolute(0);

			while (stationResultSet.next()) {
				System.out.print(stationResultSet.getInt("id") + "." + stationResultSet.getString("name") + " ");
			}
			System.out.println("");
			System.out.print("目的地を番号で選んてください。:");
			int inputArriveStation = getValidatedStationNumber();
			validatedStationDirection(direction, inputDepartureStation, inputArriveStation);
			stationResultSet.absolute(inputArriveStation);
			String arriveStation = stationResultSet.getString("name");
			String arriveStationRome = stationResultSet.getString("name_rome");

			System.out.println("");

			int hour = getValidatedDepartureHour();
			System.out.println("");
			int minute = getValidatedDepartureMinute();
			System.out.println("");
			LocalDateTime time = getValidatedDepartureTime(hour, minute);
			String timeFormat = getFormatTime(time);

			sql = "SELECT name,id,to_char(" + departureStationRome + "_departure,'HH24:MI')AS departure_time, to_char("
					+ arriveStationRome + "_arrive,'HH24:MI') AS arrive_time FROM shinkansen.tokaido_" + direction
					+ "_train" + " WHERE " + departureStationRome + "_departure IS NOT NULL AND " + arriveStationRome
					+ "_arrive IS NOT NULL AND" + "'" + timeFormat + "' <=" + departureStationRome + "_departure AND "
					+ departureStationRome + "_departure <='" + (time.getHour() + 1) + ":59' ORDER BY "
					+ departureStationRome + "_departure;";

			ResultSet trainResultSet = statement.executeQuery(sql);

			while (trainResultSet.next()) {
				System.out.println(trainResultSet.getString("name") + trainResultSet.getInt("id") + "号："
						+ departureStation + "発 " + trainResultSet.getString("departure_time") + " → " + arriveStation
						+ "着 " + trainResultSet.getString("arrive_time"));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		connect.closeConnection();

	}

	private static Integer getInputValue() throws Exception {
		try {
			return new java.util.Scanner(System.in).nextInt();
		} catch (InputMismatchException exception) {
			System.out.println("数字で入力してください。");
			System.exit(0);
		}
		return null;
	}

	private static Integer getValidatedStationNumber() throws Exception {
		try {
			int stationNumber = getInputValue();
			if (stationNumber < 1 || 17 < stationNumber) {
				throw new IllegalArgumentException();
			}

			return stationNumber;
		} catch (IllegalArgumentException exception) {
			System.out.println("1～17の範囲で入力してください。");
			System.exit(0);
		}
		return null;
	}

	private static void validatedStationDirection(String direction, int departureStation, int arriveStation) {
		try {
			if ("up".equals(direction)) {
				if (arriveStation > departureStation) {
					throw new IllegalArgumentException();
				}
			} else if ("down".equals(direction)) {
				if (arriveStation < departureStation) {
					throw new IllegalArgumentException();
				}
			}
		} catch (IllegalArgumentException exception) {
			System.out.println("出発地からの方向が逆です。");
			System.exit(0);
		}
	}

	private static String getValidatedTrainDirection() throws Exception {
		try {
			System.out.println("1.上り方面 2.下り方面");
			System.out.print("上り方面(東京方面)か下り方面(新大阪方面)か番号で入力してください。：");
			int DirectionNumber = getInputValue();
			if (DirectionNumber < 1 || 2 < DirectionNumber) {
				throw new IllegalArgumentException();
			}
			if (DirectionNumber == 1) {
				return "up";
			} else {
				return "down";
			}

		} catch (IllegalArgumentException exception) {
			System.out.println("1～2の範囲で入力してください。");
			System.exit(0);
		}
		return null;
	}

	private static Integer getValidatedDepartureHour() throws Exception {
		try {
			System.out.print("出発する時間を入力してください。：");
			int hour = getInputValue();
			if (hour < 0) {
				throw new IllegalArgumentException();
			}
			return hour;
		} catch (IllegalArgumentException exception) {
			System.out.println("0以上で入力してください。");
			System.exit(0);
		}
		return null;
	}

	private static Integer getValidatedDepartureMinute() throws Exception {
		try {
			System.out.print("出発する分を入力してください。：");
			int minute = getInputValue();
			if (minute < 0) {
				throw new IllegalArgumentException();
			}
			return minute;
		} catch (IllegalArgumentException exception) {
			System.out.println("0以上で入力してください。");
			System.exit(0);
		}
		return null;
	}

	private static LocalDateTime getValidatedDepartureTime(int hour, int minute) throws Exception {
		try {
			return LocalDateTime.of(2022, 3, 4, hour, minute);
		} catch (DateTimeException exception) {
			System.out.println("不正な時刻です。");
			System.exit(0);
		}
		return null;
	}

	private static String getFormatTime(LocalDateTime time) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
		return format.format(time);
	}
}
