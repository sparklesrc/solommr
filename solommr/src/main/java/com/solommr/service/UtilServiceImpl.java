package com.solommr.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.solommr.model.SignUp.Country;
import com.solommr.util.EnumPais;
import com.solommr.util.EnumRolesCSGO;

@Service
public class UtilServiceImpl implements UtilService {

	@Override
	public List<Country> getCountries() {
		List<Country> countries = new ArrayList();
		for (EnumPais e : EnumPais.values()) {
			Country pais = new Country();
			pais.setCodigo(e.getCodigo());
			pais.setDescripcion(e.getDescripcion());
			countries.add(pais);
		}
		return countries;
	}

	@Override
	public List<String> getRolesByCode(String... roles) {
		List<String> desc = new ArrayList<String>();
		for (String rol : roles) {
			try {
				Integer code = Integer.parseInt(rol);
				for (EnumRolesCSGO e : EnumRolesCSGO.values()) {
					if (e.getCodigo() == code) {
						desc.add(e.getDescripcion());
						break;
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("Error formatting role code...");
			}
		}
		return desc;
	}

}
