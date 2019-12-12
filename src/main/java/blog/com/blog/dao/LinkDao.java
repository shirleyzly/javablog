package blog.com.blog.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 链接表
 */
@Repository
public class LinkDao extends _BaseDao {
    /**
     * 查询总记录数
     * @return
     */
    public Integer getLinkTotalRecord() {
        String sql = "select count(id) from link where 1=1";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * 获取公告列表
     * @return
     */
    public List getLinkList(Integer startNum, Integer pageSize) {
        String sqlStr = "SELECT id, url, title, description, type, deleted, create_time AS createTime " +
                "FROM link LIMIT "+startNum+","+pageSize;
        return jdbcTemplate.queryForList(sqlStr);
    }

    /**
     * 详情
     * @param id
     * @return
     */
    public Map getLinkDetail(Integer id) {
        String where = "";
        if(id>0) {
            where = " AND id="+id;
        }
        String sql = "SELECT id, url, title, description, type,deleted, create_time AS createTime, update_time AS updateTime" +
                " FROM link WHERE deleted=0 "+where+" LIMIT 1";
        return jdbcTemplate.queryForMap(sql);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public Integer deleteSingleLink(Integer id) {
        String sql = "DELETE FROM link WHERE id="+id;
        return jdbcTemplate.update(sql);
    }

    /**
     * 保存
     * @param map
     * @return
     */
    public Integer saveSingeLink(Map<String, String> map) {
        String sql = "INSERT INTO link(url,title,description,type,deleted) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, map.get("url"), map.get("title"), map.get("description"), map.get("type"), map.get("deleted"));
    }
}