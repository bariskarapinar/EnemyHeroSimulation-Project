package com.CSproject.service;

import java.lang.System;
import java.security.GeneralSecurityException;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

public class InputFile {
	
	public String getInput(String infoMessage) {
		//ne tür verilerin girilmesi gerektiği konusunda kullanıcıyı bilgilendir
		System.out.println(infoMessage);

		InputStreamReader streamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(streamReader);
		String input = "";
		try {
			input = bufferedReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return input;
	}
}