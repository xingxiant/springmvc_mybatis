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
		//ͨ��ItemsMapperCustom��ѯ����
	
			return itemsMapperCustom.findItemsList(itemsQueryVo);
	
	}
	@Override
	public ItemsCustom findItemsById(int id) throws Exception {
		Items items=itemsMapper.selectByPrimaryKey(id);
		//items��ҵ����.....
		ItemsCustom itemsCustom=new ItemsCustom();
		//��items������ֵ������itemsCustom
		BeanUtils.copyProperties(items, itemsCustom);
		
		return itemsCustom;
	}
	@Override
	public void updateItems(int id, ItemsCustom itemsCustom) throws Exception {
		//���ҵ��У�飬ͨ����service�ӿڶԹؼ���������У��
		//У�� id�Ƿ�Ϊ�գ����Ϊ���׳��쳣
		
		//������Ʒ��Ϣʹ��updateByPrimaryKeyWithBLOBs����id����items���������ֶΣ����� ���ı������ֶ�
		//updateByPrimaryKeyWithBLOBsҪ�����ת��id
		Items items=(Items)itemsCustom;
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);

	}

}
