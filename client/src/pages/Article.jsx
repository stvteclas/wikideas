import React from 'react';
import s from "../styles/article.module.css"
import { IoIosArrowBack } from "react-icons/io";
import {FiEdit}from "react-icons/fi";
import {MdDeleteForever}from "react-icons/md";
import { useNavigate } from "react-router-dom";
import plans from "../images/marte.webp";
const Article = () => {
    const navigate = useNavigate();
    return (
        <div className={s.container}>
        <div className={s.button_container}>
            <button onClick={()=>navigate(-1)} className={s.back}>
              <IoIosArrowBack/>
            </button>

          </div>
      <div className={s.wraper}>
        <div className={s.image}>
          <img src={plans} alt="" />
        </div>
        <div className={s.register}>
          <h5 className={s.title}>Marte pudo haber sido azul, como la Tierra</h5>
         <p  className={s.welcome}>
         Marte azul y no, no hablamos de la trilogía marciana de ciencia ficción de Kim Stanley Robinson. Ese es el tentador hallazgo que los investigadores de la Universidad Estatal de Arizona y la Universidad de Stanford han anunciado en su último estudio recientemente publicado en la revista Earth and Planetary Science Letters.
         Los científicos han visto evidencia de los primeros lagos, ríos e incluso océanos en el pasado de Marte. Pero ahora, los nuevos modelos atmosféricos más los datos del rover Curiosity respaldan la posibilidad de una atmósfera temprana de hidrógeno molecular.
         </p>
         <span>This article was published on July 4, 2007.</span>
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