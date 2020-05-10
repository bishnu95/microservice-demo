package com.amdocs.media.assignement.service.remote.domain;

import java.io.Serializable;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class ServiceResponseContext implements Serializable {

	private static final long serialVersionUID = 1L;

	private HttpHeaders headers = null;

	private Object body;

	private HttpStatus httpStatus;

	public HttpHeaders getHeaders() {
		return headers;
	}

	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public String toString() {
		return "ServiceResponseContext [headers=" + headers + ", body=" + body + ", httpStatus=" + httpStatus + "]";
	}

}
