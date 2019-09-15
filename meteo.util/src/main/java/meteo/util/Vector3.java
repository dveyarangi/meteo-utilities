package meteo.util;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import meteo.util.geodesy.PolarCoord;

public class Vector3 implements Serializable
{
	private double x, y, z;




	/**
	 * Vector factory method
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static Vector3 R( final double x, final double y, final double z )
	{
		return new Vector3(x, y, z);
	}

	/**
	 * Create copy of the specified vector
	 *
	 * @param v
	 */
	public static Vector3 COPY( final Vector3 v )
	{
		return R(v.x, v.y, v.z);
	}

	/**
	 * Create new zero vector
	 */
	public static Vector3 ZERO()
	{
		return R(0,0,0);
	}

	/**
	 *
	 *
	 * @param distance
	 * @param inclination (lat) - radians
	 * @param azimuth (lon) - radians
	 * @return
	 */
	public static Vector3 POLAR( final double distance, final double inclination, final double azimuth )
	{
		return R(
				Math.sin( inclination ) * Math.cos( azimuth ) * distance,
				Math.sin( inclination ) * Math.sin( azimuth ) * distance,
				Math.cos( inclination ) * distance
			);
	}

	public static Vector3 POLAR(PolarCoord polar)
	{
		return POLAR(polar.getDistance(), polar.getZenith(), polar.getAzimuth() );
	}

	public static Vector3 GEODETIC( final double distance, final double lat, final double lon )
	{
		return R(
				Math.sin( (90-lat)*Angles.TO_RAD ) * Math.cos( lon*Angles.TO_RAD ) * distance,
				Math.sin( (90-lat)*Angles.TO_RAD ) * Math.sin( lon*Angles.TO_RAD ) * distance,
				Math.cos( (90-lat)*Angles.TO_RAD ) * distance
			);
	}

	/**
	 * Private constructor; to create vector instances use static factory methods
	 * @param x
	 * @param y
	 * @param z
	 */
	private Vector3( final double x, final double y, final double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double x() { return x; }
	public double y() { return y; }
	public double z() { return z; }
	public float xf() { return (float) x; }
	public float yf() { return (float) y; }
	public float zf() { return (float) z; }

	public double len2() { return x*x + y*y + z*z; }
	public double len() { return Math.sqrt( this.len2() ); }

	public Vector3 nor()
	{
		double len = this.len();
		if(len <= 0)
			return this;
		x = x / len;
		y = y / len;
		z = z / len;

		return this;
	}

	public Vector3 mul( final double scalar )
	{
		return R( x * scalar, y * scalar, z * scalar );
	}

	public Vector3 add( final double scalar )
	{
		return R( x + scalar, y + scalar, z + scalar );
	}

	public Vector3 add( final Vector3 v )
	{
		return R( x + v.x(), y + v.y(), z + v.z() );
	}

	public Vector3 sub( final Vector3 v )
	{
		return R( x - v.x(), y - v.y(), z - v.z() );
	}

	public double dot( final Vector3 v )
	{
		return x * v.x() + y * v.y() + z * v.z();
	}

	public Vector3 cross( final Vector3 that )
	{
		double cx = this.y*that.z - this.z*that.y;
		double cy = this.z*that.x - this.x*that.z;
		double cz = this.x*that.y - this.y*that.x;

		return R(cx, cy, cz);
	}

	public Vector3 copy() { return COPY( this ); }

	/**
	 * cosine of angle between this and the provided vector.
	 * @param v
	 * @return
	 */
	public double cos(final Vector3 v)
	{
		double thisLen = this.len();
		double thatLen = v.len();

		return this.dot( v ) / (thisLen * thatLen);
	}

	static NumberFormat nf = new DecimalFormat("#0.000");

	@Override
	public String toString()
	{
		return new StringBuilder()
			.append( "[X:" ).append( nf.format(x) )
			.append( "; Y:").append( nf.format(y) )
			.append( "; Z:" ).append( nf.format(z) ).append("]")
			.toString();
	}

	public Vector3 set(Vector3 v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		return this;
	}

	public boolean isNaN() { return Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z); }

	public Vector3 setxyz(double x, double y, double z)
	{
		this.x = x; this.y = y; this.z = z;
		return this;
	}
	
	public Vector3 increase(Vector3 ap)
	{
		this.x += ap.x;
		this.y += ap.y;
		this.z += ap.z;
		return this;
	}

	public Vector3 substract(Vector3 ap)
	{
		this.x -= ap.x;
		this.y -= ap.y;
		this.z -= ap.z;
		return this;
	}

	public Vector3 multiply(double scale)
	{
		this.x *= scale;
		this.y *= scale;
		this.z *= scale;
		return this;
	}

	public Vector3 minus() {
		return Vector3.R(-this.x, -this.y, -this.z);
	}


}
