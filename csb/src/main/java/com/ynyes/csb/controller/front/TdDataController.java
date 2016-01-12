package com.ynyes.csb.controller.front;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.csb.entity.TdBill;
import com.ynyes.csb.entity.TdData;
import com.ynyes.csb.entity.TdNavigationMenu;
import com.ynyes.csb.entity.TdUser;
import com.ynyes.csb.service.TdArticleService;
import com.ynyes.csb.service.TdBillService;
import com.ynyes.csb.service.TdCommonService;
import com.ynyes.csb.service.TdDataService;
import com.ynyes.csb.service.TdDataTypeService;
import com.ynyes.csb.service.TdNavigationMenuService;
import com.ynyes.csb.service.TdUserService;
import com.ynyes.csb.util.SiteMagConstant;

@Controller
public class TdDataController {
	
	@Autowired
    private TdCommonService tdCommonService;
	
	@Autowired
	private TdDataService tdDataService;
	
	@Autowired
	private TdDataTypeService tdDataTypeService;
	
	@Autowired
	private TdUserService tdUserService;
	
	@Autowired
	private TdNavigationMenuService tdNavigationMenuService;
	
	@Autowired
	private TdBillService tdBillService;
	
	String filepath = SiteMagConstant.imagePath;

	 @RequestMapping(value="/data" , method=RequestMethod.GET)
	    public String billUpload(Long id,HttpServletRequest req,  ModelMap map) {        
	        
	        String username = (String) req.getSession().getAttribute("username");
	        if(null == username)
	        {
	        	return "redirect:/login";
	        }
	        map.addAttribute("username" , username);
	        
	        TdUser user = tdUserService.findByUsername(username);
	        if (null != user)
	        {
	            map.addAttribute("user", user);
	        }
	        
//	        //该客户待确认上传的图片列表
//	        List<TdData> dataList = tdDataService.findByUsernameAndDataTypeIdOrderBySortIdAsc(username, dataTypeId);
//	        map.addAttribute("data_list", dataList);
	        /*
	         * TODO
	         * 想办法按类型、月份查找数据
	         */

	        
	        return "/client/data";
	    }
	 
	 @RequestMapping(value="/download/data", method = RequestMethod.GET)
	    @ResponseBody
	    public void download(String name,Long id,
	                HttpServletResponse resp,
	                HttpServletRequest req) throws IOException {
	        if (null == name)
	        {
	            return;
	        }
	        
	        OutputStream os = resp.getOutputStream();  
	        
	        File file = new File(filepath +"/" + name);
	        
	        if (file.exists())
	        {
	            try {
	                resp.reset();
	                resp.setHeader("Content-Disposition", "attachment; filename="
	                		+URLEncoder.encode(name, "UTF-8"));
	                resp.setContentType("application/octet-stream; charset=utf-8");
	                os.write(FileUtils.readFileToByteArray(file));
	                os.flush();
	            } finally {
	                if (os != null) {
	                    os.close();
	                }
	            }
	        }
	        else 
	        {
	        	return;
	        }
	    }
	
	//票据下载
	@RequestMapping(value="/download/bill/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void downloadBill(String name,@PathVariable Long id,
                HttpServletResponse resp,
                HttpServletRequest req) throws IOException {
        if (null == name)
        {
            return;
        }
        
        OutputStream os = resp.getOutputStream();  
        
        File file = new File(filepath +"/" + name);
        
        String downloadName = name;
        if(null != id)
        {
        	TdBill bill = tdBillService.findOne(id);
        	
        	if(null != bill)
        	{
        		TdUser user =tdUserService.findOne(bill.getUserId());
        		if(null != user)
        		{
        			 String ext = name.substring(name.lastIndexOf("."));
        			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        			downloadName = sdf.format(bill.getTime())+"票据_"+user.getEnterName()+"_"+user.getNumber()+ext;
        		}
        		if(null != bill.getStatusId() && bill.getStatusId() == 2L)
        		{
        			bill.setStatusId(3L);   //状态改为票据整理中，即”已下载“
        			tdBillService.save(bill);
        		}
        	}
        }
        
        if (file.exists())
        {
            try {
                resp.reset();
                resp.setHeader("Content-Disposition", "attachment; filename="
                		+URLEncoder.encode(downloadName, "UTF-8"));
                resp.setContentType("application/octet-stream; charset=utf-8");
                os.write(FileUtils.readFileToByteArray(file));
                os.flush();
            } finally {
                if (os != null) {
                    os.close();
                }
            }
        }
        else 
        {
        	return;
        }
    }
}
