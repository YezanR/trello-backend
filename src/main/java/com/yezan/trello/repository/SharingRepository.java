package com.yezan.trello.repository;

import com.yezan.trello.entity.Share;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharingRepository extends CrudRepository<Share, Integer> {
}
