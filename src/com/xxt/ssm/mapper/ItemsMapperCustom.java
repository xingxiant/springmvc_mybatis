package com.xxt.ssm.mapper;


import java.util.List;

import com.xxt.ssm.entity.ItemsCustom;
import com.xxt.ssm.entity.ItemsQueryVo;

public interface ItemsMapperCustom {
	//��ѯ��Ʒ�б�
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsCustomVo)throws Exception;
    	
}