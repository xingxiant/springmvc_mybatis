package com.xxt.ssm.mapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xxt.ssm.entity.Items;

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
		Items item=itemsMapper.selectByPrimaryKey(1);
		System.out.println(item);
	}

}
