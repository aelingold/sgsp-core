package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.QuoteQuestionReplyDTO;
import org.ucema.sgsp.persistence.model.QuoteQuestion;
import org.ucema.sgsp.persistence.model.QuoteQuestionReply;
import org.ucema.sgsp.persistence.model.QuoteQuestionReplyStatusType;

@Component
public class QuoteQuestionReplyTransformation {

	public List<QuoteQuestionReplyDTO> transformToApi(
			List<QuoteQuestionReply> quoteQuestionReplies) {
		List<QuoteQuestionReplyDTO> result = new ArrayList<QuoteQuestionReplyDTO>();

		for (QuoteQuestionReply quoteQuestionReply : quoteQuestionReplies) {
			result.add(transformToApi(quoteQuestionReply));
		}

		return result;
	}

	public List<QuoteQuestionReply> transformToModel(
			List<QuoteQuestionReplyDTO> quoteQuestionReplies) {
		List<QuoteQuestionReply> result = new ArrayList<QuoteQuestionReply>();

		for (QuoteQuestionReplyDTO quoteQuestionReply : quoteQuestionReplies) {
			result.add(transformToModel(quoteQuestionReply));
		}

		return result;
	}

	public QuoteQuestionReplyDTO transformToApi(
			QuoteQuestionReply quoteQuestionReply) {
		QuoteQuestionReplyDTO result = new QuoteQuestionReplyDTO();

		result.setId(quoteQuestionReply.getId());
		result.setDescription(quoteQuestionReply.getDescription());
		result.setQuoteQuestionId(quoteQuestionReply.getQuoteQuestion().getId());
		result.setStatusType(quoteQuestionReply.getStatusType().name());
		result.setQuoteId(quoteQuestionReply.getQuoteQuestion().getQuote()
				.getId());
		result.setQuoteQuestionDescription(quoteQuestionReply
				.getQuoteQuestion().getDescription());

		return result;
	}

	public QuoteQuestionReply transformToModel(
			QuoteQuestionReplyDTO quoteQuestionReply) {
		QuoteQuestionReply result = new QuoteQuestionReply();

		result.setId(quoteQuestionReply.getId());
		result.setDescription(quoteQuestionReply.getDescription());
		result.setQuoteQuestion(new QuoteQuestion(quoteQuestionReply
				.getQuoteQuestionId()));
		result.setStatusType(QuoteQuestionReplyStatusType
				.valueOf(quoteQuestionReply.getStatusType()));

		return result;
	}

	public QuoteQuestionReply updateFields(
			QuoteQuestionReply quoteQuestionReply,
			QuoteQuestionReplyDTO quoteQuestionReplyDTO) {

		quoteQuestionReply.setDescription(quoteQuestionReplyDTO
				.getDescription());
		quoteQuestionReply.setStatusType(QuoteQuestionReplyStatusType.DONE);

		return quoteQuestionReply;
	}
}
