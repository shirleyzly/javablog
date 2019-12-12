package blog.com.blog.controller.admin;

import blog.com.blog.service.*;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminApiController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private LinkServie linkServie;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private EnglishService englishService;

    @RequestMapping(value = "/admin/delete/record", method = RequestMethod.POST)
    public String deleteRecord(@RequestParam Integer id, @RequestParam String obj) {
        Integer res = 0;
        switch (obj) {
            case "article":
                res  = articleService.deleteSingleArticle(id);
                break;
            case "diary":
                res  = diaryService.deleteSingleDiary(id);
                break;
            case "link":
                res  = linkServie.deleteSingleLink(id);
                break;
            case "notice":
                res  = noticeService.deleteSingleNotice(id);
                break;
            case "sentence":
                res  = englishService.deleteSingleSentence(id);
                break;
        }
        Map retMap = new HashMap();
        retMap.put("error", res > 0 ? "删除成功" : "删除失败");
        retMap.put("status", res > 0 ? 0 : 1);
        return JSON.toJSONString(retMap);
    }

    /**
     * 保存
     * @param map
     * @return
     */
    @RequestMapping(value = "/admin/save/object", method = RequestMethod.POST)
    public String addObject(@RequestParam Map<String,String> map) {
        String object = map.getOrDefault("object", "");
        Integer res = 0;
        switch (object) {
            case "article":
                res  = articleService.saveArticleData(map);
                break;
            case "diary":
                res  = diaryService.saveDiaryData(map);
                break;
            case "link":
                res  = linkServie.saveLinkData(map);
                break;
            case "notice":
                res  = noticeService.saveNoticeData(map);
                break;
            case "sentence":
                res  = englishService.saveSentenceData(map);
                break;
        }
        return res.toString();
    }
}