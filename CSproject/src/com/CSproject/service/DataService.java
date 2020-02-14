package com.CSproject.service;

import java.util.List;

public class DataService {
	private static DataService _instance;
	
	public List<String> input;
	
	private String inputFile,outputFile;
	
	private DataService(){
		
	}
	
	public static synchronized DataService getInstance(){
		if(_instance == null)
			_instance = new DataService();
		
		return _instance;
	}
	
	public void setData(List<String> input){
	}
	
	public void setFileNames(String input,String output){
		this.inputFile = input;
		this.outputFile = output;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	
}