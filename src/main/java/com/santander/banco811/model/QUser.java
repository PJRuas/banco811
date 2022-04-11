package com.santander.banco811.model;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

public class QUser extends EntityPathBase<User> {
    public static final QUser User = new QUser("user");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath cpf = createString("cpf");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Class<? extends User> type, String variable) {
        super(type, variable);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata) {
        super(type, metadata);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
    }
}