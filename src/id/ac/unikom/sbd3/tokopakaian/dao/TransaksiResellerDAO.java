/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.sbd3.tokopakaian.dao;

import id.ac.unikom.sbd3.tokopakaian.entity.TransaksiReseller;
import java.util.ArrayList;

/**
 *
 * @author Iqram
 */
public interface TransaksiResellerDAO {

    //array data trans reseller
    ArrayList<TransaksiReseller> getTransaksiReseller();

    //proses pencarian
    ArrayList<TransaksiReseller> getTransaksiReseller(int keyword);

    //proses transaksi
    boolean trans(TransaksiReseller transaksiReseller);

}
