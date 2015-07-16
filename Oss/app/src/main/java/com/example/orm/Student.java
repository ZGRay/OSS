package com.example.orm;

import com.oss.common.db.anotation.Id;

public class Student {
	public String tName ="王老师";
	@Id
	public String name ="zhangsan";
	public int age = 13;

	public Student(String tName, String name, int age) {
		this.tName = tName;
		this.name = name;
		this.age = age;
	}
	public Student() {
	}

	@Override
	public String toString() {
		return "Student{" +
				"tName='" + tName + '\'' +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
