package com.wuzhupc.mobile.vo;

import java.util.HashMap;
import java.util.Map;

public class MobileRequestVO
{
	private String id;
	private String command;
	private Map<String, String> params = new HashMap<String, String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public void addParam(String name, String value) {
		this.params.put(name, value);
	}

	public String getParam(String name) {
		return this.params.get(name);
	}

	public Map<String, String> getParams() {
		return this.params;
	}
}
