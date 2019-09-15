package meteo.util;

public class FastMath
{
	private static final int BIG_ENOUGH_INT = 16 * 1024;
	private static final float BIG_ENOUGH_FLOOR = BIG_ENOUGH_INT;
	private static final float BIG_ENOUGH_ROUND = BIG_ENOUGH_INT + 0.5f;

	public static final double SQRT2 = Math.sqrt( 2 );
	public static final double SQRT3 = Math.sqrt( 3 );

	public static int floor(double x)
	{
		return (int) (x + BIG_ENOUGH_FLOOR) - BIG_ENOUGH_INT;
	}

	public static int round(double x)
	{
		return (int) (x + BIG_ENOUGH_ROUND) - BIG_ENOUGH_INT;
	}

	public static int ceil(double x)
	{
		return BIG_ENOUGH_INT - (int) (BIG_ENOUGH_FLOOR - x); // credit: roquen
	}

	public static int floor(float x)
	{
		return (int) (x + BIG_ENOUGH_FLOOR) - BIG_ENOUGH_INT;
	}

	public static int round(float x)
	{
		return (int) (x + BIG_ENOUGH_ROUND) - BIG_ENOUGH_INT;
	}

	public static int ceil(float x)
	{
		return BIG_ENOUGH_INT - (int) (BIG_ENOUGH_FLOOR - x); // credit: roquen
	}

	public static int toGrid(int val, int cell)
	{
		return val - val % cell;
		// return val/cell * cell; // embrace eternity!
	}

	public static double toGrid(double val, double cell)
	{
		return round( val / cell ) * cell;
	}

	public static float toGrid(float val, float cell)
	{
		return round( val / cell ) * cell;
	}

	public static int toGrid(double val, int cell)
	{
		return round( val / cell ) * cell;
	}

	public static int toGrid(float val, int cell)
	{
		return round( val / cell ) * cell;
	}

	public static double powOf2(double d)
	{
		return d * d;
	}

	public static float powOf2(float d)
	{
		return d * d;
	}
	
	public static int log2(int n){
	    if(n <= 0) throw new IllegalArgumentException("Argument cannot be <= 0");
	    return 31 - Integer.numberOfLeadingZeros(n);
	}
}
