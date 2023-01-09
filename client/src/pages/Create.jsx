import React, { useRef, useState, useEffect } from "react";
import { IoIosArrowBack } from 'react-icons/io';
import s from "../styles/create.module.css"
import { BsImageFill, BsEyeFill } from "react-icons/bs";
import { useNavigate } from "react-router-dom";
import create from "../images/create.jpg"
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';

// const allCategories = ["easy care", "tabletop", "pet friendly"];
// const allSize = ["mini", "small", "medium", "large"];

const Create = () => {

    const navigate = useNavigate();

    const [errorTitle, setErrorTitle] = useState(false);
    const[helperTitle, setHelperTitle]= useState("Please enter title")
    const[title,setTitle]=useState("")
    const [errorContent, setErrorContent] = useState(false);
    const[helperContent, setHelperContent]= useState("Please write a content")
    const[content,setContent]=useState("")

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
            <form action="" className={s.form}  >
                <h4>Create article</h4>
                <Box
                 component="form"
                 sx={{
                   '& .MuiTextField-root': { m: 2, width: '100%' },
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
          rows={4}
          placeholder="Content"
          helperText={helperContent}
          onChange={(e)=>handleContent(e)}
        />

                </Box>
               
            </form>

        </div>


      </div>

   
        </div>
    );
};

export default Create;