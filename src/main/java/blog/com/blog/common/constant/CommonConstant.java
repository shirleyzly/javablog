package blog.com.blog.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用常量配置
 */
public class CommonConstant {

    //博客名称
    public final static String BLOG_NAME = "溪畔芦苇";
    //博客title
    public final static String BLOG_TITLE = "溪畔芦苇-个人博客";
    //博客description
    public final static String BLOG_DESCRIPTION = "个人博客-为了代码第2次出发，从基础垒起~";
    //博客email
    public final static String BLOG_EMAIL = "blog@lulijuan.com";

    //导航栏
    public final static Map MAIN_NAVIGATION_MENU = new HashMap();
    static {
        MAIN_NAVIGATION_MENU.put("index", "首页");
        MAIN_NAVIGATION_MENU.put("basic","编程基础");
        MAIN_NAVIGATION_MENU.put("learn","编程进阶");
        MAIN_NAVIGATION_MENU.put("resume","个人简历");
        MAIN_NAVIGATION_MENU.put("write","个人写作");
        MAIN_NAVIGATION_MENU.put("login","登录后台");
    }

    //其它导航菜单
    public final static Map CORNER_NAVIGATION_MENU = new HashMap();
    static {
        CORNER_NAVIGATION_MENU.put("tags", "标签云");
        CORNER_NAVIGATION_MENU.put("links", "友情链接");
    }

    //首页大分类(后端/数据库/前端)
    public final static Map CODE_BIG_TYPE = new HashMap();
    static {
        CODE_BIG_TYPE.put("1", "后端");
        CODE_BIG_TYPE.put("2", "数据库");
        CODE_BIG_TYPE.put("3", "前端");
        CODE_BIG_TYPE.put("4", "其它");
    }

    //文章等级类型
    public final static Map CODE_GRADE = new HashMap();
    static {
        CODE_GRADE.put("0", "编程基础");
        CODE_GRADE.put("1", "编程进阶");
    }

    //代码分类 php/java/other
    public final static Map CODE_TYPE = new HashMap();
    static {
        CODE_TYPE.put("1","php");
        CODE_TYPE.put("2","java");
        CODE_TYPE.put("3","SQL");
        CODE_TYPE.put("4","go");
        CODE_TYPE.put("5","python");
        CODE_TYPE.put("6","internet");
        CODE_TYPE.put("7","大数据");
        CODE_TYPE.put("8","运维");
        CODE_TYPE.put("9","前端");
        CODE_TYPE.put("10","其它");
    }

    //代码分类默认图片
    public final static Map CODE_TYPE_PIC = new HashMap();
    static {
        CODE_TYPE_PIC.put("0","/images/excerpt.jpg");
        CODE_TYPE_PIC.put("1","/images/excerpt.jpg");
        CODE_TYPE_PIC.put("2","/images/java.jpg");
        CODE_TYPE_PIC.put("3","/images/code.jpg");
    }

    //链接类型
    public final static Map LINK_TYPE = new HashMap();
    static {
        LINK_TYPE.put("0", "收藏链接");
        LINK_TYPE.put("1", "友情链接");
    }

    //写作类型 类型0：学习相关 1：工作相关 2:生活相关 3：业余兴趣
    public final static Map WRITE_TYPE = new HashMap();
    static {
        WRITE_TYPE.put("0", "学习相关");
        WRITE_TYPE.put("1", "工作相关");
        WRITE_TYPE.put("2", "生活相关");
        WRITE_TYPE.put("3", "业余兴趣");
    }
}
