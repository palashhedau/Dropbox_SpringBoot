import axios from 'axios'

const headers = {
    'Accept': 'application/json'
};

export function uploadFile(email , file , name , directory )  {
	let data = new FormData();
	data.append('email', email);
  	data.append('file', file);
  	data.append('name', name);
  	data.append('directory', directory);
  	

	return  function(dispatch){
		 axios.post('http://localhost:8081/upload' , data , {withCredentials: true} )
	  .then(function (response) {
	  	dispatch({type : 'FILE_UPLOAD' , payload : response.data})
	  })
	  .catch(function (error) {
	    dispatch({type : 'FILE_UPLOAD' , payload : false})
	  })
	}
}



export function getRecentFiles(email)  {
	return function(dispatch){
			fetch('http://localhost:8081/readRecentfiles', {
	        method: 'POST',
	        headers: {
	            ...headers,
	            'Content-Type': 'application/json'
	        },
	        credentials:'include',
	   	    body: email

	  		}).then(function (response) {
			      
	  				
			     response.json().then(res => {
			      
			      	dispatch({type : 'GET_RECENT_FILES_SUCCESS' , payload : res.filelist})
			      })
			})
	        .catch(error => {
	            console.log("This is error");
	            
	        })
		}
}



export function getAllFiles(email , foldername , directory )  {
	 
	
	let data = new FormData();
  	data.append('email', email);
  	data.append('foldername', foldername);
  	data.append('directory', directory);
  	
  	return function(dispatch){
			fetch('http://localhost:8081/readallfiles', {
	        method: 'POST',
	        headers: {
	            ...headers,
	            'Content-Type': 'application/json'
	        },
	        credentials:'include',
	   	    body: JSON.stringify({email : email , foldername : foldername , directory : directory })

	  		}).then(function (response) {
			     response.json().then(res => {
			      	dispatch({type : 'GET_ALL_FILES_SUCCESS' , payload :  res.filelist}) ; 
			      })
			})
	        .catch(error => {
	            console.log("This is error");
	            
	        })
		}

}


export function deleteFile(email , filename , directory  )  {
	
	return function(dispatch){
			fetch('http://localhost:8081/delete', {
	        method: 'POST',
	        headers: {
	            ...headers,
	            'Content-Type': 'application/json'
	        },
	        credentials:'include',
	   	    body: JSON.stringify({email : email , filename : filename , directory : directory })

	  		}).then(function (response) {
	  			response.json().then(res => {
	  				dispatch({type : 'DELETE_FILE_SUCCESS' , payload : res.updateOfExisting})
			      })
			})
	        .catch(error => {
	            console.log("This is error");
	            
	        })
		}

}



