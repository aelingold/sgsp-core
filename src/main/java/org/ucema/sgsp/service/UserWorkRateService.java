package org.ucema.sgsp.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	public List<UserWorkRateDTO> findByQuote_User_EmailAndStatusType(
			String username, UserWorkRateStatusType statusType) {
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
	public List<UserWorkRateDTO> findByStatusTypeAndSummarizedAndWorkCompleted(
			UserWorkRateStatusType statusType, Boolean summarized,
			Boolean workCompleted) {
		return userWorkRateTransformation.transformToApi(userWorkRateDAO
				.findByStatusTypeAndSummarizedAndWorkCompleted(statusType,
						summarized, workCompleted));
	}

	@Transactional
	public void update(UserWorkRateDTO userWorkRateDTO) {

		UserWorkRate userWorkRate = userWorkRateDAO.getOne(userWorkRateDTO
				.getId());
		if (userWorkRate == null) {
			throw new RuntimeException("userWorkRate not found");
		}

		userWorkRateDAO.save(userWorkRateTransformation.updateFields(
				userWorkRate, userWorkRateDTO));
	}

	@Transactional
	public void summarized(Long id, Boolean summarized) {

		UserWorkRate userWorkRate = userWorkRateDAO.getOne(id);
		if (userWorkRate == null) {
			throw new RuntimeException("userWorkRate not found");
		}
		userWorkRate.setSummarized(summarized);

		userWorkRateDAO.save(userWorkRate);
	}
	
	@Transactional
	public void accept(Long quoteId, String username) {
		
		UserWorkRateDTO userWorkRate = new UserWorkRateDTO();
		userWorkRate.setQuoteId(quoteId);
		userWorkRate.setUsername(username);
		userWorkRate.setStatusType(UserWorkRateStatusType.PENDING.name());
		userWorkRate.setSummarized(false);		
		
		userWorkRateDAO.save(userWorkRateTransformation
				.transformToModel(userWorkRate));
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
