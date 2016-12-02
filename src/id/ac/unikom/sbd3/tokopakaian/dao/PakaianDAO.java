/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.sbd3.tokopakaian.dao;

import id.ac.unikom.sbd3.tokopakaian.entity.Pakaian;
import java.util.ArrayList;

/**
 *
 * @author Iqram
 */
public interface PakaianDAO {
    ArrayList<Pakaian> getPakaian();

    ArrayList<Pakaian> getPakaian(String nama);

    boolean ubahStok(String kodePakaian,int stok);

    boolean tambahPakaian(Pakaian pakaian);

    boolean hapusPakaian(String kode);

    boolean ubahPakaian(Pakaian pakaian);
    
}
