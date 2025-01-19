function Counter(properties) {
  return (
    <div className="counter">
      <h1>You clicked me{properties.count} times</h1>
      <button
        onClick={() =>
          properties.setCount(
            properties.count + parseInt(properties.property1, 10)
          )
        }
      >
        Increase by {properties.property1}
      </button>
      <button
        onClick={() => {
          if (properties.count > 0)
            properties.setCount(
              properties.count - parseInt(properties.property1, 10)
            );
        }}
      >
        Decrease {properties.property1}
      </button>
    </div>
  );
}

export default Counter;
