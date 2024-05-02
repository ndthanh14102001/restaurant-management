/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.GUIBan;

import DAL.DALBan;
import DAL.DALChiTietHoaDon;
import DAL.DALDanhMuc;
import DAL.DALHoaDon;
import DAL.DALMon;
import DTO.Ban;
import DTO.ChiTietHoaDon;
import DTO.DanhMuc;
import DTO.HoaDon;
import DTO.Mon;
import GUI.GUIBan.GUIBan;
import GUI.Panel.PanelMon;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
//import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DucThanh
 */
public class GUIOrder extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    Ban ban;
    String idNv;
    ArrayList<PanelMon> listPanelMon = new ArrayList<>();
    ArrayList<Mon> listMon;
    DefaultTableModel model;
    Random rd = new Random();
    GUIBan dsBan;
    public GUIOrder(String idNv,Ban ban,GUIBan dsban) {
        System.out.println(ban.getTrangThai());
        this.setIconImage(new ImageIcon("C:\\Users\\DucThanh\\OneDrive\\Documents\\NetBeansProjects\\doAnJava\\src\\icon\\add-icon.png").getImage());
        this.ban = ban;
        this.idNv = idNv;
        this.dsBan = dsban;
        initComponents();
        this.setLocationRelativeTo(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        
        model = (DefaultTableModel) tableThem.getModel();
        tableThem.setAlignmentX(LEFT_ALIGNMENT);
        getMonList();
        initCbDanhMuc();
    }
    private void initCbDanhMuc()
    {
        cbDanhMuc.removeAllItems();
        ArrayList<DanhMuc> listDanhMuc = new BUS.BUSDanhMuc().getListDanhMuc();
        cbDanhMuc.addItem("ALL");
        if(ban.getTrangThai()==false)
        {
            for (int i = 0; i < listDanhMuc.size(); i++) {
                cbDanhMuc.addItem(listDanhMuc.get(i).getTen());
            }
        }
        else
        {
            for (int i = 0; i < listDanhMuc.size(); i++) {
                if(!listDanhMuc.get(i).getTen().equals("THÊM"))
                cbDanhMuc.addItem(listDanhMuc.get(i).getTen());
            }
        }
        
    }
    private void getMonList()
    {
        
        listMon = new BUS.BUSMon().queryMonByStatus(true);
        panelDanhSachMon.setLayout(new FlowLayout(FlowLayout.LEFT,7,7));
        if(ban.getTrangThai()==false)
        {
            for (int i = 0; i < listMon.size(); i++) {
                PanelMon panelMon = new PanelMon();
                panelMon.setVisibleButtonSua(false);
                panelMon.setName(listMon.get(i).getTenMon());
                panelMon.setGia(listMon.get(i).getGia());
                panelMon.setImgMon(listMon.get(i).getLinkAnh());
                listPanelMon.add(panelMon);
                panelDanhSachMon.add(panelMon);
            }
        }
        else
        {
            for (int i = 0; i < listMon.size(); i++) {
                if(listMon.get(i).getDanhMuc()!=6)
                {
                    PanelMon panelMon = new PanelMon();
                    panelMon.setVisibleButtonSua(false);
                    panelMon.setName(listMon.get(i).getTenMon());
                    panelMon.setGia(listMon.get(i).getGia());
                    panelMon.setImgMon(listMon.get(i).getLinkAnh());
                    listPanelMon.add(panelMon);
                    panelDanhSachMon.add(panelMon);
                }
                
            }
        }
        
    }
    private void getMonList(ArrayList<Mon> mon)
    {
        panelDanhSachMon.removeAll();
        panelDanhSachMon.setLayout(new FlowLayout(FlowLayout.LEFT,7,7));
        listPanelMon = new ArrayList<PanelMon>();
        if(ban.getTrangThai()==false)
        {
            for (int i = 0; i < mon.size(); i++) {
                PanelMon panelMon = new PanelMon();
                panelMon.setVisibleButtonSua(false);
                panelMon.setName(mon.get(i).getTenMon());
                panelMon.setGia(mon.get(i).getGia());
                panelMon.setImgMon(mon.get(i).getLinkAnh());
                listPanelMon.add(panelMon);
                panelDanhSachMon.add(panelMon);
            }
        }
        else
        {
            for (int i = 0; i < mon.size(); i++) {
                if(listMon.get(i).getDanhMuc()!=6)
                {
                    PanelMon panelMon = new PanelMon();
                    panelMon.setVisibleButtonSua(false);
                    panelMon.setName(mon.get(i).getTenMon());
                    panelMon.setGia(mon.get(i).getGia());
                    panelMon.setImgMon(mon.get(i).getLinkAnh());
                    listPanelMon.add(panelMon);
                    panelDanhSachMon.add(panelMon);
                }
            }
        }
        
    }
    private void themMon()
    {
        for (int i = 0; i < listPanelMon.size(); i++) {
            if(listPanelMon.get(i).getCount()>0)
            {
                int check = checkMonTrongTable(listPanelMon.get(i).getTenMon(), model);
                
                if(check > -1)
                {
                    int soLuong = (int)model.getValueAt(check, 1) + listPanelMon.get(i).getCount();
                    float gia = soLuong * listPanelMon.get(i).getGia();
                    model.setValueAt(soLuong, check, 1);
                    model.setValueAt(gia, check, 3);
                    listPanelMon.get(i).resetCount();
                }
                else
                {
                    model.addRow(new Object[]{
                        listPanelMon.get(i).getTenMon(),listPanelMon.get(i).getCount(),listPanelMon.get(i).getGia(),
                        listPanelMon.get(i).getCount()*listPanelMon.get(i).getGia() 
                    });
                    listPanelMon.get(i).resetCount();
                }
                    
            }
        }
    }
    private int checkMonTrongTable(String name,DefaultTableModel model)
    {
        for (int i = 0; i < model.getRowCount(); i++) {
            if(name.equals(model.getValueAt(i, 0)))
            {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnThem = new javax.swing.JButton();
        panelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableThem = new javax.swing.JTable();
        btnTaoBill = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        panelDanhSachMon = new javax.swing.JPanel();
        cbDanhMuc = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thêm Món");
        setResizable(false);

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-icon.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        tableThem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Món", "Số Lượng", "Đơn Giá", "Tổng Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableThem);

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
        );
        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
        );

        btnTaoBill.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnTaoBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check-icon.png"))); // NOI18N
        btnTaoBill.setText("Đồng Ý");
        btnTaoBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoBillActionPerformed(evt);
            }
        });

        panelDanhSachMon.setBackground(new java.awt.Color(204, 153, 0));
        panelDanhSachMon.setPreferredSize(new java.awt.Dimension(820, 10000));
        scrollPane.setViewportView(panelDanhSachMon);

        cbDanhMuc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDanhMucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(558, 558, 558)
                        .addComponent(btnThem)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnTaoBill)
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnTaoBill)
                    .addComponent(cbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        themMon();
        
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnTaoBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoBillActionPerformed
        // TODO add your handling code here:
        if(model.getRowCount()>0)
        {
            ArrayList<Mon> monList = new ArrayList<>();
            int [] soLuongList = new int[100];
            int count = 0;
            float tongTien = 0;
            for(count =0;count<model.getRowCount();count++)
            {
                String tenMon = (String)model.getValueAt(count, 0);
                Mon mon = new DALMon().getMonByTen(tenMon);
                tongTien += (float)model.getValueAt(count, 3);
                monList.add(mon);
                soLuongList[count] = Integer.parseInt(model.getValueAt(count, 1)+"") ;
            }


            if(ban.getTrangThai() == true)
            {
                ban.setTrangThai(false);
                new DALBan().updateTrangThai(ban.getIdBan(), ban.getTrangThai());
                int rdIdHoaDon = rd.nextInt((999-111)+1) + 111;

                HoaDon hoaDon = new HoaDon("" + rdIdHoaDon, idNv, ban.getIdBan(), tongTien);
                while(hoaDon.checkIdHoaDon())
                {
                    rdIdHoaDon = rd.nextInt((999-111)+1) + 111;
                    hoaDon.setIdHoaDon(rdIdHoaDon+"");
                }
                new DALHoaDon().addHoaDon(hoaDon);
                for(int i=0;i<count;i++)
                {
                    new DALChiTietHoaDon().addChiTietHoaDon(new ChiTietHoaDon(monList.get(i).getIdMon(),
                            hoaDon.getIdHoaDon(),soLuongList[i],monList.get(i).getGia(),soLuongList[i] * monList.get(i).getGia(),false));
                }

            }
            else
            {
                String idHoaDon = new BUS.BUSHoaDon().queryHoaDonByBan(ban.getIdBan()).getIdHoaDon();
                for (int i = 0; i < count; i++) {
                    Mon mon = new DALMon().getMonByTen((String)model.getValueAt(i, 0));
                    if(new DALChiTietHoaDon().checkMon(mon.getIdMon(), idHoaDon, true))
                    {
                        if(new DALChiTietHoaDon().themSoLuongMon(mon.getIdMon(), idHoaDon,(int) model.getValueAt(i, 1), (float) model.getValueAt(i, 2),true))
                        {
                            if(new DALChiTietHoaDon().upadateTongGia(mon.getIdMon(), idHoaDon))
                            {
                                JOptionPane.showMessageDialog(rootPane, "Thêm Món Thành Công!");
                            }
                        }
                    }
                    else
                    {
                        new DALChiTietHoaDon().addChiTietHoaDon(new ChiTietHoaDon(monList.get(i).getIdMon(),idHoaDon,soLuongList[i],monList.get(i).getGia(),soLuongList[i] * monList.get(i).getGia(),true));
                    }
                }
            }
            dsBan.refresh();
        }
        
        
        this.dispose();
    }//GEN-LAST:event_btnTaoBillActionPerformed

    private void cbDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDanhMucActionPerformed
        // TODO add your handling code here:
        panelDanhSachMon.removeAll();
        panelDanhSachMon.setVisible(false);
        ArrayList<Mon> listMon ;
        if(cbDanhMuc.getSelectedIndex() == 0)
        {
            listMon = new BUS.BUSMon().queryMonByStatus(true);
        }
        else
        {
            
            listMon = new DALDanhMuc().queryDanhMuc((String)cbDanhMuc.getSelectedItem(),true);
            
        }
        getMonList(listMon);
        panelDanhSachMon.setVisible(true);
    }//GEN-LAST:event_cbDanhMucActionPerformed
    
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
//            java.util.logging.Logger.getLogger(GUIOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GUIOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GUIOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GUIOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
////                new Order("NV1",new Ban(1,true),new DanhSachBan()).setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTaoBill;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cbDanhMuc;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelDanhSachMon;
    private javax.swing.JPanel panelTable;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tableThem;
    // End of variables declaration//GEN-END:variables
}
