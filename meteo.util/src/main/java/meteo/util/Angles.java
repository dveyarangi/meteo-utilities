package meteo.util;

public class Angles
{
	public static final double PI = Math.PI;
	public static final double TAU = Math.PI * 2;
	public static final double PI_div_2 = Math.PI / 2;
	public static final double PI_div_3 = Math.PI / 3;
	public static final double PI_div_4 = Math.PI / 4;
	public static final double PI_div_5 = Math.PI / 5;
	public static final double PI_div_6 = Math.PI / 6;
	public static final double PI_div_12 = Math.PI / 12;
	public static final double PI_div_10 = Math.PI / 10;
	public static final double PI_div_20 = Math.PI / 20;
	public static final double PI_div_40 = Math.PI / 40;

	public static final double TO_RAD = PI / 180.;
	public static final double TO_DEG = 180. / PI;

	public static final int FINESSE = 720;

	private static final double[] SIN = new double[FINESSE];
	private static final double[] COS = new double[FINESSE];

	public final static double TRIG_STEP = TAU / FINESSE;
	public final static double INV_TRIG_STEP = FINESSE / TAU;
	static
	{
		double angle = 0;
		for ( int idx = 0; idx < FINESSE; idx++ )
		{
			SIN[idx] = Math.sin( angle );
			COS[idx] = Math.cos( angle );
			angle += TRIG_STEP;
		}
	}

	public static double SIN(final double angle)
	{
		return SIN[toTrigoIndex( angle )];
	}

	public static double COS(final double angle)
	{
		return COS[toTrigoIndex( angle )];
	}

	public static double atan2Deg(final double y, final double x)
	{
		return Angles.atan2( y, x ) * TO_DEG;
	}

	public static final float atan2DegStrict(final float y, final float x)
	{
		return (float) (Math.atan2( y, x ) * TO_DEG);
	}

	public static final float atan2(double y, double x)
	{
		float add, mul;

		if ( x < 0.0f )
		{
			if ( y < 0.0f )
			{
				x = -x;
				y = -y;

				mul = 1.0f;
			} else
			{
				x = -x;
				mul = -1.0f;
			}

			add = -3.141592653f;
		} else
		{
			if ( y < 0.0f )
			{
				y = -y;
				mul = -1.0f;
			} else
			{
				mul = 1.0f;
			}

			add = 0.0f;
		}

		double invDiv = ATAN2_DIM_MINUS_1 / (x < y ? y : x);

		int xi = (int) (x * invDiv);
		int yi = (int) (y * invDiv);

		return (atan2[yi * ATAN2_DIM + xi] + add) * mul;
	}

	private static final int ATAN2_BITS = 7;

	private static final int ATAN2_BITS2 = ATAN2_BITS << 1;
	private static final int ATAN2_MASK = ~(-1 << ATAN2_BITS2);
	private static final int ATAN2_COUNT = ATAN2_MASK + 1;
	private static final int ATAN2_DIM = (int) Math.sqrt( ATAN2_COUNT );

	private static final float ATAN2_DIM_MINUS_1 = ATAN2_DIM - 1;

	private static final float[] atan2 = new float[ATAN2_COUNT];

	static
	{
		for ( int i = 0; i < ATAN2_DIM; i++ )
		{
			for ( int j = 0; j < ATAN2_DIM; j++ )
			{
				float x0 = (float) i / ATAN2_DIM;
				float y0 = (float) j / ATAN2_DIM;

				atan2[j * ATAN2_DIM + i] = (float) Math.atan2( y0, x0 );
			}
		}
	}

	private final static int toTrigoIndex(final double angle)
	{
		int idx = FastMath.round( angle * INV_TRIG_STEP ) % FINESSE;
		if(idx < 0)
			idx += FINESSE;
		return idx;
	}

	public static double toRadians(final double degrees)
	{
		return degrees * TO_RAD;
	}

	public static double toDegrees(final double radians)
	{
		return radians * TO_DEG;
	}

	/**
	 * Compresses the angle into [-180, 180] range.
	 *
	 * @param angle
	 * @return
	 */
	public static double normalize(final double angle)
	{
		return angle < -PI_div_2 ? angle + TAU : angle > PI_div_2 ? angle - TAU : angle;
	}

	/**
	 * Adjusts the oldAngle in the closest direction to targetAngle by step.
	 *
	 * @param oldAngle
	 * @param targetAngle
	 * @param step
	 * @return
	 */
	public static double stepTo(final double oldAngle, final double targetAngle, final double step)
	{
		double diff = normalize( oldAngle - targetAngle );
		double absDiff = Math.abs( diff );

		return normalize( oldAngle + (diff < 0 ? 1 : -1) * (absDiff < step ? absDiff : step) );
	}

	/**
	 * Steps the oldAngle towards the targetAngle, making last steps gradually
	 * slowing down, for "softness" effect. A bit slower than the
	 * {@link #stepTo(double, double, double)} operation.
	 *
	 * Reaches target after (abs(targetAngle-oldAngle) - step*softness) + log
	 * b(1/softness) (step*softness)
	 *
	 * @param oldAngle
	 * @param targetAngle
	 * @param step
	 *            initial step size
	 * @param softness
	 * @return
	 */
	public static double stepSoftly(final double oldAngle, final double targetAngle, final double step, final double softness)
	{
		double diff = normalize( oldAngle - targetAngle );
		double absDiff = Math.abs( diff );

		double dir = diff < 0 ? 1 : -1;

		if ( absDiff <= softness * step )
		{
			double softStep = absDiff / softness;
			if ( softStep < 1 / (softness * softness) )
				return normalize( oldAngle + absDiff );
			return normalize( oldAngle + dir * absDiff / softness );
		}

		return normalize( oldAngle + dir * step );
	}

}
