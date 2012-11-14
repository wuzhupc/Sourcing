package com.wuzhupc.mobile.test;

import java.io.IOException;

import org.junit.Test;

public class TestJsonVersion extends TestJsonCase
{
	@Test
	public void testcheckClientVer() throws IOException {
		System.out.println(super.analyseEx("F:\\workspace\\MobileJson\\test\\mobilever_checkClientVer_data.json"));
	}
}
