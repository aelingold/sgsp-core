package org.ucema.sgsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.QuoteQuestionReplyDTO;
import org.ucema.sgsp.api.transformation.QuoteQuestionReplyTransformation;
import org.ucema.sgsp.persistence.QuoteQuestionReplyDAO;
import org.ucema.sgsp.persistence.model.QuoteQuestionReply;

@Service
public class QuoteQuestionReplyService {

	@Autowired
	private QuoteQuestionReplyTransformation quoteQuestionReplyTransformation;
	
	@Autowired
	private QuoteQuestionReplyDAO quoteQuestionReplyDAO;

	@Transactional
	public List<QuoteQuestionReplyDTO> list() {
		return quoteQuestionReplyTransformation.transformToApi(quoteQuestionReplyDAO.findAll());
	}

	@Transactional
	public QuoteQuestionReplyDTO saveOrUpdate(QuoteQuestionReplyDTO quoteQuestionReply) {
		QuoteQuestionReply response = quoteQuestionReplyDAO.save(quoteQuestionReplyTransformation.transformToModel(quoteQuestionReply));
		quoteQuestionReply.setId(response.getId());
		return quoteQuestionReply;
	}

	@Transactional
	public void delete(QuoteQuestionReplyDTO quoteQuestionReply) {
		quoteQuestionReplyDAO.delete(quoteQuestionReplyTransformation.transformToModel(quoteQuestionReply));
	}
	
	@Transactional
	public void delete(Long id) {
		QuoteQuestionReply quoteQuestionReply = quoteQuestionReplyDAO.getOne(id);
		if (quoteQuestionReply == null){
			throw new RuntimeException("quoteQuestionReply not found");
		}		
		quoteQuestionReplyDAO.delete(quoteQuestionReply);
	}	
	
	@Transactional
	public QuoteQuestionReplyDTO get(Long id){
		QuoteQuestionReply quoteQuestionReply = quoteQuestionReplyDAO.getOne(id);
		if (quoteQuestionReply == null){
			throw new RuntimeException("quoteQuestionReply not found");
		}		
		return quoteQuestionReplyTransformation.transformToApi(quoteQuestionReply);
	}	
}
