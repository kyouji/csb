package com.ynyes.csb.controller.front;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynyes.csb.entity.TdUser;
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

    @RequestMapping(value="/upload" , method=RequestMethod.GET)
    public String index(Long id,HttpServletRequest req,  ModelMap map) {        
        
        String username = (String) req.getSession().getAttribute("username");
        if(null == username)
        {
        	return "redirect:/login";
        }
        map.addAttribute("username" , username);
        
        TdUser user = tdUserService.findOne(id);
        if (null != user)
        {
            map.addAttribute("user", user);
        }

        return "/client/bill_upload";
    }
    
    @RequestMapping("/index")
    public String Index(HttpServletRequest req, Device device, ModelMap map) {        
        
        String username = (String) req.getSession().getAttribute("username");
        if(null == username)
        {
        	return "redirect:/login";
        }
        map.addAttribute("username" , username);
        
        TdUser user = tdUserService.findByUsername(username);
        if (null != user)
        {
            map.addAttribute("roleId", user.getRoleId());
        }

        return "/client/index";
    }
}
