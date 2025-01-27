import { BrowserRouter } from "react-router-dom";
import "./App.css";
import Todo from "./TodoApp/Todo";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Todo></Todo>
      </BrowserRouter>
    </div>
  );
}

export default App;
