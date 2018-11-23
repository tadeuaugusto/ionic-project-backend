package com.tadeu.estudo.springbootionic.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.tadeu.estudo.springbootionic.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preenchePagamentoComBoleto(PagamentoComBoleto pgto, Date instante) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pgto.setDataVencimento(cal.getTime());
	}
}
