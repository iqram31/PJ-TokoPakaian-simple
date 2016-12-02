/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.sbd3.tokopakaian.daoimpl;

import id.ac.unikom.sbd3.tokopakaian.dao.PakaianDAO;
import id.ac.unikom.sbd3.tokopakaian.dao.PegawaiDAO;
import id.ac.unikom.sbd3.tokopakaian.entity.Pakaian;
import id.ac.unikom.sbd3.tokopakaian.entity.Pegawai;
import id.ac.unikom.sbd3.tokopakaian.utility.DatabaseConnectivity;
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
public class PakaianDAOImpl implements PakaianDAO {

    private final java.sql.Connection conn;

    public PakaianDAOImpl() {
        conn = DatabaseConnectivity.getConnection();
    }

    @Override
    public ArrayList<Pakaian> getPakaian() {
        return getPakaian(null);
    }

    @Override
    public ArrayList<Pakaian> getPakaian(String nama) {
        ArrayList<Pakaian> arrayPakaian = null;
        PreparedStatement state = null;

        boolean isSearching = nama != null && !nama.isEmpty();
        String SELECT;
        if (isSearching) {
            SELECT = "SELECT * FROM tb_pakaian WHERE nama LIKE ?";
        } else {
            SELECT = "SELECT * FROM tb_pakaian";
        }

        try {
            state = conn.prepareStatement(SELECT);
            if (isSearching) {
                state.setString(1, "%" + nama + "%");
            }
            ResultSet result = state.executeQuery();
            if (result != null) {
                arrayPakaian = new ArrayList<>();
                while (result.next()) {
                    Pakaian pakaian = new Pakaian();
                    pakaian.setKodePakaian(result.getString(1));
                    pakaian.setNama(result.getString(2));
                    pakaian.setKatPakaian(result.getString(3));
                    pakaian.setHarga(result.getInt(4));
                    pakaian.setStok(result.getInt(5));
                    arrayPakaian.add(pakaian);
                }
            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(PakaianDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (state != null) {
                try {
                    state.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PakaianDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return arrayPakaian;
    }

    @Override
    public boolean ubahStok(String kodePakaian, int stok) {
        String UpdateStok = "UPDATE tb_pakaian SET stok = ? WHERE kode_pakaian = ?";
        PreparedStatement state = null;

        try {
            state = conn.prepareStatement(UpdateStok);
            state.setInt(1, stok);
            state.setString(2, kodePakaian);
            int qty = state.executeUpdate();
            return qty > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PakaianDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean tambahPakaian(Pakaian pakaian) {
        String INSERT = "INSERT INTO tb_pakaian(kode_pakaian, nama, kat_pakaian"
                + ",harga, stok) VALUES (?,?,?,?,?)";
        PreparedStatement state = null;

        try {
            state = conn.prepareStatement(INSERT);
            state.setString(1, pakaian.getKodePakaian());
            state.setString(2, pakaian.getNama());
            state.setString(3, pakaian.getKatPakaian());
            state.setInt(4, pakaian.getHarga());
            state.setInt(5, pakaian.getStok());

            int qty = state.executeUpdate();
            return qty > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PakaianDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean hapusPakaian(String kode) {
        String DELETE = "DELETE FROM tb_pakaian "
                + "WHERE kode_pakaian = ?";
        PreparedStatement state = null;

        try {
            state = conn.prepareStatement(DELETE);
            state.setString(1, kode);

            int qty = state.executeUpdate();
            return qty > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PakaianDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean ubahPakaian(Pakaian pakaian) {
        String UPDATE = "UPDATE tb_pakaian "
                + "SET nama = ?, kat_pakaian = ?,"
                + "harga = ?, stok = ? WHERE kode_pakaian = ?";
        PreparedStatement state = null;

        try {
            state = conn.prepareStatement(UPDATE);
            state.setString(1, pakaian.getNama());
            state.setString(2, pakaian.getKatPakaian());
            state.setInt(3, pakaian.getHarga());
            state.setInt(4, pakaian.getStok());
            state.setString(5, pakaian.getKodePakaian());

            int qty = state.executeUpdate();
            return qty > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PakaianDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
