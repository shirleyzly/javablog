package blog.com.blog.controller.admin;

import blog.com.blog.common.tools.ueditor.ActionEnter;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UeditorController {

    @RequestMapping(value="/admin/ueditor/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/admin/ueditor/uploadimage")
    public String uploadImg(MultipartFile fileName) {
        String url = "";
        try {
            // 接收上传的文件
            // 取扩展名
            String originalFilename = fileName.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename
                    .lastIndexOf(".") + 1);
            url = originalFilename+extName;
            // 响应上传图片的url
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> result = new HashMap<>();

        result.put("name", fileName.getOriginalFilename());// 新的文件名
        result.put("originalName", fileName.getOriginalFilename());// 原始文件名
        result.put("size", fileName.getSize());
        result.put("state", "SUCCESS");
        result.put("url", url);// 展示图片的请求url
        String jStr = JSON.toJSONString(result);
        return jStr;
    }
}