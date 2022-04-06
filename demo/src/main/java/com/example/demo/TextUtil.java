package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;

@Component
public class TextUtil {

//	@Value("${file.upload.url}")
	private final static String uploadFilePath = "C:\\Users\\jacks\\Documents\\";


	public static String getRecordFilePath() {
		return uploadFilePath;
	}

	@SneakyThrows
	public static void write(String filename, String content) {
		FileWriter fw = new FileWriter(getRecordFilePath() + filename + ".txt");
		fw.write(content);
		fw.flush();
		fw.close();
	}

	@SneakyThrows
	public static String read(String filename) {
		FileReader fr = new FileReader(getRecordFilePath() + filename);
		BufferedReader br = new BufferedReader(fr);
		String str = "";
		while (br.ready()) {
			str += br.readLine() + "\n";
			System.out.println(str);
		}
		fr.close();
		return str;
	}

}