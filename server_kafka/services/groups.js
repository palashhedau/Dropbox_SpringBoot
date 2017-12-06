var  pool = require("./connectionPool");
var ObjectID = require('mongodb').ObjectID

function createGroup(msg, db , dbId ,   callback){

         var res = {};

         var email = msg.email ;
         var groupname = msg.groupname ;
         
        
         
         var collection = db.collection('groups');
         collection.find({groupowner : email , groupname : groupname }).toArray(function(err , result){
             if(result[0]){
                pool.releaseConnection(db , dbId);
                 console.log('Group exist ')
             }else{
                
                var obj = {
                         groupowner : email ,
                         groupname : groupname ,
                         members : 
                             [ {"email"  : email , "groupowner" : email } ],
                } ; 
                 
                 collection.insertOne(obj , function(err , response){
                     if(err){
                        pool.releaseConnection(db , dbId);
                         console.log(err) ;
                     }else{
                        // Fetching the groups for the users 
                         var collection = db.collection('groups');
                         collection.find({members : {$elemMatch : { email : email } }}).toArray(function(err , response){
                             pool.releaseConnection(db , dbId);
                             if(err){
                                  res.code = 200 ;
                                  res.grouplist = null ; 

                                 callback(null , res ) ; 
                             }else{
                                 

                                 res.code = 201 ;
                                 res.grouplist = response ; 

                                 callback(null , res ) ; 
                             }
                         })
                     }
                 })
             }
         })
}




function getAllGroups(msg, db , dbId ,  callback){
    var res = {};

    var email = msg.email ;

     var collection = db.collection('groups');
         collection.find({members : {$elemMatch : { email : email } }}).toArray(function(err , response){
             pool.releaseConnection(db , dbId);
             if(err){
                res.code = 200 ;
                res.grouplist = null ; 
                 callback(null , res ) ; 
             }else{
                  res.code = 200 ;
                  res.grouplist = response ;
                 callback(null , res ) ;  
             }
         })


}

function addMemberToGroup(msg, db , dbId ,   callback){
    var res = {};

    var email = msg.email ;
    var emailToAdd = msg.emailToAdd ;
    var emailToAdd = msg.emailToAdd ;
    var group_id = msg.group_id ;
    group_id = new ObjectID(group_id) ;
    


     var collection = db.collection('groups');
         collection.find({_id : group_id , members: {$elemMatch : { email : emailToAdd }} }).toArray(function(err , response){
             if(response[0]){
                 res.code = 401 ;
                 pool.releaseConnection(db , dbId);
                 callback(null , res ) ;
             }else{
                 console.log('User not present , need to add ') ; 
                 var emailToAddObj = {
                        email: emailToAdd , "groupowner" : email 
                 } ;
                 
                 collection.update({_id : group_id} , {$addToSet: {members : emailToAddObj }} , function(err , response ){
                     pool.releaseConnection(db , dbId);
                     if(err){
                         console.log(err)
                     }else{
                         res.code = 201 ;
                         callback(null , res ) ;
                     }
                 })
             }
     })


}


function getMembersOfGroup(msg, db , dbId,  callback){
    var res = {};

    var email = msg.email ;
    var group_id = msg.group_id ;
    group_id = new ObjectID(group_id) ;
    


    var collection = db.collection('groups');
         
         collection.find({_id : group_id } , {members : 1 }).toArray(function(err , response ){
            pool.releaseConnection(db , dbId);
            if(err){
                res.code = 500 ;
                res.groupMemberList  =  null ;
                 callback(null , res ) ;
            }else{
                if(response[0]){
                    
                    res.code = 200 ;
                    res.groupMemberList  =   response[0].members 
                     callback(null , res ) ;
                }
         }
    })


}


function deleteMembersOfGroup(msg, db , dbId ,  callback){
    var res = {};

    var email = msg.email ;
    var group_id = msg.group_id ;
    group_id = new ObjectID(group_id) ;

    var groupname =  msg.groupname;
    var membertodelete = msg.membertodelete;


   var collection = db.collection('groups');
         
    collection.find({_id : group_id , members: {$elemMatch : { email : membertodelete }}  }).toArray(function(err , response ){
                if(err){
                    pool.releaseConnection(db , dbId);
                    console.log(error);
                   res.code = 500 ;
                   res.groupMemberList = null ;
                   callback(null , res) ;
                }else{
                    if(response[0]){
                        //group found 
                        console.log('Group and member found ') ; 
                         var emailToAddObj = {
                                    email: membertodelete , "group_owner" : email 
                             } ;
                        collection.update({_id : group_id} , {$pull: {members : emailToAddObj }} , function(err , response){
                            if(err){
                                console.log(error) ;
                                pool.releaseConnection(db , dbId);
                            }else{
                                console.log('Deleted fuccessfully ') ; 
                                 
                                 collection.find({_id : group_id } , {members : 1 }).toArray(function(err , response ){
                                    pool.releaseConnection(db , dbId);
                                    if(err){
                                        console.log(error);
                                        res.code = 500 ;
                                         res.groupMemberList = null ;
                                         callback(null , res) ;
                                    }else{
                                        if(response[0]){
                                            res.code = 200 ;
                                            res.groupMemberList = response[0].members ;
                                            callback(null , res) ;
                                        }
                                    }
                                })
                            }
                        })
                        
                    }
                }
            })     


}


function deleteGroup(msg, db , dbId ,  callback){
    var res = {};

    var email = msg.email ;
    var group_id = msg.group_id ;
    group_id = new ObjectID(group_id) ;

    var collection = db.collection('groups');
         
    collection.remove({_id : group_id } , function(err , response ){
             if(err){
                 console.log(err);
                 pool.releaseConnection(db , dbId);
             }else{
                 var collection = db.collection('groups');
                 collection.find({members : {$elemMatch : { email : email } }}).toArray(function(err , response){
                     pool.releaseConnection(db , dbId);
                     if(err){
                         res.code = 200 ;
                         res.grouplist = null ; 
                         callback(null , res);
                     }else{
                         res.code = 200 ;
                         res.grouplist = response ; 
                         callback(null , res);
                     }
                 })
             }
    })    

}


function getGroupName(msg, db , dbId ,  callback){
    var res = {};

    var email = msg.email ;
    var group_id = msg.group_id ;
    group_id = new ObjectID(group_id) ;

   var collection = db.collection('groups') ; 
         
         collection.find({_id : group_id } ,  {groupname : 1 }).toArray(function(err, result){
                pool.releaseConnection(db , dbId);
             if(err){
                 console.log(err);
                 res.code = 500 ; 
                 res.groupname = null ; 
                 callback(null , res);
                 
             }else{
                 res.code = 200 ; 
                 res.groupname = result[0].groupname ;
                 callback(null , res);
             }
    })

}


function shareFileWithGroup(msg, db , dbId ,   callback){
    var res = {};

    var fileowner = msg.file_owner ;
    var groupname = msg.groupname ;
    var filename = msg.filename ;
    var directory = msg.directory ;
    var groupowner = msg.groupowner ;
    var isdirectory = msg.is_directory ;

    console.log("Message " , msg) ; 
    

    console.log("Start") ; 
     var collection = db.collection('groups');
        
         collection.find({groupowner : groupowner , groupname : groupname , 
             filelist: {$elemMatch : { groupname : groupname ,fileowner :fileowner ,filename: filename ,
                 filedirectory : directory , groupowner : groupowner , isdirectory : isdirectory }} }).toArray(function(err , response){
             if(response[0]){
                pool.releaseConnection(db , dbId);
                 res.code = 401;
                 res.groupFileList = null ;
                 callback(null , res);
             }else{
                 console.log('File not shared  , need to add ') ; 
                 var shareFileAdd = {
                         groupname : groupname ,
                         fileowner :fileowner ,
                         filename: filename ,
                         filedirectory : directory ,
                         groupowner : groupowner,
                         isdirectory : isdirectory
                 } ;
                 
                 console.log("Update info " , groupowner , groupname )
                 collection.update({groupowner : groupowner , groupname : groupname} , {$addToSet: {filelist : shareFileAdd }} , function(err , response ){
                     console.log("After add " , err , response.result) ; 
                     if(err){
                         console.log(err);
                         pool.releaseConnection(db , dbId);
                     }else{
                        
                         collection.find({groupowner : groupowner , groupname : groupname}).toArray(function(err , result){
                             pool.releaseConnection(db , dbId); 
                             if(err ){
                                    console.log('Error while fetching data ' , err);
                                    res.code = 500;
                                     res.groupFileList = null ;
                                     callback(null , res);
                                } 
                                else{
                                    res.code = 200;
                                     res.groupFileList = result ;
                                     callback(null , res);
                                }
                         })
                         
                     }
                 })
             }
         })

}


function getAllSharedGroupComponents(msg, db , dbId ,  callback){
    var res = {};

    var email = msg.email ;
    var group_id = msg.group_id ;
    group_id = new ObjectID(group_id) ;

   var collection = db.collection('groups');
         
         collection.find({_id : group_id } , {filelist : 1 }).toArray(function(err , response ){
            pool.releaseConnection(db , dbId);
            if(err){

                res.code = 500;
                res.filelist = null ; 
                callback(null , res);
            }else{
                if(response[0]){
                    
                    if(response[0].filelist == null ){
                         res.code = 200;
                        res.filelist = [] ; 
                        callback(null , res);
                    }else{
                         res.code = 500;
                        res.filelist = response[0].filelist  ; 
                        callback(null , res);
                        
                    }
                    
                }
            }
        })
        

}



function readFolderForGroups(msg, db , dbId ,  callback){
    var res = {};

    var email = msg.email ;
    var folderowner = msg.folderowner ;
    var foldername = msg.foldername ;
    var directory = msg.directory ;

    console.log(email , foldername) ;

    var collection = db.collection('user_files') ; 
         
         collection.find({email : folderowner , directory : foldername , is_deleted : 0 }).toArray(function(err , result){
            pool.releaseConnection(db , dbId);
             if(err){
                 res.code = 200;
                 res.subGroupContent = null ;
                 callback(null , res)
             }else{
                 res.code = 200;
                 res.subGroupContent = result ;
                 callback(null , res)
             }
         })
        

}




exports.readFolderForGroups = readFolderForGroups;
exports.getAllSharedGroupComponents = getAllSharedGroupComponents;
exports.shareFileWithGroup = shareFileWithGroup;
exports.getGroupName = getGroupName;
exports.deleteGroup = deleteGroup;
exports.deleteMembersOfGroup = deleteMembersOfGroup;
exports.getMembersOfGroup = getMembersOfGroup;
exports.addMemberToGroup = addMemberToGroup;
exports.createGroup = createGroup;
exports.getAllGroups = getAllGroups;
