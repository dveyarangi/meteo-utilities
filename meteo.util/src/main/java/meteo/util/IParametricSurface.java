package meteo.util;

public interface IParametricSurface 
{
	/**
	 * Calculate surface coordinate in specified position into result vector
	 * @param u
	 * @param v
	 * @param result
	 * @return also result
	 */
	public Vector3 at(double u, double v, Vector3 result);
}
