package dao;


import java.sql.*;
import java.util.*;

import entity.Applicant;
import exception.InvalidEmailException;

public class ApplicantDAOImpl implements ApplicantDAO {
    private Connection conn;
    

    public ApplicantDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public void insertApplicant(Applicant applicant) {
        try {
            if (!applicant.getEmail().contains("@")) {
                throw new InvalidEmailException("Email format is invalid: " + applicant.getEmail());
            }
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Applicants (FirstName, LastName, Email, Phone, Resume) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, applicant.getFirstName());
            ps.setString(2, applicant.getLastName());
            ps.setString(3, applicant.getEmail());
            ps.setString(4, applicant.getPhone());
            ps.setString(5, applicant.getResume());
            ps.executeUpdate();
        } catch (InvalidEmailException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database error while inserting applicant.");
            e.printStackTrace();
        }
    }

    public List<Applicant> getAllApplicants() {
        List<Applicant> applicants = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Applicants");
            while (rs.next()) {
                applicants.add(new Applicant(
                    rs.getInt("ApplicantID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Email"),
                    rs.getString("Phone"),
                    rs.getString("Resume")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Database error while fetching applicants.");
            e.printStackTrace();
        }
        return applicants;
    }
}