package com.ynyes.csb.controller.management;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.ynyes.csb.entity.TdSetting;
import com.ynyes.csb.service.TdEnterTypeService;
import com.ynyes.csb.service.TdManagerLogService;
import com.ynyes.csb.service.TdSettingService;
import com.ynyes.csb.util.SiteMagConstant;
import com.ynyes.csb.entity.TdEnterType;

/**
 * 后台广告管理控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value="/Verwalter/setting")
public class TdManagerSettingController {
    
    @Autowired
    TdSettingService tdSettingService;
    
    @Autowired
    TdManagerLogService tdManagerLogService;
    
    @Autowired
    TdEnterTypeService tdEnterTypeService;
    
    @RequestMapping
    public String setting(Long status, ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("setting", tdSettingService.findTopBy());
        map.addAttribute("status", status);
        
        return "/site_mag/setting_edit";
    }
    
    @RequestMapping(value="/save")
    public String orderEdit(TdSetting tdSetting,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        if (null == tdSetting.getId())
        {
            tdManagerLogService.addLog("add", "用户修改系统设置", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "用户修改系统设置", req);
        }
        
        tdSettingService.save(tdSetting);
        
        return "redirect:/Verwalter/setting?status=1";
    }
    
    /*--------------------公司类型  begin ------------------------*/ 
    /**
     * 公司类型列表
     * @author Zhangji
     */
    @RequestMapping(value="/enterType/list")
    public String enterTypeList(String __EVENTTARGET,
                        String __EVENTARGUMENT,
                        String __VIEWSTATE,
                        Long[] listId,
                        Integer[] listChkId,
                        Long[] listSortId,
                        Boolean[] listIsEnable,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDeleteEnterType(listId, listChkId);
                
                tdManagerLogService.addLog("edit", "删除活动区域", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
            {
                btnSaveEnterType(listId, listSortId ,listIsEnable);
                
                tdManagerLogService.addLog("edit", "修改活动区域", req);
            }
        }

        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
                
        map.addAttribute("enterType_list", tdEnterTypeService.findAllOrderBySortIdAsc());
                
        return "/site_mag/enterType_list";
    }
    
    /**
     * 公司类型编辑
     * @author Zhangji
     */
    @RequestMapping(value="/enterType/edit")
    public String edit(Long id,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("manager");
        
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if (null != id)
        {
            map.addAttribute("enterType", tdEnterTypeService.findOne(id));
        }
        
        return "/site_mag/enterType_edit";
    }
    
    @RequestMapping(value="/enterType/save", method = RequestMethod.POST)
    public String EnterTypeSave(TdEnterType tdEnterType,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        tdEnterTypeService.save(tdEnterType);
        
        tdManagerLogService.addLog("edit", "修改活动区域", req);
        
        return "redirect:/Verwalter/setting/enterType/list";
    }
    
    //删除
    private void btnDeleteEnterType(Long[] ids, Integer[] chkIds)
    {
    	if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1)
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                
                tdEnterTypeService.delete(id);
            }
        }
    }

    private void btnSaveEnterType(Long[] ids, Long[] sortIds, Boolean[] isEnables)
    {
        if (null == ids || null == sortIds || null == isEnables
                || ids.length < 1 || sortIds.length < 1 || isEnables.length < 1)
        {
            return;
        }
        
        for (int i = 0; i < ids.length; i++)
        {
            Long id = ids[i];
            
            TdEnterType e = tdEnterTypeService.findOne(id);
            
            if (null != e)
            {
                if (sortIds.length > i && isEnables.length > i)
                {
                    e.setSortId(sortIds[i]);
                    e.setIsEnable(isEnables[i]);
                    tdEnterTypeService.save(e);
                }
            }
        }
    }
    /*--------------------公司类型  end ------------------------*/ 
}
