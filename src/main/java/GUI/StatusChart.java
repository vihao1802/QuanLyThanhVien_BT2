/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import DAL.XuLy;
import BLL.XuLyBLL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 *
 * @author duyli
 */
public class StatusChart extends JPanel {

    public StatusChart() {
        // Create a dataset
        DefaultPieDataset dataset = createDataset();

        // Create the chart
        JFreeChart chart = ChartFactory.createPieChart(
                "THỐNG KÊ TRẠNG THÁI XỬ LÝ",  // chart title
                dataset,         // dataset
                true,            // include legend
                true,
                false);

        // Create and set up the panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        add(chartPanel);
        JLabel label = new JLabel("Tổng số tiền: "+ new XuLyBLL().getTongSoTien()+ "đ");
        label.setFont(new Font("Arial",Font.BOLD,18));
        add(label);
    }

    private DefaultPieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Fetch data from database
        XuLyBLL xuLyBLL = new XuLyBLL();
        List<XuLy> xuLyList = xuLyBLL.getAllXuly();

        int processedCount = 0;
        int notProcessedCount = 0;

        // Calculate counts for each status
        for (XuLy xuLy : xuLyList) {
            if (xuLy.getTrangThaiXL() == 0) {
                processedCount++;
            } else {
                notProcessedCount++;
            }
        }

        // Add counts to the dataset
        dataset.setValue("Đã xử lý", processedCount);
        dataset.setValue("Chưa xử lý", notProcessedCount);
        System.out.println(processedCount +"  "+ notProcessedCount);
        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Status Chart Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new StatusChart());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}