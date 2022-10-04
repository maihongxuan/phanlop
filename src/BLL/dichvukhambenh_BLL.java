/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;
import java.util.*;
import DAL.*;
import DTO.*;
/**
 *
 * @author Hong Xuan
 */
public class dichvukhambenh_BLL {
    dichvukhambenh_DAL dvkbDAL=new dichvukhambenh_DAL();
    public ArrayList<String> gettendv()//lấy tất cả tên của dv
    {
        return dvkbDAL.gettendv();
    }
    public double getgia(String tendv)
    {
        return dvkbDAL.getgia(tendv);
    }
    public String tongdv(String mabn){
        return dvkbDAL.tongdv(mabn);
    }
    public String tongdvtheomahd(String mahd)
    {
        return dvkbDAL.tongdvtheomahd(mahd);
    }
    public double tonggiadvtheoma(String mahd)
    {
        return dvkbDAL.tonggiatheomahd(mahd);
    }
    public double tonggiadv(String mabn)
    {
        return dvkbDAL.tonggia(mabn);
    }
    public String getma(String tendv)
    {
        return dvkbDAL.getMa(tendv);
    }
}
