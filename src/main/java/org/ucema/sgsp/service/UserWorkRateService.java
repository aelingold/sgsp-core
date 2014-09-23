package org.ucema.sgsp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.api.dto.UserWorkRateDTO;
import org.ucema.sgsp.api.transformation.UserWorkRateTransformation;
import org.ucema.sgsp.persistence.UserWorkRateDAO;
import org.ucema.sgsp.persistence.model.UserWorkRate;
import org.ucema.sgsp.persistence.model.UserWorkRateStatusType;

@Service
public class UserWorkRateService {

	@Autowired
	private UserWorkRateTransformation userWorkRateTransformation;

	@Autowired
	private UserWorkRateDAO userWorkRateDAO;

	@Transactional
	public List<UserWorkRateDTO> findByQuote_User_EmailAndStatusType(String username,
			UserWorkRateStatusType statusType) {
		return userWorkRateTransformation.transformToApi(userWorkRateDAO
				.findByQuote_User_EmailAndStatusType(username, statusType));
	}	
	
	@Transactional
	public List<UserWorkRateDTO> findByUser_EmailAndStatusType(String username,
			UserWorkRateStatusType statusType) {
		return userWorkRateTransformation.transformToApi(userWorkRateDAO
				.findByUser_EmailAndStatusType(username, statusType));
	}

	@Transactional
	public List<UserWorkRateDTO> findByUser_Email(String username) {
		return userWorkRateTransformation.transformToApi(userWorkRateDAO
				.findByUser_Email(username));
	}

	@Transactional
	public Map<String, Long> userWorkRatesMap(List<QuoteDTO> allQuotes) {

		Set<String> allQuotesUsernames = allQuotes.stream()
				.map(aq -> aq.getUsername()).collect(Collectors.toSet());

		List<UserWorkRateDTO> userWorkRates = new ArrayList<UserWorkRateDTO>();
		if (allQuotesUsernames.size() > 0) {
			userWorkRates = findByStatusTypeAndQuoteUser_Email(
					allQuotesUsernames, UserWorkRateStatusType.DONE);
		}

		Map<String, Long> userWorkRatesMap = new HashMap<String, Long>();
		allQuotesUsernames.forEach(u -> {
			userWorkRatesMap.put(u, 0L);
		});

		for (String userWorkRatesMapKey : userWorkRatesMap.keySet()) {

			Long userCount = userWorkRates
					.stream()
					.filter(uwr -> uwr.getQuoteUsername()
							.equals(userWorkRatesMapKey))
					.collect(Collectors.counting());

			userWorkRatesMap.put(userWorkRatesMapKey, userCount);
		}

		return userWorkRatesMap;
	}

	@Transactional
	public List<UserWorkRateDTO> findByStatusTypeAndQuoteUser_Email(
			Set<String> usernames, UserWorkRateStatusType statusType) {
		return userWorkRateTransformation.transformToApi(userWorkRateDAO
				.findByStatusTypeAndQuoteUser_Email(usernames, statusType));
	}

	@Transactional
	public List<UserWorkRateDTO> list() {
		return userWorkRateTransformation.transformToApi(userWorkRateDAO
				.findAll());
	}

	@Transactional
	public void update(UserWorkRateDTO userWorkRateDTO) {
		
		UserWorkRate userWorkRate = userWorkRateDAO.getOne(userWorkRateDTO.getId());
		if (userWorkRate == null) {
			throw new RuntimeException("userWorkRate not found");
		}		
		
		userWorkRateDAO.save(userWorkRateTransformation
				.updateFields(userWorkRate,userWorkRateDTO));
	}	
	
	@Transactional
	public void saveOrUpdate(UserWorkRateDTO userWorkRate) {
		userWorkRateDAO.save(userWorkRateTransformation
				.transformToModel(userWorkRate));
	}

	@Transactional
	public void delete(UserWorkRateDTO userWorkRate) {
		userWorkRateDAO.delete(userWorkRateTransformation
				.transformToModel(userWorkRate));
	}

	@Transactional
	public void delete(Long id) {
		UserWorkRate userWorkRate = userWorkRateDAO.getOne(id);
		if (userWorkRate == null) {
			throw new RuntimeException("userWorkRate not found");
		}
		userWorkRateDAO.delete(userWorkRate);
	}

	@Transactional
	public UserWorkRateDTO get(Long id) {
		UserWorkRate userWorkRate = userWorkRateDAO.getOne(id);
		if (userWorkRate == null) {
			throw new RuntimeException("userWorkRate not found");
		}
		return userWorkRateTransformation.transformToApi(userWorkRate);
	}
}
