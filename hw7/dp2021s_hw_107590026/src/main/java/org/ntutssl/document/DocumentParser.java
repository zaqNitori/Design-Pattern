package org.ntutssl.document;

import com.google.gson.JsonObject;

class DocumentParser 
{
	private DocumentBuilder builder;

	public DocumentParser() 
	{
		builder = new DocumentBuilder();
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

	private void build(JsonObject jsonObject)
	{
		String text = jsonObject.get("text").getAsString();

		switch (jsonObject.get("type").getAsString()) 
		{
			case "title":
				builder.buildTitle(text,Integer.parseInt(jsonObject.get("size").getAsString()));
				break;
			case "paragraph":
				builder.buildParagraph(text);
				break;
			case "article":
				builder.startBuildArticle(text, Integer.parseInt(jsonObject.get("level").getAsString()));
				if(jsonObject.get("contents") != null)
					jsonObject.get("contents").getAsJsonArray().forEach((obj) -> build(obj.getAsJsonObject()));
				builder.endBuildArticle();
				break;
		}
	}

}
