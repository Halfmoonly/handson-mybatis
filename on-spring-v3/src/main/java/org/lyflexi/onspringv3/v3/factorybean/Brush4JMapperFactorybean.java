package org.lyflexi.onspringv3.v3.factorybean;



import org.lyflexi.onspringv3.v3.anno.SqlSelect;
import org.lyflexi.onspringv3.v3.entity.AccountInfo;
import org.lyflexi.onspringv3.v3.entity.ProductInfo;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * [来个全套]
 *
 * @slogan: 高于生活，源于生活
 * @Description: TODO
 * @author: lyflexi
 * @date 2020/5/5 14:44
 */
public class Brush4JMapperFactorybean<T> implements FactoryBean<T> {

	//接口类型为泛型，意味着动态代理可以代理任意的接口实现,如
	//org.lyflexi.onspringv2.v2.dao.AccountMapper
	//org.lyflexi.onspringv2.v2.dao.ProductMapper
	private Class<T> targetClass;

	public Brush4JMapperFactorybean(Class<T> targetClass) {
		this.targetClass = targetClass;
	}

	@Nullable
	@Override
	public T getObject() throws Exception {
		return (T) Proxy.newProxyInstance(targetClass.getClassLoader(),new Class[]{targetClass},new Brush4JMapperProxy());
	}

	@Nullable
	@Override
	public Class<?> getObjectType() {
		return targetClass;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}

class Brush4JMapperProxy implements InvocationHandler{

	/**
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//处理Object原生的方法
		if(method.getDeclaringClass().equals(Object.class)) {
			return method.invoke(this, args);
		}
		//处理方法注解中的sql
		SqlSelect sqlSelect = method.getAnnotation(SqlSelect.class);
		String parseSql = sqlSelect.value();
		System.out.println("解析业务sql:"+parseSql+"入参:"+ Arrays.asList(args));

		Class<?> clazz = method.getReturnType();
		if(clazz.equals(ProductInfo.class)) {
			return new ProductInfo();
		}
		if(clazz.equals(AccountInfo.class)) {
			return new AccountInfo();
		}
		return null;
	}
}
