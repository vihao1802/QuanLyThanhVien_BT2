/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.util.List;
import BLL.ThanhVienBLL;
import BLL.ThietBiBLL;
import BLL.ThongTinSDBLL;
import BLL.XuLyBLL;
import DAL.ThanhVien;
import DAL.ThanhVienDAL;
import DAL.ThietBi;
import DAL.ThongTinSD;
import DAL.ThongTinSDDAL;
import DAL.XuLy;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.xa.OracleXAResource;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
/**
 *
 * @author ADMIN
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    ThanhVienBLL thanhvienBLL;
    ThietBiBLL thietbiBLL;
    XuLyBLL xlBLL;
    JPanel tmp = new JPanel(new FlowLayout());
    JDateChooser startDateChooser = new JDateChooser();
    JDateChooser endDateChooser = new JDateChooser();

    public MainForm() {
        initComponents();
        runMain();
        
        thanhvienBLL = new ThanhVienBLL();
        thietbiBLL = new ThietBiBLL();
        xlBLL = new XuLyBLL();
        loadTimeOption();
        loadDataInToTableThietBi();
    }
    
    public void loadTimeOption() {
        jRadioKhoaTV.setSelected(true);
        pnChartmember.removeAll();
        pnChartmember.revalidate();
        pnChartmember.repaint();
        pnChartmember.setLayout(new BorderLayout());
        pnChartmember.add(new MemberChart("Khoa","",""),BorderLayout.CENTER);
        startDateChooser.setDateFormatString("yyyy-MM-dd");
        startDateChooser.setPreferredSize(new Dimension(150, 30));

        
        endDateChooser.setDateFormatString("yyyy-MM-dd");
        endDateChooser.setPreferredSize(new Dimension(150, 30));
        
        tmp.add(new JLabel("Start Date:"));
        tmp.add(startDateChooser);
        tmp.add(new JLabel("End Date:"));
        tmp.add(endDateChooser);
        tmp.setVisible(false);
        pn2ndOptionTB.add(tmp);
        
        startDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("date")) {
                    if(endDateChooser.getDate()!=null) {
                        if(checkDateValidity(startDateChooser.getDate(), endDateChooser.getDate())) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            pnDeviceChart.removeAll();
                            pnDeviceChart.revalidate();
                            pnDeviceChart.repaint();
                            pnDeviceChart.setLayout(new BorderLayout());
                            pnDeviceChart.add(new DeviceChart("Time","",dateFormat.format(startDateChooser.getDate()),dateFormat.format(endDateChooser.getDate())),BorderLayout.CENTER);
                        }
                    }
                }
            }
        });
        
        endDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("date")) {
                    if(startDateChooser.getDate()!=null) {
                        if(checkDateValidity(startDateChooser.getDate(), endDateChooser.getDate())) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            pnDeviceChart.removeAll();
                            pnDeviceChart.revalidate();
                            pnDeviceChart.repaint();
                            pnDeviceChart.setLayout(new BorderLayout());
                            pnDeviceChart.add(new DeviceChart("Time","",dateFormat.format(startDateChooser.getDate()),dateFormat.format(endDateChooser.getDate())),BorderLayout.CENTER);
                        }
                    }
                }
            }
        });
    }
    
    public boolean checkDateValidity(Date date1, Date date2) {
        if (date1 != null && date2 != null && date2.before(date1)) {
            return false;
        }
        return true;
    } 
    
    public void loadDataThanhVienToTableThanhVienMuonTra() {
        String[] col = new String[] { "Mã thành viên", "Họ tên", "Khoa", "Ngành", "Số điện thoại" };
        DefaultTableModel model = new DefaultTableModel(col,0);
        List<ThanhVien> list = this.thanhvienBLL.loadThanhVien();
        Object[] row;
        for(ThanhVien tv : list) {
            System.out.println(tv.getMaTV());
             row = new Object[5];
             row[0] = tv.getMaTV();
             row[1] = tv.getHoTen();
             row[2] = tv.getKhoa();
             row[3] = tv.getNganh();
             row[4] = tv.getSDT();
             model.addRow(row);
        }
        this.jTbl_qlThanhVien.setModel(model);
    }
    
    public void loadDataThietBiToTableThietBiMuonTra() {
        String[] col = new String[] { "Mã thiết bị", "Tên thiết bị", "Mô tả"};
        DefaultTableModel model = new DefaultTableModel(col,0);
        List<ThanhVien> list = this.thanhvienBLL.loadThanhVien();
        Object[] row;
        for(ThanhVien tv : list) {
            System.out.println(tv.getMaTV());
             row = new Object[5];
             row[0] = tv.getMaTV();
             row[1] = tv.getHoTen();
             row[2] = tv.getKhoa();
             row[3] = tv.getNganh();
             row[4] = tv.getSDT();
             model.addRow(row);
        }
        this.tbThanhVienMuonTra.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupTV = new javax.swing.ButtonGroup();
        btnGroupTB = new javax.swing.ButtonGroup();
        jTabbedPane_QLTV = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel_formVaoKhuHocTap = new javax.swing.JPanel();
        jLabe_DienMaTV = new javax.swing.JLabel();
        jBtn_enterClassZone = new javax.swing.JButton();
        jtf_maThanhVien = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbThanhVienMuonTra = new javax.swing.JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        jPanel14 = new javax.swing.JPanel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jPanel19 = new javax.swing.JPanel();
        jDateNgayMT = new com.toedter.calendar.JDateChooser();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tbThietBiMuonTra = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbDangKyMuonTra = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jBtn_xoaThanhVien = new javax.swing.JButton();
        jtf_searchInQLTV = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTbl_qlThanhVien = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jBtn_xoaThanhVien1 = new javax.swing.JButton();
        jtf_searchInQLTB = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Thietbi = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        panelxuly = new javax.swing.JPanel();
        panel_topXuLy = new javax.swing.JPanel();
        jtf_xuly = new javax.swing.JTextField();
        btn_addxuly = new javax.swing.JButton();
        reload_xuly = new javax.swing.JButton();
        jsp_mainXuLy = new javax.swing.JScrollPane();
        tb_xuly = new javax.swing.JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jPanel5 = new javax.swing.JPanel();
        tabThongke = new javax.swing.JTabbedPane();
        pnThongkeTB = new javax.swing.JPanel();
        pnDeviceChart = new javax.swing.JPanel();
        pnOptionTB = new javax.swing.JPanel();
        pn2ndOptionTB = new javax.swing.JPanel();
        cbTenTB = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jRadioTenTB = new javax.swing.JRadioButton();
        jRadioTimeTB = new javax.swing.JRadioButton();
        pnThongkeTV = new javax.swing.JPanel();
        pnChartmember = new javax.swing.JPanel();
        pnoptionmember = new javax.swing.JPanel();
        pnTimeTV = new javax.swing.JPanel();
        pn_menuBorrowedDevice1 = new javax.swing.JPanel();
        btnTKTV = new javax.swing.JButton();
        cbb_kieuTKTV = new javax.swing.JComboBox<>();
        pnVaoTV = new javax.swing.JPanel();
        lbTongVaoTV = new javax.swing.JLabel();
        lbTongVaoNumTV = new javax.swing.JLabel();
        pnRaTV = new javax.swing.JPanel();
        pnFromDateTV = new javax.swing.JPanel();
        lbFromdateTV = new javax.swing.JLabel();
        SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, 31, 1);
        spnNgayTVFrom = new javax.swing.JSpinner(model1);
        lbFromMonthTV = new javax.swing.JLabel();
        SpinnerNumberModel model2 = new SpinnerNumberModel(1, 1, 12, 1);
        spnThangTVFrom = new javax.swing.JSpinner(model2);
        lbFromYearTV = new javax.swing.JLabel();
        jtfNamTVFrom = new javax.swing.JTextField(4);
        pnFromDateTV1 = new javax.swing.JPanel();
        lbFromdateTV1 = new javax.swing.JLabel();
        SpinnerNumberModel model11 = new SpinnerNumberModel(1, 1, 31, 1);
        spnNgayTVFrom1 = new javax.swing.JSpinner(model11);
        lbFromMonthTV1 = new javax.swing.JLabel();
        SpinnerNumberModel model22 = new SpinnerNumberModel(1, 1, 12, 1);
        spnThangTVFrom1 = new javax.swing.JSpinner(model22);
        lbFromYearTV1 = new javax.swing.JLabel();
        jtfNamTVFrom1 = new javax.swing.JTextField(4);
        pnKhoaNganh = new javax.swing.JPanel();
        jRadioKhoaTV = new javax.swing.JRadioButton();
        jRadioNganhTV = new javax.swing.JRadioButton();
        jRadioTimeTV = new javax.swing.JRadioButton();
        pnThongKeTBDangMuon = new javax.swing.JPanel();
        pn_BorrowedDevice = new javax.swing.JPanel();
        pn_menuBorrowedDevice = new javax.swing.JPanel();
        Calendar calendarday = Calendar.getInstance();
        int currentDay = calendarday.get(Calendar.DAY_OF_MONTH);
        SpinnerNumberModel dayModel = new SpinnerNumberModel(currentDay, 1, 31, 1); // Giới hạn từ 1 đến 31
        spn_day = new javax.swing.JSpinner();
        jtfYear = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbbMonth = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jbtBorrowedDevice = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        int currentDay1 = calendarday.get(Calendar.DAY_OF_MONTH);
        SpinnerNumberModel dayModel1 = new SpinnerNumberModel(currentDay1, 1, 31, 1); // Giới hạn từ 1 đến 31
        spn_day1 = new javax.swing.JSpinner();
        jtfYear1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbbMonth1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbb_kieuTK = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jsp_mainXuLy2 = new javax.swing.JScrollPane();
        tb_BorrowedDevice = new javax.swing.JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        pnChartstatus = new javax.swing.JPanel();

        btnGroupTV.add(jRadioKhoaTV);
        btnGroupTV.add(jRadioNganhTV);
        btnGroupTV.add(jRadioTimeTV);

        btnGroupTB.add(jRadioTenTB);
        btnGroupTB.add(jRadioTimeTB);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mềm quản lý thành viên");
        setLocation(new java.awt.Point(250, 50));

        jTabbedPane_QLTV.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane_QLTV.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane_QLTV.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTabbedPane_QLTV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane_QLTVMouseClicked(evt);
            }
        });

        jPanel_formVaoKhuHocTap.setBackground(new java.awt.Color(255, 255, 255));

        jLabe_DienMaTV.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabe_DienMaTV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabe_DienMaTV.setText("Điền mã thành viên để vào khu học tập");

        jBtn_enterClassZone.setBackground(new java.awt.Color(51, 51, 255));
        jBtn_enterClassZone.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtn_enterClassZone.setForeground(new java.awt.Color(255, 255, 255));
        jBtn_enterClassZone.setText("Enter");
        jBtn_enterClassZone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_enterClassZoneActionPerformed(evt);
            }
        });

        jtf_maThanhVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jtf_maThanhVien.setText("Vui lòng nhập mã thành viên");
        jtf_maThanhVien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtf_maThanhVienFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtf_maThanhVienFocusLost(evt);
            }
        });
        jtf_maThanhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_maThanhVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_formVaoKhuHocTapLayout = new javax.swing.GroupLayout(jPanel_formVaoKhuHocTap);
        jPanel_formVaoKhuHocTap.setLayout(jPanel_formVaoKhuHocTapLayout);
        jPanel_formVaoKhuHocTapLayout.setHorizontalGroup(
            jPanel_formVaoKhuHocTapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_formVaoKhuHocTapLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel_formVaoKhuHocTapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabe_DienMaTV, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addComponent(jBtn_enterClassZone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtf_maThanhVien))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel_formVaoKhuHocTapLayout.setVerticalGroup(
            jPanel_formVaoKhuHocTapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_formVaoKhuHocTapLayout.createSequentialGroup()
                .addContainerGap(146, Short.MAX_VALUE)
                .addComponent(jLabe_DienMaTV)
                .addGap(33, 33, 33)
                .addComponent(jtf_maThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtn_enterClassZone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(259, Short.MAX_VALUE)
                .addComponent(jPanel_formVaoKhuHocTap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(202, 202, 202))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addComponent(jPanel_formVaoKhuHocTap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );

        jTabbedPane_QLTV.addTab("Thông tin thành viên", new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-info-30.png")), jPanel1); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chọn thành viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 14))); // NOI18N
        jPanel12.setPreferredSize(new java.awt.Dimension(405, 300));

        tbThanhVienMuonTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbThanhVienMuonTra.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane10.setViewportView(tbThanhVienMuonTra);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chọn tác vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 14))); // NOI18N
        jPanel14.setPreferredSize(new java.awt.Dimension(10, 50));

        btnGroupTV.add(jRadioButton3);
        jRadioButton3.setText("Trả thiết bị");

        btnGroupTV.add(jRadioButton4);
        jRadioButton4.setText("Mượn thiết bị");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chọn ngày", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 14))); // NOI18N
        jPanel19.setPreferredSize(new java.awt.Dimension(10, 50));

        jDateNgayMT.setBackground(new java.awt.Color(255, 255, 255));
        jDateNgayMT.setDateFormatString("dd/MM/yyyy");
        jDateNgayMT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDateNgayMT, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jDateNgayMT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chọn thiết bị", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 14))); // NOI18N
        jPanel16.setPreferredSize(new java.awt.Dimension(394, 380));

        tbThietBiMuonTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbThietBiMuonTra.setPreferredSize(new java.awt.Dimension(330, 80));
        jScrollPane12.setViewportView(tbThietBiMuonTra);

        tbDangKyMuonTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane13.setViewportView(tbDangKyMuonTra);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-arrow-pointing-left-25.png"))); // NOI18N

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-arrow-25.png"))); // NOI18N

        jTextField2.setText("Tìm kiếm thiết bị");

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setText("Danh sách đăng ký mượn trả");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addComponent(jTextField2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jButton7.setBackground(new java.awt.Color(0, 255, 51));
        jButton7.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Duyệt");
        jButton7.setToolTipText("");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel14, jPanel19});

        jTabbedPane1.addTab("Mượn trả thiết bị", jPanel11);

        jTextField3.setText("Tìm kiếm mượn trả");

        jButton11.setBackground(new java.awt.Color(255, 51, 51));
        jButton11.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Xóa");
        jButton11.setToolTipText("");

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(jTable11);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                        .addGap(367, 367, 367)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jTextField3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Danh sách mượn trả", jPanel17);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane_QLTV.addTab("Quản lý mượn trả", new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-borrow-book-30.png")), jPanel6, "Quản lý mượn trả"); // NOI18N

        jButton4.setBackground(new java.awt.Color(0, 255, 51));
        jButton4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Thêm");
        jButton4.setToolTipText("");

        jButton5.setBackground(new java.awt.Color(0, 255, 204));
        jButton5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Sửa");
        jButton5.setToolTipText("");

        jBtn_xoaThanhVien.setBackground(new java.awt.Color(255, 51, 51));
        jBtn_xoaThanhVien.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jBtn_xoaThanhVien.setForeground(new java.awt.Color(255, 255, 255));
        jBtn_xoaThanhVien.setText("Xóa");
        jBtn_xoaThanhVien.setToolTipText("");
        jBtn_xoaThanhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_xoaThanhVienActionPerformed(evt);
            }
        });

        jtf_searchInQLTV.setText("Search by ID/name");
        jtf_searchInQLTV.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtf_searchInQLTVFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtf_searchInQLTVFocusLost(evt);
            }
        });
        jtf_searchInQLTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_searchInQLTVActionPerformed(evt);
            }
        });

        jTbl_qlThanhVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã thành viên", "Họ tên", "Khoa", "Ngành", "Số điện thoại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTbl_qlThanhVien);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jtf_searchInQLTV, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtn_xoaThanhVien)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtn_xoaThanhVien, jButton5});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtn_xoaThanhVien)
                    .addComponent(jtf_searchInQLTV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBtn_xoaThanhVien, jButton5});

        jTabbedPane_QLTV.addTab("Quản lý thành viên", new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-member-30.png")), jPanel2, "Quản lý thành viên"); // NOI18N

        jButton6.setBackground(new java.awt.Color(0, 255, 51));
        jButton6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Thêm");
        jButton6.setToolTipText("");

        jButton9.setBackground(new java.awt.Color(0, 255, 204));
        jButton9.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Sửa");
        jButton9.setToolTipText("");

        jBtn_xoaThanhVien1.setBackground(new java.awt.Color(255, 51, 51));
        jBtn_xoaThanhVien1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jBtn_xoaThanhVien1.setForeground(new java.awt.Color(255, 255, 255));
        jBtn_xoaThanhVien1.setText("Xóa");
        jBtn_xoaThanhVien1.setToolTipText("");
        jBtn_xoaThanhVien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_xoaThanhVien1ActionPerformed(evt);
            }
        });

        jtf_searchInQLTB.setText("Search by ID/name");
        jtf_searchInQLTB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtf_searchInQLTBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtf_searchInQLTBFocusLost(evt);
            }
        });
        jtf_searchInQLTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_searchInQLTBActionPerformed(evt);
            }
        });

        tb_Thietbi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã thiết bị", "Tên thiết bị", "Mô tả thiết bị"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tb_Thietbi);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jtf_searchInQLTB, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtn_xoaThanhVien1)
                        .addGap(61, 61, 61)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtn_xoaThanhVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jtf_searchInQLTB, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 861, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 737, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane_QLTV.addTab("Quản lý thiết bị", new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-device-manager-30.png")), jPanel3, "Quản lý thiết bị"); // NOI18N

        jPanel4.setLayout(new java.awt.BorderLayout());

        panelxuly.setBackground(new java.awt.Color(255, 255, 255));
        panelxuly.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        panelxuly.setLayout(new java.awt.BorderLayout());

        panel_topXuLy.setBackground(new java.awt.Color(255, 255, 255));
        panel_topXuLy.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        panel_topXuLy.setPreferredSize(new java.awt.Dimension(408, 80));

        jtf_xuly.setText("Search by ......");
        jtf_xuly.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtf_xulyFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtf_xulyFocusLost(evt);
            }
        });
        jtf_xuly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_xulyActionPerformed(evt);
            }
        });

        btn_addxuly.setBackground(new java.awt.Color(51, 204, 255));
        btn_addxuly.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_addxuly.setForeground(new java.awt.Color(255, 255, 255));
        btn_addxuly.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_addxuly.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_addxuly.setLabel("Add");
        btn_addxuly.setName(""); // NOI18N
        btn_addxuly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addxulyActionPerformed(evt);
            }
        });

        reload_xuly.setBackground(new java.awt.Color(153, 153, 153));
        reload_xuly.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-reload-30.png"))); // NOI18N
        reload_xuly.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reload_xuly.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        reload_xuly.setPreferredSize(new java.awt.Dimension(40, 40));
        reload_xuly.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reload_xulyMouseClicked(evt);
            }
        });
        reload_xuly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reload_xulyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_topXuLyLayout = new javax.swing.GroupLayout(panel_topXuLy);
        panel_topXuLy.setLayout(panel_topXuLyLayout);
        panel_topXuLyLayout.setHorizontalGroup(
            panel_topXuLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_topXuLyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtf_xuly, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 397, Short.MAX_VALUE)
                .addComponent(btn_addxuly, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(reload_xuly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_topXuLyLayout.setVerticalGroup(
            panel_topXuLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_topXuLyLayout.createSequentialGroup()
                .addGroup(panel_topXuLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_topXuLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtf_xuly, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_addxuly, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(reload_xuly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        panelxuly.add(panel_topXuLy, java.awt.BorderLayout.NORTH);

        tb_xuly.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã xử lý", "Mã thành viên", "Hình thức Xl", "Tiền phạt", "Ngày xử lý", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        loadDataTableXuLy();
        tb_xuly.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_xulyClicked(evt);
            }
        });
        jsp_mainXuLy.setViewportView(tb_xuly);
        if (tb_xuly.getColumnModel().getColumnCount() > 0) {
            tb_xuly.getColumnModel().getColumn(3).setHeaderValue("Tiền phạt");
            tb_xuly.getColumnModel().getColumn(4).setHeaderValue("Ngày xử lý");
            tb_xuly.getColumnModel().getColumn(5).setHeaderValue("Trạng thái");
        }

        panelxuly.add(jsp_mainXuLy, java.awt.BorderLayout.CENTER);

        jPanel4.add(panelxuly, java.awt.BorderLayout.CENTER);

        jTabbedPane_QLTV.addTab("Xử lý vi phạm", new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-warning-30.png")), jPanel4, "Xử lý vi phạm"); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.BorderLayout());

        tabThongke.setBackground(new java.awt.Color(255, 255, 255));
        tabThongke.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabThongkeStateChanged(evt);
            }
        });
        tabThongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabThongkeMouseClicked(evt);
            }
        });

        pnThongkeTB.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout pnDeviceChartLayout = new javax.swing.GroupLayout(pnDeviceChart);
        pnDeviceChart.setLayout(pnDeviceChartLayout);
        pnDeviceChartLayout.setHorizontalGroup(
            pnDeviceChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 861, Short.MAX_VALUE)
        );
        pnDeviceChartLayout.setVerticalGroup(
            pnDeviceChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 639, Short.MAX_VALUE)
        );

        pnThongkeTB.add(pnDeviceChart, java.awt.BorderLayout.CENTER);

        pnOptionTB.setLayout(new java.awt.BorderLayout());

        cbTenTB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTenTB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTenTBItemStateChanged(evt);
            }
        });
        cbTenTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTenTBActionPerformed(evt);
            }
        });
        pn2ndOptionTB.add(cbTenTB);
        cbTenTB.setVisible(false);

        pnOptionTB.add(pn2ndOptionTB, java.awt.BorderLayout.CENTER);

        jRadioTenTB.setText("Thời gian");
        jRadioTenTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioTenTBActionPerformed(evt);
            }
        });
        jPanel10.add(jRadioTenTB);

        jRadioTimeTB.setText("Tên thiết bị");
        jRadioTimeTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioTimeTBActionPerformed(evt);
            }
        });
        jPanel10.add(jRadioTimeTB);

        pnOptionTB.add(jPanel10, java.awt.BorderLayout.PAGE_START);

        pnThongkeTB.add(pnOptionTB, java.awt.BorderLayout.PAGE_START);

        tabThongke.addTab("Thiết bị", pnThongkeTB);

        pnThongkeTV.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout pnChartmemberLayout = new javax.swing.GroupLayout(pnChartmember);
        pnChartmember.setLayout(pnChartmemberLayout);
        pnChartmemberLayout.setHorizontalGroup(
            pnChartmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 861, Short.MAX_VALUE)
        );
        pnChartmemberLayout.setVerticalGroup(
            pnChartmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        pnThongkeTV.add(pnChartmember, java.awt.BorderLayout.CENTER);

        pnoptionmember.setLayout(new java.awt.BorderLayout());

        pn_menuBorrowedDevice1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống kê thành viên", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        pn_menuBorrowedDevice1.setPreferredSize(new java.awt.Dimension(810, 150));

        btnTKTV.setBackground(new java.awt.Color(0, 204, 51));
        btnTKTV.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTKTV.setForeground(new java.awt.Color(255, 255, 255));
        btnTKTV.setText("Thống Kê");
        btnTKTV.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnTKTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKTVActionPerformed(evt);
            }
        });

        cbb_kieuTKTV.setBackground(new java.awt.Color(242, 242, 242));
        cbb_kieuTKTV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Thống kê trong 1 ngày", "Thống kê trong 1 tháng", "Thống kê trong 1 năm", "Thống kê trong khoảng" }));
        cbb_kieuTKTV.setBorder(javax.swing.BorderFactory.createTitledBorder("Kiểu thống kê"));
        cbb_kieuTKTV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_kieuTKTVItemStateChanged(evt);
            }
        });
        cbb_kieuTKTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_kieuTKTVActionPerformed(evt);
            }
        });

        lbTongVaoTV.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTongVaoTV.setForeground(new java.awt.Color(0, 204, 51));
        lbTongVaoTV.setText("Tổng vào:");
        pnVaoTV.add(lbTongVaoTV);

        lbTongVaoNumTV.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        pnVaoTV.add(lbTongVaoNumTV);

        javax.swing.GroupLayout pnRaTVLayout = new javax.swing.GroupLayout(pnRaTV);
        pnRaTV.setLayout(pnRaTVLayout);
        pnRaTVLayout.setHorizontalGroup(
            pnRaTVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 113, Short.MAX_VALUE)
        );
        pnRaTVLayout.setVerticalGroup(
            pnRaTVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        lbFromdateTV.setText("Từ ngày");
        pnFromDateTV.add(lbFromdateTV);
        pnFromDateTV.add(spnNgayTVFrom);
        JSpinner.DefaultEditor editor1 = (JSpinner.DefaultEditor) spnNgayTVFrom.getEditor();
        JTextField textField1 = editor1.getTextField();
        textField1.setColumns(2);

        lbFromMonthTV.setText("Tháng");
        pnFromDateTV.add(lbFromMonthTV);
        pnFromDateTV.add(spnThangTVFrom);
        JSpinner.DefaultEditor editor2 = (JSpinner.DefaultEditor) spnThangTVFrom.getEditor();
        JTextField textField2 = editor2.getTextField();
        textField2.setColumns(2);

        lbFromYearTV.setText("Năm");
        pnFromDateTV.add(lbFromYearTV);
        pnFromDateTV.add(jtfNamTVFrom);

        lbFromdateTV1.setText("Đến ngày");
        pnFromDateTV1.add(lbFromdateTV1);
        pnFromDateTV1.add(spnNgayTVFrom1);
        JSpinner.DefaultEditor editor11 = (JSpinner.DefaultEditor) spnNgayTVFrom1.getEditor();
        JTextField textField11 = editor11.getTextField();
        textField11.setColumns(2);

        lbFromMonthTV1.setText("Tháng");
        pnFromDateTV1.add(lbFromMonthTV1);
        pnFromDateTV1.add(spnThangTVFrom1);
        JSpinner.DefaultEditor editor22 = (JSpinner.DefaultEditor) spnThangTVFrom1.getEditor();
        JTextField textField22 = editor22.getTextField();
        textField22.setColumns(2);

        lbFromYearTV1.setText("Năm");
        pnFromDateTV1.add(lbFromYearTV1);
        pnFromDateTV1.add(jtfNamTVFrom1);

        javax.swing.GroupLayout pn_menuBorrowedDevice1Layout = new javax.swing.GroupLayout(pn_menuBorrowedDevice1);
        pn_menuBorrowedDevice1.setLayout(pn_menuBorrowedDevice1Layout);
        pn_menuBorrowedDevice1Layout.setHorizontalGroup(
            pn_menuBorrowedDevice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_menuBorrowedDevice1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbb_kieuTKTV, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTKTV, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(pnVaoTV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122)
                .addComponent(pnRaTV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(208, 208, 208))
            .addGroup(pn_menuBorrowedDevice1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(pnFromDateTV, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(pnFromDateTV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_menuBorrowedDevice1Layout.setVerticalGroup(
            pn_menuBorrowedDevice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_menuBorrowedDevice1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_menuBorrowedDevice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_menuBorrowedDevice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbb_kieuTKTV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTKTV, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnVaoTV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnRaTV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_menuBorrowedDevice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnFromDateTV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnFromDateTV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        cbb_kieuTK.setBackground(Color.white);
        pnFromDateTV.setVisible(false);
        pnFromDateTV1.setVisible(false);

        javax.swing.GroupLayout pnTimeTVLayout = new javax.swing.GroupLayout(pnTimeTV);
        pnTimeTV.setLayout(pnTimeTVLayout);
        pnTimeTVLayout.setHorizontalGroup(
            pnTimeTVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 861, Short.MAX_VALUE)
            .addGroup(pnTimeTVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnTimeTVLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pn_menuBorrowedDevice1, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnTimeTVLayout.setVerticalGroup(
            pnTimeTVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
            .addGroup(pnTimeTVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnTimeTVLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pn_menuBorrowedDevice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pn_menuBorrowedDevice1.setVisible(false);

        pnoptionmember.add(pnTimeTV, java.awt.BorderLayout.CENTER);

        jRadioKhoaTV.setText("Khoa");
        jRadioKhoaTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioKhoaTVActionPerformed(evt);
            }
        });
        pnKhoaNganh.add(jRadioKhoaTV);

        jRadioNganhTV.setText("Ngành");
        jRadioNganhTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioNganhTVActionPerformed(evt);
            }
        });
        pnKhoaNganh.add(jRadioNganhTV);

        jRadioTimeTV.setText("Thời gian");
        jRadioTimeTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioTimeTVActionPerformed(evt);
            }
        });
        pnKhoaNganh.add(jRadioTimeTV);

        pnoptionmember.add(pnKhoaNganh, java.awt.BorderLayout.PAGE_START);

        pnThongkeTV.add(pnoptionmember, java.awt.BorderLayout.PAGE_START);

        tabThongke.addTab("Thành viên", pnThongkeTV);

        pnThongKeTBDangMuon.setBackground(new java.awt.Color(255, 255, 255));
        pnThongKeTBDangMuon.setLayout(new java.awt.BorderLayout());

        pn_BorrowedDevice.setBackground(new java.awt.Color(255, 255, 255));
        pn_BorrowedDevice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        pn_BorrowedDevice.setLayout(new java.awt.BorderLayout());

        pn_menuBorrowedDevice.setBackground(new java.awt.Color(255, 255, 255));
        pn_menuBorrowedDevice.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống kê thiết bị đang được mượn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        pn_menuBorrowedDevice.setPreferredSize(new java.awt.Dimension(810, 150));

        spn_day.setModel(dayModel);
        spn_day.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        spn_day.setEnabled(false);
        spn_day.setInheritsPopupMenu(true);
        spn_day.setPreferredSize(new java.awt.Dimension(70, 25));

        jtfYear.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jtfYear.setEnabled(false);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        jtfYear.setText(String.valueOf(currentYear));
        jtfYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfYearActionPerformed(evt);
            }
        });

        jLabel3.setText("Tháng");
        jLabel3.setEnabled(false);

        cbbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cbbMonth.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cbbMonth.setEnabled(false);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        cbbMonth.setSelectedIndex(currentMonth);

        jLabel2.setText("Từ Ngày");
        jLabel2.setEnabled(false);

        jbtBorrowedDevice.setBackground(new java.awt.Color(0, 204, 51));
        jbtBorrowedDevice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtBorrowedDevice.setForeground(new java.awt.Color(255, 255, 255));
        jbtBorrowedDevice.setText("Thống Kê");
        jbtBorrowedDevice.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jbtBorrowedDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBorrowedDeviceActionPerformed(evt);
            }
        });

        jLabel4.setText("Năm");
        jLabel4.setEnabled(false);

        spn_day1.setModel(dayModel1);
        spn_day1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        spn_day1.setInheritsPopupMenu(true);
        spn_day1.setPreferredSize(new java.awt.Dimension(70, 25));

        jtfYear1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        int currentYear1 = Calendar.getInstance().get(Calendar.YEAR);
        jtfYear.setText(String.valueOf(currentYear1));
        jtfYear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfYear1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Tháng");

        cbbMonth1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cbbMonth1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        int currentMonth1 = Calendar.getInstance().get(Calendar.MONTH);
        cbbMonth.setSelectedIndex(currentMonth1);

        jLabel6.setText("Đến Ngày");

        jLabel7.setText("Năm");

        cbb_kieuTK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Thống kê trong 1 ngày", "Thống kê trong 1 tháng", "Thống kê trong 1 năm", "Thống kê trong khoảng" }));
        cbb_kieuTK.setBorder(javax.swing.BorderFactory.createTitledBorder("Kiểu thống kê"));
        cbb_kieuTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_kieuTKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_menuBorrowedDeviceLayout = new javax.swing.GroupLayout(pn_menuBorrowedDevice);
        pn_menuBorrowedDevice.setLayout(pn_menuBorrowedDeviceLayout);
        pn_menuBorrowedDeviceLayout.setHorizontalGroup(
            pn_menuBorrowedDeviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_menuBorrowedDeviceLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pn_menuBorrowedDeviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_menuBorrowedDeviceLayout.createSequentialGroup()
                        .addComponent(cbb_kieuTK, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtBorrowedDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_menuBorrowedDeviceLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spn_day, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfYear, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(spn_day1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfYear1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(136, 136, 136))
        );
        pn_menuBorrowedDeviceLayout.setVerticalGroup(
            pn_menuBorrowedDeviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_menuBorrowedDeviceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_menuBorrowedDeviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_kieuTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtBorrowedDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(pn_menuBorrowedDeviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(spn_day1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cbbMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jtfYear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(spn_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jtfYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        spn_day.setBackground(Color.white);
        jtfYear.setBackground(Color.white);
        cbbMonth.setBackground(Color.white);
        spn_day1.setBackground(Color.white);
        jtfYear1.setBackground(Color.white);
        cbbMonth1.setBackground(Color.white);
        cbb_kieuTK.setBackground(Color.white);

        pn_BorrowedDevice.add(pn_menuBorrowedDevice, java.awt.BorderLayout.NORTH);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 1, 5, 1, new java.awt.Color(255, 255, 255)));
        jPanel9.setLayout(new java.awt.BorderLayout());

        tb_BorrowedDevice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã thiết bị", "Tên thiết bị", "Mã TT ", "Mã thành viên", "Thời gian mượn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_BorrowedDevice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_BorrowedDeviceClicked(evt);
            }
        });
        jsp_mainXuLy2.setViewportView(tb_BorrowedDevice);
        loadDataTableThietBiMuon();

        jPanel9.add(jsp_mainXuLy2, java.awt.BorderLayout.CENTER);

        pn_BorrowedDevice.add(jPanel9, java.awt.BorderLayout.CENTER);

        pnThongKeTBDangMuon.add(pn_BorrowedDevice, java.awt.BorderLayout.CENTER);

        tabThongke.addTab("Thiết bị đang được mượn", pnThongKeTBDangMuon);

        pnChartstatus.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnChartstatusLayout = new javax.swing.GroupLayout(pnChartstatus);
        pnChartstatus.setLayout(pnChartstatusLayout);
        pnChartstatusLayout.setHorizontalGroup(
            pnChartstatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 861, Short.MAX_VALUE)
        );
        pnChartstatusLayout.setVerticalGroup(
            pnChartstatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 702, Short.MAX_VALUE)
        );

        tabThongke.addTab("Tình trạng xử lý", pnChartstatus);

        jPanel5.add(tabThongke, java.awt.BorderLayout.PAGE_START);

        jTabbedPane_QLTV.addTab("Thống kê", new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-statistic-30.png")), jPanel5, "Thống kê"); // NOI18N

        getContentPane().add(jTabbedPane_QLTV, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtf_maThanhVienFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_maThanhVienFocusGained
        // TODO add your handling code here:
        if (jtf_maThanhVien.getText().equals("Vui lòng nhập mã thành viên")) {
            jtf_maThanhVien.setText(null);
            jtf_maThanhVien.requestFocus();
            System.out.println("");
        }
    }//GEN-LAST:event_jtf_maThanhVienFocusGained

    private void jtf_maThanhVienFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_maThanhVienFocusLost
        // TODO add your handling code here:
        if (jtf_maThanhVien.getText().length() == 0) {
            addPlaceHolderStyle(jtf_maThanhVien);
            jtf_maThanhVien.setText("Vui lòng nhập mã thành viên");
        }
    }//GEN-LAST:event_jtf_maThanhVienFocusLost

    public void checkEnterClass() {
        // check blank
        if (jtf_maThanhVien.getText().length() == 0 || jtf_maThanhVien.getText().equals("Vui lòng nhập mã thành viên")) {
            JOptionPane.showMessageDialog(null, "Mã thành viên không để trống", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        } 
        
        // check is number
        int matv = -1;
        try {
            matv = Integer.parseInt(jtf_maThanhVien.getText().trim());
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Nhập mã không hợp lệ (Mã phải là số)", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // check exist
        ThanhVien isThanhVIen = thanhvienBLL.getThanhVien(matv);
        if(isThanhVIen == null) {
            JOptionPane.showMessageDialog(null, "Không phải thành viên", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // check violation
        XuLy isXuLy = xlBLL.getXuLyThanhVienDangViPham(matv);
        if(isXuLy != null) {
            JOptionPane.showMessageDialog(null,"Tài khoản hiện đang bị xử lý vi phạm \n"+  "Hình thức: " +isXuLy.getHinhThucXL()+ "\n" + "Từ ngày: " + isXuLy.getNgayXL() , "Vi Phạm", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        jtf_maThanhVien.setText("Vui lòng nhập mã thành viên");
        ThanhVienInformationForm tvForm = new ThanhVienInformationForm();
        tvForm.setup(isThanhVIen.getMaTV(), isThanhVIen.getHoTen(), isThanhVIen.getKhoa(), isThanhVIen.getNganh(), isThanhVIen.getSDT());
        tvForm.setVisible(true);
    }
    
    private void jBtn_enterClassZoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_enterClassZoneActionPerformed
        // TODO add your handling code here:
        checkEnterClass();
    }//GEN-LAST:event_jBtn_enterClassZoneActionPerformed

    private void jtf_searchInQLTVFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_searchInQLTVFocusGained
        // TODO add your handling code here:
        if (jtf_searchInQLTV.getText().equals("Search by ID/name")) {
            jtf_searchInQLTV.setText(null);
            jtf_searchInQLTV.requestFocus();
            System.out.println("");
        }
    }//GEN-LAST:event_jtf_searchInQLTVFocusGained

    private void jtf_searchInQLTVFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_searchInQLTVFocusLost
        // TODO add your handling code here:
        if (jtf_searchInQLTV.getText().length() == 0) {
            addPlaceHolderStyle(jtf_searchInQLTV);
            jtf_searchInQLTV.setText("Search by ID/name");
        }
    }//GEN-LAST:event_jtf_searchInQLTVFocusLost

    private void jtf_searchInQLTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_searchInQLTVActionPerformed
        // TODO add your handling code here:
        String input = jtf_searchInQLTV.getText().toLowerCase().trim();
        DefaultTableModel model = (DefaultTableModel) jTbl_qlThanhVien.getModel();
        model.setRowCount(0);
        List<ThanhVien> list = this.thanhvienBLL.loadThanhVien();
        Object[] row;
        for(ThanhVien tv : list) {
            String matv = tv.getMaTV() + "";
            String hoten = tv.getHoTen();
            String khoa = tv.getKhoa();
            String nganh = tv.getNganh();
            String sdt = tv.getSDT();
            if(matv.toLowerCase().contains(input)
                    || hoten.toLowerCase().contains(input)
                    || khoa.toLowerCase().contains(input)
                    || nganh.toLowerCase().contains(input)
                    || sdt.contains(input)) {
                row = new Object[5];
                row[0] = tv.getMaTV();
                row[1] = tv.getHoTen();
                row[2] = tv.getKhoa();
                row[3] = tv.getNganh();
                row[4] = tv.getSDT();
                model.addRow(row);
            }

        }
    }//GEN-LAST:event_jtf_searchInQLTVActionPerformed

    private void jBtn_xoaThanhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_xoaThanhVienActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel model = (DefaultTableModel) jTbl_qlThanhVien.getModel();
        int selectedRow = jTbl_qlThanhVien.getSelectedRow();

        // Check if a valid cell is clicked
        if (selectedRow != -1) {
            jTbl_qlThanhVien.setRowSelectionInterval(selectedRow, selectedRow);
            Object matv = model.getValueAt(selectedRow, 0);
            Object hoten = model.getValueAt(selectedRow, 1);
            Object khoa = model.getValueAt(selectedRow, 2);
            Object nganh = model.getValueAt(selectedRow, 3);
            Object sdt = model.getValueAt(selectedRow, 4);

            int res = JOptionPane.showConfirmDialog(null, "Bạn có muốn tiếp tục?", "Xóa thành viên",
                    JOptionPane.YES_NO_CANCEL_OPTION);
            if (res != 0) {
                return;
            }

//            ThanhVien tv = session.get(Course.class, 4063); 
//            tv.setMaTV((int) matv);
//            tv.setHoTen((String) hoten);
//            tv.setKhoa((String) khoa);
//            tv.setNganh((String)nganh);
//            tv.setSDT((String)sdt);
//            System.out.println(tv);
            
            thanhvienBLL.delete((int) matv);

            loadDataThanhVienToTableThanhVienMuonTra();
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn thành viên muốn xóa");
        }
    }//GEN-LAST:event_jBtn_xoaThanhVienActionPerformed

    private void jTabbedPane_QLTVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane_QLTVMouseClicked
        // TODO add your handling code here:
        loadDataThanhVienToTableThanhVienMuonTra();
    }//GEN-LAST:event_jTabbedPane_QLTVMouseClicked

    private void tb_xulyClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_xulyClicked
        if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
            String id = tb_xuly.getValueAt(tb_xuly.getSelectedRow(), 0).toString();
            FormXuLy formXuLy = new FormXuLy( id, "showinforXuLy", tb_xuly);
        }
    }//GEN-LAST:event_tb_xulyClicked

    private void reload_xulyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reload_xulyActionPerformed
        jtf_xuly.setText("Search by ......");
        addPlaceHolderStyle(jtf_xuly);
        loadDataTableXuLy();
    }//GEN-LAST:event_reload_xulyActionPerformed

    private void reload_xulyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reload_xulyMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_reload_xulyMouseClicked

    private void btn_addxulyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addxulyActionPerformed
        FormXuLy formXuLy = new FormXuLy( null, "addformxuly", tb_xuly);
    }//GEN-LAST:event_btn_addxulyActionPerformed

    private void jtf_xulyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_xulyActionPerformed
        String input = jtf_xuly.getText().toLowerCase().trim();
        DefaultTableModel model = (DefaultTableModel) tb_xuly.getModel();
        model.setRowCount(0);
        List<XuLy> listxuly = new XuLyBLL().getAllXuly();
        for (XuLy tmp : listxuly) {
            String maXl = tmp.getMaXL() + "";
            String maTV = tmp.getThanhvien().getMaTV() + "";
            String hinthuc = tmp.getHinhThucXL();
            String tien = tmp.getSoTien() != null ? tmp.getSoTien() + "" : "";
            String dateXL = tmp.getNgayXL() + "";
            String tinhtrang;
            if (tmp.getTrangThaiXL() == 0) {
                tinhtrang = "Chưa xử lý";
            } else {
                tinhtrang = "Đã xử lý";
            }

            if (maXl.toLowerCase().contains(input)
                    || maTV.toLowerCase().contains(input)
                    || hinthuc.toLowerCase().contains(input)
                    || tien.toLowerCase().contains(input)
                    || dateXL.toLowerCase().contains(input)
                    || tinhtrang.toLowerCase().contains(input)) {
                if (tmp.getTrangThaiXL() == 0) {
                tinhtrang = "Chưa xử lý";
                } else {
                    tinhtrang = "Đã xử lý";
                }
                model.addRow(new Object[] { tmp.getMaXL(), tmp.getThanhvien().getMaTV(), tmp.getHinhThucXL(), tmp.getSoTien(), tmp.getNgayXL(), tinhtrang});
            }
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        tb_xuly.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tb_xuly.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tb_xuly.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tb_xuly.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tb_xuly.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
        tb_xuly.setRowHeight(25);

    }//GEN-LAST:event_jtf_xulyActionPerformed

    private void jtf_xulyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_xulyFocusLost
       if (jtf_xuly.getText().length() == 0) {
            addPlaceHolderStyle(jtf_xuly);
            jtf_xuly.setText("Search by ......");
        }
    }//GEN-LAST:event_jtf_xulyFocusLost

    private void jtf_xulyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_xulyFocusGained
      if (jtf_xuly.getText().equals("Search by ......")) {
            jtf_xuly.setText(null);
            jtf_xuly.requestFocus();
        }
    }//GEN-LAST:event_jtf_xulyFocusGained

    private void jtf_maThanhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_maThanhVienActionPerformed
        // TODO add your handling code here:
        checkEnterClass();
    }//GEN-LAST:event_jtf_maThanhVienActionPerformed

    private void jRadioKhoaTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioKhoaTVActionPerformed
        // TODO add your handling code here:
        pnChartmember.removeAll();
        pnChartmember.revalidate();
        pnChartmember.repaint();
        pnChartmember.setLayout(new BorderLayout());
        pnChartmember.add(new MemberChart("Khoa","",""),BorderLayout.CENTER);
    }//GEN-LAST:event_jRadioKhoaTVActionPerformed

    private void jRadioNganhTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioNganhTVActionPerformed
        // TODO add your handling code here:
        pnChartmember.removeAll();
        pnChartmember.revalidate();
        pnChartmember.repaint();
        pnChartmember.setLayout(new BorderLayout());
        pnChartmember.add(new MemberChart("Ngành","",""),BorderLayout.CENTER);
    }//GEN-LAST:event_jRadioNganhTVActionPerformed

    private void tabThongkeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabThongkeMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tabThongkeMouseClicked

    private void jRadioTimeTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioTimeTBActionPerformed
        // TODO add your handling code here:
        cbTenTB.setVisible(true);
        cbTenTB.removeAllItems();
        List<ThietBi> list=this.thietbiBLL.loadThietBi();
        Set<String> addedNames = new HashSet<>();
        // Thêm các tên thiết bị vào JComboBox, loại bỏ các tên trùng lặp
        for (ThietBi data : list) {
            String tenTB = data.getTenTB();
            if (!addedNames.contains(tenTB)) {
                cbTenTB.addItem(tenTB);
                addedNames.add(tenTB);
            }
        }
        tmp.setVisible(false);
    }//GEN-LAST:event_jRadioTimeTBActionPerformed

    private void cbTenTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTenTBActionPerformed

    }//GEN-LAST:event_cbTenTBActionPerformed

    private void cbTenTBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTenTBItemStateChanged
        // TODO add your handling code here:
        // Kiểm tra xem sự kiện là sự kiện được chọn hoặc không được chọn
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Xóa tất cả các thành phần hiện có trong pnDeviceChart
            pnDeviceChart.removeAll();
            pnDeviceChart.revalidate();
            pnDeviceChart.repaint();

            // Thêm thành phần mới vào pnDeviceChart
            pnDeviceChart.setLayout(new BorderLayout());
            pnDeviceChart.add(new DeviceChart("Tên", (String) cbTenTB.getSelectedItem(),"",""), BorderLayout.CENTER);
        }
    }//GEN-LAST:event_cbTenTBItemStateChanged

    private void tb_BorrowedDeviceClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_BorrowedDeviceClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_BorrowedDeviceClicked

    private void jbtBorrowedDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBorrowedDeviceActionPerformed
        List<ThongTinSD> danhSachThongTinSD = new ArrayList<>();
        if (cbb_kieuTK.getSelectedItem().equals("Thống kê trong 1 ngày")) {
            int day = (int) spn_day.getValue();
            String month = (String) cbbMonth.getSelectedItem();
            int year = Integer.parseInt(jtfYear.getText());
            String formattedDay = String.format("%02d", day);
            String dateString = year + "-" + month + "-" + formattedDay;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(dateString);
                danhSachThongTinSD = new ThongTinSDBLL().getThietBiDangMuonTrongNgay(date);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        } else if (cbb_kieuTK.getSelectedItem().equals("Thống kê trong 1 tháng")) {
            String month = (String) cbbMonth.getSelectedItem();
            int year = Integer.parseInt(jtfYear.getText());
            danhSachThongTinSD = new ThongTinSDBLL().getThietBiDangMuonTrongThang(year, Integer.parseInt(month));

        }
        else if (cbb_kieuTK.getSelectedItem().equals("Thống kê trong 1 năm")) {
            String month = (String) cbbMonth.getSelectedItem();
            int year = Integer.parseInt(jtfYear.getText());
            danhSachThongTinSD = new ThongTinSDBLL().getThietBiDangMuonTrongNam(year);
        }
        else if (cbb_kieuTK.getSelectedItem().equals("Thống kê trong khoảng")) {
            int day = (int) spn_day.getValue();
            String month = (String) cbbMonth.getSelectedItem();
            int year = Integer.parseInt(jtfYear.getText());
            String formattedDay = String.format("%02d", day);
            String dateString = year + "-" + month + "-" + formattedDay;

            int day1 = (int) spn_day1.getValue();
            String month1 = (String) cbbMonth1.getSelectedItem();
            int year1 = Integer.parseInt(jtfYear1.getText());
            String formattedDay1 = String.format("%02d", day1);
            String dateString1 = year1 + "-" + month1 + "-" + formattedDay1;
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date startdate = sdf.parse(dateString);
                Date enddate = sdf.parse(dateString1);
                danhSachThongTinSD = new ThongTinSDBLL().getThongTinSDTrongKhoangThoiGian(startdate, enddate);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        else{
            danhSachThongTinSD = new ThongTinSDBLL().getThietBiDangMuon();
        }
        DefaultTableModel model = (DefaultTableModel) tb_BorrowedDevice.getModel();
        model.setRowCount(0);    
        for (ThongTinSD thongTinSD : danhSachThongTinSD) {
            int maTB = thongTinSD.getThietbi().getMaTB();
            String tenTB = thongTinSD.getThietbi().getTenTB();
            int maTT = thongTinSD.getMaTT();
            int maTV = thongTinSD.getThanhvien().getMaTV();
            String tgMuon = thongTinSD.getTGMuon().toString();
            model.addRow(new Object[]{maTB, tenTB, maTT, maTV, tgMuon});
        }
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tb_BorrowedDevice.getColumnCount(); i++) {
            tb_BorrowedDevice.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        tb_BorrowedDevice.setRowHeight(25);
    }//GEN-LAST:event_jbtBorrowedDeviceActionPerformed

    private void jtfYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfYearActionPerformed

    private void jtfYear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfYear1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfYear1ActionPerformed

    private void cbb_kieuTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_kieuTKActionPerformed
        if(cbb_kieuTK.getSelectedItem().equals("Tất cả")){
            spn_day.setEnabled(false);
            jtfYear.setEnabled(false);
            jLabel3.setEnabled(false);
            cbbMonth.setEnabled(false);
            jLabel2.setEnabled(false);
            jLabel4.setEnabled(false);

            spn_day1.setEnabled(false);
            jtfYear1.setEnabled(false);
            jLabel5.setEnabled(false);
            cbbMonth1.setEnabled(false);
            jLabel6.setEnabled(false);
            jLabel7.setEnabled(false);
        }
        else if(cbb_kieuTK.getSelectedItem().equals("Thống kê trong 1 ngày")){
            spn_day.setEnabled(true);
            jtfYear.setEnabled(true);
            jLabel3.setEnabled(true);
            cbbMonth.setEnabled(true);
            jLabel2.setEnabled(true);
            jLabel4.setEnabled(true);
            
            spn_day1.setEnabled(false);
            jtfYear1.setEnabled(false);
            jLabel5.setEnabled(false);
            cbbMonth1.setEnabled(false);
            jLabel6.setEnabled(false);
            jLabel7.setEnabled(false);
        }
        else if(cbb_kieuTK.getSelectedItem().equals("Thống kê trong 1 tháng")){
            spn_day.setEnabled(false);
            jtfYear.setEnabled(true);
            jLabel3.setEnabled(true);
            cbbMonth.setEnabled(true);
            jLabel2.setEnabled(false);
            jLabel4.setEnabled(true);
            
            spn_day1.setEnabled(false);
            jtfYear1.setEnabled(false);
            jLabel5.setEnabled(false);
            cbbMonth1.setEnabled(false);
            jLabel6.setEnabled(false);
            jLabel7.setEnabled(false);
        }
        else if(cbb_kieuTK.getSelectedItem().equals("Thống kê trong 1 năm")){
            spn_day.setEnabled(false);
            jtfYear.setEnabled(true);
            jLabel3.setEnabled(false);
            cbbMonth.setEnabled(false);
            jLabel2.setEnabled(false);
            jLabel4.setEnabled(true);
            
            spn_day1.setEnabled(false);
            jtfYear1.setEnabled(false);
            jLabel5.setEnabled(false);
            cbbMonth1.setEnabled(false);
            jLabel6.setEnabled(false);
            jLabel7.setEnabled(false);
        }
        else{
            spn_day.setEnabled(true);
            jtfYear.setEnabled(true);
            jLabel3.setEnabled(true);
            cbbMonth.setEnabled(true);
            jLabel2.setEnabled(true);
            jLabel4.setEnabled(true);
            
            spn_day1.setEnabled(true);
            jtfYear1.setEnabled(true);
            jLabel5.setEnabled(true);
            cbbMonth1.setEnabled(true);
            jLabel6.setEnabled(true);
            jLabel7.setEnabled(true);
        }
    }//GEN-LAST:event_cbb_kieuTKActionPerformed

    private void jRadioTenTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioTenTBActionPerformed
        // TODO add your handling code here:
        cbTenTB.setVisible(false);
        tmp.setVisible(true);
        if (endDateChooser.getDate() != null && startDateChooser.getDate() != null) {
            if (checkDateValidity(startDateChooser.getDate(), endDateChooser.getDate())) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                pnDeviceChart.removeAll();
                pnDeviceChart.revalidate();
                pnDeviceChart.repaint();
                pnDeviceChart.setLayout(new BorderLayout());
                pnDeviceChart.add(new DeviceChart("Time", "", dateFormat.format(startDateChooser.getDate()), dateFormat.format(endDateChooser.getDate())), BorderLayout.CENTER);
            }
        }
    }//GEN-LAST:event_jRadioTenTBActionPerformed

    private void btnTKTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKTVActionPerformed
        // TODO add your handling code here:
        String key = (String) cbb_kieuTKTV.getSelectedItem();
        if (key.equals("Tất cả")) {
            int slTongVao = new ThanhVienDAL().getSLTongVao();
            lbTongVaoNumTV.setText(String.valueOf(slTongVao));
        } else if (key.equals("Thống kê trong 1 ngày")) {
            int day = (int) spnNgayTVFrom.getValue();
            String month = String.valueOf(spnThangTVFrom.getValue()) ;
            int year = Integer.parseInt(jtfNamTVFrom.getText());
            String formattedDay = String.format("%02d", day);
            String dateString = year + "-" + month + "-" + formattedDay;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(dateString);
                pnChartmember.removeAll();
                pnChartmember.revalidate();
                pnChartmember.repaint();
                pnChartmember.setLayout(new BorderLayout());
                pnChartmember.add(new MemberChart("Ngày",sdf.format(date),""),BorderLayout.CENTER);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            lbTongVaoNumTV.setText(String.valueOf(thanhvienBLL.getSLTongVaoTheoNgayNum(new ArrayList<>(), dateString)));
        } else if (key.equals("Thống kê trong khoảng")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int day = (int) spnNgayTVFrom.getValue();
            String month = String.valueOf(spnThangTVFrom.getValue()) ;
            int year = Integer.parseInt(jtfNamTVFrom.getText());
            String formattedDay = String.format("%02d", day);
            String dateString = year + "-" + month + "-" + formattedDay;
            
            int day1 = (int) spnNgayTVFrom1.getValue();
            String month1 = String.valueOf(spnThangTVFrom1.getValue()) ;
            int year1 = Integer.parseInt(jtfNamTVFrom1.getText());
            String formattedDay1 = String.format("%02d", day1);
            String dateString1 = year1 + "-" + month1 + "-" + formattedDay1;
            try {
                Date date = sdf.parse(dateString);
                Date date1 = sdf.parse(dateString1);
                pnChartmember.removeAll();
                pnChartmember.revalidate();
                pnChartmember.repaint();
                pnChartmember.setLayout(new BorderLayout());
                pnChartmember.add(new MemberChart("Khoảng",sdf.format(date),sdf.format(date1)),BorderLayout.CENTER);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            lbTongVaoNumTV.setText(String.valueOf(thanhvienBLL.getSLTongVaoTheoKhoangNum(new ArrayList<>(), dateString, dateString1)));
        }  else if (key.equals("Thống kê trong 1 tháng")) {
            String month = String.valueOf(spnThangTVFrom.getValue()) ;
            pnChartmember.removeAll();
            pnChartmember.revalidate();
            pnChartmember.repaint();
            pnChartmember.setLayout(new BorderLayout());
            pnChartmember.add(new MemberChart("Tháng",month,jtfNamTVFrom.getText()),BorderLayout.CENTER);
            lbTongVaoNumTV.setText(String.valueOf(thanhvienBLL.getSLTongVaoTheoThangNum(new ArrayList<>(), month, jtfNamTVFrom.getText())));
        } else if (key.equals("Thống kê trong 1 năm")) {
            pnChartmember.removeAll();
            pnChartmember.revalidate();
            pnChartmember.repaint();
            pnChartmember.setLayout(new BorderLayout());
            pnChartmember.add(new MemberChart("Năm",jtfNamTVFrom.getText(),""),BorderLayout.CENTER);
            lbTongVaoNumTV.setText(String.valueOf(thanhvienBLL.getSLTongVaoTheoNamNum(new ArrayList<>(),jtfNamTVFrom.getText())));
        }

    }//GEN-LAST:event_btnTKTVActionPerformed

    private void cbb_kieuTKTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_kieuTKTVActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbb_kieuTKTVActionPerformed

    private void jRadioTimeTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioTimeTVActionPerformed
        // TODO add your handling code here:
        pn_menuBorrowedDevice1.setVisible(true);
        pnChartmember.removeAll();
        pnChartmember.revalidate();
        pnChartmember.repaint();
    }//GEN-LAST:event_jRadioTimeTVActionPerformed

    private void cbb_kieuTKTVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_kieuTKTVItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
        // Lấy giá trị của mục đã chọn từ JComboBox
        String selected = (String) cbb_kieuTKTV.getSelectedItem();
        
        // Kiểm tra xem selected có null không để tránh NullPointerException
        if (selected != null) {
            if (selected.equals("Tất cả")) {
                pnFromDateTV.setVisible(false);
            } else if (selected.equals("Thống kê trong 1 ngày")) {
                pnFromDateTV.setVisible(true);
                spnNgayTVFrom.setEnabled(true);
                spnThangTVFrom.setEnabled(true);
            } else if (selected.equals("Thống kê trong 1 tháng")) {
                pnFromDateTV.setVisible(true);
                spnNgayTVFrom.setEnabled(false);
            } else if (selected.equals("Thống kê trong 1 năm")) {
                pnFromDateTV.setVisible(true);
                spnNgayTVFrom.setEnabled(false);
                spnThangTVFrom.setEnabled(false);
            } else if (selected.equals("Thống kê trong khoảng")) {
                pnFromDateTV.setVisible(true);
                spnNgayTVFrom.setEnabled(true);
                spnThangTVFrom.setEnabled(true);
                pnFromDateTV1.setVisible(true);
            }
        }
    }
    }//GEN-LAST:event_cbb_kieuTKTVItemStateChanged

    private void jtf_searchInQLTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_searchInQLTBActionPerformed
        String input = jtf_searchInQLTB.getText().toLowerCase().trim();
        DefaultTableModel model = (DefaultTableModel) tb_Thietbi.getModel();
        model.setRowCount(0);
        List<ThietBi> list = this.thietbiBLL.loadThietBi();
        Object[] row;
        for(ThietBi tb : list) {
            String matb = tb.getMaTB() + "";
            String tentb = tb.getTenTB();
            String motatb = tb.getMoTaTB();
            if(matb.toLowerCase().contains(input)
                    || tentb.toLowerCase().contains(input)
                    || motatb.toLowerCase().contains(input)) {
                row = new Object[3];
                row[0] = tb.getMaTB();
                row[1] = tb.getTenTB();
                row[2] = tb.getMoTaTB();
                model.addRow(row);
            }
        }
    }//GEN-LAST:event_jtf_searchInQLTBActionPerformed

    private void jtf_searchInQLTBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_searchInQLTBFocusLost
        if (jtf_searchInQLTB.getText().length() == 0) {
            addPlaceHolderStyle(jtf_searchInQLTB);
            jtf_searchInQLTB.setText("Search by ID/name");
        }
    }//GEN-LAST:event_jtf_searchInQLTBFocusLost

    private void jtf_searchInQLTBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_searchInQLTBFocusGained
         if (jtf_searchInQLTB.getText().equals("Search by ID/name")) {
            jtf_searchInQLTB.setText(null);
            jtf_searchInQLTB.requestFocus();
            System.out.println("");
        }
    }//GEN-LAST:event_jtf_searchInQLTBFocusGained

    private void jBtn_xoaThanhVien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_xoaThanhVien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtn_xoaThanhVien1ActionPerformed

    private void tabThongkeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabThongkeStateChanged
        int selectedIndex = tabThongke.getSelectedIndex();

    // Xử lý sự kiện
        if (selectedIndex==3){
            
            pnChartstatus.removeAll();
            pnChartstatus.revalidate();
            pnChartstatus.repaint();
            pnChartstatus.setLayout(new BorderLayout());
            pnChartstatus.add(new StatusChart(),BorderLayout.CENTER);
        }
    }//GEN-LAST:event_tabThongkeStateChanged

    public void loadDataTableXuLy() {
        DefaultTableModel model = (DefaultTableModel) tb_xuly.getModel();
        model.setRowCount(0);
        XuLyBLL xulyBLL = new XuLyBLL();
        List<XuLy> listXuly = xulyBLL.getAllXuly();
        for (XuLy tmp : listXuly) {
            String tt;
            if(tmp.getTrangThaiXL()==0) tt ="Chưa xử lý";
            else tt ="Đã xử lý";
            model.addRow(new Object[] { tmp.getMaXL(), tmp.getThanhvien().getMaTV(), tmp.getHinhThucXL(), tmp.getSoTien(), tmp.getNgayXL(), tt});
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        tb_xuly.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tb_xuly.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tb_xuly.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tb_xuly.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tb_xuly.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
        tb_xuly.setRowHeight(25);
    }
    public static void loadDataTableXuLy(JTable tablexuly) {
        DefaultTableModel model = (DefaultTableModel) tablexuly.getModel();
        model.setRowCount(0);
        XuLyBLL xulyBLL = new XuLyBLL();
        List<XuLy> listXuly = xulyBLL.getAllXuly();
        for (XuLy tmp : listXuly) {
            String tt;
            if(tmp.getTrangThaiXL()==0) tt ="Chưa xử lý";
            else tt ="Đã xử lý";
            model.addRow(new Object[] { tmp.getMaXL(), tmp.getThanhvien().getMaTV(), tmp.getHinhThucXL(), tmp.getSoTien(), tmp.getNgayXL(), tt});        
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        tablexuly.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tablexuly.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tablexuly.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tablexuly.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tablexuly.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
        tablexuly.setRowHeight(25);
    }
    
    public void loadDataTableThietBiMuon() {
        DefaultTableModel model = (DefaultTableModel) tb_BorrowedDevice.getModel();
        model.setRowCount(0);
        List<ThongTinSD> danhSachThongTinSD = new ThongTinSDBLL().getThietBiDangMuon();
        for (ThongTinSD thongTinSD : danhSachThongTinSD) {
            int maTB = thongTinSD.getThietbi().getMaTB();
            String tenTB = thongTinSD.getThietbi().getTenTB();
            int maTT = thongTinSD.getMaTT();
            int maTV = thongTinSD.getThanhvien().getMaTV();
            String tgMuon = thongTinSD.getTGMuon().toString();
            model.addRow(new Object[]{maTB, tenTB, maTT, maTV, tgMuon});
            
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tb_BorrowedDevice.getColumnCount(); i++) {
            tb_BorrowedDevice.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        tb_BorrowedDevice.setRowHeight(25);
                                                   
    }
    
    public void loadDataInToTableThietBi(){
        String[] col = new String[] { "Mã thiết bị", "Tên thiết bị", "Mô tả thiết bị"};
        DefaultTableModel model = new DefaultTableModel(col,0);
        List<ThietBi> list = this.thietbiBLL.loadThietBi();
        Object[] row;
        for(ThietBi tb : list) {
             row = new Object[3];
             row[0] = tb.getMaTB();
             row[1] = tb.getTenTB();
             row[2] = tb.getMoTaTB();
             model.addRow(row);
        }
        this.tb_Thietbi.setModel(model);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(new FlatMacLightLaf());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println(ex);
        }
        //</editor-fold>

        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
    public void runMain() {
        setTitle("testing");
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void addPlaceHolderStyle(JTextField jtf) {
        Font f = jtf.getFont();
        f = f.deriveFont(Font.ITALIC);
        jtf.setFont(f);
        jtf.setForeground(Color.gray);
    }

    public void removePlaceHolderStyle(JTextField jtf) {
        Font f = jtf.getFont();
        f = f.deriveFont(Font.PLAIN | Font.BOLD);
        jtf.setFont(f);
        jtf.setForeground(Color.black);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupTB;
    private javax.swing.ButtonGroup btnGroupTV;
    private javax.swing.JButton btnTKTV;
    private javax.swing.JButton btn_addxuly;
    private javax.swing.JComboBox<String> cbTenTB;
    private javax.swing.JComboBox<String> cbbMonth;
    private javax.swing.JComboBox<String> cbbMonth1;
    private javax.swing.JComboBox<String> cbb_kieuTK;
    private javax.swing.JComboBox<String> cbb_kieuTKTV;
    private javax.swing.JButton jBtn_enterClassZone;
    private javax.swing.JButton jBtn_xoaThanhVien;
    private javax.swing.JButton jBtn_xoaThanhVien1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateNgayMT;
    private javax.swing.JLabel jLabe_DienMaTV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_formVaoKhuHocTap;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioKhoaTV;
    private javax.swing.JRadioButton jRadioNganhTV;
    private javax.swing.JRadioButton jRadioTenTB;
    private javax.swing.JRadioButton jRadioTimeTB;
    private javax.swing.JRadioButton jRadioTimeTV;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane_QLTV;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTbl_qlThanhVien;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton jbtBorrowedDevice;
    private javax.swing.JScrollPane jsp_mainXuLy;
    private javax.swing.JScrollPane jsp_mainXuLy2;
    private javax.swing.JTextField jtfNamTVFrom;
    private javax.swing.JTextField jtfNamTVFrom1;
    private javax.swing.JTextField jtfYear;
    private javax.swing.JTextField jtfYear1;
    private javax.swing.JTextField jtf_maThanhVien;
    private javax.swing.JTextField jtf_searchInQLTB;
    private javax.swing.JTextField jtf_searchInQLTV;
    private javax.swing.JTextField jtf_xuly;
    private javax.swing.JLabel lbFromMonthTV;
    private javax.swing.JLabel lbFromMonthTV1;
    private javax.swing.JLabel lbFromYearTV;
    private javax.swing.JLabel lbFromYearTV1;
    private javax.swing.JLabel lbFromdateTV;
    private javax.swing.JLabel lbFromdateTV1;
    private javax.swing.JLabel lbTongVaoNumTV;
    private javax.swing.JLabel lbTongVaoTV;
    private javax.swing.JPanel panel_topXuLy;
    private javax.swing.JPanel panelxuly;
    private javax.swing.JPanel pn2ndOptionTB;
    private javax.swing.JPanel pnChartmember;
    private javax.swing.JPanel pnChartstatus;
    private javax.swing.JPanel pnDeviceChart;
    private javax.swing.JPanel pnFromDateTV;
    private javax.swing.JPanel pnFromDateTV1;
    private javax.swing.JPanel pnKhoaNganh;
    private javax.swing.JPanel pnOptionTB;
    private javax.swing.JPanel pnRaTV;
    private javax.swing.JPanel pnThongKeTBDangMuon;
    private javax.swing.JPanel pnThongkeTB;
    private javax.swing.JPanel pnThongkeTV;
    private javax.swing.JPanel pnTimeTV;
    private javax.swing.JPanel pnVaoTV;
    private javax.swing.JPanel pn_BorrowedDevice;
    private javax.swing.JPanel pn_menuBorrowedDevice;
    private javax.swing.JPanel pn_menuBorrowedDevice1;
    private javax.swing.JPanel pnoptionmember;
    private javax.swing.JButton reload_xuly;
    private javax.swing.JSpinner spnNgayTVFrom;
    private javax.swing.JSpinner spnNgayTVFrom1;
    private javax.swing.JSpinner spnThangTVFrom;
    private javax.swing.JSpinner spnThangTVFrom1;
    private javax.swing.JSpinner spn_day;
    private javax.swing.JSpinner spn_day1;
    private javax.swing.JTabbedPane tabThongke;
    private javax.swing.JTable tbDangKyMuonTra;
    private javax.swing.JTable tbThanhVienMuonTra;
    private javax.swing.JTable tbThietBiMuonTra;
    private javax.swing.JTable tb_BorrowedDevice;
    private javax.swing.JTable tb_Thietbi;
    private javax.swing.JTable tb_xuly;
    // End of variables declaration//GEN-END:variables
}
