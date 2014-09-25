package org.ucema.sgsp.job;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.persistence.model.QuoteStatusType;
import org.ucema.sgsp.service.QuoteService;


@Component
public class QuoteValidationJob {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(QuoteValidationJob.class);
	
	@Autowired
	private QuoteService quoteService;
	
	@Scheduled(fixedRate = 30000)
	public void validateQuotes() {
		LOGGER.info("validating quotes dates");
		
		List<QuoteDTO> quotes = quoteService.list(QuoteStatusType.REPLIED);
		quotes.forEach(q -> {
			Date validDateUntil = q.getValidDateUntil();
			if (validDateUntil != null){
				DateTime validDateTimeUntil = new DateTime(q.getValidDateUntil().getTime());
				DateTime now = new DateTime().withTimeAtStartOfDay();
				if (now.isAfter(validDateTimeUntil)){
					q.setStatusType(QuoteStatusType.INVALID.name());
					quoteService.saveOrUpdate(q);
				}
			}
		});
	}
}
