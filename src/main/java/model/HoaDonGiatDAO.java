/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tai Smart PC
 */
public class HoaDonGiatDAO {

   public List<HoaDonGiat> getAll() {
        List<HoaDonGiat> list = new ArrayList<>();
        String sql = "SELECT MaHoaDon, TenKhachHang, TrangThai, TongTien FROM HoaDonGiat";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDonGiat hd = new HoaDonGiat(
                        rs.getInt("MaHoaDon"),
                        rs.getString("TenKhachHang"),
                        rs.getString("TrangThai"),
                        rs.getDouble("TongTien")
                );
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(HoaDonGiat hd) {
        String sql = "INSERT INTO HoaDonGiat (TenKhachHang, TrangThai, TongTien) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, hd.getTenKhachHang());
            ps.setString(2, hd.getTrangThai());
            ps.setDouble(3, hd.getTongTien());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(HoaDonGiat hd) {
        String sql = "UPDATE HoaDonGiat SET TenKhachHang=?, TrangThai=?, TongTien=? WHERE MaHoaDon=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, hd.getTenKhachHang());
            ps.setString(2, hd.getTrangThai());
            ps.setDouble(3, hd.getTongTien());
            ps.setInt(4, hd.getMaHoaDon());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int maHoaDon) {
        String sql = "DELETE FROM HoaDonGiat WHERE MaHoaDon=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maHoaDon);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
