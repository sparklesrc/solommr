package com.solommr.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.solommr.model.SignUp.Country;
import com.solommr.util.EnumPais;

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

}
