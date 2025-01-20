import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import { useState } from "react";
import {
  retrieveHelloWorld,
  retrieveHelloWorldBean,
  retrieveHelloWorldPathVar,
} from "../api/HelloWorldApiService";
function Welcome() {
  const { username } = useParams();
  const [msg, setMsg] = useState(null);
  const navigate = useNavigate();
  function handleTodo() {
    navigate(`/listtodos/${username}`);
  }
  function helloWorldRestApi() {
    retrieveHelloWorld()
      .then((response) => succesfulRestApi(response))
      .catch((error) => errorRestApi(error))
      .finally(() => console.log("cleanUp"));
  }
  function helloWorldBeanRestApi() {
    retrieveHelloWorldBean()
      .then((response) => succesfulRestApi(response))
      .catch((error) => errorRestApi(error))
      .finally(() => console.log("cleanUp"));
  }
  function helloWorldPathVarApi() {
    retrieveHelloWorldPathVar(username)
      .then((response) => succesfulRestApi(response))
      .catch((error) => errorRestApi(error))
      .finally(() => console.log("cleanUp"));
  }
  function succesfulRestApi(response) {
    setMsg(response.data.message);
    console.log(response);
  }
  function errorRestApi(error) {
    console.log(error);
  }
  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h1>Welcome to My Website! {username}</h1>
      <p>This is a basic React welcome page.</p>
      <button onClick={handleTodo}>Manage Your Todos</button>
      <button className="btn btn-success m-5" onClick={helloWorldRestApi}>
        Go to Hello World Page
      </button>
      <div className="text-info">{msg}</div>
      <button className="btn btn-success m-5" onClick={helloWorldBeanRestApi}>
        Go to Hello World Bean Page
      </button>
      <div className="text-info">{msg}</div>
      <button className="btn btn-success m-5" onClick={helloWorldPathVarApi}>
        Go to Hello World Path Variable Page
      </button>
      <div className="text-info">{msg}</div>
    </div>
  );
}

export default Welcome;
