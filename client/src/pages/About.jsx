import React from 'react';
import s from "../styles/about.module.css"
import street from "../images/street.jpg"
import man from "../images/men.png"
import oek from "../images/oec-white.png"
import{AiFillGithub}from "react-icons/ai"
const About = () => {
    return (
      <div
        className={s.container}
        style={{ backgroundImage: `url(${street})` }}
      >
        <div className={s.wraper}>
        <div className={s.right}>
          <img src={man} alt="man" className={s.man} />
            
          </div>
        
          <div className={s.left}>
            <div className={s.left_container}>
            <div className={s.logo}>
              <img src={oek} alt="" />
              <h4>The Open Collaboration Encyclopedia</h4>
              <p>The original and comprehensive open-sourced encyclopedia of collaborative models and networks. Filled with just about everything you would need to know as an entry point into the emerging open, non-hierarchical, participatory open collaboration paradigm </p>
              <div className={s.btn_container}>
              <span>SOURCE</span>
                <AiFillGithub className={s.btn}/>

              </div>
            </div>

            </div>
          </div>
         
        </div>
      </div>
    );
};

export default About;