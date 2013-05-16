package com.wuzhupc.mobile.test;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import com.wuzhupc.mobile.json.MobileJsonHandler;
import com.wuzhupc.mobile.vo.MobileRequestVO;

public class TestJsonVersion extends TestJsonCase
{
	@Test
	public void testcheckClientVer() throws IOException {
		System.out.println(super.analyseEx("D:\\workspace\\MobileJsonTest\\test\\mobilever_checkClientVer_data.json"));
	}
	
	@Test
	public void testCheckVer()
	{
		String conentString="{    \"id\":\"android34532313413\",    \"command\":\"mobilever_checkClientVer\",    \"params\":{        \"devid\":\"85673678399121\",        \"clientver\":\"1.01\"    }}";
		MobileRequestVO vo=MobileJsonHandler.parseMobileRequest(conentString);
		String resultString="command:"+vo.getCommand()+"\nID:"+vo.getId()+"\n Params:";
		System.out.println(resultString);
		for (Map.Entry<String, String> entry : vo.getParams().entrySet())
		{
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
}
