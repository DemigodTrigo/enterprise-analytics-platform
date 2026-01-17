import { useEffect, useState } from "react";
import { getCountries, getCitiesByCountry ,getStatuses , getProducts } from "../api/masterDataApi";

function MasterDataPage() {
    const [countries, setCountries] = useState([]);
    const [cities, setCities] = useState([]);
    const [statuses, setStatuses] = useState([]);
    const [products, setProducts] = useState([]);

    const [loading, setLoading] = useState(true);
    const [loadingCities, setLoadingCities] = useState(false);
    const [selectedCountry, setSelectedCountry] = useState("");
    

    useEffect(() => {
        getCountries().then(response => {
            setCountries(response.data);
            setLoading(false);
        });
    }, []);

    useEffect(() => {
    if (!selectedCountry) {
      setCities([]);
      return;
    }

    setLoadingCities(true);
    getCitiesByCountry(selectedCountry)
      .then((response) => {
        setCities(response.data);
        setLoadingCities(false);
      })
      .catch(() => {
        setCities([]);
        setLoadingCities(false);
      });
  }, [selectedCountry]);

   useEffect(() => {
    getStatuses().then(setStatuses).catch(() => setStatuses([]));
    getProducts().then(setProducts).catch(() => setProducts([]));
  }, []);

      return (
    <div>
      <h2>Master Data Explorer</h2>

      {loading && <p>Loading Countries...</p>}

      {!loading && (
        <div>
          <label>Country</label>
          <br />
          <select
            value={selectedCountry}
            onChange={(e) => setSelectedCountry(e.target.value)}
          >
            <option value="">Select country</option>
            {countries.map((country) => (
              <option key={country.code} value={country.code}>
                {country.name}
              </option>
            ))}
          </select>
        </div>
      )}

      
      {loadingCities && <p>Loading Cities...</p>}

      {!loadingCities && cities.length > 0 && (
        <div style={{ marginTop: "15px" }}>
          <label>City</label>
          <br />
          <select>
            <option value="">Select city</option>
            {cities.map((city) => (
              <option key={city.id} value={city.name}>
                {city.name}
              </option>
            ))}
          </select>
        </div>
      )}

      {statuses.length > 0 && (
              <div style={{ marginTop: "15px" }}>
                <label>Status</label>
                <br />
                <select>
                  <option value="">Select status</option>
                  {statuses.map((status) => (
                    <option key={status.code} value={status.code}>
                      {status.description}
                    </option>
                  ))}
                </select>
              </div>
            )}

        {products.length > 0 && (
         <div style={{ marginTop: "15px" }}>
            <label>Product</label>
            <br />
            <select>
              <option value="">Select product</option>
              {products.map((product) => (
                <option key={product.code} value={product.code}>
                  {product.name}
                </option>
              ))}
            </select>
          </div>
        )}        

    </div>
  );
}


export default MasterDataPage;