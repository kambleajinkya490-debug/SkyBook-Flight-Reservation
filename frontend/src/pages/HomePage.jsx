import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import api from "../api";

function HomePage() {
  const [flights, setFlights] = useState([]);

  useEffect(() => {
    api.get("/flights")
      .then((res) => setFlights(res.data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <div
      style={{
        background: "#eef6ff",
        minHeight: "100vh",
        padding: "30px",
      }}
    >
      <h1>✈ SKYBOOK</h1>
      <h2>Available Flights</h2>

      {flights.map((f) => (
        <div
          key={f.id}
          style={{
            background: "white",
            padding: 20,
            margin: "15px 0",
            borderRadius: 10,
          }}
        >
          <h3>{f.flightNumber}</h3>
          <p>{f.source} → {f.destination}</p>
          <p>₹ {f.price}</p>

          <Link to={`/seat/${f.id}`}>
            <button>Book Seat</button>
          </Link>
        </div>
      ))}
    </div>
  );
}

export default HomePage;
