package meteo.util;

public class TextUtil
{
	
	public static final String SEP = System.getProperty("file.separator");
	
	public static String concat (String separator, String ... args)
	{
		StringBuilder res = new StringBuilder();
		
		for(int idx = 0; idx < args.length-1; idx ++)
			res.append(args[idx]).append(separator);
		
		res.append(args[args.length-1]);
		
		return res.toString();

	}

}
