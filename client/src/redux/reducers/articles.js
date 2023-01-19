import {
    GET_ARTICLES, GET_ARTICLE_BY_ID, FILTER_BY_CATEGORY, GET_THEMES, CREATE_ARTICLE, DELETE_ARTICLE, UPDATE_ARTICLE, SHOW_LOADING
  
  } from "../actions";
  const initialState = {
    articles: [],
    allArticles:[],
    article:{},
    themes:[],
    loading:false
    
  };
  
  export default function articlesReducers(state = initialState, action) {
    switch (action.type) {
      case GET_ARTICLES:
        return {
          ...state,
          articles:  action.payload,
          allArticles:  action.payload,
          loading:true
        
        }
        case GET_ARTICLE_BY_ID:
          return {
            ...state,
            article:action.payload,
            loading:true
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
                return action.payload;
                case DELETE_ARTICLE:
                  return action.payload;
                  case UPDATE_ARTICLE:
                    return{
                      ...state,
                      articles: state.articles,
                
                                          };
                                        
                                            case SHOW_LOADING:
                                              return {
                                                ...state,
                                                loading: false,
                                              };

                    
                  
                 
      default:
        return state;
    }
}