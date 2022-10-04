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
public class chitiethoadon_DTO {
    String mahd;
    String mathuoc;
    int sl;
    double tt;


    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
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

    public double getTt() {
        return tt;
    }

    public void setTt(double  tt) {
        this.tt = tt;
    }

    @Override
    public String toString() {
        return "chitiethoadon_DTO{" + "mahd=" + mahd + ", mathuoc=" + mathuoc + ", sl=" + sl + ", tt=" + tt + '}';
    }
 
}
