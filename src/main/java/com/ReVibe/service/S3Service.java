package com.ReVibe.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Service {

//	@Autowired
//	AmazonS3 s3Client;
//	
//	private File convertMultipartFileToFile(MultipartFile file) {
//        File convertedFile = new File(file.getOriginalFilename());
//        
//        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
//            fos.write(file.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        
//        return convertedFile;
//    }
//	
//	public String uploadFile(MultipartFile file) {
//		
//		File fileObject = convertMultipartFileToFile(file);
//		
//		String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//		
//		s3Client.putObject( new PutObjectRequest("{s3 name}", filename, fileObject) );
//	    
//		fileObject.delete();
//
//		return "{s3 url}" + filename;
//	}
}
