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
		vo1.setNewssummary("新闻信息摘要");
		vo1.setNewstype(1);
		vo1.setTitle("新闻标题");
		vo1.setTitlepic("http:\\test.com\test.jpg");
		data.add(vo1);
		NewsVO vo2= new NewsVO();
		vo2.setHeadline(false);
		vo2.setNewsid(34562);
		vo2.setNewssummary("新闻信息摘要2");
		vo2.setNewstype(1);
		vo2.setTitle("新闻标题2");
		vo2.setTitlepic("http:\\test.com\test2.jpg");
		data.add(vo2);
		JsonMarshal marshal = new JsonMarshal("0000", SchemaName.RET_CODE_SUCESS, "成功获取新闻信息");
		marshal.add(SchemaName.ELEMENT_NAME_LIST, data);
		System.out.println(marshal.toJson());
	}
}
