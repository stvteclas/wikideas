import { Route, Routes } from 'react-router-dom';
import './App.css';
import Nav from './components/Nav';
import Article from './pages/Article';
import Create from './pages/Create';
import Home from './pages/Home';


function App() {
  return (
    <>
     <Nav/>
    <Routes>
      
      <Route path='/' element={<Home/>}/>
      <Route path='/article/:id' element={<Article/>}/>
      <Route path='/create' element={<Create/>}/>
    </Routes>
    </>
  );
}

export default App;
