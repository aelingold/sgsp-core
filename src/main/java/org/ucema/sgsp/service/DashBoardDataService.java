package org.ucema.sgsp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.CityDTO;
import org.ucema.sgsp.api.dto.DashBoardConfigDTO;
import org.ucema.sgsp.api.dto.DashBoardUserDTO;
import org.ucema.sgsp.api.dto.OrderDTO;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.api.dto.StateDTO;
import org.ucema.sgsp.api.dto.UserWorkRateDTO;
import org.ucema.sgsp.persistence.model.QuoteStatusType;
import org.ucema.sgsp.security.model.CustomUserDetails;

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
	@Autowired
	private ConnectionRepository connectionRepository;
	@Resource
	private Environment env;
	@Autowired
	private UserWorkRateSummarizeService userWorkRateSummarizeService;

	@Transactional
	public Map<String, Object> data(String username, String tabToShow,
			CustomUserDetails userDetails) {

		Map<String, Object> map = new HashMap<String, Object>();

		DashBoardUserDTO user = dashBoardUserService
				.getDashBoardUser(userDetails);

		map.put("user", user);

		map.put("tabToShow", tabToShow);

		List<OrderDTO> orders = orderService.list(username);
		map.put("orders", orders);

		List<Long> quoteIds = new ArrayList<>();
		orders.forEach(o -> {
			quoteIds.addAll(o.getQuoteIds());
		});

		List<QuoteDTO> allQuotes = quoteService.list(quoteIds);
		Set<String> usernames = allQuotes.stream().map(aq -> aq.getUsername())
				.collect(Collectors.toSet());		

		map.put("quotes", quotes(username, allQuotes));

		map.put("quote", new QuoteDTO());

		map.put("userWorkRatesQtyMap", userWorkRateSummarizeService
				.userWorkRateSummarizesMap(usernames));

		map.put("userWorkRate", new UserWorkRateDTO());

		map.put("userWorkRates", userWorkRateService.findByUser_Email(username));

		map.put("workAreaQuestions", workAreaQuestionService.list());

		map.put("workAreaItems", workAreaItemService.list());

		String countryCode = userDetails.getCountryCode();
		map.put("currency", currencyService.findByCountryCode(countryCode));

		List<CityDTO> cities = cityService.list();
		map.put("cities", cities);

		List<StateDTO> states = stateService.list();
		map.put("states", states);

		DashBoardConfigDTO dashBoardConfig = new DashBoardConfigDTO();
		if (userDetails.getCityCodes() != null) {
			dashBoardConfig.getCityCodes().addAll(userDetails.getCityCodes());
		}
		map.put("config", dashBoardConfig);

		List<Connection<?>> connections = connectionRepository
				.findConnections("facebook");
		if (!connections.isEmpty()) {
			map.put("connections", connections);
		}

		return map;
	}

	public List<QuoteDTO> quotes(String username, List<QuoteDTO> allQuotes) {

		List<QuoteDTO> repliedQuotes = repliedQuotes(allQuotes);
		Map<Long, List<QuoteDTO>> repliedQuotesGrouped = repliedQuotes.stream()
				.collect(Collectors.groupingBy(QuoteDTO::getOrderId));

		List<QuoteDTO> repliedQuotesFiltered = new ArrayList<QuoteDTO>();

		repliedQuotesGrouped.forEach((k, v) -> {
			if (repliedQuotesGrouped.get(k).size() >= Integer.valueOf(env
					.getProperty("minimum.replied.quotes", "5"))) {
				repliedQuotesFiltered.addAll(repliedQuotesGrouped.get(k));
			}
		});

		List<QuoteDTO> quotes = new ArrayList<QuoteDTO>();

		quotes.addAll(repliedQuotesFiltered);

		quotes.addAll(quoteService.list(username));

		return quotes;
	}

	public List<QuoteDTO> repliedQuotes(List<QuoteDTO> allQuotes) {
		return allQuotes
				.stream()
				.filter(q -> q.getStatusType().equals(
						QuoteStatusType.REPLIED.name())
						|| q.getStatusType().equals(
								QuoteStatusType.ACCEPTED.name())
						|| q.getStatusType().equals(
								QuoteStatusType.INVALID.name()))
				.collect(Collectors.toList());
	}
}
