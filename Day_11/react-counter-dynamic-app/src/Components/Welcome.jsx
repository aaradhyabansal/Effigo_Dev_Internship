import { useNavigate, useParams } from "react-router-dom";

function Welcome() {
  const { username } = useParams();
  const navigate = useNavigate();
  function handleTodo() {
    navigate(`/listtodos/${username}`);
  }
  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h1>Welcome to My Website! {username}</h1>
      <p>This is a basic React welcome page.</p>
      <button onClick={handleTodo}>Manage Your Todos</button>
    </div>
  );
}

export default Welcome;
