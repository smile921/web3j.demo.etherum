package zjex.com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frere921 on 2017/3/24.
 */
@Configuration

@ComponentScan(basePackages = {"zjex.com.controller"})
public class SpringServlet extends WebMvcConfigurerAdapter {

    /**
     * mediaType supportMediaTypes converter converters
     * 媒体适配器配置
     * @return
     */
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter amha = new RequestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> msgConvLst = new ArrayList<>();
        MappingJackson2HttpMessageConverter jacksonConv = new MappingJackson2HttpMessageConverter();

        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();

        MediaType mediaType = MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE);

        supportedMediaTypes.add(mediaType);
        jacksonConv.setSupportedMediaTypes(supportedMediaTypes);
        msgConvLst.add(jacksonConv);
        amha.setMessageConverters(msgConvLst);

        return amha;
    }

    /**
     *
     * @return
     */
//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//
//        multipartResolver.setDefaultEncoding("utf-8");
//
//        multipartResolver.setMaxUploadSize(2048 * 1024);
//
//        multipartResolver.setMaxInMemorySize(2048);
//
//        return multipartResolver;
//    }
}
