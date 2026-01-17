import api from "./api"

export const getRevenueByProduct = (filters) =>
  api.post("/api/analytics/revenue-by-product", filters);

export const getCountByStatus = () => {
  return api.get("/api/analytics/count-by-status");
};