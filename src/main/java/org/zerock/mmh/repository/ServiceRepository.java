package org.zerock.mmh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.mmh.entity.mService;

public interface ServiceRepository extends JpaRepository<mService, String>, QuerydslPredicateExecutor<mService> {
}
