/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.sbd3.tokopakaian.tabelmodel;

import id.ac.unikom.sbd3.tokopakaian.entity.Reseller;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Iqram
 */
public class ResellerTM extends AbstractTableModel {

    private ArrayList<Reseller> arrayReseller;

    public void setArrayReseller(ArrayList<Reseller> arrayReseller) {
        this.arrayReseller = arrayReseller;
    }

    @Override
    public int getRowCount() {
        return this.arrayReseller.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return arrayReseller.get(rowIndex).getIdReseller();
            case 1:
                return arrayReseller.get(rowIndex).getNama();
            case 2:
                return arrayReseller.get(rowIndex).getJenisKelamin();
            case 3:
                return arrayReseller.get(rowIndex).getAlamat();
            case 4:
                return arrayReseller.get(rowIndex).getNoHp();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id Reseller";
            case 1:
                return "Nama";
            case 2:
                return "Jenis Kelamin";
            case 3:
                return "Alamat";
            case 4:
                return "No Hp";
        }
        return null;
    }

}
