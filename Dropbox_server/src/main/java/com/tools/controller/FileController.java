package com.tools.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.WriteResult;
import com.tools.entity.user_files;
import com.tools.requestparams.FilesParams;
import com.tools.requestparams.GetAllFiles;
import com.tools.requestparams.ShareFileParams;
import com.tools.responseParam.GetAllFilesResponseParam;
import com.tools.service.FileService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FileController {
	
	@Autowired
	FileService fileService ; 
	
	
	
	@RequestMapping(method=RequestMethod.POST , value="/readallfiles", produces = MediaType.APPLICATION_JSON_VALUE)
	public GetAllFilesResponseParam getAllFiles(@RequestBody GetAllFiles readParams){
		GetAllFilesResponseParam params = new GetAllFilesResponseParam();
		params.setFilelist(fileService.getAllFiles(readParams) );
		return params ; 
	}
	

	@RequestMapping(method=RequestMethod.POST , value="/readRecentfiles", produces = MediaType.APPLICATION_JSON_VALUE)
	public GetAllFilesResponseParam getAllRecentFiles(@RequestBody String email){
		System.out.println("Read Recent for " + email ) ; 
		GetAllFilesResponseParam params = new GetAllFilesResponseParam();
		params.setFilelist(fileService.getAllRecentFiles(email));
		return params  ; 
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public WriteResult deleteFile(@RequestBody FilesParams params){
		return fileService.deleteFile(params)  ; 
	}
	
	
	@RequestMapping(method=RequestMethod.POST , value="/readallStarredfiles", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<user_files> readallStarredfiles(@RequestBody FilesParams params){
		return fileService.readStarredFiles(params)  ; 
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/unStarfile", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean unStarFile(@RequestBody FilesParams params){
		return fileService.unStarFile(params) ;  
	}

	@RequestMapping(method=RequestMethod.POST , value="/starFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean starFile(@RequestBody FilesParams params){
		return fileService.starFile(params) ;  
	}
	
	

	@RequestMapping(method=RequestMethod.POST , value="/createFolder", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  createFolder(@RequestBody GetAllFiles params){
		return new  ResponseEntity(fileService.createFolder(params) , HttpStatus.OK) ;
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/getFilesHistory", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  getFilesHistory(@RequestBody GetAllFiles params){
		return new  ResponseEntity(fileService.getFilesHistory(params) , HttpStatus.OK) ;
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/shareFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  getFilesHistory(@RequestBody ShareFileParams params){
		return new  ResponseEntity(fileService.shareFile(params) , HttpStatus.OK) ;
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/getAllSharedFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  getAllSharedFile(@RequestBody ShareFileParams params){
		return new  ResponseEntity(fileService.getAllSharedFile(params) , HttpStatus.OK) ;
	}
	
	
	@RequestMapping(method=RequestMethod.POST , value="/shareFileWithGroup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  shareFileWithGroup(@RequestBody ShareFileParams params){
		return new  ResponseEntity(fileService.shareFileWithGroup(params) , HttpStatus.OK) ;
	}
	
	
	@RequestMapping(method=RequestMethod.POST , value="/readFolderForIndividuals", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  readFolderForIndividuals(@RequestBody ShareFileParams params){
		return new  ResponseEntity(fileService.readFolderForIndividuals(params) , HttpStatus.OK) ;
	}
	
	
	
}
