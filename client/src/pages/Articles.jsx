import React from 'react';
import s from "../styles/articles.module.css";
import image from "../images/hoya.png";
import {HiChevronDoubleRight} from "react-icons/hi"

const Articles = () => {
    let articles=[{id:1,name:"Marte pudo haber sido azul, como la Tierra",text:"Marte azul (y no, no hablamos de la trilogía marciana de ciencia ficción de Kim Stanley Robinson). Ese es el tentador hallazgo que los investigadores de la Universidad Estatal de Arizona y la Universidad de Stanford han anunciado en su último estudio recientemente publicado en la revista Earth and Planetary Science Letters. Los científicos han visto evidencia de los primeros lagos, ríos e incluso océanos en el pasado de Marte. Pero ahora, los nuevos modelos atmosféricos más los datos del rover Curiosity respaldan la posibilidad de una atmósfera temprana de hidrógeno molecular.", image:"https://i.blogs.es/d8e162/650_1000_eso1509b/1366_2000.jpg"},
    {id:2,name:"ChatGPT: cómo usar de forma sencilla esta herramienta de IA",text:"La herramienta de inteligencia artificial llamada ChatGPT ha sido denominado como el chatbot de inteligencia artificial más poderoso de la historia. En los últimos días se ha convertido en el chatbot favorito del planeta, ya que ha acumulado millones de usuarios en pocos días tras su lanzamiento público.", image:"https://s.yimg.com/ny/api/res/1.2/SWMg2BadqTofWfb6qva04A--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyMDA7aD03OTk-/https://media.zenfs.com/es/lanacion.com.ar/97ff5cf6796eb5ca1f1711d75f984825"},
    {id:2,name:"ChatGPT: cómo usar de forma sencilla esta herramienta de IA",text:"La herramienta de inteligencia artificial llamada ChatGPT ha sido denominado como el chatbot de inteligencia artificial más poderoso de la historia. En los últimos días se ha convertido en el chatbot favorito del planeta, ya que ha acumulado millones de usuarios en pocos días tras su lanzamiento público.", image:"https://s.yimg.com/ny/api/res/1.2/SWMg2BadqTofWfb6qva04A--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyMDA7aD03OTk-/https://media.zenfs.com/es/lanacion.com.ar/97ff5cf6796eb5ca1f1711d75f984825"}
]
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
          >
            <option value="all">All categories</option>
            <option value="mini">Cience</option>
            <option value="small">Biology</option>
            <option value="medium">History</option>
            <option value="large">Technology</option>
          </select>
        </div>

                </div>
                
              
              <div className={s.favorite_list}>
              {articles?.map((article) => {
                return (
                  <div className={s.card} key={article.id}>
                    <img src={article.image} alt="" />
                    <div className={s.specs}>
                      <h5>{article.name}</h5>
                      <p>{article.text.slice(0,100)}...</p>
                      <div className={s.more}>
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