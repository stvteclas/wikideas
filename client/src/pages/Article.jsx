import React, { useEffect } from 'react';
import s from "../styles/article.module.css"
import { IoIosArrowBack } from "react-icons/io";
import {FiEdit}from "react-icons/fi";
import {MdDeleteForever}from "react-icons/md";
import { useNavigate, useParams } from "react-router-dom";
import plans from "../images/marte.webp";
import { useDispatch, useSelector } from 'react-redux';
import { getArticleById } from '../redux/actions';
const Article = () => {
    const navigate = useNavigate();
    const { id } = useParams();
    const dispatch = useDispatch()

    useEffect(()=>{
     dispatch(getArticleById(id))
  
    },[dispatch])
    const article = useSelector((state)=>state.articlesReducers.article)
console.log(article)

    return (
        <div className={s.container}>
        <div className={s.button_container}>
            <button onClick={()=>navigate(-1)} className={s.back}>
              <IoIosArrowBack/>
            </button>

          </div>
      <div className={s.wraper}>
        <div className={s.image}>
          <img src={article?.image} alt="" />
        </div>
        <div className={s.register}>
          <h5 className={s.title}>{article?.title}</h5>
         <p  className={s.welcome}>{article?.text} </p>
         <span>Publish date: {article.creationDate? article?.creationDate.slice(0,10):null}</span>
         <div className={s.btn_container}>
            <button className={s.btn_edit}>
                <FiEdit/>
            </button>
            <button className={s.btn_delete}>
                <MdDeleteForever/>
            </button>

         </div>
          
            
        </div>
      </div>
    </div>
    );
};

export default Article;