package org.ucema.sgsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.api.transformation.QuoteTransformation;
import org.ucema.sgsp.persistence.QuoteDAO;
import org.ucema.sgsp.persistence.model.Quote;

@Service
public class QuoteService {

	@Autowired
	private QuoteTransformation quoteTransformation;
	
	@Autowired
	private QuoteDAO quoteDAO;

	@Transactional
	public List<QuoteDTO> list() {
		return quoteTransformation.transformToApi(quoteDAO.findAll());
	}

	@Transactional
	public void saveOrUpdate(QuoteDTO quote) {
		quoteDAO.save(quoteTransformation.transformToModel(quote));
	}

	@Transactional
	public void delete(QuoteDTO quote) {
		quoteDAO.delete(quoteTransformation.transformToModel(quote));
	}
	
	@Transactional
	public void delete(Long id) {
		Quote quote = quoteDAO.getOne(id);
		if (quote == null){
			throw new RuntimeException("quote not found");
		}		
		quoteDAO.delete(quote);
	}	
	
	@Transactional
	public QuoteDTO get(Long id){
		Quote quote = quoteDAO.getOne(id);
		if (quote == null){
			throw new RuntimeException("quote not found");
		}		
		return quoteTransformation.transformToApi(quote);
	}	
}
