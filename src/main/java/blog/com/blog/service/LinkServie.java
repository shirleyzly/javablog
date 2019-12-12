package blog.com.blog.service;

import java.util.List;
import java.util.Map;

public interface LinkServie {
    Integer getLinkTotalRecord();
    List    getLinkList(Integer startNum, Integer pageSize);
    Map     getLinkDetail(Integer id);
    Integer deleteSingleLink(Integer id);
    Integer saveLinkData(Map<String, String> map);
}
