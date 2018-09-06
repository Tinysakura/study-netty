package com.studynetty.serialization.jdk;

import java.io.Serializable;

/**
 * «Î«Û¿‡
 * @author Administrator
 *
 */
public class Request implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int requestId;
	private String requestContent;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}
}
