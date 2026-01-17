import { useState } from "react";
import { refreshMasterData } from "../api/masterDataApi";

function AdminPage() {
    const [message, setMessage] = useState(null);
    const [loading, setLoading] = useState(false);

 const handleRefresh = () => {
    setLoading(true);
    setMessage(null);

    refreshMasterData()
      .then((response) => {
        setMessage(response.data);
        setLoading(false);
      })
      .catch(() => {
        setMessage("You are not authorized or refresh failed");
        setLoading(false);
      });
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Admin Operations</h2>

      <button onClick={handleRefresh} disabled={loading}>
        {loading ? "Refreshing..." : "Refresh Master Data Cache"}
      </button>

      {message && (
        <p style={{ marginTop: "15px", color: "green" }}>{message}</p>
      )}
    </div>
  );
}

export default AdminPage;