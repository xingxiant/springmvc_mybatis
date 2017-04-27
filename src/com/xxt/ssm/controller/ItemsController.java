package com.xxt.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xxt.ssm.entity.ItemsCustom;
import com.xxt.ssm.service.ItemsService;

/**
 * 商品的controller
 * @author 13983
 *
 */
@Controller
//添加url的根路径
@RequestMapping("/items")
public class ItemsController {
	@Autowired
	private ItemsService itemsService;
	
	@RequestMapping("/queryItems")
	public ModelAndView queryItems()throws Exception{
		
		//调用service查找 数据库，查询商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
		//向list中填充静态数
		
		//返回ModelAnd0View
		ModelAndView modelAndView =  new ModelAndView();
		//相当 于request的setAttribut，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList);
		
		//指定视图
		modelAndView.setViewName("/WEB-INF/items/itemsList.jsp");
		
		
		return modelAndView;
		
	}
	// 商品信息修改页面显示
	/*@RequestMapping("/editItems")
	//限制http请求方法，可以post和get
//	@RequestMapping(value="/editItems",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView editItems()throws Exception {
	
		//调用service根据商品id查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(1);
		
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		//将商品信息放到model
		modelAndView.addObject("itemsCustom", itemsCustom);
		
		//商品修改页面
		modelAndView.setViewName("/WEB-INF/items/editItems.jsp");
	
		return modelAndView;
	}*/
	@RequestMapping("/editItems")
	//限制http请求方法，可以post和get
//	@RequestMapping(value="/editItems",method={RequestMethod.POST,RequestMethod.GET})
	public String editItems(Model model,int id)throws Exception {
	
		//调用service根据商品id查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		

		// 通过形参中的model将model数据传到页面
		// 相当于modelAndView.addObject方法
		model.addAttribute("itemsCustom", itemsCustom);
		return "/WEB-INF/items/editItems.jsp";
	}
	
	@RequestMapping("/editItemsSubmit")
	//商品信息修改提交
	public String editItemsSubmit(HttpServletRequest request,Integer id,ItemsCustom itemsCustom)throws Exception{
		itemsService.updateItems(id, itemsCustom);
		//进行重定向,在一个controller中不用加根路径
		return "redirect:queryItems.action";
	}
}
