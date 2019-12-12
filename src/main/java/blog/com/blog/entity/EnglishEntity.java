package blog.com.blog.entity;

import lombok.Data;
import java.util.Date;

@Data
public class EnglishEntity {
    private Integer id;
    private String en;
    private String cn;
    private Date date;
    private Integer deleted;
    private Date createTime;
    private Date updateTime;
}
