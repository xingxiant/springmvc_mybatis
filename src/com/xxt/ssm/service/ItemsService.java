package com.xxt.ssm.service;

import java.util.List;

import com.xxt.ssm.entity.ItemsCustom;
import com.xxt.ssm.entity.ItemsQueryVo;

public interface ItemsService {
	//��Ʒ�б��ѯ
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	//����id��ѯ��Ʒ��Ϣ
	public ItemsCustom findItemsById(int id)throws Exception;
	
	//����id�޸���Ʒ��Ϣ
	public void updateItems(int id,ItemsCustom itemsCustom)throws Exception;
}
