package com.demo.server.cloudinary;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;

/**
 * This class will help us to upload and retrieve user profile image from the
 * cloudinary. We are following below patterns to upload profile.
 * /demo/users/{userUuid}/profile.jpg
 * 
 * @author vpatel
 *
 */
@Service
public class UsersProfileCloudinaryImageService {

	private CloudinatyProperties cloudinaryProperties;

	private Cloudinary cloudinary;

	public UsersProfileCloudinaryImageService(CloudinatyProperties cloudinaryProperties) {
		this.cloudinaryProperties = cloudinaryProperties;
		cloudinary = Singleton.getCloudinary();
		cloudinary.config.cloudName = cloudinaryProperties.getCloudName();
		cloudinary.config.apiSecret = cloudinaryProperties.getApiSecret();
		cloudinary.config.apiKey = cloudinaryProperties.getApiKey();
	}

	public String folder(String userUUID) {
		return cloudinaryProperties.getRootFolder() + "/users/" + userUUID + "/profile";
	}

	@SuppressWarnings({ "rawtypes" })
	public Map uploadProfileImage(File file, String userUUID) throws IOException {
		Map options = ObjectUtils.asMap("public_id", folder(userUUID), "use_filename", true,
				"unique_filename", false);
		return cloudinary.uploader().upload(file, options);
	}

}
