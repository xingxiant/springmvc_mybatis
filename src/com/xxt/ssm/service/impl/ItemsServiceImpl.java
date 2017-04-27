package com.xxt.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xxt.ssm.entity.Items;
import com.xxt.ssm.entity.ItemsCustom;
import com.xxt.ssm.entity.ItemsQueryVo;
import com.xxt.ssm.mapper.ItemsMapper;
import com.xxt.ssm.mapper.ItemsMapperCustom;
import com.xxt.ssm.service.ItemsService;

public class ItemsServiceImpl implements ItemsService {
	@Autowired
	ItemsMapperCustom itemsMapperCustom;
	@Autowired
	ItemsMapper itemsMapper;
	
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		//通过ItemsMapperCustom查询数据
	
			return itemsMapperCustom.findItemsList(itemsQueryVo);
	
	}
	@Override
	public ItemsCustom findItemsById(int id) throws Exception {
		Items items=itemsMapper.selectByPrimaryKey(id);
		//items的业务处理.....
		ItemsCustom itemsCustom=new ItemsCustom();
		//将items的属性值拷贝到itemsCustom
		BeanUtils.copyProperties(items, itemsCustom);
		
		return itemsCustom;
	}
	@Override
	public void updateItems(int id, ItemsCustom itemsCustom) throws Exception {
		//添加业务校验，通常在service接口对关键参数进行校验
		//校验 id是否为空，如果为空抛出异常
		
		//更新商品信息使用updateByPrimaryKeyWithBLOBs根据id更新items表中所有字段，包括 大文本类型字段
		//updateByPrimaryKeyWithBLOBs要求必须转入id
		Items items=(Items)itemsCustom;
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);

	}

}
