package com.xxt.ssm.entity;
/*
 * 商品包装对象
 */
public class ItemsQueryVo {
	private Items items;
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}
	public ItemsCustom getItemCustom() {
		return itemCustom;
	}
	public void setItemCustom(ItemsCustom itemCustom) {
		this.itemCustom = itemCustom;
	}
	private ItemsCustom itemCustom;
}
