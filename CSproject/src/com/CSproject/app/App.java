package com.CSproject.app;

import java.io.FileNotFoundException;
import java.util.List;

import com.CSproject.models.ErrorType;
import com.CSproject.service.*;;

public class App {
	public static void main(String[] args) {

		// input alma, uygulamayı başlatma
		HandleInput();
		Simulation logic = new Simulation(Parser.getInstance().parsedData.hero);
		logic.start();
	}

	public static void HandleInput() {
		String inputFileMessage = "Input dosyasini girin: (C:/input1.txt)";
		String outputFileMessage = "Output dosyasini girin: (C:/output.txt)";
		InputFile input = new InputFile();
		String inputFileName = input.getInput(inputFileMessage);
		String outputFileName = input.getInput(outputFileMessage);
		if (inputFileName.trim().equals("") || outputFileName.trim().equals(""))
			LogService.getInstance().logError(ErrorType.NULLINPUTDATA);
		DataService.getInstance().setFileNames(inputFileName, outputFileName);

		// input string okuma ve parse etme
		FileReadWrite fileLogic = new FileReadWrite("");
		List<String> inputData = null;
		try {
			inputData = fileLogic.getInputFile(inputFileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (inputData == null) {
			LogService.getInstance().logError(ErrorType.NULLINPUTDATA);
			return; 
		}
		
		Parser.getInstance().parseWholeFile(inputData);
		
	}

	public static void Log(String message) {
		System.out.println(message);
	}
}
