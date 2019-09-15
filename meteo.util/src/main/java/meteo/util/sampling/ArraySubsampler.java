package meteo.util.sampling;

public class ArraySubsampler
{
	
	public static int [][] subsample(int [][] input, int res, SamplingMethod method)
	{
		int ow = input.length;
		if( input.length != input[0].length)
			throw new IllegalArgumentException("Only rectangular arrays are implemented");
		
		if( ow % res != 0)
			throw new IllegalArgumentException("Requested resolution must be a divisor of " + input.length);
		 
		int scale = ow / res;
		
		int [][] output = new int [res][res];
		for(int x = 0; x < res; x ++)
			for(int y = 0; y < res; y ++)
			{
				int max = -10000000;
				
				for(int i = 0; i < scale; i ++)
					for(int j = 0; j < scale; j ++)
					{
						int value = input[scale*x+i][scale*y+j];
						if(value > max)
							max = value;
					}
				output[x][y] = max;
			}
		
		return output;
	}
}
