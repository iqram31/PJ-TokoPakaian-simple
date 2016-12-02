/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.sbd3.tokopakaian.tabelmodel;

import id.ac.unikom.sbd3.tokopakaian.entity.Pakaian;
import id.ac.unikom.sbd3.tokopakaian.entity.Pegawai;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Iqram
 */
public class PakaianTM extends AbstractTableModel {

    private ArrayList<Pakaian> arrayPakaian;

    public void setArrayPakaian(ArrayList<Pakaian> arrayPakaian) {
        this.arrayPakaian = arrayPakaian;
    }

    @Override
    public int getRowCount() {
        return this.arrayPakaian.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return arrayPakaian.get(rowIndex).getKodePakaian();
            case 1:
                return arrayPakaian.get(rowIndex).getNama();
            case 2:
                return arrayPakaian.get(rowIndex).getKatPakaian();
            case 3:
                return arrayPakaian.get(rowIndex).getHarga();
            case 4:
                return arrayPakaian.get(rowIndex).getStok();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Kode Pakaian";
            case 1:
                return "Nama Pakaian";
            case 2:
                return "Kategori";
            case 3:
                return "Harga";
            case 4:
                return "Stok";
        }
        return null;
    }

}
