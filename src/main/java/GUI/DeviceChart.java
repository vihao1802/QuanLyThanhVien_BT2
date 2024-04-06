/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DAL.ThanhVienDAL;
import DAL.ThietBiDAL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;

public class DeviceChart extends JPanel {
    
    public DeviceChart(String key, String tenTB, String time1, String time2) {
        // Tạo dataset với dữ liệu số lượng thành viên mỗi khoa
        JFreeChart chart = null;
        if(key.equals("Tên")) {
            DefaultCategoryDataset dataset = createDatasetTenTB(tenTB);
            // Tạo biểu đồ cột từ dataset
            chart = ChartFactory.createBarChart(
                    "Thiết bị được mượn theo tên",  // Tiêu đề biểu đồ
                    "Thiết bị",                           // Nhãn trục x
                    "Số lần",            // Nhãn trục y
                    dataset                           // Dataset
            );
        } else if(key.equals("Time")) {
            DefaultCategoryDataset dataset = createDatasetTime(time1, time2);
            chart = ChartFactory.createBarChart(
                    "Thiết bị được mượn theo thời gian",  // Tiêu đề biểu đồ
                    "Thiết bị",                           // Nhãn trục x
                    "Số lần",            // Nhãn trục y
                    dataset                           // Dataset
            );
        }
        
        
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        renderer.setMaximumBarWidth(0.1);
        
        // Thêm biểu đồ vào panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(360, 350));
        add(chartPanel); // Thêm ChartPanel vào MemberChart
    }

    // Tạo dataset với dữ liệu mẫu
    private DefaultCategoryDataset createDatasetTenTB(String tenTB) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(new ThietBiDAL().getSLThietBiTheoTen(tenTB), "Số lượng", tenTB);
        return dataset;
    }
    
    // Tạo dataset với dữ liệu mẫu
    private DefaultCategoryDataset createDatasetTime(String time1, String time2) {
        ArrayList<String> listNganh = new ArrayList<>();
        ArrayList<Integer> SL = new ThietBiDAL().getTBTheoThoiGian(listNganh,time1,time2);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0;i<listNganh.size();i++) {
            dataset.addValue(SL.get(i), "Số lượng", listNganh.get(i));
        }
        
        return dataset;
    }


//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            ColumnChartExample example = new ColumnChartExample("Column Chart Example");
//            example.setSize(800, 600);
//            example.setLocationRelativeTo(null);
//            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//            example.setVisible(true);
//        });
//    }
}
