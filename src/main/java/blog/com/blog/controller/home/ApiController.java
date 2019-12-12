package blog.com.blog.controller.home;

import blog.com.blog.service.ArticleService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private ArticleService articleService;

    /**
     * 获取标题列表-demo
     * @return
     */
    @RequestMapping("/articles/get_list")
    public JSONObject getTitle() {
        List lists = articleService.getArticleTitleList(1,10);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("data", lists);
        return jsonObj;
    }
}
