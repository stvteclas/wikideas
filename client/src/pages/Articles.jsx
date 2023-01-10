import React from 'react';
import s from "../styles/articles.module.css";
import image from "../images/hoya.png";
import {HiChevronDoubleRight} from "react-icons/hi"
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { filterByCategorie } from '../redux/actions';

const Articles = () => {
  const navigate= useNavigate()
  const dispatch= useDispatch()


  const articles = useSelector((state)=>state.articlesReducers.articles)
  const themes = useSelector((state)=>state.articlesReducers.themes)
  function filterCategories(e) {
    dispatch(filterByCategorie(e.target.value));
 
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
            onChange={e=>filterCategories(e)}
          >
           <option value="all">CATEGORIES</option>
              {themes&&themes.map((t) => (
                <option key={t?.idTheme} value={t?.theme}>
                  {t?.theme}
                </option>
              ))}
          </select>
        </div>

                </div>
                
              
              <div className={s.favorite_list}>
              {articles &&articles?.map((article) => {
                return (
                  <div className={s.card} key={article?.id}>
                    <img src={article?.image} alt="" />
                    <div className={s.specs}>
                      <h5>{article?.title}</h5>
                      <p >{article&&article?.text?.slice(0,50)}...</p>
                      <div className={s.more} onClick={()=>navigate(`article/${article?.id}`)}>
                        <span>see more</span>
                        <button className={s.more_btn}><HiChevronDoubleRight/></button>

                      </div>
                      <div>
                      
                      </div>
                    </div>
                  </div>
                );
              })}
              
              </div>
            </div>
          </div>
  
      </div>
    );
};

export default Articles;