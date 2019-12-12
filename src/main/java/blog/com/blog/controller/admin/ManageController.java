package blog.com.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "admin")
public class ManageController {

    /**
     * add-article
     * @param modelMap
     * @return
     */
    @RequestMapping("/article/add.html")
    public String addArticle(ModelMap modelMap) {
        return "/admin/article_add";
    }

    /**
     * add-diary
     * @param modelMap
     * @return
     */
    @RequestMapping("/diary/add.html")
    public String addDiary(ModelMap modelMap) {
        return "/admin/diary_add";
    }

    /**
     * add-link
     * @param modelMap
     * @return
     */
    @RequestMapping("/link/add.html")
    public String addLink(ModelMap modelMap) {
        return "/admin/link_add";
    }

    /**
     * add-notice
     * @param modelMap
     * @return
     */
    @RequestMapping("/notice/add.html")
    public String addNotice(ModelMap modelMap) {
        return "/admin/notice_add";
    }

    /**
     * add-sentence
     * @param modelMap
     * @return
     */
    @RequestMapping("/sentence/add.html")
    public String addSentence(ModelMap modelMap) {
        return "/admin/sentence_add";
    }
}