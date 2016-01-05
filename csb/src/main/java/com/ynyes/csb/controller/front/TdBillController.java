package com.ynyes.csb.controller.front;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.csb.entity.TdBill;
import com.ynyes.csb.entity.TdUser;
import com.ynyes.csb.service.TdBillService;
import com.ynyes.csb.service.TdBillTypeService;
import com.ynyes.csb.service.TdCommonService;
import com.ynyes.csb.service.TdUserService;

/**
 * 票据管理
 *@author Zhangji
 */
@Controller
@RequestMapping("/bill")
public class TdBillController {

    
    @Autowired
    TdCommonService tdCommonService;
    
    @Autowired
    TdUserService tdUserService;
    
    @Autowired
    TdBillService tdBillService;
    
    @Autowired
    TdBillTypeService tdBillTypeService;

    @RequestMapping(value="/upload" , method=RequestMethod.GET)
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
        
        //该客户待确认上传的图片列表
        List<TdBill> billList = tdBillService.findByStatusIdAndUsername(1L, username);
        map.addAttribute("bill_list", billList);
        
        //票据类别列表
        map.addAttribute("billType_list", tdBillTypeService.findByIsEnableTrueOrderBySortIdAsc());
        
        return "/client/bill_upload";
    }
    
    
    @RequestMapping(value="/upload/add" )
    public String billUploadAdd(Long billId,HttpServletRequest req,  ModelMap map) {        
        
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
        TdBill tdBill = tdBillService.findOne(billId);
        map.addAttribute("bill", tdBill);

        return "/client/bill_upload_add";
    }
    
    //单个图片页面，单个确认页面
    @RequestMapping(value="/upload/confirm" )
    public String billUploadConfirm(Long billId,HttpServletRequest req,  ModelMap map) {        
        
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
        TdBill tdBill = tdBillService.findOne(billId);
        tdBill.setStatusId(1L); //待确认状态
        tdBillService.save(tdBill);
        map.addAttribute("bill", tdBill);

        return "/client/bill_upload";
    }
    
    //完成上传 改变状态id
	@RequestMapping(value = "/upload/finish", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> billUploadFinish(Long billTypeId, Long[] billIds, 
			 HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();

		res.put("code", 1);

        String username = (String) req.getSession().getAttribute("username");
        if(null == username)
        {
        	res.put("msg", "请先登陆");
        	res.put("login", 1);
        	return res;
        }
		
        if(null == billTypeId)
        {
        	res.put("msg", "请选择票据类别！");
        	return res;
        }
        if(null == billIds || billIds.length < 1)
        {
        	res.put("msg", "请先拍摄票据并上传！");
        	return res;
        }
        
        for(Long id : billIds)
        {
        	TdBill tdBill = tdBillService.findOne(id);
        	tdBill.setStatusId(2L); 
        	tdBill.setBillTypeId(billTypeId);
        	tdBill.setTime(new Date());
        	tdBillService.save(tdBill);
        }
        	res.put("code", 0);
			return res;
	
	}
	
    //查询进度
    @RequestMapping(value="/check" )
    public String billCheck(Long billId,HttpServletRequest req,  ModelMap map) {        
        
        String username = (String) req.getSession().getAttribute("username");
        if(null == username)
        {
        	return "redirect:/login";
        }
        map.addAttribute("username" , username);

        //该客户待确认上传的图片列表
        List<TdBill> billList = tdBillService.findByUsername(username);
        if(billList.size() > 0)
        {
            TdBill bill = billList.get(0);
            map.addAttribute("bill", bill);
        }
        else{
        	return "/";
        }

        return "/client/bill_check";
    }
    
}
