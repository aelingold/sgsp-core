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
import org.ucema.sgsp.api.dto.QuoteQuestionDTO;
import org.ucema.sgsp.api.dto.QuoteQuestionReplyDTO;
import org.ucema.sgsp.api.dto.RatePlanDTO;
import org.ucema.sgsp.api.dto.StateDTO;
import org.ucema.sgsp.api.dto.UserWorkRateDTO;
import org.ucema.sgsp.persistence.model.OrderStatusType;
import org.ucema.sgsp.persistence.model.QuoteStatusType;
import org.ucema.sgsp.persistence.model.UserWorkRateStatusType;
import org.ucema.sgsp.security.model.CustomUserDetails;

import com.google.common.collect.Sets;

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
	@Autowired
	private QuoteQuestionReplyService quoteQuestionReplyService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private RatePlanService ratePlanService;

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

		map.put("inProgressOrdersQty",
				orders.stream()
						.filter(o -> o.getStatusType().equals(
								OrderStatusType.IN_PROGRESS.name())).count());
		map.put("finishedOrdersQty",
				orders.stream()
						.filter(o -> o.getStatusType().equals(
								OrderStatusType.FINISHED.name())).count());

		List<Long> quoteIds = new ArrayList<>();
		orders.forEach(o -> {
			quoteIds.addAll(o.getQuoteIds());
		});

		List<QuoteDTO> allQuotes = quoteService.list(quoteIds);
		Set<String> usernames = allQuotes.stream().map(aq -> aq.getUsername())
				.collect(Collectors.toSet());

		List<QuoteDTO> quotes = quotes(username, allQuotes);
		map.put("quotes", quotes);

		long pendingQuotesQty = quotes
				.stream()
				.filter(q -> q.getStatusType().equals(
						QuoteStatusType.PENDING.name())).count();
		map.put("pendingQuotesQty", pendingQuotesQty);

		long doneQuotesQty = quotes
				.stream()
				.filter(q -> q.getStatusType().equals(
						QuoteStatusType.DONE.name())
						|| q.getStatusType().equals(
								QuoteStatusType.REPLIED.name())).count();
		map.put("doneQuotesQty", doneQuotesQty);

		map.put("quote", new QuoteDTO());

		map.put("quoteQuestion", new QuoteQuestionDTO());

		map.put("quoteQuestionReply", new QuoteQuestionReplyDTO());

		List<QuoteQuestionReplyDTO> quoteQuestionReplies = quoteQuestionReplyService
				.findByQuoteQuestion_Quote_User_Email(username);

		List<QuoteDTO> quotesWithQuoteQuestionReplies = quoteService
				.list(quoteQuestionReplies.stream()
						.map(qqr -> qqr.getQuoteId())
						.collect(Collectors.toList()));

		map.put("quotesWithQuoteQuestionReplies",
				quotesWithQuoteQuestionReplies);

		map.put("pendingQuotesWithQuoteQuestionRepliesQty",
				quotesWithQuoteQuestionRepliesQty(quotesWithQuoteQuestionReplies));

		map.put("userWorkRatesQtyMap", userWorkRateSummarizeService
				.userWorkRateSummarizesMap(usernames));

		map.put("userWorkRate", new UserWorkRateDTO());

		List<UserWorkRateDTO> userWorkRates = userWorkRateService
				.findByUser_Email(username);

		map.put("userWorkRates", userWorkRates);

		map.put("userWorkRatesPendingQty",
				userWorkRates
						.stream()
						.filter(uwr -> uwr.getStatusType().equals(
								UserWorkRateStatusType.PENDING.name())).count());
		map.put("userWorkRatesDoneQty", userWorkRates
				.stream()
				.filter(uwr -> uwr.getStatusType().equals(
						UserWorkRateStatusType.DONE.name())).count());

		List<UserWorkRateDTO> userWorkRatesReceived = userWorkRateService
				.findByQuote_User_EmailAndStatusType(username,
						UserWorkRateStatusType.DONE);

		map.put("userWorkRatesReceived", userWorkRatesReceived);

		map.put("userWorkRatesReceivedQty", userWorkRatesReceived.size());

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

		map.put("reportUsers", userService.countUsers());

		map.put("quoteServicesDone", quoteService.quotesServices(Sets
				.newHashSet(QuoteStatusType.DONE)));
		map.put("quoteServicesReplied", quoteService.quotesServices(Sets
				.newHashSet(QuoteStatusType.REPLIED, QuoteStatusType.ACCEPTED,
						QuoteStatusType.CANCELLED, QuoteStatusType.INVALID)));

		map.put("payments", paymentService.findByUser_Email(username));

		if (user.getRatePlanCode() != null) {
			map.put("ratePlan",
					ratePlanService.findByCode(user.getRatePlanCode()));
		} else {
			map.put("ratePlan", new RatePlanDTO());
		}

		return map;
	}

	private Integer quotesWithQuoteQuestionRepliesQty(
			List<QuoteDTO> quotesWithQuoteQuestionReplies) {
		Integer result = 0;

		for (QuoteDTO quoteDTO : quotesWithQuoteQuestionReplies) {
			for (QuoteQuestionDTO quoteQuestionDTO : quoteDTO
					.getQuoteQuestions()) {
				if (quoteQuestionDTO.getQuoteQuestionReply() != null
						&& (quoteQuestionDTO.getQuoteQuestionReply()
								.getDescription() == null || quoteQuestionDTO
								.getQuoteQuestionReply().getDescription()
								.isEmpty())) {
					result++;
				}
			}
		}

		return result;
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
//		return allQuotes
//				.stream()
//				.filter(q -> q.getStatusType().equals(
//						QuoteStatusType.REPLIED.name())
//						|| q.getStatusType().equals(
//								QuoteStatusType.ACCEPTED.name())
//						|| q.getStatusType().equals(
//								QuoteStatusType.INVALID.name()))
//				.collect(Collectors.toList());
		return allQuotes;
	}
}
