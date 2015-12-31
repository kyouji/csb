package com.ynyes.csb.controller.management;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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


import com.ynyes.csb.entity.TdUser;
import com.ynyes.csb.service.TdManagerLogService;
import com.ynyes.csb.service.TdUserService;
import com.ynyes.csb.util.SiteMagConstant;

/**
 * 后台用户管理控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value="/Verwalter/user")
public class TdManagerUserController {
    
    @Autowired
    TdUserService tdUserService;
    
    @Autowired
    TdManagerLogService tdManagerLogService;
    
    @RequestMapping(value="/check", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> validateForm(String param, Long id) {
        Map<String, String> res = new HashMap<String, String>();
        
        res.put("status", "n");
        
        if (null == param || param.isEmpty())
        {
            res.put("info", "该字段不能为空");
            return res;
        }
        
        if (null == id)
        {
            if (null != tdUserService.findByUsername(param))
            {
                res.put("info", "已存在同名用户");
                return res;
            }
        }
        else
        {
            if (null != tdUserService.findByUsernameAndIdNot(param, id))
            {
                res.put("info", "已存在同名用户");
                return res;
            }
        }
        
        res.put("status", "y");
        
        return res;
    }
    
    
    
    //企业添加编辑 检验重复电话
    @RequestMapping(value = "/check/mobile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> validateFormMobile(String param, Long id) {
        Map<String, String> res = new HashMap<String, String>();

        res.put("status", "n");

	        if (null == param || param.isEmpty()) {						
	            res.put("info", "该字段不能为空");
	            return res;
	        }
	        
	        TdUser tdUser = tdUserService.findByMobile(param);
	        
	        if (null == id) // 新增
	        {
	            if (null != tdUser) {
	                res.put("info", "该手机号不能使用");
	                return res;
	            }
	        } 
	        else // 修改，查找除当前ID的所有
	        {
	            TdUser thisUser = tdUserService.findOne(id);
	            
	            if (null == thisUser)
	            {
	                if (null != tdUser) {
	                    res.put("info", "该手机号不能使用");
	                    return res;
	                }
	            }
	            else
	            {
	                if (null != tdUser && !tdUser.getMobile().equals(thisUser.getMobile())  ) {
	                    res.put("info", "该手机号已被使用");
	                    return res;
	                }
	            }
	        }

        
        res.put("status", "y");

        return res;
    }
    
    @RequestMapping(value="/list")
    public String setting(Integer page,
                          Integer size,
                          String keywords,
                          Long roleId,
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
            else if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDelete("user", listId, listChkId);
                tdManagerLogService.addLog("delete", "删除用户", req);
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
        map.addAttribute("roleId", roleId);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);

        Page<TdUser> userPage = null;
        
//        if (null == roleId)
//        {
//            if (null == keywords || "".equalsIgnoreCase(keywords))
//            {
//                userPage = tdUserService.findAllOrderBySortIdAsc(page, size);
//            }
//            else
//            {
//                userPage = tdUserService.searchAndOrderByIdDesc(keywords, page, size);
//            }
//        }
//        else
//        {
//            if (null == keywords || "".equalsIgnoreCase(keywords))
//            {
//                userPage = tdUserService.findByRoleIdOrderByIdDesc(roleId, page, size);
//            }
//            else
//            {
//                userPage = tdUserService.searchAndFindByRoleIdOrderByIdDesc(keywords, roleId, page, size);
//            }
//        }
        
        //只查找企业
      if (null == keywords || "".equalsIgnoreCase(keywords))
      {
          userPage = tdUserService.findByRoleIdOrderByIdDesc(1L, page, size);
      }
      else
      {
          userPage = tdUserService.searchAndFindByRoleIdOrderByIdDesc(keywords, 1L, page, size);
      }
        
        map.addAttribute("user_page", userPage);
        
        return "/site_mag/user_list";
    }
    
    @RequestMapping(value="/edit")
    public String orderEdit(Long id,
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
        	TdUser user = tdUserService.findOne(id);
            map.addAttribute("user",user);
            map.addAttribute("id",id);
            
        }
        return "/site_mag/user_edit";
    }
    

    
    @RequestMapping(value="/save")
    public String orderEdit(TdUser tdUser,Long totalPoints, String totalPointsRemarks,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if (null == tdUser.getId())
        {
            tdManagerLogService.addLog("add", "创建用户", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "修改用户", req);
        }
        
        tdUserService.save(tdUser);
        
        if (tdUser.getRoleId()==1)
        {
        	
        }
        
        return "redirect:/Verwalter/user/list/";
    }
    
    
    
    @RequestMapping(value = "/role" , method = RequestMethod.GET)
    public String userAssumingControl(Long id,HttpServletRequest request , ModelMap map){
        String username = (String) request.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
    	
        if (null != id )
        {
        	TdUser user = tdUserService.findOne(id);
        	
        	System.err.println(user);
			Integer roleId = user.getRoleId().intValue();
			request.getSession().setMaxInactiveInterval(60 * 60 * 2);
			
			if (null != roleId && roleId == 1)
			{
				request.getSession().setAttribute("enterpriseUsername", user.getUsername());
				request.getSession().setAttribute("enterpriseUsermobile", user.getMobile());
				request.getSession().setAttribute("username", user.getUsername());
				return "redirect:/enterprise/check";
			}
			
        }
        
    	return "redirect:/user/list";
    }
    


    
//    @RequestMapping(value="/{type}/list")
//    public String list(@PathVariable String type,
//                        Integer page,
//                        Integer size,
//                        Long userId,
//                        Long statusId,
//                        String keywords,
//                        String __EVENTTARGET,
//                        String __EVENTARGUMENT,
//                        String __VIEWSTATE,
//                        Long[] listId,
//                        Integer[] listChkId,
//                        Long[] listSortId,
//                        ModelMap map,
//                        HttpServletRequest req){
//        String username = (String) req.getSession().getAttribute("manager");
//        if (null == username)
//        {
//            return "redirect:/Verwalter/login";
//        }
//        if (null != __EVENTTARGET)
//        {
//            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
//            {
//                if (null != __EVENTARGUMENT)
//                {
//                    page = Integer.parseInt(__EVENTARGUMENT);
//                } 
//            }
//            else if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
//            {
//                btnDelete(type, listId, listChkId);
//            }
//            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
//            {
//                btnSave(type, listId, listSortId);
//            }
//            else if (__EVENTTARGET.equalsIgnoreCase("btnVerify"))
//            {
//                btnVerify(type, listId, listChkId);
//            }
//        }
//        
//        if (null == page || page < 0)
//        {
//            page = 0;
//        }
//        
//        if (null == size || size <= 0)
//        {
//            size = SiteMagConstant.pageSize;;
//        }
//        
//        if (null != keywords)
//        {
//            keywords = keywords.trim();
//        }
//        
//        map.addAttribute("page", page);
//        map.addAttribute("size", size);
//        map.addAttribute("userId", userId);
//        map.addAttribute("statusId", statusId);
//        map.addAttribute("keywords", keywords);
//        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
//        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
//        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
//            
//        if (null != type)
//        {
//            if (type.equalsIgnoreCase("point")) // 积分
//            {
//                if (null == userId)
//                {
//                    return "/site_mag/error_404";
//                }
//                
//                TdUser user = tdUserService.findOne(userId);
//                
//                if (null == user || null == user.getUsername())
//                {
//                    return "/site_mag/error_404";
//                }
//                
//                map.addAttribute("user_point_page", tdUserPointService.findByUsername(user.getUsername(), page, size));
//                return "/site_mag/user_point_list";
//            }
//            else if (type.equalsIgnoreCase("collect")) // 关注
//            {
//                if (null == userId)
//                {
//                    return "/site_mag/error_404";
//                }
//                
//                TdUser user = tdUserService.findOne(userId);
//                
//                if (null == user || null == user.getUsername())
//                {
//                    return "/site_mag/error_404";
//                }
//                
//                map.addAttribute("user_collect_page", tdUserCollectService.findByUsername(user.getUsername(), page, size));
//                return "/site_mag/user_collect_list";
//            }
//            else if (type.equalsIgnoreCase("recent")) // 最近浏览
//            {
//                if (null == userId)
//                {
//                    return "/site_mag/error_404";
//                }
//                
//                TdUser user = tdUserService.findOne(userId);
//                
//                if (null == user || null == user.getUsername())
//                {
//                    return "/site_mag/error_404";
//                }
//                
//                map.addAttribute("user_recent_page", tdUserRecentVisitService.findByUsernameOrderByVisitTimeDesc(user.getUsername(), page, size));
//                return "/site_mag/user_recent_list";
//            }
//            else if (type.equalsIgnoreCase("reward")) // 返现
//            {
//                if (null == userId)
//                {
//                    return "/site_mag/error_404";
//                }
//                
//                TdUser user = tdUserService.findOne(userId);
//                
//                if (null == user || null == user.getUsername())
//                {
//                    return "/site_mag/error_404";
//                }
//                
//                map.addAttribute("user_reward_page", tdUserCashRewardService.findByUsernameOrderByIdDesc(user.getUsername(), page, size));
//                return "/site_mag/user_reward_list";
//            }
//            else if (type.equalsIgnoreCase("level")) // 用户等级
//            {
//                map.addAttribute("user_level_page", tdUserLevelService.findAllOrderBySortIdAsc(page, size));
//                return "/site_mag/user_level_list";
//            }
//            else if (type.equalsIgnoreCase("consult")) // 用户咨询
//            {
//                map.addAttribute("user_consult_page", findTdUserConsult(statusId, keywords, page, size));
//                return "/site_mag/user_consult_list";
//            }
//            else if (type.equalsIgnoreCase("comment")) // 用户评论
//            {
//                map.addAttribute("user_comment_page", findTdUserComment(statusId, keywords, page, size));
//                return "/site_mag/user_comment_list";
//            }
//            else if (type.equalsIgnoreCase("return")) // 退换货
//            {
//                map.addAttribute("user_return_page", findTdUserReturn(statusId, keywords, page, size));
//                return "/site_mag/user_return_list";
//            }
//        }
//        
//        return "/site_mag/error_404";
//    }
    
    @ModelAttribute
    public void getModel(@RequestParam(value = "userId", required = false) Long userId,
                    @RequestParam(value = "userLevelId", required = false) Long userLevelId,
                    @RequestParam(value = "userConsultId", required = false) Long userConsultId,
                    @RequestParam(value = "userCommentId", required = false) Long userCommentId,
                    @RequestParam(value = "userReturnId", required = false) Long userReturnId,
                        Model model) {
        if (null != userId) {
            model.addAttribute("tdUser", tdUserService.findOne(userId));
        }
    }


    
//    private void btnSave(String type, Long[] ids, Long[] sortIds)
//    {
//        if (null == ids || null == sortIds
//                || ids.length < 1 || sortIds.length < 1
//                || null == type || "".equals(type))
//        {
//            return;
//        }
//        
//        for (int i = 0; i < ids.length; i++)
//        {
//            Long id = ids[i];
//            
//            if (type.equalsIgnoreCase("user")) // 用户
//            {
//                TdUser e = tdUserService.findOne(id);
//                
//                if (null != e)
//                {
//                    if (sortIds.length > i)
//                    {
//                        e.setSortId(sortIds[i]);
//                        tdUserService.save(e);
//                    }
//                }
//            }
//        }
//    }
    
    private void btnDelete(String type, Long[] ids, Integer[] chkIds)
    {
        if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1 
                || null == type || "".equals(type))
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                
                if (type.equalsIgnoreCase("user")) // 用户
                {
                	
                    tdUserService.delete(tdUserService.findOne(id));
                    
                }
            }
        }
    }
    
//    private void btnVerify(String type, Long[] ids, Integer[] chkIds)
//    {
//        if (null == ids || null == chkIds
//                || ids.length < 1 || chkIds.length < 1 
//                || null == type || "".equals(type))
//        {
//            return;
//        }
//        
//        for (int chkId : chkIds)
//        {
//            if (chkId >=0 && ids.length > chkId)
//            {
//                Long id = ids[chkId];
//                
//                if (type.equalsIgnoreCase("consult")) // 咨询
//                {
//                    TdUserConsult e = tdUserConsultService.findOne(id);
//                    
//                    if (null != e)
//                    {
//                        e.setStatusId(1L);
//                        tdUserConsultService.save(e);
//                    }
//                }
//                else if (type.equalsIgnoreCase("comment")) // 评论
//                {
//                    TdUserComment e = tdUserCommentService.findOne(id);
//                    
//                    if (null != e)
//                    {
//                        e.setStatusId(1L);
//                        tdUserCommentService.save(e);
//                    }
//                }
//                else if(type.equalsIgnoreCase("demand"))  //团购要求      @zhangji 2015年7月30日11:23:51
//                {
//                	TdDemand e = tdDemandService.findOne(id);
//                	
//                	if (null != e)
//                	{
//                		e.setStatusId(1L);
//                		tdDemandService.save(e);
//                	}
//                		
//                }
//                else if (type.equalsIgnoreCase("return")) // 退换货
//                {
//                    TdUserReturn e = tdUserReturnService.findOne(id);
//                    
//                    if (null != e)
//                    {
//                        e.setStatusId(1L);
//                        tdUserReturnService.save(e);
//                    }
//                }
//            }
//        }
//    }
}
