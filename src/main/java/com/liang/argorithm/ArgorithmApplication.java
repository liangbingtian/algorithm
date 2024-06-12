package com.liang.argorithm;

import com.liang.argorithm.aboutproject.servlet.VerifyServlet;
import com.liang.argorithm.filter.MyHttpFilter;
import com.liang.argorithm.interceptor.HttpInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("com.liang.argorithm.aboutproject.mapper")
@EnableMongoRepositories(basePackages = "com.liang.argorithm.aboutproject.repository")
public class ArgorithmApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ArgorithmApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<MyHttpFilter> httpFilter() {
		FilterRegistrationBean<MyHttpFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new MyHttpFilter());
		registrationBean.addUrlPatterns("/threadLocal/*");
		return registrationBean;
	}

	@Bean
	public ServletRegistrationBean<VerifyServlet> indexServletRegistration() {
		ServletRegistrationBean<VerifyServlet> registration = new ServletRegistrationBean<>(new VerifyServlet());
		registration.addUrlMappings("/getVerifyCode");
		return registration;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
	}
}
