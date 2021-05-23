package org.ntutssl.document;

public class AddCommandToEditor implements Command 
{

	private Editor _editor;
	private Document doc;

	/**
	 * @param target   the target editor
	 * @param document the document to be added
	 */
	public AddCommandToEditor(Editor target, Document document) 
	{
		_editor = target;
		doc = document;
	}

	public void execute() 
	{
		_editor.add(doc);
	}

	public void undo() 
	{
		_editor.remove(doc);
	}

	public void redo() 
	{
		_editor.add(doc);
	}
}
