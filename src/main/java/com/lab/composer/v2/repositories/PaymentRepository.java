package com.lab.composer.v2.repositories;

import com.lab.composer.v2.domain.Member;
import com.lab.composer.v2.domain.Payment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
  Payment findTop1ByOrderIdOrderByCreateDt(String orderId);

  List<Payment> findAllByMember(Member Member);

  @Query("SELECT distinct p FROM Payment p join fetch p.member join fetch p.lookAround where p.paySuccessYn = 'Y' ")
  List<Payment> findByMemberIdWithPaymentUsingJoin(Long memberId);
}
