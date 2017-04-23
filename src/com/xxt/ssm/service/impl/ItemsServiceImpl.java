package com.xxt.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xxt.ssm.entity.ItemsCustom;
import com.xxt.ssm.entity.ItemsQueryVo;
import com.xxt.ssm.mapper.ItemsMapperCustom;
import com.xxt.ssm.service.ItemsService;

public class ItemsServiceImpl implements ItemsService {
	@Autowired
	ItemsMapperCustom itemsMapperCustom;
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		//通过ItemsMapperCustom查询数据
	
			return itemsMapperCustom.findItemsList(itemsQueryVo);
	
	}

}
