package meteo.util;

public class Calc
{

	public static final double G = 9.8;
	public static double ZERO_CELSIUS = -273.15;

	public static double toFarenheit(final double Tcelsius)
	{
		return 9f/5f * Tcelsius + 32;
	}

	public static double toCelsius(final double Tfarenheit)
	{
		return 5f/9f * (Tfarenheit - 32);
	}

	/**
	 *
	 * @param T - temperature in celsius
	 * @return
	 */
	public static double calcSaturatedVaporPressure(final double Tcelsius)
	{
		double power = 7.5 * Tcelsius / (237.3 + Tcelsius);
		return 6.11 * Math.pow( 10, power );
	}

	/**
	 *
	 * @param T - temperature in celsius
	 * @param RH - relative humidity [0,1]
	 * @return
	 */
	public static double calcActualVaporTemperature( final double Tcelsius, final double RH )
	{
		return RH * calcSaturatedVaporPressure( Tcelsius ) / 100;
	}

	/**
	 * Refraction index * 10^6
	 * @param Tcelsius
	 * @param Ppa
	 * @param RH
	 * @return
	 */
	public static double calcRefractionCoef( final double Tcelsius, final double Phpa, final double RH)
	{
		double Tk = Tcelsius - ZERO_CELSIUS;
		// vapor pressure:
		double e = calcActualVaporTemperature( Tcelsius, RH );
		
		double A = 77.6 * Math.pow( 10, -6 ) * Phpa / Tk;
//		System.out.println(A);
		double B = 0.373*e / (Tk*Tk);
//		System.out.println(B);
		return 1 + A + B;
		
//		return 1 + (77.6890 * Phpa / Tcelsius + 71.2952 * e / Tcelsius + 375463 * e / (Tcelsius*Tcelsius)) / 1000000f;
		// TODO + 40.3* Ne / (f*f) // Ne - electron pressure, f - ray frequency
	}
	
	public static void main(String ... arg)
	{
		double T = 25;
		double RH = 100;
		
//		System.out.println("e = " + calcActualVaporTemperature(T, RH));
		
		System.out.println("es = " + calcSaturatedVaporPressure(16.9));
		System.out.println("e = " + calcActualVaporTemperature(16.9, 100));

		System.out.println("Ri = " + calcRefractionCoef(16.9, 912, 100));
		
		System.out.println("es = " + calcSaturatedVaporPressure(20.2));
		System.out.println("e = " + calcActualVaporTemperature(20.2, 34));
		
		System.out.println("Ri = " + calcRefractionCoef(20.2, 902, 34));
//		System.out.println(calcSaturatedVaporPressure(30));
	}
}
