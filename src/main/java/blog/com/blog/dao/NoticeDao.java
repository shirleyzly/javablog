package blog.com.blog.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class NoticeDao extends _BaseDao {

    /**
     * 查询总记录数
     * @return
     */
    public Integer getNoticeTotalRecord() {
        String sql = "select count(id) from notice where 1=1";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * 获取公告列表
     * @return
     */
    public List getNoticeList(Integer startNum, Integer pageSize) {
        String sqlStr = "SELECT id, title, deleted, date_format(create_time,'%m-%d') AS date,create_time AS createTime " +
                        "FROM notice LIMIT "+startNum+","+pageSize;
        return jdbcTemplate.queryForList(sqlStr);
    }

    /**
     * 详情
     * @param id
     * @return
     */
    public Map getNoticeDetail(Integer id) {
        String where = "";
        if(id>0) {
            where = " AND id="+id;
        }
        String sql = "SELECT id, title, content,deleted, create_time AS createTime, update_time AS updateTime" +
                " FROM notice WHERE deleted=0 "+where+" LIMIT 1";
        return jdbcTemplate.queryForMap(sql);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public Integer deleteSingleNotice(Integer id) {
        String sql = "DELETE FROM notice WHERE id="+id;
        return jdbcTemplate.update(sql);
    }

    /**
     * 保存
     * @param map
     * @return
     */
    public Integer saveSingeNotice(Map<String, String> map) {
        String sql = "INSERT INTO notice(title,content,deleted) values(?,?,?)";
        return jdbcTemplate.update(sql, map.get("title"), map.get("content"), map.get("deleted"));
    }
}
