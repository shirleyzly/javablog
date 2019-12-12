package blog.com.blog.service;

import java.util.List;
import java.util.Map;

public interface DiaryService {
    Integer getDiaryTotalRecord(Map<String, String> map);
    List getDiaryList(Integer startNum, Integer pageSize);
    Map getDiaryDetail(Integer id);
    Integer deleteSingleDiary(Integer id);
    Integer saveDiaryData(Map<String, String> map);
}
