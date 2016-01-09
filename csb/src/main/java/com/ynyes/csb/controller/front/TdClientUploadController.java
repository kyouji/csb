package com.ynyes.csb.controller.front;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ynyes.csb.util.SiteMagConstant;
import com.ynyes.csb.entity.TdBill;
import com.ynyes.csb.entity.TdPhoto;
import com.ynyes.csb.entity.TdUser;
import com.ynyes.csb.service.TdBillService;
import com.ynyes.csb.service.TdPhotoService;
import com.ynyes.csb.service.TdUserService;

@Controller
@RequestMapping(value="/client")
public class TdClientUploadController {

	String ImageRoot = SiteMagConstant.imagePath;
	
	@Autowired
	TdUserService tdUserService;
	
	@Autowired
	TdBillService tdBillService;
	
	@Autowired
	TdPhotoService tdPhotoService;
	
	//上传票据，返回待确认列表页
	@RequestMapping(value = "/bill/upload", method = RequestMethod.POST)
    public String billUpload(String action,Long id,
            @RequestParam MultipartFile Filedata, ModelMap map, HttpServletRequest req) {
		
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username) {
            return "redirect:/login";
        }
        
        String name = Filedata.getOriginalFilename();
//        String contentType = Filedata.getContentType();

        String ext = name.substring(name.lastIndexOf("."));
        //限制文件类型
//        if(!ext.equalsIgnoreCase(".jpg") && !ext.equalsIgnoreCase(".pdf") && !ext.equalsIgnoreCase(".png") && !ext.equalsIgnoreCase(".rar"))
//        {
//            Long done = 2L;
//            return "redirect:/region/recommendEnterprise?id="+activityId
//            		+"&isDone="+done;
//        }

        TdBill tdBill = new TdBill();
        
        try {
            byte[] bytes = Filedata.getBytes();

            Date dt = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String fileName =sdf.format(dt) + ext;

            String uri = ImageRoot + "/" + fileName;

            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
            TdUser user = tdUserService.findOne(id);
            if(null != user)
            {
                tdBill.setImgUrl(fileName);
                tdBill.setStatusId(0L);
                tdBill.setUserId(id);
                tdBillService.save(tdBill);
            }

      

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return "redirect:/bill/upload/add?billId="+tdBill.getId();

    }
	
	//上传照片，返回待确认列表页
	@RequestMapping(value = "/photo/upload", method = RequestMethod.POST)
    public String photoUpload(String action,Long id,
            @RequestParam MultipartFile Filedata, ModelMap map, HttpServletRequest req) {
		
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username) {
            return "redirect:/login";
        }
        
        String name = Filedata.getOriginalFilename();
//        String contentType = Filedata.getContentType();

        String ext = name.substring(name.lastIndexOf("."));
        //限制文件类型
//        if(!ext.equalsIgnoreCase(".jpg") && !ext.equalsIgnoreCase(".pdf") && !ext.equalsIgnoreCase(".png") && !ext.equalsIgnoreCase(".rar"))
//        {
//            Long done = 2L;
//            return "redirect:/region/recommendEnterprise?id="+activityId
//            		+"&isDone="+done;
//        }

        TdPhoto tdPhoto = new TdPhoto();
        
        try {
            byte[] bytes = Filedata.getBytes();

            Date dt = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String fileName =sdf.format(dt) + ext;

            String uri = ImageRoot + "/" + fileName;

            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
            TdUser user = tdUserService.findOne(id);
            if(null != user)
            {
            	tdPhoto.setImgUrl(fileName);
            	tdPhoto.setStatusId(0L);
            	tdPhoto.setUserId(id);
            	if(null != user.getRoleId() &&user.getRoleId() == 0L)
            	{
            		tdPhoto.setPhtoType(0L);
            	}
            	tdPhotoService.save(tdPhoto);
            }

      

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return "redirect:/bill/upload/add?photoId="+tdPhoto.getId();

    }
	
	//上传用户头像
	@RequestMapping(value = "/userHead/upload", method = RequestMethod.POST)
    public String userHeadUpload(String action,Long id,
            @RequestParam MultipartFile Filedata, ModelMap map, HttpServletRequest req) {
		
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username) {
            return "redirect:/login";
        }
        
        String name = Filedata.getOriginalFilename();
//        String contentType = Filedata.getContentType();

        String ext = name.substring(name.lastIndexOf("."));
        //限制文件类型
//        if(!ext.equalsIgnoreCase(".jpg") && !ext.equalsIgnoreCase(".pdf") && !ext.equalsIgnoreCase(".png") && !ext.equalsIgnoreCase(".rar"))
//        {
//            Long done = 2L;
//            return "redirect:/region/recommendEnterprise?id="+activityId
//            		+"&isDone="+done;
//        }

        try {
            byte[] bytes = Filedata.getBytes();

            Date dt = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String fileName =sdf.format(dt) + ext;

            String uri = ImageRoot + "/" + fileName;

            File file = new File(uri);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
            TdUser user = tdUserService.findOne(id);
            if(null != user)
            {
            	user.setHeadImageUrl("/images/"+fileName);
            	tdUserService.save(user);
            }

      

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return "redirect:/user/head";

    }
	
}
