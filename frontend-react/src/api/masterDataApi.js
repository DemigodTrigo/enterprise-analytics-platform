import api from "./axios";

export const getCountries = () => {
    return api.get("http://localhost:8081/api/master/countries");
};

export const getCitiesByCountry = (countryCode) =>
  api.get(`http://localhost:8081/api/master/countries/${countryCode}/cities`);

export const refreshMasterData = () => {
  return api.post("http://localhost:8081/api/admin/master/refresh");
};

export const getStatuses = async () => {
  const response = await api.get("http://localhost:8081/api/master/statuses");
  return response.data.data;
};

export const getProducts = async () => {

  const response = await api.get("http://localhost:8081/api/master/products");
  return response.data.data
}
