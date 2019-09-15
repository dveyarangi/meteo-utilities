package meteo.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedCanvas 
{
	BufferedImage image;
	
	Graphics2D g2d;
	
	public BufferedCanvas(int width, int height)
	{
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g2d = image.createGraphics();
	}
	
	public void writeImage(String name)
	{
		try
		{
			ImageIO.write( image, "png", new File( name ));
		} 
		catch (IOException e) { throw new RuntimeException("Failed to write image to " + name); }		
	}

	public Graphics2D g2d() { return g2d; }

}
