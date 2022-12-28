import { Route, Routes } from 'react-router-dom';
import './App.css';
import Nav from './components/Nav';
import About from './pages/About';
import Article from './pages/Article';
import Articles from './pages/Articles';
import Create from './pages/Create';
import Home from './pages/Home';


function App() {
  return (
    <>
     <Nav/>
    <Routes>
      
      <Route path='/' element={<Home/>}/>
      <Route path='/articles' element={<Articles/>}/>
      <Route path='/about' element={<About/>}/>
      <Route path='/article/:id' element={<Article/>}/>
      <Route path='/create' element={<Create/>}/>
    </Routes>
    </>
  );
}

export default App;
