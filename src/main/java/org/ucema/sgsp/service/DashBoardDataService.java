package org.ucema.sgsp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.CityDTO;
import org.ucema.sgsp.api.dto.DashBoardConfigDTO;
import org.ucema.sgsp.api.dto.DashBoardUserDTO;
import org.ucema.sgsp.api.dto.OrderDTO;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.api.dto.StateDTO;
import org.ucema.sgsp.api.dto.UserWorkRateDTO;
import org.ucema.sgsp.persistence.model.QuoteStatusType;
import org.ucema.sgsp.persistence.model.UserWorkRateStatusType;

@Service
public class DashBoardDataService {

	@Autowired
	private UserService userService;
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private UserWorkRateService userWorkRateService;
	@Autowired
	private StateService stateService;
	@Autowired
	private CityService cityService;
	@Autowired
	private DashBoardUserService dashBoardUserService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private WorkAreaQuestionService workAreaQuestionService;
	@Autowired
	private WorkAreaItemService workAreaItemService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private UserWorkZoneService userWorkZoneService;

	@Transactional
	public Map<String, Object> data(String username, String tabToShow,
			String countryCode) {

		Map<String, Object> map = new HashMap<String, Object>();

		DashBoardUserDTO user = dashBoardUserService.getDashBoardUser(username);

		map.put("user", user);

		List<OrderDTO> orders = orderService.list(username);
		map.put("orders", orders);

		List<Long> quoteIds = new ArrayList<>();
		orders.forEach(o -> {
			quoteIds.addAll(o.getQuoteIds());
		});

		List<QuoteDTO> allQuotes = quoteService.list(quoteIds);

		map.put("repliedQuotes", repliedQuotes(allQuotes));

		map.put("userWorkRates",
				userWorkRateService.userWorkRatesMap(allQuotes));

		map.put("userWorkRate", new UserWorkRateDTO());

		map.put("pendingUserWorkRates", userWorkRateService
				.findByUser_EmailAndStatusType(username,
						UserWorkRateStatusType.PENDING));

		map.put("doneUserWorkRates", userWorkRateService
				.findByQuote_User_EmailAndStatusType(username,
						UserWorkRateStatusType.DONE));

		map.put("workAreaQuestions", workAreaQuestionService.list());

		map.put("workAreaItems", workAreaItemService.list());

		map.put("tabToShow", tabToShow);

		map.put("pendingQuotes",
				quoteService.list(username, QuoteStatusType.PENDING));

		map.put("quote", new QuoteDTO());

		map.put("currency", currencyService.findByCountryCode(countryCode));

		List<CityDTO> cities = cityService.list();
		map.put("cities", cities);

		List<StateDTO> states = stateService.list();
		map.put("states", states);

		DashBoardConfigDTO dashBoardConfig = new DashBoardConfigDTO();
		userWorkZoneService.list(username).forEach(uwz -> {
			dashBoardConfig.getCityCodes().add(uwz.getCityCode());
		});

		map.put("config", dashBoardConfig);
		map.put("configMap", stateService.getConfigMap(states, cities));

		return map;
	}

	public List<QuoteDTO> repliedQuotes(List<QuoteDTO> allQuotes) {
		return allQuotes
				.stream()
				.filter(q -> q.getStatusType().equals(
						QuoteStatusType.REPLIED.name())
						|| q.getStatusType().equals(
								QuoteStatusType.ACCEPTED.name()))
				.collect(Collectors.toList());
	}
}
