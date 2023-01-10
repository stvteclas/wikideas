import {
    GET_ARTICLES, GET_ARTICLE_BY_ID, FILTER_BY_CATEGORY, GET_THEMES, CREATE_ARTICLE
  
  } from "../actions";
  const initialState = {
    articles: [],
    allArticles:[],
    article:{},
    themes:[]
    
  };
  
  export default function articlesReducers(state = initialState, action) {
    switch (action.type) {
      case GET_ARTICLES:
        return {
          ...state,
          articles:  action.payload,
          allArticles:  action.payload
        }
        case GET_ARTICLE_BY_ID:
          return {
            ...state,
            article:action.payload,
          };
          case FILTER_BY_CATEGORY:
            const filterCategory = state.allArticles;
            const category =
              action.payload === "all"
                ? filterCategory
                : state.allArticles.filter((e) =>
                    e.theme===action.payload
                  );
            return {
              ...state,
              articles: category,
            };
            case GET_THEMES:
              return {
                ...state,
                themes:action.payload
              };
              case CREATE_ARTICLE:
                return action.payload
      default:
        return state;
    }
}