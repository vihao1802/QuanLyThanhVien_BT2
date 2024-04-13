/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DAL.ThanhVienDAL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;

public class MemberChart extends JPanel {
    
    public MemberChart(String key, String time1, String time2) {
        // Tạo dataset với dữ liệu số lượng thành viên mỗi khoa
        JFreeChart chart = null;
        if(key.equals("Khoa")) {
            DefaultCategoryDataset dataset = createDatasetKhoa();
            // Tạo biểu đồ cột từ dataset
            chart = ChartFactory.createBarChart(
                    "Số lượng thành viên mỗi khoa",  // Tiêu đề biểu đồ
                    "Khoa",                           // Nhãn trục x
                    "Số lượng thành viên vào",            // Nhãn trục y
                    dataset                           // Dataset
            );
        } else if(key.equals("Ngành")) {
            DefaultCategoryDataset dataset = createDatasetNganh();
            chart = ChartFactory.createBarChart(
                    "Số lượng thành viên mỗi ngành",  // Tiêu đề biểu đồ
                    "Ngành",                           // Nhãn trục x
                    "Số lượng thành viên vào",            // Nhãn trục y
                    dataset                           // Dataset
            );
        } else if(key.equals("Ngày")) {
            DefaultCategoryDataset dataset = createDatasetTheoNgay(time1);
            chart = ChartFactory.createBarChart(
                    "Số lượng thành viên theo ngày",  // Tiêu đề biểu đồ
                    "Khung giờ",                           // Nhãn trục x
                    "Số lượng thành viên vào",            // Nhãn trục y
                    dataset                           // Dataset
            );
        } else if(key.equals("Tháng")) {
            DefaultCategoryDataset dataset = createDatasetTheoThang(time1,time2);
            chart = ChartFactory.createBarChart(
                    "Số lượng thành viên theo tháng",  // Tiêu đề biểu đồ
                    "Ngày",                           // Nhãn trục x
                    "Số lượng thành viên vào",            // Nhãn trục y
                    dataset                           // Dataset
            );
        }else if(key.equals("Năm")) {
            DefaultCategoryDataset dataset = createDatasetTheoNam(time1);
            chart = ChartFactory.createBarChart(
                    "Số lượng thành viên theo năm",  // Tiêu đề biểu đồ
                    "Tháng",                           // Nhãn trục x
                    "Số lượng thành viên vào",            // Nhãn trục y
                    dataset                           // Dataset
            );
        } else if(key.equals("Khoảng")) {
            DefaultCategoryDataset dataset = createDatasetTheoKhoang(time1, time2);
            chart = ChartFactory.createBarChart(
                    "Số lượng thành viên theo khoảng ngày",  // Tiêu đề biểu đồ
                    "Giờ",                           // Nhãn trục x
                    "Số lượng thành viên vào",            // Nhãn trục y
                    dataset                           // Dataset
            );
        }
        
        
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // Thêm biểu đồ vào panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 500));
        add(chartPanel); // Thêm ChartPanel vào MemberChart
    }

    // Tạo dataset với dữ liệu mẫu
    private DefaultCategoryDataset createDatasetKhoa() {
        ArrayList<String> listKhoa = new ArrayList<>();
        ArrayList<Integer> SL = new ThanhVienDAL().getSLThanhVienTheoKhoa(listKhoa);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0;i<listKhoa.size();i++) {
            dataset.addValue(SL.get(i), "Số lượng", listKhoa.get(i));
            System.out.println("khoa");
        }
        
        return dataset;
    }
    
    // Tạo dataset với dữ liệu mẫu
    private DefaultCategoryDataset createDatasetNganh() {
        ArrayList<String> listNganh = new ArrayList<>();
        ArrayList<Integer> SL = new ThanhVienDAL().getSLThanhVienTheoNganh(listNganh);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0;i<listNganh.size();i++) {
            dataset.addValue(SL.get(i), "Số lượng", listNganh.get(i));
            System.out.println("ngành");
        }
        
        return dataset;
    }
    
    // Tạo dataset với dữ liệu mẫu
    private DefaultCategoryDataset createDatasetTheoNgay(String date) {
        ArrayList<String> listNganh = new ArrayList<>();
        ArrayList<Integer> SL = new ThanhVienDAL().getSLTongVaoTheoNgay(listNganh, date);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0;i<listNganh.size();i++) {
            dataset.addValue(SL.get(i), "Số lượng", listNganh.get(i));
        }
        
        return dataset;
    }
    
    // Tạo dataset với dữ liệu mẫu
    private DefaultCategoryDataset createDatasetTheoKhoang(String date1, String date2) {
        ArrayList<String> listNganh = new ArrayList<>();
        ArrayList<Integer> SL = new ThanhVienDAL().getSLTongVaoTheoKhoang(listNganh, date1, date2);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0;i<listNganh.size();i++) {
            dataset.addValue(SL.get(i), "Số lượng", listNganh.get(i));
        }
        
        return dataset;
    }
    
    // Tạo dataset với dữ liệu mẫu
    private DefaultCategoryDataset createDatasetTheoThang(String date, String year) {
        ArrayList<String> listNganh = new ArrayList<>();
        ArrayList<Integer> SL = new ThanhVienDAL().getSLTongVaoTheoThang(listNganh, date, year);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0;i<listNganh.size();i++) {
            dataset.addValue(SL.get(i), "Số lượng", listNganh.get(i));
        }
        
        return dataset;
    }
    
    // Tạo dataset với dữ liệu mẫu
    private DefaultCategoryDataset createDatasetTheoNam(String date) {
        ArrayList<String> listNganh = new ArrayList<>();
        ArrayList<Integer> SL = new ThanhVienDAL().getSLTongVaoTheoNam(listNganh, date);
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
