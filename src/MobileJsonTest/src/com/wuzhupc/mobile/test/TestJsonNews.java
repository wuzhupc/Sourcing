package com.wuzhupc.mobile.test;

import java.util.ArrayList;

import org.junit.Test;

import com.wuzhupc.mobile.interoperate.JsonMarshal;
import com.wuzhupc.mobile.interoperate.SchemaName;
import com.wuzhupc.mobile.test.vo.NewsVO;

public class TestJsonNews extends TestJsonCase
{
	@Test
	public void testNews() {
		ArrayList<NewsVO> data=new ArrayList<NewsVO>();
		NewsVO vo1= new NewsVO();
		vo1.setHeadline(true);
		vo1.setNewsid(3456l);
		vo1.setNewssummary("������ϢժҪ");
		vo1.setNewstype(1);
		vo1.setTitle("���ű���");
		vo1.setTitlepic("http:\\test.com\test.jpg");
		data.add(vo1);
		NewsVO vo2= new NewsVO();
		vo2.setHeadline(false);
		vo2.setNewsid(34562);
		vo2.setNewssummary("������ϢժҪ2");
		vo2.setNewstype(1);
		vo2.setTitle("���ű���2");
		vo2.setTitlepic("http:\\test.com\test2.jpg");
		data.add(vo2);
		JsonMarshal marshal = new JsonMarshal("0000", SchemaName.RET_CODE_SUCESS, "�ɹ���ȡ������Ϣ");
		marshal.add(SchemaName.ELEMENT_NAME_LIST, data);
		System.out.println(marshal.toJson());
	}
}
