import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import api from "../api";

function SeatPage() {
  const { flightId } = useParams();
  const navigate = useNavigate();

  const [selected, setSelected] = useState("");
  const [passengerName, setPassengerName] = useState("");
  const [loading, setLoading] = useState(false);

  const seats = Array.from({ length: 30 }, (_, i) => i + 1);

  const bookFlight = async () => {
    if (!selected) {
      alert("Please select a seat");
      return;
    }

    if (!passengerName.trim()) {
      alert("Please enter passenger name");
      return;
    }

    try {
      setLoading(true);

      const response = await api.post("/api/bookings", {
        flightId: Number(flightId),
        passengerName: passengerName,
        seatNumber: String(selected)
      });

      console.log("Booking Response:", response.data);

      alert("Flight booked successfully!");

      navigate("/success");
    } catch (error) {
      console.error("Booking Error:", error);
      alert("Booking failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: 30 }}>
      <h1>🪑 Select Your Seat</h1>

      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(6, 60px)",
          gap: 10,
        }}
      >
        {seats.map((seat) => (
          <button
            key={seat}
            onClick={() => setSelected(seat)}
            style={{
              height: 50,
              background:
                selected === seat ? "#22c55e" : "#e5e7eb",
              border: "none",
              borderRadius: 8,
              cursor: "pointer",
            }}
          >
            {seat}
          </button>
        ))}
      </div>

      <h2 style={{ marginTop: 20 }}>
        Selected Seat: {selected || "None"}
      </h2>

      <div style={{ marginTop: 30 }}>
        <h2>Passenger Details</h2>

        <input
          type="text"
          placeholder="Passenger Name"
          value={passengerName}
          onChange={(e) => setPassengerName(e.target.value)}
          style={{
            padding: 10,
            width: 250,
            marginRight: 10,
          }}
        />

        <button
          onClick={bookFlight}
          disabled={loading}
          style={{
            padding: "10px 20px",
            cursor: "pointer",
          }}
        >
          {loading ? "Booking..." : "Book Now"}
        </button>
      </div>
    </div>
  );
}

export default SeatPage;
