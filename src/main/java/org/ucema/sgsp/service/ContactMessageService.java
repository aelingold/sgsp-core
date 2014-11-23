package org.ucema.sgsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.ContactMessageDTO;
import org.ucema.sgsp.api.transformation.ContactMessageTransformation;
import org.ucema.sgsp.persistence.ContactMessageDAO;
import org.ucema.sgsp.persistence.model.ContactMessage;

@Service
public class ContactMessageService {

	@Autowired
	private ContactMessageTransformation contactMessageTransformation;

	@Autowired
	private ContactMessageDAO contactMessageDAO;

	@Transactional
	public List<ContactMessageDTO> list() {
		return contactMessageTransformation.transformToApi(contactMessageDAO.findAll());
	}

	@Transactional
	public ContactMessageDTO get(Long id) {
		ContactMessage contactMessage = contactMessageDAO.getOne(id);
		if (contactMessage == null) {
			throw new RuntimeException("contactMessage not found");
		}

		return contactMessageTransformation.transformToApi(contactMessage);
	}
	
	@Transactional
	public ContactMessageDTO saveOrUpdate(ContactMessageDTO contactMessage) {
		ContactMessage response = contactMessageDAO.save(contactMessageTransformation
				.transformToModel(contactMessage));
		return contactMessageTransformation.transformToApi(response);
	}	
}
