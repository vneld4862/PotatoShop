package kh.study.team2.shop.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class IntercepterConfig implements WebMvcConfigurer{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getCategoryIntercepter())
				.addPathPatterns("/admin/**")
//					.excludePathPatterns("/admin/selectCategoryListInUseAjax"
//											,"/admin/changeStatus");
				.excludePathPatterns("/admin/**Ajax");
	}
	
	@Bean
	public AdminMenuInterceptors getCategoryIntercepter()
	{
		return new AdminMenuInterceptors();
	}
}
