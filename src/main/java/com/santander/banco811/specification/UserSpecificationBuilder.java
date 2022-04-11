package com.santander.banco811.specification;

import com.santander.banco811.criteria.SearchCriteria;
import com.santander.banco811.model.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserSpecificationBuilder {
    private List<SearchCriteria> params;

    public UserSpecificationBuilder with(String key, String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public UserSpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public Specification<User> build() {
        if (params.size() == 0) return null;
        var specs = params.stream().map(UserSpecification::new).collect(Collectors.toList());

        Specification result = specs.stream().findFirst().get();

        for (int i = 0; i < params.size(); i++){
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
