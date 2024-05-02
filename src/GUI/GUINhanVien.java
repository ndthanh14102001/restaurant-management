/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAL.DALNhanVien;
import DTO.Account;
import DTO.NhanVien;
import DTO.Quyen;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author DucThanh
 */
public class GUINhanVien extends javax.swing.JFrame {

    /**
     * Creates new form GUINhanVien
     */
    DefaultTableModel model;
    ArrayList<NhanVien> listNhanVien;
    ArrayList<Quyen> listQuyen;
    public GUINhanVien() {
        initComponents();
        init();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(2);
        this.setIconImage(new ImageIcon("icon/nhanVienIcon.png").getImage());
    }
    public void resetTable()
    {
        listNhanVien = new BUS.BUSNhanVien().getAllNhanVien();
        showAll(listNhanVien);
    }
    private void init()
    {
        model = (DefaultTableModel) tableList.getModel();
        listNhanVien = new BUS.BUSNhanVien().getAllNhanVien();
        showAll(listNhanVien);
        
        listQuyen = new BUS.BUSQuyen().getAllQuyen();
        showAllValueComboBox(listQuyen);
        
    }
    private ArrayList<Account> sortQuyen(String  tenQuyen)
    {
        int idQuyen =Integer.parseInt(new BUS.BUSQuyen().queryQuyenByTen(tenQuyen).getIdQuyen()) ;
        ArrayList<Account> listAccount =new BUS.BUSAccount().queryListAccountByQuyen(idQuyen);
        return listAccount;
    }
    private void showAllValueComboBox(ArrayList<Quyen> listQuyen)
    {
        cbSortQuyen.addItem("ALL");
        for (int i = 1; i < listQuyen.size(); i++) {
            cbSortQuyen.addItem(listQuyen.get(i).getTenQuyen());
        }
    }
//    private ArrayList<Account> sortQuyen(String  tenQuyen)
//    {
//        int idQuyen =Integer.parseInt(new BUS.BUSQuyen().queryQuyenByTen(tenQuyen).getIdQuyen()) ;
//        ArrayList<Account> listAccount =new BUS.BUSAccount().queryListAccountByQuyen(idQuyen);
//        return listAccount;
//    }
    private void showAll(ArrayList<NhanVien> listNhanVien)
    {
        while(model.getRowCount()>0)
        {
            model.removeRow(0);
        }
        for(int i=0;i<listNhanVien.size();i++)
        {
            String gioiTinh = null;
            String trangThai = null;
            if(listNhanVien.get(i).isGioiTinhNhanVien())
            {
                gioiTinh = "Nữ";
            }
            else
            {
                gioiTinh = "Nam";
            }
            if(listNhanVien.get(i).isTrangThai())
            {
                trangThai = "Đang Làm";
            }
            else
            {
                trangThai = "Nghỉ Làm";
            }
            model.addRow(new Object[]{
                i+1,listNhanVien.get(i).getTenNhanVien(),listNhanVien.get(i).getStdNhanVien(),gioiTinh,trangThai
            });
        }
    }
    public void initGUIThemNhanVien()
    {
        cbGioiTinh.removeAllItems();
        cbGioiTinh.addItem("Nam");
        cbGioiTinh.addItem("Nữ");
        GUIThemNhanVien.setLocationRelativeTo(this);
        GUIThemNhanVien.setModal(true);
    }
    private void resetGUIThemNhanVien()
    {
        txtSDT.setText("");
        txtTenNhanVien.setText("");
        cbGioiTinh.setSelectedIndex(0);
    }
    NhanVien nhanVienSelect;
    private void initGUISuaNhanVien()
    {
        GUISuaThongTin.setLocationRelativeTo(this);
        GUISuaThongTin.setModal(true);
        GUISuaThongTintxtTenNhanVien.setText(nhanVienSelect.getTenNhanVien());
        GUISuaThongTintxtSDT.setText(nhanVienSelect.getStdNhanVien());
        GUISuaThongTincbGioiTinh.removeAllItems();
        GUISuaThongTincbGioiTinh.addItem("Nam");
        GUISuaThongTincbGioiTinh.addItem("Nữ");
        
        if(nhanVienSelect.isGioiTinhNhanVien())
        {
            GUISuaThongTincbGioiTinh.setSelectedIndex(1);
        }
        else
        {
            GUISuaThongTincbGioiTinh.setSelectedIndex(0);
        }
        
        GUISuaThongTincbTrangThai.removeAllItems();
        GUISuaThongTincbTrangThai.addItem("Nghỉ làm");
        GUISuaThongTincbTrangThai.addItem("Đang Làm");
        
        if(nhanVienSelect.isTrangThai())
        {
            GUISuaThongTincbTrangThai.setSelectedIndex(1);
        }
        else
        {
            GUISuaThongTincbTrangThai.setSelectedIndex(0);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GUIThemNhanVien = new javax.swing.JDialog();
        panelTitle1 = new javax.swing.JPanel();
        labelTitle1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtTenNhanVien = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbGioiTinh = new javax.swing.JComboBox<>();
        btnDongY = new javax.swing.JButton();
        btnClose1 = new javax.swing.JButton();
        txtSDT = new javax.swing.JTextField();
        GUISuaThongTin = new javax.swing.JDialog();
        panelTitle2 = new javax.swing.JPanel();
        labelTitle2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        GUISuaThongTintxtTenNhanVien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        GUISuaThongTincbGioiTinh = new javax.swing.JComboBox<>();
        btnDongY1 = new javax.swing.JButton();
        btnClose2 = new javax.swing.JButton();
        GUISuaThongTintxtSDT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        GUISuaThongTincbTrangThai = new javax.swing.JComboBox<>();
        panelTitle = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        panelContent = new javax.swing.JPanel();
        panelFunction = new javax.swing.JPanel();
        btnThemNhanVien = new javax.swing.JButton();
        cbSortQuyen = new javax.swing.JComboBox<>();
        btnSuaThongTin = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        scrollDanhSach = new javax.swing.JScrollPane();
        tableList = new javax.swing.JTable();

        GUIThemNhanVien.setTitle("Thêm Nhân Viên");
        GUIThemNhanVien.setMinimumSize(new java.awt.Dimension(510, 400));
        GUIThemNhanVien.setResizable(false);

        panelTitle1.setBackground(new java.awt.Color(204, 153, 0));
        panelTitle1.setPreferredSize(new java.awt.Dimension(456, 50));
        panelTitle1.setLayout(new java.awt.BorderLayout());

        labelTitle1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle1.setText("Thêm Nhân Viên");
        panelTitle1.add(labelTitle1, java.awt.BorderLayout.CENTER);

        GUIThemNhanVien.getContentPane().add(panelTitle1, java.awt.BorderLayout.NORTH);

        jPanel2.setBackground(new java.awt.Color(242, 170, 144));

        txtTenNhanVien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenNhanVienFocusLost(evt);
            }
        });
        txtTenNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNhanVienActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Tên Nhân Viên:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Số Điện Thoại:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Giới Tính");

        btnDongY.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDongY.setText("Đồng Ý");
        btnDongY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongYActionPerformed(evt);
            }
        });

        btnClose1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnClose1.setText("Close");
        btnClose1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClose1ActionPerformed(evt);
            }
        });

        txtSDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSDTFocusLost(evt);
            }
        });
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnDongY)
                        .addGap(18, 18, 18)
                        .addComponent(btnClose1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDongY)
                    .addComponent(btnClose1))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        GUIThemNhanVien.getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        GUISuaThongTin.setMinimumSize(new java.awt.Dimension(510, 500));
        GUISuaThongTin.setResizable(false);

        panelTitle2.setBackground(new java.awt.Color(204, 153, 0));
        panelTitle2.setPreferredSize(new java.awt.Dimension(456, 50));
        panelTitle2.setLayout(new java.awt.BorderLayout());

        labelTitle2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle2.setText("Sửa Thông Tin Nhân Viên");
        panelTitle2.add(labelTitle2, java.awt.BorderLayout.CENTER);

        GUISuaThongTin.getContentPane().add(panelTitle2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(242, 170, 144));

        GUISuaThongTintxtTenNhanVien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                GUISuaThongTintxtTenNhanVienFocusLost(evt);
            }
        });
        GUISuaThongTintxtTenNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUISuaThongTintxtTenNhanVienActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Tên Nhân Viên:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Số Điện Thoại:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Giới Tính:");

        btnDongY1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDongY1.setText("Đồng Ý");
        btnDongY1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongY1ActionPerformed(evt);
            }
        });

        btnClose2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnClose2.setText("Close");
        btnClose2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClose2ActionPerformed(evt);
            }
        });

        GUISuaThongTintxtSDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                GUISuaThongTintxtSDTFocusLost(evt);
            }
        });
        GUISuaThongTintxtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUISuaThongTintxtSDTActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Trạng Thái:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GUISuaThongTintxtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GUISuaThongTincbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GUISuaThongTintxtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GUISuaThongTincbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDongY1)
                .addGap(18, 18, 18)
                .addComponent(btnClose2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GUISuaThongTintxtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GUISuaThongTintxtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GUISuaThongTincbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GUISuaThongTincbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDongY1)
                    .addComponent(btnClose2))
                .addGap(27, 27, 27))
        );

        GUISuaThongTin.getContentPane().add(jPanel3, java.awt.BorderLayout.LINE_END);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Thêm Nhân Viên");

        panelTitle.setBackground(new java.awt.Color(204, 153, 0));
        panelTitle.setLayout(new java.awt.BorderLayout());

        labelTitle.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("Danh Sách Nhân Viên");
        panelTitle.add(labelTitle, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelTitle, java.awt.BorderLayout.PAGE_START);

        panelFunction.setBackground(new java.awt.Color(204, 153, 0));

        btnThemNhanVien.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThemNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-icon.png"))); // NOI18N
        btnThemNhanVien.setText("Thêm Nhân Viên");
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });

        cbSortQuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSortQuyenActionPerformed(evt);
            }
        });

        btnSuaThongTin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSuaThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/swapicon.png"))); // NOI18N
        btnSuaThongTin.setText("Sửa Thông Tin");
        btnSuaThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThongTinActionPerformed(evt);
            }
        });

        btnClose.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close-icon.png"))); // NOI18N
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFunctionLayout = new javax.swing.GroupLayout(panelFunction);
        panelFunction.setLayout(panelFunctionLayout);
        panelFunctionLayout.setHorizontalGroup(
            panelFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFunctionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbSortQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addComponent(btnThemNhanVien)
                .addGap(18, 18, 18)
                .addComponent(btnSuaThongTin)
                .addGap(18, 18, 18)
                .addComponent(btnClose)
                .addGap(39, 39, 39))
        );
        panelFunctionLayout.setVerticalGroup(
            panelFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFunctionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuaThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelFunctionLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(cbSortQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnThemNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setLayout(new java.awt.BorderLayout());

        tableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên Nhân Viên", "Số Điện Thoại", "Giới Tính", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollDanhSach.setViewportView(tableList);

        jPanel1.add(scrollDanhSach, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelFunction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addComponent(panelFunction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(panelContent, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        // TODO add your handling code here:
        initGUIThemNhanVien();
        GUIThemNhanVien.setVisible(true);
        
    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    private void cbSortQuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSortQuyenActionPerformed
        // TODO add your handling code here:
        if(cbSortQuyen.getSelectedIndex() == 0)
        {
            listNhanVien = new BUS.BUSNhanVien().getAllNhanVien();
        }
        else
        {
            ArrayList<Account> listAccount = sortQuyen((String)cbSortQuyen.getSelectedItem());
            listNhanVien = new ArrayList<>();
            for (int i = 0; i < listAccount.size(); i++) {
                listNhanVien.add(new DALNhanVien().queryNhanVienById(listAccount.get(i).getMaNV()));
            }
        }
        showAll(listNhanVien);
       
    }//GEN-LAST:event_cbSortQuyenActionPerformed

    private void btnSuaThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThongTinActionPerformed
        // TODO add your handling code here:
        if(tableList.getSelectedRow()<0)
        {
            JOptionPane.showMessageDialog(panelContent, "Chưa Chọn Nhân Viên");
        }
        else
        {
            String tenNhanVien =(String) model.getValueAt(tableList.getSelectedRow(), 1);
            nhanVienSelect = new BUS.BUSNhanVien().queryNhanVienByTen(tenNhanVien);
            initGUISuaNhanVien();
            GUISuaThongTin.setVisible(true);
        }
        
    }//GEN-LAST:event_btnSuaThongTinActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void txtTenNhanVienFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenNhanVienFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNhanVienFocusLost

    private void txtTenNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNhanVienActionPerformed

    private void btnDongYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongYActionPerformed
        // TODO add your handling code here:
        if(txtTenNhanVien.getText().equals("") && txtSDT.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống");
        }
        else
        {
            Random rd = new Random();
            int id = rd.nextInt((999-100)+1)+100;
            boolean gioitinh;
            if(cbGioiTinh.getSelectedIndex()==0)
            {
                gioitinh= false;
            }
            else
            {
                gioitinh = true;
            }
            NhanVien nhanVien = new NhanVien(id+"", txtSDT.getText(), txtTenNhanVien.getText(), gioitinh, true);
            if(new DAL.DALNhanVien().addNhanVien(nhanVien))
            {
                JOptionPane.showMessageDialog(rootPane,"Thêm Nhân Viên Thành Công!");
                resetTable();
                resetGUIThemNhanVien();
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane,"Thêm Nhân Viên Thất Bại");
                resetGUIThemNhanVien();
            }
        }
    }//GEN-LAST:event_btnDongYActionPerformed

    private void btnClose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose1ActionPerformed
        // TODO add your handling code here:
        GUIThemNhanVien.setVisible(false);
    }//GEN-LAST:event_btnClose1ActionPerformed

    private void txtSDTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSDTFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTFocusLost

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void GUISuaThongTintxtTenNhanVienFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_GUISuaThongTintxtTenNhanVienFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_GUISuaThongTintxtTenNhanVienFocusLost

    private void GUISuaThongTintxtTenNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GUISuaThongTintxtTenNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GUISuaThongTintxtTenNhanVienActionPerformed

    private void btnDongY1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongY1ActionPerformed
        // TODO add your handling code here:
        
        if(GUISuaThongTintxtTenNhanVien.getText().equals("") && GUISuaThongTintxtSDT.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống");
        }
        else
        {
            boolean gioitinh = false,trangthai = false;
            if(GUISuaThongTincbGioiTinh.getSelectedIndex()==0)
            {
                gioitinh = false;
            }
            else
            {
                gioitinh = true;
            }
            if(GUISuaThongTincbTrangThai.getSelectedIndex()==0)
            {
                trangthai = false;
            }
            else
            {
                trangthai = true;
            }
            if(new BUS.BUSNhanVien().updateNhanVien(new NhanVien(nhanVienSelect.getIdNhanVien(),GUISuaThongTintxtSDT.getText(), GUISuaThongTintxtTenNhanVien.getText(),gioitinh , trangthai)))
            {
                GUISuaThongTin.setVisible(false);
                JOptionPane.showMessageDialog(rootPane, "Sửa Thông Tin Thành Công");
                resetTable();
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane, "Sửa Thông Tin Không Thành Công");
            }
        }
    }//GEN-LAST:event_btnDongY1ActionPerformed

    private void btnClose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose2ActionPerformed
        // TODO add your handling code here:
        this.GUISuaThongTin.setVisible(false);
    }//GEN-LAST:event_btnClose2ActionPerformed

    private void GUISuaThongTintxtSDTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_GUISuaThongTintxtSDTFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_GUISuaThongTintxtSDTFocusLost

    private void GUISuaThongTintxtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GUISuaThongTintxtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GUISuaThongTintxtSDTActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//
// 
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GUINhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GUINhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GUINhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GUINhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
// 
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GUINhanVien().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog GUISuaThongTin;
    private javax.swing.JComboBox<String> GUISuaThongTincbGioiTinh;
    private javax.swing.JComboBox<String> GUISuaThongTincbTrangThai;
    private javax.swing.JTextField GUISuaThongTintxtSDT;
    private javax.swing.JTextField GUISuaThongTintxtTenNhanVien;
    private javax.swing.JDialog GUIThemNhanVien;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnClose1;
    private javax.swing.JButton btnClose2;
    private javax.swing.JButton btnDongY;
    private javax.swing.JButton btnDongY1;
    private javax.swing.JButton btnSuaThongTin;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JComboBox<String> cbGioiTinh;
    private javax.swing.JComboBox<String> cbSortQuyen;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelTitle1;
    private javax.swing.JLabel labelTitle2;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelFunction;
    private javax.swing.JPanel panelTitle;
    private javax.swing.JPanel panelTitle1;
    private javax.swing.JPanel panelTitle2;
    private javax.swing.JScrollPane scrollDanhSach;
    private javax.swing.JTable tableList;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNhanVien;
    // End of variables declaration//GEN-END:variables
}
