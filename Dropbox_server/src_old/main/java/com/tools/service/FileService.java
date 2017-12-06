package com.tools.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.tools.entity.user_files;
import com.tools.repository.FileRepository;
import com.tools.requestparams.FilesParams;
import com.tools.requestparams.GetAllFiles;

@Service
public class FileService {
	
	@Autowired
	private FileRepository fileRepository ; 
	
	public List<user_files> getAllFiles(GetAllFiles readParams){
		return  fileRepository.findByEmailAndDirectoryAndIsdeleted(readParams.getEmail(), readParams.getDirectory() , 0 ) ; 
	}
	
	public List<user_files> getAllRecentFiles(String email){
		return fileRepository.findByEmailAndIsdeleted(email ,0 ,  new PageRequest(0, 5 )) ; 
	}
	
	public boolean deleteFile(FilesParams params) {
		long deleteStatus =  fileRepository.deleteByEmailAndDirectoryAndFilename(params.getEmail(), params.getDirectory(), params.getFilename());
		if(deleteStatus == 1 ) return true ; else return false ; 
	}
	
	
	public List<user_files> readStarredFiles(FilesParams params) {
		return   fileRepository.findByEmailAndIsdeletedAndStarred(params.getEmail(), 0 , 1 ) ; 
	}
	
	public boolean unStarFile(FilesParams params) {
		
		List <user_files> userFile = fileRepository.findByEmailAndIsdeletedAndDirectoryAndFilename(params.getEmail(), 0, params.getDirectory(), params.getFilename());
		if(userFile.size() == 1) {
			Optional<user_files> file = userFile.stream().findAny() ;
			
			if(file.isPresent()) {
				user_files fileToUpdate = file.get() ;
				fileToUpdate.setStarred(0);
				user_files savedFile = fileRepository.save(fileToUpdate);
				return savedFile.getStarred() == 0 ? true : false ;
			}
		}
		
		return false ;
	}

	public boolean starFile(FilesParams params) {
		List <user_files> userFile = fileRepository.findByEmailAndIsdeletedAndDirectoryAndFilename(params.getEmail(), 0, params.getDirectory(), params.getFilename());
		if(userFile.size() == 1) {
			Optional<user_files> file = userFile.stream().findAny() ;
			
			if(file.isPresent()) {
				user_files fileToUpdate = file.get() ;
				fileToUpdate.setStarred(1);
				user_files savedFile = fileRepository.save(fileToUpdate);
				return savedFile.getStarred() == 1 ? true : false ;
			}
		}
		
		return false ;
	}
	
}
