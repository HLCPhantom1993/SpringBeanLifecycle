package com.honglin_chen.spring_bean_lifecycle;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.honglin_chen.spring_bean_lifecycle.service.CustomerServiceImpAwareInterface;
import com.honglin_chen.spring_bean_lifecycle.service.CustomerServiceImpCustomMethod; 
import com.honglin_chen.spring_bean_lifecycle.service.CustomerServiceImpAwareInterface; 

@SpringBootApplication
public class SpringBeanLifeCycleApplication {

	public static void main(String[] args) {
		/* 读取构造资源文件 */
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean_imp_custom_method.xml"); 
		System.out.println("Context has been initialized"); 
		
		/* 通过context先载入构造文件xml然后构造构造文件持有的所有bean定义 */ 
		CustomerServiceImpCustomMethod service = (CustomerServiceImpCustomMethod) context.getBean(
				"customerServiceCustomMethod");
		System.out.println("Already retrieved Bean from context. Next, show bean data"); 
		System.out.println("Customer name: " + service.getCustomer().getName()); 
		/* 关闭当前文本 */
		context.close(); 
		
		System.out.println("-------New Context------"); 
		System.out.println("-------Spring Aware Interface------"); 
		
		context = new ClassPathXmlApplicationContext("bean_imp_aware.xml"); 
		/* 通过文本构造bean定义, 因为我们在xml文件中定义bean的构造 */ 
		context.getBean("customerServiceAware", CustomerServiceImpAwareInterface.class); 
		context.close();
	}
}

