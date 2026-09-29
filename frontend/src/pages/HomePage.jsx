import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api";

function HomePage() {
  const [source, setSource] = useState("");
  const [destination, setDestination] = useState("");
  const [flights, setFlights] = useState([]);

  const navigate = useNavigate();

  const searchFlights = async () => {
    try {
      const res = await api.get(
        `/api/flights/search?source=${source}&destination=${destination}`
      );

      setFlights(res.data);
    } catch (error) {
      console.error(error);
      alert("Unable to search flights");
    }
  };

  return (
    <div style={{ padding: 30 }}>
      <h1>✈ SkyBook Flight Search</h1>

      <input
        placeholder="Source"
        value={source}
        onChange={(e) => setSource(e.target.value)}
      />

      <br /><br />

      <input
        placeholder="Destination"
        value={destination}
        onChange={(e) => setDestination(e.target.value)}
      />

      <br /><br />

      <button onClick={searchFlights}>
        Search Flights
      </button>

      <hr />

      <h2>Available Flights</h2>

      {flights.length === 0 && (
        <p>No flights found. Search for a route.</p>
      )}

      {flights.map((f) => (
        <div
          key={f.id}
          style={{
            border: "1px solid gray",
            padding: 20,
            marginTop: 15,
            width: 400,
            borderRadius: 10,
          }}
        >
          <h3>✈ {f.airline}</h3>

          <p>
            <b>Flight:</b> {f.flightNumber}
          </p>

          <p>
            <b>Route:</b> {f.source} → {f.destination}
          </p>

          <p>
            <b>Price:</b> ₹{f.price}
          </p>

          <p>
            <b>Time:</b> {f.departureTime} - {f.arrivalTime}
          </p>

          <button
            onClick={() => navigate(`/seat/${f.id}`)}
            style={{
              padding: "10px 20px",
              cursor: "pointer",
            }}
          >
            Book Flight
          </button>
        </div>
      ))}
    </div>
  );
}

export default HomePage;
