package com.ynyes.csb.controller.management;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.neo4j.cypher.internal.compiler.v2_1.docbuilders.internalDocBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.csb.entity.TdBill;
import com.ynyes.csb.entity.TdBillType;
import com.ynyes.csb.entity.TdDemand;
import com.ynyes.csb.entity.TdManager;
import com.ynyes.csb.entity.TdManagerRole;
import com.ynyes.csb.entity.TdUser;
import com.ynyes.csb.service.TdArticleService;
import com.ynyes.csb.service.TdBillService;
import com.ynyes.csb.service.TdBillTypeService;
import com.ynyes.csb.service.TdManagerLogService;
import com.ynyes.csb.service.TdManagerRoleService;
import com.ynyes.csb.service.TdManagerService;
import com.ynyes.csb.service.TdUserService;
import com.ynyes.csb.util.SiteMagConstant;
/**
 * 后台首页控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value="/Verwalter/bill")
public class TdManagerBillController {
	@Autowired 
	TdBillService tdBillService;
	
	@Autowired
	TdManagerLogService tdManagerLogService;
	
	@Autowired
	TdBillTypeService tdBillTypeService;
	
	@Autowired
	TdUserService tdUserService;
	
	 @RequestMapping(value="/list/{statusId}")
	    public String billList(Integer page,
	                          Integer size,
	                          Long billTypeId,
	                          String date_1,  
		            		  String date_2,
	                          @PathVariable Long statusId,
	                          String __EVENTTARGET,
	                          String __EVENTARGUMENT,
	                          String __VIEWSTATE,
	                          Long[] listId,
	                          Integer[] listChkId,
	                          ModelMap map,
	                          HttpServletRequest req) throws ParseException{
	        String username = (String) req.getSession().getAttribute("manager");
	        if (null == username) {
	            return "redirect:/Verwalter/login";
	        }
	        if (null != __EVENTTARGET)
	        {
	            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
	            {
	                if (null != __EVENTARGUMENT)
	                {
	                    page = Integer.parseInt(__EVENTARGUMENT);
	                } 
	            }
	            else if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
	            {
	                btnDelete(listId, listChkId);
	                tdManagerLogService.addLog("delete", "删除票据", req);
	            }
	        }
	        
	        if (null == page || page < 0)
	        {
	            page = 0;
	        }
	        
	        if (null == size || size <= 0)
	        {
	            size = SiteMagConstant.pageSize;;
	        }

	        map.addAttribute("page", page);
	        map.addAttribute("size", size);
	        map.addAttribute("billTypeId", billTypeId);
	        map.addAttribute("statusId", statusId);
	        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
	        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
	        map.addAttribute("__VIEWSTATE", __VIEWSTATE);


			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = null;
			Date date2 = null;
			if(null !=date_1 && !date_1.equals(""))
			{
				date1 = sdf.parse(date_1);
			}
			if(null !=date_2 && !date_2.equals(""))
			{
				date2 = sdf.parse(date_2);
			}
			Page<TdBill> billPage = null;
			
			//开始筛选 zhangji
			if (null == billTypeId ) {
				if(null == date1)
				{
					if(null == date2)
					{
						if(null == statusId)
						{
							billPage = tdBillService.findAll(page , size);
						}
						else{
							billPage = tdBillService.findByStatusId(statusId, page, size);
						}
					}
					else{
						if(null == statusId)
						{
							billPage = tdBillService.findByTimeBefore(date2, page, size);
						}
						else{
							billPage = tdBillService.findByTimeBeforeAndStatusId(date2,statusId, page, size);
						}
					}
					
				}
				else{
					if(null == date2)
					{
						if(null == statusId)
						{
							billPage = tdBillService.findByTimeAfter(date1, page, size);
						}
						else{
							billPage = tdBillService.findByTimeAfterAndStatusId(date1,statusId, page, size);
						}
					}
					else{
						if(null == statusId)
						{
							billPage = tdBillService.findByTimeAfterAndTimeBefore(date1, date2, page, size);
						}
						else{
							billPage = tdBillService.findByTimeAfterAndTimeBeforeAndStatusId(date1,date2,statusId, page, size);
						}
					}
				}
			}
			else{
				if(null == date1)
				{
					if(null == date2)
					{
						if(null == statusId)
						{
							billPage = tdBillService.findBySearch(billTypeId,  page, size);
						}
						else{
							billPage = tdBillService.findByStatusIdAndSearch(statusId, billTypeId,  page, size);
						}
					}
					else{
						if(null == statusId)
						{
							billPage = tdBillService.findByTimeBeforeAndSearch(date2, billTypeId,  page, size);
						}
						else{
							billPage = tdBillService.findByTimeBeforeAndStatusIdAndSearch(date2, statusId, billTypeId,  page, size);
						}
					}
				}
				else{
					if(null == date2)
					{
						if(null == statusId)
						{
							billPage = tdBillService.findByTimeAfterAndSearch(date1, billTypeId,  page, size);
						}
						else{
							billPage = tdBillService.findByTimeAfterAndStatusIdAndSearch(date1, statusId, billTypeId,  page, size);
						}
					}
					else{
						if(null == statusId)
						{
							billPage = tdBillService.findByTimeAfterAndTimeBeforeAndSearch(date1, date2, billTypeId,  page, size);
						}
						else{
							billPage = tdBillService.findByTimeAfterAndTimeBeforeAndStatusIdAndSearch(date1, date2, statusId, billTypeId,  page, size);
						}
					}
				}
			}
			
			map.addAttribute("billType_list", tdBillTypeService.findByIsEnableTrueOrderBySortIdAsc());
			map.addAttribute("date_1",date_1);
			map.addAttribute("date_2",date_2);
			map.addAttribute("billTypeId",billTypeId);
			map.addAttribute("statusId",statusId);

			map.addAttribute("bill_page", billPage);
			for (TdBill item : billPage.getContent()) {
				TdUser user = tdUserService.findOne(item.getUserId());
				if (null != user) {
					map.addAttribute("user_" + item.getId(), user);
				}
				TdBillType billType = tdBillTypeService.findOne(item.getBillTypeId());
				if(null != billType)
				{
					map.addAttribute("billType_" + item.getId(), billType.getTitle());
				}
			}
			
			map.addAttribute("path", SiteMagConstant.imagePath);
	        
	        return "/site_mag/bill_list";
	    }
	 
	 //处理票据，人工处理
	 @RequestMapping(value="/edit")
	    public String billEdit(Long id,
	    					Long done,
	                        String __VIEWSTATE,
	                        ModelMap map,
	                        HttpServletRequest req){
	        String username = (String) req.getSession().getAttribute("manager");
	        if (null == username)
	        {
	            return "redirect:/Verwalter/login";
	        }
	        
	        if(null != done)
	        {
	        	map.addAttribute("done", done);
	        }
	        
	        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
	      
	        if (null != id)
	        {
	        	TdBill bill = tdBillService.findOne(id);
	            map.addAttribute("bill",bill);
	            map.addAttribute("id",id);
	            
	            map.addAttribute("user", tdUserService.findOne(bill.getUserId()));
	        }
	        return "/site_mag/bill_edit";
	    }
	 
	 @RequestMapping(value="/save")
	 @ResponseBody
    public Map<String,Object> billSave(Long id,
	    					Long statusId,
	    					String remark,
	                        ModelMap map,
	                        HttpServletRequest req){
		 Map<String, Object> res = new HashMap<String, Object>();
			res.put("code", 1);
			
	        String username = (String) req.getSession().getAttribute("manager");
	        if (null == username) {
	        	res.put("msg", "请先登录！");
	            return res;
	        }
	        
	        TdBill bill = tdBillService.findOne(id);
	        if(null != statusId)
	        {
//	        	bill.setStatusId(statusId);
	        	bill.setRemark(remark);
	        	bill.setFinishTime(new Date());
	        	tdBillService.save(bill);
	        	res.put("statusId", statusId);
	        	if(statusId == 2)
	        	{
	        		res.put("msg", "操作成功！票据已改为待处理状态");
	        	}
	        	else if(statusId == 3)
	        	{
	        		res.put("msg", "操作成功！请填写票据整理表单");
	        	}
	        	else if(statusId == 4)
	        	{
	        		res.put("msg", "操作成功！请进行相关用户的财务处理");
	        	}
	        	else if(statusId == 5)
	        	{
	        		res.put("msg", "操作成功！请进行相关用户的税费扣缴");
	        	}
	        	else if(statusId == 6)
	        	{
	        		res.put("msg", "操作成功！请上传用户的财务状况表");
	        	}
	        }

			res.put("code", 0);

			return res;
	    }
	 
	//处理票据，人工处理
		 @RequestMapping(value="/deal/{id}")
		    public String billDeal(@PathVariable Long id,
		    					Long statusId,
		                        String __VIEWSTATE,
		                        ModelMap map,
		                        HttpServletRequest req){
		        String username = (String) req.getSession().getAttribute("manager");
		        if (null == username)
		        {
		            return "redirect:/Verwalter/login";
		        }
		        
		        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
		      
		        if (null != id)
		        {
		        	TdBill bill = tdBillService.findOne(id);
		            map.addAttribute("bill",bill);
		            
		            map.addAttribute("user", tdUserService.findOne(bill.getUserId()));
		        }
		        if(null != statusId)
		        {
		        	map.addAttribute("statusId", statusId);
		        	if(statusId == 3)
		        	{
		        		return "/site_mag/bill_deal";
		        	}
		        	else 
		        	{
		        		return "redirect:/Verwalter/bill/list/"+statusId;
		        	}
		        }
		        return "/site_mag/center";
		    }
	 
	 
	 
	//票据整理 用户列表 2016年1月10日 02:04:27
	    @RequestMapping(value="/user/list")
	    public String billUserList(Integer page,
	                          Integer size,
	                          String keywords,
	                          String __EVENTTARGET,
	                          String __EVENTARGUMENT,
	                          String __VIEWSTATE,
	                          Long[] listId,
	                          Integer[] listChkId,
	                          ModelMap map,
	                          HttpServletRequest req){
	        String username = (String) req.getSession().getAttribute("manager");
	        if (null == username) {
	            return "redirect:/Verwalter/login";
	        }
	        if (null != __EVENTTARGET)
	        {
	            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
	            {
	                if (null != __EVENTARGUMENT)
	                {
	                    page = Integer.parseInt(__EVENTARGUMENT);
	                } 
	            }
	        }
	        
	        if (null == page || page < 0)
	        {
	            page = 0;
	        }
	        
	        if (null == size || size <= 0)
	        {
	            size = SiteMagConstant.pageSize;;
	        }
	        
	        if (null != keywords)
	        {
	            keywords = keywords.trim();
	        }
	        
	        map.addAttribute("page", page);
	        map.addAttribute("size", size);
	        map.addAttribute("keywords", keywords);
	        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
	        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
	        map.addAttribute("__VIEWSTATE", __VIEWSTATE);

	        Page<TdUser> userPage = null;

	        if (null == keywords || "".equalsIgnoreCase(keywords))
	        {
	            userPage = tdUserService.findAllOrderBySortIdAsc(page, size);
	        }
	        else
	        {
	            userPage = tdUserService.searchAndOrderByIdDesc(keywords, page, size);
	        }
	        
	        if(null != userPage)
	        {
	        	for(TdUser item : userPage)
	        	{
	        		List<TdBill> billList = tdBillService.findByUserId(item.getId());
	        		if(null != billList)
	        		{
	        			map.addAttribute("billAmount_"+item.getId(), billList.size());
	        		}
	        	}
	        }

	        
	        map.addAttribute("user_page", userPage);
	        
	        return "/site_mag/user_list_bill";
	    }
	 
	 
	 private void btnDelete( Long[] ids, Integer[] chkIds)
	    {
	        if (null == ids || null == chkIds
	                || ids.length < 1 || chkIds.length < 1 )
	        {
	            return;
	        }
	        
	        for (int chkId : chkIds)
	        {
	            if (chkId >=0 && ids.length > chkId)
	            {
	                Long id = ids[chkId];
	                	
	                tdBillService.delete(id);
	            }
	        }
	    }
	
}
