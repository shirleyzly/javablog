package blog.com.blog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 基类Dao
 */
public class _BaseDao {

    public JdbcTemplate jdbcTemplate;

    /**
     * 自动注入JdbcTemplate的Bean
     * @param jdbcTemplate
     */
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}