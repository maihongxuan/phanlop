/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Text;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.*;
import static java.awt.SystemColor.text;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import DTO.*;
import DAL.*;
import BLL.*;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Itext {

    public static final String FSB="C:\\Windows\\Fonts\\Arial.ttf";
    public String report(hoadon_DTO hd) throws IOException
    {
        String manv=hd.getManv();
        String mahd=hd.getMahd();
        String mabn=hd.getMabn();
        String ngay=hd.getNgay();
        String gio=hd.getGio();
        chitiethoadon_BLL cthdBLL=new chitiethoadon_BLL();
        
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);

        try {

            // Tạo đối tượng PdfWriter
            JFileChooser chooser = new JFileChooser();
            int i = chooser.showSaveDialog(chooser);
            File file = chooser.getSelectedFile();
            FileOutputStream out = new FileOutputStream(file);
            PdfWriter.getInstance(document, out);

            // Mở file để thực hiện ghi
            document.open();
            Font f14 = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\cour.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 14,
					Font.NORMAL);
            Font f = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\cour.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18,
					Font.NORMAL);
            Font f10 = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\cour.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10,
					Font.NORMAL);
            f.setColor(BaseColor.BLUE);
            //PdfFont FSB1=PdfFontFactory.createFont(FSB,true);
            // Thêm nội dung sử dụng add function
            
//            Chunk chunk=new Chunk(new VerticalPositionMark());
            Paragraph par=new Paragraph("HÓA ĐƠN",f);
            par.setAlignment(Element.ALIGN_CENTER);
            par.setFont(f);
            document.add(par);
            document.add(Chunk.NEWLINE);
            Paragraph mahdpdf=new Paragraph("Mã Hóa đơn: "+mahd,f14);
            mahdpdf.setAlignment(Element.ALIGN_CENTER);
            document.add(mahdpdf);
            document.add(Chunk.NEWLINE);
            Paragraph  giopdf=new Paragraph(    "Giờ :"+ gio+",Ngày : "+ngay,f14);
            document.add(giopdf);
            document.add(Chunk.NEWLINE);
            Paragraph  Manvpdf=new Paragraph(   "Mã nhân viên :    "+ manv,f14);
            document.add(Manvpdf);
            document.add(Chunk.NEWLINE);
            benhnhan_BLL bnBLL=new benhnhan_BLL();
            Paragraph  mabnpdf=new Paragraph(   "Tên Bệnh Nhân :    "+ bnBLL.tenbn(mabn) ,f14);
            document.add(mabnpdf);
            document.add(Chunk.NEWLINE);
            dichvukhambenh_BLL dichvu=new dichvukhambenh_BLL();
           PdfPTable table=new PdfPTable(5);
           int []a={25,50,25,10,25};
            table.setWidths(a);
            table.setSpacingAfter(5);
            PdfPCell c1 = new PdfPCell(new Phrase("Mã Thuốc",f10));
            table.addCell(c1);
           
            PdfPCell c2 = new PdfPCell(new Phrase("Tên Thuốc",f10));
            table.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("Giá",f10));
            table.addCell(c3);
            PdfPCell c4=new PdfPCell(new Phrase("SL",f10));
            table.addCell(c4);
            PdfPCell c5=new PdfPCell(new Phrase("Thành tiền ",f10));
            table.addCell(c5);
            ArrayList<chitiethoadon_DTO> arr=new ArrayList<chitiethoadon_DTO>();
            arr=cthdBLL.theoma(mahd);
            DefaultTableModel cthd=new DefaultTableModel();
            thuoc_BLL thuoc=new thuoc_BLL();
            double tongtien=0;
            for(int r=0 ; r<arr.size();r++)
            {
                chitiethoadon_DTO cthd_DTO=arr.get(r);
                String mathuoc=cthd_DTO.getMathuoc();
                PdfPCell mt = new PdfPCell(new Phrase(mathuoc,f10));//
                table.addCell(mt);
                String tenthuoc=thuoc.getten(mathuoc);
                PdfPCell ten = new PdfPCell(new Phrase(tenthuoc,f10));//
                table.addCell(ten);
                double gia=thuoc.getgia(tenthuoc);
                PdfPCell giathuoc = new PdfPCell(new Phrase(String.valueOf(gia),f10));//
                table.addCell(giathuoc);
                int sl1=cthd_DTO.getSl();
                PdfPCell sl = new PdfPCell(new Phrase(String.valueOf(sl1),f10));//
                table.addCell(sl);
                double tt=gia*sl1;
                tongtien+=tt;
                PdfPCell thanhtien = new PdfPCell(new Phrase(String.valueOf(tt),f10));//
                table.addCell(thanhtien);
            }
            PdfPCell tendichvu = new PdfPCell(new Phrase(dichvu.tongdv(mabn),f10));//
                table.addCell(tendichvu);
            PdfPCell tiendichvu = new PdfPCell(new Phrase(String.valueOf(dichvu.tonggiadv(mabn)),f10));//
                table.addCell(tiendichvu);
            document.add(table);
            // Đóng File
            document.add(Chunk.NEWLINE);
//            Paragraph dichvukhambenh=new Paragraph("Dịch vụ khám bệnh : "+dichvu.tongdv(mabn),f14);
//            document.add(dichvukhambenh);
//            document.add(Chunk.NEWLINE);
//            Paragraph tienkham=new Paragraph("Tiền dịch vụ : "+dichvu.tonggiadv(mabn),f14);
//            tongtien+=dichvu.tonggiadv(mabn);
//            document.add(tienkham);
//            document.add(Chunk.NEWLINE);
            Paragraph tongcong=new Paragraph("Tổng cộng : "+tongtien,f14);
            document.add(tongcong);
            document.close();
            return "report thành công!";
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
            return "report không thành công";
        }
    }
}
