package blog.com.blog.service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Resource
public interface EnglishService {
    Integer getSentenceTotalRecord();
    List getSentenceList(Integer startNum, Integer pageSize);
    Map getEnglishSentences();
    Map getEnglishSentenceDetail(Integer id);
    Integer deleteSingleSentence(Integer id);
    Integer saveSentenceData(Map<String, String> map);
}
