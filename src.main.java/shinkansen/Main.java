package shinkansen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		Station tokyo = new Station(1, "����", 0);
		Station shinagawa = new Station(2, "�i��", 6.8);
		Station shinyokohama = new Station(3, "�V���l", 28.8);
		Station odawara = new Station(4, "���c��", 83.9);
		Station atami = new Station(5, "�M�C", 104.6);
		Station mishima = new Station(6, "�O��", 120.7);
		Station shinfuji = new Station(7, "�V�x�m", 146.2);
		Station shizuoka = new Station(8, "�É�", 180.2);
		Station kakegawa = new Station(9, "�|��", 229.3);
		Station hamamatsu = new Station(10, "�l��", 257.1);
		Station toyohashi = new Station(11, "�L��", 293.6);
		Station mikawaanjyo = new Station(12, "�O�͈���", 336.3);
		Station nagoya = new Station(13, "���É�", 366.3);
		Station gifuhashima = new Station(14, "�򕌉H��", 396.3);
		Station maibara = new Station(15, "�Č�", 445.9);
		Station kyoto = new Station(16, "���s", 513.6);
		Station shinosaka = new Station(17, "�V���", 552.6);
		List<Station> stations = new ArrayList<>();
		stations = Arrays.asList(tokyo, shinagawa, shinyokohama, odawara, atami, mishima, shinfuji, shizuoka, kakegawa,
				hamamatsu, toyohashi, mikawaanjyo, nagoya, gifuhashima, maibara, kyoto, shinosaka);
		for (Station station : stations) {
			System.out.print(station.getId() + "." + station.getName() + " ");
		}
		System.out.println("");
		System.out.print("�o���n��ԍ��őI��łĂ��������B:");
		int inputDeparturePlace = getValidatedValue();
		for (Station station : stations) {
			System.out.print(station.getId() + "." + station.getName() + " ");
		}
		System.out.println("");
		System.out.print("�ړI�n��ԍ��őI��Ă��������B:");
		int inputDestination = getValidatedValue();
		Fare fare = new Fare();

		double km = Math.round(stations.get(inputDeparturePlace - 1).getKmFromTokyo()
				- stations.get(inputDestination - 1).getKmFromTokyo());
		if (km <= -1) {
			km *= -1;
		}

		ReservedSeat reservedSeat1 = new ReservedSeat(1, "���R��");
		ReservedSeat reservedSeat2 = new ReservedSeat(2, "�w���(�Ђ���A������)");
		ReservedSeat reservedSeat3 = new ReservedSeat(3, "�w���(�̂���)");
		List<ReservedSeat> seats = new ArrayList<>();
		seats = Arrays.asList(reservedSeat1, reservedSeat2, reservedSeat3);
		for (ReservedSeat seat : seats) {
			System.out.print(seat.getId() + "." + seat.getName() + " ");
		}
		System.out.println("");
		System.out.print("���Ȃ̎�ނ�ԍ��őI��łĂ��������B:");
		int inputSeat = getValidatedValue();
		seats.get(inputSeat - 1).setTicketPrice(km);
		int limitedTicketPrice = seats.get(inputSeat - 1).getLimitedTicket(km, inputSeat);
		int allPrice = limitedTicketPrice + fare.fare(km);

		System.out.print("��Ԍ�:" + fare.fare(km) + "�~ " + seats.get(inputSeat - 1).getName() + ":" + limitedTicketPrice
				+ " ���v:" + allPrice);
	}

	private static Integer getValidatedValue() throws Exception {
		try {
			return new java.util.Scanner(System.in).nextInt();
		} catch (InputMismatchException exception) {
			System.out.println("�����œ��͂��Ă��������B");
			System.exit(0);
		}
		return null;
	}

}