package shinkansen;

public class ReservedSeat {
	private int id;
	private String name;
	private int limitedTicket;

	public ReservedSeat(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public int limitedTicket() {
		return this.limitedTicket;
	}

	public Integer getLimitedTicket(double kmFromTokyo, int id) {
		switch (id) {
		case 1:
			break;
		case 2:
			return this.limitedTicket + 530;
		case 3:
			if (kmFromTokyo <= 400) {
				return this.limitedTicket + 530 + 210;
			}
			return this.limitedTicket + 530 + 320;
		}
		return this.limitedTicket;
	}

	public void setTicketPrice(double kmFromTokyo) {
		if (1 <= kmFromTokyo && kmFromTokyo <= 100) {
			this.limitedTicket = 1760;
		}
		if (101 <= kmFromTokyo && kmFromTokyo <= 200) {
			this.limitedTicket = 2530;
		}
		if (201 <= kmFromTokyo && kmFromTokyo <= 300) {
			this.limitedTicket = 3400;
		}
		if (301 <= kmFromTokyo && kmFromTokyo <= 400) {
			this.limitedTicket = 4180;
		}
		if (401 <= kmFromTokyo && kmFromTokyo <= 500) {
			this.limitedTicket = 4620;
		}
		if (501 <= kmFromTokyo && kmFromTokyo <= 600) {
			this.limitedTicket = 4960;
		}
		if (601 <= kmFromTokyo && kmFromTokyo <= 700) {
			this.limitedTicket = 5390;
		}
		if (701 <= kmFromTokyo && kmFromTokyo <= 800) {
			this.limitedTicket = 5930;
		}
		if (801 <= kmFromTokyo && kmFromTokyo <= 900) {
			this.limitedTicket = 6500;
		}
		if (901 <= kmFromTokyo && kmFromTokyo <= 1000) {
			this.limitedTicket = 7040;
		}
		if (1001 <= kmFromTokyo && kmFromTokyo <= 1100) {
			this.limitedTicket = 7600;
		}
	}

}
