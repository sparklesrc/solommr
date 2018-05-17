package com.solommr.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.solommr.model.ClanDataResponse.Members;
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

	@Override
	public String getPaisByCode(String code) {
		for (EnumPais e : EnumPais.values()) {
			if (code.equals(e.getCodigo()))
				return e.getDescripcion();
		}
		return null;
	}

	@Override
	public Long getTeamLeaderId(List<Members> members) {
		for (Members m : members) {
			if ("Team Leader".equals(m.getType())) {
				return m.getUserId();
			}
		}
		return null;
	}

}
