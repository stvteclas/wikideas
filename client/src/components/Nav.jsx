import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import oec from "../images/oec-white.png"
import"../styles/nav.css"

const Nav = () => {
  const navigate= useNavigate()

    return (
        <>
        <nav className="navbar navbar-expand-lg navbar-light bg-light fixed-top bg-transparent">
        <div className="container">
          <img src={oec} alt="" className="img_sun" />
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
              <li className="nav-item">
                <Link className="nav-link" to="/">HOME</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/about">ABOUT</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/articles">ARTICLES</Link>
              </li>
              <li className="nav-item">
                
               <button type='button' className='collaborate' onClick={()=>navigate("/create")}>CREATE ARTICLE </button>
              </li>
              
            </ul>
            
          </div>
        </div>
      </nav>
        </>
    );
};

export default Nav;