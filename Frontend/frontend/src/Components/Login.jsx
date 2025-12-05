import React, { useState } from "react";
import {
  Box,
  Button,
  TextField,
  Typography,
  Paper,
  Alert,
} from "@mui/material";
import { useAuth } from "./AuthContext";

export default function Login() {
  const { login } = useAuth();

  const [form, setForm] = useState({ email: "", password: "" });
  const [msg, setMsg] = useState("");
  const [type, setType] = useState("error");

  const changeHandler = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const submitLogin = async (e) => {
    e.preventDefault();

    const resp = await fetch("http://localhost:8085/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    });

    const data = await resp.json();

    setMsg(data.message);
    setType(resp.status === 200 ? "success" : "error");

    if (resp.status === 200) {
      login(data.data);
      setTimeout(() => {
        window.location.href = "/";
      }, 1000);
    }
  };

  return (
    <Box display="flex" justifyContent="center" mt={4}>
      <Paper elevation={4} sx={{ padding: 4, width: 400 }}>
        <Typography variant="h5" mb={2} textAlign="center">
          Login
        </Typography>

        {msg && (
          <Alert severity={type} sx={{ mb: 2 }}>
            {msg}
          </Alert>
        )}

        <form onSubmit={submitLogin}>
          <TextField
            label="Email"
            name="email"
            fullWidth
            onChange={changeHandler}
            sx={{ mb: 2 }}
          />

          <TextField
            label="Password"
            name="password"
            type="password"
            fullWidth
            onChange={changeHandler}
            sx={{ mb: 2 }}
          />

          <Button type="submit" variant="contained" fullWidth>
            Login
          </Button>
        </form>
      </Paper>
    </Box>
  );
}

