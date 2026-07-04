package hello.exception;

import hello.exception.filter.LogFilter;
import hello.exception.intercepter.LogInterceptor;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 필터
    // @Bean
    public FilterRegistrationBean<Filter> logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");

        // 이 설정을 통해 클라이언트 요청(REQUEST)과 에러 재요청(ERROR) 시 모두 필터가 호출되도록 제어함
        // default는 REQUEST만 허용 하는 것(만약, 에러 재요청에 대해 필터를 호출하지 않게 하려면, REQUEST만 setDispatcherTypes 하면 됨)
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);

        return filterRegistrationBean;
    }

    // 인터셉터
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/css/**", "/*.ico", "/error", "/error-page/**" // 오류 페이지 경로("/error-page" 는 인터셉터 호출 x)
                );
    }
}
