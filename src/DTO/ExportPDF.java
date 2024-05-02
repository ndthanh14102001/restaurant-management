package DTO;

import DAL.DALChiTietHoaDon;
import DAL.DALHoaDon;
import DTO.ChiTietHoaDon;
import DTO.HoaDon;
import DTO.Mon;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportPDF {

    private static String FILE = "C:\\Users\\DucThanh\\OneDrive\\Desktop\\/HoaDonPdf.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    
    Font fontData;
    Font fontTitle;
    Font fontHeader;
    public ExportPDF() {
        try {
            fontData = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 11, Font.NORMAL);
            fontTitle = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 25, Font.BOLD);
            fontHeader = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 13, Font.BOLD);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(ExportPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public boolean xuatPDFHoaDon(ArrayList<ChiTietHoaDon> listChiTietHoaDon)
    {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
      
            new ExportPDF().xuatTitle(new BUS.BUSHoaDon().queryHoaDon(listChiTietHoaDon.get(0).getIdHoaDon()),FILE, document);
            new BUS.BUSHoaDon().updateTongTien(listChiTietHoaDon.get(0).getIdHoaDon());
            new ExportPDF().xuatBillPDF(listChiTietHoaDon, FILE, document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");

    }

    

    
    private void xuatTitle(HoaDon hoadon,String link,Document document) throws DocumentException
    {
        Paragraph title = new Paragraph("HÓA ĐƠN THANH TOÁN",fontTitle);
        title.setAlignment(Element.ALIGN_CENTER);
        
        document.add(title);
        PdfPTable table = new PdfPTable(3);
        
        PdfPCell cell1 = new PdfPCell(new Phrase("Mã Hóa Đơn: " + hoadon.getIdHoaDon(),fontData));
        
        cell1.setBorder(0);
        cell1.setPaddingTop(50);
        cell1.setPaddingRight(10);
        cell1.setPaddingBottom(20);
        
        
        PdfPCell cell2 = new PdfPCell(new Phrase("Nhân Viên: " + hoadon.getIdNVThanhToan(),fontData));
        cell2.setBorder(0);
        cell2.setPaddingTop(50);
        cell2.setPaddingRight(10);
        cell2.setPaddingBottom(20);
        
        
        PdfPCell cell3 = new PdfPCell(new Phrase("Bàn: " + hoadon.getIdBan(),fontData));
        cell3.setBorder(0);
        cell3.setPaddingTop(50);
        cell3.setPaddingRight(10);
        cell3.setPaddingBottom(20);
        
        
        PdfPCell cell4 = new PdfPCell(new Phrase("Ngày: " + hoadon.getNgayThanhToan(),fontData));
        cell4.setBorder(0);
        cell4.setPaddingRight(10);
        cell4.setPaddingBottom(20);
        
        
        PdfPCell cell5 = new PdfPCell(new Phrase("Giờ: " + hoadon.getGioThanhToan(),fontData));
        cell5.setBorder(0);
        cell5.setPaddingRight(10);
        cell5.setPaddingBottom(20);
        
        
        PdfPCell cell6 = new PdfPCell(new Phrase(""));
        cell6.setBorder(0);
        cell6.setPaddingRight(10);
        cell6.setPaddingBottom(20);
        
        
        
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);
        document.add(table);
    }
    private void xuatBillPDF(ArrayList<ChiTietHoaDon> list, String link, Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{30,20,25,25});
        PdfPCell c1 = new PdfPCell(new Phrase("Tên Món", fontHeader));
        
        c1.setPaddingBottom(10);
        table.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase("Số Lượng", fontHeader));
        c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c2.setPaddingBottom(10);
        table.addCell(c2);

        PdfPCell c3 = new PdfPCell(new Phrase("Đơn Giá", fontHeader));
        c3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c3.setPaddingBottom(10);
        table.addCell(c3);

        PdfPCell c4 = new PdfPCell(new Phrase("Tổng Giá", fontHeader));
        c4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(c4);
        float s = 0;
        for (int i = 0; i < list.size(); i++) {
            if(!list.get(i).isMonThem())
            {
                Mon mon = new DAL.DALMon().getMonById(list.get(i).getIdMon());

                PdfPCell cell1 = new PdfPCell(new Phrase(mon.getTenMon(),fontData));
                cell1.setPaddingRight(10);

                String soLuong = list.get(i).getSoluong() + "";
                PdfPCell cell2 = new PdfPCell(new Phrase(soLuong,fontData));
                cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell2.setPaddingRight(10);

                String gia = mon.getGia() + "";
                PdfPCell cell3 = new PdfPCell(new Phrase(gia));
                cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell3.setPaddingRight(10);

                String tonggia = list.get(i).getTongGia() + "";
                PdfPCell cell4 = new PdfPCell(new Phrase(tonggia));
                cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell4.setPaddingRight(10);

                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);

                s += list.get(i).getTongGia();
            }
        }
        
        
        
        if(new DALChiTietHoaDon().countMonThem(list.get(0).getIdHoaDon())>0)
        {
            PdfPCell cellMonThem = new PdfPCell(new Phrase("Món Gọi Thêm: ", fontHeader));
            cellMonThem.setBorder(0);
            cellMonThem.setPaddingTop(50);
            cellMonThem.setPaddingBottom(20);
            table.addCell(cellMonThem);

            PdfPCell cellnull1 = new PdfPCell(new Phrase("", subFont));
            cellnull1.setBorder(0);
            table.addCell(cellnull1);

            PdfPCell cellnull2 = new PdfPCell(new Phrase(""));
            cellnull2.setBorder(0);
            table.addCell(cellnull2);

            PdfPCell cellnull3 = new PdfPCell(new Phrase(""));
            cellnull3.setBorder(0);
            table.addCell(cellnull3);

            PdfPCell c5 = new PdfPCell(new Phrase("Tên Món", fontHeader));
            c5.setPaddingBottom(10);
            table.addCell(c5);

            PdfPCell c6 = new PdfPCell(new Phrase("Số Lượng", fontHeader));
            c6.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c6.setPaddingBottom(10);
            table.addCell(c6);

            PdfPCell c7 = new PdfPCell(new Phrase("Đơn Giá", fontHeader));
            c7.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c7.setPaddingBottom(10);
            table.addCell(c7);

            PdfPCell c8 = new PdfPCell(new Phrase("Tổng Giá", fontHeader));
            c8.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(c8);
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).isMonThem())
                {
                    Mon mon = new DAL.DALMon().getMonById(list.get(i).getIdMon());

                    PdfPCell cell1 = new PdfPCell(new Phrase(mon.getTenMon(),fontData));

                    cell1.setPaddingRight(10);

                    String soLuong = list.get(i).getSoluong() + "";
                    PdfPCell cell2 = new PdfPCell(new Phrase(soLuong));
                    cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell2.setPaddingRight(10);

                    String gia = mon.getGia() + "";
                    PdfPCell cell3 = new PdfPCell(new Phrase(gia));
                    cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell3.setPaddingRight(10);

                    String tonggia = list.get(i).getTongGia() + "";
                    PdfPCell cell4 = new PdfPCell(new Phrase(tonggia));
                    cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell4.setPaddingRight(10);

                    table.addCell(cell1);
                    table.addCell(cell2);
                    table.addCell(cell3);
                    table.addCell(cell4);

                    s += list.get(i).getTongGia();
                }
            }
        
        }
        
        
        PdfPCell tongtien = new PdfPCell(new Phrase("Thành Tiền:", fontHeader));
        tongtien.setBorder(0);
        tongtien.setHorizontalAlignment(Element.ALIGN_LEFT);
        tongtien.setPaddingTop(20);
        table.addCell(tongtien);
        
        PdfPCell cellempty = new PdfPCell(new Phrase("", subFont));
        cellempty.setBorder(0);
        table.addCell(cellempty);

        PdfPCell cellempty1 = new PdfPCell(new Phrase(""));
        cellempty1.setBorder(0);
        table.addCell(cellempty1);

        

        PdfPCell tien = new PdfPCell(new Phrase( s + "" ));
        tien.setBorder(0);
        tien.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tien.setPaddingTop(20);
        table.addCell(tien);
        document.add(table);
    }

    
}
