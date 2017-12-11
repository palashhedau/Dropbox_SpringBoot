package com.tools.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	
	@RequestMapping(method=RequestMethod.POST , value="/upload", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  upload(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file ,@RequestParam("email") String email , @RequestParam("directory") String directory , HttpSession session){
		if(session.getAttribute("id") != null) {
			System.out.println("Upload called");
			return new ResponseEntity(fileService.upload(file , name , email , directory) , HttpStatus.OK) ; 
		}else {
			return new ResponseEntity( HttpStatus.UNAUTHORIZED) ; 
		}
	}
	
	@RequestMapping(method=RequestMethod.GET , value="/downloadFile")
	public ResponseEntity<?>  downloadFile(@RequestParam String email , String directory , String filename , HttpSession session ){
		
		if(session.getAttribute("id") != null) {
			String filePath = fileService.downloadFile(email , filename , directory ) ; 
			File file = new File(filePath);
			
			HttpHeaders respHeaders = new HttpHeaders();
		    respHeaders.setContentDispositionFormData("attachment" , filename);

		    InputStreamResource isr;
			try {
				isr = new InputStreamResource(new FileInputStream(file));
				System.out.println("FIle " + isr);
				return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		    return new ResponseEntity<InputStreamResource>( null , respHeaders, HttpStatus.OK); 
		}else {
			return new ResponseEntity( HttpStatus.UNAUTHORIZED) ; 
		}
		
		
		
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST , value="/readallfiles", produces = MediaType.APPLICATION_JSON_VALUE)
	public GetAllFilesResponseParam getAllFiles(@RequestBody GetAllFiles readParams , HttpSession session){
		if(session.getAttribute("id") != null) {
			GetAllFilesResponseParam params = new GetAllFilesResponseParam(); 
			params.setFilelist(fileService.getAllFiles(readParams) );
			return params ;
		}else {
			return null ; 
		} 
	}
	

	@RequestMapping(method=RequestMethod.POST , value="/readRecentfiles", produces = MediaType.APPLICATION_JSON_VALUE)
	public GetAllFilesResponseParam getAllRecentFiles(@RequestBody String email , HttpSession session){
		if(session.getAttribute("id") != null) {
			GetAllFilesResponseParam params = new GetAllFilesResponseParam();
			params.setFilelist(fileService.getAllRecentFiles(email));
			return params  ; 
		}else {
			return null ; 
		}
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public WriteResult deleteFile(@RequestBody FilesParams params , HttpSession session ){
		if(session.getAttribute("id") != null) {
			return fileService.deleteFile(params)  ;  
		}else {
			return null  ; 
		}
	}
	
	
	@RequestMapping(method=RequestMethod.POST , value="/readallStarredfiles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> readallStarredfiles(@RequestBody FilesParams params , HttpSession session){
		if(session.getAttribute("id") != null) {
			return  new ResponseEntity(  fileService.readStarredFiles(params)  , HttpStatus.OK)  ;  
		}else {
			return  new ResponseEntity(  HttpStatus.UNAUTHORIZED)  ;
		}
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/unStarfile", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean unStarFile(@RequestBody FilesParams params , HttpSession session ){
		if(session.getAttribute("id") != null) {
			return fileService.unStarFile(params) ;   
		}else {
			return false ; 
		}
	}

	@RequestMapping(method=RequestMethod.POST , value="/starFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean starFile(@RequestBody FilesParams params , HttpSession session ){
		if(session.getAttribute("id") != null) {
			return fileService.starFile(params) ;   
		}else {
			return false ; 
		}
	}
	
	

	@RequestMapping(method=RequestMethod.POST , value="/createFolder", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  createFolder(@RequestBody GetAllFiles params , HttpSession session ){
		if(session.getAttribute("id") != null) {
			return new  ResponseEntity(fileService.createFolder(params) , HttpStatus.OK) ; 
		}else {
			return new ResponseEntity( HttpStatus.UNAUTHORIZED) ; 
		}
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/getFilesHistory", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  getFilesHistory(@RequestBody GetAllFiles params , HttpSession session ){
		if(session.getAttribute("id") != null) {
			return new  ResponseEntity(fileService.getFilesHistory(params) , HttpStatus.OK) ; 
		}else {
			return new ResponseEntity( HttpStatus.UNAUTHORIZED) ; 
		}
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/shareFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  getFilesHistory(@RequestBody ShareFileParams params , HttpSession session ){
		if(session.getAttribute("id") != null) {
			return new  ResponseEntity(fileService.shareFile(params) , HttpStatus.OK) ; 
		}else {
			return new ResponseEntity( HttpStatus.UNAUTHORIZED) ; 
		}
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/getAllSharedFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  getAllSharedFile(@RequestBody ShareFileParams params , HttpSession session ){
		if(session.getAttribute("id") != null) {
			return new  ResponseEntity(fileService.getAllSharedFile(params) , HttpStatus.OK) ; 
		}else {
			return new ResponseEntity( HttpStatus.UNAUTHORIZED) ; 
		}
	}
	
	
	@RequestMapping(method=RequestMethod.POST , value="/shareFileWithGroup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  shareFileWithGroup(@RequestBody ShareFileParams params , HttpSession session){
		if(session.getAttribute("id") != null) {
			return new  ResponseEntity(fileService.shareFileWithGroup(params) , HttpStatus.OK) ; 
		}else {
			return new ResponseEntity( HttpStatus.UNAUTHORIZED) ; 
		}
	}
	
	
	@RequestMapping(method=RequestMethod.POST , value="/readFolderForIndividuals", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  readFolderForIndividuals(@RequestBody ShareFileParams params , HttpSession session){
		if(session.getAttribute("id") != null) {
			return new  ResponseEntity(fileService.readFolderForIndividuals(params) , HttpStatus.OK) ;
		}else {
			return new ResponseEntity( HttpStatus.UNAUTHORIZED) ; 
		}
	}
	
	
	
}
