import {
    GET_ARTICLES
  
  } from "../actions";
  const initialState = {
    articles: [],
    
  };
  
  export default function articlesReducers(state = initialState, action) {
    if (action.type===GET_ARTICLES) {
        return {
          ...state,
          articles: action.payload,
        };
  }
  return state;
}