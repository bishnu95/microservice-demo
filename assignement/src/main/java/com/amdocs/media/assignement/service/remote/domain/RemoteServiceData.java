package com.amdocs.media.assignement.service.remote.domain;

import java.io.Serializable;

public class RemoteServiceData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String version;

	private String operation;

	private ServiceRequestContext serviceRequestContext = new ServiceRequestContext();

	private ServiceResponseContext serviceResponseContext = new ServiceResponseContext();

	private boolean fallbackFlag = false;

	private Throwable throwable = null;

	public String getName() {
		return name;
	}

	public RemoteServiceData setName(String name) {
		this.name = name;
		return this;
	}

	public String getVersion() {
		return version;
	}

	public RemoteServiceData setVersion(String version) {
		this.version = version;
		return this;
	}

	public String getOperation() {
		return operation;
	}

	public RemoteServiceData setOperation(String operation) {
		this.operation = operation;
		return this;
	}

	public ServiceRequestContext getServiceRequestContext() {
		return serviceRequestContext;
	}

	public RemoteServiceData setServiceRequestContext(ServiceRequestContext serviceRequestContext) {
		this.serviceRequestContext = serviceRequestContext;
		return this;
	}

	public ServiceResponseContext getServiceResponseContext() {
		return serviceResponseContext;
	}

	public RemoteServiceData setServiceResponseContext(ServiceResponseContext serviceResponseContext) {
		this.serviceResponseContext = serviceResponseContext;
		return this;
	}

	public boolean isFallbackFlag() {
		return fallbackFlag;
	}

	public RemoteServiceData setFallbackFlag(boolean fallbackFlag) {
		this.fallbackFlag = fallbackFlag;
		return this;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public RemoteServiceData setThrowable(Throwable throwable) {
		this.throwable = throwable;
		return this;
	}

	@Override
	public String toString() {
		return "RemoteServiceData [name=" + name + ", version=" + version + ", operation=" + operation
				+ ", serviceRequestContext=" + serviceRequestContext + ", serviceResponseContext="
				+ serviceResponseContext + ", fallbackFlag=" + fallbackFlag + ", throwable=" + throwable + "]";
	}

}
