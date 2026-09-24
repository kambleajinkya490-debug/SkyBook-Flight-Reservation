import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../api";

function SeatPage() {
  const { id } = useParams();

  const [seats, setSeats] = useState([]);
  const [selectedSeat, setSelectedSeat] = useState(null);
  const navigate = useNavigate();
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [age, setAge] = useState("");

  useEffect(() => {
    loadSeats();
  }, [id]);

  const loadSeats = () => {
    api
      .get(`/flights/${id}/seats`)
      .then((res) => setSeats(res.data))
      .catch((err) => console.log(err));
  };

const bookSeat = () => {
  if (!selectedSeat) {
    alert("Please select a seat");
    return;
  }

  if (!firstName || !lastName || !age) {
    alert("Please fill passenger details");
    return;
  }

  api
    .post("/flights/book-seat", {
      flightId: Number(id),
      seatNumber: selectedSeat,
      firstName: firstName,
      lastName: lastName,
      age: Number(age),
    })
.then((res) => {
  navigate("/success", {
    state: {
      pnr: res.data,
      passengerName: `${firstName} ${lastName}`,
      flightNumber: `SK10${id}`,
      route: "Pune → Mumbai",
      seat: selectedSeat,
      bookingStatus: "CONFIRMED",
    },
  });
})
    .catch(() => {
      alert("Booking Failed");
    });
};
  return (
    <div
      style={{
        padding: 30,
        background: "#eef6ff",
        minHeight: "100vh",
        textAlign: "center",
      }}
    >
      <h1>🪑 Seat Selection</h1>
      <h3>Flight ID : {id}</h3>
      <p>Total Seats: {seats.length}</p>

      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(6, 60px)",
          gap: "10px",
          justifyContent: "center",
          marginTop: "30px",
        }}
      >
        {seats.map((seat) => (
          <button
            key={seat.id}
            disabled={seat.booked}
            onClick={() => setSelectedSeat(seat.seatNumber)}
            style={{
              width: "60px",
              height: "50px",
              border: "none",
              borderRadius: "8px",
              fontWeight: "bold",
              background: seat.booked
                ? "#ef4444"
                : selectedSeat === seat.seatNumber
                ? "#2563eb"
                : "#22c55e",
              color: "white",
              cursor: seat.booked ? "not-allowed" : "pointer",
            }}
          >
            {seat.seatNumber}
          </button>
        ))}
      </div>

      <div
        style={{
          maxWidth: "420px",
          margin: "30px auto",
          display: "flex",
          flexDirection: "column",
          gap: "12px",
        }}
      >
        <input
          placeholder="First Name"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
          style={{ padding: "12px", borderRadius: "8px" }}
        />

        <input
          placeholder="Last Name"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
          style={{ padding: "12px", borderRadius: "8px" }}
        />

        <input
          type="number"
          placeholder="Age"
          value={age}
          onChange={(e) => setAge(e.target.value)}
          style={{ padding: "12px", borderRadius: "8px" }}
        />
      </div>

      <button
        disabled={!selectedSeat}
        onClick={bookSeat}
        style={{
          background: selectedSeat ? "#2563eb" : "#94a3b8",
          color: "white",
          border: "none",
          padding: "14px 28px",
          borderRadius: "10px",
          fontSize: "16px",
          cursor: selectedSeat ? "pointer" : "not-allowed",
        }}
      >
        {selectedSeat
          ? `Confirm Seat ${selectedSeat}`
          : "Select a Seat"}
      </button>
    </div>
  );
}

export default SeatPage;
