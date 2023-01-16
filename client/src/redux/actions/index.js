import axios from "axios";

export const GET_ARTICLES = "GET_ARTICLES";
export const GET_THEMES = "GET_THEMES";
export const GET_ARTICLE_BY_ID = "GET_ARTICLE_BY_ID";
export const FILTER_BY_CATEGORY = "FILTER_BY_CATEGORY";
export const CREATE_ARTICLE = "CREATE_ARTICLE";
export const DELETE_ARTICLE = "DELETE_ARTICLE";
export const UPDATE_ARTICLE = "UPDATE_ARTICLE";
export const REFRESH="REFRESH";
export const SHOW_LOADING="SHOW_LOADING"



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

//----------Create Article------------
export function createArticle(obj) {

  return dispatch => {
    // Hace la petición POST y envía los datos en el cuerpo de la solicitud
    return axios.post('http://localhost:8080/article/create', obj)
      .then(res => {
        // Dispatch de la acción de actualización con los datos devueltos de la API
        dispatch( {type: CREATE_ARTICLE,
          payload: res.data});
      });
  };
}
//----------Edit Article------------
export function editArticle(id,obj) {

  return dispatch => {
    return axios
      .patch( `http://localhost:8080/article/update/${id}`,obj)
      .then((res) => {
        dispatch({
          type: UPDATE_ARTICLE,
          payload: res.data,
        });
      })
      .catch((err) => console.log(err));
  };
}

//----------Delete Article------------
export function deleteArticle(id) {
  return dispatch=> {
    return axios
      .delete( `http://localhost:8080/article/delete/${id}`)
      .then((res) => {
        dispatch({
          type: DELETE_ARTICLE,
          payload: res.data,
        });
      })
      .catch((err) => console.log(err));
  };
}

//-----REFRESH-----

export const refresh = () => (dispatch) => {
  dispatch({
    type: REFRESH,
  });
};
//-----HIDE LOADING-----

export const showLoading = () => (dispatch) => {
  dispatch({
    type: SHOW_LOADING,
  });
};