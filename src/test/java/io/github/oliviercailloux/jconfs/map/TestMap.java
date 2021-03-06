package io.github.oliviercailloux.jconfs.map;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mapsforge.map.reader.MapFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.oliviercailloux.jconfs.gui.GuiConference;

public class TestMap {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestMap.class);

	/**
	 * Method that test if you can create a MapFile from a file in classPath
	 * @throws IOException 
	 */
	@Test
	public void openMapTest() throws IOException{
		URL fileURL = TestMap.class.getResource("world.map");
		if (fileURL == null)
			throw new FileNotFoundException("file doesn't exist");
		LOGGER.debug("URL", fileURL);
		
		MapFile map = new MapFile(fileURL.getPath());
		LOGGER.debug("MapFile", map);
		assertEquals(map.startZoomLevel().doubleValue(), 5.0);
		assertEquals(map.startPosition().getLatitude(), 0.0);
		assertEquals(map.startPosition().getLongitude(), 0.0);
		map.startZoomLevel();
		assertEquals(Byte.MAX_VALUE, 127);
		map.close();

	}
}
