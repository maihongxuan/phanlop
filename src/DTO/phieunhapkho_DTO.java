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
public class phieunhapkho_DTO {
    String mank;
    String manv;
    String ngaynk;

    public String getMank() {
        return mank;
    }

    public void setMank(String mank) {
        this.mank = mank;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getNgaynk() {
        return ngaynk;
    }

    public void setNgaynk(String ngaynk) {
        this.ngaynk = ngaynk;
    }

    @Override
    public String toString() {
        return "phieunhapkho_DTO{" + "mank=" + mank + ", manv=" + manv + ", ngaynk=" + ngaynk + '}';
    }
    
}
