import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from "react-router-dom";
import './index.css';
import App from './App';
import {Provider} from "react-redux"
import store from './redux/store/index.js';
import axios from 'axios';

axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';



const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <Provider store={store}>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </Provider>
);

