package mcutils.gui.style.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import mcutils.gui.style.StyleField;

public class StyleClassDesc {
	
	File destination;
	String pkg;
	String clazzName;
	List<StyleFieldDesc> fields = new ArrayList<>();
	Set<String> clazzes = new HashSet<>();
	public StyleClassDesc(URL u) throws Exception
	{
		destination = new File(u.getPath().replace(".xml", ".java").replace("bin","src"));
		
		clazzName = destination.getName();
		int index = clazzName.indexOf('.');
		clazzName = clazzName.substring(0, index);
		
		class Thing extends DefaultHandler
		{
			
			StyleFieldDesc currentField = null;
			String lastKnownTag = null;
			
			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes)
					throws SAXException
			{
				
				if(qName.equals("class"))
					return;
				
				if(qName.equals("field"))
				{
					String name = attributes.getValue(0);
					currentField = new StyleFieldDesc(clazzName,name);
					fields.add(currentField);
					lastKnownTag = null;
				}
				else
				{
					lastKnownTag = qName;
				}
			}
			
			@Override
			public void characters(char[] ch, int start, int length) throws SAXException
			{
				String val = new String(ch,start,length);
				if(lastKnownTag == null)
					return;
				if(lastKnownTag.equals("package"))
					pkg = val;
				if(currentField == null)
					return;
				if(lastKnownTag.equals("display-name"))
					currentField.setDisplayName(val);
				if(lastKnownTag.equals("type"))
					currentField.setType(val);
				if(lastKnownTag.equals("default-value"))
					currentField.setDefaultValue(val);
				if(lastKnownTag.equals("description"))
					currentField.setDescription(val);
			}
			
			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException
			{
				if(qName.equals("class"))
					return;
				if(qName.equals("field"))
				{
					if(currentField != null && currentField.getType() != null)
						clazzes.add(currentField.getType());
					currentField = null;
				}
				else
					lastKnownTag = null;
			}
		}
		
		SAXParserFactory spf = SAXParserFactory.newInstance();
//	    spf.setNamespaceAware(true);
	    SAXParser saxParser = spf.newSAXParser();
	    XMLReader xmlReader = saxParser.getXMLReader();
	    xmlReader.setContentHandler(new Thing());
	    
	    xmlReader.parse(u.toString());
	}
	
	public void write() throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("package " + pkg + ";");
		sb.append("\n");
		
		List<String> imports = generateImports();
		for(String i : imports)
		{
			sb.append("\nimport " + i + ";");
		}
		sb.append("\n");
		
		sb.append("\npublic class " + clazzName + " implements StyleInterface");
		sb.append("\n{");
		
		for(StyleFieldDesc f : fields)
			sb.append("\n\t" + f.getInitializer() + ";");
		
		sb.append("\n\t");
		sb.append("\n\tprivate static LinkedHashMap<String,StyleField<?>> fieldMap = new LinkedHashMap<>();");
		sb.append("\n\t");
		
		sb.append("\n\tstatic");
		sb.append("\n\t{");
		
		for(StyleFieldDesc f : fields)
			for(String prop : f.getProperties())
				sb.append("\n\t\t" + prop + ";");
		
		sb.append("\n\t}");
		
		
		
		sb.append("\n\tprivate StyleMetaData metaData = new StyleMetaData(fieldMap);");
		sb.append("\n\t");
		
		for(StyleFieldDesc f : fields)
			sb.append("\n\t" + f.getField());
		
		
		sb.append("\n\t");
		
		
		
		for(StyleFieldDesc f : fields)
			sb.append("\n\t" + f.getGetter());

		sb.append("\n\t");
		
		for(StyleFieldDesc f : fields)
			sb.append("\n\t" + f.getSetter());
		
		sb.append("\n\t");
		sb.append("\n\tpublic StyleMetaData getMetaData() { return metaData; }");
		
		sb.append("\n\t");
		sb.append("\n\tpublic static void addField(StyleField<?> f) { fieldMap.put(f.getName(),f); }");
		
		sb.append("\n}");
		
		System.out.println("Writing " + destination.getPath());
		if(destination.exists())
			destination.delete();
		FileWriter w = new FileWriter(destination);
		w.write(sb.toString());
		w.close();
	}
	
	private List<String> generateImports()
	{
		List<String> imports = new ArrayList<>();
		imports.add("mcutils.gui.style.StyleField");
		imports.add("mcutils.gui.style.StyleInterface");
		imports.add("mcutils.gui.style.StyleMetaData");
		imports.add("java.util.LinkedHashMap");
		if(clazzes.contains("Color"))
			imports.add("java.awt.Color");
		
		return imports;
	}
}
