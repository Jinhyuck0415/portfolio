package org.zerock.mmh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.mmh.entity.UploadResult;

public interface UploadResultRepository extends JpaRepository<UploadResult, Long> {
}
