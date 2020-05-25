package com.yulong.manager.userController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author 赵玉龙
 */
@RequestMapping(value = "/upload")
@RestController
public class UploadController {

    private final String IMAGE_PATH = "E:/code/GraduationProject/community_backstrage/manager/src/main/webapp/images/";

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public String uploadImage(HttpServletRequest request) {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //保证文件在内存中不被删除，否则默认大小为10KB，超过后就被删除，无法进行第二次transferTo
        resolver.setMaxInMemorySize(52882190);
        resolver.setMaxUploadSize(52882190);
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        MultipartFile file = multipartRequest.getFile("fileImage");
        String imageName = file.getOriginalFilename();
        String contextPath = request.getSession().getServletContext().getRealPath("/");
        String realPath = IMAGE_PATH + imageName;
        System.out.println(contextPath + "\\images\\" + imageName);
        try {
            file.transferTo(new File(realPath));
            file.transferTo(new File(contextPath + "images\\" + imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String httpImgPath = "http://localhost:8689/images/" + imageName;
        System.out.println(httpImgPath);
        JSONObject result = new JSONObject();
        if (request.getSession().getAttribute("card_id") == null) {
            result.put("code", 98);
            result.put("message", "未登陆，请登录！");
            return result.toString();
        }
        result.put("code", 100);
        result.put("message", "success");
        result.put("path", httpImgPath);
        return result.toString();
    }

}
