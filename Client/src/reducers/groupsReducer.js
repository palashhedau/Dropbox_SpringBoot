const initialState = {
	
	getAllGroups : [],
	error : null ,
	groupmembers : [],
	sharedCurrentGroup : {} ,
	sharedCurrentGroupContents : [] ,
	groupname : '' ,


	groupCreateSuccess : null,
	deleteMemberSuccess : null ,
	deleteGroupSuccess : null
}


export default function reducer (state=initialState , action )  {
	switch(action.type){
		
		//get all groups
		case 'GET_ALL_GROUPS_SUCCESS' : {
			return {...state ,  getAllGroups : action.payload  , groupCreateSuccess : null , deleteMemberSuccess : null , deleteGroupSuccess : null}
		}
		case 'GET_ALL_GROUPS_FAILURE' : {
			return {...state ,  error : action.payload ,   groupCreateSuccess : null , deleteMemberSuccess : null , deleteGroupSuccess : null}
		}

		
		//create group
		case 'CREATE_GROUP' : {
			return {...state , groupCreateSuccess : action.payload  }
		}
		
		//delete group
		case 'DELETE_GROUP' : {
			return {...state , deleteGroupSuccess : action.payload  }
		}



		//unknown
		case 'SET_CURRENT_GROUP_FOLDER' : {
			return {...state ,  sharedCurrentGroup : action.payload }
		}
		case 'SET_CURRENT_GROUP_FOLDER_CONTENT_SUCCESS' : {
			return {...state ,  sharedCurrentGroupContents : action.payload }
		}


		//get group name 
		case 'GET_GROUP_NAME_SUCCESS' : {
			return {...state ,  groupname : action.payload }
		}


		//delete Memeber
		case 'DELETE_MEMBER' : {
			return {...state ,  deleteMemberSuccess : action.payload }
		}

		
		//set back variables
		case 'SET_BACK_GROUP_REDUCER_VARIABLES' : {
			return {...state ,  groupCreateSuccess : null , deleteMemberSuccess : null , deleteGroupSuccess : null }
		}

		default :
			return state ; 

	}


}