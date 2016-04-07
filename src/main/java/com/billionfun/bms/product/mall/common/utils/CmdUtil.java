package com.billionfun.bms.product.mall.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CmdUtil {
	private static final Logger logger = LoggerFactory.getLogger(CmdUtil.class);

	public static void exec(String cmd) {
		try {
			String commands = cmd;
			Process process = Runtime.getRuntime().exec(commands);
			InputStreamReader ir = new InputStreamReader(
					process.getInputStream());
			BufferedReader input = new BufferedReader(ir);
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
		} catch (java.io.IOException e) {
			System.err.println("IOException " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		CmdUtil.exec("");
	}
}
