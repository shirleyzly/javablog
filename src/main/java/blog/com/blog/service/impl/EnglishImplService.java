package blog.com.blog.service.impl;

import blog.com.blog.dao.EnglishDao;
import blog.com.blog.service.EnglishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class EnglishImplService implements EnglishService {
    @Autowired
    private EnglishDao englishDao;

    /**
     * 总记录数
     * @return
     */
    @Override
    public Integer getSentenceTotalRecord() {
        return englishDao.getSentenceTotalRecord();
    }

    /**
     * 获取列表
     * @return
     */
    @Override
    public List getSentenceList(Integer startNum, Integer pageSize) {
        return englishDao.getSentenceList(startNum, pageSize);
    }

    @Override
    public Map getEnglishSentences() {
        return englishDao.getEnglishSentences();
    }

    @Override
    public Map getEnglishSentenceDetail(Integer id) {
        return englishDao.getEnglishSentenceDetail(id);
    }

    @Override
    public Integer deleteSingleSentence(Integer id) {
        Map detail = getEnglishSentenceDetail(id);
        if(detail.size()>0 && !detail.equals("")) {
            return englishDao.deleteSingleSentence(id);
        }
        return 0;
    }

    @Override
    public Integer saveSentenceData(Map<String, String> map) {
        return englishDao.saveSingeSentence(map);
    }
}
