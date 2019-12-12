package blog.com.blog.service.impl;

import blog.com.blog.dao.NoticeDao;
import blog.com.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class NoticeImplService implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Override
    public Integer getNoticeTotalRecord() {
        return noticeDao.getNoticeTotalRecord();
    }

    @Override
    public List getNoticeList(Integer startNum, Integer pageSize) {
       return noticeDao.getNoticeList(startNum,pageSize);
    }

    @Override
    public Map getNoticeDetail(Integer id) {
        return noticeDao.getNoticeDetail(id);
    }

    @Override
    public Integer deleteSingleNotice(Integer id) {
        Map detail = getNoticeDetail(id);
        if(detail.size()>0 && !detail.equals("")) {
            return noticeDao.deleteSingleNotice(id);
        }
        return 0;
    }

    @Override
    public Integer saveNoticeData(Map<String, String> map) {
        return noticeDao.saveSingeNotice(map);
    }
}
