package org.ucema.sgsp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.QuoteQuestionReplyDTO;
import org.ucema.sgsp.api.transformation.QuoteQuestionReplyTransformation;
import org.ucema.sgsp.persistence.QuoteQuestionReplyDAO;
import org.ucema.sgsp.persistence.model.QuoteQuestionReply;
import org.ucema.sgsp.persistence.model.QuoteQuestionReplyStatusType;

@Service
public class QuoteQuestionReplyService {

	@Autowired
	private QuoteQuestionReplyTransformation quoteQuestionReplyTransformation;
	@Autowired
	private QuoteQuestionReplyDAO quoteQuestionReplyDAO;
	@Autowired
	private MailService mailService;

	@Transactional
	public List<QuoteQuestionReplyDTO> findByQuoteQuestion_Quote_User_Email(
			String username) {
		return quoteQuestionReplyTransformation
				.transformToApi(quoteQuestionReplyDAO
						.findByQuoteQuestion_Quote_User_Email(username));
	}

	@Transactional
	public List<QuoteQuestionReplyDTO> findByStatusTypeAndQuoteQuestion_Quote_User_Email(
			QuoteQuestionReplyStatusType statusType, String username) {
		return quoteQuestionReplyTransformation
				.transformToApi(quoteQuestionReplyDAO
						.findByStatusTypeAndQuoteQuestion_Quote_User_Email(
								statusType, username));
	}

	@Transactional
	public List<QuoteQuestionReplyDTO> list() {
		return quoteQuestionReplyTransformation
				.transformToApi(quoteQuestionReplyDAO.findAll());
	}

	@Transactional
	public void quoteQuestionReply(QuoteQuestionReplyDTO quoteQuestionReplyDTO) {

		QuoteQuestionReply quoteQuestionReply = quoteQuestionReplyDAO
				.getOne(quoteQuestionReplyDTO.getId());
		quoteQuestionReplyDAO.save(quoteQuestionReplyTransformation
				.updateFields(quoteQuestionReply, quoteQuestionReplyDTO));
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("firstName", quoteQuestionReply.getQuoteQuestion().getQuote()
				.getUser().getFirstName());
		model.put("lastName", quoteQuestionReply.getQuoteQuestion().getQuote()
				.getUser().getLastName());

		mailService.save(quoteQuestionReply.getQuoteQuestion().getQuote()
				.getOrder().getUser().getEmail(), MailService.FROM_EMAIL,
				"Respuesta recibida", "mail/sendQuestionResponse.ftl", model);
	}

	@Transactional
	public QuoteQuestionReplyDTO saveOrUpdate(
			QuoteQuestionReplyDTO quoteQuestionReply) {
		QuoteQuestionReply response = quoteQuestionReplyDAO
				.save(quoteQuestionReplyTransformation
						.transformToModel(quoteQuestionReply));
		quoteQuestionReply.setId(response.getId());
		return quoteQuestionReply;
	}

	@Transactional
	public void delete(QuoteQuestionReplyDTO quoteQuestionReply) {
		quoteQuestionReplyDAO.delete(quoteQuestionReplyTransformation
				.transformToModel(quoteQuestionReply));
	}

	@Transactional
	public void delete(Long id) {
		QuoteQuestionReply quoteQuestionReply = quoteQuestionReplyDAO
				.getOne(id);
		if (quoteQuestionReply == null) {
			throw new RuntimeException("quoteQuestionReply not found");
		}
		quoteQuestionReplyDAO.delete(quoteQuestionReply);
	}

	@Transactional
	public QuoteQuestionReplyDTO get(Long id) {
		QuoteQuestionReply quoteQuestionReply = quoteQuestionReplyDAO
				.getOne(id);
		if (quoteQuestionReply == null) {
			throw new RuntimeException("quoteQuestionReply not found");
		}
		return quoteQuestionReplyTransformation
				.transformToApi(quoteQuestionReply);
	}
}
