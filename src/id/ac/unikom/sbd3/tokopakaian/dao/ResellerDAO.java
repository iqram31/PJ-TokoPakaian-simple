/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.sbd3.tokopakaian.dao;

import id.ac.unikom.sbd3.tokopakaian.entity.Reseller;
import java.util.ArrayList;

/**
 *
 * @author Iqram
 */
public interface ResellerDAO {

    ArrayList<Reseller> getReseller();

    ArrayList<Reseller> getReseller(String nama);

    boolean tambahReseller(Reseller reseller);

    boolean hapusReseller(String idReseller);

    boolean ubahReseller(Reseller reseller);

}
