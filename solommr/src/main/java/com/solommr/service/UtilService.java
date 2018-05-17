package com.solommr.service;

import java.util.List;
import com.solommr.model.ClanDataResponse.Members;
import com.solommr.model.SignUp.Country;

public interface UtilService {

	List<Country> getCountries();

	List<String> getRolesByCode(String... roles);

	String getPaisByCode(String code);

	Long getTeamLeaderId(List<Members> members);
}
