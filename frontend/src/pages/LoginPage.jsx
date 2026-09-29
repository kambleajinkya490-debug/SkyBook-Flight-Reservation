import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api";

function LoginPage() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);

  const handleLogin = async () => {
    if (!email || !password) {
      alert("Please enter email and password");
      return;
    }

    try {
      setLoading(true);

      const response = await api.post("/api/auth/login", {
        email,
        password,
      });

      const token = response.data;

      localStorage.setItem("token", token);

      alert("Login successful!");

      navigate("/");
    } catch (error) {
      console.error("Login error:", error);

      if (error.response) {
        alert("Invalid email or password");
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
      <h1>🔐 SkyBook Login</h1>

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
        onClick={handleLogin}
        disabled={loading}
        style={{
          padding: "10px 20px",
          cursor: "pointer",
          marginRight: 10,
        }}
      >
        {loading ? "Logging in..." : "Login"}
      </button>

      <button
        onClick={() => navigate("/register")}
        style={{
          padding: "10px 20px",
          cursor: "pointer",
        }}
      >
        Register
      </button>

      <p style={{ marginTop: 20 }}>
        Don't have an account?
      </p>

      <button
        onClick={() => navigate("/register")}
        style={{
          padding: "8px 15px",
          cursor: "pointer",
        }}
      >
        Create New Account
      </button>
    </div>
  );
}

export default LoginPage;
