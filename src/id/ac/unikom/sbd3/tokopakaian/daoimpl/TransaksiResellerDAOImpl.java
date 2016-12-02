/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.sbd3.tokopakaian.daoimpl;

import id.ac.unikom.sbd3.tokopakaian.dao.TransaksiResellerDAO;
import id.ac.unikom.sbd3.tokopakaian.entity.TransaksiReseller;
import id.ac.unikom.sbd3.tokopakaian.utility.DatabaseConnectivity;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iqram
 */
public class TransaksiResellerDAOImpl implements TransaksiResellerDAO {

    private final java.sql.Connection conn;

    public TransaksiResellerDAOImpl() {
        conn = DatabaseConnectivity.getConnection();
    }

    @Override
    public ArrayList<TransaksiReseller> getTransaksiReseller() {
        return getTransaksiReseller(0);
    }

    @Override
    public ArrayList<TransaksiReseller> getTransaksiReseller(int keyword) {
        ArrayList<TransaksiReseller> arrayTransReseller = null;
        PreparedStatement state = null;
        boolean isSearching = keyword != 0;

        String SELECT;
        if (isSearching) {
            SELECT = "SELECT * FROM tb_trans_reseller WHERE id_trans = ?";
        } else {
            SELECT = "SELECT * FROM tb_trans_reseller";
        }

        /*"SELECT "
         + "tb_trans_reseller.id_trans, "
         + "tb_trans_reseller.tgl_trans, "
         + "tb_trans_reseller.jumlah, "
         + "tb_trans_reseller.harga, "
         + "tb_trans_reseller.hrg_total,"
         + "tb_trans_reseller.id_reseller,"
         + "tb_trans_reseller.kode_pakaian,"
         + "tb_trans_reseller.nip "
         + "FROM tb_trans_reseller,tb_pakaian,tb_pegawai,tb_reseller "
         + "WHERE tb_trans_reseller.kode_pakaian=tb_pakaian.kode_pakaian AND "
         + "tb_trans_reseller.nip=tb_pegawai.nip AND "
         + "tb_trans_reseller.id_reseller=tb_reseller.id_reseller";*/
        try {
            state = conn.prepareStatement(SELECT);
            if (isSearching) {
                state.setInt(1, keyword);
            }
            ResultSet result = state.executeQuery();
            if (result != null) {
                arrayTransReseller = new ArrayList<>();
                while (result.next()) {
                    TransaksiReseller tr = new TransaksiReseller();
                    tr.setIdTrans(result.getInt(1));
                    tr.setTglTrans(result.getDate(2));
                    tr.setJumlah(result.getInt(3));
                    tr.setHarga(result.getInt(4));
                    tr.setHargaTotal(result.getInt(5));
                    tr.setIdReseller(result.getString(6));
                    tr.setKodePakaian(result.getString(7));
                    tr.setNip(result.getString(8));

                    arrayTransReseller.add(tr);
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

        return arrayTransReseller;
    }

    @Override
    public boolean trans(TransaksiReseller transaksiReseller) {
        String INSERT = "INSERT INTO tb_trans_reseller (id_trans,tgl_trans,"
                + "jumlah,harga,hrg_total,kode_pakaian,id_reseller,nip) "
                + "VALUES (?,?,?,?,?,?,?,?)";

        PreparedStatement state = null;

        try {
            state = conn.prepareStatement(INSERT);

            java.util.Date utilDate = new java.util.Date();
            utilDate = transaksiReseller.getTglTrans();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            //Calendar date = transaksiReseller.getTglTrans();
            //java.sql.Date sqlDate = new java.sql.Date(date.getTime().getTime());

            state.setInt(1, transaksiReseller.getIdTrans());
            state.setDate(2, sqlDate);
            state.setInt(3, transaksiReseller.getJumlah());
            state.setInt(4, transaksiReseller.getHarga());
            state.setInt(5, transaksiReseller.getHargaTotal());
            state.setString(6, transaksiReseller.getKodePakaian());
            state.setString(7, transaksiReseller.getIdReseller());
            state.setString(8, transaksiReseller.getNip());

            int qty = state.executeUpdate();
            return qty > 0;

        } catch (SQLException ex) {
            Logger.getLogger(TransaksiResellerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
