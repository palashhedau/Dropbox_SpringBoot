import axios from 'axios' ; 

const headers = {
    'Accept': 'application/json'
};


export function starItems(email , file , directory)  {
	
	
	return function(dispatch){
			fetch('http://localhost:8081/starFile', {
	        method: 'POST',
	        headers: {
	            ...headers,
	            'Content-Type': 'application/json'
	        },
	        credentials:'include',
	   	    body: JSON.stringify({filename : file,
							  	directory : directory,
							  	email : email })

	  		}).then(function (response) {
			       
			     response.json().then(res => {
			    	  dispatch({type : 'STAR_FILES' , payload : res});
			    })
																		        
	   		})
	        .catch(error => {
	            dispatch({type : 'STAR_FILES' , payload : false});
	        })
		}


	
}




export function getAllStarredFiles(email , directory )  {
	 
	return function(dispatch){
			fetch('http://localhost:8081/readallStarredfiles', {
	        method: 'POST',
	        headers: {
	            ...headers,
	            'Content-Type': 'application/json'
	        },
	        credentials:'include',
	   	    body: JSON.stringify({ directory : directory,
							  	   email : email })

	  		}).then(function (response) {
			       
			      response.json().then(res => {
			      	console.log("gettng Starred Files " , res)
			      	dispatch({type : 'GET_STAR_FILES' , payload : res});
			    })
			})
	        .catch(error => {
	            dispatch({type : 'STAR_FILES_FAILURE' , payload : error})
	            
	        })
		}
}



export function unStarItems(email , filename , directory)  {
	return function(dispatch){
			fetch('http://localhost:8081/unStarfile', {
	        method: 'POST',
	        headers: {
	            ...headers,
	            'Content-Type': 'application/json'
	        },
	        credentials:'include',
	   	    body: JSON.stringify({ email : email ,
		 	filename : filename,
		 	directory : directory  })

	  		}).then(function (response) {
			   response.json().then(res => {
			   		dispatch({type : 'UN_STAR_FILE' , payload : res})
			    })
																		        
	   		})
	        .catch(error => {
	           dispatch({type : 'UN_STAR_FILE' , payload : false})
	            
	        })
		}
}

