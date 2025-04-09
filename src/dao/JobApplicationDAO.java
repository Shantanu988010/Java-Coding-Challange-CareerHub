package dao;

import java.util.List;

import entity.JobApplication;

public interface JobApplicationDAO {
	void insertJobApplication(JobApplication application);
    List<JobApplication> getApplicationsByJobID(int jobID);

}
