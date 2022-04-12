package com.santander.banco811.repository;

import com.santander.banco811.dto.UserResponse;
import com.santander.banco811.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>, QuerydslPredicateExecutor<User> {
    Page<User> findByName(String name, Pageable pageable);

//    @Query("select new com.santander.banco811.model.User(u.id, u.cpf, u.name, u.creationDate, u.updateDate) from User u " +
//            "where u.cpf = :cpf")
    List<User> findByCpf(@Param("cpf") String cpf, Pageable pageable);

    Optional<User> findByLogin(String login);

    List<User> findByNameAndCpf(String name, String cpf);

    List<User> findByNameIs(String name);
    List<User> findByNameIsNot(String name);

    List<User> findByNameIsNull();
    List<User> findByNameIsNotNull();

    List<User> findByCpfStartingWith(String cpf);
    List<User> findByCpfEndingWith(String cpf);
    List<User> findByCpfContaining(String cpf);

    List<User> findByNameLike(String pattern);

    List<User> findByCreationDateBeforeAndNameAndCpf(LocalDateTime creationDate, String name, String cpf);
    List<User> findByCreationDate(LocalDateTime creationDate);

    List<User> findByNameAndCreationDateOrderByNameAsc(String name, LocalDateTime creationDate);
}
