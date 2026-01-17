import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  PieChart,
  Pie,
  Cell,
  LineChart,
  Line
} from "recharts";


function ChartRenderer({ chart }) {
  if (!chart || !chart.series || chart.series.length === 0) {
    return <p>No chart data</p>;
  }

  const series = chart.series[0];

  // Convert backend map → recharts array
  const chartData = Object.entries(series.data).map(([key, value]) => ({
    name: key,
    value
  }));

  // -------------------------
  // BAR CHART
  // -------------------------
  if (chart.chartType === "BAR") {
    return (
      <div>
        <h3>{chart.title}</h3>
        <BarChart width={500} height={300} data={chartData}>
          <XAxis dataKey="name" label={{ value: chart.xAxisLabel, position: "insideBottom" }} />
          <YAxis label={{ value: chart.yAxisLabel, angle: -90, position: "insideLeft" }} />
          <Tooltip />
          <Bar dataKey="value" fill={series.color} />
        </BarChart>
      </div>
    );
  }

  // -------------------------
  // PIE CHART
  // -------------------------
  if (chart.chartType === "PIE") {
    return (
      <div>
        <h3>{chart.title}</h3>
        <PieChart width={400} height={300}>
          <Pie
            data={chartData}
            dataKey="value"
            nameKey="name"
            outerRadius={110}
            label
          >
            {chartData.map((_, index) => (
              <Cell key={index} fill={series.color} />
            ))}
          </Pie>
          <Tooltip />
        </PieChart>
      </div>
    );
  }

  // -------------------------
  // LINE CHART (FUTURE)
  // -------------------------
  if (chart.chartType === "LINE") {
    return (
      <div>
        <h3>{chart.title}</h3>
        <LineChart width={500} height={300} data={chartData}>
          <XAxis dataKey="name" />
          <YAxis />
          <Tooltip />
          <Line dataKey="value" stroke={series.color} />
        </LineChart>
      </div>
    );
  }

  return <p>Unsupported chart type</p>;
}

export default ChartRenderer;

