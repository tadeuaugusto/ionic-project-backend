package com.tadeu.estudo.springbootionic.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.tadeu.estudo.springbootionic.domain.Cliente;
import com.tadeu.estudo.springbootionic.domain.enums.TipoCliente;
import com.tadeu.estudo.springbootionic.dto.ClienteNewDTO;
import com.tadeu.estudo.springbootionic.repositories.ClienteRepository;
import com.tadeu.estudo.springbootionic.resources.exception.FieldMessage;
import com.tadeu.estudo.springbootionic.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext ctx) {
		// TODO Auto-generated method stub
		List<FieldMessage> list = new ArrayList<>();
		
		if (dto.getTipoCliente().equals(TipoCliente.PF.getCod()) && !BR.isValidCPF(dto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CPF Invalido"));
		}
		
		if (dto.getTipoCliente().equals(TipoCliente.PJ.getCod()) && !BR.isValidCNPJ(dto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CNPJ Invalido"));
		}
		
		Cliente aux = clienteRepository.findByEmail(dto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email ja existente na base de dados"));
		}
		
		for (FieldMessage e : list) {
			ctx.disableDefaultConstraintViolation();
			ctx.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
				.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
