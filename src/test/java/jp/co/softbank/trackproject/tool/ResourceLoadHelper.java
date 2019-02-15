package jp.co.softbank.trackproject.tool;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.core.io.Resource;

public class ResourceLoadHelper {

  private static final String DEFAULT_CHARSET = "UTF-8";
  
  private ClassRelativeResourceLoader loader;
  
  private String charset;

  public ResourceLoadHelper(Class<?> clazz) {
    this(clazz, DEFAULT_CHARSET);
  }
  
  public ResourceLoadHelper(Class<?> clazz, String charset) {
    this.loader = new ClassRelativeResourceLoader(clazz);
    this.charset = charset;
  }
  
  public String content(String filePath) throws IOException {
    Resource resource = loader.getResource(filePath);
    try (InputStream is = resource.getInputStream()) {
      return IOUtils.toString(is, charset);
    }
  }
}
