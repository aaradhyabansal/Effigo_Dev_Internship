import { BrowserRouter } from "react-router-dom";
import Batch from "./BatchApp/Batch";
function App() {
  return (
    <div>
      <BrowserRouter>
        <Batch />
      </BrowserRouter>
    </div>
  );
}

export default App;
