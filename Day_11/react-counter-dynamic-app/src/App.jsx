import "./App.css";
import Counter from "./Components/counter";
import { useState } from "react";
function App() {
  const [count1, setCount1] = useState(0);
  const [count2, setCount2] = useState(0);
  const [count3, setCount3] = useState(0);
  const total = count1 + count2 + count3;
  return (
    <div>
      Hello World
      <Counter property1="1" count={count1} setCount={setCount1}></Counter>
      <Counter property1="2" count={count2} setCount={setCount2}></Counter>
      <Counter property1="3" count={count3} setCount={setCount3}></Counter>
      <h2>Your total Sum is= {total}</h2>
    </div>
  );
}

export default App;
