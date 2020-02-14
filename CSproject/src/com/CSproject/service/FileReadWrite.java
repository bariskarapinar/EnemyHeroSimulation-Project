package com.CSproject.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import com.CSproject.app.App;

public class FileReadWrite {
	String fileNameWithPath;
	String invalidMessage = "Dosya geçerli degil";
	String fileNotFoundMessage = invalidMessage + " :Dosya bulunamadi";
	InputFile logic;

	public FileReadWrite(String filePath) {
		this.fileNameWithPath = filePath;
		this.logic = new InputFile();
	}

	public void read() {
		try {
			File f = new File("src/xx.txt");
			App.Log(f.getAbsolutePath());
		} catch (Exception e) {
			// TODO: handle exception
			App.Log(e.getMessage());
		}
	}

	public void readFromSpecifiedFile() throws IOException {
		String info = "Input dosyasini girin: (C:/input1.txt)";
		String inputFile = logic.getInput(info);
		if (inputFile.trim() == "") {
			App.Log(invalidMessage);
			return;
		}
		if (!new File(inputFile).exists())
			App.Log(fileNotFoundMessage + inputFile);

		this.fileNameWithPath = inputFile;
		readSmall();
	}

	public void writeToSpecifiedFile() throws IOException {
		String info = "Output dosyasini girin: (C:/output.txt)";
		String outputFile = logic.getInput(info);
		if (outputFile.trim() == "") {
			App.Log(invalidMessage);
			return;
		}

		if (!new File(outputFile).exists())
			App.Log(fileNotFoundMessage + " : " + outputFile + "Bu isimde yeni bir dosya olusturulacak");

		App.Log("");

		this.fileNameWithPath = outputFile;
		Path path = Paths.get(outputFile);
		write(path, getSampleData());

	}
	
	public void writeOutput(String outputFile,List<String> outputData){
		if(outputData == null)
			outputData = getSampleData();
		
		Path path = Paths.get(outputFile);
		write(path,outputData);
	}

	public void readSmall() throws IOException {
		Path path = Paths.get(this.fileNameWithPath);
		List<String> lines = Files.readAllLines(path);
		for (String string : lines) {
			App.Log(string);
		}
	}

	public void writeSmall() throws IOException {
		Path path = Paths.get(this.fileNameWithPath);

		write(path, getSampleData());
	}

	public List<String> getInputFile(String fileName) throws FileNotFoundException {
		Path path = Paths.get(fileName);
		List<String> inputData = null;
		try {
			inputData = read(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return inputData;
	}
	
	Boolean fileExists(String fileName){
		return new File(fileName).exists();
	}

	public List<String> read(Path path) throws IOException {
		return Files.readAllLines(path);
	}
	
	void write(Path path, List<String> data) {
		try {
			Files.write(path, data, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	List<String> getSampleData() {
		List<String> lines = new ArrayList<String>();
		lines.add("OK");
		return lines;
	}

}
