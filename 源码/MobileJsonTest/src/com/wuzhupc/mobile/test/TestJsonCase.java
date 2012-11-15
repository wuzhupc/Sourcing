package com.wuzhupc.mobile.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import com.wuzhupc.mobile.json.MobileJsonHandler;
import com.wuzhupc.mobile.vo.MobileRequestVO;


public class TestJsonCase
{
	protected String  analyse(String jsonpath) throws IOException
	{
		StringBuilder contents=new StringBuilder();
		String line=null;
		FileReader reader=new FileReader(jsonpath);
		BufferedReader input=new BufferedReader(reader);
		while((line=input.readLine())!=null)
		{
			contents.append(line.trim()+"\n");
		}
		System.out.println(contents);
		MobileRequestVO vo=MobileJsonHandler.parseMobileRequest(contents.toString());
		return vo.getCommand();
	}
	
	protected String  analyseEx(String jsonpath) throws FileNotFoundException 
	{		
		InputStream stream=new FileInputStream(jsonpath);
		MobileRequestVO vo=MobileJsonHandler.parseMobileRequest(stream);
		return vo.getCommand();
	}
}
