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
	public ModelAndView queryItem(HttpServletRequest request,ItemsQueryVo itemsQueryVo)throws Exception{
		
	
		//����service���� ���ݿ⣬��ѯ��Ʒ�б�
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
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
	
	//��Ʒ��Ϣ�޸��ύ
	// ����ҪУ���pojoǰ�����@Validated������ҪУ���pojo������BindingResult
	// bindingResult����У�������Ϣ
	// ע�⣺@Validated��BindingResult bindingResult����Գ��֣������β�˳���ǹ̶��ģ�һǰһ�󣩡�
	// value={ValidGroup1.class}ָ��ʹ��ValidGroup1�����У��
	// @ModelAttribute����ָ��pojo���Ե�ҳ����request�е�key
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(Model model,HttpServletRequest request,Integer id,
			@Validated(value={ValidGroup1.class}) ItemsCustom itemsCustom,
			BindingResult bindingResult,
			MultipartFile items_pic)throws Exception{
		
		
		// ��ȡУ�������Ϣ
		if (bindingResult.hasErrors()) {
			// ���������Ϣ
			List<ObjectError> allErrors = bindingResult.getAllErrors();

			for (ObjectError objectError : allErrors) {
				// ���������Ϣ
				System.out.println(objectError.getDefaultMessage());

			}
			// ��������Ϣ����ҳ��
			model.addAttribute("allErrors", allErrors);
			
			
			//����ֱ��ʹ��model���ύpojo���Ե�ҳ��
			//model.addAttribute("items", itemsCustom);
			
			// �������µ���Ʒ�޸�ҳ��
			return "/WEB-INF/items/editItems.jsp";
		}
		//ԭʼ����
		String originalFilename = items_pic.getOriginalFilename();
		//�ϴ�ͼƬ
		if(items_pic!=null && originalFilename!=null && originalFilename.length()>0){
			
			//�洢ͼƬ������·��
			String pic_path = "F:\\server_pic\\";
			
			
			//�µ�ͼƬ����
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			//��ͼƬ
			File newFile = new File(pic_path+newFileName);
			
			//���ڴ��е�����д�����
			items_pic.transferTo(newFile);
			
			//����ͼƬ����д��itemsCustom��
			itemsCustom.setPic(newFileName);
			
		}
		
		itemsService.updateItems(id, itemsCustom);
		//�����ض���,��һ��controller�в��üӸ�·��
		return "redirect:queryItems.action";
	}
	
	//����ɾ����Ʒitems
	@RequestMapping("/deleteItems")
	public String deleteItems(int [] itemsid)throws Exception{
		
		//����service������������ɾ����������
		//System.out.println(itemsid[0]);
		return "redirect:queryItems.action";
	}
}
