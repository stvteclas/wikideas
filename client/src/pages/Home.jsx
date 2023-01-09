import React from 'react';
import s from "../styles/home.module.css"
import castle from "../images/castle.jpg"
import oek from "../images/oec.png"
import SearchBar from '../components/SearchBar';


const Home = () => {
    return (
      <div
        className={s.container}
        
      >
        {/* <div className={s.image_container}>
          <img src={lady} className={s.lady} alt="" />
        </div> */}
        <div className={s.search}>
            <div className={s.brand}>
                <img src={oek} alt="" />
                <h2>The Open Collaboration Encyclopedia</h2>

            </div>
            <SearchBar/>

        </div>
      </div>
    );
};

export default Home;