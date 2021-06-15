package org.ntutssl.shop;

public class GoodsParser implements EventListener {

  public GoodsParser() { }

  public void onEvent(Event event) { }

  private void importShoppingCartList(Event<String> event) { }

  private void importReplenishmentList(Event<String> event) { }

  private Goods parse(JsonObject jsonObj) { }
}
