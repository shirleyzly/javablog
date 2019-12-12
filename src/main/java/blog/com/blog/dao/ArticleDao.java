package blog.com.blog.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleDao extends _BaseDao {

    /**
     * 查询记录数
     * @return
     */
    public Integer getArticleTotalRecord(Map<String, String> map) {
        String andWhere = "";
        //等级
        String grade = map.getOrDefault("grade", "");
        if(!grade.equals("")) {
            andWhere += " AND grade="+grade;
        }
        //关键词
        String keyword = map.getOrDefault("keyword", "");
        if(!keyword.equals("")) {
            andWhere += " AND title like '%"+keyword+"%'";
        }
        //类型
        String type = map.getOrDefault("type","");
        if(!type.equals("")) {
            if(!type.equals("99")) {
                andWhere += " AND type="+type;
            }else{
                andWhere += " AND type>3";
            }
        }

        String sql = "select count(id) from article where deleted=0 "+andWhere;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * 获取文章标题列表
     * @return
     */
    public List getArticleTitleList(Integer pageNum, Integer pageSize) {
        String sql = "SELECT id, title, pic,description, type, grade, deleted, click, create_time AS createTime, update_time as updateTime " +
                     "FROM article ORDER BY id DESC LIMIT "+pageNum+","+pageSize;
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 基础/搜索
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List getArticleList(Map<String, String> map, Integer pageNum, Integer pageSize) {
        String andWhere = "";
        //等级
        String grade = map.getOrDefault("grade", "");
        if(!grade.equals("")) {
            andWhere += " AND grade="+grade;
        }
        //关键词
        String keyword = map.getOrDefault("keyword", "");
        if(!keyword.equals("")) {
            andWhere += " AND title LIKE '%"+keyword+"%'";
        }

        //类型
        String type = map.getOrDefault("type","");
        if(!type.equals("")) {
            if(!type.equals("99")) {
                andWhere += " AND type="+type;
            }else{
                andWhere += " AND type>3";
            }
        }

        String sql = "SELECT id, title, pic, description, type, grade, deleted, click, create_time AS createTime, update_time as updateTime " +
                "FROM article WHERE deleted=0 "+andWhere+" LIMIT "+pageNum+","+pageSize;
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 热门文章
     * 相关推荐
     * @return
     */
    public List getRecommondArticleList(Map<String,String> map) {
        String where = "";
        if(map!=null) {
            Object idObj = map.get("id");
            Object typeObj = map.get("type");
            Integer id = Integer.valueOf(String.valueOf(idObj));
            Integer type = Integer.valueOf(String.valueOf(typeObj));
            if(id>0) {
                where = " AND id !="+id;
            }
            if(type>=0) {
                where+=" AND type="+type;
            }
        }

        String sql = "SELECT id, title, pic, type, deleted, click, create_time AS createTime FROM article " +
                " WHERE deleted=0 "+where+" ORDER BY click DESC LIMIT 5";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 今日推荐
     * @return
     */
    public Map getDailyRecommendOne() {
        String sql = "SELECT id, title, description FROM article WHERE deleted=0 ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.queryForMap(sql);
    }

    /**
     * 文章详情
     * @param id
     * @return
     */
    public Map getArticleDetail(Integer id) {
        String where = "";
        if(id>0) {
            where = " AND id="+id;
        }
        String sql = "SELECT id,title,description,content,type,grade,click,create_time AS createTime FROM article " +
                "WHERE deleted=0 "+where+" LIMIT 1";
        return jdbcTemplate.queryForMap(sql);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public Integer deleteSingleArticle(Integer id) {
        String sql = "DELETE FROM article WHERE id="+id;
        return jdbcTemplate.update(sql);
    }

    /**
     * 保存
     * @param map
     * @return
     */
    public Integer saveSingeArticle(Map<String, String> map) {
        String sql = "INSERT INTO article(title, pic, description,content,type, grade, deleted) values(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                map.get("title"), map.get("pic"), map.get("description"),
                map.get("content"), map.get("type"),map.get("grade"), map.get("deleted"));
    }
}