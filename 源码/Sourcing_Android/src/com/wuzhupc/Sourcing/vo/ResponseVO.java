package com.wuzhupc.Sourcing.vo;

public class ResponseVO 
{
	public static final int RESPONSE_CODE_FAIL = 1;
	public static final int RESPONSE_CODE_SUCESS = 0;
	
	private int code;	// ���ر���״̬�룺1.����  2.������
	private String msg;	// ���ر���״̬��Ϣ��
	
	public int getCode(){
		return code;
	}
	public void setCode(int code){
		this.code = code;
	}
	public String getMsg(){
		return msg;
	}
	public void setMsg(String msg){
		this.msg = msg;
	}
	
	public boolean isSucess()
	{
		return this.code==RESPONSE_CODE_SUCESS;
	}
}
