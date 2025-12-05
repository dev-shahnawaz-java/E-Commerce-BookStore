import React, { useState } from "react";
import {
  Box,
  Button,
  TextField,
  Typography,
  Paper,
  Alert,
} from "@mui/material";

export default function Register() {
  const [form, setForm] = useState({ name: "", email: "", password: "" });
  const [msg, setMsg] = useState("");
  const [type, setType] = useState("success");

  const changeHandler = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const submitForm = async (e) => {
    e.preventDefault();

    const resp = await fetch("http://localhost:8085/userregisters", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    });

    const data = await resp.json();

    setMsg(data.message);
    setType(resp.status === 201 ? "success" : "error");
  };

  return (
    <Box display="flex" justifyContent="center" mt={4}>
      <Paper elevation={4} sx={{ padding: 4, width: 400 }}>
        <Typography variant="h5" mb={2} textAlign="center">
          Create Account
        </Typography>

        {msg && (
          <Alert severity={type} sx={{ mb: 2 }}>
            {msg}
          </Alert>
        )}

        <form onSubmit={submitForm}>
          <TextField
            label="Name"
            name="name"
            fullWidth
            onChange={changeHandler}
            sx={{ mb: 2 }}
          />

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
            Register
          </Button>
        </form>
      </Paper>
    </Box>
  );
}
