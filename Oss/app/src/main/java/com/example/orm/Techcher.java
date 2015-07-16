package com.example.orm;

import java.util.ArrayList;

import com.oss.common.db.anotation.Id;
import com.oss.common.db.anotation.One2Many;
import com.oss.common.db.anotation.One2One;

public class Techcher {
	@Id
	public String name = "dddd";
	public int age = 22;
	@One2One(referencedColumnName = "tName")
	public House hose;
	@One2Many(referencedColumnName = "tName")
	public ArrayList<Student> lists;
	public String sex;
	public String height;

	public Techcher() {
	}

	public Techcher(String name, int age, House hose, ArrayList<Student> lists) {
		this.name = name;
		this.age = age;
		this.hose = hose;
		this.lists = lists;
	}

	@Override
	public String toString() {
		return "Techcher{" +
				"name='" + name + '\'' +
				", age=" + age +
				", hose=" + hose +
				", lists=" + lists +
				", sex='" + sex + '\'' +
				", height='" + height + '\'' +
				'}';
	}
}
