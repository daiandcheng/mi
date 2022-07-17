package com.edu.util;

import java.util.UUID;

public class UUIDUtil {
	public static void main(String[] args) {
		System.out.println(getId());
		System.out.println(getCode());
	}
	public static String getId() {
		return UUID.randomUUID().toString().replace("-", "") ;
	}
	public static String getCode() {
		return getId();
	}
}
