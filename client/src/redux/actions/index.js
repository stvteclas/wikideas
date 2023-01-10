import axios from "axios";

export const GET_ARTICLES = "GET_ARTICLES";
export const GET_THEMES = "GET_THEMES";
export const GET_ARTICLE_BY_ID = "GET_ARTICLE_BY_ID";
export const FILTER_BY_CATEGORY = "FILTER_BY_CATEGORY";



//Get all Articles------------
export function getArticles() {
  return async function (dispatch) {
    return axios
      .get("http://localhost:8080/article/articles")
      .then((res) => {
        dispatch({
          type: GET_ARTICLES,
          payload: res.data,
        });
      })
      .catch((err) => console.log(err));
  };
}

//Get Article by ID------------
  export const getArticleById = (id) => {
    return async (dispatch) => {
      axios
        .get(
          `http://localhost:8080/article/${id}`
        )
        .then((res) =>
          dispatch({ type: GET_ARTICLE_BY_ID, payload: res.data })
        );
    };
  };

  //Filter categories
  export function filterByCategorie(payload) {
    return {
      type: FILTER_BY_CATEGORY,
      payload,
    };
  }

  //Get all Themes------------
export function getThemes() {
  return async function (dispatch) {
    return axios
      .get("http://localhost:8080/themes")
      .then((res) => {
        dispatch({
          type: GET_THEMES,
          payload: res.data,
        });
      })
      .catch((err) => console.log(err));
  };
}