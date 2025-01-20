import { useAuth } from "./AuthContext";

function Navbar() {
  const authContext = useAuth();
  console.log(authContext);
  function handleLogout() {
    authContext.logout();
  }
  return (
    <div>
      <nav className="navbar navbar-expand-md navbar-light bg-light mb-3 p-1 shadow-sm">
        <a className="navbar-brand m-1 fw-bold text-primary" href="/listtodo">
          Todoooo
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav me-auto">
            <li className="nav-item">
              <a className="nav-link text-dark" href="/">
                Home
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link text-dark" href="/list-todos">
                Todos
              </a>
            </li>
          </ul>
          <ul className="navbar-nav">
            <li className="nav-item">
              {authContext.isAuthenticated ? (
                <a
                  className="nav-link text-danger fw-bold"
                  href="/"
                  onClick={handleLogout}
                >
                  Logout
                </a>
              ) : (
                <a className="nav-link text-danger fw-bold" href="/login">
                  Login
                </a>
              )}
            </li>
          </ul>
        </div>
      </nav>
    </div>
  );
}

export default Navbar;
