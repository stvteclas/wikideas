import axios from "axios";

export const GET_ARTICLES = "GET_ARTICLES";



//Get all Articles------------
export function getArticles() {
  return async function (dispatch) {
    return axios
      .get("/article/articles")
      .then((res) => {
        dispatch({
          type: GET_ARTICLES,
          payload: res.data,
        });
      })
      .catch((err) => console.log(err));
  };
}

