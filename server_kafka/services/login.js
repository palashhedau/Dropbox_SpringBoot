var bcrypt = require('bcrypt') ; 
var auth = require('passport-local-authenticate');
var  pool = require("./connectionPool");

function handle_request(msg, db , dbId ,  callback){

    var res = {};
    console.log("Login called")
    var collection = db.collection('users');
    
    console.log(msg.email) ; 
                collection.find({email : msg.email} , {email : 1 , fname : 1 , lname : 1 , gender : 1 , password : 1 , dob : 1  } ).toArray(function(err , result){
                    pool.releaseConnection(db , dbId); 
                    if(result[0]){
                        console.log('User found ')
                        auth.verify(msg.password, result[0].password, function(err, verified) {
                                console.log(dbId) ; 
                               if(verified){
                                     console.log('User authenticated') ; 
                                user =  {user_id : result[0]._id , email : result[0].email , 
                                            fname : result[0].fname , dob :  result[0].dob , 
                                            lname : result[0].lname , gender : result[0].gender};


                                 res.code = "200";
                                 res.user = user ; 
                                 
                                 callback(null , res);
                               }else{
                                    res.code = "401";
                                  res.user = {} ;
                                  callback(null , res);
                               } 
                        });
                    }else{
                            res.code = "401";
                             res.user = {} ;
                             callback(res , null );
                    }
                    console.log("Response lol " , res) ;       
                 })

}



function registration(msg, db , dbId ,  callback){

    var res = {};
    
    var email = msg.email ; 
    var password = msg.password ; 
    var fname = msg.fname ; 
    var lname = msg.lname ; 
    var dob = msg.dob ; 
    var gender = msg.gender ; 
    
    console.log("Registration called " , email ) ; 
    var collection = db.collection('users');
        
        collection.find({email : email}).toArray(function(err , result){
           
            if(err){
                console.log(err);
                pool.releaseConnection(db , dbId);
            }else{
                if(result[0]){
                   
                    pool.releaseConnection(db , dbId);
                    res.code = 200 ; 
                    res.success  = false ; 
                    res.error = 'User already present'
                    callback(null , res) ; 
                }else{
                    
                    
                    const saltRounds = 10;
                    
                    auth.hash(password, function(err, hashed) {
                     
                     var obj = {email : email ,
                                password : hashed ,
                                fname : fname,
                                lname : lname  ,
                                dob : dob ,
                                gender : gender } ; 
                        
                        collection.insertOne(obj , function(err , response){
                            pool.releaseConnection(db , dbId);
                            if(err){
                                console.log(err);
                                res.code = 500 ; 
                                res.success  = false ; 
                                res.error = error
                                callback(null , res) ; 
                               
                            }else{
                                console.log("User successfully registered ", email , dbId);
                                res.code = 200 ; 
                                res.success  = true ; 
                                res.error = ''
                                callback(null , res) ;
                                
                            }
                        })


                    });
                    
                }
            }
        })
    

}













exports.handle_request = handle_request;
exports.registration = registration;