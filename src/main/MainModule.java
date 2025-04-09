package main;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import entity.Applicant;
import entity.Company;
import entity.JobApplication;
import entity.JobListing;
import util.DBConnUtil;

import dao.CompanyDAO;
import dao.JobListingDAO;
import dao.ApplicantDAO;
import dao.JobApplicationDAO;
import dao.CompanyDAOImpl;
import dao.JobListingDAOImpl;
import dao.ApplicantDAOImpl;
import dao.JobApplicationDAOImpl;

public class MainModule {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Connection conn = DBConnUtil.getConnection("src/db.properties");

        CompanyDAO companyDAO = new CompanyDAOImpl(conn);
        JobListingDAO jobListingDAO = new JobListingDAOImpl(conn);
        ApplicantDAO applicantDAO = new ApplicantDAOImpl(conn);
        JobApplicationDAO jobApplicationDAO = new JobApplicationDAOImpl(conn);

        while (true) {
            System.out.println(" CareerHub Job Board\n");
            System.out.println("1. Register Company ");
            System.out.println("2. Company Job Posting:");
            System.out.println("3. Applicant Profile Creation");
            System.out.println("4. Job Application Submission");
            System.out.println("5. Job Listing Retrieval");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Company Name: ");
                    String cname = scanner.nextLine();
                    System.out.print("Location: ");
                    String loc = scanner.nextLine();
                    Company company = new Company(0, cname, loc);
                    companyDAO.insertCompany(company);
                    System.out.println("Company Registered.");
                    break;
                case 2:
                    System.out.print("Company ID: ");
                    int cid = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Job Title: ");
                    String jtitle = scanner.nextLine();
                    System.out.print("Job Desc: ");
                    String jdesc = scanner.nextLine();
                    System.out.print("Job Location: ");
                    String jloc = scanner.nextLine();
                    System.out.print("Salary: ");
                    double sal = scanner.nextDouble(); scanner.nextLine();
                    System.out.print("Job Type: ");
                    String jtype = scanner.nextLine();
                    JobListing job = new JobListing(0, cid, jtitle, jdesc, jloc, sal, jtype, LocalDateTime.now());
                    jobListingDAO.insertJobListing(job);
                    System.out.println("Job Posted.");
                    break;
                case 3:
                    System.out.print("First Name: ");
                    String fname = scanner.nextLine();
                    System.out.print("Last Name: ");
                    String lname = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    if (!email.contains("@")) {
                        System.out.println("Invalid email format.");
                        break;
                    }
                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Resume file name: ");
                    String resume = scanner.nextLine();
                    Applicant app = new Applicant(0, fname, lname, email, phone, resume);
                    applicantDAO.insertApplicant(app);
                    System.out.println("Applicant Registered.");
                    break;
                case 4:
                    System.out.print("Applicant ID: ");
                    int aid = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Job ID: ");
                    int jid = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Cover Letter: ");
                    String cover = scanner.nextLine();
                    JobApplication appn = new JobApplication(0, jid, aid, LocalDateTime.now(), cover);
                    jobApplicationDAO.insertJobApplication(appn);
                    System.out.println("Application Submitted.");
                    break;
                case 5:
                    List<JobListing> jobs = jobListingDAO.getAllJobListings();
                    for (JobListing j : jobs) {
                        System.out.println("[" + j.getJobID() + "] " + j.getJobTitle() + " - Rs. " + j.getSalary());
                    }
                    break;
                case 6:
                    System.out.println("Thank you for using CareerHub!");
                    scanner.close();
                    System.exit(0);
            }
        }
    }
}
