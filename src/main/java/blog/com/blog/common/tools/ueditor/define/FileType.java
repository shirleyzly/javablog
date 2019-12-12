package blog.com.blog.common.tools.ueditor.define;

import java.util.HashMap;
import java.util.Map;

public class FileType {

	public static final String JPG = "JPG";
	
	private static final Map<String, String> types = new HashMap<String, String>(){{
		
		put( blog.com.blog.common.tools.ueditor.define.FileType.JPG, ".jpg" );
		
	}};
	
	public static String getSuffix ( String key ) {
		return blog.com.blog.common.tools.ueditor.define.FileType.types.get( key );
	}
	
	/**
	 * 根据给定的文件名,获取其后缀信息
	 * @param filename
	 * @return
	 */
	public static String getSuffixByFilename ( String filename ) {
		
		return filename.substring( filename.lastIndexOf( "." ) ).toLowerCase();
		
	}
	
}
