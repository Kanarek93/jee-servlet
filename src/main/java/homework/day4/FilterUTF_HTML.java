package homework.day4;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "FilterUTF_HTML", value = "/*")
public class FilterUTF_HTML implements Filter {
    private String charsetEncoding = "utf-8";
    private String contentType = "text/html";
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(charsetEncoding);
        resp.setCharacterEncoding(charsetEncoding);
        resp.setContentType(contentType);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
