import { useState } from "react";
import api from "../api";

function HomePage() {
  const [source, setSource] = useState("");
  const [destination, setDestination] = useState("");
  const [flights, setFlights] = useState([]);

  const searchFlights = async () => {
    const res = await api.get(
      `/api/flights/search?source=${source}&destination=${destination}`
    );
    setFlights(res.data);
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

      <button onClick={searchFlights}>Search Flights</button>

      <hr />

      {flights.map((f) => (
        <div
          key={f.id}
          style={{
            border: "1px solid gray",
            padding: 15,
            marginTop: 10,
          }}
        >
          <h3>{f.airline}</h3>
          <p>{f.flightNumber}</p>
          <p>{f.source} → {f.destination}</p>
          <p>₹ {f.price}</p>
          <p>{f.departureTime} - {f.arrivalTime}</p>
        </div>
      ))}
    </div>
  );
}

export default HomePage;
