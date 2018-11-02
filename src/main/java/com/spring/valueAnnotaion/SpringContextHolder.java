package com.spring.valueAnnotaion;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {
    private static ApplicationContext context = null;

    public SpringContextHolder() {
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        context = arg0;
    }

    @Override
    public void destroy() throws Exception {
        context = null;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
