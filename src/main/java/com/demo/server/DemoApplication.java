package com.demo.server;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.server.cloudinary.CloudinatyProperties;
import com.demo.server.cloudinary.UsersProfileCloudinaryImageService;

@RestController
@EnableConfigurationProperties(CloudinatyProperties.class)
@SpringBootApplication
public class DemoApplication {

	@Autowired
	private UsersProfileCloudinaryImageService cloudinaryService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostMapping("/uploadprofile")
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam("file") MultipartFile multipart)
			throws URISyntaxException, IllegalStateException, IOException {
		File file = null;
		try {
			file = this.multipartToFile(multipart);
			cloudinaryService.uploadProfileImage(file, "12345");
		} finally {
			if (file != null && file.exists()) {
				FileUtils.forceDelete(file);
			}
		}

		return ResponseEntity.ok().build();
	}

	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		File profileImage = new File(System.getProperty("java.io.tmpdir") + File.separator + "profile.jpg");
		multipart.transferTo(profileImage);
		return profileImage;
	}
}
