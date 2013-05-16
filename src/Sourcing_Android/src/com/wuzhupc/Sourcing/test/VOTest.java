package com.wuzhupc.Sourcing.test;

import junit.framework.Assert;

import com.wuzhupc.Sourcing.vo.BaseVO;
import com.wuzhupc.Sourcing.vo.NewsVO;
import com.wuzhupc.utils.SerializeUtil;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * VO相关单元测试
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-12-2 下午9:07:28
 */
public class VOTest extends AndroidTestCase
{
	protected static final String tag=VOTest.class.getSimpleName();
	
	public void testBaseVOEquals() throws Throwable
	{
		BaseVO bvo1 = new BaseVO();
		bvo1.setId(1l);
		BaseVO bvo2 = new BaseVO();
		bvo2.setId(1l);
		BaseVO bvo3 = new BaseVO();
		bvo3.setId(2l);
		Assert.assertFalse(bvo1.equals(bvo3));
		Assert.assertTrue(bvo1.equals(bvo2));
		NewsVO nvo1 = new NewsVO();
		nvo1.setNewsid(1l);
		NewsVO nvo2 = new NewsVO();
		nvo2.setNewsid(1l);
		Assert.assertTrue(nvo1.equals(nvo2));
		Assert.assertFalse(bvo1.equals(nvo1));
	}
	
	public void testVOToString() throws Throwable
	{
		BaseVO bvo1 = new BaseVO();
		bvo1.setId(1l);
		NewsVO nvo1 = new NewsVO();
		nvo1.setNewsid(2l);
		nvo1.setAuther("\\n中文\\n序列化");
		Log.d(tag, bvo1.toString());
		Log.d(tag, nvo1.toString());
		BaseVO resultvo = (BaseVO)SerializeUtil.getObjectFromString(nvo1.toString());
		if(resultvo instanceof NewsVO)
		{
			assertEquals(2l, ((NewsVO)resultvo).getNewsid());
			Log.d(tag,"id:"+ ((NewsVO)resultvo).getAuther());
		}
		Log.d(tag,"id:"+ resultvo.getId());
	}
}
