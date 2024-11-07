package org.zerock.mmh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.mmh.entity.Option;

public interface OptionRepository extends JpaRepository<Option, String>, QuerydslPredicateExecutor<Option> {
}
