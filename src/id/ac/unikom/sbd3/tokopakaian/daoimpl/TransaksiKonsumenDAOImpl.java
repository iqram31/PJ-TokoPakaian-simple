/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.sbd3.tokopakaian.daoimpl;

import id.ac.unikom.sbd3.tokopakaian.dao.TransaksiKonsumenDAO;
import id.ac.unikom.sbd3.tokopakaian.entity.TransaksiKonsumen;
import id.ac.unikom.sbd3.tokopakaian.utility.DatabaseConnectivity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iqram
 */
public class TransaksiKonsumenDAOImpl implements TransaksiKonsumenDAO {

    private final Connection conn;

    public TransaksiKonsumenDAOImpl() {
        conn = DatabaseConnectivity.getConnection();
    }

    @Override
    public ArrayList<TransaksiKonsumen> getTransaksiKonsumen() {
        return getTransaksiKonsumen(0);
    }

    @Override
    public ArrayList<TransaksiKonsumen> getTransaksiKonsumen(int keyword) {
        ArrayList<TransaksiKonsumen> arrayTransKonsumen = null;
        PreparedStatement state = null;
        boolean isSearching = keyword != 0;

        String SELECT;
        if (isSearching) {
            SELECT = "SELECT * FROM tb_trans_konsumen WHERE id_trans = ?";
        } else {
            SELECT = "SELECT * FROM tb_trans_konsumen";
        }
        /*"SELECT "
         + "tb_trans_konsumen.id_trans, "
         + "tb_trans_konsumen.tgl_trans, "
         + "tb_trans_konsumen.jumlah, "
         + "tb_trans_konsumen.harga, "
         + "tb_trans_konsumen.harga_total,"
         + "tb_trans_konsumen.kode_pakaian,"
         + "tb_trans_konsumen.nip "
         + "FROM tb_trans_konsumen,tb_pakaian,tb_pegawai "
         + "WHERE tb_trans_konsumen.kode_pakaian=tb_pakaian.kode_pakaian AND "
         + "tb_trans_konsumen.nip=tb_pegawai.nip";*/
        try {
            state = conn.prepareStatement(SELECT);

            if (isSearching) {
                state.setInt(1, keyword);
            }
            ResultSet result = state.executeQuery();
            if (result != null) {
                arrayTransKonsumen = new ArrayList<>();
                while (result.next()) {
                    TransaksiKonsumen tk = new TransaksiKonsumen();
                    tk.setIdTrans(result.getInt(1));
                    tk.setTglTrans(result.getDate(2));
                    tk.setJumlah(result.getInt(3));
                    tk.setHarga(result.getInt(4));
                    tk.setHargaTotal(result.getInt(5));
                    tk.setKodePakaian(result.getString(6));
                    tk.setNip(result.getString(7));

                    arrayTransKonsumen.add(tk);
                }
            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiResellerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (state != null) {
                try {
                    state.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiResellerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return arrayTransKonsumen;
    }

    @Override
    public boolean trans(TransaksiKonsumen transaksiKonsumen) {
        String INSERT = "INSERT INTO tb_trans_konsumen (id_trans,tgl_trans,"
                + "jumlah,harga,harga_total,kode_pakaian,nip) "
                + "VALUES (?,?,?,?,?,?,?)";

        PreparedStatement state = null;

        try {
            state = conn.prepareStatement(INSERT);

            java.util.Date utilDate = new java.util.Date();
            utilDate = transaksiKonsumen.getTglTrans();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            //Calendar date = transaksiReseller.getTglTrans();
            //java.sql.Date sqlDate = new java.sql.Date(date.getTime().getTime());

            state.setInt(1, transaksiKonsumen.getIdTrans());
            state.setDate(2, sqlDate);
            state.setInt(3, transaksiKonsumen.getJumlah());
            state.setInt(4, transaksiKonsumen.getHarga());
            state.setInt(5, transaksiKonsumen.getHargaTotal());
            state.setString(6, transaksiKonsumen.getKodePakaian());
            state.setString(7, transaksiKonsumen.getNip());

            int qty = state.executeUpdate();
            return qty > 0;

        } catch (SQLException ex) {
            Logger.getLogger(TransaksiResellerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
