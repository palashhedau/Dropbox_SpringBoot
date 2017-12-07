const initialState = {
	
	profile : null ,
	profile_details : {} ,
	profileExist : false ,
	profileSubmitSuccess : null 
}


export default function reducer (state=initialState , action )  {
	switch(action.type){
		case 'GET_PROFILE_SUCCESS' : {
			return {...state ,  profile : action.payload }
		}
		case 'CHECK_PROFILE_SUCCESS' : {
			return {...state ,  profile_details : action.payload.user ,
								profileExist : action.payload.profileExist }
		}
		case 'PROFILE_SUBMIT' : {
			return {...state  }
		}


		default :
			return state ; 

	}


}