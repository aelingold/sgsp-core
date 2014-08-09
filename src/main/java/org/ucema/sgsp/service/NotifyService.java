package org.ucema.sgsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.NotifyDTO;
import org.ucema.sgsp.api.transformation.NotifyTransformation;
import org.ucema.sgsp.persistence.NotifyDAO;
import org.ucema.sgsp.persistence.model.Notify;

@Service
public class NotifyService {

	@Autowired
	private NotifyTransformation notifyTransformation;
	
	@Autowired
	private NotifyDAO notifyDAO;

	@Transactional
	public List<NotifyDTO> list() {
		return notifyTransformation.transformToApi(notifyDAO.findAll());
	}

	@Transactional
	public NotifyDTO saveOrUpdate(NotifyDTO notify) {
		Notify response = notifyDAO.save(notifyTransformation.transformToModel(notify));
		notify.setId(response.getId());
		return notify;
	}

	@Transactional
	public void delete(NotifyDTO notify) {
		notifyDAO.delete(notifyTransformation.transformToModel(notify));
	}
	
	@Transactional
	public void delete(Long id) {
		Notify notify = notifyDAO.getOne(id);
		if (notify == null){
			throw new RuntimeException("notify not found");
		}				
		notifyDAO.delete(notify);
	}	
	
	@Transactional
	public NotifyDTO get(Long id){
		Notify notify = notifyDAO.getOne(id);
		if (notify == null){
			throw new RuntimeException("notify not found");
		}			
		return notifyTransformation.transformToApi(notify);
	}	
}
