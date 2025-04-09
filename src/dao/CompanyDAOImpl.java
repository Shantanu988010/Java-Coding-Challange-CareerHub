package dao;

import java.sql.*;
import java.util.*;

import entity.Company;

public class CompanyDAOImpl implements CompanyDAO {
    private Connection conn;
    

    public CompanyDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public void insertCompany(Company company) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Companies (CompanyName, Location) VALUES (?, ?)");
            ps.setString(1, company.getCompanyName());
            ps.setString(2, company.getLocation());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Companies");
            while (rs.next()) {
                companies.add(new Company(
                    rs.getInt("CompanyID"),
                    rs.getString("CompanyName"),
                    rs.getString("Location")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }
}
