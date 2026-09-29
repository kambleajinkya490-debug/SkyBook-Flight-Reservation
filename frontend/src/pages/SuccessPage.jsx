import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import api from "../api";

function SuccessPage() {
  const location = useLocation();
  const pnr = location.state?.pnr;

  const [ticket, setTicket] = useState(null);
  const [error, setError] = useState("");

  useEffect(() => {
    if (!pnr) {
      setError("PNR not available");
      return;
    }

    api
      .get(`/api/tickets/${pnr}`)
      .then((res) => {
        setTicket(res.data);
      })
      .catch((err) => {
        console.error(err);
        setError("Ticket not found");
      });
  }, [pnr]);

  if (error) {
    return (
      <div style={{ padding: 30 }}>
        <h2>❌ {error}</h2>
        <p>PNR: {pnr || "Not available"}</p>
      </div>
    );
  }

  if (!ticket) {
    return <h2 style={{ padding: 30 }}>Loading Ticket...</h2>;
  }

  return (
    <div style={{ padding: 30 }}>
      <h1>🎫 SkyBook E-Ticket</h1>

      <div
        style={{
          border: "2px solid #2563eb",
          padding: 20,
          borderRadius: 10,
          width: 400,
        }}
      >
        <h2>{ticket.passengerName}</h2>

        <p>
          <b>PNR:</b> {ticket.pnr}
        </p>

        <p>
          <b>Flight:</b> {ticket.flightNumber}
        </p>

        <p>
          <b>Route:</b> {ticket.route}
        </p>

        <p>
          <b>Seat:</b> {ticket.seatNumber}
        </p>

        <p>
          <b>Status:</b> {ticket.bookingStatus}
        </p>

        <button
          onClick={() =>
            window.open(
              `${import.meta.env.VITE_API_URL}/api/tickets/${ticket.pnr}/pdf`,
              "_blank"
            )
          }
          style={{
            padding: "10px 20px",
            marginTop: 15,
            cursor: "pointer",
          }}
        >
          Download Ticket PDF
        </button>
      </div>
    </div>
  );
}

export default SuccessPage;
