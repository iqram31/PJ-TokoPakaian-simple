/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.sbd3.tokopakaian.daoimpl;

import id.ac.unikom.sbd3.tokopakaian.dao.PegawaiDAO;
import id.ac.unikom.sbd3.tokopakaian.entity.Pegawai;
import java.util.ArrayList;
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
public class PegawaiDAOImpl implements PegawaiDAO {

    private final java.sql.Connection conn;

    public PegawaiDAOImpl() {
        conn = DatabaseConnectivity.getConnection();
    }

    @Override
    public ArrayList<Pegawai> getPegawai() {
        return getPegawai(null);
    }

    @Override
    public ArrayList<Pegawai> getPegawai(String nama) {
        ArrayList<Pegawai> arrayPegawai = null;
        PreparedStatement state = null;
        
        boolean isSearching = nama != null && !nama.isEmpty();
        String SELECT;
        if (isSearching) {
            SELECT = "SELECT * FROM tb_pegawai WHERE nama LIKE ?";
        } else {
            SELECT = "SELECT * FROM tb_pegawai";
        }

        try {
            state = conn.prepareStatement(SELECT);
            if (isSearching) {
               
                state.setString(1, "%" + nama + "%");
            }
            ResultSet result = state.executeQuery();
            if (result != null) {
                arrayPegawai = new ArrayList<>();
                while (result.next()) {
                    Pegawai pegawai = new Pegawai();
                    pegawai.setNip(result.getString(1));
                    pegawai.setNama(result.getString(2));
                    pegawai.setJenisKelamin(result.getString(3));
                    pegawai.setAlamat(result.getString(4));
                    pegawai.setUserName(result.getString(5));
                    pegawai.setPassWord(result.getString(6));
                    pegawai.setHakAkses(result.getString(7));
                    arrayPegawai.add(pegawai);
                }
            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (state != null) {
                try {
                    state.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PegawaiDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return arrayPegawai;
    }

    @Override
    public Pegawai login(String userName, String passWord, String hakAkses ) {
        Pegawai pegawai = null;
        PreparedStatement state = null;
        String SELECT = "SELECT * FROM tb_pegawai WHERE " + "username = ?"
                + " AND pass = ? AND hak_akses=?";

        try {
            state = conn.prepareStatement(SELECT);
            state.setString(1, userName);
            state.setString(2, passWord);
            state.setString(3, hakAkses);

            ResultSet result = state.executeQuery();
            if (result != null && result.next()) {
                pegawai = new Pegawai();

                pegawai = new Pegawai();
                pegawai.setNip(result.getString(1));
                pegawai.setNama(result.getString(2));
                pegawai.setJenisKelamin(result.getString(3));
                pegawai.setAlamat(result.getString(4));
                pegawai.setUserName(result.getString(5));
                pegawai.setPassWord(result.getString(6));
                pegawai.setHakAkses(result.getString(7));

            }
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pegawai;
    }

    @Override
    public boolean tambahPegawai(Pegawai pegawai) {
        String INSERT = "INSERT INTO tb_pegawai (nip, nama, jenis_kelamin, alamat,"
                + "username, pass, hak_akses) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement state = null;

        try {
            state = conn.prepareStatement(INSERT);
            state.setString(1, pegawai.getNip());
            state.setString(2, pegawai.getNama());
            state.setString(3, pegawai.getJenisKelamin());
            state.setString(4, pegawai.getAlamat());
            state.setString(5, pegawai.getUserName());
            state.setString(6, pegawai.getPassWord());
            state.setString(7, pegawai.getHakAkses());

            int qty = state.executeUpdate();
            return qty > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean hapusPegawai(String nip) {
        String DELETE = "DELETE FROM tb_pegawai "
                + "WHERE nip = ?";
        PreparedStatement state = null;

        try {
            state = conn.prepareStatement(DELETE);
            state.setString(1, nip);

            int qty = state.executeUpdate();
            return qty > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean ubahPegawai(Pegawai pegawai) {
        String UPDATE = "UPDATE tb_pegawai "
                + "SET nama = ?, jenis_kelamin = ?, alamat = ?,  "
                + "username = ?, pass = ?, hak_akses = ? WHERE "
                + "nip = ?";
        PreparedStatement state = null;

        try {
            state = conn.prepareStatement(UPDATE);
            state.setString(1, pegawai.getNama());
            state.setString(2, pegawai.getJenisKelamin());
            state.setString(3, pegawai.getAlamat());
            state.setString(4, pegawai.getUserName());
            state.setString(5, pegawai.getPassWord());
            state.setString(6, pegawai.getHakAkses());
            state.setString(7, pegawai.getNip());

            int qty = state.executeUpdate();
            return qty > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
