package org.ntutssl.document;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

import static org.junit.Assert.assertEquals;

public class TitleTest 
{  
    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void testCorrect_getText()
    {
        Title title = new Title("game");

        assertEquals(title.getText(), "game");
    }

    @Test
    public void testUse_addMethod()
    {
        Title title = new Title("game");

        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid Behavior: add.");
        title.add(new Title("music"));
    }

    @Test
    public void testUse_getLevelMethod()
    {
        Title title = new Title("game");

        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid Behavior: getLevel.");
        title.getLevel();
    }

    @Test
    public void testUse_getContentMethod()
    {
        Title title = new Title("game");

        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid Behavior: getContent.");
        title.getContent(1);
    }
}
