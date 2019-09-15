package meteo.util;

public class Tuple 
{
	double [] vals;
	
	public Tuple( double ... vals)
	{
		this.vals = vals;
	}
	
	public double get(int idx ) { return vals[idx]; }
}
