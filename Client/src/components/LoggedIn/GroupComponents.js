import React, { Component } from 'react';
import { Link } from 'react-router-dom'
import {viewFile} from '../../actions/viewFileAction'
import {starItems} from '../../actions/StarredAction'
import {connect} from 'react-redux'
import {deleteFile} from '../../actions/uploadFileAction'
import {shareFile} from '../../actions/shareFileAction'
import {deleteGroup , addMembersToTheGroup} from '../../actions/GroupAction'
import Modal from 'react-modal'

class GroupComponent extends Component{

	constructor(props){
		super(props);
		this.state = {
			addEmail : '',
			groupname : '' ,
			showButtonOrDropDown : 'Button' ,
			addTogroup : '' ,
			groupUrl : '/groups/',
			modalIsOpen: false
		}
	}


	componentDidMount(){
		this.setState({
			groupname : this.props.group.group_name
		})
	}

	
	render(){
		const stylePadding = {
			paddingLeft : "10px"
		}

		const customStyles = {
	      content : {
	        top                   : '40%',
	        left                  : '50%',
	        right                 : '50%',
	        bottom                : 'auto',
	        marginRight           : '-50%',
	        transform             : 'translate(-50%, -50%)'
	      }
	    };

		return (
				 <li   className="list-group-item padd">
				 	<Link  data-toggle="tooltip" data-placement="bottom" title={this.props.group.groupowner}
				 		   to={this.state.groupUrl + this.props.group.id}>{this.props.group.groupname}</Link>
				 	
				 	{
				 		this.props.email === this.props.group.groupowner ? 
				 		<button onClick={() => {
				 			this.setState({
				 				modalIsOpen : true
				 			})
				 		}} style={stylePadding} className="btn btn-danger pull-right btn-xs">Delete</button> 
				 		: 
				 		<b></b>
				 	}

					<Modal
                        isOpen={this.state.modalIsOpen}
                        style= {customStyles}
                        onRequestClose={this.closeModal}
                        shouldCloseOnOverlayClick={false}
                        contentLabel="Profile">
							<h4 className="modal-title textCenter">Are you sure you want to delete the group ?</h4>
							   <div className="modal-footer">
									       <button type="button" className="btn btn-danger"  onClick={() => {
									 		this.props.deleteGroup(this.props.email , this.props.group.groupname , this.props.group.id)
									 		}} >Delete</button>
									       <button type="button" className="btn btn-default" onClick={() => {
			                      				this.setState({modalIsOpen: false});
			                      			}}>Dismiss</button>
								</div>
                      </Modal>

				 	
				 	{
				 		this.props.email === this.props.group.groupowner ? 

				 		(
				 		this.state.showButtonOrDropDown === 'Button' ? 
					 		
					 		<button className="btn btn-primary pull-right btn-xs" onClick={() => {
					 			this.setState({showButtonOrDropDown : 'DropDOwn'})
					 		}}>Add Member</button> 

				 		:
				 		<div>
					 		<select className="input-medium" onChange={(e) => {	this.setState({
														 		addTogroup : e.target.value,})
													}} className="form-control" id="sel1"> 	
											 	<option>---Select Email---</option>
											 	{this.props.AllUsers.map((user , key) => {
											  		return <option   key={key}>{user.email}</option>
											  	})} 

							</select>
							<button className="btn btn-primary btn-sm " onClick={() => {
								
								if (this.state.addTogroup === ''){
								}else{
									this.props.addMembersToTheGroup(this.props.email , this.state.addTogroup , 
												this.props.group.groupname , this.props.group.id)
								}

								this.setState({
									showButtonOrDropDown : 'Button'
								})
							}}>Add</button>
						<button style={stylePadding} className="btn btn-danger btn-sm" onClick={() => {
							this.setState({
								showButtonOrDropDown : 'Button'
							})
						}}>Cancel</button>
						</div>   ) : <div></div>
				 	}


				 </li>
			   )
	}


}

function mapDispatchToProps(dispatch){
	return {
		viewFile : (filename) => dispatch(viewFile(filename)),
		starItems : (email , item , directory ) => dispatch(starItems(email ,item , directory)),
		deleteFile : (email , filename , directory  ) => dispatch(deleteFile(email , filename , directory )),
		shareFile : (filename , directory , fromUser , toUser ) => dispatch(shareFile(filename , directory , fromUser , toUser )),
		deleteGroup : (email , groupname , id ) => dispatch(deleteGroup(email,groupname ,id )),
		addMembersToTheGroup : (email , emailtoadd , groupname , id) => dispatch(addMembersToTheGroup(email , emailtoadd , groupname, id))
	}
}


function mapStateToProps(state) {
    return {
        isAuthenticated : state.AuthReducer.isAuthenticated,
        listOfFiles : state.fileUploadReducer.listOfFiles,
        fileContent : state.getClickedFileDataReducer.fileData, 
        listOfSTarredFiles : state.fileUploadReducer.listOfStarredFiles,
        currentUrl : state.CurrentDirectoryReducer.directory,
        directoryForServer : state.CurrentDirectoryReducer.directoryForServer,
        email : state.AuthReducer.email,
        AllUsers : state.HomeReducer.getAllUsers,
    };
}


export default connect(mapStateToProps , mapDispatchToProps)(GroupComponent) ;