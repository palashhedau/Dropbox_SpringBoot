import axios from 'axios'

const headers = {
    'Accept': 'application/json'
};




export function setHomeHeading(heading)  {
	
	return {
		type : 'SET_HOME_HEADING' ,
		payload : heading
	}
	
	
}


export function getAllUsers(email  ){
	
		return function(dispatch){
			fetch('http://localhost:8081/getAllUsers', {
	        method: 'POST',
	        headers: {
	            ...headers,
	            'Content-Type': 'application/json'
	        },
	        credentials:'include',
	   	    body: JSON.stringify({email : email})

	  		}).then(function (response) {
			     response.json().then(res => {
			      	dispatch({type : 'GET_ALL_USERS_SUCCESS' , payload : res });
				})
																		        
	   		})
	        .catch(error => {
	        })
		}
}
