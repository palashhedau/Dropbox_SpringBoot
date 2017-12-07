import axios from 'axios'

export function register(email , password, fname , lname , gender , dob)  {
	
	return  function(dispatch){
		 axios.post('http://localhost:8081/registration', {
	  	email : email , password : password , fname : fname  
	  	, lname : lname  , gender : gender  , dob : dob
	  })
	  .then(function (response) {
	  	console.log("Dta after registraton" , response.data)
	  	dispatch({type : 'REGISTRATION_SUCCESS' , payload : response.data});
	  })
	  .catch(function (error) {
	  	dispatch({type : 'REGISTRATION_FALIURE' , payload : true})
	  })
	}
}



export function setBackRegistered(){
	return {
		type : 'SET_BACK_REGISTERED',
		payload : {success : false }
	}
}