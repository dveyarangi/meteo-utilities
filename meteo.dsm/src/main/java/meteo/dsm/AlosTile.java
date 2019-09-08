package meteo.dsm;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.GridGeometry2D;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.OverviewPolicy;
import org.geotools.gce.geotiff.GeoTiffReader;
import org.opengis.parameter.GeneralParameterValue;
import org.opengis.parameter.ParameterValue;

import lombok.Getter;
import midas.core.spatial.AOI;

public class AlosTile
{
	private String filenamePreffix;
	
	@Getter private String tileId;
	
	private static final String TILE_FOLDER_FMT = "N%03dE%03d_N%03dE%03d";
	private static final String TILE_FMT = "N%03dE%03d_AVE_";
	
	@Getter private AOI coverage; 
	
	public AlosTile(String dataRoot, int lat, int lon)
	{
		
		this.coverage = new AOI(lat+0.5f, lon+0.5f, 0.5f, 0.5f);
		
		this.tileId = createTileId(this.coverage);
		
		filenamePreffix = new StringBuilder()
				.append(dataRoot)
				.append("/")
				.append(createFolderName(lat, lon))
				.append("/")
				.append(tileId)
				.toString();
	}
	
	private String createFolderName( int lat, int lon )
	{
		int latWidth = 5;
		int lonWidth = 5;
		
		int folderLat = lat / latWidth * latWidth;
		int folderLon = lon / lonWidth * lonWidth;		
		
		String folderName = new StringBuilder()
			.append(String.format(TILE_FOLDER_FMT, folderLat, folderLon, folderLat + latWidth, folderLon+lonWidth))
			.toString();
		
		return folderName;
	}

	private String createTileId(AOI aoi)
	{
		return String.format(TILE_FMT, (int)aoi.getMinLat(), (int)aoi.getMinLon());
	}
	
	public GridCoverage2D readDSMGrid() throws IOException
	{
		return readGeotiff(filenamePreffix+"DSM.tif");
	}
	private GridCoverage2D readGeotiff(String filename) throws IOException
	{
		AlosCfg cfg = new AlosCfg();
		
		File f = new File(filename);

		ParameterValue<OverviewPolicy> policy = AbstractGridFormat.OVERVIEW_POLICY.createValue();
		policy.setValue(OverviewPolicy.IGNORE);

		ParameterValue<String> gridsize = AbstractGridFormat.SUGGESTED_TILE_SIZE.createValue();
		ParameterValue<Boolean> useJaiRead = AbstractGridFormat.USE_JAI_IMAGEREAD.createValue();
		useJaiRead.setValue(true);

		GridCoverage2D image = new GeoTiffReader(f).read(new GeneralParameterValue[]{policy, gridsize, useJaiRead});

		Rectangle2D bounds2D = image.getEnvelope2D().getBounds2D();     
		GridGeometry2D geometry = image.getGridGeometry();
		
		return image;
	}
}
