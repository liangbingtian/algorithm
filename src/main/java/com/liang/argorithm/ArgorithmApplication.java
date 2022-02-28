package com.liang.argorithm;

import com.liang.argorithm.concurrency.threadLocal.HttpFilter;
import com.liang.argorithm.concurrency.threadLocal.HttpInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.liang.argorithm.aboutproject")
public class ArgorithmApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ArgorithmApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<HttpFilter> httpFilter() {
		FilterRegistrationBean<HttpFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new HttpFilter());
		registrationBean.addUrlPatterns("/threadLocal/*");
		return registrationBean;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
	}
}
