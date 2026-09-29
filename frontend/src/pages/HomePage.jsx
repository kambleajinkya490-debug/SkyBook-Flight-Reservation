import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api";

function HomePage() {
  const [source, setSource] = useState("");
  const [destination, setDestination] = useState("");
  const [flights, setFlights] = useState([]);

  const navigate = useNavigate();

  const token = localStorage.getItem("token");

  const logout = () => {
    localStorage.removeItem("token");
    alert("Logged out successfully!");
    navigate("/login");
  };

  const searchFlights = async () => {
    if (!source || !destination) {
      alert("Please enter source and destination");
      return;
    }

    try {
      const res = await api.get(
        `/api/flights/search?source=${source}&destination=${destination}`
      );

      setFlights(res.data);
    } catch (error) {
      console.error("Search error:", error);
      alert("Unable to search flights");
    }
  };

  return (
    <div style={{ padding: 30 }}>

      {/* Authentication Buttons */}
      <div style={{ marginBottom: 20 }}>

        {token ? (
          <button
            onClick={logout}
            style={{
              padding: "10px 20px",
              cursor: "pointer",
            }}
          >
            🚪 Logout
          </button>
        ) : (
          <button
            onClick={() => navigate("/login")}
            style={{
              padding: "10px 20px",
              cursor: "pointer",
            }}
          >
            🔐 Login
          </button>
        )}

      </div>

      <h1>✈ SkyBook Flight Search</h1>

      {/* Search Section */}

      <div style={{ marginTop: 20 }}>

        <input
          type="text"
          placeholder="Source"
          value={source}
          onChange={(e) => setSource(e.target.value)}
          style={{
            padding: 10,
            width: 250,
            marginRight: 10,
          }}
        />

        <input
          type="text"
          placeholder="Destination"
          value={destination}
          onChange={(e) => setDestination(e.target.value)}
          style={{
            padding: 10,
            width: 250,
            marginRight: 10,
          }}
        />

        <button
          onClick={searchFlights}
          style={{
            padding: "10px 20px",
            cursor: "pointer",
          }}
        >
          Search Flights
        </button>

      </div>

      <hr style={{ marginTop: 30 }} />

      <h2>Available Flights</h2>

      {flights.length === 0 && (
        <p>No flights found. Search for a route.</p>
      )}

      {/* Flight List */}

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
            <b>Departure:</b> {f.departureTime}
          </p>

          <p>
            <b>Arrival:</b> {f.arrivalTime}
          </p>

          <button
            onClick={() => navigate(`/seat/${f.id}`)}
            style={{
              padding: "10px 20px",
              cursor: "pointer",
            }}
          >
            🪑 Book Flight
          </button>

        </div>
      ))}

    </div>
  );
}

export default HomePage;
