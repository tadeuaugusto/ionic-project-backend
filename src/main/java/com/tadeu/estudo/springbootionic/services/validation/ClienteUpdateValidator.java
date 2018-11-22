package com.tadeu.estudo.springbootionic.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.tadeu.estudo.springbootionic.domain.Cliente;
import com.tadeu.estudo.springbootionic.dto.ClienteDTO;
import com.tadeu.estudo.springbootionic.repositories.ClienteRepository;
import com.tadeu.estudo.springbootionic.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>{

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public boolean isValid(ClienteDTO dto, ConstraintValidatorContext ctx) {

		@SuppressWarnings("unchecked")
		Map<String, String>  map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
			
		Cliente aux = clienteRepository.findByEmail(dto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Nao eh possivel atualizar o Cliente. Email ja existente na base de dados"));
		}
		
		for (FieldMessage e : list) {
			ctx.disableDefaultConstraintViolation();
			ctx.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
				.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
