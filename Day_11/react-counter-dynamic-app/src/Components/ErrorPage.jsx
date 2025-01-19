function ErrorPage() {
  return (
    <div
      style={{
        textAlign: "center",
        marginTop: "50px",
        fontFamily: "Arial, sans-serif",
      }}
    >
      <h1 style={{ fontSize: "48px", color: "red" }}>404</h1>
      <h2>Oops! You have hit the wrong URL.</h2>
      <p>
        The page {"you're"} looking for {"doesn't"} exist.
      </p>
      <a href="/" style={{ color: "blue", textDecoration: "underline" }}>
        Go back to Homepage
      </a>
    </div>
  );
}

export default ErrorPage;
