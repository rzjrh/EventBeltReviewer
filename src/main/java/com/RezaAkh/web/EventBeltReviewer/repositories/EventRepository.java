package com.RezaAkh.web.EventBeltReviewer.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.RezaAkh.web.EventBeltReviewer.models.Event;

public interface EventRepository extends CrudRepository<Event, Long>{
	List<Event> findByState(String state);
	List<Event> findByStateIsNot(String state);
}
