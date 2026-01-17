import { useEffect, useState } from "react";
import ChartRenderer from "../components/ChartRenderer";
import {
  getRevenueByProduct,
  getCountByStatus
} from "../api/analyticsApi";

function DashboardPage() {
  const [revenueChart, setRevenueChart] = useState(null);
  const [statusChart, setStatusChart] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    loadRevenue();
    loadStatus();
  }, []);

  const loadRevenue = () => {
    setError(null);

    getRevenueByProduct({})
      .then((res) => {
        if (res.data.success) {
          setRevenueChart(res.data.data);
        } else {
          setError("Failed to load revenue analytics");
        }
      })
      .catch(() => {
        setError("Failed to load revenue analytics");
      });
  };

  const loadStatus = () => {
    getCountByStatus()
      .then((res) => {
        if (res.data.success) {
          setStatusChart(res.data.data);
        }
      })
      .catch(() => {});
  };

  return (
    <div>
      <h2>Analytics Dashboard</h2>

      {error && <p style={{ color: "red" }}>{error}</p>}

      {revenueChart && (
        <div style={{ marginBottom: "30px" }}>
          <ChartRenderer chart={revenueChart} />
        </div>
      )}

      {statusChart && (
        <div>
          <ChartRenderer chart={statusChart} />
        </div>
      )}
    </div>
  );
}

export default DashboardPage;
