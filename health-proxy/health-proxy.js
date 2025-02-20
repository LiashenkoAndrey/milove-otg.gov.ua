const express = require("express");
const fetch = require("node-fetch");

const app = express();
const PORT = 3001;
const SERVER_URL = "https://localhost:8443/actuator/health";

let isServerUp = true;

const checkServerHealth = async () => {
  try {
    const res = await fetch(SERVER_URL, {timeout: 5000});

    if (res.ok) {
      const data = await res.json();
      isServerUp = data.status === "UP";
      console.log(`Server status: ${data.status}`);
    } else {
      isServerUp = false;
      console.error('Server health check failed with status:', res.status);
    }
  } catch (error) {
    isServerUp = false;
    console.error('Error occurred while checking server health:', error);
  }
};

setInterval(checkServerHealth, 5000);

app.get("/health-proxy", (req, res) => {
  res.json({serverUp: isServerUp});
});

app.listen(PORT, () => {
  console.log(`Health Proxy running on port ${PORT}`);
});
