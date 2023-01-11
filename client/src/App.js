import React,{ useState, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { Route, Routes } from 'react-router-dom';
import './App.css';
import Nav from './components/Nav';
import About from './pages/About';
import Article from './pages/Article';
import Articles from './pages/Articles';
import Create from './pages/Create';
import Edit from './pages/Edit';
import Home from './pages/Home';
import { getArticles, getThemes } from './redux/actions';


function App() {

  const dispatch = useDispatch()

  useEffect(()=>{
   dispatch(getArticles())
   dispatch(getThemes())

  },[dispatch])


  return (
    <>
    <Nav/>
    <Routes>
      
      <Route path='/' element={<Home/>}/>
      <Route path='/create' element={<Create/>}/>
      <Route path='/edit/:id' element={<Edit/>}/>
      <Route path='/articles' element={<Articles/>}/>
      <Route path='/about' element={<About/>}/>
      <Route path='/articles/article/:id' element={<Article/>}/>
      
    </Routes>
    </>
  );
}

export default App;
