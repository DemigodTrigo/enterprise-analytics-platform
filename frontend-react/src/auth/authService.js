import api from "../api/axios";

export const login = async (username, password) => {
  const response = await api.post("/auth/login", {
    username,
    password
  });

  localStorage.setItem("accessToken", response.data.accessToken);
  return response.data;
};

export const logout = () => {
  localStorage.removeItem("accessToken");
  window.location.href = "/login";
};
