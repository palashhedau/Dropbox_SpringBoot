import axios from 'axios'



const headers = {
    'Accept': 'application/json'
};




export function shareFile(filename , directory , fromUser , toUser , is_directory )  {
	
	console.log("SGaring " , is_directory);
		return function(dispatch){
			fetch('http://localhost:8081/shareFile', {
	        method: 'POST',
	        headers: {
	            ...headers,
	            'Content-Type': 'application/json'
	        },
	        credentials:'include',
	   	    body: JSON.stringify({	filename : filename,
							  	directory : directory,
							  	email : fromUser ,
							  	 touser : toUser,
							  	 isdirectory : is_directory })

	  		}).then(function (response) {
			    response.json().then(res => {
			      	dispatch({type : 'SHARE_FILE' , payload : res });
			    })
			})
	        .catch(error => {
	           dispatch({type : 'SHARE_FILE' , payload : false})
	        })
		}
}



export function getAllSharedComponents(email )  {
	
	return function(dispatch){
			fetch('http://localhost:8081/getAllSharedFile', {
	        method: 'POST',
	        headers: {
	            ...headers,
	            'Content-Type': 'application/json'
	        },
	        credentials:'include',
	   	    body: JSON.stringify({	email : email})

	  		}).then(function (response) {
			    response.json().then(res => {
			      	dispatch({type : 'GET_SHARED_FILE_SUCCESS' , payload : res });
			    })
			})
	        .catch(error => {
	            dispatch({type : 'GET_SHARED_FILE_FAILURE' , payload : null})
	        })
		}
}





export function shareFileInGroup(email , groupname , filename , directory , is_directory )  {
	
	var group = groupname.substring(0,groupname.indexOf('- ')-1)
	var owner = groupname.substring(groupname.indexOf('- ')+2 ,groupname.length )

	return function(dispatch){
			fetch('http://localhost:8081/shareFileWithGroup', {
	        method: 'POST',
	        headers: {
	            ...headers,
	            'Content-Type': 'application/json'
	        },
	        credentials:'include',
	   	    body: JSON.stringify({ email : email,
								  	groupname : group,
								  	filename : filename,
								  	directory : directory,
								  	groupowner :  owner ,
								  	isdirectory : is_directory})

	  		}).then(function (response) {
			     response.json().then(res => {
			    	dispatch({type : 'SHARE_FILE_GROUP' , payload : res });
			    })
			})
	        .catch(error => {
	            dispatch({type : 'SHARE_FILE_GROUP' , payload : false})
	        })
		}
}




export function setCurrentShared(email ){
	return {
		type : 'SET_SHARED_USER_FILES' ,
		payload : email
	}
}



export function openFolderAndViewContentIndividual(email , folderowner ,  foldername)  {
	
	
	return function(dispatch){
			fetch('http://localhost:8081/readIndividualFolder', {
	        method: 'POST',
	        headers: {
	            ...headers,
	            'Content-Type': 'application/json'
	        },
	        credentials:'include',
	   	    body: JSON.stringify({
			email : folderowner,
			directory : foldername  })

	  		}).then(function (response) {
			      response.json().then(res => {
			      	console.log("Individual folder " , res ) ; 
			      	dispatch({type : 'SET_CURRENT_INDIVIDUAL_FOLDER_CONTENT_SUCCESS' , payload : res  })
			      })
			})
	        .catch(error => {
	            console.log("This is error");
	             dispatch({type :  'SET_CURRENT_INDIVIDUAL_FOLDER_CONTENT_ERROR' , payload : error})
	        })
		}


	
}