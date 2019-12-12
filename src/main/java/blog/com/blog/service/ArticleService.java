package blog.com.blog.service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Resource
public interface ArticleService {
    List getArticleList(Map<String,String> map,Integer pageNum, Integer pageSize);
    Integer getArticleTotalRecord(Map<String, String> map);
    List getArticleTitleList(Integer pageNum, Integer pageSize);
    List getRecommondArticleList(Map<String,String> map);
    Map getDailyRecommendOne();
    Map getArticleDetail(Integer id);
    Integer deleteSingleArticle(Integer id);
    Integer saveArticleData(Map<String, String> map);
}
