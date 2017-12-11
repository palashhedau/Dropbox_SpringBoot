import axios from 'axios'
import  setAuthorizationToken from '../utils/setAuthorizationToken'
import jwt from 'jsonwebtoken' ; 

const headers = {
    'Accept': 'application/json'
};





export function checkUser(email , password ){
	
		return function(dispatch){
			fetch('http://localhost:8081/login', {
	        method: 'POST',
	        headers: {
	            ...headers,
	            'Content-Type': 'application/json'
	        },
	        credentials:'include',
	   	    body: JSON.stringify({email: email, password: password})

	  		}).then(function (response) {
			      response.json().then(res => {
			      
			      	
			      	if(res.length == 0 ){
			      		dispatch({ type : 'UNSUCCESSFUL_LOGIN' , payload : { userFound : false }} )
			      	}else{
			      		dispatch({ type : 'SET_CURRENT_USER' , payload : { userFound : true  , user : res[0]}} )
			    	}

			    })
																		        
	   		})
	        .catch(error => {
	            console.log("This is error");
	        })
		}
	
}





export function logout()  {
	return function(dispatch){
			fetch('http://localhost:8081/logout', {
	        method: 'POST',
	        headers: {
	            ...headers,
	            'Content-Type': 'application/json'
	        },
	        credentials:'include'
			}).then(function (response) {
			   response.json().then(res => {
			   	console.log()
			      dispatch({ type : 'SET_CURRENT_USER_LOGOUT' , payload : {userFound : res }}) ;
			    })
	
			})
	        .catch(error => {
	            console.log("This is error");
	        })
		}

}




export function checkIfAldreadyLoggedIn()  {
	
	return function(dispatch){
			fetch('http://localhost:8081/checkIfAlreadyLoggedIn', {
	        method: 'POST',
	        headers: {
	            ...headers,
	            'Content-Type': 'application/json'
	        },
	        credentials:'include'
			}).then(function (response) {
			   response.json().then(res => {
					
					if(res === false){
			      		dispatch({ type : 'UNSUCCESSFUL_LOGIN' , payload : { userFound : res }} )
			      	}else{
			      		dispatch({ type : 'SET_CURRENT_USER' , payload : { userFound : true , user : res}} )
			      	}
			      
				})
			})
	        .catch(error => {
	            console.log("This is error");
	            
	        })
		}

}



