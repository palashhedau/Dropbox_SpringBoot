const initialState = {
	fileUploader : false ,
	error : null ,
	listOfFiles : [] ,
	listOfStarredFiles : [] ,
	listOfSharedFiles : [],
	listOfGroupSharedFiles : [],
	groupmembers : [] , 
	listOfRecentFiles : [],
	listOfIndividualSHaredContent : [],
	deleteFileSuccess : null ,
	unStarSuccess : null ,
	starSuccess : null ,
	createFolderSuccess : null ,
	shareFileSuccess : null
}


export default function reducer (state=initialState , action )  {
	switch(action.type){
		
		//File
		case 'FILE_UPLOAD_SUCCESS' : {
			return {...state , listOfFiles : action.payload.filelist};
		}
		case 'FILE_UPLOAD_FALIURE' : {
			return {...state , error : action.payload}
		}
		case 'GET_ALL_FILES_SUCCESS' : {
			return {...state , listOfFiles : action.payload ,shareFileSuccess: null ,  deleteFileSuccess : null , unStarSuccess : null , starSuccess : null , createFolderSuccess : null}
		}


		
		//Star Files
		case 'STAR_FILES' : {
			return {...state  , starSuccess : action.payload} ;
		}
		case 'GET_STAR_FILES' : {
			return {...state , listOfStarredFiles : action.payload ,shareFileSuccess : null ,  deleteFileSuccess : null , unStarSuccess : null , starSuccess : null , createFolderSuccess : null} ;
		}
		case 'UN_STAR_FILE' : {
			return {...state , unStarSuccess :  action.payload } 
			
		}


		//delete
		case 'DELETE_FILE_SUCCESS' : {
			return {...state , deleteFileSuccess : action.payload} 
		}

		
		//folder 
		case 'CREATE_FOLDERNAME' : {
			return {...state , listOfFiles : action.payload.filelist } 
		}


		// get user shared files 
		case 'GET_SHARED_FILE_SUCCESS' : {
			return {...state , listOfSharedFiles : action.payload} 
		}
		



		/*case 'GET_ALL_STAR_FILES' : {
			return {...state , listOfStarredFiles : action.payload} 
		}*/

		




		//groups
		case 'GET_GROUP_SHARED_FILE_SUCCESS' : {
			return {...state , listOfGroupSharedFiles : action.payload} 
		}
		case 'GET_GROUP_MEMBER_SUCCESS' : {
			return {...state ,  groupmembers : action.payload }
		}
		case 'DELETE_MEMBER_SUCCESS' : {
			return {...state ,  listOfGroupSharedFiles : action.payload.filelist ,
								groupmembers : action.payload.groupMemberList}
		}





		case 'GET_RECENT_FILES_SUCCESS' : {
			return {...state ,  listOfRecentFiles : action.payload ,shareFileSuccess : null , createFolderSuccess : null ,  deleteFileSuccess : null , unStarSuccess : null , starSuccess : null }
		}
		case 'SET_CURRENT_INDIVIDUAL_FOLDER_CONTENT_SUCCESS' : {
			return {...state ,  listOfIndividualSHaredContent : action.payload.subGroupContent}
		}

		

		//set back variables 
		case 'SET_BACK_FILE_REDUCER_VARIABLES' : {
			return {...state , shareFileSuccess : null , deleteFileSuccess : null , unStarSuccess : null , starSuccess : null , createFolderSuccess: null}
		}

		

		//share file 
		case 'SHARE_FILE' : {
			return {...state , shareFileSuccess : action.payload}
		}

		default :
			return state ; 

	}

}