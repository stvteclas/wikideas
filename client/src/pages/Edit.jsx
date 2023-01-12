import React, { useState } from "react";
import { connect } from 'react-redux';
import { IoIosArrowBack } from 'react-icons/io';
import s from "../styles/create.module.css"

import { useNavigate, useParams } from "react-router-dom";
import create from "../images/edit.webp"
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { useDispatch, useSelector } from "react-redux";
import { editArticle } from "../redux/actions";
import Swal from 'sweetalert2'

const Edit = () => {
    
    const navigate = useNavigate();
    const dispatch= useDispatch()

  
    const[title,setTitle]=useState("")
    const [errorTitle, setErrorTitle] = useState(false);

    const[content,setContent]=useState("")
    const [errorContent, setErrorContent] = useState(false);


    const[image,setImage]=useState("")
    const [errorImage, setErrorImage] = useState(false);
  
    const[categories, setCategories]=useState("all")
    const themes = useSelector((state)=>state.articlesReducers.themes)
    const { id } = useParams();

    
  

  
    const handleTitle = (e) => {
      e.preventDefault()
      setTitle(e.target.value)
      const inputValue=e.target.value
      if (inputValue.length>=4 && inputValue.length<=45) {
          setErrorTitle(false)
      }else{
          setErrorTitle(true)
      }
  };
  
  const handleImage = (e) => {
    e.preventDefault()
    setImage(e.target.value)
    const imageUrlRegex = /^https?:\/\/.*\/.*\.(png|gif|webp|jpeg|jpg)\??.*$/gim
    if (!image.match(imageUrlRegex)) {
        setErrorImage(false);
    } else{
        setErrorImage(true);
    }
};

const handleContent = (e) => {
  e.preventDefault()
  setContent(e.target.value)
  const inputValue=e.target.value
  if (inputValue.length>=255 && inputValue.length<=1000) {
      setErrorContent(false)
  }else{
      setErrorContent(true)
  }
};
    const handleBack = () => {
        navigate(-1);
        window.scrollTo(0, {behavior: 'smooth'})
      };
      function handleSelect(e) {
       e.preventDefault()
       setCategories(e.target.value)

      }
      function handleSubmit(e) {
        e.preventDefault();
        Swal.fire({
          title: 'Are you sure you want to save the changes?',
          icon:"question",
          showCancelButton: true,
          confirmButtonText: 'Save',
        }).then((result) => {
  
          if (result.isConfirmed) {
          
            let obj={title, text:content,image,theme:categories}
            dispatch(editArticle(obj,id));
            Swal.fire('Saved!', '', 'success')
            navigate("/articles")
            window.location.reload(false);
            
          } 
        })
        
     
   
       
       
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
            <h4>Edit article</h4>
            <Box
             component="form"
             sx={{
               '& .MuiTextField-root': { m: 1, width: '100%' },
             }}
             noValidate
             autoComplete="off"
            >
                   <TextField
                   value={title}
  
      id="filled-textarea"
      label="Title"
      placeholder="Title"
      error={errorTitle}
      helperText={errorTitle&& title.length<=4?"title must longer than 4 characters":title.length>=45?"title must lower than 45 characters":""}
      onChange={(e)=>handleTitle(e)}
    />
        <TextField
        value={content}
    
      id="outlined-multiline-static"
      label="Content"
      multiline
      rows={2}
      placeholder="Content"
      error={errorContent}
      helperText={errorContent&& content.length<=255?"content must longer than 255 characters":content.length>=1000?"content must lower than 1000 characters":""}
      onChange={(e)=>handleContent(e)}
    />
    <TextField
    value={image}

      id="filled-textarea"
      label="Image URL"
      placeholder="Image URL"
      error={errorImage}
      helperText={errorImage? "Enter a valid image url":""}
      onChange={(e)=>handleImage(e)}
    />
     <select
  
       
       name=""
       id=""
       className={s.select}
       onChange={e=>handleSelect(e)}
     >
      <option value={categories}>CATEGORIES</option>
         {themes&&themes.map((t) => (
           <option key={t?.idTheme} value={t?.theme}>
             {t?.theme}
           </option>
         ))}
     </select>

            </Box>
            <button className={s.create_btn}  type="submit" >Edit</button>
           
        </form>

    </div>


  </div>


    </div>
    );
};

export default connect()(Edit);