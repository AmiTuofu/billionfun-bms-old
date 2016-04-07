package com.billionfun.bms.product.mall.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.commons.io.FileUtils;

public class CodeUtil {
	public static final String SOURCES_PATH = "/Users/zhuyi/Downloads/CodeCreate/Sources";
	public static final String TARGET_PATH = "/Users/zhuyi/Downloads/CodeCreate/Target";
	public static final String PROJECT_PAGE_PATH = "C:/Users/ZHUYI/Documents/Workspaces/www/WebRoot/page/";

	public static void main(String[] args) throws IOException {

		System.out.println("请输入module:");
		BufferedReader module_in = new BufferedReader(new InputStreamReader(
				System.in));
		String module = module_in.readLine();
		System.out.println("请输入数据Model:");
		BufferedReader dataModel_in = new BufferedReader(new InputStreamReader(
				System.in));
		String dataModel = dataModel_in.readLine();
		System.out.println("请输入数据Model别名:");
		BufferedReader dataModelRef_in = new BufferedReader(
				new InputStreamReader(System.in));
		String dataModelRef = dataModelRef_in.readLine();
		System.out.println("请输入数据Model名称(中文):");
		BufferedReader dataModelName_in = new BufferedReader(
				new InputStreamReader(System.in));
		String dataModelName = dataModelName_in.readLine();
		System.out.println("请输入数据Model的字段:");
		BufferedReader dataModel_fields = new BufferedReader(
				new InputStreamReader(System.in));

		System.out.println("Create Controller...");
		File sourcesFile = new File(SOURCES_PATH + "/Controller.java");
		File targetFile = new File(TARGET_PATH + "/" + dataModel
				+ "Controller.java");
		FileUtils.copyFile(sourcesFile, targetFile);
		replaceByString(targetFile, "Template", dataModel);
		replaceByString(targetFile, "template", dataModelRef);
		replaceByString(targetFile, "module", module);
		System.out.println("Create " + dataModel + "Controller.java"
				+ " Success");

		System.out.println("Create Dao...");
		sourcesFile = new File(SOURCES_PATH + "/Dao.java");
		targetFile = new File(TARGET_PATH + "/" + dataModel + "Dao.java");
		FileUtils.copyFile(sourcesFile, targetFile);
		replaceByString(targetFile, "Template", dataModel);
		replaceByString(targetFile, "template", dataModelRef);
		replaceByString(targetFile, "module", module);
		System.out.println("Create " + dataModel + "Dao.java" + " Success");

		System.out.println("Create DaoImpl...");
		sourcesFile = new File(SOURCES_PATH + "/DaoImpl.java");
		targetFile = new File(TARGET_PATH + "/" + dataModel + "DaoImpl.java");
		FileUtils.copyFile(sourcesFile, targetFile);
		replaceByString(targetFile, "Template", dataModel);
		replaceByString(targetFile, "template", dataModelRef);
		replaceByString(targetFile, "module", module);
		System.out.println("Create " + dataModel + "DaoImpl.java" + " Success");

		System.out.println("Create Service...");
		sourcesFile = new File(SOURCES_PATH + "/Service.java");
		targetFile = new File(TARGET_PATH + "/" + dataModel + "Service.java");
		FileUtils.copyFile(sourcesFile, targetFile);
		replaceByString(targetFile, "Template", dataModel);
		replaceByString(targetFile, "template", dataModelRef);
		replaceByString(targetFile, "module", module);
		System.out.println("Create " + dataModel + "Service.java" + " Success");

		System.out.println("Create ServiceImpl...");
		sourcesFile = new File(SOURCES_PATH + "/ServiceImpl.java");
		targetFile = new File(TARGET_PATH + "/" + dataModel
				+ "ServiceImpl.java");
		FileUtils.copyFile(sourcesFile, targetFile);
		replaceByString(targetFile, "Template", dataModel);
		replaceByString(targetFile, "template", dataModelRef);
		replaceByString(targetFile, "module", module);
		System.out.println("Create " + dataModel + "ServiceImpl.java"
				+ " Success");

		System.out.println("Create QueryJsp...");
		sourcesFile = new File(SOURCES_PATH + "/query.jsp");
		targetFile = new File(TARGET_PATH + "/query.jsp");
		FileUtils.copyFile(sourcesFile, targetFile);
		replaceByString(targetFile, "Template", dataModel);
		replaceByString(targetFile, "template", dataModelRef);
		replaceByString(targetFile, "module", module);
		replaceByString(targetFile, "模板", dataModelName);
		System.out.println("Create Query.jsp" + " Success");

		System.out.println("Create QueryJs...");
		sourcesFile = new File(SOURCES_PATH + "/query.js");
		targetFile = new File(TARGET_PATH + "/query.js");
		FileUtils.copyFile(sourcesFile, targetFile);
		replaceByString(targetFile, "Template", dataModel);
		replaceByString(targetFile, "template", dataModelRef);
		replaceByString(targetFile, "module", module);
		replaceByString(targetFile, "模板", dataModelName);
		System.out.println("Create Query.js" + " Success");
	}

	public static void replaceByString(File file, String oldStr, String newStr) {
		try {
			String readStr = "";
			String read;

			try {
				InputStreamReader isr = new InputStreamReader(
						new FileInputStream(file), "UTF-8");
				BufferedReader bufread = new BufferedReader(isr);
				while ((read = bufread.readLine()) != null) {
					readStr = readStr + read + "\r\n";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			readStr = readStr.replaceAll(oldStr, newStr);
			OutputStreamWriter write = new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(readStr);
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getMod(String module) {
		return module.substring(0, 1).toUpperCase()
				+ module.substring(1, 3).toLowerCase();
	}
}
