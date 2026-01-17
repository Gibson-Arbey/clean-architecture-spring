CREATE TABLE comunity.user_roles (
    user_id int8 NOT NULL,
    role_id int8 NOT NULL,
    CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_roles_ukey_role FOREIGN KEY (role_id) REFERENCES comunity.roles(role_id),
    CONSTRAINT user_roles_ukey_user FOREIGN KEY (user_id) REFERENCES comunity.users(user_id)
);