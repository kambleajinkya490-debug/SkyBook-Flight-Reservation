import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api";

function RegisterPage() {
  const navigate = useNavigate();

  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);

  const handleRegister = async () => {
    if (!fullName || !email || !phone || !password) {
      alert("Please fill all fields");
      return;
    }

    try {
      setLoading(true);

      const response = await api.post("/api/auth/register", {
        fullName,
        email,
        phone,
        password,
      });

      console.log("Register response:", response.data);

      alert("Registration successful!");

      navigate("/login");
    } catch (error) {
      console.error("Registration error:", error);

      if (error.response) {
        alert(error.response.data || "Registration failed");
      } else {
        alert("Unable to connect to server");
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <div
      style={{
        padding: 30,
        maxWidth: 400,
        margin: "50px auto",
      }}
    >
      <h1>📝 SkyBook Register</h1>

      <input
        type="text"
        placeholder="Full Name"
        value={fullName}
        onChange={(e) => setFullName(e.target.value)}
        style={{
          padding: 10,
          width: "100%",
          marginBottom: 15,
          boxSizing: "border-box",
        }}
      />

      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        style={{
          padding: 10,
          width: "100%",
          marginBottom: 15,
          boxSizing: "border-box",
        }}
      />

      <input
        type="text"
        placeholder="Phone Number"
        value={phone}
        onChange={(e) => setPhone(e.target.value)}
        style={{
          padding: 10,
          width: "100%",
          marginBottom: 15,
          boxSizing: "border-box",
        }}
      />

      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        style={{
          padding: 10,
          width: "100%",
          marginBottom: 15,
          boxSizing: "border-box",
        }}
      />

      <button
        onClick={handleRegister}
        disabled={loading}
        style={{
          padding: "10px 20px",
          cursor: "pointer",
          marginRight: 10,
        }}
      >
        {loading ? "Registering..." : "Register"}
      </button>

      <button
        onClick={() => navigate("/login")}
        style={{
          padding: "10px 20px",
          cursor: "pointer",
        }}
      >
        Login
      </button>
    </div>
  );
}

export default RegisterPage;
