package org.ntutssl.document;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.util.Iterator;

public class Editor
{

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

			//jsonArray.forEach((obj) -> docList.add(documentParser.parse(obj.getAsJsonObject())));

			for(JsonElement jsonElement: jsonArray)
			{
				docList.add(documentParser.parse(jsonElement.getAsJsonObject()));
			}
		} 
		catch (IOException e) 
		{
            e.printStackTrace();
        }

	}

	public void exportDocumentAsHtmlFile(String outputPath) { }

	public void findContent(String target) 
	{ 
		List<Document> docTarget = new ArrayList<>();
		FindContentConsumer fcc = new FindContentConsumer(docTarget, target);
		for(Document doc: docList)
			fcc.accept(doc);
			
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