package com.test.urlparser;




import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/03/16/14:27
 * @Description: 解析url中的协议、IP/域名、端口、路径、查询参数
 */
public class UrlParser {
    // 核心正则表达式
    private static final String URL_REGEX = "^(?:([a-zA-Z0-9+-]+):\\/\\/)?(?:([^:@\\/]+)(?::([^@\\/]+))?@)?([^:\\/?#]+)(?::(\\d+))?([^?#]*)(?:\\?([^#]*))?(?:#(.*))?$";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);



    public static UrlInfo parseUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return new UrlInfo("", "", "", "", "", ""); // 处理空URL
        }

        Matcher matcher = URL_PATTERN.matcher(url);
        if (matcher.matches()) {
            // 直接从分组中取值，构造UrlInfo对象（空值由构造方法处理）
            return new UrlInfo(
                    matcher.group(1),   // 协议
                    matcher.group(4),   // IP/域名
                    matcher.group(5),   // 端口
                    matcher.group(6),   // 路径
                    matcher.group(7),   // 查询参数
                    matcher.group(8)    // 锚点
            );
        }
        // 不匹配的URL返回空字段的对象
        return new UrlInfo("", "", "", "", "", "");
    }


    public static class UrlInfo{
        private final String protocol;  // 协议（http/https/ftp等）
        private final String ipOrDomain;// IP/域名
        private final String port;      // 端口号
        private final String path;      // 路径
        private final String query;     // 查询参数
        private final String anchor;    // 锚点（hash）

        public UrlInfo(String protocol, String ipOrDomain, String port, String path, String query, String anchor) {
            this.protocol = protocol;
            this.ipOrDomain = ipOrDomain;
            this.port = port;
            this.path = path;
            this.query = query;
            this.anchor = anchor;
        }

        public String getProtocol() {
            return protocol;
        }

        public String getIpOrDomain() {
            return ipOrDomain;
        }

        public String getPort() {
            return port;
        }

        public String getPath() {
            return path;
        }

        public String getQuery() {
            return query;
        }

        public String getAnchor() {
            return anchor;
        }

        @Override
        public String toString() {
            return "UrlInfo{" +
                    "protocol='" + protocol + '\'' +
                    ", ipOrDomain='" + ipOrDomain + '\'' +
                    ", port='" + port + '\'' +
                    ", path='" + path + '\'' +
                    ", query='" + query + '\'' +
                    ", anchor='" + anchor + '\'' +
                    '}';
        }
    }
}
