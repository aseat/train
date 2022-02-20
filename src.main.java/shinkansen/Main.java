package shinkansen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		Station tokyo = new Station(1, "東京", 0);
		Station shinagawa = new Station(2, "品川", 6.8);
		Station shinyokohama = new Station(3, "新横浜", 28.8);
		Station odawara = new Station(4, "小田原", 83.9);
		Station atami = new Station(5, "熱海", 104.6);
		Station mishima = new Station(6, "三島", 120.7);
		Station shinfuji = new Station(7, "新富士", 146.2);
		Station shizuoka = new Station(8, "静岡", 180.2);
		Station kakegawa = new Station(9, "掛川", 229.3);
		Station hamamatsu = new Station(10, "浜松", 257.1);
		Station toyohashi = new Station(11, "豊橋", 293.6);
		Station mikawaanjyo = new Station(12, "三河安城", 336.3);
		Station nagoya = new Station(13, "名古屋", 366.3);
		Station gifuhashima = new Station(14, "岐阜羽島", 396.3);
		Station maibara = new Station(15, "米原", 445.9);
		Station kyoto = new Station(16, "京都", 513.6);
		Station shinosaka = new Station(17, "新大阪", 552.6);
		List<Station> stations = new ArrayList<>();
		stations = Arrays.asList(tokyo, shinagawa, shinyokohama, odawara, atami, mishima, shinfuji, shizuoka, kakegawa,
				hamamatsu, toyohashi, mikawaanjyo, nagoya, gifuhashima, maibara, kyoto, shinosaka);
		for (Station station : stations) {
			System.out.print(station.getId() + "." + station.getName() + " ");
		}
		System.out.println("");
		System.out.print("出発地を番号で選んでてください。:");
		int inputDeparturePlace = getValidatedValue();
		for (Station station : stations) {
			System.out.print(station.getId() + "." + station.getName() + " ");
		}
		System.out.println("");
		System.out.print("目的地を番号で選んてください。:");
		int inputDestination = getValidatedValue();
		Fare fare = new Fare();

		double km = Math.round(stations.get(inputDeparturePlace - 1).getKmFromTokyo()
				- stations.get(inputDestination - 1).getKmFromTokyo());
		if (km <= -1) {
			km *= -1;
		}

		ReservedSeat reservedSeat1 = new ReservedSeat(1, "自由席");
		ReservedSeat reservedSeat2 = new ReservedSeat(2, "指定席(ひかり、こだま)");
		ReservedSeat reservedSeat3 = new ReservedSeat(3, "指定席(のぞみ)");
		List<ReservedSeat> seats = new ArrayList<>();
		seats = Arrays.asList(reservedSeat1, reservedSeat2, reservedSeat3);
		for (ReservedSeat seat : seats) {
			System.out.print(seat.getId() + "." + seat.getName() + " ");
		}
		System.out.println("");
		System.out.print("座席の種類を番号で選んでてください。:");
		int inputSeat = getValidatedValue();
		seats.get(inputSeat - 1).setTicketPrice(km);
		int limitedTicketPrice = seats.get(inputSeat - 1).getLimitedTicket(km, inputSeat);
		int allPrice = limitedTicketPrice + fare.fare(km);

		System.out.print("乗車券:" + fare.fare(km) + "円 " + seats.get(inputSeat - 1).getName() + ":" + limitedTicketPrice
				+ " 合計:" + allPrice);
	}

	private static Integer getValidatedValue() throws Exception {
		try {
			return new java.util.Scanner(System.in).nextInt();
		} catch (InputMismatchException exception) {
			System.out.println("数字で入力してください。");
			System.exit(0);
		}
		return null;
	}

}