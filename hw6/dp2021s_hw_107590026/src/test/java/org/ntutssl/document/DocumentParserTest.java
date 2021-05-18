package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Test;

public class DocumentParserTest 
{ 
    public DocumentParser documentParser = new DocumentParser();
    public String jsonTitle="{\"type\":\"title\",\"text\":\"I'm title.\",\"size\":\"1\"}";
    public String jsonParagraph="{\"type\":\"paragraph\",\"text\":\"I'm paragraph.\"}";
    public String jsonArticle="{\"type\":\"article\",\"text\":\"I'm article\",\"level\":\"1\"}";

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

}