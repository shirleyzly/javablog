package blog.com.blog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlogProperties {

    @Value("${com.blog.name}")
    private String blogName;

    @Value("${com.blog.url}")
    private String blogUrl;

    public String getBlogName() {
        return blogName;
    }

    public String getBlogUrl() {
        return blogUrl;
    }
}
