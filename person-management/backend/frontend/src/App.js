import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import ListPersonComponent from './components/ListPersonComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreatePersonComponent from './components/CreatePersonComponent';
import ViewPersonComponent from './components/ViewPersonComponent';

function App() {
  // this is the react functional componenet
  return (
    // jsx code [JavaScript xml code].
    <div>
      <Router>
      <HeaderComponent />
      <div className="container">
        <Switch>
          <Route path = "/" exact component = {ListPersonComponent}></Route>
          <Route path = "/allEmployees" component = {ListPersonComponent}></Route>
          <Route path = "/addEmployee/:id" component = {CreatePersonComponent}></Route>
          <Route path = "/viewEmployee/:id" component = {ViewPersonComponent}></Route>
        </Switch>
      </div>
      <FooterComponent />
      </Router>
    </div>
  );
}

export default App;
