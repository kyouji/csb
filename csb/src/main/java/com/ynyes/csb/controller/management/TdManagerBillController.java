package com.ynyes.csb.controller.management;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
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

import com.ynyes.csb.entity.TdArticle;
import com.ynyes.csb.entity.TdBill;
import com.ynyes.csb.entity.TdBillType;
import com.ynyes.csb.entity.TdDemand;
import com.ynyes.csb.entity.TdEnterType;
import com.ynyes.csb.entity.TdFinance;
import com.ynyes.csb.entity.TdGather;
import com.ynyes.csb.entity.TdManager;
import com.ynyes.csb.entity.TdManagerRole;
import com.ynyes.csb.entity.TdPay;
import com.ynyes.csb.entity.TdUser;
import com.ynyes.csb.service.TdArticleService;
import com.ynyes.csb.service.TdBillService;
import com.ynyes.csb.service.TdBillTypeService;
import com.ynyes.csb.service.TdEnterTypeService;
import com.ynyes.csb.service.TdFinanceService;
import com.ynyes.csb.service.TdGatherService;
import com.ynyes.csb.service.TdManagerLogService;
import com.ynyes.csb.service.TdManagerRoleService;
import com.ynyes.csb.service.TdManagerService;
import com.ynyes.csb.service.TdPayService;
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
	
	@Autowired
	TdGatherService tdGatherService;
	
	@Autowired
	TdFinanceService tdFinanceService;
	
	@Autowired
	TdPayService tdPayService;
	
	@Autowired
	TdEnterTypeService tdEnterTypeService;
	
 	private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;
	
	@RequestMapping(value="/list/{statusId}")
    public String billList(Integer page,
                          Integer size,
                          @PathVariable Long statusId,
                          ModelMap map,
                          HttpServletRequest req) throws ParseException{
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        if (null == page || page < 0)
        {
            page = 0;
        }
        
        if (null == size || size <= 0)
        {
            size = SiteMagConstant.pageSize;;
        }


		Page<TdBill> billPage = null;
		if(null == statusId)
		{
			billPage = tdBillService.findAll(page , size);
		}
		else{
			billPage = tdBillService.findByStatusId(statusId, page, size);
		}

		
		map.addAttribute("billType_list", tdBillTypeService.findByIsEnableTrueOrderBySortIdAsc());
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
		
        
        return "/site_mag/bill_list";
    }
	
	 @RequestMapping(value="/list/{statusId}",method = RequestMethod.POST)
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
	        	res.put("statusId", statusId);
	        	if(statusId == 2 )
	        	{
		        	bill.setStatusId(statusId);
		        	tdBillService.save(bill);
	        	}
	        	else if(statusId == 3 || statusId == 4 || statusId == 5 || statusId == 6)
	        	{
		        	tdBillService.save(bill);
	        	}
	        }

			res.put("code", 0);

			return res;
	    }
	 
    //票据整理 整理列表 tdGather列表
  	 @RequestMapping(value="/gather/list")
  	    public String gatherList(Integer page,
  	                          Integer size,
  	                          String time,
  	                          ModelMap map,
  	                          HttpServletRequest req) throws ParseException{
  	        String username = (String) req.getSession().getAttribute("manager");
  	        if (null == username) {
  	            return "redirect:/Verwalter/login";
  	        }
  	        if (null == page || page < 0)
  	        {
  	            page = 0;
  	        }
  	        
  	        if (null == size || size <= 0)
  	        {
  	            size = SiteMagConstant.pageSize;
  	        }
  	        
  	        map.addAttribute("page", page);
  	        map.addAttribute("size", size);

  	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
  	        Date date = new Date();
  	        if(null == time || time.equals(""))
  	        {
  	        	 time = sdf.format(date);
  	        	 date = sdf.parse(time);
  	        }
  	        else{
  	        	date = sdf.parse(time);
  	        }
  	        Page<TdGather> gatherPage = tdGatherService.findByTime(date, page, size);
  	        
  	        if(null != gatherPage)
  	        {
  	        	for(TdGather item : gatherPage)
  	        	{
  	        		TdUser tdUser = tdUserService.findOne(item.getUserId());
  	        		if(null != tdUser)
  	        		{
  	        			map.addAttribute("user_"+item.getId(), tdUser);
  	        			if (null != tdUser.getEnterTypeId())
  	        			{
  	        				TdEnterType enterType = tdEnterTypeService.findOne(tdUser.getEnterTypeId());
  	  	        			map.addAttribute("enterType_"+item.getId(), enterType.getTitle());
  	        			}
  	        		}
  	        	}
  	        }

  	        map.addAttribute("time", time);
  	        map.addAttribute("gather_page", gatherPage);
  	        
  	        return "/site_mag/bill_gather_list";
  	    }
  	 
  	//票据整理 整理列表 tdGather列表 POST
  	 @RequestMapping(value="/gather/list",method=RequestMethod.POST)
  	    public String gatherListPost(Integer page,
  	                          Integer size,
  	                          String time,
  	                          ModelMap map,
  	                          HttpServletRequest req) throws ParseException{
  	        String username = (String) req.getSession().getAttribute("manager");
  	        if (null == username) {
  	            return "redirect:/Verwalter/login";
  	        }
  	        if (null == page || page < 0)
  	        {
  	            page = 0;
  	        }
  	        
  	        if (null == size || size <= 0)
  	        {
  	            size = SiteMagConstant.pageSize;
  	        }
  	        
  	        map.addAttribute("page", page);
  	        map.addAttribute("size", size);
  	        
  	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
  	        Date date = new Date();
  	        if(null == time || time.equals(""))
  	        {
  	        	 date = sdf.parse(sdf.format(date));
  	        }
  	        else{
  	        	date = sdf.parse(time);
  	        }
  	        
  	        Page<TdGather> gatherPage = tdGatherService.findByTime(date, page, size);
  	        
  	        if(null != gatherPage)
  	        {
  	        	for(TdGather item : gatherPage)
  	        	{
  	        		TdUser tdUser = tdUserService.findOne(item.getUserId());
  	        		if(null != tdUser)
  	        		{
  	        			map.addAttribute("user_"+item.getId(), tdUser);
  	        		}
  	        	}
  	        }

  	        map.addAttribute("time", time);
  	        map.addAttribute("gather_page", gatherPage);
  	        
  	        return "/site_mag/bill_gather_list";
  	    }
  	 
  	//处理票据单个用户汇总
	 @RequestMapping(value="/gather/deal/{id}")
	    public String billGatherDeal(@PathVariable Long id,
	    					String time,
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
	        	TdGather tdGather = tdGatherService.findOne(id);
	            map.addAttribute("tdGather",tdGather);
	            map.addAttribute("user", tdUserService.findOne(tdGather.getUserId()));
	            
	            Date date  = null;
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	            if(null == time)
	            {
	            	time = sdf.format(new Date()); 
	            }
	      
    			try {
					date = sdf.parse(time);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
	          
	            map.addAttribute("time", time);
	    }
	        return "/site_mag/bill_deal";
	 }
    
	//处理单个票据）
		 @RequestMapping(value="/deal/{id}")
		    public String billDeal(@PathVariable Long id,
		    					Long statusId,
		    					String time,
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
		            
		            Date date  = null;
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		            if(null == time)
		            {
		            	time = sdf.format(new Date()); 
		            }
		      
	    			try {
    					date = sdf.parse(time);
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
		          
		            map.addAttribute("date", date);
		            
		            //试试能不能直接用new Date()找到当月的票据汇总
		            TdGather tdGather = tdGatherService.findByUserIdAndTime(bill.getUserId(),date);
		            if (null != tdGather)
		            {
		            	map.addAttribute("tdGather", tdGather);
		            }
		        
			        if(null != statusId)
			        {
			        	map.addAttribute("statusId", statusId);
			        	if(statusId == 3)
			        	{
			        		return "/site_mag/bill_deal";
			        	}
			        	else if(statusId == 4)
			        	{
			        		return "redirect:/Verwalter/bill/finance/edit?billId="+bill.getId();
			        	}
			        	else if(statusId == 5)
			        	{
			        		return "redirect:/Verwalter/user/pay/id="+bill.getUserId();
			        	}
			        	else if(statusId == 6)
			        	{
			        		return "redirect:/Verwalter/bill/upload?billId="+bill.getId();
			        	}
			        	else 
			        	{
			        		return "redirect:/Verwalter/bill/list/"+statusId;
			        	}
			        }
		        }
		        return "/site_mag/center";
		    }
	 
	 //票据整理 提交 类TdGather
	 @RequestMapping(value="/gather/save", method = RequestMethod.POST)
	 @ResponseBody
	    public Map<String,Object> gatherSave(TdGather tdGather,
	    		Long billId,
	            String __EVENTTARGET,
	            String __EVENTARGUMENT,
	            String __VIEWSTATE,
	            ModelMap map,
	            HttpServletRequest req){
		 Map<String, Object> res = new HashMap<String, Object>();
			res.put("code", 1);
			
	        String username = (String) req.getSession().getAttribute("manager");
	        if (null == username) {
	        	res.put("msg", "请先登录！");
	        	res.put("check", 0);
	            return res;
	        }
	        
	        String logType = null;
	        TdGather check = tdGatherService.findByUserIdAndTime(tdGather.getUserId(), tdGather.getTime()); //查重，每月应只有一个gather表
	        if (null == tdGather.getId())
	        {
	            logType = "add";
		        if (null != check)
		        {
		        	res.put("msg", "该月份的票据整理已存在！");
		        	return res;
		        }
	        }
	        else
	        {
	            logType = "edit";
//			        if (null != check && check.getId() != tdGather.getId())
//			        {
//			        	res.put("msg", "该月份的票据整理已存在！");
//			        	return res;
//			        }
	        }
	        
	        tdGather.setStatusId(0L);
	        tdGatherService.save(tdGather);
	        
	        if(null != billId)
	        {
	        	TdBill bill =	tdBillService.findOne(billId);
	        	bill.setStatusId(3L);
	        	tdBillService.save(bill);
	        }
	        
	        tdManagerLogService.addLog(logType, "整理票据", req);
	        

	        res.put("code", 0);
	        return res;
	    }
	 /*-----------------------------------------------------
	  * =========导入表格模块////////==========
	  =================================*/
	 //票据处理：导入excel表格
		 @RequestMapping(value="/gather/import")
		    public String billGatherImport(
		    					String time,
		                        String __VIEWSTATE,
		                        ModelMap map,
		                        HttpServletRequest req){
		        String username = (String) req.getSession().getAttribute("manager");
		        if (null == username)
		        {
		            return "redirect:/Verwalter/login";
		        }
		        
		        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
	            map.addAttribute("time", time);

		        return "/site_mag/bill_gather_import";
		 } 
		 
		 @RequestMapping(value="/gather/import/submit", method=RequestMethod.POST)
		 @ResponseBody
		    public Map<String,Object> billGatherImportSubmit(
		    					String time,
		    					String fileUrl,
//		                        ModelMap map,
		                        HttpServletResponse resp,
                                HttpServletRequest req) throws IOException{
			 Map<String, Object> res = new HashMap<String, Object>();
				res.put("code", 1);
		        String username = (String) req.getSession().getAttribute("manager");
		        if (null == username)
		        {
		        	res.put("login", 1);
		            return res;
		        }
		        
		        //导入过程
		        String filepath = SiteMagConstant.imagePath;	
		        File file = new File(filepath +"/" +fileUrl);

		        try {
		            // 对读取Excel表格标题测试
		            InputStream is = new FileInputStream(file);
		            String[] title = readExcelTitle(is);
		            System.out.println("获得Excel表格的标题:");
		            for (String s : title) {
		                System.out.print(s + " ");
		            }

		            // 对读取Excel表格内容测试
		            InputStream is2 = new FileInputStream(file);
		            Map<Integer, String> map = readExcelContent(is2);
		            System.out.println("获得Excel表格的内容:");
		            for (int i = 1; i <= map.size(); i++) {
		                System.out.println(map.get(i));
		            }

		            
		        } catch (FileNotFoundException e) {
		            System.out.println("未找到指定路径的文件!");
		            e.printStackTrace();
		        }
		    
		        
		        
		        res.put("code", 0);
		        return res;
		 } 


		 /**
		     * 读取Excel表格表头的内容
		     * @param InputStream
		     * @return String 表头内容的数组
		     */
		    public String[] readExcelTitle(InputStream is) {
		        try {
		            fs = new POIFSFileSystem(is);
		            wb = new HSSFWorkbook(fs);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        sheet = wb.getSheetAt(0);
		        row = sheet.getRow(0);
		        // 标题总列数
		        int colNum = row.getPhysicalNumberOfCells();
		        System.out.println("colNum:" + colNum);
		        String[] title = new String[colNum];
		        for (int i = 0; i < colNum; i++) {
		            //title[i] = getStringCellValue(row.getCell((short) i));
		            title[i] = getCellFormatValue(row.getCell((short) i));
		        }
		        return title;
		    }

		    /**
		     * 读取Excel数据内容
		     * @param InputStream
		     * @return Map 包含单元格数据内容的Map对象
		     */
		    public Map<Integer, String> readExcelContent(InputStream is) {
		        Map<Integer, String> content = new HashMap<Integer, String>();
		        String str = "";
		        try {
		            fs = new POIFSFileSystem(is);
		            wb = new HSSFWorkbook(fs);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        sheet = wb.getSheetAt(0);
		        // 得到总行数
		        int rowNum = sheet.getLastRowNum();
		        row = sheet.getRow(0);
		        int colNum = row.getPhysicalNumberOfCells();
		        // 正文内容应该从第二行开始,第一行为表头的标题
		        for (int i = 1; i <= rowNum; i++) {
		            row = sheet.getRow(i);
		            int j = 0;
		            while (j < colNum) {
		                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
		                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
		                // str += getStringCellValue(row.getCell((short) j)).trim() +
		                // "-";
		                str += getCellFormatValue(row.getCell((short) j)).trim() + "    ";
		                j++;
		            }
		            content.put(i, str);
		            str = "";
		            
		        }
		        return content;
		    }

		    /**
		     * 获取单元格数据内容为字符串类型的数据
		     * 
		     * @param cell Excel单元格
		     * @return String 单元格数据内容
		     */
		    private String getStringCellValue(HSSFCell cell) {
		        String strCell = "";
		        switch (cell.getCellType()) {
		        case HSSFCell.CELL_TYPE_STRING:
		            strCell = cell.getStringCellValue();
		            break;
		        case HSSFCell.CELL_TYPE_NUMERIC:
		            strCell = String.valueOf(cell.getNumericCellValue());
		            break;
		        case HSSFCell.CELL_TYPE_BOOLEAN:
		            strCell = String.valueOf(cell.getBooleanCellValue());
		            break;
		        case HSSFCell.CELL_TYPE_BLANK:
		            strCell = "";
		            break;
		        default:
		            strCell = "";
		            break;
		        }
		        if (strCell.equals("") || strCell == null) {
		            return "";
		        }
		        if (cell == null) {
		            return "";
		        }
		        return strCell;
		    }

		    /**
		     * 获取单元格数据内容为日期类型的数据
		     * 
		     * @param cell
		     *            Excel单元格
		     * @return String 单元格数据内容
		     */
		    private String getDateCellValue(HSSFCell cell) {
		        String result = "";
		        try {
		            int cellType = cell.getCellType();
		            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
		                Date date = cell.getDateCellValue();
		                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
		                        + "-" + date.getDate();
		            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
		                String date = getStringCellValue(cell);
		                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
		            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
		                result = "";
		            }
		        } catch (Exception e) {
		            System.out.println("日期格式不正确!");
		            e.printStackTrace();
		        }
		        return result;
		    }

		    /**
		     * 根据HSSFCell类型设置数据
		     * @param cell
		     * @return
		     */
		    private String getCellFormatValue(HSSFCell cell) {
		        String cellvalue = "";
		        if (cell != null) {
		            // 判断当前Cell的Type
		            switch (cell.getCellType()) {
		            // 如果当前Cell的Type为NUMERIC
		            case HSSFCell.CELL_TYPE_NUMERIC:
		            case HSSFCell.CELL_TYPE_FORMULA: {
		                // 判断当前的cell是否为Date
		                if (HSSFDateUtil.isCellDateFormatted(cell)) {
		                    // 如果是Date类型则，转化为Data格式
		                    
		                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
		                    //cellvalue = cell.getDateCellValue().toLocaleString();
		                    
		                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
		                    Date date = cell.getDateCellValue();
		                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		                    cellvalue = sdf.format(date);
		                    
		                }
		                // 如果是纯数字
		                else {
		                    // 取得当前Cell的数值
		                    cellvalue = String.valueOf(cell.getNumericCellValue());
		                }
		                break;
		            }
		            // 如果当前Cell的Type为STRIN
		            case HSSFCell.CELL_TYPE_STRING:
		                // 取得当前的Cell字符串
		                cellvalue = cell.getRichStringCellValue().getString();
		                break;
		            // 默认的Cell值
		            default:
		                cellvalue = " ";
		            }
		        } else {
		            cellvalue = "";
		        }
		        return cellvalue;

		    }

		 
		    
		 
		 /*-----------------------------------------------------
		  * =========/////导入表格模块=============
		  =================================*/
	 
	 
	//票据整理 用户列表 2016年1月10日 02:04:27
	 @RequestMapping(value="/user/list")
	    public String UserList(Integer page,
	                          Integer size,
	                          ModelMap map,
	                          HttpServletRequest req){
	        String username = (String) req.getSession().getAttribute("manager");
	        if (null == username) {
	            return "redirect:/Verwalter/login";
	        }
	        if (null == page || page < 0)
	        {
	            page = 0;
	        }
	        
	        if (null == size || size <= 0)
	        {
	            size = SiteMagConstant.pageSize;
	        }
	        
	        map.addAttribute("page", page);
	        map.addAttribute("size", size);

	        Page<TdUser> userPage = null;
	            userPage = tdUserService.findAllOrderBySortIdAsc(page, size);
	        
	        if(null != userPage)
	        {
	        	for(TdUser item : userPage)
	        	{
	        		List<TdBill> billList = tdBillService.findByUserId(item.getId());
	        		if(null != billList)
	        		{
	        			map.addAttribute("billAmount_"+item.getId(), billList.size());
	        		}
	        		List<TdBill> todo = tdBillService.findByStatusIdAndUserId(2L,item.getId());
	        		if(null != todo)
	        		{
	        			map.addAttribute("todo_"+item.getId(), todo.size());
	        		}
	        		List<TdPay> pay = tdPayService.findByUserIdAndIsPaidFalse(item.getId());
	        		if(null != pay)
	        		{
	        			map.addAttribute("pay_"+item.getId(), pay.size());
	        		}
	        	}
	        }

	        
	        map.addAttribute("user_page", userPage);
	        
	        return "/site_mag/user_list_bill";
	    }
	 
	    @RequestMapping(value="/user/list",method = RequestMethod.POST)
	    public String billUserList(Integer page,
	                          Integer size,
	                          String keywords,
	                          String __EVENTTARGET,
	                          String __EVENTARGUMENT,
	                          String __VIEWSTATE,
	                          Long[] listId,
	                          Integer[] listChkId,
	                          ModelMap map,
	                          HttpServletResponse resp,
	                          HttpServletRequest req) throws IOException{
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
	            else if (__EVENTTARGET.equalsIgnoreCase("btnDownload"))
	            {
	            	btnZipUser(listId, listChkId , resp, req);
	            }
	        }
	        
	        if (null == page || page < 0)
	        {
	            page = 0;
	        }
	        
	        if (null == size || size <= 0)
	        {
	            size = SiteMagConstant.pageSize;
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
	        		List<TdBill> todo = tdBillService.findByStatusIdAndUserId(2L,item.getId());
	        		if(null != todo)
	        		{
	        			map.addAttribute("todo_"+item.getId(), todo.size());
	        		}
	        		List<TdPay> pay = tdPayService.findByUserIdAndIsPaidFalse(item.getId());
	        		if(null != pay)
	        		{
	        			map.addAttribute("pay_"+item.getId(), pay.size());
	        		}
	        	}
	        }

	        
	        map.addAttribute("user_page", userPage);
	        
	        return "/site_mag/user_list_bill";
	    }
	    
	    //票据整理 单个角色票据页面
	    @RequestMapping(value="/user/billList/{userId}")
	    public String BillListDetail(Integer page,
	                          Integer size,
	                          @PathVariable Long userId,
	                          ModelMap map,
	                          HttpServletResponse resp,
	                          HttpServletRequest req) throws ParseException, IOException{
	        String username = (String) req.getSession().getAttribute("manager");
	        if (null == username) {
	            return "redirect:/Verwalter/login";
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

			Page<TdBill> billPage = null;
			
			//开始筛选 zhangji
			if (null == userId)
			{
				billPage = tdBillService.findAll( page , size);
			}
			else{				
				billPage = tdBillService.findByUserId( userId, page , size);
			}
			
			if (null != userId)
			{
				TdUser user = tdUserService.findOne(userId);
				if(null != user)
				{
					map.addAttribute("user", user);
					if (null != user.getEnterTypeId())
					{
						TdEnterType enterType = tdEnterTypeService.findOne(user.getEnterTypeId());
						if (null != enterType)
						{
							map.addAttribute("enterType", enterType);
						}
					}
				}
			}
			map.addAttribute("billType_list", tdBillTypeService.findByIsEnableTrueOrderBySortIdAsc());
			map.addAttribute("userId",userId);
			map.addAttribute("user", tdUserService.findOne(userId));

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
	        
	        return "/site_mag/bill_user_billList";
	    }
    
	    
		 @RequestMapping(value="/user/billList/{userId}", method = RequestMethod.POST)
		    public String billListDetail(Integer page,
		                          Integer size,
		                          @PathVariable Long userId,
		                          String date_1,  
			            		  String date_2,
		                          Long statusId,
		                          String __EVENTTARGET,
		                          String __EVENTARGUMENT,
		                          String __VIEWSTATE,
		                          Long[] listId,
		                          Integer[] listChkId,
		                          ModelMap map,
		                          HttpServletResponse resp,
		                          HttpServletRequest req) throws ParseException, IOException{
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
		            else if (__EVENTTARGET.equalsIgnoreCase("btnDownload"))
		            {
		            	btnZip(listId, listChkId , userId, resp, req);
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
				if (null == userId ) {
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
								billPage = tdBillService.findByUserId(userId,  page, size);
							}
							else{
								billPage = tdBillService.findByStatusIdAndUserId(statusId, userId,  page, size);
							}
						}
						else{
							if(null == statusId)
							{
								billPage = tdBillService.findByTimeBeforeAndUserId(date2, userId,  page, size);
							}
							else{
								billPage = tdBillService.findByTimeBeforeAndStatusIdAndUserId(date2, statusId, userId,  page, size);
							}
						}
					}
					else{
						if(null == date2)
						{
							if(null == statusId)
							{
								billPage = tdBillService.findByTimeAfterAndUserId(date1, userId,  page, size);
							}
							else{
								billPage = tdBillService.findByTimeAfterAndStatusIdAndUserId(date1, statusId, userId,  page, size);
							}
						}
						else{
							if(null == statusId)
							{
								billPage = tdBillService.findByTimeAfterAndTimeBeforeAndUserId(date1, date2, userId,  page, size);
							}
							else{
								billPage = tdBillService.findByTimeAfterAndTimeBeforeAndStatusIdAndUserId(date1, date2, statusId, userId,  page, size);
							}
						}
					}
				}
				
				if (null != userId)
				{
					TdUser user = tdUserService.findOne(userId);
					map.addAttribute("user", user);
					map.addAttribute("enterType", tdEnterTypeService.findOne(user.getEnterTypeId()).getTitle());
				}
				map.addAttribute("billType_list", tdBillTypeService.findByIsEnableTrueOrderBySortIdAsc());
				map.addAttribute("date_1",date_1);
				map.addAttribute("date_2",date_2);
				map.addAttribute("userId",userId);
				map.addAttribute("user", tdUserService.findOne(userId));
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
		        
		        return "/site_mag/bill_user_billList";
		    }
		 
		 
		  //票据整理 单个角色票据页面
		    @RequestMapping(value="/finance/list/{time}")
		    public String financeList(Integer page,
		                          Integer size,
		                          @PathVariable String time,
		                          ModelMap map,
		                          HttpServletResponse resp,
		                          HttpServletRequest req) throws ParseException, IOException{
		        String username = (String) req.getSession().getAttribute("manager");
		        if (null == username) {
		            return "redirect:/Verwalter/login";
		        }
		        
		        if (null == page || page < 0)
		        {
		            page = 0;
		        }
		        
		        if (null == size || size <= 0)
		        {
		            size = SiteMagConstant.pageSize;;
		        }
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		        Date date = new Date();
		        if (null != time)
		        {
		        	date =sdf.parse(time);
		        }

		        map.addAttribute("page", page);
		        map.addAttribute("size", size);

				Page<TdFinance> tdFinance = tdFinanceService.findByTime(date, page , size);
				for (TdFinance item : tdFinance.getContent())
				{
					TdUser tdUser = tdUserService.findOne(item.getUserId());
					if (null != tdUser)
					{
						map.addAttribute("user_"+item.getId(), tdUser);
					}
				}
		        
		        return "/site_mag/bill_finance_list";
		    }
		 
		 
		 
	  //财务管理，人工处理
		 @RequestMapping(value="/finance/edit")
		    public String financeEdit( Long billId,
		    					Long userId,
		    					Long statusId,
		    					String time,
		                        String __VIEWSTATE,
		                        ModelMap map,
		                        HttpServletRequest req){
		        String username = (String) req.getSession().getAttribute("manager");
		        if (null == username)
		        {
		            return "redirect:/Verwalter/login";
		        }
		        
		        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
		        
		        Date date  = null;
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	            if(null == time)
	            {
	            	time = sdf.format(new Date()); 
	            }
	      
    			try {
					date = sdf.parse(time);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
	          
	            map.addAttribute("date", date);
		      
		        if (null != billId)
		        {
		        	TdBill bill = tdBillService.findOne(billId);
		            map.addAttribute("bill",bill);
		            map.addAttribute("user", tdUserService.findOne(bill.getUserId()));
		            
		            TdFinance tdFinance = tdFinanceService.findByUserIdAndTime(bill.getUserId(),date);
		            if (null != tdFinance)
		            {
		            	map.addAttribute("tdFinance", tdFinance);
		            }
		            
		            return "/site_mag/user_finance_edit";
		        }
		        else if (null != userId)
		        {
		            map.addAttribute("user", tdUserService.findOne(userId));
		            
		            TdFinance tdFinance = tdFinanceService.findByUserIdAndTime(userId,date);
		            if (null != tdFinance)
		            {
		            	map.addAttribute("tdFinance", tdFinance);
		            }
		            
		            return "/site_mag/user_finance_edit";
		        }

		        return "/site_mag/center";
		    }	    
		 
		 //财务管理 提交 类TdFinance
		 @RequestMapping(value="/finance/save", method = RequestMethod.POST)
		 @ResponseBody
		    public Map<String,Object> financeSave(TdFinance tdFinance,
		    		Long billId,
		    		Long userId,
		            String __EVENTTARGET,
		            String __EVENTARGUMENT,
		            String __VIEWSTATE,
		            ModelMap map,
		            HttpServletRequest req){
			 Map<String, Object> res = new HashMap<String, Object>();
				res.put("code", 1);
				
		        String username = (String) req.getSession().getAttribute("manager");
		        if (null == username) {
		        	res.put("msg", "请先登录！");
		        	res.put("check", 0);
		            return res;
		        }
		        
		        String logType = null;
		        TdFinance check = tdFinanceService.findByUserIdAndTime(tdFinance.getUserId(), tdFinance.getTime()); //查重，每月应只有一个finance表
		        if (null == tdFinance.getId())
		        {
		            logType = "add";
			        if (null != check)
			        {
			        	res.put("msg", "该月份的票据整理已存在！");
			        	return res;
			        }
		        }
		        else
		        {
		            logType = "edit";
//				        if (null != check && check.getId() != tdGather.getId())
//				        {
//				        	res.put("msg", "该月份的票据整理已存在！");
//				        	return res;
//				        }
		        }
		        
		        tdFinanceService.save(tdFinance);
		        
		        if(null != billId)
		        {
		        	TdBill bill =	tdBillService.findOne(billId);
		        	bill.setStatusId(4L);
		        	tdBillService.save(bill);
		        }
		        
		        tdManagerLogService.addLog(logType, "财务管理", req);
		        

		        res.put("code", 0);
		        return res;
		    }		
		 
		 
		 @RequestMapping(value = "/list/dialog/{type}")
			public String ListDialog(@PathVariable String type, String keywords, Long categoryId, Integer page, Long priceId,
					Integer size, Integer total, String __EVENTTARGET, String __EVENTARGUMENT, String __VIEWSTATE, ModelMap map,
					HttpServletRequest req) {
				String username = (String) req.getSession().getAttribute("manager");
				if (null == username) {
					return "redirect:/Verwalter/login";
				}
				if (null != __EVENTTARGET) {
					if (__EVENTTARGET.equalsIgnoreCase("btnPage")) {
						if (null != __EVENTARGUMENT) {
							page = Integer.parseInt(__EVENTARGUMENT);
						}
					} else if (__EVENTTARGET.equalsIgnoreCase("btnSearch")) {

					} else if (__EVENTTARGET.equalsIgnoreCase("categoryId")) {

					}
				}

				if (null == page || page < 0) {
					page = 0;
				}

				if (null == size || size <= 0) {
					size = SiteMagConstant.pageSize;
					;
				}

				if (null != keywords) {
					keywords = keywords.trim();
				}

//				Page<TdGoods> goodsPage = null;
//
//				if (null == categoryId) {
//					if (null == keywords || "".equalsIgnoreCase(keywords)) {
//						goodsPage = tdGoodsService.findAllOrderBySortIdAsc(page, size);
//					} else {
//						goodsPage = tdGoodsService.searchAndOrderBySortIdAsc(keywords, page, size);
//					}
//				} else {
//					if (null == keywords || "".equalsIgnoreCase(keywords)) {
//						goodsPage = tdGoodsService.findByCategoryIdTreeContainingOrderBySortIdAsc(categoryId, page, size);
//					} else {
//						goodsPage = tdGoodsService.searchAndFindByCategoryIdOrderBySortIdAsc(keywords, categoryId, page, size);
//					}
//				}

//				map.addAttribute("goods_page", goodsPage);

				// 参数注回
//				map.addAttribute("category_list", tdProductCategoryService.findAll());
				map.addAttribute("page", page);
				map.addAttribute("size", size);
				map.addAttribute("total", total);
				map.addAttribute("keywords", keywords);
				map.addAttribute("categoryId", categoryId);
				map.addAttribute("__EVENTTARGET", __EVENTTARGET);
				map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
				map.addAttribute("__VIEWSTATE", __VIEWSTATE);

				if (null != type && type.equalsIgnoreCase("gift")) {
					return "/site_mag/dialog_goods_gift_list";
				}
				else if (null != type && type.equalsIgnoreCase("stock")){
					return "/site_mag/dialog_stock_list";
				}



				return "/site_mag/dialog_goods_combination_list";
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
	/*批量下载 试试----------------- */
	 //把一个用户的票据打包
	    public void btnZip( Long[] ids, Integer[] chkIds, Long userId,
                HttpServletResponse resp,
                HttpServletRequest req) throws IOException {
	    	String filepath = SiteMagConstant.imagePath;	
	    	req.setCharacterEncoding("UTF-8");
	    	
	    	
	        if (null == ids || null == chkIds
	                || ids.length < 1 || chkIds.length < 1 )
	        {
	            return;
	        }
        TdUser user = tdUserService.findOne(userId);
	        
        byte[] buffer = new byte[1024];
        
        //生成的ZIP文件名为Demo.zip
        String strZipName = "Demo.zip";
        File zipfile = new File(filepath +"/" + strZipName);
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
	                Long id = ids[chkId];
	                TdBill bill = tdBillService.findOne(id);
	                if(null != bill)
	                {
	                	String name = bill.getImgUrl();
		                
		                File file = new File(filepath +"/" + name);
			         	FileInputStream fis = new FileInputStream(file);
			         	String ext = name.substring(name.lastIndexOf("."));
			 	        out.putNextEntry(new ZipEntry(user.getUsername()+"_票据"+user.getNumber()+"_"+id+ext));
			 	        
				 	    resp.reset();
			            resp.setHeader("Content-Disposition", "attachment; filename="
			                       + name);
			            resp.setContentType("application/octet-stream; charset=utf-8");
		 	
				 	    int len;
				 	
				 	   //读入需要下载的文件的内容，打包到zip文件
				 	   while((len = fis.read(buffer))>0) {
				 	        out.write(buffer,0,len);
				 	   }

				 	   out.closeEntry();

				 	   fis.close();
				 	   
		        		if(null != bill.getStatusId() && bill.getStatusId() == 2L)
		        		{
		        			bill.setStatusId(3L);   //状态改为票据整理中，即”已下载“
		        			tdBillService.save(bill);
		        		}
	                }
            }
        }

        out.close();
        btnDownload(zipfile, strZipName,userId,resp,req);
    }
	    
	    //把多个用户的票据打包，每个用户建一个文件夹
	    public void btnZipUser( Long[] ids, Integer[] chkIds, 
                HttpServletResponse resp,
                HttpServletRequest req) throws IOException {
	    	String filepath = SiteMagConstant.imagePath;	
	    	req.setCharacterEncoding("UTF-8");
	    	
	        if (null == ids || null == chkIds
	                || ids.length < 1 || chkIds.length < 1 )
	        {
	            return;
	        }
        
        byte[] buffer = new byte[1024];
        
        //生成的ZIP文件名为Demo.zip
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        String date = sdf.format(new Date());
        String strZipName =  "财税宝用户票据"+date+".zip";
        File zipfile = new File(filepath +"/" + strZipName);
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
        int len;
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
	                Long id = ids[chkId];
	                TdUser user = tdUserService.findOne(id);
//	                //每个用户建一个文件夹
//                	File userfile =new File(filepath +"/" + URLEncoder.encode(user.getEnterName(), "UTF-8"));    
//                	//如果文件夹不存在则创建    
//                	if  (!userfile .exists()  && !userfile .isDirectory())      
//                	{       
//                	    System.out.println("//不存在");  
//                	    userfile .mkdir();    
//                	} else   
//                	{  
//                	    System.out.println("//目录存在");  
//                	} 
                	
	                List<TdBill> billList = tdBillService.findByStatusIdAndUserId(2L, user.getId());
	                for (TdBill item : billList)
	                {
	                	
	                	String name = item.getImgUrl();
	                	File file = new File(filepath +"/" + name);
//	                	File filefis = new File(filepath +"/" + name);
//	                	File filefos = new File(userfile +"/" + name);
//	              
//	                	FileInputStream filein = new FileInputStream(filefis);   //源文件存放路径
//	                	FileOutputStream fileout = new FileOutputStream(filefos);  //目标路径
//    	    		 	
//    		 	       
//    		 	       while((len = filein.read(buffer))>0) {
//    		 	    	  fileout.write(buffer,0,len);
//    		 	       }
//    		 	      filein.close();
//    		 	      fileout.close();
	                	
	                	 //读入需要下载的文件的内容，打包到zip文件
	                	String ext = name.substring(name.lastIndexOf("."));
    		 	     out.putNextEntry(new ZipEntry(user.getEnterName()+"_票据"+user.getNumber()+"_"+item.getId()+ext));
	               
    	 	        
    		 	       resp.reset();
    	               resp.setHeader("Content-Disposition", "attachment; filename="
    	                       + URLEncoder.encode(user.getEnterName(), "UTF-8"));
    	               resp.setContentType("application/octet-stream; charset=utf-8");
    	               
 	               FileInputStream fis = new FileInputStream(file);
 		 	        //读入需要下载的文件的内容，打包到zip文件
 		 	
 		 	       while((len = fis.read(buffer))>0) {
 		 	
 		 	        out.write(buffer,0,len);
             }
	               
 			         out.closeEntry();

 			         fis.close();
		 			         
			         //更改状态 已下载
	        		if(null != item.getStatusId() && item.getStatusId() == 2L)
	        		{
	        			item.setStatusId(3L);   //状态改为票据整理中，即”已下载“
	        			tdBillService.save(item);
	        		}
                }
            }
            
        }

        out.close();
        btnDownload(zipfile, strZipName,null,resp,req);
    }
	    
	  //下载打包文件
	    public void btnDownload( File zipfile, String strZipName, Long userId,
	    		 HttpServletResponse resp,
	                HttpServletRequest req) throws IOException {
		    	req.setCharacterEncoding("UTF-8");
        OutputStream os = resp.getOutputStream();  
        String downloadName = strZipName;
        if(null != userId)
        {
    		TdUser user =tdUserService.findOne(userId);
    		if(null != user)
    		{
    			 String ext = strZipName.substring(strZipName.lastIndexOf("."));
//    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
    			downloadName = user.getEnterName()+"票据_"+user.getNumber()+ext;
    		}
        }
        
        if (zipfile.exists())
        {
            try {
                resp.reset();
                resp.setHeader("Content-Disposition", "attachment; filename="
                		+URLEncoder.encode(downloadName, "UTF-8"));
                resp.setContentType("application/octet-stream; charset=utf-8");
                os.write(FileUtils.readFileToByteArray(zipfile));
                os.flush();
            } finally {
                if (os != null) {
                    os.close();
                }
            }
            zipfile.delete();
        }
        else 
        {
        	return;
        }
	 }
	 /*    --------------------批量下载--*/
	 
}
