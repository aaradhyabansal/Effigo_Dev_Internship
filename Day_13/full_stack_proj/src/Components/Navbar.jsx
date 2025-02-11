import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import Menu from "@mui/material/Menu";
import MenuIcon from "@mui/icons-material/Menu";
import Container from "@mui/material/Container";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import Tooltip from "@mui/material/Tooltip";
import MenuItem from "@mui/material/MenuItem";
import { useNavigate } from "react-router-dom";
import logo from "../assets/zwigato.png"; // ✅ Corrected image import

const pages = [
  { name: "Dishes", path: "/dishes" },
  { name: "Add Dish", path: "/dishes/addnewdish/-1" },
];

const settings = ["Profile", "Account", "Dashboard", "Logout"];

function NavBar({ toggleTheme, isDarkTheme }) {
  const [anchorElNav, setAnchorElNav] = React.useState(null);
  const [anchorElUser, setAnchorElUser] = React.useState(null);
  const navigate = useNavigate();

  // ✅ Soft pastel button style
  const buttonStyle = React.useMemo(
    () => ({
      padding: "10px 18px",
      borderRadius: "8px",
      cursor: "pointer",
      transition: "all 0.3s ease",
      background: "linear-gradient(135deg, #FFD3B6, #FFAAA5, #D5AAFF, #85E3FF)",
      color: "#333",
      fontWeight: "bold",
      border: "none",
      boxShadow: "0px 4px 6px rgba(0, 0, 0, 0.1)",
      "&:hover": {
        transform: "scale(1.05)",
        boxShadow: "0px 6px 12px rgba(0, 0, 0, 0.2)",
      },
    }),
    []
  );

  const handleOpenNavMenu = (event) => setAnchorElNav(event.currentTarget);
  const handleOpenUserMenu = (event) => setAnchorElUser(event.currentTarget);
  const handleCloseNavMenu = () => setAnchorElNav(null);
  const handleCloseUserMenu = () => setAnchorElUser(null);

  const handleNavigate = (path) => {
    navigate(path);
    handleCloseNavMenu();
  };

  return (
    <AppBar
      position="static"
      sx={{
        background:
          "linear-gradient(135deg, #FFD3B6, #FFAAA5, #D5AAFF, #85E3FF)",
      }}
    >
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          {/* ✅ Logo with proper sizing */}
          <IconButton
            edge="start"
            color="inherit"
            aria-label="logo"
            sx={{ mr: 2 }}
          >
            <img
              src={logo}
              alt="Logo"
              style={{ width: "70px", height: "70px" }}
            />
          </IconButton>

          {/* ✅ Title Clickable - Navigates to Home */}
          <Typography
            variant="h6"
            component="div"
            onClick={() => navigate("/restaurants")}
            sx={{
              mr: 2,
              display: { xs: "none", md: "flex" },
              fontFamily: "monospace",
              fontWeight: 700,
              letterSpacing: ".2rem",
              color: "#333",
              textDecoration: "none",
              cursor: "pointer",
            }}
          >
            Zwigato
          </Typography>

          {/* ✅ Mobile Navigation Menu */}
          <Box sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}>
            <IconButton
              size="large"
              onClick={handleOpenNavMenu}
              color="inherit"
            >
              <MenuIcon />
            </IconButton>
            <Menu
              anchorEl={anchorElNav}
              anchorOrigin={{ vertical: "bottom", horizontal: "left" }}
              transformOrigin={{ vertical: "top", horizontal: "left" }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
            >
              {pages.map((page) => (
                <MenuItem
                  key={page.name}
                  onClick={() => handleNavigate(page.path)}
                >
                  <Typography sx={{ textAlign: "center", color: "#333" }}>
                    {page.name}
                  </Typography>
                </MenuItem>
              ))}
            </Menu>
          </Box>

          {/* ✅ Desktop Navigation */}
          <Box sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}>
            {pages.map((page) => (
              <Button
                key={page.name}
                onClick={() => handleNavigate(page.path)}
                sx={{
                  my: 2,
                  color: "#333",
                  fontWeight: "bold",
                  background: "linear-gradient(135deg, #FFD3B6, #FFAAA5)",
                  borderRadius: "8px",
                  marginRight: "10px",
                  boxShadow: "0px 4px 6px rgba(0, 0, 0, 0.1)",
                  "&:hover": {
                    background: "linear-gradient(135deg, #D5AAFF, #85E3FF)",
                    transform: "scale(1.05)",
                  },
                }}
              >
                {page.name}
              </Button>
            ))}
          </Box>

          {/* ✅ User Avatar & Menu */}
          <Box sx={{ flexGrow: 0 }}>
            <Tooltip title="Open settings">
              <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                <Avatar alt="User Avatar" src="/static/images/avatar/2.jpg" />
              </IconButton>
            </Tooltip>
            <Menu
              sx={{ mt: "45px" }}
              anchorEl={anchorElUser}
              anchorOrigin={{ vertical: "top", horizontal: "right" }}
              transformOrigin={{ vertical: "top", horizontal: "right" }}
              open={Boolean(anchorElUser)}
              onClose={handleCloseUserMenu}
            >
              {settings.map((setting) => (
                <MenuItem key={setting} onClick={handleCloseUserMenu}>
                  <Typography sx={{ textAlign: "center", color: "#333" }}>
                    {setting}
                  </Typography>
                </MenuItem>
              ))}
            </Menu>
          </Box>

          {/* ✅ Theme Toggle Button */}
          <Button sx={buttonStyle} onClick={toggleTheme}>
            {isDarkTheme ? "Light Theme" : "Dark Theme"}
          </Button>
        </Toolbar>
      </Container>
    </AppBar>
  );
}

export default NavBar;
