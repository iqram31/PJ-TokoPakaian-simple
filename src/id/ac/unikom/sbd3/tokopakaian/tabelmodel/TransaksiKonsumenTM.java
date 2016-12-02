/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.sbd3.tokopakaian.tabelmodel;

import id.ac.unikom.sbd3.tokopakaian.entity.TransaksiKonsumen;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Iqram
 */
public class TransaksiKonsumenTM extends AbstractTableModel {

    private ArrayList<TransaksiKonsumen> arrayTransaksiKonsumen;

    public void setArrayKonsumen(ArrayList<TransaksiKonsumen> arrayTransaksiKonsumen) {
        this.arrayTransaksiKonsumen = arrayTransaksiKonsumen;
    }

    @Override
    public int getRowCount() {
        return this.arrayTransaksiKonsumen.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return arrayTransaksiKonsumen.get(rowIndex).getIdTrans();
            case 1:
                return arrayTransaksiKonsumen.get(rowIndex).getTglTrans();
            case 2:
                return arrayTransaksiKonsumen.get(rowIndex).getJumlah();
            case 3:
                return arrayTransaksiKonsumen.get(rowIndex).getHarga();
            case 4:
                return arrayTransaksiKonsumen.get(rowIndex).getHargaTotal();
            case 5:
                return arrayTransaksiKonsumen.get(rowIndex).getKodePakaian();
            case 6:
                return arrayTransaksiKonsumen.get(rowIndex).getNip();
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
                return "NIP";
        }
        return null;
    }
}
