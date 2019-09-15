package meteo.util.sampling;

public interface SamplingMethod
{
	public float sample(float [][] input, int i, int j, int irad, int jrad);
	
	
	public static class MaxSampling implements SamplingMethod
	{

		@Override
		public float sample( float[][] input, int i, int j, int irad, int jrad )
		{
			throw new UnsupportedOperationException();
		}
		
	}
}
