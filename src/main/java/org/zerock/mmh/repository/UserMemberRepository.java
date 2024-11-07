package org.zerock.mmh.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mmh.entity.UserMember;

import java.util.Optional;

public interface UserMemberRepository extends JpaRepository<UserMember,String> {

    @EntityGraph(attributePaths = {"roleSet"},type=EntityGraph.EntityGraphType.LOAD)
    @Query("select m from UserMember m where m.user_mem_id =:user_mem_id")
    Optional<UserMember> findByUser_mem_id(String user_mem_id);
}
