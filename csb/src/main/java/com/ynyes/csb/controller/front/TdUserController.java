package com.ynyes.csb.controller.front;

import java.util.ArrayList;
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

import com.ynyes.csb.entity.TdDemand;
import com.ynyes.csb.entity.TdUser;
import com.ynyes.csb.service.TdCommonService;
import com.ynyes.csb.service.TdDemandService;
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

	@RequestMapping(value = "/user")
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
		// map.addAttribute("order_page",
		// tdOrderService.findByUsername(username,
		// 0, ClientConstant.pageSize));
		// map.addAttribute("collect_page", tdUserCollectService.findByUsername(
		// username, 0, ClientConstant.pageSize));
		// map.addAttribute("recent_page", tdUserRecentVisitService
		// .findByUsernameOrderByVisitTimeDesc(username, 0,
		// ClientConstant.pageSize));
		// map.addAttribute("total_unpayed",
		// tdOrderService.countByUsernameAndStatusId(username, 2));
		// map.addAttribute("total_undelivered",
		// tdOrderService.countByUsernameAndStatusId(username, 3));
		// map.addAttribute("total_unreceived",
		// tdOrderService.countByUsernameAndStatusId(username, 4));
		// map.addAttribute("total_finished",
		// tdOrderService.countByUsernameAndStatusId(username, 6));
		if (4L == tdUser.getRoleId()) {
			return "redirect:/activity/list";
		} else if (3L == tdUser.getRoleId()) {
			return "redirect:/expert/enterprise/list";
		} else if (2L == tdUser.getRoleId()) {
			return "redirect:/region/enterprise/list";
		} else if (1L == tdUser.getRoleId()) {
			if (null != tdUser.getStatusId()) {
				return "redirect:/enterprise/check";
			} else {
				return "redirect:/enterprise/info";
			}
		}

		return "/client/user_index";
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
	public String userInfo(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");

		if (null == username) {
			return "redirect:/login";
		}

		tdCommonService.setHeader(map, req);

		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

		map.addAttribute("user", user);

		return "/client/user_info";
	}

	@RequestMapping(value = "/user/info", method = RequestMethod.POST)
	public String userInfo(HttpServletRequest req, String realName, String sex, String email, String mobile,
			ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");

		if (null == username) {
			return "redirect:/login";
		}

		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

		if (null != email && null != mobile) {
			user.setRealName(realName);
			user.setEmail(email);
			user.setMobile(mobile);
			user = tdUserService.save(user);
		}

		return "redirect:/user/info";
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

	/**
	 * @author mdj
	 * @param rep
	 * @param imgUrl
	 *            头像图片地址
	 * @return
	 */
	@RequestMapping(value = "/user/headImageUrl", method = RequestMethod.POST)
	@ResponseBody
	public String saveHeadPortrait(String imgUrl, HttpServletRequest rep) {
		String username = (String) rep.getSession().getAttribute("username");
		if (null == username) {
			return "redirect:/login";
		}
		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
		user.setHeadImageUri(imgUrl);
		tdUserService.save(user);

		return "client/user_index";
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
