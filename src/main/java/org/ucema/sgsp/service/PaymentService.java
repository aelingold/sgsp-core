package org.ucema.sgsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.PaymentDTO;
import org.ucema.sgsp.api.transformation.PaymentTransformation;
import org.ucema.sgsp.persistence.PaymentDAO;
import org.ucema.sgsp.persistence.model.Payment;

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
		Payment response = paymentDAO.save(paymentTransformation.transformToModel(payment));
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
		if (payment == null){
			throw new RuntimeException("payment not found");
		}		
		paymentDAO.delete(payment);
	}		
	
	@Transactional
	public PaymentDTO get(Long id){
		Payment payment = paymentDAO.getOne(id);
		if (payment == null){
			throw new RuntimeException("payment not found");
		}		
		return paymentTransformation.transformToApi(payment);
	}	
}
