import React from 'react';
import s from "../styles/loader.module.css"
import logo from "../images/oec-white.webp"

const Loader = () => {
    return (
        <div className={s.loading}>
        <img src={logo} className={s.monstera} alt="" />
         <img className={s.loader} src="https://i.pinimg.com/originals/49/23/29/492329d446c422b0483677d0318ab4fa.gif" alt="" />
     </div>
    );
};

export default Loader;