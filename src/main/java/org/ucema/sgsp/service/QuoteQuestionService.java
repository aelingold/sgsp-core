package org.ucema.sgsp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.api.dto.QuoteQuestionDTO;
import org.ucema.sgsp.api.dto.QuoteQuestionReplyDTO;
import org.ucema.sgsp.api.transformation.QuoteQuestionTransformation;
import org.ucema.sgsp.persistence.QuoteQuestionDAO;
import org.ucema.sgsp.persistence.model.QuoteQuestion;
import org.ucema.sgsp.persistence.model.QuoteQuestionReplyStatusType;

@Service
public class QuoteQuestionService {

	@Autowired
	private QuoteQuestionTransformation quoteQuestionTransformation;
	@Autowired
	private QuoteQuestionDAO quoteQuestionDAO;
	@Autowired
	private QuoteQuestionReplyService quoteQuestionReplyService;
	@Autowired
	private MailService mailService;
	@Autowired
	private QuoteService quoteService;

	@Transactional
	public List<QuoteQuestionDTO> list() {
		return quoteQuestionTransformation.transformToApi(quoteQuestionDAO
				.findAll());
	}

	@Transactional
	public QuoteQuestionDTO save(QuoteQuestionDTO quoteQuestion) {
		QuoteQuestion response = quoteQuestionDAO
				.save(quoteQuestionTransformation
						.transformToModel(quoteQuestion));

		QuoteQuestionReplyDTO quoteQuestionReply = new QuoteQuestionReplyDTO();
		quoteQuestionReply.setQuoteQuestionId(response.getId());
		quoteQuestionReply.setStatusType(QuoteQuestionReplyStatusType.PENDING
				.name());
		quoteQuestionReplyService.saveOrUpdate(quoteQuestionReply);

		QuoteDTO quoteDTO = quoteService.get(response.getQuote().getId());

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("firstName", quoteDTO.getOrder().getFirstName());
		model.put("lastName", quoteDTO.getOrder().getLastName());

		mailService.sendEmail(quoteDTO.getUsername(),
				MailService.FROM_EMAIL, "Nueva pregunta",
				"mail/sendQuestion.ftl", model);

		quoteQuestion.setId(response.getId());
		return quoteQuestion;
	}

	@Transactional
	public QuoteQuestionDTO saveOrUpdate(QuoteQuestionDTO quoteQuestion) {
		QuoteQuestion response = quoteQuestionDAO
				.save(quoteQuestionTransformation
						.transformToModel(quoteQuestion));
		quoteQuestion.setId(response.getId());
		return quoteQuestion;
	}

	@Transactional
	public void delete(QuoteQuestionDTO quoteQuestion) {
		quoteQuestionDAO.delete(quoteQuestionTransformation
				.transformToModel(quoteQuestion));
	}

	@Transactional
	public void delete(Long id) {
		QuoteQuestion quoteQuestion = quoteQuestionDAO.getOne(id);
		if (quoteQuestion == null) {
			throw new RuntimeException("quoteQuestion not found");
		}
		quoteQuestionDAO.delete(quoteQuestion);
	}

	@Transactional
	public QuoteQuestionDTO get(Long id) {
		QuoteQuestion quoteQuestion = quoteQuestionDAO.getOne(id);
		if (quoteQuestion == null) {
			throw new RuntimeException("quoteQuestion not found");
		}
		return quoteQuestionTransformation.transformToApi(quoteQuestion);
	}
}
