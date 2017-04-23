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
 * ��Ʒ��controller
 * @author 13983
 *
 */
@Controller
public class ItemsController {
	@Autowired
	private ItemsService itemsService;
	@RequestMapping("/items")
	public ModelAndView queryItems()throws Exception{
		
		//����service���� ���ݿ⣬��ѯ��Ʒ�б�
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
		//��list����侲̬����

		
		//����ModelAndView
		ModelAndView modelAndView =  new ModelAndView();
		//�൱ ��request��setAttribut����jspҳ����ͨ��itemsListȡ����
		modelAndView.addObject("itemsList", itemsList);
		
		//ָ����ͼ
		modelAndView.setViewName("/itemsList.jsp");
		
		return modelAndView;
		
	}

}
