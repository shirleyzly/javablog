package blog.com.blog;
import blog.com.blog.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@SpringBootApplication
public class BlogApplicationTests {
    @Autowired
    private ArticleService articleService;

    @Test
    public void test() throws Exception {
        Map<String, String> detailMap = articleService.getArticleDetail(1);
        Object obj = detailMap.get("id");
        Integer id = Integer.valueOf(String.valueOf(obj));

        List recommond = articleService.getRecommondArticleList(detailMap);
    }
}
