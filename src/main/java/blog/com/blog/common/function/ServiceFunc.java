package blog.com.blog.common.function;

import org.springframework.ui.ModelMap;
import java.util.List;

/**
 * 业务型公共类
 */
public class ServiceFunc {

    /**
     * 格式化待渲染的ModelMap
     * @return
     */
    public static ModelMap formatMapAttribute(Integer page, Integer pageSize,
                                              Integer totalPages, List data, String pageName) {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("pageSize", pageSize);
        modelMap.addAttribute("isFirstPage", page>1 ? false : true);
        modelMap.addAttribute("totalPages", totalPages);
        modelMap.addAttribute("isLastPage", (page==totalPages) ? true : false);
        modelMap.addAttribute("data", data);
        modelMap.addAttribute("page_name",pageName+".html");
        return modelMap;
    }
}
