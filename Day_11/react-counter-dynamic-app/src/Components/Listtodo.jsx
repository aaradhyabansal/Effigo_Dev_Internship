import { useParams } from "react-router-dom";

function Listtodo() {
  const { username } = useParams();
  const todos = [
    {
      id: 1,
      username: "aaradhya",
      description: "Complete React project",
      targetDate: "2025-02-15",
      isDone: false,
    },
    {
      id: 2,
      username: "john_doe",
      description: "Write unit tests",
      targetDate: "2025-02-10",
      isDone: true,
    },
    {
      id: 3,
      username: "sarah_98",
      description: "Attend team meeting",
      targetDate: "2025-01-25",
      isDone: false,
    },
    {
      id: 4,
      username: "michael123",
      description: "Fix bugs in API",
      targetDate: "2025-02-05",
      isDone: true,
    },
    {
      id: 5,
      username: "jane_doe",
      description: "Design new UI components",
      targetDate: "2025-02-20",
      isDone: false,
    },
    {
      id: 6,
      username: "susan_91",
      description: "Update documentation",
      targetDate: "2025-01-30",
      isDone: true,
    },
    {
      id: 7,
      username: "david_lee",
      description: "Deploy new features",
      targetDate: "2025-02-10",
      isDone: false,
    },
    {
      id: 8,
      username: "emily_jones",
      description: "Optimize database queries",
      targetDate: "2025-02-01",
      isDone: false,
    },
    {
      id: 9,
      username: "adam_smith",
      description: "Integrate third-party API",
      targetDate: "2025-02-18",
      isDone: true,
    },
    {
      id: 10,
      username: "lucy_williams",
      description: "Conduct code review",
      targetDate: "2025-01-28",
      isDone: false,
    },
  ];

  return (
    <div className="d-flex flex-column align-items-center justify-content-center vh-100">
      <h1 className="mb-4">{username}, Tasks To Do Are</h1>
      <div className="table-responsive" style={{ width: "80%" }}>
        <table className="table table-striped table-dark text-center">
          <thead>
            <tr>
              <th>Id</th>
              <th>Description</th>
              <th>Target Date</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {todos.map((todo) => (
              <tr key={todo.id}>
                <td>{todo.id}</td>
                <td>{todo.description}</td>
                <td>{todo.targetDate}</td>
                <td>{todo.isDone ? "Done" : "Pending"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Listtodo;
