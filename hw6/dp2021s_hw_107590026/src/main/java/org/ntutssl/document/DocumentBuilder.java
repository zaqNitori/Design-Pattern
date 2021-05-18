package org.ntutssl.document;

import java.util.Stack;

/**
 * Document builder helps you build a Document object.
 * 
 * Please note that the return value of each build procedure is 
 * DocumentBuilder itself, so you can build a large object using
 * a single statement.
 */
class DocumentBuilder 
{
	private Stack<Document> docStack;
	private Document doc;

	public DocumentBuilder()
	{ 
		docStack = new Stack<>();
	}

	public DocumentBuilder buildTitle(String text, int size) 
	{ 
		doc = new Title(text, size);
		if(docStack.isEmpty() == false)
			docStack.peek().add(doc);

		return this;
	}

	public DocumentBuilder buildParagraph(String text) 
	{ 
		doc = new Paragraph(text);
		if(docStack.isEmpty() == false)
			docStack.peek().add(doc);

		return this;
	}

	public DocumentBuilder startBuildArticle(String topic, int level) 
	{ 
		Article article = new Article(topic,level);
		docStack.push(article);

		return this;
	}

	public DocumentBuilder endBuildArticle() 
	{ 
		doc = docStack.pop();
		if(docStack.isEmpty() == false)
			docStack.peek().add(doc);

		return this;
	}

	public Document getResult() 
	{ 
		return doc;
	}
}