import React, { useState } from "react";
import { useSelector,useDispatch } from "react-redux";
import s from "../styles/search.module.css"
import {TbSearch} from "react-icons/tb"
import {useNavigate  } from "react-router-dom";
import { refresh } from '../redux/actions';

const SearchBar = () => {
    const [searchTerm, setSearchTerm] = useState("");
    const [suggestions, setSuggestions] = useState([]);

    const articles = useSelector((state) => state.articlesReducers.articles);
const navigate= useNavigate()
const dispatch= useDispatch()

    const handleChange = (event) => {
      setSearchTerm(event.target.value);
      const filteredArticles = articles.filter((article) =>
        article.title.toLowerCase().includes(searchTerm.toLowerCase())
      );
      setSuggestions(filteredArticles);
    };
    function handleClick(e,id) {
      e.preventDefault();
      navigate(`/articles/article/${id}`)
      
     
    }

    return (
       <div action="" className={s.container} >
        <div className={s.searchbar}>

        <input type="text" placeholder='Search...' value={searchTerm} onChange={handleChange}/>
        <button>
            <TbSearch/>
        </button>
        </div>

       {
        searchTerm.length>0&& suggestions.length>0?

       <ul className={s.ul_suggestions}>
        {suggestions.map((suggestion) => (
            <div key={suggestion.id} className={s.suggestion} onClick={(e)=>handleClick(e,suggestion?.id)}>
                <img src={suggestion.image} alt="" />
              <li>{suggestion.title}</li>
            </div>
          
        ))}
      </ul>:null
       }
       </div>
     
    );
};

export default SearchBar;