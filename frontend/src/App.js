import React from "react";
import { BrowserRouter } from "react-router-dom";
import {SimpleRoutes} from "./SimpleRoutes";
import 'materialize-css';

function App() {
  //TODO
  const isAuth = true;
  const routes = SimpleRoutes(isAuth);

  return (
    <BrowserRouter>
      {routes}
    </BrowserRouter>
  );
}

export default App;
