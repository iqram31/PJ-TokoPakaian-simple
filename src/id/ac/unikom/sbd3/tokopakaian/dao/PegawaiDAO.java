/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.sbd3.tokopakaian.dao;

import id.ac.unikom.sbd3.tokopakaian.entity.Pegawai;
import java.util.ArrayList;

/**
 *
 * @author Iqram
 */
public interface PegawaiDAO {
    ArrayList<Pegawai> getPegawai();

    ArrayList<Pegawai> getPegawai(String nama);
    
    Pegawai login(String userName, String passWord, String hakAkses);

    boolean tambahPegawai(Pegawai pegawai);

    boolean hapusPegawai(String nip);

    boolean ubahPegawai(Pegawai pegawai);
}
