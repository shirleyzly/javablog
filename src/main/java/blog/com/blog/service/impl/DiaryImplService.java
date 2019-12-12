package blog.com.blog.service.impl;

import blog.com.blog.dao.DiaryDao;
import blog.com.blog.service.DiaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class DiaryImplService implements DiaryService {

    @Resource
    private DiaryDao diaryDao;

    /**
     * 获取记录数
     * @param map
     * @return
     */
    @Override
    public Integer getDiaryTotalRecord(Map<String, String> map) {
        return diaryDao.getDiaryTotalRecord(map);
    }

    /**
     * 获取列表
     * @param startNum
     * @param pageSize
     * @return
     */
    @Override
    public List getDiaryList(Integer startNum, Integer pageSize) {
        return diaryDao.getDiaryList(startNum, pageSize);
    }

    /**
     * detail
     * @param id
     * @return
     */
    @Override
    public Map getDiaryDetail(Integer id) {
        return diaryDao.getDiaryDetail(id);
    }

    /**
     * 删除
     * 0删除失败
     * @param id
     * @return
     */
    @Override
    public Integer deleteSingleDiary(Integer id) {
        Map detail = getDiaryDetail(id);
        if(detail.size()>0 && !detail.equals("")) {
            return diaryDao.deleteSingleDiary(id);
        }
        return 0;
    }

    /**
     * 保存
     * @param map
     * @return
     */
    @Override
    public Integer saveDiaryData(Map<String, String> map) {
        return diaryDao.saveSingeDiary(map);
    }
}
