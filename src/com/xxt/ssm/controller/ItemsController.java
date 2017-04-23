package com.xxt.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xxt.ssm.entity.ItemsCustom;
import com.xxt.ssm.service.ItemsService;

/**
 * 商品的controller
 * @author 13983
 *
 */
@Controller
public class ItemsController {
	@Autowired
	private ItemsService itemsService;
	@RequestMapping("/items")
	public ModelAndView queryItems()throws Exception{
		
		//调用service查找 数据库，查询商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
		//向list中填充静态数据

		
		//返回ModelAndView
		ModelAndView modelAndView =  new ModelAndView();
		//相当 于request的setAttribut，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList);
		
		//指定视图
		modelAndView.setViewName("/itemsList.jsp");
		
		return modelAndView;
		
	}

}
