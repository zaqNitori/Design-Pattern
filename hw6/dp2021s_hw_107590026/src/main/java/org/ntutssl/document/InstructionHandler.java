package org.ntutssl.document;

import java.util.Scanner;

public class InstructionHandler 
{

	private Editor _editor;
	private Scanner sc;

	public InstructionHandler(Editor editor) 
	{ 
		_editor = editor;
		sc = new Scanner(System.in);
	}

	public void run() 
	{ 
		String cmd;
		while(true)
		{
			printEditorInstructions();
			cmd = sc.nextLine().trim();
			if(cmd.matches("exit"))
				break;
			else
				handleEditorInstructions(cmd);
		}
	}

	private void printEditorInstructions() 
	{ 
		System.out.println("Please enter the following instruction to start editing:");
		System.out.println("1. 'add title': to add a title to the editor");
		System.out.println("2. 'add paragraph': to add a paragraph to the editor");
		System.out.println("3. 'add article': to add an article to the editor");
		System.out.println("4. 'find content': to find the specific string in the editor");
		System.out.println("5. 'import json': to import Document into editor from json file");
		System.out.println("6. 'output html': to transfer the text to html format");
		System.out.println("7. 'exit': to exit the program");
	}
	
	private void handleEditorInstructions(String instruction) 
	{ 
		Document doc;
		String cmd;
		if(instruction.matches("add title"))
		{
			doc = addTitleInstruction();
			if(doc != null)
				_editor.add(doc);
		}
		else if(instruction.matches("add paragraph"))
		{
			doc = addParagraphInstruction();
			if(doc != null)
				_editor.add(doc);
		}
		else if(instruction.matches("add article"))
		{
			doc = addArticleInstruction(0);
			if(doc != null)
			{
				_editor.add(doc);
				while(true)
				{
					printArticleInstructions();
					cmd = sc.nextLine().trim();
					if(cmd.matches("exit"))
						break;
					else
						handleArticleInstructions(cmd, (Article)doc);
				}
			}
		}
		else if(instruction.matches("find content"))
		{
			findContentInstruction();
		}
		else if(instruction.matches("import json"))
		{
			importJsonInstruction();
		}
		else if(instruction.matches("output html"))
		{
			outputHtmlInstruction();
		}
		else System.out.println("Invalid Instruction");
		return;
	}

	private Document addTitleInstruction() 
	{ 
		System.out.println("Please enter the information of title:");
		System.out.print("Text of title: ");
		String text = sc.nextLine().trim();
		System.out.print("Size of title: ");
		int size = Integer.parseInt(sc.nextLine().trim());
		if(size < 1 || size > 6)
		{
			System.out.println("Invalid Input: The size should be in range 1 to 6");
			return null;
		}
		else
		{
			Title title = new Title(text, size);
			System.out.println("Title added to the editor.");
			return title;
		}
	}

	private Document addParagraphInstruction() 
	{ 
		System.out.println("Please enter the information of paragraph:");
		System.out.print("Text of paragraph: ");
		String text = sc.nextLine().trim();
		Paragraph paragraph = new Paragraph(text);
		System.out.println("Paragraph added to the editor.");
		return paragraph;
	}

	private Document addArticleInstruction(int lastLevel) 
	{ 
		System.out.println("Please enter the information of article:");
		System.out.print("Text of article: ");
		String topic = sc.nextLine().trim();
		System.out.print("Level of article: ");
		int level = Integer.parseInt(sc.nextLine().trim());
		if(level <= lastLevel)
		{
			System.out.println("Invalid Input: The level should be positive or higher than the level of the current article");
			return null;
		}
		else
		{
			Article article = new Article(topic, level);
			return article;
		}
	}

	private void printArticleInstructions() 
	{ 
		System.out.println("Please enter the following instruction to edit the article:");
		System.out.println("1. 'add title': to add a title to the article");
		System.out.println("2. 'add paragraph': to add a paragraph to the article");
		System.out.println("3. 'add article': to add an article to the article");
		System.out.println("4. 'exit': to exit the process");
	}

	private void handleArticleInstructions(String instruction, Article article) 
	{ 
		String cmd;
		Document doc;
		if(instruction.matches("add title"))
		{
			doc = addTitleInstruction();
			if(doc != null)
				article.add(doc);
		}
		else if(instruction.matches("add paragraph"))
		{
			doc = addParagraphInstruction();
			if(doc != null)
				article.add(doc);
		}
		else if(instruction.matches("add article"))
		{
			doc = addArticleInstruction(article.getLevel());
			if(doc != null)
			{
				article.add(doc);
				while(true)
				{
					printArticleInstructions();
					cmd = sc.nextLine().trim();
					if(cmd.matches("exit"))
						break;
					else
						handleArticleInstructions(cmd, (Article)doc);
				}
			}
		}
		else System.out.println("Invalid Instruction");
		return;
	}

	private void findContentInstruction() 
	{ 
		String target;
		System.out.print("Enter the word you want to find: ");
		target = sc.nextLine().trim();
		
		_editor.findContent(target);
	}

	private void importJsonInstruction() 
	{ 
		String filePath;
		System.out.print("Enter the file path you want to import: ");
		filePath = sc.nextLine().trim();

		_editor.importDocumentFromJsonFile(filePath);
		System.out.println("Documents were imported.");
	}

	private void outputHtmlInstruction() 
	{ 
		String outputPath;
		System.out.print("Enter the file path you want to output: ");
		outputPath = sc.nextLine().trim();
		
		_editor.exportDocumentAsHtmlFile(outputPath);
		System.out.println("File saved.");
	}
}