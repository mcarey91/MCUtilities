package mcutils.gui.style.generator;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class StyleGen {
	public static void main(String[] args) {
		try {
			doTheThing();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	private static void doTheThing() throws IOException, URISyntaxException
	{
		List<URL> files = new ArrayList<>();
		URL styleList = StyleGen.class.getResource("Styles.txt");
		try (Stream<String> stream = Files.lines(Paths.get(styleList.toURI())))
		{
	        stream.forEach(l -> {
	        	URL u = StyleGen.class.getResource(l);
	        	if(u != null)
	        		files.add(u);
	        	else
	        		System.err.println("File not found: " + u.getFile());
	        });
		}
		for(URL u  : files)
		{
			try {
				StyleClassDesc c = new StyleClassDesc(u);
				c.write();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
