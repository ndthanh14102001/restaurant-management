package DTO;


import DAL.DALHoaDon;
import DTO.HoaDon;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExportExcel {

    public boolean xuatExcel(ArrayList<HoaDon> listHoaDon)
    {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Hóa Đơn");

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Danh Sách Hóa Đơn");

            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("ID");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Nhân Viên Thanh Toán");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Bàn");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ngày");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Giờ");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Tổng Tiền");
            float s = 0;
            for (int i = 0; i < listHoaDon.size(); i++) {
                HoaDon hoadon = listHoaDon.get(i);
                s+= hoadon.getTongtien();
                row = spreadsheet.createRow((short) 4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(hoadon.getIdHoaDon());
                row.createCell(2).setCellValue(hoadon.getIdNVThanhToan());
                row.createCell(3).setCellValue(hoadon.getIdBan());
                row.createCell(4).setCellValue(hoadon.getNgayThanhToan());
                row.createCell(5).setCellValue(hoadon.getGioThanhToan());
                row.createCell(6).setCellValue(hoadon.getTongtien());
            }
            
            row = spreadsheet.createRow((short) 4 + listHoaDon.size());
            row.setHeight((short) 400);
            row.createCell(5).setCellValue("Tổng");
            row.createCell(6).setCellValue(s);
            
            FileOutputStream out = new FileOutputStream(new File("C:\\Users\\DucThanh\\OneDrive\\Desktop\\/hd.xlsx"));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}