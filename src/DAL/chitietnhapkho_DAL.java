/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.*;
import DAL.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author Hong Xuan
 */
public class chitietnhapkho_DAL {

    Connection con = null;
    String dburl;

    public boolean createconnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            dburl = "jdbc:sqlserver://localhost:1433;"
                    + "DatabaseName=QUANLIPHONGKHAM";
            con = DriverManager.getConnection(dburl, "sa", "sa");
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            return false;
        }
    }

    public void closeconnection() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<chitietnhapkho_DTO> gettheomank(String mank) {
        ArrayList<chitietnhapkho_DTO> arr = new ArrayList<chitietnhapkho_DTO>();
        if (this.createconnection()) {
            try {
                String sql = "select * \n"
                        + "from chitietnhapkho\n"
                        + "where mank='" + mank + "'";
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while (rs.next()) {
                    chitietnhapkho_DTO chitietnk = new chitietnhapkho_DTO();
                    chitietnk.setMank(rs.getString("MANK"));
                    chitietnk.setMathuoc(rs.getString("mathuoc"));
                    chitietnk.setTenthuoc(rs.getString("TENTHUOC"));
                    chitietnk.setGia(rs.getFloat("Gia"));
                    chitietnk.setSl(rs.getInt("SL"));
                    arr.add(chitietnk);
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                this.closeconnection();
            }
        }
        return arr;
    }

    public boolean haschitiet(String mank, String mathuoc) {
        boolean kq = false;
        if (this.createconnection()) {
            try {
                String sql = "select *\n"
                        + "from CHITIETNHAPKHO\n"
                        + "where MANK='" + mank + "' and MATHUOC='" + mathuoc + "'";
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                if (rs.next()) {
                    kq = true;
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                this.closeconnection();
            }
        }
        return kq;
    }

    public boolean them(chitietnhapkho_DTO chitiet) {
        boolean kq = false;
        thuoc_DAL thuocDAL = new thuoc_DAL();
        thuoc_DTO thuoc = new thuoc_DTO();
        if (this.createconnection()) {
            String mank = chitiet.getMank();
            String mathuoc = chitiet.getMathuoc();
            String tenthuoc = chitiet.getTenthuoc();
            double gia = chitiet.getGia();
            int sl = chitiet.getSl();
            thuoc.setMathuoc(mathuoc);
            thuoc.setTenthuoc(tenthuoc);
            thuoc.setGia(gia);
            thuoc.setSl(sl);
            if (thuocDAL.hasthuoc(mathuoc))//là mã thuốc này đã có trong csdl
            {
                try {
                    String sql = "insert into CHITIETNHAPKHO "
                            + "values('" + mank + "','" + mathuoc + "',N'" + tenthuoc + "'," + gia + "," + sl + ")";
                    Statement stm = con.createStatement();
                    int i = stm.executeUpdate(sql);
                    int slmoi=sl+thuocDAL.getsl(mathuoc);
                    if (i > 0 && thuocDAL.capnhatsl(mathuoc, slmoi)) {
                        kq = true;
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                } finally {
                    this.closeconnection();
                }
            } else//ngược lại thì không có cần thêm trước khi mà ...
            {
                if (thuocDAL.them(thuoc)) {
                    try {
                        String sql = "insert into CHITIETNHAPKHO "
                                + "values('" + mank + "','" + mathuoc + "','" + tenthuoc + "'," + gia + "," + sl + ")";
                        Statement stm = con.createStatement();
                        int i = stm.executeUpdate(sql);
                        if (i > 0) {
                            kq = true;
                        }
                    } catch (SQLException e) {
                        System.out.println(e);
                    } finally {
                        this.closeconnection();
                    }
                }
            }

        }
        return kq;
    }

    public static void main(String[] args) {
        chitietnhapkho_DAL nkDAL = new chitietnhapkho_DAL();
        chitietnhapkho_DTO chitiet=new chitietnhapkho_DTO();
        chitiet.setMank("NK_0006");
        chitiet.setMathuoc("MT_0029");
        chitiet.setTenthuoc("panadol");
        chitiet.setGia(5000);
        chitiet.setSl(200);
        System.out.println(nkDAL.them(chitiet));
    }

}
