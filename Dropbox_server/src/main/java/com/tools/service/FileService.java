package com.tools.service;


import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.mongodb.WriteResult;
import com.tools.entity.GroupFiles;
import com.tools.entity.groups;
import com.tools.entity.user_files;
import com.tools.entity.user_shared_files;
import com.tools.repository.FileRepository;
import com.tools.repository.GroupsRepository;
import com.tools.repository.UserSharedFileRepository;
import com.tools.requestparams.FilesParams;
import com.tools.requestparams.GetAllFiles;
import com.tools.requestparams.ShareFileParams;


@Service
public class FileService {
	
	@Autowired
	private FileRepository fileRepository ; 
	
	@Autowired
	MongoTemplate mongoTemplate ; 
	
	@Autowired
	UserSharedFileRepository userShareRepository ; 
	
	@Autowired
	private GroupsRepository groupRepository ;
	
	
	public List<user_files> getAllFiles(GetAllFiles readParams){
		return  fileRepository.findByEmailAndDirectoryAndIsdeleted(readParams.getEmail(), readParams.getDirectory() , 0 ) ; 
	}
	
	public List<user_files> getAllRecentFiles(String email){
		return fileRepository.findByEmailAndIsdeleted(email ,0 ,  new PageRequest(0, 5 )) ; 
	}
	
	public WriteResult deleteFile(FilesParams params) {
		/*long deleteStatus =  fileRepository.deleteByEmailAndDirectoryAndFilename(params.getEmail(), params.getDirectory(), params.getFilename());
		if(deleteStatus == 1 ) return true ; else return false ;*/ 
		
		Query query = new Query(Criteria.where("email").is(params.getEmail()).and("directory").is(params.getDirectory()).and("filename").is(params.getFilename()));
        Update update = new Update();
        update.set("isdeleted", 1);
        return mongoTemplate.updateFirst(query, update , user_files.class);
		
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

	public boolean createFolder(GetAllFiles params) {
		System.out.println(params.getDirectory() + params.getEmail() + params.getFoldername());
		if(fileRepository.findByEmailAndIsdeletedAndDirectoryAndFilename(params.getEmail() , 0 , params.getDirectory()
																		, params.getFoldername()).size() > 0) {
			return false ; 
		}else {
			user_files file = new user_files();
			file.setDirectory(params.getDirectory());
			file.setEmail(params.getEmail());
			file.setFilename(params.getFoldername());
			file.setFileadddate(new Date());
			file.setStarred(0);
			file.setIsdirectory(1);
			file.setIsdeleted(0);
			
			user_files savedFile = fileRepository.save(file);
			if(savedFile != null) {
				return true ;
			}else {
				return false;
			}
		}
	}

	public List<user_files> getFilesHistory(GetAllFiles params) {
		return fileRepository.findByEmailAndIsdeleted(params.getEmail(), 1);
	}

	public boolean shareFile(ShareFileParams params) {
		// check if already shared ?
		if(userShareRepository.findByFromuserAndTouserAndFilenameAndDirectoryAndIsdirectory(params.getEmail() ,
				params.getTouser() , params.getFilename() , params.getDirectory() , params.getIsdirectory()).size() > 0) {
			return false ; 
		}else {
			user_shared_files fileObj = new user_shared_files() ;
			fileObj.setDirectory(params.getDirectory());
			fileObj.setFilename(params.getFilename());
			fileObj.setFromuser(params.getEmail());
			fileObj.setIsdirectory(params.getIsdirectory());
			fileObj.setTouser(params.getTouser());
			
			user_shared_files temp_obj = userShareRepository.save(fileObj) ; 
			
			if(temp_obj != null) {
				return true ;
			}else {
				return false ;
			}
		}
	}

	public List<user_shared_files> getAllSharedFile(ShareFileParams params) {
		return userShareRepository.findByTouser(params.getEmail());
	}

	public boolean shareFileWithGroup(ShareFileParams params) {
		List <groups> grpToFind = groupRepository.findByGroupownerAndGroupname(params.getGroupowner(),params.getGroupname()) ; 
		
		
		if(grpToFind.size() > 0) {
			Optional<groups> grp = grpToFind.stream().findFirst();
			if(grp.isPresent()) {
				groups groupToEdit  = grp.get();
				
				List<GroupFiles> grpFiles = groupToEdit.getFilelist();
				
				System.out.println(grpFiles.size());
				
				boolean foundFileToInsert = false ; 
				Iterator iterator = grpFiles.iterator();
				
				while(iterator.hasNext()) {
					GroupFiles fileToCheck = (GroupFiles) iterator.next();
					if(fileToCheck.getFileowner().equalsIgnoreCase(params.getEmail()) && fileToCheck.getFilename().equalsIgnoreCase(params.getFilename()) && fileToCheck.getIsdirectory() == params.getIsdirectory() && fileToCheck.getFiledirectory().equalsIgnoreCase(params.getDirectory())) 
					{
						foundFileToInsert = true  ; 
						break; 
					}		
					
				}
				
			
				if(foundFileToInsert) {
					System.out.println("Already present");
					return false;
				}
				else {
					System.out.println("Addinf the file ");
					GroupFiles fileToAdd = new GroupFiles();
					fileToAdd.setIsdirectory(params.getIsdirectory());
					fileToAdd.setFiledirectory(params.getDirectory());
					fileToAdd.setFilename(params.getFilename());
					fileToAdd.setFileowner(params.getEmail());
					fileToAdd.setGroupname(params.getGroupname());
					fileToAdd.setGroupowner(params.getGroupowner());
					
					grpFiles.add(fileToAdd);
					groupToEdit.setFilelist(grpFiles);
					
					groups groupSaved = groupRepository.save(groupToEdit);
					if(groupSaved !=null) return true ; else return false ; 
				}
				
			}
		}
		
		return false;
	}

	public List<user_files> readFolderForIndividuals(ShareFileParams params) {
		System.out.println("XXXXXXXXX" + params.getEmail() + "   " + params.getDirectory());
		return null;
	}
	
	
	
	
	
}
