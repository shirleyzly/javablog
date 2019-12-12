package blog.com.blog.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 边角模块
 */
@Controller
@RequestMapping(value = "/")
public class OtherController {

    /**
     * 标签云
     * @param model
     * @return
     */
    @GetMapping("/tags.html")
    public String tags(ModelMap model) {
        return "home/tags";
    }

    /**
     * 读者墙
     * @param model
     * @return
     */
    @GetMapping("/readers.html")
    public String readers(ModelMap model) {
        return "home/readers";
    }

    /**
     * 友情链接
     * @param model
     * @return
     */
    @GetMapping("/links.html")
    public String links(ModelMap model) {
        return "home/links";
    }
}
