/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.sbd3.tokopakaian.tabelmodel;

import id.ac.unikom.sbd3.tokopakaian.entity.Pegawai;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Iqram
 */
public class PegawaiTM extends AbstractTableModel {

    private ArrayList<Pegawai> arrayPegawai;

    public void setArrayPegawai(ArrayList<Pegawai> arrayPegawai) {
        this.arrayPegawai = arrayPegawai;
    }

    @Override
    public int getRowCount() {
        return this.arrayPegawai.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    switch (columnIndex) {
            case 0:
                return arrayPegawai.get(rowIndex).getUserName();
            case 1:
                return arrayPegawai.get(rowIndex).getPassWord();
            case 2:
                return arrayPegawai.get(rowIndex).getHakAkses();
            case 3:
                return arrayPegawai.get(rowIndex).getNip();
            case 4:
                return arrayPegawai.get(rowIndex).getNama();
            case 5:
                return arrayPegawai.get(rowIndex).getJenisKelamin();
            case 6:
                return arrayPegawai.get(rowIndex).getAlamat();
        }
        return null;   
    }

    @Override
    public String getColumnName(int column) {
     switch (column) {
            case 0:
                return "Username";
            case 1:
                return "Password";
            case 2:
                return "Akses";
            case 3:
                return "NIP";
            case 4:
                return "Nama";
            case 5:
                return "Jenis Kelamin";
            case 6:
                return "Alamat";
        }
        return null;   
    }

    
}
