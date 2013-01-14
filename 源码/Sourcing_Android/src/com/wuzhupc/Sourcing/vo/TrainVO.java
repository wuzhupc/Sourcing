package com.wuzhupc.Sourcing.vo;

import android.content.Context;

import com.wuzhupc.services.MobileInfoService;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.utils.JavaLangUtil;
import com.wuzhupc.utils.json.JsonParser;

/**
 * ��ѵ����
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����7:45:06
 */
public class TrainVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9193391814832088770L;
	/**
	 * ��ѵ�������
	 */
	//private long trainid;
	/**
	 * ��ѵ��������
	 */
	private String trainname;
	public long getTrainid()
	{
		return id;
	}
	public void setTrainid(long trainid)
	{
		this.id = trainid;
	}
	public void setTrainid(String trainid)
	{
		setTrainid(JavaLangUtil.StrToLong(trainid,-1l));
	}
	public String getTrainname()
	{
		return trainname;
	}
	public void setTrainname(String trainname)
	{
		this.trainname = trainname;
	}
	
	/**
	 * ��������HTML����
	 * @return
	 */
	@Override
	public String getHtmlTitle()
	{
		String result = "<br/><div align=\"center\"><font color=\"#111111\" size=\"4pt\"><strong>"
				+getTrainname()
				+"</strong></font></div><br/>";
		return result;
	}

	/**
	 * ���ɷ�����Ϣ���ݲ���
	 */
	@Override
	public String generateShareText()
	{
		return getTrainname();
	}
	
	/**
	 * ��������HTML�ӱ���
	 * @return
	 */
	@Override
	public String getHtmlSubTitle()
	{
		String result = "<div style=\"height:0;border-bottom:1px solid #f00\"></div>";
		return result;
	}
	
	/**
	 * ��������Html������ʾ
	 */
	@Override
	public void setHtmlToShow(Context c, final DetailInfoListener detailInfoListener)
	{
		if(detailInfoListener==null)
			return;
		MobileInfoService infoService= new MobileInfoService(c);
		infoService.getTrainDetail(getTrainid(), new IBaseReceiver()
		{
			@Override
			public void receiveCompleted(boolean isSuc, String content)
			{
				if (!isSuc)
				{
					detailInfoListener.onError(content);
					return;
				}
				ResponseVO respVO = new ResponseVO();
				TrainDetailVO mDetailVO = (TrainDetailVO)JsonParser.parseJsonToEntity(content, respVO);
				if(respVO.getCode()!=ResponseVO.RESPONSE_CODE_SUCESS)
				{
					detailInfoListener.onError(respVO.getMsg());
					return;
				} 
				detailInfoListener.onComplete(mDetailVO);
			}
		});
	}
}
