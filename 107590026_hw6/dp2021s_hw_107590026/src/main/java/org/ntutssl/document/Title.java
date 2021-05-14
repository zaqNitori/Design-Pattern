package org.ntutssl.document;

public class Title implements Document 
{

	private String _text;
	private int _size;

	public Title(String text, int size) 
	{ 
		_text = text;
		_size = size;
	}

	public String getText() 
	{ 
		return _text;
	}

	public int getSize() 
	{ 
		return _size;
	}

	@Override
	public String toString() 
	{ 
		String s = "Title\t\ttext: " + _text + 
		"\n\t\tsize: " + _size + "\n";
		return s;
	}
}