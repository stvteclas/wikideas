import React, { useEffect } from 'react';
import s from "../styles/articles.module.css";

import image from "../images/hoya.webp";
import {HiChevronDoubleRight} from "react-icons/hi"
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { filterByCategorie, getArticles, getThemes,  showLoading } from '../redux/actions';
import Loader from '../components/Loader';


const Articles = () => {


  const navigate= useNavigate()
  const dispatch= useDispatch()
  const articles = useSelector((state)=>state.articlesReducers.articles)
  const loading = useSelector((state)=>state.articlesReducers.loading)

  useEffect(()=>{

    
   

    dispatch(getArticles())
    dispatch(getThemes())
 
   
  

 
   },[dispatch])




  
  const themes = useSelector((state)=>state.articlesReducers.themes)
  function filterCategories(e) {
    dispatch(filterByCategorie(e.target.value));
 
  }
  function handleClick(e,id) {
    e.preventDefault()
    navigate(`article/${id}`)
    dispatch(showLoading())
  }
    return (
      <div className={s.main}>
        <div className={s.favorites}>
          <div className={s.left} style={{ backgroundImage: `url(${image})` }}>
            {" "}
          </div>
          <div className={s.right}>
            <div className={s.header}>
              <h2>Articles</h2>
              <div className={s.size_container}>
                <select
                  name=""
                  id=""
                  className={s.select}
                  onChange={(e) => filterCategories(e)}
                >
                  <option value="all">CATEGORIES</option>
                  {themes &&
                    themes.map((t) => (
                      <option key={t?.idTheme} value={t?.theme}>
                        {t?.theme}
                      </option>
                    ))}
                </select>
              </div>
            </div>

            <div className={s.favorite_list}>
              {loading === true ? (
                <>
                  {articles?.length > 0 ? (
                    <>
                      {articles &&
                        articles?.map((article) => {
                          return (
                            <div className={s.card} key={article?.id}>
                              <img src={article?.image} alt="" />
                              <div className={s.specs}>
                                <h5>{article?.title}</h5>
                                <p>
                                  {article && article?.text?.slice(0, 35)}...
                                </p>
                                <div
                                  className={s.more}
                                  onClick={(e) =>
                                    handleClick(e,article?.id)
                                  }
                                >
                                  <span>see more</span>
                                  <button className={s.more_btn}>
                                    <HiChevronDoubleRight />
                                  </button>
                                </div>
                                <div></div>
                              </div>
                            </div>
                          );
                        })}
                    </>
                  ) : (
                    <div className={s.empty}>
                      <h1>empty</h1>
                    </div>
                  )}
                </>
              ) : (
                  <Loader/>
             
              )}
            </div>
          </div>
        </div>
      </div>
    );
};

export default Articles;