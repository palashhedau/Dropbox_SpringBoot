package com.tools.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.entity.user_files;
import com.tools.requestparams.FilesParams;
import com.tools.requestparams.GetAllFiles;
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
		
		GetAllFilesResponseParam params = new GetAllFilesResponseParam();
		params.setFilelist(fileService.getAllRecentFiles(email));
		return params  ; 
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteFile(@RequestBody FilesParams params){
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
}
