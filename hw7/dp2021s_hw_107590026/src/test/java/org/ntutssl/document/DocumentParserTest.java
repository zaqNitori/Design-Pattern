package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Test;

public class DocumentParserTest 
{ 
    public DocumentParser documentParser = new DocumentParser();
    public String jsonTitle="{\"type\":\"title\",\"text\":\"I'm title.\",\"size\":\"1\"}";
    public String jsonParagraph="{\"type\":\"paragraph\",\"text\":\"I'm paragraph.\"}";
    public String jsonArticle="{\"type\":\"article\",\"text\":\"I'm article\",\"level\":\"1\"}";
    public String jsonArticleWithContents="{\"type\":\"article\",\"text\":\"I'm article\",\"level\":\"1\",\"contents\":[{\"type\": \"title\",\"text\": \"Information\",\"size\": \"2\"}]}";

    @Test
    public void testParseTitle()
    {
        JsonObject jo = (JsonObject)JsonParser.parseString(jsonTitle);
        assertNotNull(jo);
        assertEquals(jo.get("text").getAsString(), documentParser.parse(jo).getText());
    }

    @Test
    public void testParseParagraph()
    {
        JsonObject jo = (JsonObject)JsonParser.parseString(jsonParagraph);
        assertNotNull(jo);
        assertEquals(jo.get("text").getAsString(), documentParser.parse(jo).getText());
    }

    @Test
    public void testParseArticle()
    {
        JsonObject jo = (JsonObject)JsonParser.parseString(jsonArticle);
        assertNotNull(jo);
        assertEquals(jo.get("text").getAsString(), documentParser.parse(jo).getText());
    }

    @Test
    public void testParseArticleWithContents()
    {
        JsonObject jo = (JsonObject)JsonParser.parseString(jsonArticleWithContents);
        assertNotNull(jo);

        Document doc = documentParser.parse(jo);
        assertNotNull(doc);

        assertEquals(jo.get("text").getAsString(), doc.getText());
        assertEquals(jo.get("contents").getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString()
        , doc.iterator().next().getText());
    }

}