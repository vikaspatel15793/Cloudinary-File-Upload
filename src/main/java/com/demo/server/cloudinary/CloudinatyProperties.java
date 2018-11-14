package com.demo.server.cloudinary;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cloudinary", ignoreUnknownFields = false)
public class CloudinatyProperties {

	private String cloudName;

	private String apiSecret;

	private String apiKey;

	private String enhanceImageTag;

	private String staticImageSupport;

	private String rootFolder;

	public String getCloudName() {
		return cloudName;
	}

	public void setCloudName(String cloudName) {
		this.cloudName = cloudName;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getEnhanceImageTag() {
		return enhanceImageTag;
	}

	public void setEnhanceImageTag(String enhanceImageTag) {
		this.enhanceImageTag = enhanceImageTag;
	}

	public String getStaticImageSupport() {
		return staticImageSupport;
	}

	public void setStaticImageSupport(String staticImageSupport) {
		this.staticImageSupport = staticImageSupport;
	}

	public String getRootFolder() {
		return rootFolder;
	}

	public void setRootFolder(String rootFolder) {
		this.rootFolder = rootFolder;
	}

}
