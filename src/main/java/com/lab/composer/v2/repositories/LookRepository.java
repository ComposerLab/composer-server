package com.lab.composer.v2.repositories;

import com.lab.composer.v2.domain.LookAround;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LookRepository extends JpaRepository<LookAround, Long> {

}
