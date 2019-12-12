package blog.com.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ArticlesEntity implements Serializable {
    private Integer id;
    private String title;
    private String content;
    private Integer type;
    private Integer deleted;
    private Date createTime;
    private Date updateTime;
}
