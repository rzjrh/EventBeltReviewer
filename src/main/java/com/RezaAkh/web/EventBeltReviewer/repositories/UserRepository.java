package com.RezaAkh.web.EventBeltReviewer.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.RezaAkh.web.EventBeltReviewer.models.EventUser;

@Repository
public interface UserRepository extends CrudRepository<EventUser, Long>{
	EventUser findByEmail(String email);
}
