package com.xxt.ssm.mapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xxt.ssm.entity.Items;
import com.xxt.ssm.entity.ItemsCustom;

public class ItemsMapperTest {
	private ApplicationContext applicationContext;
	@Before
	public void setUp() throws Exception {
		applicationContext = 
				new ClassPathXmlApplicationContext("classpath:config/applicationContext-dao.xml");
	}

	@Test
	public void test() {
		ItemsMapper itemsMapper = (ItemsMapper) applicationContext.getBean("itemsMapper");
		ItemsCustom itemsCustom=new ItemsCustom();
		Items items=new Items();
		items=itemsMapper.selectByPrimaryKey(1);
		itemsCustom.setCreatetime(items.getCreatetime());
		itemsCustom.setId(1);
		itemsCustom.setName(items.getName());
		itemsCustom.setPrice(items.getPrice());
		System.out.println(items.getPrice());
		
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
		
		items= itemsMapper.selectByPrimaryKey(1);
		System.out.println(items.getPrice());
		
		
	}
	@Test
	public void test1() {
		ItemsMapper itemsMapper = (ItemsMapper) applicationContext.getBean("itemsMapper");
		ItemsCustom itemsCustom=new ItemsCustom();
		Items items=new Items();
		items=itemsMapper.selectByPrimaryKey(1);
		
		print(itemsCustom);
		
		
		
	}

	private void print(Items items) {
		System.out.println(items.getPrice());
		
	}

}
