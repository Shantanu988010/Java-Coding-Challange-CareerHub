package dao;
import java.util.List;

import entity.Company;

public interface CompanyDAO {
	void insertCompany(Company company);
    List<Company> getAllCompanies();

}
