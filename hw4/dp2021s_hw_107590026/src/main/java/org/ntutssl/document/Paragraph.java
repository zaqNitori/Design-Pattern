package org.ntutssl.document;

import java.util.Iterator;

public class Paragraph implements Document 
{

	private String _text;

	public Paragraph(String text) 
	{
		_text = text;  
	}

	public String getText() 
	{
		return _text;  
	}

	@Override
	public void accept(Visitor visitor) 
	{
		visitor.visitParagraph(this);
	}

	@Override
	public String toString() 
	{
		String s = "Paragraph\t\t text: " + _text;
		return s;
	}
}