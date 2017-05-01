package com.xxt.ssm.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xxt.ssm.controller.validation.ValidGroup1;
import com.xxt.ssm.entity.ItemsCustom;
import com.xxt.ssm.entity.ItemsQueryVo;
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
	public ModelAndView queryItem(HttpServletRequest request,ItemsQueryVo itemsQueryVo)throws Exception{
		
	
		//调用service查找 数据库，查询商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
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
	
	//商品信息修改提交
	// 在需要校验的pojo前边添加@Validated，在需要校验的pojo后边添加BindingResult
	// bindingResult接收校验出错信息
	// 注意：@Validated和BindingResult bindingResult是配对出现，并且形参顺序是固定的（一前一后）。
	// value={ValidGroup1.class}指定使用ValidGroup1分组的校验
	// @ModelAttribute可以指定pojo回显到页面在request中的key
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(Model model,HttpServletRequest request,Integer id,
			@Validated(value={ValidGroup1.class}) ItemsCustom itemsCustom,
			BindingResult bindingResult,
			MultipartFile items_pic)throws Exception{
		
		
		// 获取校验错误信息
		if (bindingResult.hasErrors()) {
			// 输出错误信息
			List<ObjectError> allErrors = bindingResult.getAllErrors();

			for (ObjectError objectError : allErrors) {
				// 输出错误信息
				System.out.println(objectError.getDefaultMessage());

			}
			// 将错误信息传到页面
			model.addAttribute("allErrors", allErrors);
			
			
			//可以直接使用model将提交pojo回显到页面
			//model.addAttribute("items", itemsCustom);
			
			// 出错重新到商品修改页面
			return "/WEB-INF/items/editItems.jsp";
		}
		//原始名称
		String originalFilename = items_pic.getOriginalFilename();
		//上传图片
		if(items_pic!=null && originalFilename!=null && originalFilename.length()>0){
			
			//存储图片的物理路径
			String pic_path = "F:\\server_pic\\";
			
			
			//新的图片名称
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			//新图片
			File newFile = new File(pic_path+newFileName);
			
			//将内存中的数据写入磁盘
			items_pic.transferTo(newFile);
			
			//将新图片名称写到itemsCustom中
			itemsCustom.setPic(newFileName);
			
		}
		
		itemsService.updateItems(id, itemsCustom);
		//进行重定向,在一个controller中不用加根路径
		return "redirect:queryItems.action";
	}
	
	//批量删除商品items
	@RequestMapping("/deleteItems")
	public String deleteItems(int [] itemsid)throws Exception{
		
		//调用service方法进行批量删除。。。。
		//System.out.println(itemsid[0]);
		return "redirect:queryItems.action";
	}
}
