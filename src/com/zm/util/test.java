package com.zm.util;

import java.util.UUID;

public class test {
	public static void main(String[] args) {
		for (int i =10; i < 100; i++) {
			UUID uuid = UUID.randomUUID();
			System.out.println("('"+uuid+"'),");
		}
	}
}