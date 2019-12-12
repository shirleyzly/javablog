package blog.com.blog.service.impl;

import blog.com.blog.dao.ArticleDao;
import blog.com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ArticleImplService implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    /**
     * 编程基础
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List getArticleList(Map<String, String> map, Integer pageNum, Integer pageSize) {
        return articleDao.getArticleList(map, pageNum, pageSize);
    }

    /**
     * 查询记录数
     * @return
     */
    @Override
    public Integer getArticleTotalRecord(Map<String, String> map) {
        return articleDao.getArticleTotalRecord(map);
    }

    /**
     * 获取文章列表
     * @return
     */
    @Override
    public List getArticleTitleList(Integer pageNum, Integer pageSize) {
        return articleDao.getArticleTitleList(pageNum, pageSize);
    }

    /**
     * 热门文章
     * @return
     */
    @Override
    public List getRecommondArticleList(Map<String,String> map) {
        return articleDao.getRecommondArticleList(map);
    }

    /**
     * 每日推荐
     * @return
     */
    @Override
    public Map getDailyRecommendOne() {
        return articleDao.getDailyRecommendOne();
    }

    @Override
    public Map getArticleDetail(Integer id) {
        return articleDao.getArticleDetail(id);
    }

    /**
     * 删除/0删除失败
     * 先查询再删除
     * @param id
     * @return
     */
    @Override
    public Integer deleteSingleArticle(Integer id) {
        Map detail = getArticleDetail(id);
        if(detail.size()>0 && !detail.equals("")) {
            return articleDao.deleteSingleArticle(id);
        }
        return 0;
    }

    /**
     * 保存
     * @param map
     * @return
     */
    @Override
    public Integer saveArticleData(Map<String, String> map) {
        return articleDao.saveSingeArticle(map);
    }
}