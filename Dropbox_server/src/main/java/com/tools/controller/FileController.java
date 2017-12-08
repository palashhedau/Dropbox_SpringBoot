package com.tools.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

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
			@RequestParam("file") MultipartFile file ,@RequestParam("email") String email , @RequestParam("directory") String directory ){
		return new ResponseEntity(fileService.upload(file , name , email , directory) , HttpStatus.OK) ; 
	}
	
	@RequestMapping(method=RequestMethod.GET , value="/downloadFile")
	public ResponseEntity<?>  downloadFile(@RequestParam String email , String directory , String filename ){
		
		
		String filePath = fileService.downloadFile(email , filename , directory ) ; 
		File file = new File(filePath);
		/*MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		String mimeType = mimeTypesMap.getContentType(filePath);*/
		
	    HttpHeaders respHeaders = new HttpHeaders();
	    //respHeaders.setContentType(mimeType);
	   // respHeaders.setContentLength(12345678);
	    respHeaders.setContentDispositionFormData("attachment" , filename);

	    InputStreamResource isr;
		try {
			isr = new InputStreamResource(new FileInputStream(file));
			System.out.println("FIle " + isr);
			return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
		return new ResponseEntity<InputStreamResource>( null , respHeaders, HttpStatus.OK);
		
		
	}
	
	
	
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
