package blog.com.blog.service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Resource
public interface NoticeService {
    Integer getNoticeTotalRecord();
    List getNoticeList(Integer startNum, Integer pageSize);
    Map getNoticeDetail(Integer id);
    Integer deleteSingleNotice(Integer id);
    Integer saveNoticeData(Map<String, String> map);
}
