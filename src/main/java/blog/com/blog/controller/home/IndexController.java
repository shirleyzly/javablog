package blog.com.blog.controller.home;

import blog.com.blog.common.constant.CommonConstant;
import blog.com.blog.common.function.FormatFunc;
import blog.com.blog.common.function.ServiceFunc;
import blog.com.blog.service.ArticleService;
import blog.com.blog.service.DiaryService;
import blog.com.blog.service.EnglishService;
import blog.com.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class IndexController {
    // 每页记录数
    private static Integer pageSize = 10;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private DiaryService diaryService;

    @Autowired
    private EnglishService englishService;

    @Autowired
    private NoticeService noticeService;

    /**
     * 博客首页
     * @param modelMap
     * @param page
     * @return
     */
    @RequestMapping(value = {"index.html","/",""})
    public String index(ModelMap modelMap, @RequestParam(defaultValue = "1") Integer page) {
        //页码数
        Integer total = articleService.getArticleTotalRecord(new HashMap<>());
        Integer pageCounts = total/pageSize;
        if(total%pageSize > 0) {
            pageCounts = pageCounts+1;
        }
        //列表
        List lists = articleService.getArticleList(new HashMap<>(),(page-1)*pageSize,pageSize);
        //渲染
        String pageName = "/index";
        modelMap.addAttribute("nav_name", 1);
        modelMap.putAll(ServiceFunc.formatMapAttribute(page, pageSize, pageCounts, lists, pageName));
        modelMap.addAttribute("typeCodeMap", CommonConstant.CODE_TYPE);
        modelMap.addAttribute("recommond", articleService.getRecommondArticleList(null));
        modelMap.addAttribute("englishSentences", englishService.getEnglishSentences());
        modelMap.addAttribute("YmdAndWeek",FormatFunc.getDateOfYmdAndWeek());
        modelMap.addAttribute("notice", noticeService.getNoticeList(0,5));
        modelMap.addAttribute("dailyRecommond", articleService.getDailyRecommendOne());
        modelMap.addAttribute("keyword","");
        modelMap.addAttribute("gradeCodeMap", CommonConstant.CODE_GRADE);
        return "/home/index";
    }

    /**
     * 编程技术
     * @param modelMap
     * @return
     */
    @GetMapping("/basic.html")
    public String basic(ModelMap modelMap, @RequestParam(defaultValue = "1") Integer page) {
        Map<String, String> map =  new HashMap<>();
        map.put("grade", "0");
        //页码数
        Integer total = articleService.getArticleTotalRecord(map);
        Integer pageCounts = total/pageSize;
        if(total%pageSize > 0) {
            pageCounts = pageCounts+1;
        }
        //列表
        List lists = articleService.getArticleList(map,(page-1)*pageSize,pageSize);
        //渲染
        String pageName = "/basic";
        modelMap.addAttribute("nav_name", 2);
        modelMap.putAll(ServiceFunc.formatMapAttribute(page, pageSize, pageCounts, lists, pageName));
        modelMap.addAttribute("typeCodeMap", CommonConstant.CODE_TYPE);
        modelMap.addAttribute("recommond", articleService.getRecommondArticleList(null));
        modelMap.addAttribute("englishSentences", englishService.getEnglishSentences());
        modelMap.addAttribute("YmdAndWeek",FormatFunc.getDateOfYmdAndWeek());
        return "/home/basic";
    }

    /**
     * 英语口语
     * @param modelMap
     * @return
     */
    @GetMapping("/learn.html")
    public String learn(ModelMap modelMap, @RequestParam(defaultValue = "1") Integer page) {
        Map<String, String> map =  new HashMap<>();
        map.put("grade", "1");
        //页码数
        Integer total = articleService.getArticleTotalRecord(map);
        Integer pageCounts = total/pageSize;
        if(total%pageSize > 0) {
            pageCounts = pageCounts+1;
        }
        //列表
        List lists = articleService.getArticleList(map,(page-1)*pageSize,pageSize);
        //渲染
        String pageName = "/learn";
        modelMap.addAttribute("nav_name", 3);
        modelMap.putAll(ServiceFunc.formatMapAttribute(page, pageSize, pageCounts, lists, pageName));
        modelMap.addAttribute("typeCodeMap", CommonConstant.CODE_TYPE);
        modelMap.addAttribute("recommond", articleService.getRecommondArticleList(null));
        modelMap.addAttribute("englishSentences", englishService.getEnglishSentences());
        modelMap.addAttribute("YmdAndWeek",FormatFunc.getDateOfYmdAndWeek());
        return "home/learn";
    }

    /**
     * 个人简历
     * @param model
     * @return
     */
    @GetMapping("/resume.html")
    public String resume(ModelMap model) {
        model.addAttribute("nav_name", 4);
        return "home/resume";
    }

    /**
     * 个人写作
     * @param modelMap
     * @return
     */
    @GetMapping("/write.html")
    public String write(ModelMap modelMap, @RequestParam(defaultValue = "1") Integer page) {
        //页码数
        Integer total = diaryService.getDiaryTotalRecord(null);
        Integer pageCounts = total/pageSize;
        if(total%pageSize > 0) {
            pageCounts = pageCounts+1;
        }
        //列表
        List lists = diaryService.getDiaryList((page-1)*pageSize,pageSize);
        //渲染
        String pageName = "/write";
        modelMap.addAttribute("nav_name", 5);
        modelMap.putAll(ServiceFunc.formatMapAttribute(page, pageSize, pageCounts, lists, pageName));
        modelMap.addAttribute("typeCodeMap", CommonConstant.WRITE_TYPE);
        modelMap.addAttribute("recommond", articleService.getRecommondArticleList(null));
        modelMap.addAttribute("englishSentences", englishService.getEnglishSentences());
        modelMap.addAttribute("YmdAndWeek",FormatFunc.getDateOfYmdAndWeek());
        return "home/write";
    }

    /**
     * 内容详情
     * @param modelMap
     * @return
     */
    @GetMapping("/article.html")
    public String article(ModelMap modelMap,@RequestParam Integer id) {
        Map<String, String> detailMap = articleService.getArticleDetail(id);
        modelMap.addAttribute("article",detailMap);
        modelMap.addAttribute("typeCodeMap", CommonConstant.CODE_TYPE);
        modelMap.addAttribute("gradeCodeMap", CommonConstant.CODE_GRADE);
        modelMap.addAttribute("aboutRecommond", articleService.getRecommondArticleList(detailMap));
        modelMap.addAttribute("recommond", articleService.getRecommondArticleList(null));
        modelMap.addAttribute("englishSentences", englishService.getEnglishSentences());
        modelMap.addAttribute("YmdAndWeek",FormatFunc.getDateOfYmdAndWeek());
        modelMap.addAttribute("notice", noticeService.getNoticeList(0,5));
        return "home/article";
    }

    /**
     * 个人写作-文章详情
     * @param modelMap
     * @return
     */
    @GetMapping("/writing.html")
    public String writting(ModelMap modelMap, @RequestParam Integer id) {
        Map<String, String> detailMap = diaryService.getDiaryDetail(id);
        modelMap.addAttribute("diary",detailMap);
        modelMap.addAttribute("typeCodeMap", CommonConstant.WRITE_TYPE);
        modelMap.addAttribute("recommond", articleService.getRecommondArticleList(null));
        modelMap.addAttribute("englishSentences", englishService.getEnglishSentences());
        modelMap.addAttribute("YmdAndWeek",FormatFunc.getDateOfYmdAndWeek());
        modelMap.addAttribute("notice", noticeService.getNoticeList(0,5));
        return "home/writing";
    }

    /**
     * search
     * @param modelMap
     * @param keyword
     * @param page
     * @return
     */
    @RequestMapping("/search.html")
    public String search(ModelMap modelMap,
                         @RequestParam(defaultValue = "") String type,
                         @RequestParam(defaultValue = "") String keyword,
                         @RequestParam(defaultValue = "1") Integer page)
    {
        Map<String, String> map =  new HashMap<>();
        map.put("keyword", keyword);
        map.put("type",type);
        //页码数
        Integer total = articleService.getArticleTotalRecord(map);
        Integer pageCounts = total/pageSize;
        if(total%pageSize > 0) {
            pageCounts = pageCounts+1;
        }
        //列表
        List lists = articleService.getArticleList(map,(page-1)*pageSize,pageSize);
        //渲染
        String pageName = "/search";
        modelMap.putAll(ServiceFunc.formatMapAttribute(page, pageSize, pageCounts, lists, pageName));
        modelMap.addAttribute("typeCodeMap", CommonConstant.CODE_TYPE);
        modelMap.addAttribute("recommond", articleService.getRecommondArticleList(null));
        modelMap.addAttribute("englishSentences", englishService.getEnglishSentences());
        modelMap.addAttribute("YmdAndWeek",FormatFunc.getDateOfYmdAndWeek());
        modelMap.addAttribute("keyword",keyword);
        return "home/search";
    }
}