package com.xxt.ssm.service;

import java.util.List;

import com.xxt.ssm.entity.ItemsCustom;
import com.xxt.ssm.entity.ItemsQueryVo;

public interface ItemsService {
	//商品列表查询
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	//根据id查询商品信息
	public ItemsCustom findItemsById(int id)throws Exception;
	
	//根据id修改商品信息
	public void updateItems(int id,ItemsCustom itemsCustom)throws Exception;
}
