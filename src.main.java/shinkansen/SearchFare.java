package shinkansen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import connect.ConnectDatabase;

public class SearchFare {

	public static void main(String[] args) throws Exception {
		ConnectDatabase connect = new ConnectDatabase();

		Statement statement = connect.getStatement();

		String sql = "SELECT * FROM shinkansen.station";

		ResultSet resultSet = statement.executeQuery(sql);
		try {
			while (resultSet.next()) {
				System.out.print(resultSet.getInt("id") + "." + resultSet.getString("name") + " ");
			}
			System.out.println("");
			System.out.print("出発地を番号で選んでてください。:");
			int inputDepartureStation = getValidatedStationNumber();

			System.out.println("");
			resultSet.absolute(0);

			while (resultSet.next()) {
				System.out.print(resultSet.getInt("id") + "." + resultSet.getString("name") + " ");
			}
			System.out.println("");

			System.out.print("目的地を番号で選んてください。:");
			int inputArriveStation = getValidatedStationNumber();

			System.out.println("");

			resultSet.absolute(inputDepartureStation);
			double depertureKmFromTokyo = resultSet.getDouble("km_from_tokyo");
			boolean depertureIsNozomi = resultSet.getBoolean("nozomi");

			resultSet.absolute(inputArriveStation);
			double arriveKmFromTokyo = resultSet.getDouble("km_from_tokyo");
			boolean arriveIsNozomi = resultSet.getBoolean("nozomi");

			double km = Math.round(depertureKmFromTokyo - arriveKmFromTokyo);
			if (km <= -1) {
				km *= -1;
			}
			Fare fare = new Fare();
			ReservedSeat reservedSeat1 = new ReservedSeat(1, "自由席");
			ReservedSeat reservedSeat2 = new ReservedSeat(2, "指定席(ひかり、こだま)");
			ReservedSeat reservedSeat3 = new ReservedSeat(3, "指定席(のぞみ)");
			List<ReservedSeat> seats = new ArrayList<>();
			seats = Arrays.asList(reservedSeat1, reservedSeat2, reservedSeat3);
			for (ReservedSeat seat : seats) {
				if (!depertureIsNozomi && seat.getId() == 3 || !arriveIsNozomi && seat.getId() == 3) {
					break;
				}
				System.out.print(seat.getId() + "." + seat.getName() + " ");
			}
			System.out.println("");
			System.out.print("座席の種類を番号で選んでてください。:");
			int inputSeat = getValidatedSeat(depertureIsNozomi, arriveIsNozomi);
			seats.get(inputSeat - 1).setTicketPrice(km);
			int limitedTicketPrice = seats.get(inputSeat - 1).getLimitedTicket(km, inputSeat);
			int allPrice = limitedTicketPrice + fare.fare(km);

			System.out.print("乗車券:" + fare.fare(km) + "円 " + seats.get(inputSeat - 1).getName() + ":"
					+ limitedTicketPrice + " 合計:" + allPrice);
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

	private static Integer getValidatedSeat(boolean depertureIsNozomi, boolean arriveIsNozomi) throws Exception {
		try {
			int seat = getInputValue();
			if (seat < 1 || 3 < seat) {
				throw new IllegalArgumentException();
			}
			if (seat < 1 && !depertureIsNozomi || !arriveIsNozomi && 2 < seat) {
				throw new Exception();
			}
			return seat;
		} catch (IllegalArgumentException exception) {
			System.out.println("1～3の範囲で入力してください。");
			System.exit(0);
		} catch (Exception excepition) {
			System.out.println("のぞみが止まらない駅が含まれてます。(のぞみ停車駅：東京、品川、新横浜、名古屋、京都、新大阪)");
			System.exit(0);
		}
		return null;
	}
}