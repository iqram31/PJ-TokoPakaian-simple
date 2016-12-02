/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.sbd3.tokopakaian.daoimpl;

import id.ac.unikom.sbd3.tokopakaian.dao.ResellerDAO;
import id.ac.unikom.sbd3.tokopakaian.entity.Pegawai;
import id.ac.unikom.sbd3.tokopakaian.entity.Reseller;
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
public class ResellerDAOImpl implements ResellerDAO {

    private final java.sql.Connection conn;

    public ResellerDAOImpl() {
        conn = DatabaseConnectivity.getConnection();
    }

    @Override
    public ArrayList<Reseller> getReseller() {
        return getReseller(null);
    }

    @Override
    public ArrayList<Reseller> getReseller(String nama) {
        ArrayList<Reseller> arrayReseller = null;
        PreparedStatement state = null;

        boolean isSearching = nama != null && !nama.isEmpty();
        String SELECT;
        if (isSearching) {
            SELECT = "SELECT * FROM tb_reseller WHERE nama LIKE ?";
        } else {
            SELECT = "SELECT * FROM tb_reseller";
        }

        try {
            state = conn.prepareStatement(SELECT);

            if (isSearching) {
                state.setString(1, "%" + nama + "%");
            }
            ResultSet result = state.executeQuery();
            if (result != null) {
                arrayReseller = new ArrayList<>();

                while (result.next()) {
                    Reseller reseller = new Reseller();
                    reseller.setIdReseller(result.getString(1));
                    reseller.setNama(result.getString(2));
                    reseller.setJenisKelamin(result.getString(3));
                    reseller.setAlamat(result.getString(4));
                    reseller.setNoHp(result.getString(5));
                    arrayReseller.add(reseller);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResellerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (state != null) {
                try {
                    state.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ResellerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return arrayReseller;

    }

    @Override
    public boolean tambahReseller(Reseller reseller) {
        String INSERT = "INSERT INTO tb_reseller (id_reseller, nama, jenis_kelamin,"
                + "alamat, no_hp) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement state = null;

        try {
            state = conn.prepareStatement(INSERT);
            state.setString(1, reseller.getIdReseller());
            state.setString(2, reseller.getNama());
            state.setString(3, reseller.getJenisKelamin());
            state.setString(4, reseller.getAlamat());
            state.setString(5, reseller.getNoHp());

            int qty = state.executeUpdate();
            return qty > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ResellerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean hapusReseller(String idReseller) {
        String DELETE = "DELETE FROM tb_reseller "
                + "WHERE id_reseller = ?";
        PreparedStatement state = null;

        try {
            state = conn.prepareStatement(DELETE);
            state.setString(1, idReseller);
            int qty = state.executeUpdate();
            return qty > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ResellerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean ubahReseller(Reseller reseller) {
        String UPDATE = "UPDATE tb_reseller SET nama = ?, jenis_kelamin = ?,"
                + "alamat = ?, no_hp = ? WHERE id_reseller = ?";
        PreparedStatement state = null;

        try {
            state = conn.prepareStatement(UPDATE);
            state.setString(1, reseller.getNama());
            state.setString(2, reseller.getJenisKelamin());
            state.setString(3, reseller.getAlamat());
            state.setString(4, reseller.getNoHp());
            state.setString(5, reseller.getIdReseller());

            int qty = state.executeUpdate();
            return qty > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ResellerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
