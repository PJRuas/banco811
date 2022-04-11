package com.santander.banco811.repository;

import com.santander.banco811.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

@DataJpaTest
@Profile("test")
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    private User generateTestUser(String name, String... cpf){
        User user = new User();
        user.setName(name);
        user.setCpf(cpf[0] != null ? cpf[0] : "01234567890");
        user.setPassword("testpass1234");
        return user;
    }

    @Test
    public void validate_findAll_empty_if_repository_empty() {
        var users = userRepository.findAll();

        Assertions.assertEquals(Arrays.asList(), users);
    }

    @Test
    public void return_registered_users_with_findAll() {
        User user = generateTestUser("Pedro");
        User user2 = generateTestUser("Luisa");

        entityManager.persist(user);
        entityManager.persist(user2);

        var users = userRepository.findAll();

        Assertions.assertEquals(Arrays.asList(user, user2), users);
    }

    @Test
    public void return_users_by_name_success() {
        User user = generateTestUser("Pedro");
        User user1 = generateTestUser("Luisa");
        User user2 = generateTestUser("Pedro");

        user = entityManager.persist(user);
        entityManager.persist(user1);
        entityManager.persist(user2);

        PageRequest pageRequest = PageRequest.of(0,10, Sort.Direction.DESC, "name");

        var users = userRepository.findByName("Pedro", pageRequest);
        var users1 = userRepository.findByName("Luisa", pageRequest);

        Assertions.assertEquals(2, users.getTotalElements());
        Assertions.assertEquals(1, users1.getTotalElements());
        Assertions.assertEquals(user, users.stream().findFirst().get());
    }

    @Test
    public void save_new_user_success() {
        User user = userRepository.save(generateTestUser("Pedro"));

        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getUpdateDate());
        Assertions.assertNotNull(user.getCreationDate());
    }

    @Test
    public void return_users_by_id() {
        User user = entityManager.persist(generateTestUser("Pedro"));

        var userFound = userRepository.findById(user.getId()).get();

        Assertions.assertEquals(userFound.getId(), user.getId());
        Assertions.assertEquals(user.getCpf(), userFound.getCpf());
    }

    @Test
    public void edit_user_by_id(){
        User user = entityManager.persist(generateTestUser("Pedro"));
        user.setName("Luisa");
        userRepository.save(user);

        var userFound = userRepository.findById(user.getId()).get();

        Assertions.assertEquals(userFound.getId(), user.getId());
        Assertions.assertEquals("Luisa", user.getName());
        Assertions.assertEquals("Luisa", userFound.getName());
    }
}