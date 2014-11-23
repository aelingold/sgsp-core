package org.ucema.sgsp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.PaymentDTO;
import org.ucema.sgsp.api.transformation.PaymentTransformation;
import org.ucema.sgsp.persistence.PaymentDAO;
import org.ucema.sgsp.persistence.model.Payment;
import org.ucema.sgsp.persistence.model.PaymentStatusType;

@Service
public class PaymentService {

	@Autowired
	private PaymentTransformation paymentTransformation;

	@Autowired
	private PaymentDAO paymentDAO;

	@Transactional
	public List<PaymentDTO> list() {
		return paymentTransformation.transformToApi(paymentDAO.findAll());
	}

	@Transactional
	public PaymentDTO saveOrUpdate(PaymentDTO payment) {
		Payment response = paymentDAO.save(paymentTransformation
				.transformToModel(payment));
		payment.setId(response.getId());
		return payment;
	}

	@Transactional
	public void delete(PaymentDTO payment) {
		paymentDAO.delete(paymentTransformation.transformToModel(payment));
	}

	@Transactional
	public void delete(Long id) {
		Payment payment = paymentDAO.getOne(id);
		if (payment == null) {
			throw new RuntimeException("payment not found");
		}
		paymentDAO.delete(payment);
	}

	@Transactional
	public PaymentDTO get(Long id) {
		Payment payment = paymentDAO.getOne(id);
		if (payment == null) {
			throw new RuntimeException("payment not found");
		}
		return paymentTransformation.transformToApi(payment);
	}

	@Transactional
	public List<PaymentDTO> findByQuote_User_Email(String username) {
		List<Payment> payments = paymentDAO.findByQuote_User_Email(username);

		List<PaymentDTO> result = new ArrayList<PaymentDTO>();
		payments.forEach(payment -> {
			result.add(paymentTransformation.transformToApi(payment));
		});
		return result;
	}
	
	@Transactional
	public List<PaymentDTO> findByUser_Email(String username) {
		List<Payment> payments = paymentDAO.findByUser_Email(username);

		List<PaymentDTO> result = new ArrayList<PaymentDTO>();
		payments.forEach(payment -> {
			result.add(paymentTransformation.transformToApi(payment));
		});
		return result;
	}	

	@Transactional
	public List<PaymentDTO> findByUser_EmailOrderByCreatedAtDesc(String username) {
		List<Payment> payments = paymentDAO.findByUser_EmailOrderByCreatedAtDesc(username);

		List<PaymentDTO> result = new ArrayList<PaymentDTO>();
		payments.forEach(payment -> {
			result.add(paymentTransformation.transformToApi(payment));
		});
		return result;
	}
	
	@Transactional
	public List<PaymentDTO> findByStatusType(PaymentStatusType paymentStatusType){
		List<Payment> payments = paymentDAO.findByStatusType(paymentStatusType);
		
		List<PaymentDTO> result = new ArrayList<PaymentDTO>();
		payments.forEach(payment -> {
			result.add(paymentTransformation.transformToApi(payment));
		});
		return result;		
	}
}
