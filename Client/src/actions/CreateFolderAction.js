import axios from 'axios'


const headers = {
    'Accept': 'application/json'
};



export function createFolder(email , foldername , directory )  {
	
		return function(dispatch){
			fetch('http://localhost:8081/createFolder', {
	        method: 'POST',
	        headers: {
	            ...headers,
	            'Content-Type': 'application/json'
	        },
	        credentials:'include',
	   	    body: JSON.stringify({email : email ,
							 	foldername : foldername,
							 	directory : directory})
			}).then(function (response) {
			    response.json().then(res => {
			      dispatch({type : 'CREATE_FOLDERNAME' , payload : res })
			    })
																		        
	   		})
	        .catch(error => {
	          dispatch({type : 'CREATE_FOLDERNAME' , payload : false })
	        })
		}
}