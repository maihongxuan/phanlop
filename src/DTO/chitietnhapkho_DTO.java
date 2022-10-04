/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Hong Xuan
 */
public class chitietnhapkho_DTO {
    String mank;
    String tenthuoc;
    String mathuoc;
    int sl;
    double gia;

    public String getMank() {
        return mank;
    }

    public void setMank(String mank) {
        this.mank = mank;
    }

    public String getTenthuoc() {
        return tenthuoc;
    }

    public void setTenthuoc(String tenthuoc) {
        this.tenthuoc = tenthuoc;
    }

    public String getMathuoc() {
        return mathuoc;
    }

    public void setMathuoc(String mathuoc) {
        this.mathuoc = mathuoc;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    @Override
    public String toString() {
        return "chitietnhapkho_DTO{" + "mank=" + mank + ", tenthuoc=" + tenthuoc + ", mathuoc=" + mathuoc + ", sl=" + sl + ", gia=" + gia + '}';
    }
    
}
