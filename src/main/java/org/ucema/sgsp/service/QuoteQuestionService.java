package org.ucema.sgsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.QuoteQuestionDTO;
import org.ucema.sgsp.api.transformation.QuoteQuestionTransformation;
import org.ucema.sgsp.persistence.QuoteQuestionDAO;
import org.ucema.sgsp.persistence.model.QuoteQuestion;

@Service
public class QuoteQuestionService {

	@Autowired
	private QuoteQuestionTransformation quoteQuestionTransformation;
	
	@Autowired
	private QuoteQuestionDAO quoteQuestionDAO;

	@Transactional
	public List<QuoteQuestionDTO> list() {
		return quoteQuestionTransformation.transformToApi(quoteQuestionDAO.findAll());
	}

	@Transactional
	public QuoteQuestionDTO saveOrUpdate(QuoteQuestionDTO quoteQuestion) {
		QuoteQuestion response = quoteQuestionDAO.save(quoteQuestionTransformation.transformToModel(quoteQuestion));
		quoteQuestion.setId(response.getId());
		return quoteQuestion;
	}

	@Transactional
	public void delete(QuoteQuestionDTO quoteQuestion) {
		quoteQuestionDAO.delete(quoteQuestionTransformation.transformToModel(quoteQuestion));
	}
	
	@Transactional
	public void delete(Long id) {
		QuoteQuestion quoteQuestion = quoteQuestionDAO.getOne(id);
		if (quoteQuestion == null){
			throw new RuntimeException("quoteQuestion not found");
		}		
		quoteQuestionDAO.delete(quoteQuestion);
	}	
	
	@Transactional
	public QuoteQuestionDTO get(Long id){
		QuoteQuestion quoteQuestion = quoteQuestionDAO.getOne(id);
		if (quoteQuestion == null){
			throw new RuntimeException("quoteQuestion not found");
		}		
		return quoteQuestionTransformation.transformToApi(quoteQuestion);
	}	
}
