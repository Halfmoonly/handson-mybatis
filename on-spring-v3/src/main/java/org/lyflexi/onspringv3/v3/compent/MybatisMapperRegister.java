package org.lyflexi.onspringv3.v3.compent;

import org.lyflexi.onspringv3.v3.factorybean.Brush4JMapperFactorybean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Set;

/**
 * [来个全套]
 *
 * @slogan: 高于生活，源于生活
 * @Description: TODO
 * @author: lyflexi
 * @date 2020/5/5 15:12
 */
public class MybatisMapperRegister implements BeanDefinitionRegistryPostProcessor {

    private String basePackage;
    // 提供setter方法
    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
    private static Class targetClass = Brush4JMapperFactorybean.class;


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //扫描bean定义
        MybatisClassPathMapperScanner mapperScanner = new MybatisClassPathMapperScanner(registry);

        mapperScanner.addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                return true;
            }
        });


        Set<BeanDefinitionHolder> scannedBds = mapperScanner.doScan(this.basePackage);

        for (BeanDefinitionHolder bdh : scannedBds) {

            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) bdh.getBeanDefinition();
            String sourceClass = beanDefinition.getBeanClassName();
            System.out.println("原生接口的class类型:" + sourceClass);
            beanDefinition.setBeanClass(targetClass);
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(sourceClass);
        }

        System.out.println(scannedBds);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
