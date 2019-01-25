package com.honglin_chen.spring_bean_lifecycle.bean;

public class Customer {
	/* 定义Customer的成员变量 */
	private String name; 
	
	/* 定义getter方法得到客户的名字 */
	public String getName() {
		return name;
	}
	
	/* 定义setter方法设置客户的名字 */ 
	public void setName(String name) {
		this.name = name; 
	}
}
