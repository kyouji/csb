package com.ynyes.csb.controller.front;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.ynyes.csb.entity.TdApply;
import com.ynyes.csb.entity.TdApplyType;
import com.ynyes.csb.entity.TdArea;
import com.ynyes.csb.entity.TdBill;
import com.ynyes.csb.entity.TdDemand;
import com.ynyes.csb.entity.TdPhoto;
import com.ynyes.csb.entity.TdUser;
import com.ynyes.csb.service.TdApplyService;
import com.ynyes.csb.service.TdApplyTypeService;
import com.ynyes.csb.service.TdAreaService;
import com.ynyes.csb.service.TdCommonService;
import com.ynyes.csb.service.TdDemandService;
import com.ynyes.csb.service.TdEnterTypeService;
import com.ynyes.csb.service.TdPhotoService;
import com.ynyes.csb.service.TdUserService;
import com.ynyes.csb.util.ClientConstant;

/**
 * 用户中心
 * 
 * @author Sharon
 *
 */
@Controller
public class TdUserController {

	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdDemandService tdDemandService;

	@Autowired
	private TdCommonService tdCommonService;
	
	@Autowired
	private TdEnterTypeService tdEnterTypeService;
	
	@Autowired
	private TdAreaService tdAreaService;
	
	@Autowired
	private TdApplyTypeService tdApplyTypeService;
	
	@Autowired
	private TdApplyService tdApplyService;
	
	@Autowired
	private TdPhotoService tdPhotoService;

	@RequestMapping(value = "/user/center")
	public String user(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			return "redirect:/login";
		}

		tdCommonService.setHeader(map, req);

		map.addAttribute("server_ip", req.getLocalName());
		map.addAttribute("server_port", req.getLocalPort());

		TdUser tdUser = tdUserService.findByUsername(username);

		if (null == tdUser) {
			return "/client/error_404";
		}

		map.addAttribute("user", tdUser);

		return "/client/user_center";
	}
	
	@RequestMapping(value = "/user/about")
	public String userAbout(HttpServletRequest req, ModelMap map) {
		
		tdCommonService.setHeader(map, req);
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			return "redirect:/login";
		}

		TdUser tdUser = tdUserService.findByUsername(username);

		if (null == tdUser) {
			return "/client/error_404";
		}

		map.addAttribute("user", tdUser);

		return "/client/user_about";
	}

	

	@RequestMapping(value = "/demand/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> demandAdd(HttpServletRequest req, String content, String name, String mobile,
			String mail, Long statusId, ModelMap map) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("code", 1);

		TdDemand tdDemand = new TdDemand();

		tdDemand.setContent(content);
		tdDemand.setTime(new Date());
		tdDemand.setName(name);
		tdDemand.setMail(mail);
		tdDemand.setMobile(mobile);
		tdDemand.setStatusId(0L);

		// TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

		tdDemandService.save(tdDemand);

		map.addAttribute("demand_list", tdDemand);

		res.put("code", 0);

		return res;
	}

	@RequestMapping(value = "/user/comment/sec")
	public String commentSec(HttpServletRequest req, Long commentId, ModelMap map) {
		return "/client/comment_sec";
	}



	@RequestMapping("/user/check/oldpassword")
	@ResponseBody
	public Map<String, Object> checkOldPassword(HttpServletRequest req, String param) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", "n");

		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsername(username);
		if (!param.equals(user.getPassword())) {
			res.put("info", "当前密码输入错误！");
			return res;
		}
		res.put("status", "y");
		return res;
	}



	@RequestMapping(value = "/user/info", method = RequestMethod.GET)
	public String userInfo(Long hatu , HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");

		if (null == username) {
			return "redirect:/login";
		}

		tdCommonService.setHeader(map, req);
		
		map.addAttribute("enterType_list", tdEnterTypeService.findByIsEnableTrueOrderBySortIdAsc());

		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

		map.addAttribute("user", user);
		map.addAttribute("hatu", hatu);
		
		
        //该客户待确认上传的图片列表
        List<TdPhoto> photoList1 = tdPhotoService.findByStatusIdAndUserId(1L, user.getId());
        List<TdPhoto> photoList2 = tdPhotoService.findByStatusIdAndUserId(2L, user.getId());
       
        map.addAttribute("photo_list1", photoList1);
        map.addAttribute("photo_list2", photoList2);

		return "/client/user_info";
	}

	@RequestMapping(value = "/user/info", method = RequestMethod.POST)
	@ResponseBody
	public Map<String , Object> userInfo(HttpServletRequest req,
													String enterName,
													Long enterTypeId,
													String username,
													String realName, 
													String mobile, 
													Long[] photoIds,
													HttpServletRequest request) {
		Map<String, Object> res = new HashMap<String, Object>();
	    res.put("code", 1);
		
		String usernameSession = (String) req.getSession().getAttribute("username");

		if (null == usernameSession) {
			res.put("msg", "请先登录！");
			res.put("login", 1);
			return res;
		}

		TdUser user = tdUserService.findByUsernameAndIsEnabled(usernameSession);
		
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

		TdUser user1 = tdUserService.findByUsername(username);
		if (null != user1 && user1.getId() != user.getId()) {
			res.put("msg", "该用户名已被注册！");
			return res;
		}
		TdUser user2 = tdUserService.findByMobile(mobile);
		if (null != user2 && user1.getId() != user.getId()) {
			res.put("msg", "该联系电话已被注册！");
			return res;
		}
		if(null != user.getRoleId() && user.getRoleId() == 0 && (null == enterName ||enterName.equals("")))
		{
			res.put("msg", "公司名称不能为空！");
			return res;
		}
		
		if(user.getRoleId()==0)
		{
			user.setEnterName(enterName);
			user.setEnterTypeId(enterTypeId);
			user.setEnterType(tdEnterTypeService.findOne(enterTypeId).getTitle());
		}

		user.setUsername(username);
		user.setRealName(realName);
		user.setMobile(mobile);
		user = tdUserService.save(user);
		
		request.getSession().setAttribute("username", user.getUsername());
        
		if(null != photoIds)
		{
	        for(Long id : photoIds)
	        {
	        	TdPhoto tdPhoto = tdPhotoService.findOne(id);
	        	tdPhoto.setStatusId(2L); 
	        	tdPhoto.setTime(new Date());
	        	tdPhotoService.save(tdPhoto);
	        }
		}

        
        //确认页面中取消上传的要删除
        List<TdPhoto> toDelete = tdPhotoService.findByStatusIdAndUserId(1L, user.getId());
        tdPhotoService.delete(toDelete);
  
	    res.put("code", 0);
	    return res;
	}
	
	public boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^(0|86|17951|[0-9]{3})?([0-9]{8})|((13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8})$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
		}

	@RequestMapping(value = "/user/password", method = RequestMethod.GET)
	public String userPassword(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			return "/client/login";
		}
		TdUser user = tdUserService.findByUsername(username);

		map.addAttribute("user", user);
		return "/client/password_reset";

	}

	@RequestMapping(value = "/user/password", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userPassword(HttpServletRequest req, String oldPassword, String newPassword,
			ModelMap map) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("code", 1);

		String username = (String) req.getSession().getAttribute("username");

		if (null == username) {
			res.put("msg", "请先登录！");
			return res;
		}

		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

		if (user.getPassword().equals(oldPassword)) {
			user.setPassword(newPassword);
		}

		map.addAttribute("user", tdUserService.save(user));

		res.put("code", 0);
		return res;
	}

	@RequestMapping("/user/password/save")
	public String savePassword(HttpServletRequest req, String newPassword) {
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsername(username);
		user.setPassword(newPassword);
		tdUserService.save(user);
		
		return "/client/login";
	}
	
	//改变头像
	@RequestMapping(value = "/user/head")
	public String userHead(ModelMap map,  HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			return "redirect:/login";
		}
		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
		map.addAttribute("user", user);
		
		return "client/user_head";
	}
	
	//提交表单页面【我要】
	@RequestMapping(value = "/apply")
	public String apply(ModelMap map,  HttpServletRequest req) {
		tdCommonService.setHeader(map, req); 
		String username = (String) req.getSession().getAttribute("username");
//		if (null == username) {
//			return "redirect:/login";
//		}
		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
		if (null != user)
		{
			map.addAttribute("user", user);
		}
		
		
		//业务类型
		List<TdApplyType> applyTypeList = tdApplyTypeService.findByIsEnableTrueOrderBySortIdAsc();
		map.addAttribute("applyType_list", applyTypeList);
		
		return "client/apply_index";
	}
	
	//填写信息
	@RequestMapping(value = "/apply/edit/{applyTypeId}")
	public String applyMake(@PathVariable Long applyTypeId, ModelMap map,  HttpServletRequest req) {
		tdCommonService.setHeader(map, req); 
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			return "redirect:/login";
		}
		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
		map.addAttribute("user", user);
		
		if (null != applyTypeId)
		{
			TdApplyType applyType = tdApplyTypeService.findOne(applyTypeId);
			map.addAttribute("typeTitle", applyType.getTitle());
			map.addAttribute("applyTypeId", applyTypeId);
			if(null != applyType.getSpAcc()&&applyType.getSpAcc()==1)
			{
				map.addAttribute("spAcc", applyType.getSpAcc());
				map.addAttribute("enterType_list", tdEnterTypeService.findByIsEnableTrueOrderBySortIdAsc());
			}
			
			//【防止重复提交】检查当前类别是否有未审核的业务
			List<TdApply> tdApplyList = tdApplyService.findByUserIdAndStatusIdAndApplyTypeId(user.getId(), 0L, applyTypeId);
			if(null != tdApplyList && tdApplyList.size() > 0)
			{
				map.addAttribute("aru", 1);
			}
		}
		
		List<TdArea> areaList = tdAreaService.findByIsEnableTrueOrderBySortIdAsc();
		map.addAttribute("area_list", areaList);
		

		return "client/apply_edit";
	}
	
	//提交表单
	@RequestMapping(value = "/apply/submit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> applySubmit(HttpServletRequest req, 
												String realName, 
												String mobile,
												Long areaId,
												String address,
												String remark,
												Long applyTypeId,
												Long enterTypeId) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("code", 1);

		String username = (String) req.getSession().getAttribute("username");

		if (null == username) {
			res.put("msg", "请先登录！");
			return res;
		}

		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

		if(null == realName || realName.equals(""))
		{
			res.put("msg", "请输入联系人姓名！");
			return res;
		}
		if(null == mobile || mobile.equals(""))
		{
			res.put("msg", "请输入联系人电话！");
			return res;
		}
		if(null == areaId || areaId.equals(""))
		{
			res.put("msg", "请输入公司所处区域！");
			return res;
		}
		
		TdApply tdApply = new TdApply();
		tdApply.setRealName(realName);
		tdApply.setMobile(mobile);
		tdApply.setAreaId(areaId);
		tdApply.setStatusId(0L);
		tdApply.setSortId(99L);
		tdApply.setTime(new Date());
		tdApply.setUserId(user.getId());
		if(null != remark)
		{
			tdApply.setRemark(remark);
		}
		if(null != address)
		{
			tdApply.setAddress(address);
		}
		if(null != enterTypeId)
		{
			tdApply.setEnterTypeId(enterTypeId);
		}
		if(null != applyTypeId)
		{
			tdApply.setApplyTypeId(applyTypeId);
		}
		tdApplyService.save(tdApply);
		
		res.put("code", 0);
		return res;
	}


	/**
	 * 图片地址字符串整理，多张图片用,隔开
	 * 
	 * @param params
	 * @return
	 */
	private String parsePicUris(String[] uris) {
		if (null == uris || 0 == uris.length) {
			return null;
		}

		String res = "";

		for (String item : uris) {
			String uri = item.substring(item.indexOf("|") + 1, item.indexOf("|", 2));

			if (null != uri) {
				res += uri;
				res += ",";
			}
		}

		return res;
	}
}
