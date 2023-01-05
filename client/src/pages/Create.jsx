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

    const handleTitle = (e) => {
        e.preventDefault()
        setTitle(e.target.value)
        if (title.length>45) {
            setErrorTitle(true)
            setHelperTitle("title cannot be longer than 45 characters")
   
        }else{
            setErrorTitle(false)
            setHelperTitle("")
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
                   '& .MuiTextField-root': { m: 1, width: '25ch' },
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

                </Box>
               
            </form>

        </div>


      </div>

   
        </div>
    );
};

export default Create;