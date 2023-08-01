package com.Sk09Team.Doctor.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  @Query(value = """
      select t from Token t inner join Doctor u\s
      on t.user.doctorId = u.doctorId\s
      where u.doctorId = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<Token> findAllValidTokenByUser(Long id);




  Optional<Token> findByToken(String token);
}
