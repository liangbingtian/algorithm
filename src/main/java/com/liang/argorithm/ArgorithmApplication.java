package com.liang.argorithm;

import com.liang.argorithm.filter.HttpFilter;
import com.liang.argorithm.interceptor.HttpInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("com.liang.argorithm.aboutproject")
@EnableMongoRepositories(basePackages = "com.liang.argorithm.aboutproject.repository")
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
