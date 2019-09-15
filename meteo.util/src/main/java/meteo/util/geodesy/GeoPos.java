package meteo.util.geodesy;

public class GeoPos 
{
	private double lat;
	private double lon;
	private double elevation;
	
	public GeoPos(double lat, double lon, double elevation)
	{
		this.lat = lat;
		this.lon = lon;
		this.elevation = elevation;
	}
	
	public double lat() { return lat; }
	public double lon() { return lon; }
	public double elevation() { return elevation; }

}
