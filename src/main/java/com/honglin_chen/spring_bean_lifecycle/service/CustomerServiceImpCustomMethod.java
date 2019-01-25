package com.honglin_chen.spring_bean_lifecycle.service;

import com.honglin_chen.spring_bean_lifecycle.bean.Customer;

/* 导入bean工厂接口 */
import org.springframework.beans.factory.InitializingBean; 
import org.springframework.beans.factory.DisposableBean;

/* 导入注解包 */
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/* InitializingBean: bean工厂callback接口用来初始化bean
 * DisposableBean: bean工厂callback接口用来回收bean
 */
public class CustomerServiceImpCustomMethod implements InitializingBean, DisposableBean {
	private Customer customer; 
	
	/* 定义当前类构造器 */
	public CustomerServiceImpCustomMethod() {
		System.out.println("This is CustomerServiceImpCustomMethod contstructor");
	}
	
	/* 得到一个客户object */
	public Customer getCustomer() {
		return customer; 
	}
	
	/* 设置一个客户object */
	public void setCustomer(Customer customer) {
		this.customer = customer; 
	}
	
	/* 继承InitializingBean接口的afterPropertiesSet()方法 */
	
	/**
	 * 静态初始化:
	 * 静态初始化块码只有在类被类载入器载入时被执行, 此时并没有任何的
	 * 类的实例存在, 所以只能access到类级别的变量(static variable
	 * )而不能access到实例变量(instance variable).  实例变量只能
	 * 被类的实例access(object.instance_variable). 类变量/ 静态
	 * 变量可以被类名或类实例access(ClassName.static_variable)/
	 * (object.static_variable)
	 */
	
	/**
	 * 非静态初始化: 
	 * 非静态初始化代码块是当实例被构造但是properties还没有被注入时
	 * 执行的. 即非静态初始化代码块儿被实际复制到构造器中进行初始化.
	 */
	
	/**
	 * afterPropertiesSet和@PostConstruct注解的方法: 
	 * 两种方法都是在类实例已经被创建而且properties被设置完毕执行的.
	 * 比如可以提前载入一些该方法可以处理的数据, 因为所有的依赖已经建
	 * 立完毕. 如果有强制依赖则需要通过构造器注入建立依赖反之如果是选
	 * 择性依赖通过setter方法执行可以使用该方法, 因为选择性依赖不需要
	 * 在类载入的时候了解所有的依赖关系而是在类和关系被建立设定完毕之后
	 * 了解即可. 反之强制依赖在类构造的时候必须了解彼此的依赖关系. 
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet(): bean initialization here");
	}
	
	/**
	 * 继承callback接口DisposableBean的destroy()方法实现callback
	 * 通知(当前正在使用资源的实例在被容器移除的过程中). 
	 */
	@Override 
	public void destroy() throws Exception {
		System.out.println("destroy(): bean destruction here"); 
	}
	
	/* 用户定义的初始化bean方法 */
	public void customInitBean() throws Exception {
		System.out.println("customInitBean()");
	}
	
	/* 用户定义的回收bean方法 */
	public void customDestroyBean() throws Exception {
		System.out.println("customDestroyBean()"); 
	}
	
	/* 注解方法: 同InitializingBean相同工作原理 */
	@PostConstruct 
	public void postConstruct() {
		System.out.println("postConstruct(): perform some initialization after all the setter "
				+ "methods have been called");
	}
	
	/* 注解方法: 同DisposableBean相同工作原理 */
	@PreDestroy
	public void predestroy() {
		System.out.println("predestroy()"); 
	}
}
