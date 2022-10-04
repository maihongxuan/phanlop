/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;
import DTO.*;
import DAL.*;

/**
 *
 * @author Hong Xuan
 */
public class login_BLL {
    login_DAL lgDAL=new login_DAL();
    public boolean kiemtra(String manv,String pass)
    {
        return lgDAL.kiemtra(manv, pass);
    }
    public int trangthai(String manv)
    {
        return lgDAL.kiemtratt(manv);
    }
}
