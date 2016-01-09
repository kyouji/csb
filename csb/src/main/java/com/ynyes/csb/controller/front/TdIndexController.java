package com.ynyes.csb.controller.front;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.csb.entity.TdUser;
import com.ynyes.csb.service.TdCommonService;
import com.ynyes.csb.service.TdUserService;

/**
 * 首页（我的）【需登陆】
 * 角色数：2
 * 会计【唯一界面】、客户。
 *@author Zhangji
 */
@Controller
@RequestMapping("/")
public class TdIndexController {

    
    @Autowired
    TdCommonService tdCommonService;
    
    @Autowired
    TdUserService tdUserService;

    @RequestMapping
    public String index(HttpServletRequest req, Device device, ModelMap map) {        
    	tdCommonService.setHeader(map, req); 
    	
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
            map.addAttribute("user", user);
        }

        return "/client/index";
    }
    
    @RequestMapping("/index")
    public String Index(HttpServletRequest req, Device device, ModelMap map) {        
    	tdCommonService.setHeader(map, req); 
    	
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
