package blog.com.blog.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EnglishDao extends _BaseDao {

    /**
     * 查询总记录数
     * @return
     */
    public Integer getSentenceTotalRecord() {
        String sql = "select count(id) from english where 1=1";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * 获取列表
     * @return
     */
    public List getSentenceList(Integer startNum, Integer pageSize) {
        String sqlStr = "SELECT id, en, cn, deleted, date, create_time AS createTime FROM english LIMIT "+startNum+","+pageSize;
        return jdbcTemplate.queryForList(sqlStr);
    }

    /**
     * 每日推荐一句
     * @return
     */
    public Map getEnglishSentences() {
        String sqlStr = "SELECT id,en,cn,date,deleted FROM english ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.queryForMap(sqlStr);
    }

    /**
     * 详情
     * @param id
     * @return
     */
    public Map getEnglishSentenceDetail(Integer id) {
        String where = "";
        if(id>0) {
            where = " AND id="+id;
        }
        String sql = "SELECT  id,en,cn,date,deleted  FROM english WHERE deleted=0 "+where+" LIMIT 1";
        return jdbcTemplate.queryForMap(sql);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public Integer deleteSingleSentence(Integer id) {
        String sql = "DELETE FROM english WHERE id="+id;
        return jdbcTemplate.update(sql);
    }

    /**
     * 保存
     * @param map
     * @return
     */
    public Integer saveSingeSentence(Map<String, String> map) {
        String sql = "INSERT INTO english(en,cn,date,deleted) values(?,?,?,?)";
        return jdbcTemplate.update(sql, map.get("en"), map.get("cn"), map.get("date"), map.get("deleted"));
    }
}
