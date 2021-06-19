package com.example.demo.dao;

import com.example.demo.model.MqMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MqMessageRepository extends CrudRepository<MqMessage, Long> {
}
