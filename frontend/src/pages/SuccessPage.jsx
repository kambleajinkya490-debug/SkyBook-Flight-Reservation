import { useEffect, useState } from "react";
import api from "../api";

function SuccessPage() {
  const [ticket, setTicket] = useState(null);

  useEffect(() => {
    api.get("/api/tickets/BF5FBA")
      .then((res) => setTicket(res.data))
      .catch(() => alert("Ticket not found"));
  }, []);

  if (!ticket) return <h2>Loading Ticket...</h2>;

  return (
    <div style={{ padding: 30 }}>
      <h1>🎫 SkyBook E-Ticket</h1>

      <div style={{
        border: "2px solid #2563eb",
        padding: 20,
        borderRadius: 10,
        width: 400
      }}>
        <h2>{ticket.passengerName}</h2>

        <p><b>PNR:</b> {ticket.pnr}</p>

        <p><b>Flight:</b> {ticket.flightNumber}</p>

        <p><b>Route:</b> {ticket.route}</p>

        <p><b>Seat:</b> {ticket.seatNumber}</p>

        <p><b>Status:</b> {ticket.bookingStatus}</p>
      </div>
    </div>
  );
}

export default SuccessPage;
