package org.ntutssl.document;

import java.util.Stack;

public class CommandManager 
{

	private Stack<Command> cmdRedoStack;
	private Stack<Command> cmdUndoStack;

	public CommandManager() 
	{ 
		cmdRedoStack = new Stack<>();
		cmdUndoStack = new Stack<>();
	}

	public void executeCmd(Command cmd) 
	{
		cmd.execute();
		cmdUndoStack.push(cmd);
	}

	public void undoCmd() 
	{
		if(cmdUndoStack.isEmpty())
		{
			System.out.print("No command can be undid.\n");
			return;
		}
		
		Command cmd = cmdUndoStack.pop();
		cmdRedoStack.push(cmd);
		cmd.undo();
	}

	public void redoCmd() 
	{
		if(cmdRedoStack.isEmpty())
		{
			System.out.print("No command can be redid.\n");
			return;
		}
	
		Command cmd = cmdRedoStack.pop();
		cmdUndoStack.push(cmd);
		cmd.redo();
	}
}
