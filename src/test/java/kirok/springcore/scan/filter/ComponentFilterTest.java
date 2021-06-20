package kirok.springcore.scan.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.context.annotation.FilterType.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(
            FilterConfig.class
        );

        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        Assertions.assertThrows(NoSuchBeanDefinitionException.class,
            () -> ac.getBean("beanB", BeanB.class));

    }

    @Configuration
    @ComponentScan(
        includeFilters = @ComponentScan.Filter(type = ANNOTATION, classes = MyIncludeComponent.class),
        excludeFilters = @ComponentScan.Filter(type = ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class FilterConfig {

    }

}
