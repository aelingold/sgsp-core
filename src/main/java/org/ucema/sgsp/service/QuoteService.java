package org.ucema.sgsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.api.transformation.QuoteTransformation;
import org.ucema.sgsp.persistence.QuoteDAO;
import org.ucema.sgsp.persistence.model.Quote;
import org.ucema.sgsp.persistence.model.QuoteStatusType;

@Service
public class QuoteService {

	@Autowired
	private QuoteTransformation quoteTransformation;
	@Autowired
	private QuoteDAO quoteDAO;
	@Autowired
	private UserService userService;

	@Transactional
	public List<QuoteDTO> list(Long userId, QuoteStatusType statusType) {
		return list(userService.get(userId).getEmail(), statusType);
	}

	@Transactional
	public List<QuoteDTO> list(String username, QuoteStatusType statusType) {
		return quoteTransformation.transformToApi(quoteDAO
				.findByStatusTypeAndUser_Email(statusType, username));
	}

	@Transactional
	public List<QuoteDTO> list(String username) {
		return quoteTransformation.transformToApi(quoteDAO
				.findByUser_Email(username));
	}
	
	@Transactional
	public List<QuoteDTO> list(List<Long> ids) {
		return quoteTransformation.transformToApi(quoteDAO
				.findAll(ids));
	}	

	@Transactional
	public List<QuoteDTO> list() {
		return quoteTransformation.transformToApi(quoteDAO.findAll());
	}

	@Transactional
	public QuoteDTO saveOrUpdate(QuoteDTO quote) {
		Quote response = quoteDAO.save(quoteTransformation
				.transformToModel(quote));
		quote.setId(response.getId());
		return quote;
	}

	@Transactional
	public void update(QuoteDTO quoteDTO) {
		Quote quote = quoteDAO.getOne(quoteDTO.getId());
		quoteDAO.save(quoteTransformation.updateFields(quote, quoteDTO));
	}

	@Transactional
	public void delete(QuoteDTO quote) {
		quoteDAO.delete(quoteTransformation.transformToModel(quote));
	}

	@Transactional
	public void delete(Long id) {
		Quote quote = quoteDAO.getOne(id);
		if (quote == null) {
			throw new RuntimeException("quote not found");
		}
		quoteDAO.delete(quote);
	}

	@Transactional
	public QuoteDTO get(Long id) {
		Quote quote = quoteDAO.getOne(id);
		if (quote == null) {
			throw new RuntimeException("quote not found");
		}
		return quoteTransformation.transformToApi(quote);
	}
}
