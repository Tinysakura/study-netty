package com.studynetty.serialization.jdk;

import java.io.Serializable;

/**
 * œÏ”¶¿‡
 * @author Administrator
 *
 */
public class Response implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int responseId;
	private int requestId;
	private String ResponseContent;
	
	public int getResponseId() {
		return responseId;
	}
	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getResponseContent() {
		return ResponseContent;
	}
	public void setResponseContent(String responseContent) {
		ResponseContent = responseContent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
