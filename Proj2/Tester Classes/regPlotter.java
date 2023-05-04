package proj2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class regPlotter {

    public static void main(String[] args) throws IOException {
        // Read data from a CSV file
        List<Double> data = readData("C:/Users/yashm/OneDrive/Documents/SU - CSCI-3327-001 - PROBABILITY AND APPLIED STATS/PROGRAMMING/data.csv");
        
        // Plot the original data
        plotData(data, "RegPlot.png");
        
        // Salt the data (add random variation)
        saltData(data, "RegSalted.png");
        
        // Smooth the data
        smoothData(data, "RegSmooth.png");
    }
    
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
    
    public static void plotData(List<Double> data, String filename) throws IOException {
        int width = 800;
        int height = 400;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        
        // Set background color to white
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        
        // Set data line color to black
        g2d.setColor(Color.BLACK);
        
        // Calculate scaling factors
        double xScale = (double) width / (data.size() - 1);
        double yScale = height / (max(data) - min(data));
        
        // Draw data line
        for (int i = 0; i < data.size() - 1; i++) {
            double x1 = i * xScale;
            double y1 = (data.get(i) - min(data)) * yScale;
            double x2 = (i + 1) * xScale;
            double y2 = (data.get(i + 1) - min(data)) * yScale;
            g2d.drawLine((int)x1, height - (int)y1, (int)x2, height - (int)y2);
        }
        
        g2d.dispose();
        
        // Save the image to a file
        ImageIO.write(image, "png", new File(filename));
    }
    
    public static void saltData(List<Double> data, String filename) throws IOException {
        List<Double> saltedData = new ArrayList<>();
        
        // Add random variation to each data point
        for (Double value : data) {
            saltedData.add(value * (1 + Math.random() * 0.1 - 0.05));
        }
        
        // Plot the salted data
        plotData(saltedData, filename);
    }
    
    public static void smoothData(List<Double> data, String filename) throws IOException {
        List<Double> smoothedData = new ArrayList<>();
        
        // Smooth the data using a moving average
        for (int i = 0; i < data.size(); i++) {
            double sum = 0;
            int count = 0;
         // Calculate the sum of values within a window of size 5 (2 on each side)
            for (int j = i - 2; j <= i + 2; j++) {
                if (j >= 0 && j < data.size()) {
                    sum += data.get(j);
                    count++;
                }
            }
            
            // Compute the average and add it to the smoothed data
            smoothedData.add(sum / count);
        }
        
        // Plot the smoothed data
        plotData(smoothedData, filename);
    }
    
    public static double min(List<Double> data) {
        double min = Double.MAX_VALUE;
        
        // Find the minimum value in the data
        for (Double value : data) {
            if (value < min) {
                min = value;
            }
        }
        
        return min;
    }
    
    public static double max(List<Double> data) {
        double max = Double.MIN_VALUE;
        
        // Find the maximum value in the data
        for (Double value : data) {
            if (value > max) {
                max = value;
            }
        }
        
        return max;
    }
}
