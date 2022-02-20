package shinkansen;

public class Fare {
	public int fare(double i) {
		if (1 <= i && i <= 3) {
			return 150;
		}
		if (4 <= i && i <= 6) {
			return 190;
		}
		if (7 <= i && i <= 10) {
			return 200;
		}
		if (11 <= i && i <= 15) {
			return 240;
		}
		if (16 <= i && i <= 20) {
			return 330;
		}
		if (21 <= i && i <= 25) {
			return 420;
		}
		if (26 <= i && i <= 30) {
			return 510;
		}
		if (31 <= i && i <= 35) {
			return 590;
		}
		if (36 <= i && i <= 40) {
			return 680;
		}
		if (41 <= i && i <= 45) {
			return 770;
		}
		if (46 <= i && i <= 50) {
			return 860;
		}
		if (51 <= i && i <= 60) {
			return 990;
		}
		if (61 <= i && i <= 69) {
			return 1170;
		}
		if (71 <= i && i <= 80) {
			return 1340;
		}
		if (81 <= i && i <= 90) {
			return 1520;
		}
		if (91 <= i && i <= 100) {
			return 1690;
		}
		if (101 <= i && i <= 120) {
			return 1980;
		}
		if (121 <= i && i <= 140) {
			return 2310;
		}
		if (141 <= i && i <= 160) {
			return 2640;
		}
		if (161 <= i && i <= 180) {
			return 3080;
		}
		if (181 <= i && i <= 200) {
			return 3410;
		}
		if (201 <= i && i <= 220) {
			return 3740;
		}
		if (221 <= i && i <= 240) {
			return 4070;
		}
		if (241 <= i && i <= 260) {
			return 4510;
		}
		if (261 <= i && i <= 280) {
			return 4840;
		}
		if (281 <= i && i <= 300) {
			return 5170;
		}
		if (301 <= i && i <= 320) {
			return 5500;
		}
		if (321 <= i && i <= 340) {
			return 5720;
		}
		if (341 <= i && i <= 360) {
			return 6050;
		}
		if (361 <= i && i <= 380) {
			return 6380;
		}
		if (381 <= i && i <= 400) {
			return 6600;
		}
		if (401 <= i && i <= 420) {
			return 6930;
		}
		if (421 <= i && i <= 440) {
			return 7150;
		}
		if (441 <= i && i <= 460) {
			return 7480;
		}
		if (461 <= i && i <= 480) {
			return 7700;
		}
		if (481 <= i && i <= 500) {
			return 8030;
		}
		if (501 <= i && i <= 520) {
			return 8360;
		}
		if (521 <= i && i <= 540) {
			return 8580;
		}
		if (541 <= i && i <= 560) {
			return 8910;
		}
		if (561 <= i && i <= 580) {
			return 9130;
		}
		if (581 <= i && i <= 600) {
			return 9460;
		}
		if (601 <= i && i <= 640) {
			return 9790;
		}
		if (641 <= i && i <= 680) {
			return 10010;
		}
		if (681 <= i && i <= 720) {
			return 10340;
		}
		if (721 <= i && i <= 760) {
			return 10670;
		}
		if (761 <= i && i <= 800) {
			return 11000;
		}
		if (801 <= i && i <= 840) {
			return 11330;
		}
		if (841 <= i && i <= 880) {
			return 11550;
		}
		if (881 <= i && i <= 920) {
			return 11880;
		}
		if (921 <= i && i <= 960) {
			return 12210;
		}
		if (961 <= i && i <= 1000) {
			return 12540;
		}
		if (1001 <= i && i <= 1040) {
			return 12870;
		}
		return 13420;
	}
}