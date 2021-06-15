package org.ntutssl.shop;

public class FixedDiscountDecorator extends Decorator {

  /**
   * counstructor
   * @param goods goods to be decorated
   * @param discount fixed discount, which should be lower than the price of goods
   */
  public FixedDiscountDecorator(Goods goods, double discount) { }

  public int id() { }

  public double price() { }

  public String name() { }

  public String description() { }
}
