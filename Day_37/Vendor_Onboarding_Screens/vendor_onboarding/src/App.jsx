import { BrowserRouter } from "react-router-dom";
import Vendor from "./Vendor";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Vendor />
      </BrowserRouter>
    </div>
  );
}

export default App;
