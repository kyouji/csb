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
	
	@RequestMapping(value = "/reg/check/{type}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> validateForm(@PathVariable String type, String param,HttpServletRequest req) {
		Map<String, String> res = new HashMap<String, String>();

		res.put("status", "n");

		if (null == type) {
			res.put("info", "参数错误");
			return res;
		}

		if (type.equalsIgnoreCase("username")) {
			if (null == param || param.isEmpty()) {
				res.put("info", "用户名不能为空");
				return res;
			}

			TdUser user = tdUserService.findByUsername(param);

			if (null != user) {
				res.put("info", "该用户名已被注册");
				return res;
			}
		}
		
		/**
		 * ajax实时验证验证码，判断用户验证码是否输入正确
		 * @author dengxiao
		 */
		if(type.equalsIgnoreCase("smsCode")){
			String smsCode = (String) req.getSession().getAttribute("SMSCODE");
			if(null == smsCode){
				res.put("info", "请点击\"发送验证码\"");
				return res;
			}
			if (null == param || param.isEmpty()) {
				res.put("info", "用户名不能为空");
				return res;
			}
			if(!smsCode.equalsIgnoreCase(param)){
				res.put("info", "验证码输入错误！");
				return res;
			}
		}

		
		/**
		 * ajax实时验证 手机号查找用户 判断手机号是已否注册
		 * 
		 * @author libiao
		 */
		if (type.equalsIgnoreCase("mobile")) {
			if (null == param || param.isEmpty()) {
				res.put("info", "用户名不能为空");
				return res;
			}

			TdUser user = tdUserService.findByMobile(param);

			if (null != user) {
				res.put("info", "该手机已经注册");
				return res;
			}
		}

		res.put("status", "y");

		return res;
	}

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
	public String reg(String username, 
									String mobile, 
									String password, 
									String password2, 
									String enterType, 
									Long enterTypeId,
									Long roleId,
									Long changeRole,
									HttpServletRequest request) {
		if(null != changeRole)
		{
			return "redirect:/reg?type="+changeRole;
		}
		
		if (null == username ||username.equals("")
				|| null == mobile || mobile.equals("")
				|| null == password || password.equals("")
				)
		{
			Long error = 1L;
			return "redirect:/reg?error="+error;
		}
		
		TdUser user = new TdUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setMobile(mobile);
		user.setStatusId(1L);
		user.setRegisterTime(new Date());
		user.setRoleId(1L);
		tdUserService.save(user);
		
		
		return "redirect:/enterprise/info";

	}

	@RequestMapping(value = "/code", method = RequestMethod.GET)
	public void verify(HttpServletResponse response, HttpServletRequest request) {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		VerifServlet randomValidateCode = new VerifServlet();
		randomValidateCode.getRandcode(request, response);
		// QRCodeUtils qr = new QRCodeUtils();
		// qr.getQRCode("weixin://wxpay/bizpayurl?appid=wx2421b1c4370ec43b&mch_id=10000100&nonce_str=f6808210402125e30663234f94c87a8c&product_id=1&time_stamp=1415949957&sign=512F68131DD251DA4A45DA79CC7EFE9D",
		// 125, response);
	}
	public boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
		}

}