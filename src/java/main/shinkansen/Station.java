package shinkansen;

public class Station {
	private int id;
	private String name;
	private double kmFromTokyo;

	public Station(int id, String name, double kmFromTokyo) {
		this.id = id;
		this.name = name;
		this.kmFromTokyo = kmFromTokyo;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public double getKmFromTokyo() {
		return this.kmFromTokyo;
	}
}
