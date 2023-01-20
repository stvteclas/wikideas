import React, { useEffect } from 'react';
import s from "../styles/home.module.css"

import oek from "../images/oec.webp"
import SearchBar from '../components/SearchBar';
import { useDispatch } from 'react-redux';
import { showLoading } from '../redux/actions';



const Home = () => {
  const dispatch = useDispatch();


  useEffect(()=>{
dispatch(showLoading())

  },[dispatch])





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