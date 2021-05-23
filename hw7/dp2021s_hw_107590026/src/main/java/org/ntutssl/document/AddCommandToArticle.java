package org.ntutssl.document;

public class AddCommandToArticle implements Command 
{

	private Article article;
	private Document doc;
	/**
	 * @param target   the target document
	 * @param document the document to be added
	 */
	public AddCommandToArticle(Article target, Document document) 
	{
		article = target;
		doc = document;
	}

	public void execute() 
	{
		article.add(doc);
	}

	public void undo()
	{
		article.remove(doc);
	}

	public void redo() 
	{
		article.add(doc);
	}
}
