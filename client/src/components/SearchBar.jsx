import React from 'react';
import s from "../styles/search.module.css"
import {TbSearch} from "react-icons/tb"

const SearchBar = () => {
    return (
       <form action="" className={s.container}>
        <input type="text" placeholder='Search...'/>
        <button>
            <TbSearch/>
        </button>

       </form>
    );
};

export default SearchBar;