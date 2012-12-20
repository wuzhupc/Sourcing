package com.wuzhupc.mobile.test;

import java.util.ArrayList;

import org.junit.Test;

import com.wuzhupc.mobile.interoperate.JsonMarshal;
import com.wuzhupc.mobile.interoperate.SchemaName;
import com.wuzhupc.mobile.test.vo.ConsultResultVO;
import com.wuzhupc.mobile.test.vo.ConsultVO;

public class TestJsonConsult extends TestJsonCase
{
	@Test
	public void testConsult() {
		ArrayList<ConsultVO> data=new ArrayList<ConsultVO>();
		ConsultVO vo1= new ConsultVO();
		vo1.setConsultcontent("consultcontent");
		vo1.setConsultid(1l);
		vo1.setPublishtime("2012-12-20");
		data.add(vo1);
		ConsultVO vo2= new ConsultVO();
		vo2.setConsultcontent("consultcontent2");
		vo2.setConsultid(1l);
		vo2.setPublishtime("2012-12-21");
		ConsultResultVO resultVO = new ConsultResultVO();
		resultVO.setConsultresultcontent("consultresultcontent");
		resultVO.setConsultresultid(1l);
		resultVO.setPublishtime("2012-12-21");
		resultVO.setPublisher("publisher");
		vo2.setConsultResultVO(resultVO);
		data.add(vo2);
		JsonMarshal marshal = new JsonMarshal("0000", SchemaName.RET_CODE_SUCESS, "成功获取新闻信息");
		marshal.add(SchemaName.ELEMENT_NAME_LIST, data);
		System.out.println(marshal.toJson());
	}
}
