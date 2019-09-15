package meteo.util;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Debug
{
	public static final String DONE_ = "done ";
	public static final String PERCENT = "%";
	public static final String ELLIPSIS = "...";
	public static final String _OF_ = " of ";
	public static final String __ = " ";

	public static void logPercents(Logger log, String preffix, final double x, double y, final double fractions)
	{


		StringBuilder sb = new StringBuilder();
		// logging completion percentage:
		//		System.out.println(y % 100 + " " +  y);
		double percent = 100.0*x/y;

		double f = Math.pow( 10, fractions );
		double val =  Math.round(percent*f) / f;
		/*		if(percent*f == Math.round(percent*f))
		{
			log(sb.append(DONE_).append( percent ).append( PERCENT ).append( ELLIPSIS ));
		} */
		log(sb.append(preffix).append(__).append(DONE_).append( x ).append( _OF_ ).append( y )
				.append(__)
				.append( val ).append(PERCENT).toString());
	}

	public static void log(final String line)
	{
		log.debug( line );
	}

	public static void log(final double ... ds)
	{
		StringBuilder sb = new StringBuilder();
		for(double d : ds)
			sb.append(d).append(" ");
		log( sb.toString() );
	}

	public static void log(final int ... ds)
	{
		StringBuilder sb = new StringBuilder();
		for(double d : ds)
			sb.append(d).append(" ");
		log( sb.toString() );
	}
}
