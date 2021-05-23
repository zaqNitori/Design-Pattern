package org.ntutssl.document;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class Editor 
{  
	private List<String> strOutputList;
	private List<Document> docTarget;
	private List<Document> docList;
	private DocumentParser docParser;

	public Editor() 
	{
		strOutputList = new ArrayList<>();
		docTarget = new ArrayList<>();
		docList = new ArrayList<>();
		docParser = new DocumentParser();
	}

	public void importDocumentFromJsonFile(String filePath) 
	{
		try (JsonReader reader = new JsonReader(new FileReader(filePath)))
		{
			JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

			jsonArray.forEach((obj) -> docList.add(docParser.parse(obj.getAsJsonObject())));
		} 
		catch (IOException e) 
		{
			//TODO: handle exception
			e.printStackTrace();
		}
	}

	public void exportDocumentAsHtmlFile(String outputPath) 
	{
		HtmlOutputConsumer hoc = new HtmlOutputConsumer(strOutputList);

		docList.forEach((doc) -> hoc.accept(doc));
		
		try(FileWriter writer = new FileWriter(outputPath))
		{
			for(String str: strOutputList)
				writer.write(str);
		}
		catch (IOException e) 
		{
			//TODO: handle exception
			e.printStackTrace();
		}
	}

	public void findContent(String target) 
	{
		if(target.equals(""))
			return;
		FindContentConsumer	fcc = new FindContentConsumer(docTarget, target);
		docList.forEach((doc) -> fcc.accept(doc));

		docTarget.forEach((doc) -> System.out.print(doc.toString()));
	}

	public List<Document> getFindContent()
	{
		return docTarget;
	}

	public void add(Document document) 
	{
		docList.add(document);
	}

	public void remove(Document document) 
	{
		docList.remove(document);
	}

	public int size() 
	{
		return docList.size();
	}

	public Iterator<Document> iterator() 
	{
		return docList.iterator();
	}
}
