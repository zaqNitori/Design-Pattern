package org.ntutssl.document;

import com.google.gson.JsonObject;

class DocumentParser 
{
	private DocumentBuilder builder;

	public DocumentParser() 
	{ 
		builder = new DocumentBuilder();
	}

	private void build(JsonObject jObj)
	{
		String text = jObj.get("text").getAsString();
		switch(jObj.get("type").getAsString())
		{

			case "title":
				builder = builder.buildTitle(text,Integer.valueOf(jObj.get("size").getAsString()));
				break;
			case "paragraph":
				builder = builder.buildParagraph(text);
				break;
			case "article":
				builder.startBuildArticle(text, Integer.valueOf(jObj.get("level").getAsString()));
				jObj.get("contents").getAsJsonArray().forEach((obj) -> parse(obj.getAsJsonObject()));
				builder.endBuildArticle();
				break;
		}
	}

	/**
	 * parse a JsonObject as a Document
	 * @param jObj the json object to be parsed
	 */
	public Document parse(JsonObject jObj) 
	{ 
		build(jObj);
		return builder.getResult();
	}
}