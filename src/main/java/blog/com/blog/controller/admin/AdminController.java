package blog.com.blog.controller.admin;

import blog.com.blog.common.constant.CommonConstant;
import blog.com.blog.common.function.ServiceFunc;
import blog.com.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/")
public class AdminController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private EnglishService englishService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private LinkServie linkServie;

    @Autowired
    private DiaryService diaryService;

    // 每页记录数
    private static Integer pageSize = 10;

    /**
     * 登录页面
     * @param modelMap
     * @return
     */
    @RequestMapping("/login.html")
    public String login(ModelMap modelMap) {
        return "/admin/admin_login";
    }

    /**
     * 后台首页
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"index.html","/", ""})
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("host", "www.lulijuan.com");
        return "/admin/admin_index";
    }

    /**
     * 英语口语列表
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/english/index.html")
    public String articleEnglishList(ModelMap modelMap, @RequestParam(defaultValue = "1") Integer page) {
        if(page<1) page = 1;

        //页码数
        Integer total = articleService.getArticleTotalRecord(new HashMap<>());
        Integer pageCounts = total/pageSize;
        if(total%pageSize > 0) {
            pageCounts = pageCounts+1;
        }
        //列表
        List articles = articleService.getArticleTitleList((page-1)*pageSize,pageSize);
        //渲染
        modelMap.putAll(ServiceFunc.formatMapAttribute(page, pageSize, pageCounts, articles, "/admin/english/index"));
        //将文章类型名称渲染进页面中
        modelMap.addAttribute("typeCodeMap", CommonConstant.CODE_TYPE);
        modelMap.addAttribute("codeGradeMap", CommonConstant.CODE_GRADE);
        return "/admin/english_index";
    }

    /**
     * 文章列表
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/article/index.html")
    public String articleList(ModelMap modelMap, @RequestParam(defaultValue = "1") Integer page) {
        if(page<1) page = 1;

        //页码数
        Integer total = articleService.getArticleTotalRecord(new HashMap<>());
        Integer pageCounts = total/pageSize;
        if(total%pageSize > 0) {
            pageCounts = pageCounts+1;
        }
        //列表
        List articles = articleService.getArticleTitleList((page-1)*pageSize,pageSize);
        //渲染
        modelMap.putAll(ServiceFunc.formatMapAttribute(page, pageSize, pageCounts, articles, "/admin/article/index"));
        //将文章类型名称渲染进页面中
        modelMap.addAttribute("typeCodeMap", CommonConstant.CODE_TYPE);
        modelMap.addAttribute("codeGradeMap", CommonConstant.CODE_GRADE);
        return "/admin/article_index";
    }

    /**
     * 写作
     * @param modelMap
     * @param page
     * @return
     */
    @RequestMapping(value = "/diary/index.html")
    public String diaryList(ModelMap modelMap, @RequestParam(defaultValue = "1") Integer page) {
        if(page<1) page = 1;

        //页码数
        Integer total = diaryService.getDiaryTotalRecord(new HashMap<>());
        Integer pageCounts = total/pageSize;
        if(total%pageSize > 0) {
            pageCounts = pageCounts+1;
        }
        //列表
        List articles = diaryService.getDiaryList((page-1)*pageSize,pageSize);
        //渲染
        modelMap.putAll(ServiceFunc.formatMapAttribute(page, pageSize, pageCounts, articles, "/admin/diary/index"));
        //将文章类型名称渲染进页面中
        modelMap.addAttribute("typeCodeMap", CommonConstant.WRITE_TYPE);
        return "/admin/diary_index";
    }

    /**
     * 英语句子
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/sentence/index.html"})
    public String englishSentenseList(ModelMap modelMap,@RequestParam(defaultValue = "1") Integer page) {
        Integer pageSize = 8;
        //页码数
        Integer total = englishService.getSentenceTotalRecord();
        Integer pageCounts = total/pageSize;
        if(total%pageSize > 0) {
            pageCounts = pageCounts+1;
        }
        List lists = englishService.getSentenceList((page-1)*pageSize, pageSize);
        //渲染
        modelMap.putAll(ServiceFunc.formatMapAttribute(page, pageSize, pageCounts, lists, "/admin/sentence/index"));
        return  "/admin/sentence_index";
    }

    /**
     * 公告列表
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/notice/index.html"})
    public String noticeList(ModelMap modelMap,@RequestParam(defaultValue = "1") Integer page) {
        //页码数
        Integer total = noticeService.getNoticeTotalRecord();
        Integer pageCounts = total/pageSize;
        if(total%pageSize > 0) {
            pageCounts = pageCounts+1;
        }
        List lists = noticeService.getNoticeList((page-1)*pageSize,pageSize);
        //渲染
        modelMap.putAll(ServiceFunc.formatMapAttribute(page, pageSize, pageCounts, lists,"/admin/notice/index"));
        return "/admin/notice_index";
    }

    /**
     * 友情链接/收藏链接等等
     * @param modelMap
     * @param page
     * @return
     */
    @RequestMapping(value = {"/link/index.html"})
    public String linkList(ModelMap modelMap,@RequestParam(defaultValue = "1") Integer page) {
        //页码数
        Integer total = linkServie.getLinkTotalRecord();
        Integer pageCounts = total/pageSize;
        if(total%pageSize > 0) {
            pageCounts = pageCounts+1;
        }
        List lists = linkServie.getLinkList((page-1)*pageSize,pageSize);
        //渲染
        modelMap.putAll(ServiceFunc.formatMapAttribute(page, pageSize, pageCounts, lists, "/admin/link/index"));
        //链接类型名称
        modelMap.addAttribute("typeCodeMap", CommonConstant.LINK_TYPE);
        return "/admin/link_index";
    }

    /**
     * 设置
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/setting/index.html"})
    public String setting(ModelMap modelMap) {
        modelMap.addAttribute("host", "www.lulijuan.com");
        return "/admin/setting_index";
    }
}