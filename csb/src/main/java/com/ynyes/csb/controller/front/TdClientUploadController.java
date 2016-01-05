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
import com.ynyes.csb.entity.TdUser;
import com.ynyes.csb.service.TdBillService;
import com.ynyes.csb.service.TdUserService;

@Controller
@RequestMapping(value="/client")
public class TdClientUploadController {

	String ImageRoot = SiteMagConstant.imagePath;
	
	@Autowired
	TdUserService TdUserService;
	
	@Autowired
	TdBillService tdBillService;
	

	@RequestMapping(value = "/bill/upload", method = RequestMethod.POST)
    public String upload(String action,Long id,
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
            TdUser user = TdUserService.findOne(id);
            if(null != user)
            {
                tdBill.setImgUrl(fileName);
                tdBill.setSortId(99L);
                tdBill.setStatusId(0L);
                tdBill.setUserId(id);
                tdBill.setUsername(user.getUsername());
                tdBillService.save(tdBill);
            }

      

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        Long done = 1L;
        return "redirect:/bill/upload/add?billId="+tdBill.getId();

    }
	
}
