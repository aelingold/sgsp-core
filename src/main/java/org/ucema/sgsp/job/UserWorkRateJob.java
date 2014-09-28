package org.ucema.sgsp.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.UserWorkRateDTO;
import org.ucema.sgsp.persistence.model.UserWorkRateRatingType;
import org.ucema.sgsp.persistence.model.UserWorkRateStatusType;
import org.ucema.sgsp.service.UserWorkRateService;
import org.ucema.sgsp.service.UserWorkRateSummarizeService;

@Component
public class UserWorkRateJob {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserWorkRateJob.class);

	@Autowired
	private UserWorkRateService userWorkRateService;
	@Autowired
	private UserWorkRateSummarizeService userWorkRateSummarizeService;

	@Scheduled(fixedRate = 30000)
	public void processWorkRates() {
		LOGGER.info("Summarizing pending work rates");

		List<UserWorkRateDTO> userWorkRates = userWorkRateService
				.findByStatusTypeAndSummarizedAndWorkCompleted(
						UserWorkRateStatusType.DONE, false, true);
		
		userWorkRates.forEach(uwr -> {
			String username = uwr.getQuoteUsername();
			userWorkRateSummarizeService.increment(username,
					UserWorkRateRatingType.valueOf(uwr.getRatingType()));
			userWorkRateService.summarized(uwr.getId(), true);
		});
	}
}
