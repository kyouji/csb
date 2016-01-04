package com.ynyes.csb.controller.front;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.csb.entity.TdUser;
import com.ynyes.csb.service.TdCommonService;
import com.ynyes.csb.service.TdEnterTypeService;
import com.ynyes.csb.service.TdSettingService;
import com.ynyes.csb.service.TdUserService;
import com.ynyes.csb.util.VerifServlet;

/**
 * 注册处理
 * 
 */
@Controller
public class TdRegController {
	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdSettingService tdSettingService;

	@Autowired
	private TdCommonService tdCommonService;
	
	@Autowired
	TdEnterTypeService tdEnterTypeService;
	
	@RequestMapping("/reg")
	public String reg(/* Integer shareId, */String name,  Long type, HttpServletRequest request,
			ModelMap map) {
		String username = (String) request.getSession().getAttribute("username");

//		if (null != shareId) {
//			map.addAttribute("share_id", shareId);
//		}
		// 基本信息
		tdCommonService.setHeader(map, request);

		if (null == username) {
			
			map.addAttribute("username", name);
			
			if (null != type &&type == 1)
			{
				return "/client/reg_acc";
			}
			else{
				map.addAttribute("enterType_list", tdEnterTypeService.findByIsEnableTrueOrderBySortIdAsc());
				return "/client/reg_enter";
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/logutil")
	public String LogUtils() {
		return "/logutil";
	}

	/**
	 * 
	 * 注册用户保存到数据库<BR>
	 * 方法名：saveUser<BR>
	 * 时间：2015年2月2日-下午1:44:45 <BR>
	 * 
	 * @param user
	 * @param name
	 * @param mobile
	 * @param password
	 * @param newpassword
	 * @return String<BR>
	 * @param [参数1]
	 *            [参数1说明]
	 * @param [参数2]
	 *            [参数2说明]
	 * @exception <BR>
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> reg(String username, 
									String mobile, 
									String password, 
									String password2, 
									String realName,
									String enterName,
									String enterType, 
									Long enterTypeId,
									Long roleId,
									Long changeRole,
									ModelMap map,
									HttpServletRequest request) {
	    Map<String, Object> res = new HashMap<String, Object>();
	    res.put("code", 1);
		
		if (null == realName ||realName.equals(""))
		{
			res.put("msg", "联系人姓名不能为空！");
			return res;
		}
		if (null == username ||username.equals(""))
		{
			res.put("msg", "用户名不能为空！");
			return res;
		}
		if (null == mobile || mobile.equals(""))
		{
			res.put("msg", "联系电话不能为空！");
			return res;
		}
		if(!isMobileNO(mobile))
		{
			res.put("msg", "电话号码格式不对！");
			return res;
		}
		if(null == password || password.equals(""))
		{
			res.put("msg", "密码不能为空！");
			return res;
		}
		if (null == password2 || password2.equals("") || null != password2 && !password2.equals(password))
		{
			res.put("msg", "两次输入密码不一致！");
			return res;
		}
		TdUser user1 = tdUserService.findByUsername(username);
		if (null != user1) {
			res.put("msg", "该用户名已被注册！");
			return res;
		}
		TdUser user2 = tdUserService.findByMobile(mobile);
		if (null != user2) {
			res.put("msg", "该联系电话已被注册！");
			return res;
		}
		if(null != enterType && !enterType.equals("") && (null == enterName ||enterName.equals("")))
		{
			res.put("msg", "公司名称不能为空！");
			return res;
		}

		
		TdUser user = new TdUser();
		user.setRealName(realName);
		user.setUsername(username);
		user.setPassword(password);
		user.setMobile(mobile);
		if(null != enterName)
		{
			user.setEnterName(enterName);
		}
		user.setEnterType(enterType);
		user.setEnterTypeId(enterTypeId);
		user.setStatusId(1L);
		user.setRegisterTime(new Date());
		user.setRoleId(roleId);
		user.setLastLoginTime(new Date());
		tdUserService.save(user);
		
	    res.put("code", 0);
	    return res;

	}
	
	@RequestMapping(value = "/reg/changeRole", method = RequestMethod.POST)
	public String regChanegeRole(String username, 
									String realName,
									String mobile, 
									Long changeRole,
									ModelMap map,
									HttpServletRequest request) {
		
		if(null != changeRole)
		{
			map.addAttribute("realName", realName);
			map.addAttribute("username", username);
			map.addAttribute("mobile", mobile);
			
			if(changeRole == 0L)
			{
				map.addAttribute("enterType_list", tdEnterTypeService.findByIsEnableTrueOrderBySortIdAsc());
				return "/client/reg_enter";
			}
			else if(changeRole == 1L){
				return "/client/reg_acc";
			}
		}
	
    return "redirect:/reg";
	}

	public boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^(0|86|17951|[0-9]{3})?([0-9]{8})|((13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8})$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
		}

}