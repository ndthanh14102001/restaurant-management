/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.GUIBan;

import DAL.DALBan;
import DAL.DALHoaDon;
import DAL.DALMon;
import DAL.DALNhanVien;
import DAL.DALQuery;
import DTO.Account;
import DTO.Ban;
import DTO.ChiTietHoaDon;
import DTO.ExportPDF;
import DTO.HoaDon;
import DTO.Mon;
import javax.swing.table.DefaultTableModel;
import GUI.Panel.PanelBan;
import java.awt.Color;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 *
 * @author DucThanh
 */
public class GUIBan extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    private DefaultTableModel modelTable;
    DefaultTableModel modelTableGoiThem;
    Account accountConn;
    Ban banCurrent;
    ArrayList<PanelBan> listBan = new ArrayList<>() ;
    public GUIBan(Account account) {
        initComponents();
        this.setIconImage(new ImageIcon("C:\\Users\\DucThanh\\OneDrive\\Documents\\NetBeansProjects\\doAnJava\\src\\icon\\orderIcon.png").getImage());
        this.setLocationRelativeTo(null);
        this.accountConn = account;
        modelTable = (DefaultTableModel) tableBill.getModel();
        modelTableGoiThem = (DefaultTableModel) tableBillGoiThem.getModel();
        showWindow();
        initAllBan();
    }
    private void initAllBan()
    {
        PanelBan ban;
        panelBoxBan.setLayout(new FlowLayout(FlowLayout.LEFT,7,7));
        for (int i = 1; i <=11; i++) {
            ban = new PanelBan(this);
            listBan.add(ban);
            ban.setColor(new DALBan().queryBan(i).getTrangThai());
            ban.setText("Ban " + i);
            panelBoxBan.add(ban);
        }
    }
    private void showWindow()
    {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(2);
        labelDate.setText(new SimpleDateFormat("dd/MM/yy").format(new Date()));
        labelTenNhanVien.setText(new DALNhanVien().queryNhanVienById(accountConn.getMaNV()).getIdNhanVien());
    }
    public void setBanCurrent(int ban)
    {
        labelSoBan.setText(ban + "");
        banCurrent = new DALBan().queryBan(ban);
    }
    public void showBil(int ban)
    {
        
        while ( modelTable.getRowCount()>0)
        {
            modelTable.removeRow(0);
        }
        HoaDon hoaDon = new BUS.BUSHoaDon().queryHoaDonByBan(ban);
        ArrayList<ChiTietHoaDon> listChiTietHoaDon = new BUS.BUSChiTietHoaDon().queryChiTietHoaDon(hoaDon.getIdHoaDon(), false);
        for (int i = 0; i < listChiTietHoaDon.size(); i++) {
            Mon mon = new DALMon().getMonById(listChiTietHoaDon.get(i).getIdMon());
            modelTable.addRow(new Object[]{
                mon.getTenMon(),listChiTietHoaDon.get(i).getSoluong(),mon.getGia(),listChiTietHoaDon.get(i).getTongGia()
            });
        }
        
    }
    public void showBillGoiThem(int ban)
    {
        while ( modelTableGoiThem.getRowCount()>0)
        {
            modelTableGoiThem.removeRow(0);
        }
        HoaDon hoaDon = new BUS.BUSHoaDon().queryHoaDonByBan(ban);
        ArrayList<ChiTietHoaDon> listChiTietHoaDon = new BUS.BUSChiTietHoaDon().queryChiTietHoaDon(hoaDon.getIdHoaDon(), true);
        for (int i = 0; i < listChiTietHoaDon.size(); i++) {
            Mon mon = new DALMon().getMonById(listChiTietHoaDon.get(i).getIdMon());
            modelTableGoiThem.addRow(new Object[]{
                mon.getTenMon(),listChiTietHoaDon.get(i).getSoluong(),mon.getGia(),listChiTietHoaDon.get(i).getTongGia()
            });
        }
    }
    public void refresh()
    {
        showBil(banCurrent.getIdBan());
        showBillGoiThem(banCurrent.getIdBan());
        showTongBill(banCurrent.getIdBan());
        listBan.get(banCurrent.getIdBan()-1).setColor(false);
    }
    private void chuyenBan()
    {
        if(banCurrent.getTrangThai() == true)
        {
            JOptionPane.showMessageDialog(this, "Bàn Không Có Bill!");
        }
        else
        {
           
            String input = (String)JOptionPane.showInputDialog(this, "Nhập Bàn Cần Chuyển Sang :");
            int banChuyen = 0;
            try {
                banChuyen = Integer.parseInt(input);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Phải Nhập Số!");
                return ;
            }
            if( banChuyen > listBan.size()|| banChuyen < 1)
            {
                JOptionPane.showMessageDialog(this, "Bàn Không Tồn Tại!");
            }
            else
            {
                if(new DALBan().queryBan(banChuyen).getTrangThai() == false)
                {
                    JOptionPane.showMessageDialog(this, "Bàn Đã Có Người Ngồi!");
                }
                else
                {
                    HoaDon hoaDon = new BUS.BUSHoaDon().queryHoaDonByBan(banCurrent.getIdBan());
                    if(new BUS.BUSHoaDon().updateBanHoaDon(hoaDon.getIdHoaDon(), banChuyen))
                    {
                        JOptionPane.showMessageDialog(this, "Chuyển Bàn Thành Công!");
                        while(tableBill.getRowCount()>0)
                        {
                            modelTable.removeRow(0);
                        }
                        while(tableBillGoiThem.getRowCount()>0)
                        {
                            modelTableGoiThem.removeRow(0);
                        }
                        banCurrent.setTrangThai(true);

                        new DALBan().updateTrangThai(banCurrent.getIdBan(),banCurrent.getTrangThai());
                        new DALBan().updateTrangThai(banChuyen, false);

                        listBan.get(banCurrent.getIdBan() -1).setColor(banCurrent.getTrangThai());
                        listBan.get(banChuyen -1).setColor(false);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Chuyển Bàn Thất Bại!");
                    }
                }
            }
            
        }
        
    }
    public void showTongBill(int ban)
    {
        float tongtien = 0;
        HoaDon hoaDon = new BUS.BUSHoaDon().queryHoaDonByBan(ban);
        ArrayList<ChiTietHoaDon> listchiHoaDon = new BUS.BUSChiTietHoaDon().getAllChiTietHoaDonByIdHoaDon(hoaDon.getIdHoaDon());
        for (int i = 0; i < listchiHoaDon.size(); i++) {
            tongtien += listchiHoaDon.get(i).getTongGia();
        }
        lbInBill.setText(""+ tongtien);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBox = new javax.swing.JPanel();
        panelBoxBan = new javax.swing.JPanel();
        panelBoxBill = new javax.swing.JPanel();
        panelTitle = new javax.swing.JPanel();
        labelSoBan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelDate = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        labelTenNhanVien = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panelBillGoiThem = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableBillGoiThem = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        panelFunction = new javax.swing.JPanel();
        panelInBill = new javax.swing.JPanel();
        lbInBill = new javax.swing.JLabel();
        panelChuyenBan = new javax.swing.JPanel();
        lbChuyenBan = new javax.swing.JLabel();
        panelHuyMon = new javax.swing.JPanel();
        lbHuyMon = new javax.swing.JLabel();
        panelThemMon = new javax.swing.JPanel();
        lbThemMon = new javax.swing.JLabel();
        panelHome = new javax.swing.JPanel();
        lbHome = new javax.swing.JLabel();
        panelBill = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableBill = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Order");
        setPreferredSize(new java.awt.Dimension(1538, 800));

        panelBox.setToolTipText("");
        panelBox.setPreferredSize(new java.awt.Dimension(1507, 800));
        panelBox.setLayout(new java.awt.BorderLayout());

        panelBoxBan.setBackground(new java.awt.Color(255, 255, 255));
        panelBoxBan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        panelBoxBan.setPreferredSize(new java.awt.Dimension(800, 800));
        panelBox.add(panelBoxBan, java.awt.BorderLayout.WEST);

        panelBoxBill.setPreferredSize(new java.awt.Dimension(725, 700));
        panelBoxBill.setLayout(new java.awt.BorderLayout());

        labelSoBan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Bàn số:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Ngày:");

        labelDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Nhân Viên:");

        labelTenNhanVien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTitleLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(labelSoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(147, 147, 147)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelDate, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
                    .addGroup(panelTitleLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelTitleLayout.setVerticalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelSoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelTitleLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTenNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelBoxBill.add(panelTitle, java.awt.BorderLayout.PAGE_START);

        tableBillGoiThem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Món", "Số lượng", "Đơn giá", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tableBillGoiThem);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Gọi Thêm :");

        panelInBill.setBackground(new java.awt.Color(204, 153, 0));
        panelInBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelInBillMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelInBillMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelInBillMouseExited(evt);
            }
        });
        panelInBill.setLayout(new java.awt.BorderLayout());

        lbInBill.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbInBill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInBill.setText("In Bill");
        lbInBill.setPreferredSize(new java.awt.Dimension(100, 17));
        panelInBill.add(lbInBill, java.awt.BorderLayout.CENTER);

        panelChuyenBan.setBackground(new java.awt.Color(204, 153, 0));
        panelChuyenBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelChuyenBanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelChuyenBanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelChuyenBanMouseExited(evt);
            }
        });

        lbChuyenBan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbChuyenBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbChuyenBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/switchicon.png"))); // NOI18N
        lbChuyenBan.setText("Chuyển Bàn");

        javax.swing.GroupLayout panelChuyenBanLayout = new javax.swing.GroupLayout(panelChuyenBan);
        panelChuyenBan.setLayout(panelChuyenBanLayout);
        panelChuyenBanLayout.setHorizontalGroup(
            panelChuyenBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbChuyenBan, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
        );
        panelChuyenBanLayout.setVerticalGroup(
            panelChuyenBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChuyenBanLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbChuyenBan, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelHuyMon.setBackground(new java.awt.Color(204, 153, 0));
        panelHuyMon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelHuyMonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelHuyMonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelHuyMonMouseExited(evt);
            }
        });

        lbHuyMon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbHuyMon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete-icon.png"))); // NOI18N
        lbHuyMon.setText("Hủy món");

        javax.swing.GroupLayout panelHuyMonLayout = new javax.swing.GroupLayout(panelHuyMon);
        panelHuyMon.setLayout(panelHuyMonLayout);
        panelHuyMonLayout.setHorizontalGroup(
            panelHuyMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbHuyMon, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
        );
        panelHuyMonLayout.setVerticalGroup(
            panelHuyMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbHuyMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelThemMon.setBackground(new java.awt.Color(204, 153, 0));
        panelThemMon.setPreferredSize(new java.awt.Dimension(150, 48));
        panelThemMon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelThemMonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelThemMonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelThemMonMouseExited(evt);
            }
        });
        panelThemMon.setLayout(new java.awt.BorderLayout());

        lbThemMon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbThemMon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThemMon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-icon.png"))); // NOI18N
        lbThemMon.setText("Thêm Món");
        lbThemMon.setPreferredSize(new java.awt.Dimension(128, 48));
        panelThemMon.add(lbThemMon, java.awt.BorderLayout.CENTER);

        panelHome.setBackground(new java.awt.Color(204, 153, 0));
        panelHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelHomeMouseExited(evt);
            }
        });

        lbHome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbHome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/blue-home-icon.png"))); // NOI18N
        lbHome.setText("Home");

        javax.swing.GroupLayout panelHomeLayout = new javax.swing.GroupLayout(panelHome);
        panelHome.setLayout(panelHomeLayout);
        panelHomeLayout.setHorizontalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbHome, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
        );
        panelHomeLayout.setVerticalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelFunctionLayout = new javax.swing.GroupLayout(panelFunction);
        panelFunction.setLayout(panelFunctionLayout);
        panelFunctionLayout.setHorizontalGroup(
            panelFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFunctionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelChuyenBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelThemMon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelHuyMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelInBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelFunctionLayout.setVerticalGroup(
            panelFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFunctionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelInBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelHuyMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelThemMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelChuyenBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelHome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBillGoiThemLayout = new javax.swing.GroupLayout(panelBillGoiThem);
        panelBillGoiThem.setLayout(panelBillGoiThemLayout);
        panelBillGoiThemLayout.setHorizontalGroup(
            panelBillGoiThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBillGoiThemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBillGoiThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .addGroup(panelBillGoiThemLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(panelFunction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBillGoiThemLayout.setVerticalGroup(
            panelBillGoiThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBillGoiThemLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(panelFunction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        tableBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Món", "Số lượng", "Đơn giá", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableBill);

        javax.swing.GroupLayout panelBillLayout = new javax.swing.GroupLayout(panelBill);
        panelBill.setLayout(panelBillLayout);
        panelBillLayout.setHorizontalGroup(
            panelBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBillLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        panelBillLayout.setVerticalGroup(
            panelBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBillLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBillGoiThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBill, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBillGoiThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        panelBoxBill.add(jPanel1, java.awt.BorderLayout.CENTER);

        panelBox.add(panelBoxBill, java.awt.BorderLayout.EAST);

        getContentPane().add(panelBox, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelThemMonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelThemMonMouseClicked
        // TODO add your handling code here:
        if(banCurrent == null)
        {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn bàn");
        }
        else
        {
            GUIOrder themMon = new GUIOrder(accountConn.getMaNV(),banCurrent,this);
            if(banCurrent.getTrangThai()==true)
            {
                
            }
            
            themMon.setVisible(true);
        }

    }//GEN-LAST:event_panelThemMonMouseClicked

    private void panelChuyenBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelChuyenBanMouseClicked
        // TODO add your handling code here:
        chuyenBan();
    }//GEN-LAST:event_panelChuyenBanMouseClicked

    private void panelInBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelInBillMouseClicked
         // TODO add your handling code here:
        if(banCurrent.getTrangThai())
        {
            JOptionPane.showMessageDialog(this, "Bàn Không Có Bill!");
        }
        else
        {
            int check = JOptionPane.showConfirmDialog(rootPane, "In bill Ban " + banCurrent.getIdBan());
        
            if(check == JOptionPane.YES_OPTION)
            {
                banCurrent.setTrangThai(true);
                new DALBan().updateTrangThai(banCurrent.getIdBan(), true);
                listBan.get(banCurrent.getIdBan()-1).setColor(true);
                while ( modelTable.getRowCount()>0)
                {
                    modelTable.removeRow(0);
                }
                while ( modelTableGoiThem.getRowCount()>0)
                {
                    modelTableGoiThem.removeRow(0);
                }
                String idHoaDon = new BUS.BUSHoaDon().queryHoaDonByBan(banCurrent.getIdBan()).getIdHoaDon();

                new DALHoaDon().setStatusHoaDon(idHoaDon, true);
                lbInBill.setText("0.0");
                
                int index= JOptionPane.showConfirmDialog(this, "Xuất File FDF");
                if(index == JOptionPane.YES_OPTION)
                {
                    if(new ExportPDF().xuatPDFHoaDon(new BUS.BUSChiTietHoaDon().getAllChiTietHoaDonByIdHoaDon(idHoaDon)))
                    {
                        JOptionPane.showMessageDialog(this, "Xuất PDF Thành Công!");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Xuất PDF Thất Bại!");
                    }
                }
            }
        }
        
    }//GEN-LAST:event_panelInBillMouseClicked

    private void panelHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHomeMouseClicked
        // TODO add your handling code here:
        GUI.GUIHome home = new GUI.GUIHome(accountConn);
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelHomeMouseClicked

    private void panelChuyenBanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelChuyenBanMouseEntered
        // TODO add your handling code here:
        lbChuyenBan.setForeground(Color.white);
    }//GEN-LAST:event_panelChuyenBanMouseEntered

    private void panelChuyenBanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelChuyenBanMouseExited
        // TODO add your handling code here:
        lbChuyenBan.setForeground(Color.black);
    }//GEN-LAST:event_panelChuyenBanMouseExited

    private void panelHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHomeMouseEntered
        // TODO add your handling code here:
        lbHome.setForeground(Color.white);
    }//GEN-LAST:event_panelHomeMouseEntered

    private void panelHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHomeMouseExited
        // TODO add your handling code here:
        lbHome.setForeground(Color.black);
    }//GEN-LAST:event_panelHomeMouseExited

    private void panelThemMonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelThemMonMouseEntered
        // TODO add your handling code here:
        lbThemMon.setForeground(Color.white);
    }//GEN-LAST:event_panelThemMonMouseEntered

    private void panelThemMonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelThemMonMouseExited
        // TODO add your handling code here:
        lbThemMon.setForeground(Color.black);
    }//GEN-LAST:event_panelThemMonMouseExited

    private void panelHuyMonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHuyMonMouseEntered
        // TODO add your handling code here:
        lbHuyMon.setForeground(Color.white);
    }//GEN-LAST:event_panelHuyMonMouseEntered

    private void panelHuyMonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHuyMonMouseExited
        // TODO add your handling code here:
        lbHuyMon.setForeground(Color.black);
    }//GEN-LAST:event_panelHuyMonMouseExited

    private void panelInBillMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelInBillMouseEntered
        // TODO add your handling code here:
        lbInBill.setForeground(Color.white);
    }//GEN-LAST:event_panelInBillMouseEntered

    private void panelInBillMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelInBillMouseExited
        // TODO add your handling code here:
        lbInBill.setForeground(Color.black);
    }//GEN-LAST:event_panelInBillMouseExited

    private void panelHuyMonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHuyMonMouseClicked
        // TODO add your handling code here:
        HoaDon hoaDon = new BUS.BUSHoaDon().queryHoaDonByBan(banCurrent.getIdBan());
        int rowBill =-1;
        try {
            rowBill = tableBill.getSelectedRow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int rowBillGoiThem = -1;
        try {
            rowBillGoiThem = tableBillGoiThem.getSelectedRow();
        } catch (Exception e) {
        }
        if(rowBill>=0)
        {
            Mon mon = new DALMon().getMonByTen((String)modelTable.getValueAt(rowBill, 0));
            if(new BUS.BUSChiTietHoaDon().deleteChiTietHoaDon(hoaDon.getIdHoaDon(), mon.getIdMon(), false))
            {
                modelTable.removeRow(rowBill);
                JOptionPane.showMessageDialog(this, "Hủy Món Thành Công!");
                new BUS.BUSHoaDon().updateTongTien(hoaDon.getIdHoaDon());
                lbInBill.setText(new BUS.BUSHoaDon().queryHoaDonByBan(banCurrent.getIdBan()).getTongtien() + "");
            }
        }
        if(rowBillGoiThem >= 0)
        {
            Mon mon = new DALMon().getMonByTen((String)modelTableGoiThem.getValueAt(rowBillGoiThem, 0));
            if(new BUS.BUSChiTietHoaDon().deleteChiTietHoaDon(hoaDon.getIdHoaDon(), mon.getIdMon(), true))
            {
                modelTableGoiThem.removeRow(rowBillGoiThem);
                JOptionPane.showMessageDialog(this, "Hủy Món Thành Công!");
                new BUS.BUSHoaDon().updateTongTien(hoaDon.getIdHoaDon());
                lbInBill.setText(new BUS.BUSHoaDon().queryHoaDonByBan(banCurrent.getIdBan()).getTongtien() + "");
            }
        }
    }//GEN-LAST:event_panelHuyMonMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GUIBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GUIBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GUIBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GUIBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GUIBan(new Account()).setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelDate;
    private javax.swing.JLabel labelSoBan;
    private javax.swing.JLabel labelTenNhanVien;
    private javax.swing.JLabel lbChuyenBan;
    private javax.swing.JLabel lbHome;
    private javax.swing.JLabel lbHuyMon;
    private javax.swing.JLabel lbInBill;
    private javax.swing.JLabel lbThemMon;
    private javax.swing.JPanel panelBill;
    private javax.swing.JPanel panelBillGoiThem;
    private javax.swing.JPanel panelBox;
    private javax.swing.JPanel panelBoxBan;
    private javax.swing.JPanel panelBoxBill;
    private javax.swing.JPanel panelChuyenBan;
    private javax.swing.JPanel panelFunction;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelHuyMon;
    private javax.swing.JPanel panelInBill;
    private javax.swing.JPanel panelThemMon;
    private javax.swing.JPanel panelTitle;
    private javax.swing.JTable tableBill;
    private javax.swing.JTable tableBillGoiThem;
    // End of variables declaration//GEN-END:variables
}
