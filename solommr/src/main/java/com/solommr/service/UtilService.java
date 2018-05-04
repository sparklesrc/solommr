package com.solommr.service;

import java.util.List;
import com.solommr.model.SignUp.Country;
import com.solommr.util.EnumRolesCSGO;

public interface UtilService {

	List<Country> getCountries();

	List<String> getRolesByCode(String... roles);

	String getPaisByCode(String code);
}
