package ASTWeb.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;



/**
 * Created by xiangpeng on 2017/12/18.
 */
@CrossOrigin(origins="http://127.0.0.1:8080",maxAge=3600)
@Controller
@RequestMapping("/AST")


public class UploadPic {
    /**
     * 图片文件上传
     */
    class picData {
        public List<String> data;
        public int errno;
    };

    @ResponseBody
    @RequestMapping(value = "/uploadPic",method = RequestMethod.POST)
    public void uploadPic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8080");  // 第二个参数填写允许跨域的域名称，不建议直接写 "*"
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        // 接收跨域的cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String ,MultipartFile> fileList = multipartRequest.getFileMap();
//pic infos
        picData data = new picData();
        data.data = new ArrayList<String>();
        data.errno = 0;
        for (Map.Entry<String ,MultipartFile> entry : fileList.entrySet()) {
            try{
                System.out.println("Value = " + entry.getValue().getName());
                String fileName = entry.getValue().getOriginalFilename();
               data.data.add("http://127.0.0.1:80/img/"+fileName);
               FileOutputStream fos = FileUtils.openOutputStream(new File("d:/img/" +fileName));//打开FileOutStrean流
               IOUtils.copy(entry.getValue().getInputStream(),fos);//将MultipartFile file转成二进制流并输入到FileOutStrean
               fos.close();
           }catch (Exception error)
           {
                System.out.print(error);
           }
        }
        response.setContentType("text/text;charset=utf-8");
        PrintWriter out = response.getWriter();

        try{
            String json = JSON.toJSONString(data);
            System.out.println(json);
            out.print(json);  //返回url地址
        }catch (Exception e)
        {
            System.out.println(e);
        }

        out.flush();
        out.close();

    }
}


