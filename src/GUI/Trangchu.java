/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Date;
import DTO.*;
import DAL.*;
import BLL.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author Hong Xuan
 */
public class Trangchu extends javax.swing.JFrame {

    benhnhan_DAL a = new benhnhan_DAL();
    DefaultTableModel benhnhan = new DefaultTableModel();
    DefaultTableModel nhanvien = new DefaultTableModel();
    DefaultTableModel datlich = new DefaultTableModel();
    DefaultTableModel taikhoan = new DefaultTableModel();
    DefaultTableModel hoadon = new DefaultTableModel();
    DefaultTableModel thuoc = new DefaultTableModel();
    DefaultTableModel phieunk = new DefaultTableModel();
    DefaultTableModel thongke = new DefaultTableModel();

    /**
     * Creates new form Trangchu
     */
    public Trangchu(String manv) {//khi mà khởi tạo thì nó sẽ bật hàm kiểm tra lên. làm sao để kiểm tra đc
        
        initComponents();
        setVisible(true);
        setSize(1500, 750);
        setResizable(false);
        this.settext(manv);
        this.kiemtra();
        this.showtablebenhnhan();
        this.showtablenhanvien();
        this.showtabledatlich();
        this.showtabletaikhoan();
        this.showtablehoadon();
        this.showtablethuoc();
        this.showtablephieunk();
        this.seteditdatlich(false);
        tfdatlichketquabn.setEditable(false);
        tfdatlichketquabn.setText("chưa có");
        tfdatlichchucvunv.setEditable(false);
        tfdatlichtennv.setEditable(false);
    }
    public void settext(String manv)
    {
        tenuser.setText(manv.trim());
    }
    public void kiemtra() {
            if (tenuser.getText().trim().equals("user")) {
            this.setVisible(false);
            JOptionPane.showConfirmDialog(this, "Vui lòng Đăng nhập", "Thông báo", JOptionPane.CLOSED_OPTION);
            login a = new login();
            a.setVisible(true);
        }
        

    }
    public void phanquyen()
    {
        if (tenuser.getText().trim().equalsIgnoreCase("NV_0002") == false) {
            //Trangchu a=new Trangchu();
            
            paneltrangchu.remove(1);

            paneltrangchu.remove(2);
            //a.setVisible(true);
            this.setVisible(true);

        } else {
                this.setVisible(true);
        }
    }
    public void showtablebenhnhan() {
        benhnhan.setRowCount(0);
        benhnhan.setColumnCount(0);
        benhnhan_BLL bnBLL = new benhnhan_BLL();

        benhnhan.addColumn("Mã Bệnh Nhân");
        benhnhan.addColumn("Tên Bệnh Nhân");
        benhnhan.addColumn("Năm sinh");
        benhnhan.addColumn("Giới tính");
        benhnhan.addColumn("Địa Chỉ");
        benhnhan.addColumn("Triệu chứng");
        benhnhan.addColumn("Tiền sử");
        benhnhan.addColumn("Kết quả");
        tablebenhnhan.setModel(benhnhan);

        ArrayList<benhnhan_DTO> arr = new ArrayList<benhnhan_DTO>();
        arr = bnBLL.getall();
        for (int i = 0; i < arr.size(); i++) {
            benhnhan_DTO bnDTO = arr.get(i);
            String mabn = bnDTO.getMabn();
            String tenbn = bnDTO.getTenbn();
            String namsinh = bnDTO.getNamsinh();
            String gioitinh = bnDTO.getGioitinh();
            String diachi = bnDTO.getDiachi();
            String trieuchung = bnDTO.getTrieuchung();
            String tiensu = bnDTO.getTiensu();
            String kq = bnDTO.getKetqua();
            Object[] row = {mabn, tenbn, namsinh, gioitinh, diachi, trieuchung, tiensu, kq};
            benhnhan.addRow(row);
        }

        tablebenhnhan.setModel(benhnhan);
        tablebenhnhan.setRowHeight(25);
        tablebenhnhan.getTableHeader().setFont(new Font("Courier New",Font.PLAIN,16));
        tablebenhnhan.getTableHeader().setBackground(new Color(17,185,255));
    }

    public void showtablenhanvien() {
        nhanvien.setRowCount(0);
        nhanvien.setColumnCount(0);
        nhanvien_BLL nvBLL = new nhanvien_BLL();

        nhanvien.addColumn("Mã NV");
        nhanvien.addColumn("Tên NV");
        nhanvien.addColumn("Năm sinh");
        nhanvien.addColumn("Chức vụ");
        nhanvien.addColumn("Giới Tính");
        nhanvien.addColumn("Gmail");
        nhanvien.addColumn("SDT");
        nhanvien.addColumn("Diachi");
        tablenhanvien.setModel(benhnhan);

        ArrayList<nhanvien_DTO> arr = new ArrayList<nhanvien_DTO>();
        arr = nvBLL.getall();
        for (int i = 0; i < arr.size(); i++) {
            nhanvien_DTO nvDTO = arr.get(i);
            String manv = nvDTO.getManv();
            String tennv = nvDTO.getTennv();
            String namsinh = nvDTO.getNgaysinh();
            String chucvu = nvDTO.getChucvu();
            String gioitinh = nvDTO.getGioitinh();
            String gmail = nvDTO.getGmail();
            String sdt = nvDTO.getSdt();
            String diachi = nvDTO.getDiachi();
            Object[] row = {manv, tennv, namsinh, chucvu, gioitinh, gmail, sdt, diachi};
            nhanvien.addRow(row);
        }
        tablenhanvien.setModel(nhanvien);
        tablenhanvien.getTableHeader().setFont(new Font("Courier New",Font.PLAIN,16));
        tablenhanvien.getTableHeader().setBackground(new Color(17,185,255));
    }

    public void showtabledatlich() {

        datlich.setRowCount(0);
        datlich.setColumnCount(0);
        //tạo tên cột
        datlich.addColumn("Mã bệnh nhân");
        datlich.addColumn("Mã nhân viên");
        datlich.addColumn("Ngày Khám");
        datlich.addColumn("Giờ khám");
        tabledatlich.setModel(datlich);

        datlich_BLL dlBLL = new datlich_BLL();
        ArrayList<datlich_DTO> arr = new ArrayList<datlich_DTO>();
        arr = dlBLL.getall();
        for (int i = 0; i < arr.size(); i++) {
            datlich_DTO dlDTO = arr.get(i);
            String mabn = dlDTO.getMabn();
            String manv = dlDTO.getManv();
            String ngay = dlDTO.getNgaydatlich();
            String gio = dlDTO.getGiodatlich();
            Object[] row = {mabn, manv, ngay, gio};
            datlich.addRow(row);
        }
        tabledatlich.setModel(datlich);
        this.seteditdatlich(false);
    }

    public void showtabletaikhoan() {
        taikhoan.setRowCount(0);
        taikhoan.setColumnCount(0);
        taikhoan.addColumn("Mã nhân viên");
        taikhoan.addColumn("Mật Khẩu");
        taikhoan.addColumn("Trạng thái");
        tabletaikhoan.setModel(taikhoan);
        taikhoan_BLL tkBLL = new taikhoan_BLL();
        ArrayList<taikhoan_DTO> arr = new ArrayList<taikhoan_DTO>();
        arr = tkBLL.getall();
        for (int i = 0; i < arr.size(); i++) {
            taikhoan_DTO tkDTO = arr.get(i);
            String manv = tkDTO.getManv();
            String pass = tkDTO.getPass();
            int tt=tkDTO.getTrangthai();
            String trangthai="";
            if(tt==0)
            {
                trangthai="bị khóa";
            }
            else
            {
                trangthai="Đang hoạt động";
            }
            Object[] row = {manv, pass,trangthai};
            taikhoan.addRow(row);
        }
        tabletaikhoan.setModel(taikhoan);
    }

    public void showtablehoadon() {
        hoadon.setColumnCount(0);
        hoadon.setRowCount(0);
        hoadon.addColumn("Mã hóa đơn");
        hoadon.addColumn("Mã bệnh nhân");
        hoadon.addColumn("Mã nhân viên");
        hoadon.addColumn("Ngày");
        hoadon.addColumn("Giờ");
        tablehoadon.setModel(hoadon);
        hoadon_BLL hdBLL = new hoadon_BLL();
        ArrayList<hoadon_DTO> arr = new ArrayList<hoadon_DTO>();
        arr = hdBLL.getall();
        for (int i = 0; i < arr.size(); i++) {
            hoadon_DTO hd = arr.get(i);
            String mahd = hd.getMahd();
            String mabn = hd.getMabn();
            String manv = hd.getManv();
            String ngay = hd.getNgay();
            String gio = hd.getGio();
            Object[] obj = {mahd, mabn, manv, ngay, gio};
            hoadon.addRow(obj);
        }
        tablehoadon.setModel(hoadon);
    }

    public void showtablethuoc() {
        thuoc.setColumnCount(0);
        thuoc.setRowCount(0);
        thuoc.addColumn("Mã thuốc");
        thuoc.addColumn("Tên thuốc");
        thuoc.addColumn("Giá");
        thuoc.addColumn("Số lượng");
        thuoc_BLL thuocBLL = new thuoc_BLL();
        ArrayList<thuoc_DTO> arr = new ArrayList<thuoc_DTO>();
        arr = thuocBLL.getall();
        for (int i = 0; i < arr.size(); i++) {
            thuoc_DTO thuocDTO = arr.get(i);
            String mathuoc = thuocDTO.getMathuoc();
            String tenthuoc = thuocDTO.getTenthuoc();
            double gia = thuocDTO.getGia();
            int sl = thuocDTO.getSl();
            Object[] obj = {mathuoc, tenthuoc, gia, sl};
            thuoc.addRow(obj);
        }
        tablekhothuoc.setModel(thuoc);
    }

    public void showtablephieunk() {
        phieunk.setColumnCount(0);
        phieunk.setRowCount(0);
        phieunk.addColumn("Mã Phiếu");
        phieunk.addColumn("Nhân viên quản lí");
        phieunk.addColumn("Ngày nhập kho");
        phieunhapkho_BLL pnkBLL = new phieunhapkho_BLL();
        ArrayList<phieunhapkho_DTO> arr = new ArrayList<phieunhapkho_DTO>();
        arr = pnkBLL.getall();
        for (int i = 0; i < arr.size(); i++) {
            phieunhapkho_DTO pnkDTO = arr.get(i);
            String mank = pnkDTO.getMank();
            String manv = pnkDTO.getManv();
            String ngay = pnkDTO.getNgaynk();
            Object[] obj = {mank, manv, ngay};
            phieunk.addRow(obj);
        }
        tablephieunk.setModel(phieunk);
    }

    public void export(JTable jtable) throws IOException {

        try {

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Đồ án java");
            XSSFRow rowcol = sheet.createRow(0);
            
            for (int i = 0; i < jtable.getColumnCount(); i++) {
                XSSFCell cell = rowcol.createCell(i);
                cell.setCellValue(jtable.getColumnName(i));
            }
            for (int j = 0; j < jtable.getRowCount(); j++) {
                XSSFRow row = sheet.createRow(j + 1);
                for (int k = 0; k < jtable.getColumnCount(); k++) {
                    XSSFCell cell = row.createCell(k);
                    if (jtable.getValueAt(j, k) != null) {
                        cell.setCellValue(jtable.getValueAt(j, k).toString());
                    }
                }
            }
            JFileChooser chooser = new JFileChooser();
            int i = chooser.showSaveDialog(chooser);
            File file = chooser.getSelectedFile();
            FileOutputStream out = new FileOutputStream(file + ".xlsx");
            wb.write(out);
            out.close();

        } catch (FileNotFoundException | NullPointerException ex) {
            System.out.println(ex);
            JOptionPane.showConfirmDialog(this, ex, "Lỗi", JOptionPane.CLOSED_OPTION);
        } catch (IOException io) {
            System.out.println(io);
            JOptionPane.showConfirmDialog(this, io, "Lỗi", JOptionPane.CLOSED_OPTION);
        }
    }

    public void thongkethoigian() {

        if (datebatdau.getDate().after(dateketthuc.getDate())) {
            JOptionPane.showConfirmDialog(this, "Ngày bắt đầu sau ngày kết thúc.Vui lòng chọn lại", "Thông báo", JOptionPane.CLOSED_OPTION);
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngaybd = sdf.format(datebatdau.getDate());
        String ngaykt = sdf.format(dateketthuc.getDate());
        thongke.setColumnCount(0);
        thongke.setRowCount(0);
        thongke.addColumn("Mã Hóa đơn");
        thongke.addColumn("Ngày");
        thongke.addColumn(" Tên thuốc");
        thongke.addColumn("Giá");
        thongke.addColumn("SL");
        thongke.addColumn("thành tiền");
        thongke.addColumn("dịch vụ");
        thongke.addColumn("tiền dịch vụ");
        chitiethoadon_BLL cthdBLL = new chitiethoadon_BLL();
        ArrayList<chitiethoadon_DTO> arr = new ArrayList<chitiethoadon_DTO>();
        arr = cthdBLL.thongketheohd(ngaybd, ngaykt);
        thuoc_BLL thuoc = new thuoc_BLL();
        double tong = 0;
        int dem=0;
        String tmp="";
        for (int i = 0; i < arr.size(); i++) {
            chitiethoadon_DTO cthd = arr.get(i);
            String mahd = cthd.getMahd();
            String ngay = cthdBLL.getNgay(mahd);
            String tenthuoc = cthdBLL.gettenthuoc(cthd.getMathuoc());
            double gia = thuoc.getgia(tenthuoc);
            int sl = cthd.getSl();
            double thanhtien = cthd.getTt();
            
            tong += thanhtien;
            dichvukhambenh_BLL dichvu=new dichvukhambenh_BLL();//làm sao để cộng 1 lần??
            String tendv=dichvu.tongdvtheomahd(mahd);
            double tiendv=dichvu.tonggiadvtheoma(mahd);
            if(tmp.equals(mahd)==false)
            {
                tong+=tiendv;
                
                tmp=mahd;
            }
            else
            {
                tendv="";
                tiendv=0;
            }
            Object[] row = {mahd, ngay, tenthuoc, gia, sl, thanhtien,tendv,tiendv};
            thongke.addRow(row);
        }
        tablethongke.setModel(thongke);
        tftktongcong.setText("" + tong);
    }

    public boolean hasnv(String manv) {
        nhanvien_BLL nvBLL = new nhanvien_BLL();
        if (nvBLL.hasnv(manv)) {
            return true;
        } else {
            return false;
        }
    }

    public void thongkenv() {
        if (tftkmanv.getText().trim().equals("")) {
            JOptionPane.showConfirmDialog(this, "Vui Lòng nhập mã nhân viên", "Thông báo", JOptionPane.CLOSED_OPTION);
            return;
        }
        String manv = tftkmanv.getText().trim();
        if (this.hasnv(manv) == false) {
            JOptionPane.showConfirmDialog(this, "Mã nhân viên Sai.Vui lòng nhập lại!", "Thông báo", JOptionPane.CLOSED_OPTION);
            return;
        }
        if (datebatdau.getDate().after(dateketthuc.getDate())) {
            JOptionPane.showConfirmDialog(this, "Ngày bắt đầu sau ngày kết thúc.Vui lòng chọn lại", "Thông báo", JOptionPane.CLOSED_OPTION);
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngaybd = sdf.format(datebatdau.getDate());
        String ngaykt = sdf.format(dateketthuc.getDate());
        thongke.setColumnCount(0);
        thongke.setRowCount(0);
        thongke.addColumn("Mã Hóa đơn");
        thongke.addColumn("Ngày");
        thongke.addColumn(" Tên thuốc");
        thongke.addColumn("Giá");
        thongke.addColumn("SL");
        thongke.addColumn("thành tiền");
        nhanvien_BLL nvBLL = new nhanvien_BLL();

        ArrayList<chitiethoadon_DTO> arr = new ArrayList<chitiethoadon_DTO>();
        chitiethoadon_BLL cthdBLL = new chitiethoadon_BLL();
        arr = nvBLL.tktheomanv(manv, ngaybd, ngaykt);
        thuoc_BLL thuoc = new thuoc_BLL();
        double tong = 0;
        for (int i = 0; i < arr.size(); i++) {
            chitiethoadon_DTO cthd = arr.get(i);
            String mahd = cthd.getMahd();
            String ngay = cthdBLL.getNgay(mahd);
            String tenthuoc = cthdBLL.gettenthuoc(cthd.getMathuoc());
            double gia = thuoc.getgia(tenthuoc);
            int sl = cthd.getSl();
            double thanhtien = cthd.getTt();
            tong += thanhtien;
            Object[] row = {mahd, ngay, tenthuoc, gia, sl, thanhtien};
            thongke.addRow(row);
        }
        tablethongke.setModel(thongke);
        tftktongcong.setText("" + tong);
    }

    public boolean hasthuoc(String mathuoc) {
        thuoc_BLL thuoc = new thuoc_BLL();
        if (thuoc.hasthuoc(mathuoc.trim())) {
            return true;
        } else {
            return false;
        }
    }

    public void thongkemathuoc() {
        
        if (datebatdau.getDate().after(dateketthuc.getDate())) {
            JOptionPane.showConfirmDialog(this, "Ngày bắt đầu sau ngày kết thúc.Vui lòng chọn lại", "Thông báo", JOptionPane.CLOSED_OPTION);
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngaybd = sdf.format(datebatdau.getDate());
        String ngaykt = sdf.format(dateketthuc.getDate());
        thongke.setColumnCount(0);
        thongke.setRowCount(0);
        thongke.addColumn("Mã thuốc");
        thongke.addColumn("Tên thuốc");
        thongke.addColumn("SL");
        thongke.addColumn("Thành tiền");
        double tong=0;
        thuoc_BLL thuoc = new thuoc_BLL();
        ArrayList<thuoc_DTO> arr = new ArrayList<thuoc_DTO>();
        arr = thuoc.thongketheothuoc(ngaybd, ngaykt);
        for (int i = 0; i < arr.size(); i++) {
            thuoc_DTO thuocDTO = arr.get(i);
            String mathuoc = thuocDTO.getMathuoc();
            String tenthuoc = thuocDTO.getTenthuoc();
            int sl = thuocDTO.getSl();
            double thanhtien = sl * thuocDTO.getGia();
            tong+=thanhtien;
            Object [] row={mathuoc,tenthuoc,sl,thanhtien};
            thongke.addRow(row);
        }
        
        tablethongke.setModel(thongke);
        tftktongcong.setText("" + tong);
    }
//
//        if(i==JFileChooser.APPROVE_OPTION)
//        {
//            File file=chooser.getSelectedFile();
//            try{
//                FileWriter out=new FileWriter(file+".xls");
//                BufferedWriter bwrite=new BufferedWriter(out);
//                DefaultTableModel defaultTableModel=(DefaultTableModel) jtable.getModel();
//                for(int j=0;j<jtable.getColumnCount();j++)
//                {
//                    bwrite.write(defaultTableModel.getColumnName(j)+"\t");
//                }
//                for(int j=0;j<jtable.getRowCount();j++)
//                {
//                    for(int k=0;k<jtable.getColumnCount();k++)
//                    {
//                        bwrite.write(defaultTableModel.getValueAt(j, k)+"\t");
//                    }
//                    bwrite.write("\n");
//                }
//                bwrite.close();
//                JOptionPane.showConfirmDialog(this, "Lưu thành công","lưu thành công",JOptionPane.CLOSE_OPTION);
//            }
//            catch(Exception ex)
//            {
//                System.out.println(ex);
//                JOptionPane.showConfirmDialog(this, "Lưu thành công","lưu thành công",JOptionPane.CLOSE_OPTION);
//            }
//            
//        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        btnns = new javax.swing.JLabel();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        paneltrangchu = new javax.swing.JTabbedPane();
        panelbenhnhan = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tfmabn = new javax.swing.JTextField();
        tftenbn = new javax.swing.JTextField();
        tfdiachibn = new javax.swing.JTextField();
        tftrieuchungbn = new javax.swing.JTextField();
        tftiensubn = new javax.swing.JTextField();
        tfketqua = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablebenhnhan = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int column)
            {
                return false;
            }
        };
        btntimkiembn = new javax.swing.JButton();
        tftimkiembn = new javax.swing.JTextField();
        btnthembn = new javax.swing.JButton();
        btnsuabn = new javax.swing.JButton();
        btnxoabn = new javax.swing.JButton();
        btnthoat = new javax.swing.JButton();
        btnxuat = new javax.swing.JButton();
        radiobnnam = new javax.swing.JRadioButton();
        radiobnnu = new javax.swing.JRadioButton();
        reload = new javax.swing.JButton();
        resetbn = new javax.swing.JButton();
        btnimportbn = new javax.swing.JButton();
        tfnamsinhbn = new com.toedter.calendar.JDateChooser();
        panelnhanvien = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablenhanvien = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int column)
            {
                return false;
            }
        };
        btnthemnv = new javax.swing.JButton();
        btnsuanv = new javax.swing.JButton();
        btnxoanv = new javax.swing.JButton();
        btntimkiemnv = new javax.swing.JButton();
        tftimkiemnv = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfmanv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tftennv = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfchucvunv = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfgmailnv = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        radionamnv = new javax.swing.JRadioButton();
        radionunv = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        tfsdtnv = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tfdiachinv = new javax.swing.JTextField();
        btnthoanv = new javax.swing.JButton();
        btntailainv = new javax.swing.JButton();
        btnresetnv = new javax.swing.JButton();
        btnexportnv = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        paneltaikhoan = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        tftaikhoanmanv = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabletaikhoan = new javax.swing.JTable(){
            @Override
            public boolean isCellEditable(int row,int column)
            {
                return false;
            }
        };
        btntaikhoanthem = new javax.swing.JButton();
        btntaikhoansua = new javax.swing.JButton();
        btntaikhoandoitt = new javax.swing.JButton();
        btntaikhoantimkiem = new javax.swing.JButton();
        tftaikhoantimkiem = new javax.swing.JTextField();
        btntaikhoanthoat = new javax.swing.JButton();
        btntkxuatfile = new javax.swing.JButton();
        btntktailai = new javax.swing.JButton();
        btntkreset = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        tftaikhoanmk = new javax.swing.JPasswordField();
        tftaikhoanmkagain = new javax.swing.JPasswordField();
        panelhoadon = new javax.swing.JPanel();
        btnhoadonthoat = new javax.swing.JButton();
        btnthemhoadon = new javax.swing.JButton();
        tfhoadontimkiem = new javax.swing.JTextField();
        btnhoadontimkiem = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablehoadon = new javax.swing.JTable(){
            @Override
            public boolean isCellEditable(int row,int columns)
            {
                return false;
            }
        };
        btnxuatfile = new javax.swing.JButton();
        btnxemchitiet = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        btnhoadonreport = new javax.swing.JButton();
        btnhoadonreload = new javax.swing.JButton();
        panelkhothuoc = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablekhothuoc = new javax.swing.JTable(){
            @Override
            public boolean isCellEditable(int row,int columns)
            {
                return false;
            }
        };
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        tftimkiemthuoc = new javax.swing.JButton();
        tfkhothuoctimkiem = new javax.swing.JTextField();
        btnkhothuocreload = new javax.swing.JButton();
        panelthongke = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel38 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablethongke = new javax.swing.JTable(){
            @Override
            public boolean isCellEditable(int row,int columns)
            {
                return false;
            }
        };
        btnthongke = new javax.swing.JButton();
        radiothongkemanv = new javax.swing.JRadioButton();
        radiothongkethoigian = new javax.swing.JRadioButton();
        tftkmanv = new javax.swing.JTextField();
        btntkthoat = new javax.swing.JButton();
        btnthongkexuat = new javax.swing.JButton();
        radiotktheomathuoc = new javax.swing.JRadioButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        tftktongcong = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        panelphieunhap = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablephieunk = new javax.swing.JTable(){
            @Override
            public boolean isCellEditable(int row,int columns)
            {
                return false;
            }
        };
        btnphieunkxemct = new javax.swing.JButton();
        btntaophieunhap = new javax.swing.JButton();
        btnphieunktimkiem = new javax.swing.JButton();
        tfphieunktimkiem = new javax.swing.JTextField();
        btnphieunkxuat = new javax.swing.JButton();
        btnphieunkthoat = new javax.swing.JButton();
        btnphieunkreload = new javax.swing.JButton();
        paneldatlich = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        tfdatlichmabn = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        tfdatlichtenbn = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        tfdatlichdiachibn = new javax.swing.JTextField();
        tfdatlichtiensubn = new javax.swing.JTextField();
        tfdatlichtrieuchungbn = new javax.swing.JTextField();
        tfdatlichketquabn = new javax.swing.JTextField();
        radiodatlichnam = new javax.swing.JRadioButton();
        radiodatlichnu = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        tfdatlichmanv = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        tfdatlichtennv = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        tfdatlichchucvunv = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabledatlich = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int column)
            {
                return false;
            }
        };
        radiochuakham = new javax.swing.JRadioButton();
        radiodakham = new javax.swing.JRadioButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        btndatlich = new javax.swing.JButton();
        btnthoatdailich = new javax.swing.JButton();
        panelthoigiandatlich = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        tfdatlichgio = new javax.swing.JTextField();
        btndatlichsua = new javax.swing.JButton();
        btndatlichxoa = new javax.swing.JButton();
        btnxuatfiledatlich = new javax.swing.JButton();
        btndatlichtailai = new javax.swing.JButton();
        btndatlichreset = new javax.swing.JButton();
        btndatlichtimkiem = new javax.swing.JButton();
        tfdatlichtimkiem = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        tenuser = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        btnns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/anh-dep-background-y-tejpg.jpg"))); // NOI18N
        btnns.setText("jLabel23");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 153));
        setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        getContentPane().setLayout(null);

        paneltrangchu.setBackground(new java.awt.Color(255, 204, 204));
        paneltrangchu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        paneltrangchu.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        paneltrangchu.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                paneltrangchuStateChanged(evt);
            }
        });

        panelbenhnhan.setBackground(new java.awt.Color(255, 204, 204));

        jLabel11.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel11.setText("Mã bệnh nhân");

        jLabel12.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel12.setText("Tên bệnh nhân");

        jLabel13.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel13.setText("Năm sinh");

        jLabel14.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel14.setText("Giới tính");

        jLabel15.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel15.setText("Triệu chứng");

        jLabel16.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel16.setText("Tiền sử");

        jLabel17.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel17.setText("Địa chỉ");

        jLabel18.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel18.setText("kết quả chuẩn đoán");

        tfmabn.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        tfmabn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfmabnActionPerformed(evt);
            }
        });

        tftenbn.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        tfdiachibn.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        tftrieuchungbn.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        tftiensubn.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        tfketqua.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        tablebenhnhan.setFont(new java.awt.Font("Courier New", 0, 11)); // NOI18N
        tablebenhnhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablebenhnhan.setFocusTraversalPolicyProvider(true);
        tablebenhnhan.setFocusable(false);
        tablebenhnhan.setRowHeight(25);
        tablebenhnhan.setSelectionBackground(new java.awt.Color(255, 153, 153));
        tablebenhnhan.setShowGrid(true);
        tablebenhnhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablebenhnhanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablebenhnhan);

        btntimkiembn.setBackground(new java.awt.Color(204, 204, 255));
        btntimkiembn.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btntimkiembn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Find.png"))); // NOI18N
        btntimkiembn.setText("Tìm kiếm");
        btntimkiembn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiembnActionPerformed(evt);
            }
        });

        tftimkiembn.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        tftimkiembn.setText("Nhập mã bệnh nhân hoặc tên bệnh nhân cần tìm ...");
        tftimkiembn.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tftimkiembnCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tftimkiembnInputMethodTextChanged(evt);
            }
        });
        tftimkiembn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tftimkiembnActionPerformed(evt);
            }
        });
        tftimkiembn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tftimkiembnPropertyChange(evt);
            }
        });
        tftimkiembn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tftimkiembnKeyPressed(evt);
            }
        });

        btnthembn.setBackground(new java.awt.Color(204, 204, 255));
        btnthembn.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnthembn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add.png"))); // NOI18N
        btnthembn.setText("Thêm");
        btnthembn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthembnActionPerformed(evt);
            }
        });

        btnsuabn.setBackground(new java.awt.Color(204, 204, 255));
        btnsuabn.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnsuabn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Wrench.png"))); // NOI18N
        btnsuabn.setText("Sửa");
        btnsuabn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuabnActionPerformed(evt);
            }
        });

        btnxoabn.setBackground(new java.awt.Color(204, 204, 255));
        btnxoabn.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnxoabn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cut.png"))); // NOI18N
        btnxoabn.setText("Xóa");
        btnxoabn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoabnActionPerformed(evt);
            }
        });

        btnthoat.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnthoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Exit.png"))); // NOI18N
        btnthoat.setText("Thoát");
        btnthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthoatActionPerformed(evt);
            }
        });

        btnxuat.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/New document.png"))); // NOI18N
        btnxuat.setText("Xuất file excel");
        btnxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxuatActionPerformed(evt);
            }
        });

        buttonGroup4.add(radiobnnam);
        radiobnnam.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radiobnnam.setText("Nam");

        buttonGroup4.add(radiobnnu);
        radiobnnu.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radiobnnu.setText("Nữ");

        reload.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        reload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Raise.png"))); // NOI18N
        reload.setText("Tải lại");
        reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadActionPerformed(evt);
            }
        });

        resetbn.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        resetbn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Application.png"))); // NOI18N
        resetbn.setText("Reset");
        resetbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetbnActionPerformed(evt);
            }
        });

        btnimportbn.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnimportbn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Database.png"))); // NOI18N
        btnimportbn.setText("Import dữ liệu");
        btnimportbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimportbnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelbenhnhanLayout = new javax.swing.GroupLayout(panelbenhnhan);
        panelbenhnhan.setLayout(panelbenhnhanLayout);
        panelbenhnhanLayout.setHorizontalGroup(
            panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelbenhnhanLayout.createSequentialGroup()
                .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelbenhnhanLayout.createSequentialGroup()
                        .addGap(550, 550, 550)
                        .addComponent(btntimkiembn, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tftimkiembn, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelbenhnhanLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelbenhnhanLayout.createSequentialGroup()
                                .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelbenhnhanLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelbenhnhanLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelbenhnhanLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelbenhnhanLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelbenhnhanLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelbenhnhanLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelbenhnhanLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelbenhnhanLayout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfmabn, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tftenbn, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tftrieuchungbn, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tftiensubn, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfketqua, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(radiobnnu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(tfdiachibn, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panelbenhnhanLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(radiobnnam, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelbenhnhanLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfnamsinhbn, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(22, 22, 22))
                            .addGroup(panelbenhnhanLayout.createSequentialGroup()
                                .addComponent(btnxoabn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(btnsuabn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnthembn, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)))
                        .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelbenhnhanLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(resetbn, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(btnimportbn)
                                .addGap(29, 29, 29)
                                .addComponent(reload)
                                .addGap(34, 34, 34)
                                .addComponent(btnxuat)
                                .addGap(37, 37, 37)
                                .addComponent(btnthoat, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelbenhnhanLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)))))
                .addGap(40, 40, 40))
        );
        panelbenhnhanLayout.setVerticalGroup(
            panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelbenhnhanLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tftimkiembn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntimkiembn))
                .addGap(12, 12, 12)
                .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelbenhnhanLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelbenhnhanLayout.createSequentialGroup()
                        .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelbenhnhanLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel12)
                                .addGap(36, 36, 36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelbenhnhanLayout.createSequentialGroup()
                                .addComponent(tfmabn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(tftenbn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)))
                        .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfnamsinhbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelbenhnhanLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel14)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel17)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel15)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel16)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel18))
                            .addGroup(panelbenhnhanLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(radiobnnam)
                                    .addComponent(radiobnnu, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addComponent(tfdiachibn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(tftrieuchungbn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(tftiensubn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(tfketqua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(36, 36, 36)
                .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelbenhnhanLayout.createSequentialGroup()
                        .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(resetbn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnthembn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelbenhnhanLayout.createSequentialGroup()
                                .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnimportbn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(reload)
                                        .addComponent(btnxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnthoat)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(145, 145, 145))
                    .addGroup(panelbenhnhanLayout.createSequentialGroup()
                        .addGroup(panelbenhnhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnsuabn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnxoabn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        paneltrangchu.addTab("Bệnh Nhân", new javax.swing.ImageIcon(getClass().getResource("/image/User group.png")), panelbenhnhan); // NOI18N

        panelnhanvien.setBackground(new java.awt.Color(255, 204, 204));
        panelnhanvien.setForeground(new java.awt.Color(255, 204, 204));

        tablenhanvien.setFont(new java.awt.Font("Courier New", 0, 10)); // NOI18N
        tablenhanvien.setFont(new java.awt.Font("Courier New", 0, 10)); // NOI18N
        tablenhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Nhân Viên", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablenhanvien.setRowHeight(25);
        tablenhanvien.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tablenhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablenhanvienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablenhanvien);

        btnthemnv.setBackground(new java.awt.Color(204, 204, 255));
        btnthemnv.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnthemnv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add.png"))); // NOI18N
        btnthemnv.setText("Thêm");
        btnthemnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemnvActionPerformed(evt);
            }
        });

        btnsuanv.setBackground(new java.awt.Color(204, 204, 255));
        btnsuanv.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnsuanv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Wrench.png"))); // NOI18N
        btnsuanv.setText("Sửa");
        btnsuanv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuanvActionPerformed(evt);
            }
        });

        btnxoanv.setBackground(new java.awt.Color(51, 255, 255));
        btnxoanv.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnxoanv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cut.png"))); // NOI18N
        btnxoanv.setText("Xóa");
        btnxoanv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 204), 3));
        btnxoanv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoanvActionPerformed(evt);
            }
        });

        btntimkiemnv.setBackground(new java.awt.Color(204, 204, 255));
        btntimkiemnv.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btntimkiemnv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Find.png"))); // NOI18N
        btntimkiemnv.setText("Tìm kiếm");
        btntimkiemnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemnvActionPerformed(evt);
            }
        });

        tftimkiemnv.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        tftimkiemnv.setText("nhập mã nhân viên hoặc tên nhân viên cần tìm...");
        tftimkiemnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tftimkiemnvActionPerformed(evt);
            }
        });
        tftimkiemnv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tftimkiemnvKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel1.setText("Mã nhân viên");

        tfmanv.setBackground(new java.awt.Color(225, 255, 245));
        tfmanv.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel2.setText("Tên Nhân Viên");

        tftennv.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel3.setText("Chức vụ");

        tfchucvunv.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        tfchucvunv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfchucvunvActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel4.setText("Ngày sinh");

        jLabel5.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel5.setText("Gmail");

        tfgmailnv.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        tfgmailnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfgmailnvActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel7.setText("Giới tính");

        buttonGroup1.add(radionamnv);
        radionamnv.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radionamnv.setText("Nam");

        buttonGroup1.add(radionunv);
        radionunv.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radionunv.setText("Nữ");

        jLabel8.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel8.setText("SĐT");

        tfsdtnv.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        tfsdtnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfsdtnvActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel9.setText("Địa Chỉ");

        tfdiachinv.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        tfdiachinv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfdiachinvActionPerformed(evt);
            }
        });

        btnthoanv.setBackground(new java.awt.Color(204, 204, 255));
        btnthoanv.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnthoanv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Exit.png"))); // NOI18N
        btnthoanv.setText("Thoát");
        btnthoanv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthoanvActionPerformed(evt);
            }
        });

        btntailainv.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btntailainv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Raise.png"))); // NOI18N
        btntailainv.setText("Tải lại");
        btntailainv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntailainvActionPerformed(evt);
            }
        });

        btnresetnv.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnresetnv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Application.png"))); // NOI18N
        btnresetnv.setText("Reset");
        btnresetnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetnvActionPerformed(evt);
            }
        });

        btnexportnv.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnexportnv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/New document.png"))); // NOI18N
        btnexportnv.setText("Xuất File Excel");
        btnexportnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportnvActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Database.png"))); // NOI18N
        jButton3.setText("Import dữ liệu");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelnhanvienLayout = new javax.swing.GroupLayout(panelnhanvien);
        panelnhanvien.setLayout(panelnhanvienLayout);
        panelnhanvienLayout.setHorizontalGroup(
            panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelnhanvienLayout.createSequentialGroup()
                .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelnhanvienLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelnhanvienLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(tftennv, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelnhanvienLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(tfmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(100, 100, 100)
                        .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelnhanvienLayout.createSequentialGroup()
                                .addComponent(btntimkiemnv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(tftimkiemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelnhanvienLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)
                        .addGap(20, 20, 20)
                        .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelnhanvienLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(radionamnv)
                                .addGap(63, 63, 63)
                                .addComponent(radionunv, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfsdtnv, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfdiachinv, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tfchucvunv, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfgmailnv, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelnhanvienLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnxoanv, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btnsuanv, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnthemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addComponent(btnresetnv, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btntailainv)
                .addGap(51, 51, 51)
                .addComponent(btnexportnv)
                .addGap(35, 35, 35)
                .addComponent(btnthoanv, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(362, 362, 362))
        );
        panelnhanvienLayout.setVerticalGroup(
            panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelnhanvienLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelnhanvienLayout.createSequentialGroup()
                        .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btntimkiemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tftimkiemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelnhanvienLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tftennv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelnhanvienLayout.createSequentialGroup()
                                .addGap(204, 204, 204)
                                .addComponent(jLabel6))
                            .addGroup(panelnhanvienLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfchucvunv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelnhanvienLayout.createSequentialGroup()
                                        .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(tfgmailnv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5))
                                        .addGap(25, 25, 25)
                                        .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(radionamnv)
                                            .addComponent(radionunv)))
                                    .addGroup(panelnhanvienLayout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25)
                                        .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(tfsdtnv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(25, 25, 25)
                                        .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel9)
                                            .addComponent(tfdiachinv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addGap(48, 48, 48)
                .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnxoanv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnsuanv, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnthemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnthoanv, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnexportnv, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btntailainv, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnresetnv, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );

        paneltrangchu.addTab("Nhân Viên", new javax.swing.ImageIcon(getClass().getResource("/image/Boss.png")), panelnhanvien); // NOI18N

        paneltaikhoan.setBackground(new java.awt.Color(255, 204, 204));

        jLabel10.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel10.setText("Mã nhân viên");

        jLabel22.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel22.setText("Mật Khẩu");

        jLabel20.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel20.setText("Nhập lại mật khẩu");

        tftaikhoanmanv.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        tabletaikhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tabletaikhoan.setRowHeight(25);
        tabletaikhoan.setRowMargin(2);
        tabletaikhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabletaikhoanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabletaikhoan);

        btntaikhoanthem.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btntaikhoanthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add.png"))); // NOI18N
        btntaikhoanthem.setText("Thêm");
        btntaikhoanthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaikhoanthemActionPerformed(evt);
            }
        });

        btntaikhoansua.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btntaikhoansua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Wrench.png"))); // NOI18N
        btntaikhoansua.setText("Sửa");
        btntaikhoansua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaikhoansuaActionPerformed(evt);
            }
        });

        btntaikhoandoitt.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btntaikhoandoitt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Lock.png"))); // NOI18N
        btntaikhoandoitt.setText("Đổi TT");
        btntaikhoandoitt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaikhoandoittActionPerformed(evt);
            }
        });

        btntaikhoantimkiem.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btntaikhoantimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Find.png"))); // NOI18N
        btntaikhoantimkiem.setText("Tìm Kiếm");
        btntaikhoantimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaikhoantimkiemActionPerformed(evt);
            }
        });

        tftaikhoantimkiem.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        tftaikhoantimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tftaikhoantimkiemKeyPressed(evt);
            }
        });

        btntaikhoanthoat.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btntaikhoanthoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Exit.png"))); // NOI18N
        btntaikhoanthoat.setText("Thoát");
        btntaikhoanthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaikhoanthoatActionPerformed(evt);
            }
        });

        btntkxuatfile.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btntkxuatfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Copy.png"))); // NOI18N
        btntkxuatfile.setText("Xuất File Excel");
        btntkxuatfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntkxuatfileActionPerformed(evt);
            }
        });

        btntktailai.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btntktailai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Raise.png"))); // NOI18N
        btntktailai.setText("Tải lại");
        btntktailai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntktailaiActionPerformed(evt);
            }
        });

        btntkreset.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btntkreset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Application.png"))); // NOI18N
        btntkreset.setText("Reset");
        btntkreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntkresetActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jLabel19.setText("Thông tin tài khoản");

        javax.swing.GroupLayout paneltaikhoanLayout = new javax.swing.GroupLayout(paneltaikhoan);
        paneltaikhoan.setLayout(paneltaikhoanLayout);
        paneltaikhoanLayout.setHorizontalGroup(
            paneltaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneltaikhoanLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(paneltaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneltaikhoanLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(143, 143, 143))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneltaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneltaikhoanLayout.createSequentialGroup()
                            .addComponent(btntaikhoandoitt)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                            .addComponent(btntaikhoansua, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(31, 31, 31)
                            .addComponent(btntaikhoanthem, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(45, 45, 45))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneltaikhoanLayout.createSequentialGroup()
                            .addGroup(paneltaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(paneltaikhoanLayout.createSequentialGroup()
                                    .addGroup(paneltaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel20))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(paneltaikhoanLayout.createSequentialGroup()
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29)))
                            .addGroup(paneltaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tftaikhoanmanv, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                .addComponent(tftaikhoanmk)
                                .addComponent(tftaikhoanmkagain))
                            .addGap(101, 101, 101))))
                .addGroup(paneltaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(paneltaikhoanLayout.createSequentialGroup()
                        .addComponent(btntkreset, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btntktailai, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btntkxuatfile, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btntaikhoanthoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(paneltaikhoanLayout.createSequentialGroup()
                        .addComponent(btntaikhoantimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(tftaikhoantimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(633, 633, 633))
        );
        paneltaikhoanLayout.setVerticalGroup(
            paneltaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneltaikhoanLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(paneltaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntaikhoantimkiem)
                    .addComponent(tftaikhoantimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(paneltaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneltaikhoanLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneltaikhoanLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(paneltaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(tftaikhoanmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(paneltaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tftaikhoanmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(paneltaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(tftaikhoanmkagain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40)
                .addGroup(paneltaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntaikhoanthoat)
                    .addComponent(btntkxuatfile, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntktailai)
                    .addComponent(btntkreset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntaikhoanthem)
                    .addComponent(btntaikhoansua)
                    .addComponent(btntaikhoandoitt))
                .addContainerGap(176, Short.MAX_VALUE))
        );

        paneltrangchu.addTab("Tài Khoản", new javax.swing.ImageIcon(getClass().getResource("/image/Blue key.png")), paneltaikhoan); // NOI18N

        panelhoadon.setBackground(new java.awt.Color(255, 204, 204));

        btnhoadonthoat.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnhoadonthoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Exit.png"))); // NOI18N
        btnhoadonthoat.setText("Thoát");
        btnhoadonthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhoadonthoatActionPerformed(evt);
            }
        });

        btnthemhoadon.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnthemhoadon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Notes.png"))); // NOI18N
        btnthemhoadon.setText("Thêm Hóa Đơn");
        btnthemhoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemhoadonActionPerformed(evt);
            }
        });

        tfhoadontimkiem.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        tfhoadontimkiem.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tfhoadontimkiemPropertyChange(evt);
            }
        });
        tfhoadontimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfhoadontimkiemKeyPressed(evt);
            }
        });

        btnhoadontimkiem.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnhoadontimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Find.png"))); // NOI18N
        btnhoadontimkiem.setText("Tìm Kiếm");
        btnhoadontimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhoadontimkiemActionPerformed(evt);
            }
        });

        tablehoadon.setModel(new javax.swing.table.DefaultTableModel(
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
        tablehoadon.setRowHeight(25);
        jScrollPane7.setViewportView(tablehoadon);

        btnxuatfile.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnxuatfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Copy.png"))); // NOI18N
        btnxuatfile.setText("Xuất file Excel");
        btnxuatfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxuatfileActionPerformed(evt);
            }
        });

        btnxemchitiet.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnxemchitiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Zoom.png"))); // NOI18N
        btnxemchitiet.setText("Xem chi tiết");
        btnxemchitiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxemchitietActionPerformed(evt);
            }
        });

        jToolBar1.setRollover(true);

        btnhoadonreport.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnhoadonreport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Report.png"))); // NOI18N
        btnhoadonreport.setText("In Hóa Đơn");
        btnhoadonreport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhoadonreportActionPerformed(evt);
            }
        });

        btnhoadonreload.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnhoadonreload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Refresh.png"))); // NOI18N
        btnhoadonreload.setText("Reload");
        btnhoadonreload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhoadonreloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelhoadonLayout = new javax.swing.GroupLayout(panelhoadon);
        panelhoadon.setLayout(panelhoadonLayout);
        panelhoadonLayout.setHorizontalGroup(
            panelhoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelhoadonLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(panelhoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelhoadonLayout.createSequentialGroup()
                        .addComponent(btnhoadontimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfhoadontimkiem))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelhoadonLayout.createSequentialGroup()
                        .addComponent(btnthemhoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnhoadonreload, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnxuatfile)
                        .addGap(49, 49, 49)
                        .addComponent(btnxemchitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btnhoadonreport, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btnhoadonthoat, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 332, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelhoadonLayout.setVerticalGroup(
            panelhoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelhoadonLayout.createSequentialGroup()
                .addGap(350, 350, 350)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelhoadonLayout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(panelhoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfhoadontimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhoadontimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(panelhoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthemhoadon)
                    .addComponent(btnxuatfile, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxemchitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhoadonreport, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhoadonthoat)
                    .addComponent(btnhoadonreload, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(176, 176, 176))
        );

        paneltrangchu.addTab("Hóa Đơn", new javax.swing.ImageIcon(getClass().getResource("/image/Dollar.png")), panelhoadon); // NOI18N

        panelkhothuoc.setBackground(new java.awt.Color(255, 204, 204));

        tablekhothuoc.setModel(new javax.swing.table.DefaultTableModel(
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
        tablekhothuoc.setRowHeight(25);
        jScrollPane5.setViewportView(tablekhothuoc);

        jButton1.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Exit.png"))); // NOI18N
        jButton1.setText("Thoát");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Exit.png"))); // NOI18N
        jButton2.setText("Xuất file excel");

        tftimkiemthuoc.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        tftimkiemthuoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Find.png"))); // NOI18N
        tftimkiemthuoc.setText("Tìm kiếm");
        tftimkiemthuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tftimkiemthuocActionPerformed(evt);
            }
        });

        tfkhothuoctimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfkhothuoctimkiemKeyPressed(evt);
            }
        });

        btnkhothuocreload.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnkhothuocreload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Refresh.png"))); // NOI18N
        btnkhothuocreload.setText("Reload");
        btnkhothuocreload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkhothuocreloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelkhothuocLayout = new javax.swing.GroupLayout(panelkhothuoc);
        panelkhothuoc.setLayout(panelkhothuocLayout);
        panelkhothuocLayout.setHorizontalGroup(
            panelkhothuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelkhothuocLayout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addGroup(panelkhothuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelkhothuocLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(btnkhothuocreload, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelkhothuocLayout.createSequentialGroup()
                        .addComponent(tftimkiemthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfkhothuoctimkiem)))
                .addContainerGap(850, Short.MAX_VALUE))
        );
        panelkhothuocLayout.setVerticalGroup(
            panelkhothuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelkhothuocLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelkhothuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tftimkiemthuoc)
                    .addComponent(tfkhothuoctimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(panelkhothuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(btnkhothuocreload, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(127, 127, 127))
        );

        paneltrangchu.addTab("Kho Thuốc", new javax.swing.ImageIcon(getClass().getResource("/image/Home.png")), panelkhothuoc); // NOI18N

        panelthongke.setBackground(new java.awt.Color(255, 204, 204));

        jPanel9.setBackground(new java.awt.Color(255, 204, 204));

        jLabel36.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel36.setText("Từ Ngày : ");

        jLabel37.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel37.setText("Đến ngày: ");

        jLabel38.setFont(new java.awt.Font("Courier New", 0, 30)); // NOI18N
        jLabel38.setText("Báo cáo thống kê");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(457, 457, 457)
                        .addComponent(jLabel36)
                        .addGap(230, 230, 230)
                        .addComponent(jLabel37))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(522, 522, 522)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(514, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(255, 204, 204));

        tablethongke.setModel(new javax.swing.table.DefaultTableModel(
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
        tablethongke.setRowHeight(25);
        jScrollPane6.setViewportView(tablethongke);

        btnthongke.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnthongke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Pie chart.png"))); // NOI18N
        btnthongke.setText("Thống kê");
        btnthongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthongkeActionPerformed(evt);
            }
        });

        buttonGroup3.add(radiothongkemanv);
        radiothongkemanv.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        radiothongkemanv.setText("Theo nhân viên");
        radiothongkemanv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiothongkemanvActionPerformed(evt);
            }
        });

        buttonGroup3.add(radiothongkethoigian);
        radiothongkethoigian.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        radiothongkethoigian.setText("Theo thời gian");
        radiothongkethoigian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiothongkethoigianActionPerformed(evt);
            }
        });

        btntkthoat.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btntkthoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Exit.png"))); // NOI18N
        btntkthoat.setText("Thoát");
        btntkthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntkthoatActionPerformed(evt);
            }
        });

        btnthongkexuat.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnthongkexuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Down.png"))); // NOI18N
        btnthongkexuat.setText("Xuất file Excel");
        btnthongkexuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthongkexuatActionPerformed(evt);
            }
        });

        buttonGroup3.add(radiotktheomathuoc);
        radiotktheomathuoc.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        radiotktheomathuoc.setText("Theo thuốc");
        radiotktheomathuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiotktheomathuocActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel44.setText("Mã NV");

        jLabel41.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel41.setText("Tổng cộng");

        tftktongcong.setEditable(false);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(radiotktheomathuoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radiothongkethoigian, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addGap(18, 18, 18)
                        .addComponent(tftkmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(radiothongkemanv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 272, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btnthongke, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnthongkexuat, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btntkthoat, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tftktongcong, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(154, 154, 154))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tftktongcong, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btntkthoat)
                            .addComponent(btnthongkexuat)
                            .addComponent(btnthongke, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(radiothongkemanv)
                        .addGap(41, 41, 41)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tftkmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44))
                        .addGap(39, 39, 39)
                        .addComponent(radiothongkethoigian)
                        .addGap(33, 33, 33)
                        .addComponent(radiotktheomathuoc)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jButton4.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Exit.png"))); // NOI18N
        jButton4.setText("Thoát");

        javax.swing.GroupLayout panelthongkeLayout = new javax.swing.GroupLayout(panelthongke);
        panelthongke.setLayout(panelthongkeLayout);
        panelthongkeLayout.setHorizontalGroup(
            panelthongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelthongkeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelthongkeLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelthongkeLayout.setVerticalGroup(
            panelthongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelthongkeLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jButton4)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        paneltrangchu.addTab("Thống kê", new javax.swing.ImageIcon(getClass().getResource("/image/3d bar chart.png")), panelthongke); // NOI18N

        panelphieunhap.setBackground(new java.awt.Color(255, 204, 204));

        tablephieunk.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(tablephieunk);

        btnphieunkxemct.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnphieunkxemct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Go forward.png"))); // NOI18N
        btnphieunkxemct.setText("Xem chi tiết");
        btnphieunkxemct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnphieunkxemctActionPerformed(evt);
            }
        });

        btntaophieunhap.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btntaophieunhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Create.png"))); // NOI18N
        btntaophieunhap.setText("Tạo Phiếu Nhập Kho");
        btntaophieunhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaophieunhapActionPerformed(evt);
            }
        });

        btnphieunktimkiem.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnphieunktimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Find.png"))); // NOI18N
        btnphieunktimkiem.setText("Tìm Kiếm");
        btnphieunktimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnphieunktimkiemActionPerformed(evt);
            }
        });

        tfphieunktimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfphieunktimkiemKeyPressed(evt);
            }
        });

        btnphieunkxuat.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnphieunkxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/New document.png"))); // NOI18N
        btnphieunkxuat.setText("Xuất File Excel");
        btnphieunkxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnphieunkxuatActionPerformed(evt);
            }
        });

        btnphieunkthoat.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnphieunkthoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Exit.png"))); // NOI18N
        btnphieunkthoat.setText("Thoát");
        btnphieunkthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnphieunkthoatActionPerformed(evt);
            }
        });

        btnphieunkreload.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnphieunkreload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Refresh.png"))); // NOI18N
        btnphieunkreload.setText("Tải lại");
        btnphieunkreload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnphieunkreloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelphieunhapLayout = new javax.swing.GroupLayout(panelphieunhap);
        panelphieunhap.setLayout(panelphieunhapLayout);
        panelphieunhapLayout.setHorizontalGroup(
            panelphieunhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelphieunhapLayout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addGroup(panelphieunhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnphieunkthoat, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(btntaophieunhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(btnphieunkxemct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(btnphieunkxuat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnphieunkreload, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(172, 172, 172)
                .addGroup(panelphieunhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelphieunhapLayout.createSequentialGroup()
                        .addComponent(btnphieunktimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfphieunktimkiem))
                    .addGroup(panelphieunhapLayout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addContainerGap(588, Short.MAX_VALUE))
        );
        panelphieunhapLayout.setVerticalGroup(
            panelphieunhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelphieunhapLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(panelphieunhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnphieunktimkiem)
                    .addComponent(tfphieunktimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelphieunhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelphieunhapLayout.createSequentialGroup()
                        .addComponent(btntaophieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnphieunkxemct)
                        .addGap(43, 43, 43)
                        .addComponent(btnphieunkxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnphieunkreload, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addComponent(btnphieunkthoat)
                .addContainerGap(198, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1772, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelphieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelphieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelphieunhap.getAccessibleContext().setAccessibleName("");

        paneltrangchu.addTab("Phiếu Nhập Kho", jPanel1);

        paneldatlich.setBackground(new java.awt.Color(255, 204, 204));
        paneldatlich.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 204, 204));

        jLabel24.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel24.setText("Mã Bệnh Nhân");

        tfdatlichmabn.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel23.setText("Tên Bệnh Nhân");

        tfdatlichtenbn.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel25.setText("Giới Tính");

        jLabel21.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel21.setText("Năm sinh");

        jLabel26.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel26.setText("Địa chỉ");

        jLabel27.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel27.setText("Tiền sử");

        jLabel28.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel28.setText("Triệu Chứng");

        jLabel29.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel29.setText("Kết quả");

        tfdatlichdiachibn.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        tfdatlichtiensubn.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        tfdatlichtrieuchungbn.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        tfdatlichketquabn.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        buttonGroup5.add(radiodatlichnam);
        radiodatlichnam.setText("Nam");
        radiodatlichnam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiodatlichnamActionPerformed(evt);
            }
        });

        buttonGroup5.add(radiodatlichnu);
        radiodatlichnu.setText("Nữ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(10, 10, 10))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(51, 51, 51)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfdatlichtiensubn, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(tfdatlichdiachibn)
                            .addComponent(tfdatlichtenbn)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(radiodatlichnam, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(radiodatlichnu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfdatlichmabn)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfdatlichtrieuchungbn)
                            .addComponent(tfdatlichketquabn, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdatlichmabn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(tfdatlichtenbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(radiodatlichnam)
                    .addComponent(radiodatlichnu))
                .addGap(23, 23, 23)
                .addComponent(jLabel21)
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(tfdatlichdiachibn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(tfdatlichtiensubn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdatlichtrieuchungbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdatlichketquabn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(26, 26, 26))
        );

        jPanel7.setBackground(new java.awt.Color(255, 204, 204));

        jLabel30.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel30.setText("Mã Bác Sĩ");

        jLabel31.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel31.setText("Chuyên khoa");

        jLabel35.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel35.setText("Tên Bác Sĩ");

        tfdatlichchucvunv.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                    .addComponent(jLabel31))
                .addGap(34, 34, 34)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfdatlichtennv)
                    .addComponent(tfdatlichmanv)
                    .addComponent(tfdatlichchucvunv, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(tfdatlichmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdatlichtennv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdatlichchucvunv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabledatlich.setModel(new javax.swing.table.DefaultTableModel(
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
        tabledatlich.setRowHeight(25);
        tabledatlich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabledatlichMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabledatlich);

        buttonGroup2.add(radiochuakham);
        radiochuakham.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radiochuakham.setText("Chưa từng khám");
        radiochuakham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiochuakhamActionPerformed(evt);
            }
        });

        buttonGroup2.add(radiodakham);
        radiodakham.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radiodakham.setText("Đã từng khám bệnh");
        radiodakham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiodakhamActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jLabel32.setText("Thông tin Bệnh Nhân");

        jLabel33.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jLabel33.setText("Thông Tin Bác Sĩ");

        btndatlich.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        btndatlich.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Clock.png"))); // NOI18N
        btndatlich.setText("Đặt Lịch");
        btndatlich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndatlichActionPerformed(evt);
            }
        });

        btnthoatdailich.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        btnthoatdailich.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Exit.png"))); // NOI18N
        btnthoatdailich.setText("Thoát");
        btnthoatdailich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthoatdailichActionPerformed(evt);
            }
        });

        panelthoigiandatlich.setBackground(new java.awt.Color(255, 204, 204));

        jLabel39.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel39.setText("Thời Gian");

        jLabel34.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel34.setText("Ngày");

        jLabel40.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel40.setText("Giờ");

        javax.swing.GroupLayout panelthoigiandatlichLayout = new javax.swing.GroupLayout(panelthoigiandatlich);
        panelthoigiandatlich.setLayout(panelthoigiandatlichLayout);
        panelthoigiandatlichLayout.setHorizontalGroup(
            panelthoigiandatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelthoigiandatlichLayout.createSequentialGroup()
                .addGroup(panelthoigiandatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelthoigiandatlichLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelthoigiandatlichLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelthoigiandatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdatlichgio, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        panelthoigiandatlichLayout.setVerticalGroup(
            panelthoigiandatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelthoigiandatlichLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel34)
                .addGap(20, 20, 20)
                .addGroup(panelthoigiandatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(tfdatlichgio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        btndatlichsua.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btndatlichsua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Wrench.png"))); // NOI18N
        btndatlichsua.setText("Sửa");
        btndatlichsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndatlichsuaActionPerformed(evt);
            }
        });

        btndatlichxoa.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btndatlichxoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cut.png"))); // NOI18N
        btndatlichxoa.setText("Xóa");
        btndatlichxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndatlichxoaActionPerformed(evt);
            }
        });

        btnxuatfiledatlich.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnxuatfiledatlich.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Copy.png"))); // NOI18N
        btnxuatfiledatlich.setText("Xuất file excel");
        btnxuatfiledatlich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxuatfiledatlichActionPerformed(evt);
            }
        });

        btndatlichtailai.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btndatlichtailai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Raise.png"))); // NOI18N
        btndatlichtailai.setText("Tải Lại");
        btndatlichtailai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndatlichtailaiActionPerformed(evt);
            }
        });

        btndatlichreset.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btndatlichreset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Application.png"))); // NOI18N
        btndatlichreset.setText("Reset");
        btndatlichreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndatlichresetActionPerformed(evt);
            }
        });

        btndatlichtimkiem.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btndatlichtimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Find.png"))); // NOI18N
        btndatlichtimkiem.setText("Tìm kiếm");
        btndatlichtimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndatlichtimkiemActionPerformed(evt);
            }
        });

        tfdatlichtimkiem.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        tfdatlichtimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdatlichtimkiemKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout paneldatlichLayout = new javax.swing.GroupLayout(paneldatlich);
        paneldatlich.setLayout(paneldatlichLayout);
        paneldatlichLayout.setHorizontalGroup(
            paneldatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneldatlichLayout.createSequentialGroup()
                .addGroup(paneldatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneldatlichLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(paneldatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneldatlichLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(201, 201, 201))
                            .addGroup(paneldatlichLayout.createSequentialGroup()
                                .addGroup(paneldatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(paneldatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(paneldatlichLayout.createSequentialGroup()
                                            .addGap(8, 8, 8)
                                            .addComponent(btndatlichxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(56, 56, 56)
                                            .addComponent(btndatlichsua, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(59, 59, 59)
                                            .addComponent(btndatlich, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(77, 77, 77)
                                            .addComponent(btndatlichtailai)
                                            .addGap(68, 68, 68)
                                            .addComponent(btndatlichreset, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(paneldatlichLayout.createSequentialGroup()
                                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(panelthoigiandatlich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(paneldatlichLayout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(radiochuakham)
                                        .addGap(40, 40, 40)
                                        .addComponent(radiodakham)
                                        .addGap(471, 471, 471)))
                                .addGroup(paneldatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(paneldatlichLayout.createSequentialGroup()
                                        .addGap(76, 76, 76)
                                        .addComponent(btnxuatfiledatlich)
                                        .addGap(80, 80, 80)
                                        .addComponent(btnthoatdailich, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(paneldatlichLayout.createSequentialGroup()
                                        .addComponent(btndatlichtimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfdatlichtimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(210, 210, 210))))
                    .addGroup(paneldatlichLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        paneldatlichLayout.setVerticalGroup(
            paneldatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneldatlichLayout.createSequentialGroup()
                .addGroup(paneldatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneldatlichLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(paneldatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneldatlichLayout.createSequentialGroup()
                                .addGroup(paneldatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btndatlichtimkiem)
                                    .addComponent(tfdatlichtimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(paneldatlichLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paneldatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btndatlichxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(paneldatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btndatlich)
                                .addComponent(btndatlichtailai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btndatlichreset)
                                .addComponent(btnxuatfiledatlich)
                                .addComponent(btnthoatdailich))
                            .addComponent(btndatlichsua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(paneldatlichLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(paneldatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(paneldatlichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radiodakham)
                            .addComponent(radiochuakham))
                        .addGap(41, 41, 41)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(panelthoigiandatlich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        paneltrangchu.addTab("Đặt lịch", new javax.swing.ImageIcon(getClass().getResource("/image/Calendar.png")), paneldatlich); // NOI18N

        getContentPane().add(paneltrangchu);
        paneltrangchu.setBounds(0, 70, 1776, 714);

        jLabel42.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel42.setText("Ứng dụng quản lí phòng khám");
        getContentPane().add(jLabel42);
        jLabel42.setBounds(502, 11, 402, 28);

        tenuser.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        tenuser.setText("user");
        getContentPane().add(tenuser);
        tenuser.setBounds(1190, 30, 147, 28);

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void paneltrangchuStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_paneltrangchuStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_paneltrangchuStateChanged

    private void btnxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxuatActionPerformed

        try {
            // TODO add your handling code here:
            export(tablebenhnhan);
        } catch (IOException ex) {
            System.out.println("1502");
        }

    }//GEN-LAST:event_btnxuatActionPerformed

    private void btntaikhoanthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaikhoanthemActionPerformed
        // TODO add your handling code here:
        taikhoan_BLL tkBLL = new taikhoan_BLL();
        if (tftaikhoanmanv.getText().trim().equals("") || tftaikhoanmk.getText().trim().equals("")
                || tftaikhoanmkagain.getText().trim().equals("")) {
            JOptionPane.showConfirmDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.CLOSED_OPTION);
        } else {
            if (tftaikhoanmk.getText().trim().equals(tftaikhoanmkagain.getText().trim()) == false) {
                JOptionPane.showConfirmDialog(this, "Tài khoản không giống nhau", "Thông báo", JOptionPane.CLOSED_OPTION);
            } else {
                taikhoan_DTO tkDTO = new taikhoan_DTO();
                tkDTO.setManv(tftaikhoanmanv.getText());
                tkDTO.setPass(tftaikhoanmk.getText());
                JOptionPane.showConfirmDialog(this, tkBLL.them(tkDTO), "Thông báo!!", JOptionPane.CLOSED_OPTION);
            }

        }
        this.showtabletaikhoan();

    }//GEN-LAST:event_btntaikhoanthemActionPerformed

    private void tfdiachinvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfdiachinvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfdiachinvActionPerformed

    private void tfsdtnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfsdtnvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfsdtnvActionPerformed

    private void tfgmailnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfgmailnvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfgmailnvActionPerformed

    private void tfchucvunvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfchucvunvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfchucvunvActionPerformed

    private void tftimkiemnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tftimkiemnvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tftimkiemnvActionPerformed

    private void tfmabnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfmabnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfmabnActionPerformed

    private void radiothongkemanvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiothongkemanvActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_radiothongkemanvActionPerformed

    private void radiothongkethoigianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiothongkethoigianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radiothongkethoigianActionPerformed

    private void radiodatlichnamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiodatlichnamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radiodatlichnamActionPerformed
    public boolean sosanhchuoi(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        } else {
            int n = s1.length();
            for (int i = 0; i < n; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    return false;
                }

            }
        }
        return true;
    }
    private void btntimkiembnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiembnActionPerformed
        // TODO add your handling code here:
        this.showtablebenhnhan();
        int dem = 0;
        int d = 0;
        String bn = tftimkiembn.getText();
        int n = benhnhan.getRowCount();
        do {//d=0,1

            String tmp = (String) benhnhan.getValueAt(d, 0);//dong i cot j
            String tmp1 = (String) benhnhan.getValueAt(d, 1);
            if (this.sosanhchuoi(tmp.trim(), bn) == true || tmp1.toLowerCase().contains(bn.toLowerCase())) {
                dem++;
            } else {
                benhnhan.removeRow(d);
                d--;
            }
            d++;//d=1,2
        }//defaultModeltable.getRowCount()=4
        while (d < benhnhan.getRowCount());
        System.out.println(dem);
    }//GEN-LAST:event_btntimkiembnActionPerformed
    public boolean Isns(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String Date = sdf.format(date.getDate());
        Date now = java.util.Date.from(Instant.now());
        if (date.before(now)) {
            return true;
        } else {
            return false;
        }
    }
    private void btnthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthoatActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát ứng dụng?", "Thông báo", JOptionPane.CANCEL_OPTION);
        if (a == 0) {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            System.exit(0);
        }
    }//GEN-LAST:event_btnthoatActionPerformed

    private void reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadActionPerformed
        // TODO add your handling code here:
        this.showtablebenhnhan();
    }//GEN-LAST:event_reloadActionPerformed

    private void btnthembnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthembnActionPerformed
        // TODO add your handling code here:
        try {

            if (tfmabn.getText().trim().equals("") || tftenbn.getText().trim().equals("")
                    || tfdiachibn.getText().trim().equals("") || (radiobnnam.isSelected() == false && radiobnnu.isSelected() == false)
                    || tfnamsinhbn.getDateFormatString().equals("") || tftrieuchungbn.getText().trim().equals("")
                    || tftiensubn.getText().trim().equals("") || tfketqua.getText().trim().equals("")) {
                JOptionPane.showConfirmDialog(panelhoadon, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.CLOSED_OPTION);

            } else {
                benhnhan_DTO thembn = new benhnhan_DTO();
                benhnhan_BLL bnBLL = new benhnhan_BLL();
                thembn.setMabn(tfmabn.getText());
                thembn.setTenbn(tftenbn.getText());
                thembn.setDiachi(tfdiachibn.getText());
                if (radiobnnam.isSelected() == true) {
                    thembn.setGioitinh(radiobnnam.getText());
                } else {
                    thembn.setGioitinh(radiobnnu.getText());
                }
//            Date date=new SimpleDateFormat("yyyy-MM-dd").parse(tfnamsinhbn.getDateFormatString());
//            System.out.println(tfnamsinhbn.getDateFormatString());
                if (this.Isns(tfnamsinhbn.getDate())) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String Date = sdf.format(tfnamsinhbn.getDate());
                    thembn.setNamsinh(Date);
                } else {
                    JOptionPane.showConfirmDialog(this, "nhập sai ngày sinh!", "Thông báo!!", JOptionPane.CLOSED_OPTION);
                    return;
                }
                thembn.setTrieuchung(tftrieuchungbn.getText());
                thembn.setTiensu(tftiensubn.getText());
                thembn.setKetqua(tfketqua.getText());
                int a = JOptionPane.showConfirmDialog(this, "bạn có muốn thêm bệnh nhân??", "Thông báo", JOptionPane.CANCEL_OPTION);
                if (a == 0) {
                    JOptionPane.showConfirmDialog(this, bnBLL.them(thembn), "Thông báo", JOptionPane.CLOSED_OPTION);
                }
                showtablebenhnhan();
            }

        } catch (HeadlessException | NullPointerException e) {
            System.out.println("hello Mai Hồng Xuân 1638");
            JOptionPane.showConfirmDialog(this, e, "", JOptionPane.CLOSED_OPTION);
        }
    }//GEN-LAST:event_btnthembnActionPerformed

    private void tablebenhnhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablebenhnhanMouseClicked
        // TODO add your handling code here:
        int i = tablebenhnhan.getSelectedRow();
        tfmabn.setText((String) tablebenhnhan.getValueAt(i, 0));
        tftenbn.setText((String) tablebenhnhan.getValueAt(i, 1));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //chuyển từ String sang Date
//        Date date=new Date();
//        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//        String dateString = df.format(date);
        String dateString = (String) tablebenhnhan.getValueAt(i, 2);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = df.parse(dateString);
            tfnamsinhbn.setDate(date);
        } catch (ParseException ex) {
            System.out.println("hé lô Mai Hồng Xuân!!" + ex);
        }
        System.out.println(tablebenhnhan.getValueAt(i, 3));
        if (tablebenhnhan.getValueAt(i, 3).equals("Nữ")) {
            radiobnnu.setSelected(true);
        } else {
            radiobnnam.setSelected(true);
        }
        tfdiachibn.setText((String) tablebenhnhan.getValueAt(i, 4));
        tftrieuchungbn.setText((String) tablebenhnhan.getValueAt(i, 5));
        tftiensubn.setText((String) tablebenhnhan.getValueAt(i, 6));
        tfketqua.setText((String) tablebenhnhan.getValueAt(i, 7));
        // tftenbn.setText((String) tablebenhnhan.getValueAt(i, 2));
    }//GEN-LAST:event_tablebenhnhanMouseClicked

    private void btnsuabnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuabnActionPerformed
        // TODO add your handling code here:
        try {

            if (tfmabn.getText().trim().equals("") || tftenbn.getText().trim().equals("")
                    || tfdiachibn.getText().trim().equals("") || (radiobnnam.isSelected() == false && radiobnnu.isSelected() == false)
                    || tfnamsinhbn.getDateFormatString().equals("") || tftrieuchungbn.getText().trim().equals("")
                    || tftiensubn.getText().trim().equals("") || tfketqua.getText().trim().equals("")) {
                JOptionPane.showConfirmDialog(panelhoadon, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.CLOSED_OPTION);

            } else {
                benhnhan_DTO suabn = new benhnhan_DTO();
                benhnhan_BLL bnBLL = new benhnhan_BLL();
                suabn.setMabn(tfmabn.getText());
                suabn.setTenbn(tftenbn.getText());
                suabn.setDiachi(tfdiachibn.getText());
                if (radiobnnam.isSelected() == true) {
                    suabn.setGioitinh(radiobnnam.getText());
                } else {
                    suabn.setGioitinh(radiobnnu.getText());
                }
//            Date date=new SimpleDateFormat("yyyy-MM-dd").parse(tfnamsinhbn.getDateFormatString());
//            System.out.println(tfnamsinhbn.getDateFormatString());
                if (this.Isns(tfnamsinhbn.getDate())) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String Date = sdf.format(tfnamsinhbn.getDate());
                    suabn.setNamsinh(Date);
                } else {
                    JOptionPane.showConfirmDialog(this, "Ngày Sinh sai rồi.Nhập lại đi", "thông báo", JOptionPane.CLOSED_OPTION);
                    return;
                }
                suabn.setTrieuchung(tftrieuchungbn.getText());
                suabn.setTiensu(tftiensubn.getText());
                suabn.setKetqua(tfketqua.getText());
                int a = JOptionPane.showConfirmDialog(this, "bạn có muốn sửa thông tin bệnh nhân không ?", "Thông báo", JOptionPane.CANCEL_OPTION);
                if (a == 0) {
                    JOptionPane.showConfirmDialog(this, bnBLL.sua(suabn), "Thông báo", JOptionPane.CLOSED_OPTION);
                }
                showtablebenhnhan();
            }

        } catch (HeadlessException | NullPointerException e) {
            System.out.println("hello Mai Hồng Xuân 1638");
            JOptionPane.showConfirmDialog(this, e, "", JOptionPane.CLOSED_OPTION);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showConfirmDialog(this, "Bạn Chưa chọn bệnh nhân cần sửa.Vui Lòng Chọn ", "Thông báo ", JOptionPane.CLOSED_OPTION);
        }
    }//GEN-LAST:event_btnsuabnActionPerformed

    private void resetbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetbnActionPerformed
        // TODO add your handling code here:

        tfmabn.setText("");
        tftenbn.setText("");
        tfnamsinhbn.setDate(java.util.Date.from(Instant.now()));

        tfdiachibn.setText("");
        buttonGroup4.clearSelection();
        tftrieuchungbn.setText("");
        tftiensubn.setText("");
        tfketqua.setText("");

    }//GEN-LAST:event_resetbnActionPerformed

    private void btnxoabnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoabnActionPerformed
        // TODO add your handling code here:
        try {
            benhnhan_DTO bn = new benhnhan_DTO();
            benhnhan_BLL bnBLL = new benhnhan_BLL();
            bn.setMabn(tfmabn.getText());
            int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa thông tin bệnh nhân không??", "Thông báo", JOptionPane.CANCEL_OPTION);
            if (a == 0) {
                JOptionPane.showConfirmDialog(this, bnBLL.xoa(bn), "Thông báo", JOptionPane.CLOSED_OPTION);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showConfirmDialog(this, "Bạn Chưa chọn bệnh nhân cần xóa.Vui Lòng Chọn ", "Thông báo ", JOptionPane.CLOSED_OPTION);
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(this, ex, "Thông báo", JOptionPane.CLOSED_OPTION);
        } finally {
            this.showtablebenhnhan();
        }
    }//GEN-LAST:event_btnxoabnActionPerformed

    private void btntailainvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntailainvActionPerformed
        // TODO add your handling code here:
        this.showtablenhanvien();
    }//GEN-LAST:event_btntailainvActionPerformed

    private void btntimkiemnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemnvActionPerformed
        // TODO add your handling code here:
        this.showtablenhanvien();
        int dem = 0;
        int d = 0;
        String nv = tftimkiemnv.getText();
        int n = nhanvien.getRowCount();
        do {//d=0,1

            String tmp = (String) nhanvien.getValueAt(d, 0);//dong i cot j
            String tmp1 = (String) nhanvien.getValueAt(d, 1);
            if (this.sosanhchuoi(tmp.trim(), nv) == true || tmp1.toLowerCase().contains(nv.toLowerCase())) {
                dem++;
            } else {
                nhanvien.removeRow(d);
                d--;
            }
            d++;//d=1,2
        }//defaultModeltable.getRowCount()=4
        while (d < nhanvien.getRowCount());
        System.out.println(dem);
        if (dem == 0) {
            JOptionPane.showConfirmDialog(this, "Không tìm thấy thông tin ", "Thông báo!!", JOptionPane.CLOSED_OPTION);
        }
    }//GEN-LAST:event_btntimkiemnvActionPerformed

    private void btnexportnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportnvActionPerformed
        try {
            // TODO add your handling code here:
            this.export(tablenhanvien);
        } catch (IOException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnexportnvActionPerformed

    private void btnresetnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetnvActionPerformed
        // TODO add your handling code here:
        tfmanv.setText("");
        tftennv.setText("");
        tfngaysinhnv.setDate(java.util.Date.from(Instant.now()));
        tfchucvunv.setText("");
        buttonGroup1.clearSelection();
        tfgmailnv.setText("");
        tfdiachinv.setText("");
        tfsdtnv.setText("");
    }//GEN-LAST:event_btnresetnvActionPerformed

    private void btnthoanvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthoanvActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát ứng dụng?", "Thông báo", JOptionPane.CANCEL_OPTION);
        if (a == 0) {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            System.exit(0);
        }
    }//GEN-LAST:event_btnthoanvActionPerformed
    public boolean Issdt(String t) {
        try {
            if (t.charAt(0) != '0' || t.length() != 10) {
                return false;
            }
            long a = Long.parseLong(t);
            return true;
        } catch (NumberFormatException e) {
            System.out.println(e);
            return false;
        }

    }
    private void btnthemnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemnvActionPerformed
        // TODO add your handling code here:
        try {
            if ((tfmanv.getText().trim().equals("") || tftennv.getText().trim().equals("")
                    || tfchucvunv.getText().trim().equals("") || (radionamnv.isSelected() == false && radionunv.isSelected() == false)
                    || tfngaysinhnv.getDateFormatString().equals("") || tfgmailnv.getText().trim().equals("")
                    || tfdiachinv.getText().trim().equals("") || tfsdtnv.getText().trim().equals(""))) {
                JOptionPane.showConfirmDialog(this, "Vui lòng nhập đầy đủ thông tin!!", "thông báo", JOptionPane.CLOSED_OPTION);
                return;
            }
            nhanvien_DTO nvDTO = new nhanvien_DTO();
            nvDTO.setManv(tfmanv.getText().trim());
            nvDTO.setTennv(tftennv.getText().trim());
            nvDTO.setChucvu(tfchucvunv.getText().trim());
            if (this.Isns(tfngaysinhnv.getDate())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String Date = sdf.format(tfngaysinhnv.getDate());
                nvDTO.setNgaysinh(Date);
            } else {
                JOptionPane.showConfirmDialog(this, "lỗi ngày sinh rồi.nhập lại đi !", "thông báo", JOptionPane.CLOSED_OPTION);
            }
            nvDTO.setGmail(tfgmailnv.getText().trim());
            if (radionamnv.isSelected()) {
                nvDTO.setGioitinh("Nam");
            } else {
                nvDTO.setGioitinh("Nữ");
            }
            if (this.Issdt(tfsdtnv.getText())) {
                nvDTO.setSdt(tfsdtnv.getText().trim());
            } else {
                JOptionPane.showConfirmDialog(this, "Số điện thoại bị lỗi", "Thông báo", JOptionPane.CLOSED_OPTION);
                return;
            }
            nvDTO.setDiachi(tfdiachinv.getText().trim());
            nhanvien_BLL nvBLL = new nhanvien_BLL();
            int a = JOptionPane.showConfirmDialog(this, "bạn có muốn thêm nhân viên không ?", "Thông báo", JOptionPane.CANCEL_OPTION);
            if (a == 0) {
                taikhoan_DTO tk=new taikhoan_DTO();
                tk.setManv(tfmanv.getText());
                tk.setPass("123456789");
                tk.setTrangthai(1);
                taikhoan_BLL tkBLL=new taikhoan_BLL();
                JOptionPane.showConfirmDialog(this, nvBLL.them(nvDTO)+"\n"+tkBLL.them(tk), "thông báo", JOptionPane.CLOSED_OPTION);
            }

        } catch (NullPointerException ex) {
            System.out.println(ex);
        } finally {
            this.showtablenhanvien();
        }
    }//GEN-LAST:event_btnthemnvActionPerformed

    private void btnsuanvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuanvActionPerformed
        // TODO add your handling code here:
        try {
            if ((tfmanv.getText().trim().equals("") || tftennv.getText().trim().equals("")
                    || tfchucvunv.getText().trim().equals("") || (radionamnv.isSelected() == false && radionunv.isSelected() == false)
                    || tfngaysinhnv.getDateFormatString().equals("") || tfgmailnv.getText().trim().equals("")
                    || tfdiachinv.getText().trim().equals("") || tfsdtnv.getText().trim().equals(""))) {
                JOptionPane.showConfirmDialog(this, "Vui lòng nhập đầy đủ thông tin!!", "thông báo", JOptionPane.CLOSED_OPTION);
                return;
            } else {
                nhanvien_DTO nvDTO = new nhanvien_DTO();
                nvDTO.setManv(tfmanv.getText().trim());
                nvDTO.setTennv(tftennv.getText().trim());
                nvDTO.setChucvu(tfchucvunv.getText().trim());
                if (this.Isns(tfngaysinhnv.getDate())) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String Date = sdf.format(tfngaysinhnv.getDate());
                    nvDTO.setNgaysinh(Date);
                } else {
                    JOptionPane.showConfirmDialog(this, "Lỗi ngày sinh rồi .nhập lại đi", "Thông báo!!", JOptionPane.CLOSED_OPTION);
                }
                nvDTO.setGmail(tfgmailnv.getText().trim());
                if (radionamnv.isSelected()) {
                    nvDTO.setGioitinh("Nam");
                } else {
                    nvDTO.setGioitinh("Nữ");
                }
                if (this.Issdt(tfsdtnv.getText())) {
                    nvDTO.setSdt(tfsdtnv.getText().trim());
                } else {
                    JOptionPane.showConfirmDialog(this, "Số điện thoại bị lỗi", "Thông báo", JOptionPane.CLOSED_OPTION);
                    return;
                }

                nvDTO.setDiachi(tfdiachinv.getText().trim());
                nhanvien_BLL nvBLL = new nhanvien_BLL();
                int a = JOptionPane.showConfirmDialog(this, "bạn có muốn sửa nhân viên không ?", "Thông báo", JOptionPane.CANCEL_OPTION);
                if (a == 0) {
                    JOptionPane.showConfirmDialog(this, nvBLL.sua(nvDTO), "thông báo", JOptionPane.CLOSED_OPTION);
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showConfirmDialog(this, "Bạn Chưa chọn nhân viên cần sửa.Vui Lòng Chọn ", "Thông báo ", JOptionPane.CLOSED_OPTION);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            this.showtablenhanvien();
        }
    }//GEN-LAST:event_btnsuanvActionPerformed

    private void tablenhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablenhanvienMouseClicked
        // TODO add your handling code here:
        int i = tablenhanvien.getSelectedRow();
        tfmanv.setText((String) tablenhanvien.getValueAt(i, 0));
        tftennv.setText((String) tablenhanvien.getValueAt(i, 1));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //chuyển từ String sang Date
//        Date date=new Date();
//        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//        String dateString = df.format(date);
        String dateString = (String) tablenhanvien.getValueAt(i, 2);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = df.parse(dateString);
            tfngaysinhnv.setDate(date);
        } catch (ParseException ex) {
            System.out.println("hé lô Mai Hồng Xuân!!" + ex);
        }
        tfchucvunv.setText((String) tablenhanvien.getValueAt(i, 3));
        //System.out.println(tablebenhnhan.getValueAt(i, 3));
        if (tablenhanvien.getValueAt(i, 4).equals("Nữ")) {
            radionunv.setSelected(true);
        } else {
            radionamnv.setSelected(true);
        }
        tfgmailnv.setText((String) tablenhanvien.getValueAt(i, 5));
        tfsdtnv.setText((String) tablenhanvien.getValueAt(i, 6));
        tfdiachinv.setText((String) tablenhanvien.getValueAt(i, 7));


    }//GEN-LAST:event_tablenhanvienMouseClicked

    private void btnxoanvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoanvActionPerformed
        // TODO add your handling code here:
        try {
            taikhoan_DTO tkDTO = new taikhoan_DTO();
            taikhoan_BLL tk = new taikhoan_BLL();
            nhanvien_DTO nv = new nhanvien_DTO();
            nhanvien_BLL nvBLL = new nhanvien_BLL();
            nv.setManv(tfmanv.getText());
            tkDTO.setManv(tfmanv.getText());
            int a = JOptionPane.showConfirmDialog(this, "bạn có muốn xóa nhân viên không ?", "Thông báo", JOptionPane.CANCEL_OPTION);
            if (a == 0) {
                JOptionPane.showConfirmDialog(this, tk.xoa(tkDTO)+"\n"+nvBLL.xoa(nv)  , "Thông báo", JOptionPane.CLOSED_OPTION);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showConfirmDialog(this, "Bạn Chưa chọn nhân viên cần xóa.Vui Lòng Chọn ", "Thông báo ", JOptionPane.CLOSED_OPTION);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            this.showtablenhanvien();
        }
    }//GEN-LAST:event_btnxoanvActionPerformed

    private void tftimkiembnPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tftimkiembnPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_tftimkiembnPropertyChange

    private void tftimkiembnInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tftimkiembnInputMethodTextChanged
        // TODO add your handling code here:
        this.showtablebenhnhan();
        int dem = 0;
        int d = 0;
        String bn = tftimkiembn.getText();
        int n = benhnhan.getRowCount();
        do {//d=0,1

            String tmp = (String) benhnhan.getValueAt(d, 0);//dong i cot j
            String tmp1 = (String) benhnhan.getValueAt(d, 1);
            if (this.sosanhchuoi(tmp.trim(), bn) == true || tmp1.toLowerCase().contains(bn.toLowerCase())) {
                dem++;
            } else {
                benhnhan.removeRow(d);
                d--;
            }
            d++;//d=1,2
        }//defaultModeltable.getRowCount()=4
        while (d < benhnhan.getRowCount());
        System.out.println(dem);
        if (dem == 0) {
            JOptionPane.showConfirmDialog(this, "Không tìm thấy thông tin ", "Thông báo!!", JOptionPane.CLOSED_OPTION);
        }
    }//GEN-LAST:event_tftimkiembnInputMethodTextChanged

    private void tftimkiembnCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tftimkiembnCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tftimkiembnCaretPositionChanged

    private void tftimkiembnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tftimkiembnKeyPressed
        // TODO add your handling code here:
        this.showtablebenhnhan();
        int dem = 0;
        int d = 0;
        String bn = tftimkiembn.getText();
        int n = benhnhan.getRowCount();
        do {//d=0,1

            String tmp = (String) benhnhan.getValueAt(d, 0);//dong i cot j
            String tmp1 = (String) benhnhan.getValueAt(d, 1);
            if (this.sosanhchuoi(tmp.trim(), bn) == true || tmp1.toLowerCase().contains(bn.toLowerCase())) {
                dem++;
            } else {
                benhnhan.removeRow(d);
                d--;
            }
            d++;//d=1,2
        }//defaultModeltable.getRowCount()=4
        while (d < benhnhan.getRowCount());
        System.out.println(dem);

    }//GEN-LAST:event_tftimkiembnKeyPressed

    private void tftimkiemnvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tftimkiemnvKeyPressed
        // TODO add your handling code here:
        this.showtablenhanvien();
        int dem = 0;
        int d = 0;
        String nv = tftimkiemnv.getText();
        int n = nhanvien.getRowCount();
        do {//d=0,1

            String tmp = (String) nhanvien.getValueAt(d, 0);//dong i cot j
            String tmp1 = (String) nhanvien.getValueAt(d, 1);
            if (this.sosanhchuoi(tmp.trim(), nv) == true || tmp1.toLowerCase().contains(nv.toLowerCase())) {
                dem++;
            } else {
                nhanvien.removeRow(d);
                d--;
            }
            d++;//d=1,2
        }//defaultModeltable.getRowCount()=4
        while (d < nhanvien.getRowCount());

    }//GEN-LAST:event_tftimkiemnvKeyPressed
    public void seteditdatlich(boolean rs) {
        tfdatlichtenbn.setEditable(rs);
        datedatlichnsbn.setOpaque(rs);
        tfdatlichdiachibn.setEditable(rs);
        tfdatlichtiensubn.setEditable(rs);
        tfdatlichtrieuchungbn.setEditable(rs);
        datedatlichnsbn.setEnabled(rs);
        radiodatlichnam.setEnabled(rs);
        radiodatlichnu.setEnabled(rs);

    }
    private void radiochuakhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiochuakhamActionPerformed
        this.seteditdatlich(true);
    }//GEN-LAST:event_radiochuakhamActionPerformed

    private void btnthoatdailichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthoatdailichActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát ứng dụng", "Thông báo", JOptionPane.CANCEL_OPTION);
        if (a == 0) {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            System.exit(0);
        }
    }//GEN-LAST:event_btnthoatdailichActionPerformed

    private void btnxuatfiledatlichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxuatfiledatlichActionPerformed
        // TODO add your handling code here:
        try {
            this.export(tabledatlich);
        } catch (Exception e) {
            System.out.println("2328" + e);
        }
    }//GEN-LAST:event_btnxuatfiledatlichActionPerformed

    private void radiodakhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiodakhamActionPerformed
        this.seteditdatlich(false);
    }//GEN-LAST:event_radiodakhamActionPerformed

    private void btndatlichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndatlichActionPerformed
        // TODO add your handling code here:
        datlich_BLL dlBLL = new datlich_BLL();
        if (radiochuakham.isSelected() == true)//khi mà bệnh nhân chưa khám lần nào thì thêm bệnh nhân qua bảng benhnhan
        {
            if (tfdatlichmabn.getText().trim().equals("") || tfdatlichtenbn.getText().trim().equals("")
                    || tfdatlichdiachibn.getText().trim().equals("") || (radiodatlichnam.isSelected() == false && radiodatlichnu.isSelected() == false)
                    || datedatlichnsbn.getDateFormatString().equals("") || tfdatlichtrieuchungbn.getText().trim().equals("")
                    || tfdatlichtiensubn.getText().trim().equals("") || tfdatlichmanv.getText().trim().equals("") || tfdatlichchucvunv.getText().trim().equals("")
                    || ngaydatlich.getDateFormatString().equals("") || tfdatlichgio.getText().trim().equals("")) {
                JOptionPane.showConfirmDialog(panelhoadon, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.CLOSED_OPTION);

            } else {
                datlich_DTO dlDTO = new datlich_DTO();
                benhnhan_DTO bnDTO = new benhnhan_DTO();
                dlDTO.setMabn(tfdatlichmabn.getText());
                bnDTO.setTenbn(tfdatlichtenbn.getText());

                if (this.Isns(datedatlichnsbn.getDate()) == true) {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String Date = sdf.format(datedatlichnsbn.getDate());
                    bnDTO.setNamsinh(Date);

                }

                bnDTO.setDiachi(tfdatlichdiachibn.getText());
                if (radiodatlichnam.isSelected() == true) {
                    bnDTO.setGioitinh(radiodatlichnam.getText());
                } else {
                    bnDTO.setGioitinh(radiodatlichnu.getText());
                }
//            Date date=new SimpleDateFormat("yyyy-MM-dd").parse(tfnamsinhbn.getDateFormatString());
//            System.out.println(tfnamsinhbn.getDateFormatString());

                bnDTO.setTrieuchung(tfdatlichtrieuchungbn.getText());
                bnDTO.setTiensu(tfdatlichtiensubn.getText());
                bnDTO.setKetqua("chưa có");
                tfdatlichketquabn.setEnabled(false);
                dlDTO.setManv(tfdatlichmanv.getText());
                if (ngaydatlich.getDate().after(Date.from(Instant.now()))) {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    String Date1 = sdf1.format(ngaydatlich.getDate());
                    dlDTO.setNgaydatlich(Date1);

                } else {
                    JOptionPane.showConfirmDialog(this, "Nhập ngày sai", "Thông báo", JOptionPane.CLOSED_OPTION);
                    return;
                }
                //chuyển từ String sang Date
//        Date date=new Date();
//        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//        String dateString = df.format(date);
                DateFormat sdf1 = new SimpleDateFormat("HH:mm");
                try
                {
                    
                    Date gio=sdf1.parse(tfdatlichgio.getText().trim());
                    dlDTO.setGiodatlich(tfdatlichgio.getText().trim());
                }
                catch(Exception e)
                {
                    JOptionPane.showConfirmDialog(this, "Giờ không hợp lệ !!!", "thông báo", JOptionPane.CLOSED_OPTION);
                    return;
                }
                int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm lịch?", "Thông báo", JOptionPane.CANCEL_OPTION);
                if (a == 0) {
                    JOptionPane.showConfirmDialog(this, dlBLL.themlichchuakham(dlDTO, bnDTO), "Thông báo", JOptionPane.CLOSED_OPTION);

                }

                this.showtabledatlich();

            }

        } 
        else if (radiodakham.isSelected()) {

            if (tfdatlichmabn.getText().trim().equals("") 
                    || ngaydatlich.getDateFormatString().equals("") || tfdatlichgio.getText().trim().equals("")) {
                JOptionPane.showConfirmDialog(panelhoadon, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.CLOSED_OPTION);

            } else {
                datlich_DTO dl = new datlich_DTO();
                dl.setMabn(tfdatlichmabn.getText());
                dl.setManv(tfdatlichmanv.getText());
                if (ngaydatlich.getDate().after(Date.from(Instant.now()))) {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    String Date1 = sdf1.format(ngaydatlich.getDate());
                    dl.setNgaydatlich(Date1);
                } else {
                    JOptionPane.showConfirmDialog(this, "Nhập ngày sai", "Thông báo", JOptionPane.CLOSED_OPTION);
                    return;
                }
                //chuyển từ String sang Date
//        Date date=new Date();
//        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//        String dateString = df.format(date);
                DateFormat sdf1 = new SimpleDateFormat("HH:mm");
                try
                {
                    
                    Date gio=sdf1.parse(tfdatlichgio.getText().trim());
                    dl.setGiodatlich(tfdatlichgio.getText().trim());
                }
                catch(Exception e)
                {
                    JOptionPane.showConfirmDialog(this, "Giờ không hợp lệ !!!", "thông báo", JOptionPane.CLOSED_OPTION);
                    return;
                }
                int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm lịch?", "Thông báo", JOptionPane.CANCEL_OPTION);
                if (a == 0) {
                    JOptionPane.showConfirmDialog(this, dlBLL.themlichdakham(dl), "Thông báo", JOptionPane.CLOSED_OPTION);
                }

                this.showtabledatlich();
            }

        } else {
            JOptionPane.showConfirmDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.CLOSED_OPTION);
        }
        this.seteditdatlich(false);
    }//GEN-LAST:event_btndatlichActionPerformed

    private void btndatlichtailaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndatlichtailaiActionPerformed
        // TODO add your handling code here:

        this.showtabledatlich();
    }//GEN-LAST:event_btndatlichtailaiActionPerformed

    private void tabledatlichMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabledatlichMouseClicked
        // TODO add your handling code here:

        datlich_BLL dlBLL = new datlich_BLL();
        int i = tabledatlich.getSelectedRow();
        String mabn = (String) tabledatlich.getValueAt(i, 0);
        String manv = (String) tabledatlich.getValueAt(i, 1);
        benhnhan_DTO bnDTO = new benhnhan_DTO();
        try {
            bnDTO = dlBLL.timkiembn(mabn);
            tfdatlichmabn.setText(bnDTO.getMabn());
            tfdatlichtenbn.setText(bnDTO.getTenbn());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //chuyển từ String sang Date
//        Date date=new Date();
//        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//        String dateString = df.format(date);
            String dateString = (String) bnDTO.getNamsinh();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = df.parse(dateString);
                datedatlichnsbn.setDate(date);
            } catch (ParseException ex) {
                System.out.println("hé lô Mai Hồng Xuân!!" + ex);
            }

            if (bnDTO.getGioitinh().toLowerCase().equals("nữ")) {
                radiodatlichnu.setSelected(true);
            } else {
                radiodatlichnam.setSelected(true);
            }
            tfdatlichdiachibn.setText((String) bnDTO.getDiachi());
            tfdatlichtrieuchungbn.setText((String) bnDTO.getTrieuchung());
            tfdatlichtiensubn.setText((String) bnDTO.getTiensu());
            tfdatlichketquabn.setText("chưa có");
            nhanvien_DTO nvDTO = new nhanvien_DTO();
            nvDTO = dlBLL.timkiemnv(manv);
            tfdatlichmanv.setText(nvDTO.getManv());
            tfdatlichtennv.setText(nvDTO.getTennv());
            tfdatlichchucvunv.setText(nvDTO.getChucvu());

        } catch (SQLException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //chuyển từ String sang Date
//        Date date=new Date();
//        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//        String dateString = df.format(date);
        String dateString = (String) tabledatlich.getValueAt(i, 2);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = df.parse(dateString);
            ngaydatlich.setDate(date);
        } catch (ParseException ex) {
            System.out.println("hé lô Mai Hồng Xuân!!" + ex);
        }
        tfdatlichgio.setText((String) tabledatlich.getValueAt(i, 3));
        this.seteditdatlich(false);
    }//GEN-LAST:event_tabledatlichMouseClicked
    public void resettabdatlich() {
        tfdatlichmabn.setText("");
        tfdatlichtenbn.setText("");
        buttonGroup5.clearSelection();
        datedatlichnsbn.setDate(java.util.Date.from(Instant.now()));
        tfdatlichdiachibn.setText("");
        tfdatlichtrieuchungbn.setText("");
        tfdatlichtiensubn.setText("");
        tfdatlichmanv.setText("");
        tfdatlichtennv.setText("");
        tfdatlichchucvunv.setText("");
        ngaydatlich.setDate(java.util.Date.from(Instant.now()));
        tfdatlichgio.setText("");
    }
    private void btndatlichresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndatlichresetActionPerformed
        // TODO add your handling code here:
        this.seteditdatlich(false);
        this.resettabdatlich();
    }//GEN-LAST:event_btndatlichresetActionPerformed

    private void btndatlichsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndatlichsuaActionPerformed
        // TODO add your handling code here:
        try {
            datlich_BLL dlBLL = new datlich_BLL();
            datlich_DTO dl = new datlich_DTO();
            dl.setMabn(tfdatlichmabn.getText());
            dl.setManv(tfdatlichmanv.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String Date = sdf.format(ngaydatlich.getDate());
            dl.setNgaydatlich(Date);
            DateFormat sdf1 = new SimpleDateFormat("HH:mm");
                try
                {
                    
                    Date gio=sdf1.parse(tfdatlichgio.getText().trim());
                    dl.setGiodatlich(tfdatlichgio.getText().trim());
                }
                catch(Exception e)
                {
                    JOptionPane.showConfirmDialog(this, "Giờ không hợp lệ !!!", "thông báo", JOptionPane.CLOSED_OPTION);
                    return;
                }
            int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa lịch không?", "Thông báo", JOptionPane.CANCEL_OPTION);
            if (a == 0) {
                JOptionPane.showConfirmDialog(this, dlBLL.sualich(dl), "Thông báo", JOptionPane.CLOSED_OPTION);
            }
            this.showtabledatlich();
            this.seteditdatlich(false);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showConfirmDialog(this, "Bạn Chưa chọn lịch cần sửa.Vui Lòng Chọn ", "Thông báo ", JOptionPane.CLOSED_OPTION);
        }
    }//GEN-LAST:event_btndatlichsuaActionPerformed

    private void btndatlichxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndatlichxoaActionPerformed
        // TODO add your handling code here:
        try {
            datlich_BLL dlBLL = new datlich_BLL();
            datlich_DTO dl = new datlich_DTO();
            dl.setMabn(tfdatlichmabn.getText());
            dl.setManv(tfdatlichmanv.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String Date = sdf.format(ngaydatlich.getDate());
            dl.setNgaydatlich(Date);
            dl.setGiodatlich(tfdatlichgio.getText());
            int a = JOptionPane.showConfirmDialog(this, "bạn có muốn xóa lịch ?", "Thông báo", JOptionPane.CANCEL_OPTION);
            if (a == 0) {
                JOptionPane.showConfirmDialog(this, dlBLL.xoalich(dl), "Thông báo", JOptionPane.CLOSED_OPTION);
            }
            this.showtabledatlich();
            this.seteditdatlich(false);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showConfirmDialog(this, "Bạn Chưa chọn lịch cần xóa.Vui Lòng Chọn ", "Thông báo ", JOptionPane.CLOSED_OPTION);
        }

    }//GEN-LAST:event_btndatlichxoaActionPerformed

    private void tfdatlichtimkiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdatlichtimkiemKeyPressed
        // TODO add your handling code here:
        this.showtabledatlich();
        int dem = 0;
        int d = 0;
        String dl = tfdatlichtimkiem.getText();
        int n = datlich.getRowCount();
        do {//d=0,1

            String tmp = (String) datlich.getValueAt(d, 0);//dong i cot j
            String tmp1 = (String) datlich.getValueAt(d, 1);
            if (this.sosanhchuoi(tmp.trim(), dl) == true || this.sosanhchuoi(tmp1.trim(), dl) == true) {
                dem++;
            } else {
                datlich.removeRow(d);
                d--;
            }
            d++;//d=1,2
        }//defaultModeltable.getRowCount()=4
        while (d < datlich.getRowCount());
        System.out.println(dem);
    }//GEN-LAST:event_tfdatlichtimkiemKeyPressed

    private void btndatlichtimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndatlichtimkiemActionPerformed
        // TODO add your handling code here:
        this.showtabledatlich();
        int dem = 0;
        int d = 0;
        String dl = tfdatlichtimkiem.getText();
        int n = datlich.getRowCount();
        do {//d=0,1

            String tmp = (String) datlich.getValueAt(d, 0);//dong i cot j
            String tmp1 = (String) datlich.getValueAt(d, 1);
            if (this.sosanhchuoi(tmp.trim(), dl) == true || this.sosanhchuoi(tmp1.trim(), dl) == true) {
                dem++;
            } else {
                datlich.removeRow(d);
                d--;
            }
            d++;//d=1,2
        }//defaultModeltable.getRowCount()=4
        while (d < datlich.getRowCount());
        System.out.println(dem);
        if (dem == 0) {
            JOptionPane.showConfirmDialog(this, "Không tìm thấy thông tin ", "Thông báo!!", JOptionPane.CLOSED_OPTION);
        }
    }//GEN-LAST:event_btndatlichtimkiemActionPerformed

    private void btntkxuatfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntkxuatfileActionPerformed
        try {
            // TODO add your handling code here:
            this.export(tabledatlich);
        } catch (IOException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btntkxuatfileActionPerformed

    private void btntaikhoanthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaikhoanthoatActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(this, "bạn có muốn thoát ứng dụng ?", "thông báo", JOptionPane.CANCEL_OPTION);
        if (a == 0) {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            System.exit(0);
        }
    }//GEN-LAST:event_btntaikhoanthoatActionPerformed

    private void btntktailaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntktailaiActionPerformed
        // TODO add your handling code here:
        this.showtabletaikhoan();
    }//GEN-LAST:event_btntktailaiActionPerformed

    private void btntkresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntkresetActionPerformed
        // TODO add your handling code here:
        tftaikhoanmanv.setText("");
        tftaikhoanmk.setText("");
        tftaikhoanmkagain.setText("");
    }//GEN-LAST:event_btntkresetActionPerformed

    private void tabletaikhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabletaikhoanMouseClicked
        // TODO add your handling code here:
        int i = tabletaikhoan.getSelectedRow();
        String manv = (String) tabletaikhoan.getValueAt(i, 0);
        String pass = (String) tabletaikhoan.getValueAt(i, 1);
        tftaikhoanmanv.setText(manv);
        tftaikhoanmk.setText(pass);
        tftaikhoanmk.setText(pass);
    }//GEN-LAST:event_tabletaikhoanMouseClicked

    private void btntaikhoansuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaikhoansuaActionPerformed
        // TODO add your handling code here:
        try {
            taikhoan_BLL tkBLL = new taikhoan_BLL();
            if (tftaikhoanmanv.getText().trim().equals("") || tftaikhoanmk.getText().trim().equals("")
                    || tftaikhoanmkagain.getText().trim().equals("")) {
                JOptionPane.showConfirmDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.CLOSED_OPTION);
            } else {
                if (tftaikhoanmk.getText().trim().equals(tftaikhoanmkagain.getText().trim()) == false) {
                    JOptionPane.showConfirmDialog(this, "Tài khoản không giống nhau", "Thông báo", JOptionPane.CLOSED_OPTION);
                } else {
                    taikhoan_DTO tkDTO = new taikhoan_DTO();
                    tkDTO.setManv(tftaikhoanmanv.getText());
                    tkDTO.setPass(tftaikhoanmk.getText());
                    JOptionPane.showConfirmDialog(this, tkBLL.sua(tkDTO), "Thông báo!!", JOptionPane.CLOSED_OPTION);
                }

            }
            this.showtabletaikhoan();
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showConfirmDialog(this, "Bạn Chưa chọn tài khoản cần sửa.Vui Lòng Chọn ", "Thông báo ", JOptionPane.CLOSED_OPTION);
        }

    }//GEN-LAST:event_btntaikhoansuaActionPerformed

    private void btntaikhoandoittActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaikhoandoittActionPerformed
        // TODO add your handling code here:
        int a=JOptionPane.showConfirmDialog(this, "Bạn có muốn đổi trạng thái hoạt động hay không ??", "thông báo", JOptionPane.CLOSED_OPTION);
        if(a==0)
        {
            try {
            taikhoan_BLL tkBLL = new taikhoan_BLL();
            if (tftaikhoanmanv.getText().trim().equals("") || tftaikhoanmk.getText().trim().equals("")) {
                JOptionPane.showConfirmDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.CLOSED_OPTION);
            } else {
                String manv=tftaikhoanmanv.getText();
                     JOptionPane.showConfirmDialog(this, tkBLL.doitt(manv), "Thông báo", JOptionPane.CLOSED_OPTION);
                
            }
            
            this.showtabletaikhoan();
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showConfirmDialog(this, "Bạn Chưa chọn tài khoản.Vui Lòng Chọn ", "Thông báo ", JOptionPane.CLOSED_OPTION);
        }
        }
        

    }//GEN-LAST:event_btntaikhoandoittActionPerformed

    private void btntaikhoantimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaikhoantimkiemActionPerformed
        // TODO add your handling code here:
        this.showtabletaikhoan();
        int dem = 0;
        int d = 0;
        String dl = tftaikhoantimkiem.getText();
        int n = nhanvien.getRowCount();
        do {//d=0,1

            String tmp = (String) taikhoan.getValueAt(d, 0);//dong i cot j
            String tmp1 = (String) taikhoan.getValueAt(d, 1);
            if (this.sosanhchuoi(tmp.trim(), dl) == true) {
                dem++;
            } else {
                taikhoan.removeRow(d);
                d--;
            }
            d++;//d=1,2
        }//defaultModeltable.getRowCount()=4
        while (d < taikhoan.getRowCount());
        System.out.println(dem);
        if (dem == 0) {
            JOptionPane.showConfirmDialog(this, "Không tìm thấy thông tin ", "Thông báo!!", JOptionPane.CLOSED_OPTION);
        }
    }//GEN-LAST:event_btntaikhoantimkiemActionPerformed

    private void btnxemchitietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxemchitietActionPerformed
        // TODO add your handling code here:
        try {
            int i = tablehoadon.getSelectedRow();
            hoadon_DTO hd_DTO = new hoadon_DTO();
            String mahd = (String) tablehoadon.getValueAt(i, 0);
            String mabn = (String) tablehoadon.getValueAt(i, 1);
            String manv = (String) tablehoadon.getValueAt(i, 2);
            String ngay = (String) tablehoadon.getValueAt(i, 3);
            String gio = (String) tablehoadon.getValueAt(i, 4);
            hd_DTO.setMahd(mahd);
            hd_DTO.setMabn(mabn);
            hd_DTO.setManv(manv);
            hd_DTO.setNgay(ngay);
            hd_DTO.setGio(gio);
            hoadon hd = new hoadon();
            hd.showhoadon(hd_DTO);
            hd.setVisible(true);
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showConfirmDialog(this, "bạn chưa chọn hóa đơn cần xem chi tiết.Vui Lòng chọn!!", "Thông báo", JOptionPane.CLOSED_OPTION);
        }

    }//GEN-LAST:event_btnxemchitietActionPerformed

    private void btnhoadontimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhoadontimkiemActionPerformed
        // TODO add your handling code here:
        this.showtablehoadon();
        int dem = 0;
        int d = 0;
        String data = tfhoadontimkiem.getText();
        int n = hoadon.getRowCount();
        do {//d=0,1

            String mahd = (String) hoadon.getValueAt(d, 0);//dong i cot j
            String mabn = (String) hoadon.getValueAt(d, 1);
            String manv = (String) hoadon.getValueAt(d, 2);
            if (this.sosanhchuoi(mahd.trim(), data) == true || this.sosanhchuoi(manv.trim(), data) == true
                    || this.sosanhchuoi(mabn.trim(), data) == true) {
                dem++;
            } else {
                hoadon.removeRow(d);
                d--;
            }
            d++;//d=1,2
        }//defaultModeltable.getRowCount()=4
        while (d < hoadon.getRowCount());
    }//GEN-LAST:event_btnhoadontimkiemActionPerformed

    private void btnthemhoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemhoadonActionPerformed
        // TODO add your handling code here:
        hoadon hd = new hoadon();
        hd.setManv(tenuser.getText());
        hd.setVisible(true);

    }//GEN-LAST:event_btnthemhoadonActionPerformed

    private void btnhoadonthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhoadonthoatActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(this, "bạn có muốn thoát ứng dụng??", "thông báo!!", JOptionPane.CANCEL_OPTION);
        if (a == 0) {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            System.exit(0);
        }
    }//GEN-LAST:event_btnhoadonthoatActionPerformed

    private void btnxuatfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxuatfileActionPerformed

        try {
            this.export(tablehoadon);
        } catch (IOException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnxuatfileActionPerformed

    private void btnimportbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimportbnActionPerformed
        // TODO add your handling code here:
        benhnhan_BLL a = new benhnhan_BLL();

        try {
            JOptionPane.showConfirmDialog(this, a.importt(), "thông báo", JOptionPane.CLOSED_OPTION);
        } catch (IOException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnimportbnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        nhanvien_BLL nvBLL = new nhanvien_BLL();
        JOptionPane.showConfirmDialog(this, nvBLL.importt(), "thông báo", JOptionPane.CLOSED_OPTION);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnhoadonreportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhoadonreportActionPerformed
        // TODO add your handling code here:
        try {
            hoadon_DTO hd = new hoadon_DTO();
            int i = tablehoadon.getSelectedRow();
            String mahd = (String) tablehoadon.getValueAt(i, 0);
            String mabn = (String) tablehoadon.getValueAt(i, 1);
            String manv = (String) tablehoadon.getValueAt(i, 2);
            String ngay = (String) tablehoadon.getValueAt(i, 3);
            String gio = (String) tablehoadon.getValueAt(i, 4);
            hd.setMahd(mahd);
            hd.setMabn(mabn);
            hd.setManv(manv);
            hd.setNgay(ngay);
            hd.setGio(gio);
            Itext a = new Itext();
            JOptionPane.showConfirmDialog(this, a.report(hd), "Thông báo ", JOptionPane.CLOSED_OPTION);

        } catch (IOException | ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showConfirmDialog(this, "lỗi chọn hóa đơn !!", "thông báo", JOptionPane.CLOSED_OPTION);
        }
    }//GEN-LAST:event_btnhoadonreportActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát ứng dụng không ??", "Thông báo", JOptionPane.CANCEL_OPTION);
        if (a == 0) {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            System.exit(0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btntaophieunhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaophieunhapActionPerformed
        // TODO add your handling code here:
        try {
            phieunhapkho a = new phieunhapkho();
            a.setmanv(tenuser.getText());
            a.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(this, e, "Lỗi", JOptionPane.CLOSED_OPTION);
        }
    }//GEN-LAST:event_btntaophieunhapActionPerformed

    private void btnphieunkxemctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnphieunkxemctActionPerformed
        // TODO add your handling code here:
        try {
            int i = tablephieunk.getSelectedRow();
            phieunhapkho_DTO phieunkDTO = new phieunhapkho_DTO();
            String mank = (String) phieunk.getValueAt(i, 0);
            String manv = (String) phieunk.getValueAt(i, 1);
            String ngaynk = (String) phieunk.getValueAt(i, 2);
            phieunkDTO.setMank(mank);
            phieunkDTO.setManv(manv);
            phieunkDTO.setNgaynk(ngaynk);
            phieunhapkho phieunhap = new phieunhapkho();
            phieunhap.setVisible(true);
            phieunhap.showphieunk(phieunkDTO);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showConfirmDialog(this, "bạn chưa chọn phiếu nhập cần xem chi tiết.Vui Lòng chọn!!!", "Thông báo", JOptionPane.CLOSED_OPTION);
        }
    }//GEN-LAST:event_btnphieunkxemctActionPerformed

    private void btnphieunkxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnphieunkxuatActionPerformed
        // TODO add your handling code here:
        try {
            this.export(tablephieunk);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnphieunkxuatActionPerformed

    private void btnphieunkthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnphieunkthoatActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát ứng dụng???", "Thông Báo", JOptionPane.CANCEL_OPTION);
        if (a == 0) {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            System.exit(0);
        }
    }//GEN-LAST:event_btnphieunkthoatActionPerformed

    private void btnphieunkreloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnphieunkreloadActionPerformed
        // TODO add your handling code here:
        this.showtablephieunk();
    }//GEN-LAST:event_btnphieunkreloadActionPerformed

    private void btnhoadonreloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhoadonreloadActionPerformed
        // TODO add your handling code here:
        this.showtablehoadon();
    }//GEN-LAST:event_btnhoadonreloadActionPerformed

    private void tfhoadontimkiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfhoadontimkiemKeyPressed
        // TODO add your handling code here:
        this.showtablehoadon();
        int dem = 0;
        int d = 0;
        String data = tfhoadontimkiem.getText();
        int n = hoadon.getRowCount();
        do {//d=0,1

            String mahd = (String) hoadon.getValueAt(d, 0);//dong i cot j
            String mabn = (String) hoadon.getValueAt(d, 1);
            String manv = (String) hoadon.getValueAt(d, 2);
            if (this.sosanhchuoi(mahd.trim(), data) == true || this.sosanhchuoi(manv.trim(), data) == true
                    || this.sosanhchuoi(mabn.trim(), data) == true) {
                dem++;
            } else {
                hoadon.removeRow(d);
                d--;
            }
            d++;//d=1,2
        }//defaultModeltable.getRowCount()=4
        while (d < hoadon.getRowCount());
    }//GEN-LAST:event_tfhoadontimkiemKeyPressed

    private void tfhoadontimkiemPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tfhoadontimkiemPropertyChange
        // TODO add your handling code here:
        this.showtablehoadon();
        int dem = 0;
        int d = 0;
        String data = tfhoadontimkiem.getText();
        int n = hoadon.getRowCount();
        do {//d=0,1

            String mahd = (String) hoadon.getValueAt(d, 0);//dong i cot j
            String mabn = (String) hoadon.getValueAt(d, 1);
            String manv = (String) hoadon.getValueAt(d, 2);
            if (this.sosanhchuoi(mahd.trim(), data) == true || this.sosanhchuoi(manv.trim(), data) == true
                    || this.sosanhchuoi(mabn.trim(), data) == true) {
                dem++;
            } else {
                hoadon.removeRow(d);
                d--;
            }
            d++;//d=1,2
        }//defaultModeltable.getRowCount()=4
        while (d < hoadon.getRowCount());

    }//GEN-LAST:event_tfhoadontimkiemPropertyChange

    private void tfkhothuoctimkiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfkhothuoctimkiemKeyPressed
        // TODO add your handling code here:
        this.showtablethuoc();
        int dem = 0;
        int d = 0;
        String data = tfkhothuoctimkiem.getText();
        int n = thuoc.getRowCount();
        do {//d=0,1

            String mathuoc = (String) thuoc.getValueAt(d, 0);//dong i cot j
            String tenthuoc = (String) thuoc.getValueAt(d, 1);

            if (this.sosanhchuoi(mathuoc.trim(), data) == true || tenthuoc.toLowerCase().contains(data.toLowerCase()) == true) {
                dem++;
            } else {
                thuoc.removeRow(d);
                d--;
            }
            d++;//d=1,2
        }//defaultModeltable.getRowCount()=4
        while (d < thuoc.getRowCount());
    }//GEN-LAST:event_tfkhothuoctimkiemKeyPressed

    private void btnkhothuocreloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhothuocreloadActionPerformed

        // TODO add your handling code here:
        try {
            this.showtablethuoc();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(this, e, "thông báo", JOptionPane.CLOSED_OPTION);
        }
    }//GEN-LAST:event_btnkhothuocreloadActionPerformed

    private void tftimkiemthuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tftimkiemthuocActionPerformed
        // TODO add your handling code here:
        this.showtablethuoc();
        int dem = 0;
        int d = 0;
        String data = tfkhothuoctimkiem.getText();
        int n = tablekhothuoc.getRowCount();
        do {//d=0,1

            String mathuoc = (String) tablekhothuoc.getValueAt(d, 0);//dong i cot j
            String tenthuoc = (String) tablekhothuoc.getValueAt(d, 1);

            if (this.sosanhchuoi(mathuoc.trim(), data) == true || tenthuoc.toLowerCase().contains(data.toLowerCase()) == true) {
                dem++;
            } else {
                thuoc.removeRow(d);
                d--;
            }
            d++;//d=1,2
        }//defaultModeltable.getRowCount()=4
        while (d < phieunk.getRowCount());
    }//GEN-LAST:event_tftimkiemthuocActionPerformed

    private void tfphieunktimkiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfphieunktimkiemKeyPressed
        // TODO add your handling code here:
        this.showtablephieunk();
        int dem = 0;
        int d = 0;
        String data = tfphieunktimkiem.getText().trim();
        
        int n = tablephieunk.getRowCount();

        do {//d=0,1

            String mank = (String) phieunk.getValueAt(d, 0);//dong i cot j
            String manv = (String) phieunk.getValueAt(d, 1);

            if (this.sosanhchuoi(mank.trim(), data) == true || this.sosanhchuoi(manv.trim(), data) == true) {
                dem++;
            } else {
                phieunk.removeRow(d);
                d--;
            }
            d++;//d=1,2
        }//defaultModeltable.getRowCount()=4
        while (d < phieunk.getRowCount());
    }//GEN-LAST:event_tfphieunktimkiemKeyPressed

    private void btnphieunktimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnphieunktimkiemActionPerformed
        // TODO add your handling code here:
        this.showtablephieunk();
        try
        {
            int dem = 0;
        int d = 0;
        String data = tfphieunktimkiem.getText();
        int n = phieunk.getRowCount();
        do {//d=0,1

            String mank = (String) phieunk.getValueAt(d, 0);//dong i cot j
            String manv = (String) phieunk.getValueAt(d, 1);

            if (this.sosanhchuoi(mank.trim(), data) == true || this.sosanhchuoi(manv.trim(), data) == true) {
                dem++;
            } else {
                phieunk.removeRow(d);
                d--;
            }
            d++;//d=1,2
        }//defaultModeltable.getRowCount()=4
        while (d < thuoc.getRowCount());
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            JOptionPane.showConfirmDialog(this, e, "thông báo", JOptionPane.CLOSED_OPTION);
        }
               
        
    }//GEN-LAST:event_btnphieunktimkiemActionPerformed

    private void tftaikhoantimkiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tftaikhoantimkiemKeyPressed
        // TODO add your handling code here:
        this.showtabletaikhoan();
        int dem = 0;
        int d = 0;
        String data = tftaikhoantimkiem.getText();
        int n = taikhoan.getRowCount();
        do {//d=0,1

            String manv = (String) taikhoan.getValueAt(d, 0);//dong i cot j

            if (this.sosanhchuoi(manv.trim(), data) == true) {
                dem++;
            } else {
                taikhoan.removeRow(d);
                d--;
            }
            d++;//d=1,2
        }//defaultModeltable.getRowCount()=4
        while (d < taikhoan.getRowCount());
    }//GEN-LAST:event_tftaikhoantimkiemKeyPressed

    private void btnthongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthongkeActionPerformed
        // TODO add your handling code here:

        try {
            if (radiothongkethoigian.isSelected()) {
                this.thongkethoigian();
            } else if (radiothongkemanv.isSelected()) {
                this.thongkenv();
            } else if (radiotktheomathuoc.isSelected()) {
                this.thongkemathuoc();
            } else {
                JOptionPane.showConfirmDialog(this, "vui lòng chọn phương án thống kê!", "Thông báo", JOptionPane.CLOSED_OPTION);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showConfirmDialog(this, "vui lòng chọn phương án thống kê!", "Thông báo", JOptionPane.CLOSED_OPTION);
        } catch (NullPointerException e) {
            JOptionPane.showConfirmDialog(this, "vui lòng chọn ngày!", "Thông báo", JOptionPane.CLOSED_OPTION);
        }


    }//GEN-LAST:event_btnthongkeActionPerformed

    private void btntkthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntkthoatActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(this, "bạn có muốn Thoát??", "Thông báo!", JOptionPane.CANCEL_OPTION);
        if (a == 0) {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            System.exit(0);
        }
    }//GEN-LAST:event_btntkthoatActionPerformed

    private void btnthongkexuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthongkexuatActionPerformed
        // TODO add your handling code here:
        try {
            this.export(tablethongke);
        } catch (Exception e) {
            System.out.println(e);
        }
        //chỗ cột thuốc phải có thêm một cái mã nhập kho nữa.để biết đc nguồn gốc của kho thuốc đó
    }//GEN-LAST:event_btnthongkexuatActionPerformed

    private void radiotktheomathuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiotktheomathuocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radiotktheomathuocActionPerformed

    private void tftimkiembnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tftimkiembnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tftimkiembnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Trangchu("user");
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndatlich;
    private javax.swing.JButton btndatlichreset;
    private javax.swing.JButton btndatlichsua;
    private javax.swing.JButton btndatlichtailai;
    private javax.swing.JButton btndatlichtimkiem;
    private javax.swing.JButton btndatlichxoa;
    private javax.swing.JButton btnexportnv;
    private javax.swing.JButton btnhoadonreload;
    private javax.swing.JButton btnhoadonreport;
    private javax.swing.JButton btnhoadonthoat;
    private javax.swing.JButton btnhoadontimkiem;
    private javax.swing.JButton btnimportbn;
    private javax.swing.JButton btnkhothuocreload;
    private javax.swing.JLabel btnns;
    private javax.swing.JButton btnphieunkreload;
    private javax.swing.JButton btnphieunkthoat;
    private javax.swing.JButton btnphieunktimkiem;
    private javax.swing.JButton btnphieunkxemct;
    private javax.swing.JButton btnphieunkxuat;
    private javax.swing.JButton btnresetnv;
    private javax.swing.JButton btnsuabn;
    private javax.swing.JButton btnsuanv;
    private javax.swing.JButton btntaikhoandoitt;
    private javax.swing.JButton btntaikhoansua;
    private javax.swing.JButton btntaikhoanthem;
    private javax.swing.JButton btntaikhoanthoat;
    private javax.swing.JButton btntaikhoantimkiem;
    private javax.swing.JButton btntailainv;
    private javax.swing.JButton btntaophieunhap;
    private javax.swing.JButton btnthembn;
    private javax.swing.JButton btnthemhoadon;
    private javax.swing.JButton btnthemnv;
    private javax.swing.JButton btnthoanv;
    private javax.swing.JButton btnthoat;
    private javax.swing.JButton btnthoatdailich;
    private javax.swing.JButton btnthongke;
    private javax.swing.JButton btnthongkexuat;
    private javax.swing.JButton btntimkiembn;
    private javax.swing.JButton btntimkiemnv;
    private javax.swing.JButton btntkreset;
    private javax.swing.JButton btntktailai;
    private javax.swing.JButton btntkthoat;
    private javax.swing.JButton btntkxuatfile;
    private javax.swing.JButton btnxemchitiet;
    private javax.swing.JButton btnxoabn;
    private javax.swing.JButton btnxoanv;
    private javax.swing.JButton btnxuat;
    private javax.swing.JButton btnxuatfile;
    private javax.swing.JButton btnxuatfiledatlich;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel panelbenhnhan;
    private javax.swing.JPanel paneldatlich;
    private javax.swing.JPanel panelhoadon;
    private javax.swing.JPanel panelkhothuoc;
    private javax.swing.JPanel panelnhanvien;
    private javax.swing.JPanel panelphieunhap;
    private javax.swing.JPanel paneltaikhoan;
    private javax.swing.JPanel panelthoigiandatlich;
    private javax.swing.JPanel panelthongke;
    private javax.swing.JTabbedPane paneltrangchu;
    private javax.swing.JRadioButton radiobnnam;
    private javax.swing.JRadioButton radiobnnu;
    private javax.swing.JRadioButton radiochuakham;
    private javax.swing.JRadioButton radiodakham;
    private javax.swing.JRadioButton radiodatlichnam;
    private javax.swing.JRadioButton radiodatlichnu;
    private javax.swing.JRadioButton radionamnv;
    private javax.swing.JRadioButton radionunv;
    private javax.swing.JRadioButton radiothongkemanv;
    private javax.swing.JRadioButton radiothongkethoigian;
    private javax.swing.JRadioButton radiotktheomathuoc;
    private javax.swing.JButton reload;
    private javax.swing.JButton resetbn;
    private javax.swing.JTable tablebenhnhan;
    private javax.swing.JTable tabledatlich;
    private javax.swing.JTable tablehoadon;
    private javax.swing.JTable tablekhothuoc;
    private javax.swing.JTable tablenhanvien;
    private javax.swing.JTable tablephieunk;
    private javax.swing.JTable tabletaikhoan;
    private javax.swing.JTable tablethongke;
    private javax.swing.JLabel tenuser;
    private javax.swing.JTextField tfchucvunv;
    private javax.swing.JTextField tfdatlichchucvunv;
    private javax.swing.JTextField tfdatlichdiachibn;
    private javax.swing.JTextField tfdatlichgio;
    private javax.swing.JTextField tfdatlichketquabn;
    private javax.swing.JTextField tfdatlichmabn;
    private javax.swing.JTextField tfdatlichmanv;
    private javax.swing.JTextField tfdatlichtenbn;
    private javax.swing.JTextField tfdatlichtennv;
    private javax.swing.JTextField tfdatlichtiensubn;
    private javax.swing.JTextField tfdatlichtimkiem;
    private javax.swing.JTextField tfdatlichtrieuchungbn;
    private javax.swing.JTextField tfdiachibn;
    private javax.swing.JTextField tfdiachinv;
    private javax.swing.JTextField tfgmailnv;
    private javax.swing.JTextField tfhoadontimkiem;
    private javax.swing.JTextField tfketqua;
    private javax.swing.JTextField tfkhothuoctimkiem;
    private javax.swing.JTextField tfmabn;
    private javax.swing.JTextField tfmanv;
    private com.toedter.calendar.JDateChooser tfnamsinhbn;
    private javax.swing.JTextField tfphieunktimkiem;
    private javax.swing.JTextField tfsdtnv;
    private javax.swing.JTextField tftaikhoanmanv;
    private javax.swing.JPasswordField tftaikhoanmk;
    private javax.swing.JPasswordField tftaikhoanmkagain;
    private javax.swing.JTextField tftaikhoantimkiem;
    private javax.swing.JTextField tftenbn;
    private javax.swing.JTextField tftennv;
    private javax.swing.JTextField tftiensubn;
    private javax.swing.JTextField tftimkiembn;
    private javax.swing.JTextField tftimkiemnv;
    private javax.swing.JButton tftimkiemthuoc;
    private javax.swing.JTextField tftkmanv;
    private javax.swing.JTextField tftktongcong;
    private javax.swing.JTextField tftrieuchungbn;
    // End of variables declaration//GEN-END:variables
}
