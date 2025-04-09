package dao;

import java.sql.*;
import java.util.*;

import entity.JobListing;

public class JobListingDAOImpl implements JobListingDAO {
    private Connection conn;

    public JobListingDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public void insertJobListing(JobListing job) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Jobs (CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, job.getCompanyID());
            ps.setString(2, job.getJobTitle());
            ps.setString(3, job.getJobTitle());
            ps.setString(4, job.getJobTitle());
            ps.setDouble(5, job.getSalary());
            ps.setString(6, job.getJobTitle());
            ps.setTimestamp(7, Timestamp.valueOf(java.time.LocalDateTime.now()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<JobListing> getAllJobListings() {
        List<JobListing> jobs = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Jobs");
            while (rs.next()) {
                jobs.add(new JobListing(
                    rs.getInt("JobID"),
                    rs.getInt("CompanyID"),
                    rs.getString("JobTitle"),
                    rs.getString("JobDescription"),
                    rs.getString("JobLocation"),
                    rs.getDouble("Salary"),
                    rs.getString("JobType"),
                    rs.getTimestamp("PostedDate").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    public List<JobListing> getJobListingsBySalaryRange(double minSalary, double maxSalary) {
        List<JobListing> jobs = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Jobs WHERE Salary BETWEEN ? AND ?");
            ps.setDouble(1, minSalary);
            ps.setDouble(2, maxSalary);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                jobs.add(new JobListing(
                    rs.getInt("JobID"),
                    rs.getInt("CompanyID"),
                    rs.getString("JobTitle"),
                    rs.getString("JobDescription"),
                    rs.getString("JobLocation"),
                    rs.getDouble("Salary"),
                    rs.getString("JobType"),
                    rs.getTimestamp("PostedDate").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }
}