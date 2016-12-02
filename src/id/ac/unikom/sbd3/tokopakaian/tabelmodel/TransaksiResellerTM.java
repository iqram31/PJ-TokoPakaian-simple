/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.sbd3.tokopakaian.tabelmodel;

import id.ac.unikom.sbd3.tokopakaian.entity.TransaksiReseller;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Iqram
 */
public class TransaksiResellerTM extends AbstractTableModel {

    private ArrayList<TransaksiReseller> arrayTransaksiReseller;

    public void setArrayReseller(ArrayList<TransaksiReseller> arrayTransaksiReseller) {
        this.arrayTransaksiReseller = arrayTransaksiReseller;
    }

    @Override
    public int getRowCount() {
        return this.arrayTransaksiReseller.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return arrayTransaksiReseller.get(rowIndex).getIdTrans();
            case 1:
                return arrayTransaksiReseller.get(rowIndex).getTglTrans();
            case 2:
                return arrayTransaksiReseller.get(rowIndex).getJumlah();
            case 3:
                return arrayTransaksiReseller.get(rowIndex).getHarga();
            case 4:
                return arrayTransaksiReseller.get(rowIndex).getHargaTotal();
            case 5:
                return arrayTransaksiReseller.get(rowIndex).getKodePakaian();
            case 6:
                return arrayTransaksiReseller.get(rowIndex).getIdReseller();
            case 7:
                return arrayTransaksiReseller.get(rowIndex).getNip();

        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id Trans";
            case 1:
                return "Tgl Trans";
            case 2:
                return "Jumlah";
            case 3:
                return "Harga";
            case 4:
                return "Harga Total";
            case 5:
                return "Kode Pakaian";
            case 6:
                return "Id Reseler";
            case 7:
                return "NIP";
        }

        return null;
    }

}
