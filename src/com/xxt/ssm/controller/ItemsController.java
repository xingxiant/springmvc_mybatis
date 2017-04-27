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
 * ��Ʒ��controller
 * @author 13983
 *
 */
@Controller
//���url�ĸ�·��
@RequestMapping("/items")
public class ItemsController {
	@Autowired
	private ItemsService itemsService;
	
	@RequestMapping("/queryItems")
	public ModelAndView queryItems()throws Exception{
		
		//����service���� ���ݿ⣬��ѯ��Ʒ�б�
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
		//��list����侲̬��
		
		//����ModelAnd0View
		ModelAndView modelAndView =  new ModelAndView();
		//�൱ ��request��setAttribut����jspҳ����ͨ��itemsListȡ����
		modelAndView.addObject("itemsList", itemsList);
		
		//ָ����ͼ
		modelAndView.setViewName("/WEB-INF/items/itemsList.jsp");
		
		
		return modelAndView;
		
	}
	// ��Ʒ��Ϣ�޸�ҳ����ʾ
	/*@RequestMapping("/editItems")
	//����http���󷽷�������post��get
//	@RequestMapping(value="/editItems",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView editItems()throws Exception {
	
		//����service������Ʒid��ѯ��Ʒ��Ϣ
		ItemsCustom itemsCustom = itemsService.findItemsById(1);
		
		// ����ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		//����Ʒ��Ϣ�ŵ�model
		modelAndView.addObject("itemsCustom", itemsCustom);
		
		//��Ʒ�޸�ҳ��
		modelAndView.setViewName("/WEB-INF/items/editItems.jsp");
	
		return modelAndView;
	}*/
	@RequestMapping("/editItems")
	//����http���󷽷�������post��get
//	@RequestMapping(value="/editItems",method={RequestMethod.POST,RequestMethod.GET})
	public String editItems(Model model,int id)throws Exception {
	
		//����service������Ʒid��ѯ��Ʒ��Ϣ
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		

		// ͨ���β��е�model��model���ݴ���ҳ��
		// �൱��modelAndView.addObject����
		model.addAttribute("itemsCustom", itemsCustom);
		return "/WEB-INF/items/editItems.jsp";
	}
	
	@RequestMapping("/editItemsSubmit")
	//��Ʒ��Ϣ�޸��ύ
	public String editItemsSubmit(HttpServletRequest request,Integer id,ItemsCustom itemsCustom)throws Exception{
		itemsService.updateItems(id, itemsCustom);
		//�����ض���,��һ��controller�в��üӸ�·��
		return "redirect:queryItems.action";
	}
}
