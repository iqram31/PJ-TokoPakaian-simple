/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.sbd3.tokopakaian.dao;

import id.ac.unikom.sbd3.tokopakaian.entity.TransaksiKonsumen;
import java.util.ArrayList;

/**
 *
 * @author Iqram
 */
public interface TransaksiKonsumenDAO {

    //array data trans konsumen
    ArrayList<TransaksiKonsumen> getTransaksiKonsumen();

    //proses pencarian
    ArrayList<TransaksiKonsumen> getTransaksiKonsumen(int keyword);

    //proses transaksi
    boolean trans(TransaksiKonsumen transaksiKonsumen);
}
