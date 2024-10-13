package org.lyflexi.onspringv2.v2.compent;


import org.lyflexi.onspringv2.v2.anno.EnableMapperScanner;
import org.lyflexi.onspringv2.v2.factorybean.TulingMapperFactorybean;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
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
 * @date 2020/5/5 14:29
 */
public class TulingImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

	private static Class targetClass = TulingMapperFactorybean.class;

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		AnnotationAttributes attributes = (AnnotationAttributes) importingClassMetadata.getAnnotationAttributes(EnableMapperScanner.class.getName());

		//配置了EnableMapperScanner 注解
		if(attributes ==null) {
			return;
		}
		String basePackage = attributes.getString("basePackage");

		//扫描bean定义
		TulingClassPathMapperScanner mapperScanner = new TulingClassPathMapperScanner(registry);

		mapperScanner.addIncludeFilter(new TypeFilter() {
			@Override
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
				return true;
			}
		});


		//此时扫描出来的bean定义 是一个一个的接口  //我们指定的mapper包下的接口类型bean定义.
		//批量版的导入bean定义
		Set<BeanDefinitionHolder> scannedBds =  mapperScanner.doScan(basePackage);



		for (BeanDefinitionHolder bdh:scannedBds) {



			//获取bean定义
			GenericBeanDefinition beanDefinition = (GenericBeanDefinition) bdh.getBeanDefinition();
			//拿到bean定义中的接口的calss 字符串  com.tuling.dao.AccountMapper

			String  sourceClass = beanDefinition.getBeanClassName();


			System.out.println("原生接口的class类型:"+sourceClass);

			//虽然使用自定义的扫描器TulingClassPathMapperScanner可以扫描到接口mapper
			// 但是接口仍然是无法实例化的，所以这里修改beanDefinition，将beanClass设置为factoryBean
			beanDefinition.setBeanClass(targetClass);



			/**
			 * 	这行代码的作用是向 BeanDefinition 的构造函数参数列表中添加一个泛型参数值。具体来说：
			 * 	BeanDefinition：表示一个Bean的定义，包含了Bean的各种配置信息。
			 * 	getConstructorArgumentValues()：返回一个 ConstructorArgumentValues 对象，该对象用于存储构造函数参数的值。
			 * 	addGenericArgumentValue(sourceClass)：向构造函数参数列表中添加一个泛型参数值。sourceClass 是要添加的参数值，通常是一个类对象。
			 *
			 * 为什么需要这行代码
			 * 动态配置FactoryBean：在某些情况下，你可能需要在运行时动态地为Bean的构造函数提供参数值，而不是在XML配置文件中静态地指定。假设你有一个 UserMapper 接口和一个 MapperFactoryBean 类，MapperFactoryBean 类有一个带参数的构造函数
			 */
			beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(sourceClass);
		}




		System.out.println("TulingImportBeanDefinitionRegister scan counts:"+scannedBds);
	}
}
