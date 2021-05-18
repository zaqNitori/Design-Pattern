package org.ntutssl.document;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.util.Iterator;

public class Editor
{

	private List<String> strOutputList;
	private List<Document> docTarget;
	private List<Document> docList;
	private DocumentParser documentParser;

	public Editor() 
	{ 
		docList = new ArrayList<>();
		documentParser = new DocumentParser();
	}

	public void importDocumentFromJsonFile(String filePath) 
	{
		
        try(JsonReader reader = new JsonReader(new FileReader(filePath))) 
		{
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

			jsonArray.forEach((obj) -> docList.add(documentParser.parse(obj.getAsJsonObject())));
		} 
		catch (IOException e) 
		{
            e.printStackTrace();
        }

	}

	public void exportDocumentAsHtmlFile(String outputPath) 
	{ 
		strOutputList = new ArrayList<>();
		HtmlOutputConsumer hoc = new HtmlOutputConsumer(strOutputList);

		docList.forEach((doc) -> hoc.accept(doc));
		
		try(FileWriter writeFile = new FileWriter(outputPath)) 
		{
            for(String str: strOutputList)
				writeFile.write(str);
        } catch (IOException e) 
		{
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
	
	}

	public void findContent(String target) 
	{ 
		docTarget = new ArrayList<>();
		FindContentConsumer fcc = new FindContentConsumer(docTarget, target);
		
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

	public int size() 
	{ 
		return docList.size();
	}

	public Iterator<Document> iterator() 
	{ 
		Iterator<Document> docIter = docList.iterator();
		return docIter;
	}
}