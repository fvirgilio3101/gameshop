package it.ecubit.gameshop;

import jakarta.annotation.PreDestroy;
import jakarta.servlet.ServletException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    private static final Logger _log = LoggerFactory.getLogger(ServletInitializer.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

    @Override
    public void onStartup(jakarta.servlet.ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }

    @PreDestroy
    public void destroy() {
        _log.info("Destroy ");
    }
}

