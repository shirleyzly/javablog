package blog.com.blog.service.impl;

import blog.com.blog.dao.LinkDao;
import blog.com.blog.service.LinkServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class LinkImplService implements LinkServie {

    @Autowired
    private LinkDao linkDao;

    /**
     * 记录数
     * @return
     */
    @Override
    public Integer getLinkTotalRecord() {
        return linkDao.getLinkTotalRecord();
    }

    /**
     * 列表
     * @param startNum
     * @param pageSize
     * @return
     */
    @Override
    public List getLinkList(Integer startNum, Integer pageSize) {
        return linkDao.getLinkList(startNum, pageSize);
    }

    @Override
    public Map getLinkDetail(Integer id) {
        return linkDao.getLinkDetail(id);
    }

    @Override
    public Integer deleteSingleLink(Integer id) {
        Map detail = getLinkDetail(id);
        if(detail.size()>0 && !detail.equals("")) {
            return linkDao.deleteSingleLink(id);
        }
        return 0;
    }

    @Override
    public Integer saveLinkData(Map<String, String> map) {
        return linkDao.saveSingeLink(map);
    }
}
