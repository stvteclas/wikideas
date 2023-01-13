import React, { useEffect } from 'react';
import s from "../styles/article.module.css"
import { IoIosArrowBack } from "react-icons/io";
import {FiEdit}from "react-icons/fi";
import {MdDeleteForever}from "react-icons/md";
import { useNavigate, useParams } from "react-router-dom";
import { useDispatch, useSelector } from 'react-redux';
import { deleteArticle, getArticleById } from '../redux/actions';
import Swal from 'sweetalert2'
const Article = () => {
    const navigate = useNavigate();
    const { id } = useParams();
    const dispatch = useDispatch()

    useEffect(()=>{
     dispatch(getArticleById(id))
  
    },[dispatch,id])
    function handleDelete(e) {
      e.preventDefault();
      Swal.fire({
        title: 'Are you sure you want to delete this article?',
        icon:"question",
        showConfirmButton:false,
        showDenyButton: true,
        showCancelButton: true,
        denyButtonText: `Delete`,
      }).then((result) => {

        if (result.isDenied) {
        
              
      dispatch(deleteArticle(id))
          Swal.fire('Deleted!', '', 'success')
          navigate("/articles")
          window.location.reload(false);
          
        } 
      })
      
   
 

 
    }
  
    const article = useSelector((state)=>state.articlesReducers.article)


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
         <span>Publish date: {article.creationDate? `${article?.creationDate[0]} /${article?.creationDate[1]<9? `0 ${article?.creationDate[1]}`: article?.creationDate[1] }/ ${ article?.creationDate[2]} `:null}</span>
         <div className={s.btn_container}>
            <button className={s.btn_edit} onClick={()=>navigate(`/edit/${id}`)}>
                <FiEdit/>
            </button>
            <button className={s.btn_delete} onClick={(e)=>handleDelete(e)} >
                <MdDeleteForever/>
            </button>

         </div>
          
            
        </div>
      </div>
    </div>
    );
};

export default Article;