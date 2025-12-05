import React from "react";
import { AppBar, Toolbar, Typography, Button } from "@mui/material";
import { useAuth } from "./AuthContext";

export default function Navbar() {
  const { user, logout } = useAuth();

  return (
    <AppBar position="static" color="primary">
      <Toolbar>
        <Typography variant="h6" sx={{ flexGrow: 1 }}>
          Online BookStore
        </Typography>

        {!user ? (
          <>
            <Button color="inherit" href="/register">Register</Button>
            <Button color="inherit" href="/login">Login</Button>
          </>
        ) : (
          <>
            <Typography variant="body1" sx={{ marginRight: 2 }}>
              Hello, {user.name}
            </Typography>
            <Button variant="outlined" color="inherit" onClick={logout}>
              Logout
            </Button>
          </>
        )}
      </Toolbar>
    </AppBar>
  );
}


