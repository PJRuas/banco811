package com.santander.banco811.specification;

import com.santander.banco811.criteria.SearchCriteria;
import com.santander.banco811.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class UserSpecification implements Specification<User> {

    private SearchCriteria searchCriteria;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        Object key = root.get(searchCriteria.getKey());
//        Object value = searchCriteria.getValue().toString();
//
        Map<String, Predicate> keymap = new HashMap<>();
        keymap.put(">", criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString()));
//        keymap.put(">=", criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString()));
        keymap.put("<", criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString()));
//        keymap.put("<=", criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString()));
        keymap.put(":", criteriaBuilder.like(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString()));

        Predicate defaultPred = criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());

        return keymap.getOrDefault(searchCriteria.getOperation(), defaultPred);
    }
}
