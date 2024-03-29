import {createStore , applyMiddleware } from 'redux';
import { createLogger as logger } from 'redux-logger'
import thunk from 'redux-thunk'
import promise from 'redux-promise-middleware'
import reducer from './reducers/combinedReducers'



const middleware = applyMiddleware(promise() , thunk , logger());


export default  createStore(reducer, middleware);
//export default  createStore(reducer);