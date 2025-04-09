package dao;

import java.sql.*;
import java.util.*;

import entity.JobApplication;

public class JobApplicationDAOImpl implements JobApplicationDAO {
    private Connection conn;

    public JobApplicationDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public void insertJobApplication(JobApplication application) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Applications (JobID, ApplicantID, ApplicationDate, CoverLetter) VALUES (?, ?, ?, ?)");
            ps.setInt(1, application.getJobID());
            ps.setInt(2, application.getApplicantID());
            ps.setTimestamp(3, Timestamp.valueOf(application.getApplicationDate()));
            ps.setString(4, application.getCoverLetter());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<JobApplication> getApplicationsByJobID(int jobID) {
        List<JobApplication> apps = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Applications WHERE JobID = ?");
            ps.setInt(1, jobID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                apps.add(new JobApplication(
                    rs.getInt("ApplicationID"),
                    rs.getInt("JobID"),
                    rs.getInt("ApplicantID"),
                    rs.getTimestamp("ApplicationDate").toLocalDateTime(),
                    rs.getString("CoverLetter")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apps;
    }
}

