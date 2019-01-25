package com.honglin_chen.spring_bean_lifecycle.service;

import com.honglin_chen.spring_bean_lifecycle.bean.Customer;

/* ApplicationContext: 应用中央接口提供应用构造. 当应用运行时处于只读模式
 * ApplicationContextAware: 接口用于提供通知给所有希望被通知的object, 比如当一个object
 * 							access到一个资源，发布一个事件时, 文本就会通知该object
 * ApplicationEventPublisher: 接口用于包装事件发布功能 
 * EnvironmentAware: 接口用于通知所有希望被通知且在当前环境下运行的bean
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware; 
import org.springframework.context.EnvironmentAware; 
import org.springframework.context.ResourceLoaderAware;

/* core.env.Environment: 接口用于表示当前应用正在运行的环境并模型化应用环境的两个方面
 * 						 profile: 包含了一组需要被容器注册的bean的定义 
 * 						 property: 环境object提供构造property的接口
 * core.io.Resource: 资源描述器接口用于实现真实资源的抽象化, 对于任意physical的资源
 * 					 提供资源输入流
 * core.io.ResourceLoader: 资源加载器
 */
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader; 

/* BeanFactory: 根接口用于获取Spring bean的容器. 该接口被持有bean定义的object继承
 * 				根据bean的定义工厂可能返回一个独立的原型类实例(prototype)或是一个单
 * 				元类实例(Singleton). 通常BeanFactory会先从构造资源(XML)处载入存储
 * 				bean定义的文件然后再构造bean
 */
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
/* BeanNameAware: 在工厂中的bean为其提供显示其名字的服务
 */
import org.springframework.beans.factory.BeanNameAware; 
/* BeanClassLoaderAware: 回溯函数允许bean通知当前正在加载bean类的类加载器
 */
import org.springframework.beans.factory.BeanClassLoaderAware;

import java.util.Arrays; 

public class CustomerServiceImpAwareInterface implements ApplicationContextAware, 
	ApplicationEventPublisherAware, BeanClassLoaderAware, BeanFactoryAware, BeanNameAware, 
	EnvironmentAware, ResourceLoaderAware {
	
	private Customer customer; 
	/* 声明一个bean name的字符串数组 */
	private String[] beanNames;
	
	/* 得到一个客户object */
	public Customer getCustomer() {
		return customer; 
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer; 
	}
	
	/* 得到一组bean的名字 */
	public String[] getBeanNames() {
		return beanNames; 
	}
	
	/* 设置一组bean的名字 */
	public void setBeanNames(String[] beanNames) {
		this.beanNames = beanNames; 
	}
	
	/* 建立当前类的构造函数 */
	public CustomerServiceImpAwareInterface() {
		System.out.println("这是CustomerImpAwareInterface的构造函数");
	}
	
	/* 重载setBeanName函数实现设置bean名字的功能 */
	@Override 
	public void setBeanName(String beanName) {
		System.out.println("setBeanName()函数: ");
		System.out.println("现在的bean的名字是: " + beanName); 
	}
	
	/* 重载setBeanClassLoader函数实现设置bean类加载器的功能 */ 
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("setBeanClassLoader()函数: "); 
		System.out.println("现在的bean类加载器是: " + classLoader.getClass().getName()); 
	}
	
	/* 重载setBeanFactory函数实现设置bean工厂功能 */ 
	@Override 
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("setBeanFactory()函数: ");
		for(String beanName : beanNames) {
			System.out.println("bean工厂含有: \"" + beanName + "\"?: " + beanFactory.containsBean(beanName));
		}
	}
	
	/* 重载设置环境函数实现设置整体环境 */
	@Override 
	public void setEnvironment(Environment env) {
		System.out.println("setEnvironment()函数: "); 
	}
	
	/* 重载设置资源加载器函数实现设置资源加载器功能 */
	@Override 
	public void setResourceLoader(ResourceLoader resourceLoader) {
		System.out.println("setResourceLoader()函数 "); 
		/* 创建一个资源object */
		Resource resource = resourceLoader.getResource("classpath:bean_imp_aware_xml"); 
		System.out.println("现在的资源文件名字是: " + resource.getFilename()); 
	}
	
	/* 重载设置文本事件发布者函数实现设置文本事件发布者功能 */
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		System.out.println("setApplicationEventPublisher()函数"); 
	}
	
	/* 重载设置文本函数实现设置文本功能 */
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		System.out.println("setApplicationContext()函数");
		System.out.println("所有的bean: " + Arrays.toString(context.getBeanDefinitionNames()));
	}
}
