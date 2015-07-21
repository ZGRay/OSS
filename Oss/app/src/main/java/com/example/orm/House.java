package com.example.orm;

import com.oss.common.db.anotation.Id;

import java.io.Serializable;

public class House implements Serializable {
	public String tName ="王老师";
	@Id
	public String name ="别墅";

	public House(String tName, String name) {
		this.tName = tName;
		this.name = name;
	}
	public House() {
	}

	@Override
	public String toString() {
		return "House{" +
				"tName='" + tName + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
