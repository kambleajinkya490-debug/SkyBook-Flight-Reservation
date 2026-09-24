import { useLocation, Link } from "react-router-dom";

function SuccessPage() {
  const { state } = useLocation();

  if (!state) {
    return (
      <div style={{ padding: 40, textAlign: "center" }}>
        <h2>No Booking Found</h2>
        <Link to="/">Go Home</Link>
      </div>
    );
  }

  return (
    <div
      style={{
        minHeight: "100vh",
        background: "#eef6ff",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <div
        style={{
          background: "white",
          padding: 40,
          borderRadius: 15,
          width: 420,
          textAlign: "center",
          boxShadow: "0 10px 30px rgba(0,0,0,.1)",
        }}
      >
        <h1>✅ Booking Confirmed</h1>

        <h2>{state.passengerName}</h2>

        <hr />

        <p><b>PNR:</b> {state.pnr}</p>
        <p><b>Flight:</b> {state.flightNumber}</p>
        <p><b>Route:</b> {state.route}</p>
        <p><b>Seat:</b> {state.seat}</p>

        <div
          style={{
            marginTop: 20,
            padding: 15,
            borderRadius: 10,
            background: "#dcfce7",
          }}
        >
          <b>{state.bookingStatus}</b>
        </div>

        <Link to="/">
          <button
            style={{
              marginTop: 25,
              width: "100%",
              padding: 12,
              border: "none",
              borderRadius: 8,
              background: "#2563eb",
              color: "white",
              fontSize: 16,
              cursor: "pointer",
            }}
          >
            Back to Home
          </button>
        </Link>
      </div>
    </div>
  );
}

export default SuccessPage;
