package com.amdocs.media.assignement.service.remote.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class ServiceRequestContext implements Serializable {

	private static final long serialVersionUID = 1L;

	private String url;

	private String method;

	private HttpHeaders headers = null;

	private Map<String, String> pathParams = null;

	private MultiValueMap<String, String> queryParams = null;

	private Object body;

	public ServiceRequestContext(HttpHeaders headers, Map<String, String> pathParams,
			MultiValueMap<String, String> queryParams, Object body) {
		this.headers = headers;
		this.pathParams = pathParams;
		this.queryParams = queryParams;
		this.body = body;
	}

	public ServiceRequestContext() {
		this.headers = new HttpHeaders();
		this.pathParams = new LinkedHashMap<>();
		this.queryParams = new LinkedMultiValueMap<>();
	}

	public ServiceRequestContext(HttpHeaders headers, Map<String, String> pathParams,
			MultiValueMap<String, String> queryParams) {
		this(headers, pathParams, queryParams, null);
	}

	public String getUrl() {
		return this.url;
	}

	public ServiceRequestContext setUrl(String url) {
		this.url = url;
		return this;
	}

	public Object getBody() {
		return body;
	}

	public ServiceRequestContext setBody(Object body) {
		this.body = body;
		return this;
	}

	public String getMethod() {
		return method;
	}

	public ServiceRequestContext setMethod(String method) {
		this.method = method;
		return this;
	}

	public HttpHeaders getHttpHeaders() {
		return this.headers;
	}

	public List<String> getHttpHeader(String key) {
		return this.headers.get(key);
	}

	public ServiceRequestContext addHttpHeader(String key, String value) {

		List<String> list = this.headers.get(key);
		if (list == null) {
			list = new ArrayList<>();
			this.headers.put(key, list);
		}
		list.add(value);

		return this;
	}

	public ServiceRequestContext addHttpHeader(String key, List<String> values) {
		this.headers.put(key, values);
		return this;
	}

	public ServiceRequestContext addHttpHeaders(HttpHeaders header) {
		this.headers.putAll(header);
		return this;
	}

	public ServiceRequestContext removeHeader(String key) {
		this.headers.remove(key);
		return this;
	}

	public Map<String, String> getPathParams() {
		return this.pathParams;
	}

	public String getPathParam(String key) {
		return this.pathParams.get(key);
	}

	public ServiceRequestContext addPathParam(String key, String value) {
		this.pathParams.put(key, value);
		return this;
	}

	public ServiceRequestContext addPathParams(Map<String, String> pathParam) {
		this.pathParams.putAll(pathParam);
		return this;
	}

	public ServiceRequestContext removePathParam(String key) {
		this.pathParams.remove(key);
		return this;
	}

	public MultiValueMap<String, String> getQueryParams() {
		return this.queryParams;
	}

	public List<String> getQueryParam(String key) {
		return this.queryParams.get(key);
	}

	public ServiceRequestContext addQueryParam(String key, String value) {
		List<String> list = this.queryParams.get(key);
		if (list == null) {
			list = new ArrayList<>();
			this.queryParams.put(key, list);
		}
		list.add(value);
		return this;
	}

	public ServiceRequestContext addQueryParam(String key, List<String> values) {
		this.queryParams.put(key, values);
		return this;
	}

	public ServiceRequestContext addQueryParams(MultiValueMap<String, String> qryParams) {
		this.queryParams.putAll(qryParams);
		return this;
	}

	public ServiceRequestContext removeQueryParam(String key) {
		this.queryParams.remove(key);
		return this;
	}

	@Override
	public String toString() {
		return "ServiceRequestContext [url=" + url + ", headers=" + headers + ", pathParams=" + pathParams
				+ ", queryParams=" + queryParams + ", body=" + body + "]";
	}

}
