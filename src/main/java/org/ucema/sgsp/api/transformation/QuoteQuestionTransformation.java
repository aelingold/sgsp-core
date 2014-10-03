package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.QuoteQuestionDTO;
import org.ucema.sgsp.persistence.model.Quote;
import org.ucema.sgsp.persistence.model.QuoteQuestion;

@Component
public class QuoteQuestionTransformation {

	@Autowired
	private QuoteQuestionReplyTransformation quoteQuestionReplyTransformation;

	public List<QuoteQuestionDTO> transformToApi(
			List<QuoteQuestion> quoteQuestions) {
		List<QuoteQuestionDTO> result = new ArrayList<QuoteQuestionDTO>();

		for (QuoteQuestion quoteQuestion : quoteQuestions) {
			result.add(transformToApi(quoteQuestion));
		}

		return result;
	}

	public List<QuoteQuestion> transformToModel(
			List<QuoteQuestionDTO> quoteQuestions) {
		List<QuoteQuestion> result = new ArrayList<QuoteQuestion>();

		for (QuoteQuestionDTO quoteQuestion : quoteQuestions) {
			result.add(transformToModel(quoteQuestion));
		}

		return result;
	}

	public QuoteQuestionDTO transformToApi(QuoteQuestion quoteQuestion) {
		QuoteQuestionDTO result = new QuoteQuestionDTO();

		result.setId(quoteQuestion.getId());
		result.setDescription(quoteQuestion.getDescription());
		result.setQuoteId(quoteQuestion.getQuote().getId());
		result.setCreatedAt(quoteQuestion.getCreatedAt());

		if (quoteQuestion.getQuoteQuestionReply() != null) {
			result.setQuoteQuestionReply(quoteQuestionReplyTransformation
					.transformToApi(quoteQuestion.getQuoteQuestionReply()));
			result.setQuoteQuestionReplyId(quoteQuestion
					.getQuoteQuestionReply().getId());
		}

		return result;
	}

	public QuoteQuestion transformToModel(QuoteQuestionDTO quoteQuestion) {
		QuoteQuestion result = new QuoteQuestion();

		result.setId(quoteQuestion.getId());
		result.setDescription(quoteQuestion.getDescription());
		result.setQuote(new Quote(quoteQuestion.getQuoteId()));

		if (quoteQuestion.getQuoteQuestionReply() != null) {
			result.setQuoteQuestionReply(quoteQuestionReplyTransformation
					.transformToModel(quoteQuestion.getQuoteQuestionReply()));
		}

		return result;
	}
}
