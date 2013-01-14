package com.wuzhupc.Sourcing.vo;

import android.content.Context;

import com.wuzhupc.services.MobileInfoService;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.utils.JavaLangUtil;
import com.wuzhupc.utils.json.JsonParser;

/**
 * 培训机构
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午7:45:06
 */
public class TrainVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9193391814832088770L;
	/**
	 * 培训机构编号
	 */
	//private long trainid;
	/**
	 * 培训机构名称
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
	 * 返回详情HTML标题
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
	 * 生成分享信息内容部分
	 */
	@Override
	public String generateShareText()
	{
		return getTrainname();
	}
	
	/**
	 * 返回详情HTML子标题
	 * @return
	 */
	@Override
	public String getHtmlSubTitle()
	{
		String result = "<div style=\"height:0;border-bottom:1px solid #f00\"></div>";
		return result;
	}
	
	/**
	 * 设置内容Html内容显示
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
