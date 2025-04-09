package entity;

import java.time.LocalDateTime;

public class JobApplication {
	private int applicationID;
    private int jobID;
    private int applicantID;
    private LocalDateTime applicationDate;
    private String coverLetter;

    public JobApplication(int applicationID, int jobID, int applicantID, LocalDateTime applicationDate, String coverLetter) {
        this.applicationID = applicationID;
        this.jobID = jobID;
        this.applicantID = applicantID;
        this.applicationDate = applicationDate;
        this.coverLetter = coverLetter;
    }

    public int getApplicationID() { return applicationID; }
    public int getJobID() { return jobID; }
    public int getApplicantID() { return applicantID; }
    public LocalDateTime getApplicationDate() { return applicationDate; }
    public String getCoverLetter() { return coverLetter; }

}
