package dao;

import java.util.List;

import entity.JobListing;

public interface JobListingDAO {
	void insertJobListing(JobListing job);
    List<JobListing> getAllJobListings();
    List<JobListing> getJobListingsBySalaryRange(double minSalary, double maxSalary);
}

}
