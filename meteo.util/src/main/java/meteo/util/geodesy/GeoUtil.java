package meteo.util.geodesy;

import meteo.util.Angles;
import meteo.util.Vector3;

public class GeoUtil
{


	public static double getEarthRadiusKm(double lat)
	{
//		System.out.println("");
		return getEarthRadiusKm(lat, Datum.WGS_84);
	}

	public static double getEarthRadiusKm(double lat, Datum datum)
	{
		double sin = Math.sin( lat*Angles.TO_RAD );
		double cos = Math.cos( lat*Angles.TO_RAD );
		double a = datum.getEquatorialRadius();
		double b = datum.getPolarRadius();

		return Math.sqrt(((a*a*cos)*(a*a*cos) + (b*b*sin)*(b*b*sin)) / ((a*cos)*(a*cos) + (b*sin)*(b*sin)));
	}

	/**
	 * Returns spherical coordinates for provided vector

	 * @param v
	 * @return
	 */
	public static PolarCoord toGeodeticCoords( final Vector3 v )
	{
		return toGeodeticCoords( v.x(), v.y(), v.z() );
	}
	public static PolarCoord toGeodeticCoords( final double x, final double y, final double z)
	{
		double radius = Math.sqrt( x*x + y*y + z*z );
		double theta = Math.atan( y / x ) * Angles.TO_DEG;
		double phi = Math.acos( z / radius ) * Angles.TO_DEG;
		double longitude = theta;
		double latitude = 90 - phi;

		return new PolarCoord(
				radius,
				latitude,
				longitude
			);
	}

	public static PolarCoord toSphericalCoords( final double x, final double y, final double z)
	{
		double radius = Math.sqrt( x*x + y*y + z*z );

		double phi = Math.acos( z / radius ) * Angles.TO_DEG;
		double theta = Math.atan( y / x ) * Angles.TO_DEG;

		return new PolarCoord(
				radius,
				phi,
				theta
			);
	}

	/*public static com.badlogic.gdx.math.Vector3 toCartesianVector( float lat, float lon, float distance )
	{
		return new com.badlogic.gdx.math.Vector3(
				Math.sin( (90-lat)*Angles.TO_RAD ) * Math.cos( lon*Angles.TO_RAD ) * distance,
				Math.sin( (90-lat)*Angles.TO_RAD ) * Math.sin( lon*Angles.TO_RAD ) * distance,
				Math.cos( (90-lat)*Angles.TO_RAD ) * distance
			);

	}*/
}
