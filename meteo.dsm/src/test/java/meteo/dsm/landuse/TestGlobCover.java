package meteo.dsm.landuse;

import java.io.IOException;

import org.junit.Test;

import com.badlogic.gdx.graphics.Color;

public class TestGlobCover
{
	@Test
	public void testGlobCover() throws IOException
	{
		GlobCover cover = new GlobCover();
		
		Color color = cover.getColor(32, 32);
		
		System.out.println(color);
	}
}
