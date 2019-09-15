package meteo.util.geodesy;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import meteo.util.Angles;
import meteo.util.Vector3;


/**
 * Polar coordinate
 * 
 * TODO dependency on LatLonPoint is redundant
 *
 * @author Fima
 */
public class PolarCoord
{
	/**
	 * distance from origin
	 */
	private double distance;

	/**
	 * longitude, theta
	 */
	private double azimuthAngle;

	/**
	 * latitude, phi
	 */
	private double zenithAngle;

	/**
	 *
	 * @param distance
	 * @param azimuthAngle degrees
	 * @param zenithAngle degrees
	 */
	public PolarCoord(final double distance, final double zenithAngle, final double azimuthAngle)
	{
		this.distance = distance;
//		System.out.println(zenithAngle + " : " + azimuthAngle);
//		assert azimuthAngle >= -180 && azimuthAngle <= 180;
//		assert zenithAngle >= -90 && zenithAngle <= 90;
		this.azimuthAngle = azimuthAngle;
		this.zenithAngle = zenithAngle;
	}
	
	public static PolarCoord toPolar(Vector3 vec)
	{
		double distance = vec.len();
		
		double theta = Angles.toDegrees(Math.acos(vec.z()/distance));
		
		double phi = Angles.toDegrees(Math.atan2(vec.y(), vec.x()));
		
		return new PolarCoord(distance, theta, phi);
	}

	public double getDistance() { return distance; }
	
	/**
	 * latitude ( radians )
	 */
	public double getZenith() { return zenithAngle * Angles.TO_RAD; }
	
	public double getZenithDeg() { return zenithAngle; }

	/**
	 * zenith
	 * @return
	 */
	public double getLatitude() { return zenithAngle; }

	/**
	 * longitude  ( radians )
	 */
	public double getAzimuth() { return azimuthAngle * Angles.TO_RAD; }
	/**
	 * azimuth
	 * @return
	 */

	public double getLongitude() { return azimuthAngle; }

	public boolean equals( final PolarCoord arg0 )
	{
		if(this == arg0) return true;
		return this.getLatitude() == arg0.getLatitude()
				&& this.getLongitude() == arg0.getLongitude();
	}

	static NumberFormat nf = new DecimalFormat("#0.0##");


	@Override
	public String toString()
	{
		return new StringBuilder()
			.append(   "D:" ) .append( nf.format(distance) )
			.append( "; LON:" ) .append( nf.format( getLongitude() ) )
			.append( "; LAT:" ) .append( nf.format( getLatitude() ) )
			.toString();
	}
	
	@Override
	public int hashCode()
	{
		// TODO: very bad
		return toString().hashCode();
	}

}
