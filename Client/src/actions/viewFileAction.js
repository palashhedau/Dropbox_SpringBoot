import axios from 'axios' ; 
import FileSaver  from 'file-saver' ; 

var some = true ; 

const headers = {
    'Accept': 'application/json'
};


export function viewFile(email , file , directory )  {
	axios.get('http://localhost:8081/downloadFile',{responseType: 'blob',  params: {
		      email: email,
		      filename : file ,
		      directory : directory
		    },
		    withCredentials: true
	  })
	  .then(function (response) {
	  		FileSaver.saveAs(response.data, file);
	  })
	  .catch(function (error) {
	   		
	  })
}





export function viewFileForGroup(email , fileowner , file , directory )  {
	
	axios.get('http://localhost:8081/downloadFile',{responseType: 'blob',  params: {
		      filename : file ,
		      directory : directory,
		      email : fileowner
		    },
		    withCredentials: true
	  })
	  .then(function (response) {
	  	
	    
		FileSaver.saveAs(response.data, file);
	    	

	  })
	  .catch(function (error) {
	   		
	  })






	
	
}