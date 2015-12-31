package com.ynyes.csb.controller.front;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.csb.entity.TdArticle;
import com.ynyes.csb.entity.TdArticleCategory;
import com.ynyes.csb.service.TdArticleCategoryService;
import com.ynyes.csb.service.TdArticleService;
import com.ynyes.csb.service.TdCommonService;
import com.ynyes.csb.util.ClientConstant;

/**
 * 前端首页控制
 *
 */
@Controller
@RequestMapping("/")
public class TdIndexController {

    @Autowired
    private TdArticleService tdArticleService;

    @Autowired
    private TdArticleCategoryService tdArticleCategoryService;
    
    @Autowired
    TdCommonService tdCommonService;

    @RequestMapping
    public String index(HttpServletRequest req, Device device, ModelMap map) {        
        
    
        tdCommonService.setHeader(map, req);              
        
        String username = (String) req.getSession().getAttribute("username");
        map.addAttribute("username" , username);
        
        //新闻动态
        List<TdArticle> tdArticles = tdArticleService.findByMenuId(10L);
        if (null != tdArticles) {
			map.addAttribute("news_list", tdArticles);
		}

        return "/client/index";
    }
}
