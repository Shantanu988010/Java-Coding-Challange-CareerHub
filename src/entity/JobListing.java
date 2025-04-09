package entity;
import java.util.*;
import java.time.LocalDateTime;

public class JobListing {
	private int jobID;
    private int companyID;
    private String jobTitle;
    private String jobDescription;
    private String jobLocation;
    private double salary;
    private String jobType;
    private LocalDateTime postedDate;
    private List<Applicant> applicants = new ArrayList<>();

    public JobListing(int jobID, int companyID, String jobTitle, String jobDescription,
                      String jobLocation, double salary, String jobType, LocalDateTime postedDate) {
        this.jobID = jobID;
        this.companyID = companyID;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.salary = salary;
        this.jobType = jobType;
        this.postedDate = postedDate;
    }

    public void apply(Applicant applicant, String coverLetter) {
        applicants.add(applicant);
        System.out.println("Applicant " + applicant.getFirstName() + " applied successfully.");
    }

    public List<Applicant> getApplicants() {
        return applicants;
    }

    public int getJobID() { return jobID; }
    public String getJobTitle() { return jobTitle; }
    public double getSalary() { return salary; }
    public int getCompanyID() { return companyID; }

}
