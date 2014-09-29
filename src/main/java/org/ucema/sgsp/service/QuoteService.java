package org.ucema.sgsp.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.api.dto.ReportWorkAreaDTO;
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
	public void updateStatus(Long quoteId, QuoteStatusType statusType) {
		updateStatus(Arrays.asList(quoteId), statusType);
	}

	@Transactional
	public void updateStatus(List<Long> quoteIds, QuoteStatusType statusType) {

		quoteIds.forEach(id -> {
			Quote quote = quoteDAO.getOne(id);
			quote.setStatusType(statusType);
			quoteDAO.save(quote);
		});
	}

	@Transactional
	public List<QuoteDTO> findByOrder_Id(List<Long> orderIds) {
		return quoteTransformation.transformToApi(quoteDAO
				.findByOrder_Id(orderIds));
	}

	@Transactional
	public List<QuoteDTO> findByOrder_Id(Long orderId) {
		return quoteTransformation.transformToApi(quoteDAO
				.findByOrder_Id(orderId));
	}

	@Transactional
	public List<QuoteDTO> list(Long userId, QuoteStatusType statusType) {
		return list(userService.get(userId).getEmail(), statusType);
	}

	@Transactional
	public List<QuoteDTO> list(QuoteStatusType statusType) {
		return quoteTransformation.transformToApi(quoteDAO
				.findByStatusType(statusType));
	}

	@Transactional
	public List<QuoteDTO> list(Set<QuoteStatusType> statusTypes) {
		return quoteTransformation.transformToApi(quoteDAO
				.findByStatusType(statusTypes));
	}

	@Transactional
	public Collection<ReportWorkAreaDTO> quotesServices(
			Set<QuoteStatusType> statusTypes) {

		List<QuoteDTO> quotes = list(statusTypes);
		return getReportWorkAreaMap(quotes);
	}

	private Collection<ReportWorkAreaDTO> getReportWorkAreaMap(
			List<QuoteDTO> quotes) {

		Map<DateTime, ReportWorkAreaDTO> response = new HashMap<DateTime, ReportWorkAreaDTO>();

		for (QuoteDTO quote : quotes) {
			String workAreaDescription = quote.getOrder()
					.getWorkAreaDescription();

			Date updatedAt = quote.getUpdatedAt();
			DateTime updatedAtCustom = new DateTime(updatedAt);
			updatedAtCustom = new DateTime(updatedAtCustom.getYear(),
					updatedAtCustom.getMonthOfYear(),
					updatedAtCustom.getDayOfMonth(), 0, 0, 0, 0);

			if (response.get(updatedAtCustom) == null) {

				ReportWorkAreaDTO reportWorkAreaDTO = new ReportWorkAreaDTO();
				reportWorkAreaDTO.setDate(updatedAtCustom
						.toString(DateTimeFormat.forPattern("dd-MM-yyyy")));
				reportWorkAreaDTO.setWorkAreaDescription(workAreaDescription);
				reportWorkAreaDTO.setCount(reportWorkAreaDTO.getCount() + 1);

				response.put(updatedAtCustom, reportWorkAreaDTO);

			} else {

				ReportWorkAreaDTO reportWorkAreaDTO = response
						.get(updatedAtCustom);
				reportWorkAreaDTO.setCount(reportWorkAreaDTO.getCount() + 1);

				response.put(updatedAtCustom, reportWorkAreaDTO);
			}
		}

		return response.values();
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
		return quoteTransformation.transformToApi(quoteDAO.findAll(ids));
	}

	@Transactional
	public List<QuoteDTO> list() {
		return quoteTransformation.transformToApi(quoteDAO.findAll());
	}

	@Transactional
	public void save(Long quoteId) {
		QuoteDTO quote = get(quoteId);
		quote.setStatusType(QuoteStatusType.ACCEPTED.name());

		saveOrUpdate(quote);
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
