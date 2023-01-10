import React, { useRef, useState, useEffect } from "react";
import { connect } from 'react-redux';
import { IoIosArrowBack } from 'react-icons/io';
import s from "../styles/create.module.css"
import { BsImageFill, BsEyeFill } from "react-icons/bs";
import { useNavigate } from "react-router-dom";
import create from "../images/create.jpg"
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { useDispatch, useSelector } from "react-redux";
import { createArticle, getThemes } from "../redux/actions";

// const allCategories = ["easy care", "tabletop", "pet friendly"];
// const allSize = ["mini", "small", "medium", "large"];

const Create = () => {

    const navigate = useNavigate();
    const dispatch= useDispatch()

    const [errorTitle, setErrorTitle] = useState(false);
    const [errorImage, setErrorImage] = useState(false);
    const[helperTitle, setHelperTitle]= useState("Please enter title")
    const[helperImage, setHelperImage]= useState("Please enter image url")
    const[title,setTitle]=useState("")
    const[image,setImage]=useState("")
    const [errorContent, setErrorContent] = useState(false);
    const[helperContent, setHelperContent]= useState("Please write a content")
    const[content,setContent]=useState("")
    const[categories, setCategories]=useState("all")
    const themes = useSelector((state)=>state.articlesReducers.themes)

    useEffect(()=>{
      dispatch(getThemes())
     },[dispatch])
 console.log(title,content, image,categories)

    const handleTitle = (e) => {
        e.preventDefault()
        setTitle(e.target.value)
        if (title.length > 45) {
          setErrorTitle(true);
          setHelperTitle("title cannot be longer than 45 characters");
        } else if (title.length < 10) {
          setErrorTitle(true);
          setHelperTitle("title cannot be lower than 10 characters");
        } else {
          setErrorTitle(false);
          setHelperTitle("");
        }
     

      };
      const handleImage = (e) => {
        e.preventDefault()
        setImage(e.target.value)
        if (!image.match(/^https?:\/\/.*\/.*\.(png|gif|webp|jpeg|jpg)\??.*$/gim)) {
          setErrorImage(true);
          setErrorImage("Enter a valid image url");
        } else{
          setErrorImage(false);
          setHelperImage("");
        }
     

      };
      const handleContent = (e) => {
        e.preventDefault()
        setContent(e.target.value)
        if (content.length > 1000) {
          setErrorContent(true);
          setHelperContent("title cannot be longer than 1000 characters");
        } else if (content.length < 55) {
          setErrorContent(true);
          setHelperContent("title cannot be lower than 255 characters");
        } else {
          setErrorContent(false);
          setHelperContent("");
        }
     

      };
    const handleBack = () => {
        navigate('/dashboard');
        window.scrollTo(0, {behavior: 'smooth'})
      };
      function handleSelect(e) {
       e.preventDefault()
       setCategories(e.target.value)

      }
      function handleSubmit(e) {
        e.preventDefault();
        let obj={title, text:content,image,theme:categories}
        dispatch(createArticle(obj));
       
       
      }
    return (
        <div className={s.container}>
            <div className={s.button_container}>
        <button onClick={handleBack} className={s.back}>
          <IoIosArrowBack />
        </button>
      </div>
      <div className={s.wraper}>
        <div className={s.left} style={{backgroundImage: `url(${create})`}}>

        </div>
        <div className={s.right} >
            <form action="" className={s.form} onSubmit={(e) => handleSubmit(e)}  >
                <h4>Create article</h4>
                <Box
                 component="form"
                 sx={{
                   '& .MuiTextField-root': { m: 1, width: '100%' },
                 }}
                 noValidate
                 autoComplete="off"
                >
                       <TextField
        error={errorTitle}
          id="filled-textarea"
          label="Title"
          placeholder="Title"
          helperText={helperTitle}
          onChange={(e)=>handleTitle(e)}
        />
            <TextField
             error={errorContent}
          id="outlined-multiline-static"
          label="Content"
          multiline
          rows={2}
          placeholder="Content"
          helperText={helperContent}
          onChange={(e)=>handleContent(e)}
        />
        <TextField
        error={errorImage}
          id="filled-textarea"
          label="Image URL"
          placeholder="Image URL"
          helperText={helperImage}
          onChange={(e)=>handleImage(e)}
        />
         <select
           
           name=""
           id=""
           className={s.select}
           onChange={e=>handleSelect(e)}
         >
          <option value={categories}>CATEGORIES</option>
             {themes.map((t) => (
               <option key={t?.idTheme} value={t?.theme}>
                 {t?.theme}
               </option>
             ))}
         </select>

                </Box>
                <button className={s.create_btn} type="submit" >Create</button>
               
            </form>

        </div>


      </div>

   
        </div>
    );
};

export default connect()(Create);