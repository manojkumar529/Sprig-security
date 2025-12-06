
package com.example.client;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OAuthClientApplication implements CommandLineRunner{
	 @Autowired
	    private ApplicationContext applicationContext;
    public static void main(String[] args) {
        SpringApplication.run(OAuthClientApplication.class, args);
    }
	@Override
	public void run(String... args) throws Exception {
		System.out.println("--- Registered Servlet Filters ---");
        Map<String, FilterRegistrationBean> beans = applicationContext.getBeansOfType(FilterRegistrationBean.class);
        
        beans.forEach((beanName, bean) -> {
            System.out.println("Filter: " + bean.getFilter().getClass().getSimpleName());
            System.out.println("  Name: " + bean.getFilterName());
            System.out.println("  Order: " + bean.getOrder());
            System.out.println("  URL Patterns: " + bean.getUrlPatterns());
        });
		
	}
}
