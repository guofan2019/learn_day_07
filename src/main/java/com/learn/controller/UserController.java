package com.learn.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/fileupload1")
    public String fileuoload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上...");
        //使用upload组件实现上传
        //上传位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        File file=new File(path);
        if(!file.exists())
        {
            file.mkdir();
        }
        //获取磁盘文件项
        DiskFileItemFactory factory=new DiskFileItemFactory();
        //获取上传组件
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析request
        List<FileItem> list = upload.parseRequest(request);
        //遍历
        for(FileItem item:list)
        {
            //判断当前item是否是上传文件项
            if(item.isFormField())
            {
              //说明普通表单项
            }else{
                //说明上传文件项
                //获取上传文件的名称
                String filename = item.getName();
                item.write(new File(path,filename));
                //删除临时文件
                item.delete();
                System.out.println("上传完成！");
            }
        }
        return "success";
    }

}
