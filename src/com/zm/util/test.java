package com.zm.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import com.google.zxing.WriterException;

public class test {
	public static void main(String[] args) throws FileNotFoundException, WriterException, IOException {
		//BarCode.encode("jy12345678", "C:\\Users\\Administrator\\Desktop\\qrcode\\x.png", 400, 100, 20);
		System.out.println(UUID.randomUUID().toString());
	}
}
