package org.ntutssl.document;

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
	public String toString() 
	{
		String s = "Paragraph\ttext: " + _text + "\n";
		return s;
	}
}
