package com.xxt.ssm.service;

import java.util.List;

import com.xxt.ssm.entity.ItemsCustom;
import com.xxt.ssm.entity.ItemsQueryVo;

public interface ItemsService {
	//��Ʒ�б��ѯ
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}
