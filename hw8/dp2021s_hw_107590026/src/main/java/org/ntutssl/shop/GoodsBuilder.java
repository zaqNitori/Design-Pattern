package org.ntutssl.shop;

import java.util.Stack;

public class GoodsBuilder 
{

  	Stack<Goods> goodStack;
	Goods goods;

	public GoodsBuilder() 
	{ 
		goodStack = new Stack<>();
	}

	public void buildMerchandise(int id, String name, String desc, double price) 
	{ 
		goods = new Merchandise(id, name, desc, price);
		if(goodStack.isEmpty() == false)
			goodStack.peek().add(goods);
	}

	public void startBuildCollection(int id, String name, String desc) 
	{ 
		goods = new Collection(id, name, desc);
		goodStack.push(goods);
	}

	public void endBuildCollection() 
	{ 
		goods = goodStack.pop();
		if(goodStack.isEmpty() == false)
			goodStack.peek().add(goods);
	}

	public Goods getResult() 
	{ 
		return goods;
	}
}
