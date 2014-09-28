package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.ContactMessageDTO;
import org.ucema.sgsp.persistence.model.ContactMessage;

@Component
public class ContactMessageTransformation {

	public List<ContactMessageDTO> transformToApi(
			List<ContactMessage> contactMessages) {
		List<ContactMessageDTO> result = new ArrayList<ContactMessageDTO>();

		for (ContactMessage contactMessage : contactMessages) {
			result.add(transformToApi(contactMessage));
		}

		return result;
	}

	public ContactMessageDTO transformToApi(ContactMessage contactMessage) {
		ContactMessageDTO result = new ContactMessageDTO();

		result.setId(contactMessage.getId());
		result.setEmail(contactMessage.getEmail());
		result.setMessage(contactMessage.getMessage());
		result.setName(contactMessage.getName());

		return result;
	}

	public List<ContactMessage> transformToModel(
			List<ContactMessageDTO> contactMessages) {
		List<ContactMessage> result = new ArrayList<ContactMessage>();

		for (ContactMessageDTO contactMessage : contactMessages) {
			result.add(transformToModel(contactMessage));
		}

		return result;
	}

	public ContactMessage transformToModel(ContactMessageDTO contactMessage) {
		ContactMessage result = new ContactMessage();

		result.setId(contactMessage.getId());
		result.setEmail(contactMessage.getEmail());
		result.setMessage(contactMessage.getMessage());
		result.setName(contactMessage.getName());

		return result;
	}
}
