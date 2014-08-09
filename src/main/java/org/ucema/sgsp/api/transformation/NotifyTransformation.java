package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.NotifyDTO;
import org.ucema.sgsp.persistence.model.Notify;
import org.ucema.sgsp.persistence.model.Order;

@Component
public class NotifyTransformation {

	@Autowired
	private OrderTransformation orderTransformation;

	public List<NotifyDTO> transformToApi(List<Notify> notifies) {
		List<NotifyDTO> result = new ArrayList<NotifyDTO>();

		for (Notify notify : notifies) {
			result.add(transformToApi(notify));
		}

		return result;
	}

	public List<Notify> transformToModel(List<NotifyDTO> notifies) {
		List<Notify> result = new ArrayList<Notify>();

		for (NotifyDTO notify : notifies) {
			result.add(transformToModel(notify));
		}

		return result;
	}

	public NotifyDTO transformToApi(Notify notify) {
		NotifyDTO result = new NotifyDTO();

		result.setId(notify.getId());
		if (notify.getOrder() != null) {
			result.setOrderId(notify.getOrder().getId());
		}
		result.setType(notify.getType());

		return result;
	}

	public Notify transformToModel(NotifyDTO notify) {
		Notify result = new Notify();

		result.setId(notify.getId());
		if (notify.getOrderId() != null) {
			result.setOrder(new Order(notify.getOrderId()));
		}
		result.setType(notify.getType());

		return result;
	}
}
