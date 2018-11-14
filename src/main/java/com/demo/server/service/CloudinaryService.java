package com.demo.server.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;

@Component
public class CloudinaryService {

	private Cloudinary cloudinary;

	@Autowired
	public CloudinaryService(@Value("${cloudinary.apikey}") String key,
			@Value("${cloudinary.apisecret}") String secret,
			@Value("${cloudinary.cloudname}") String cloud) {
		cloudinary = Singleton.getCloudinary();
		cloudinary.config.cloudName = cloud;
		cloudinary.config.apiSecret = secret;
		cloudinary.config.apiKey = key;
	}

	public String createUrl(String name, String action) {
		return cloudinary.url().imageTag(name);
	}
	
	public String createProfileImageUrl(String userUUID) {
		return cloudinary.url().imageTag("profileimages"+"/"+userUUID);
	}
	
	public Map uploadProfileImage(File file,String userUUID){
        try{
        	Map options = ObjectUtils.asMap(
        			"use_filename", true);
        			//"eager_async", true,
        			//"eager_notification_url", "http://mysite/my_notification_endpoint");
            return cloudinary.uploader().upload(file, options);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static void main(String[] args) {
		CloudinaryService service = new CloudinaryService("724649914971376", "zQHLR7qCL54Tsv48pPz1fJqCF7I", "dfaejuhyn");
		System.out.println(service.uploadProfileImage(new File("/Users/vikas/Downloads/arrow_left.png"), "123"));
		
	}

}
