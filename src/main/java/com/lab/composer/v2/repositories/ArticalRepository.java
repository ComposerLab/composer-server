package com.lab.composer.v2.repositories;

import com.lab.composer.v2.domain.Artical;
import com.lab.composer.v2.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ArticalRepository extends JpaRepository<Artical, Long> {

}
