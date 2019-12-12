package blog.com.blog.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DiaryDao extends _BaseDao {

    /**
     * 记录数
     * @param map
     * @return
     */
    public Integer getDiaryTotalRecord(Map<String, String> map) {
        String sql = "select count(id) from diary where 1=1";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * 获取列表
     * @return
     */
    public List getDiaryList(Integer startNum, Integer pageSize) {
        String sqlStr = "SELECT id, title, description, pic, type,click, deleted, create_time AS createTime, update_time AS updateTime" +
                " FROM diary LIMIT "+startNum+","+pageSize;
        return jdbcTemplate.queryForList(sqlStr);
    }

    /**
     * 详情
     * @param id
     * @return
     */
    public Map getDiaryDetail(Integer id) {
        String where = "";
        if(id>0) {
            where = " AND id="+id;
        }
        String sql = "SELECT id,title,description,content,type,click,create_time AS createTime, update_time AS updateTime" +
                " FROM diary WHERE deleted=0 "+where+" LIMIT 1";
        return jdbcTemplate.queryForMap(sql);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public Integer deleteSingleDiary(Integer id) {
        String sql = "DELETE FROM diary WHERE id="+id;
        return jdbcTemplate.update(sql);
    }

    /**
     * 保存
     * @param map
     * @return
     */
    public Integer saveSingeDiary(Map<String, String> map) {
        String sql = "INSERT INTO diary(title, pic, description,content,type, deleted) values(?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                map.get("title"), map.get("pic"), map.get("description"),
                map.get("content"), map.get("type"), map.get("deleted"));
    }
}
