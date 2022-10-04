/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;
import java.util.*;
import DTO.*;
import DAL.*;
/**
 *
 * @author Hong Xuan
 */
public class dvbn_BLL {
    dvbn_DAL dvbn=new dvbn_DAL();
    public boolean hasdvbn(String mabn,String madv,String mahd)
    {
        return dvbn.hasdvbn(mabn, madv,mahd);
    }
    public boolean them(dvbn_DTO dichvu)
    {
        if(dvbn.hasdvbn(dichvu.getMabn(), dichvu.getMadv(),dichvu.getMahd()))
        {
            return false;
        }
        if(dvbn.them(dichvu))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
