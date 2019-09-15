package meteo.util;


public class Geometry
{
	public static double calcDistanceToLine(final Vector3 P, final Vector3 Q, final Vector3 v)
	{
		Vector3 A = P.copy().substract(Q);
		Vector3 D = v.copy().nor();
		
		double Blen = A.dot(D);
		
		Vector3 B = D.mul( Blen );
		Vector3 C = A.substract(B);
		
		return C.len();
	}
}
