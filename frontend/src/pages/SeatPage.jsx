import { useState } from "react";

function SeatPage() {
  const [selected, setSelected] = useState("");

  const seats = Array.from({ length: 30 }, (_, i) => i + 1);

  return (
    <div style={{ padding: 30 }}>
      <h1>🪑 Select Your Seat</h1>

      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(6,60px)",
          gap: 10,
        }}
      >
        {seats.map((seat) => (
          <button
            key={seat}
            onClick={() => setSelected(seat)}
            style={{
              height: 50,
              background: selected === seat ? "#22c55e" : "#e5e7eb",
              border: "none",
              borderRadius: 8,
            }}
          >
            {seat}
          </button>
        ))}
      </div>

      <h2 style={{ marginTop: 20 }}>
        Selected Seat: {selected || "None"}
      </h2>
    </div>
  );
}

export default SeatPage;
