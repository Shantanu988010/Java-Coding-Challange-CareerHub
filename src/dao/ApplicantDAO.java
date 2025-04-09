package dao;
import java.util.List;

import entity.Applicant;

public interface ApplicantDAO {
	void insertApplicant(Applicant applicant);
    List<Applicant> getAllApplicants();

}
