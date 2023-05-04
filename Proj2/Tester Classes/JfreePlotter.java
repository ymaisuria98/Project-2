package proj2;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class JfreePlotter {
    public static void main(String[] args) throws IOException {
        // Read data from a CSV file
        List<Double> data = readData("C:/Users/yashm/OneDrive/Documents/SU - CSCI-3327-001 - PROBABILITY AND APPLIED STATS/PROGRAMMING/data.csv");
        
        // Plot the original data
        plotData(data, "Jplot.png");
        
        // Salt the data (add random variation)
        saltData(data, "Jsalted.png");
        
        // Smooth the data
        smoothData(data, "Jsmooth.png");
    }

    // Read data from a CSV file and return as a list of doubles
    public static List<Double> readData(String filename) throws IOException {
        List<Double> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] tokens = line.split(",");
            for (String token : tokens) {
                data.add(Double.parseDouble(token));
            }
        }
        br.close();
        return data;
    }

    // Plot the data using JFreeChart library
    public static void plotData(List<Double> data, String filename) throws IOException {
        XYSeries series = new XYSeries("Data");
        
        // Add data points to the series
        for (int i = 0; i < data.size(); i++) {
            series.add(i, data.get(i));
        }
        
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        
        // Create an XY line chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Data Plot",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );
        
        // Set the background color of the chart to white
        chart.getPlot().setBackgroundPaint(Color.WHITE);
        
        // Set the line color for the series to black
        chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.BLACK);
        
        // Save the chart as a PNG image file
        ChartUtils.saveChartAsPNG(new File(filename), chart, 800, 400);
    }

    // Add random salt (variation) to the data
    public static void saltData(List<Double> data, String filename) throws IOException {
        List<Double> saltedData = new ArrayList<>();
        
        // Add random variation to each data point
        for (Double value : data) {
            saltedData.add(value * (1 + Math.random() * 0.1 - 0.05));
        }
        
        // Plot the salted data
        plotData(saltedData, filename);
    }

    // Smooth the data using a moving median filter
    public static void smoothData(List<Double> data, String filename) throws IOException {
        List<Double> smoothedData = new ArrayList<>();
        
        for (int i = 0; i < data.size(); i++) {
            double[] values = new double[5];
            
            // Collect values within a  window of size 5 for smoothing
            for (int j = i - 2; j <= i + 2; j++) {
            	if (j >= 0 && j < data.size()) {
            	values[j - i + 2] = data.get(j);
            	}
            	}
            // Compute the median of the values
            Percentile percentile = new Percentile();
            smoothedData.add(percentile.evaluate(values, 50));
        }
        
        // Plot the smoothed data
        plotData(smoothedData, filename);
    }
}
