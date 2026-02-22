create table if not exists account
(
    id           bigint             not null primary key,
    version      integer            null,
    display_id   varchar(64)        not null,
    org_id       varchar(64)        not null,
    type         varchar(32)        not null,
    country_code varchar(16)        null,
    phone_number varchar(64)        null,
    email        varchar(128)       not null,
    password     varchar(255)       not null,
    salt         varchar(128)       not null,
    external_id  varchar(255)       null,
    status       smallint default 1 null,
    created_by   varchar(64)        not null,
    updated_by   varchar(64)        not null,
    deleted_by   varchar(64)        null,
    created_at   timestamp          not null,
    updated_at   timestamp          not null,
    deleted_at   timestamp          null
);
create unique index if not exists uq_account_email on account (email);
create unique index if not exists uq_account_display_id on account (display_id);
create index if not exists idx_account_org_id on account (org_id);
create index if not exists uq_idx_mobile on account (country_code, phone_number);

-- =============================================

create table if not exists organization
(
    id         bigint      not null primary key,
    version    integer     null,
    display_id varchar(64) not null,
    name       varchar(64) not null,
    code       varchar(32) not null,
    status     varchar(32) not null,
    created_by varchar(64) not null,
    updated_by varchar(64) not null,
    deleted_by varchar(64) null,
    created_at timestamp   not null,
    updated_at timestamp   not null,
    deleted_at timestamp   null
);
create unique index if not exists uq_organization_display_id on organization (display_id);
create unique index if not exists uq_organization_code on organization (code);

-- =============================================

create table if not exists feature
(
    id          bigint       not null primary key,
    version     integer      null,
    type        varchar(16)  not null,
    active      smallint     not null,
    name        varchar(64)  null,
    code        varchar(64)  not null,
    description varchar(255) null,
    created_by  varchar(64)  not null,
    updated_by  varchar(64)  not null,
    deleted_by  varchar(64)  null,
    created_at  timestamp    not null,
    updated_at  timestamp    not null,
    deleted_at  timestamp    null
);
create unique index if not exists uq_feature_code on feature (code);

-- =============================================

create table if not exists organization_feature
(
    id              bigint      not null primary key,
    version         integer     null,
    organization_id bigint      not null,
    feature_id      bigint      not null,
    created_by      varchar(64) not null,
    updated_by      varchar(64) not null,
    deleted_by      varchar(64) null,
    created_at      timestamp   not null,
    updated_at      timestamp   not null,
    deleted_at      timestamp   null
);
create unique index if not exists uq_org_feature_org_id_feature_id on organization_feature (organization_id, feature_id);
create index if not exists idx_org_feature_organization_id on organization_feature (organization_id);
create index if not exists idx_org_feature_feature_id on organization_feature (feature_id);

-- =============================================

create table if not exists role
(
    id          bigint       not null primary key,
    version     integer      null,
    name        varchar(64)  not null,
    description varchar(255) null,
    status      varchar(16)  not null,
    created_by  varchar(64)  not null,
    updated_by  varchar(64)  not null,
    deleted_by  varchar(64)  null,
    created_at  timestamp    not null,
    updated_at  timestamp    not null,
    deleted_at  timestamp    null
);
create unique index if not exists uq_role_name on role (name);

-- =============================================

create table if not exists permission
(
    id             bigint       not null primary key,
    version        integer      null,
    code           varchar(128) not null,
    type           varchar(16)  not null,
    module_name    varchar(64)  not null,
    module_code    varchar(64)  not null,
    feature_name   varchar(64)  not null,
    feature_code   varchar(64)  not null,
    operation_code varchar(32)  not null,
    created_by     varchar(64)  not null,
    updated_by     varchar(64)  not null,
    deleted_by     varchar(64)  null,
    created_at     timestamp    not null,
    updated_at     timestamp    not null,
    deleted_at     timestamp    null
);
create unique index if not exists uq_permission_code on permission (code);
create unique index if not exists uq_permission_module_feature_operation on permission (module_code, feature_code, operation_code, deleted_at);

-- =============================================


create table if not exists role_permission
(
    id            bigint      not null primary key,
    version       integer     null,
    role_id       bigint      not null,
    permission_id bigint      not null,
    created_by    varchar(64) not null,
    updated_by    varchar(64) not null,
    deleted_by    varchar(64) null,
    created_at    timestamp   not null,
    updated_at    timestamp   not null,
    deleted_at    timestamp   null
);
create unique index if not exists uq_role_permission_role_id_permission_id on role_permission (role_id, permission_id);



-- create table if not exists user_group
-- (
--     id          bigint       not null primary key,
--     name        varchar(64)  not null,
--     description varchar(255) null,
--     created_by  varchar(64)  not null,
--     updated_by  varchar(64)  not null,
--     deleted_by  varchar(64)  null,
--     created_at  timestamp    not null,
--     updated_at  timestamp    not null,
--     deleted_at  timestamp    null
-- );
-- create unique index if not exists uq_user_group_name on user_group (name);

-- =============================================

-- create table if not exists user_group_role
-- (
--     id            bigint      not null primary key,
--     user_group_id bigint      not null,
--     role_id       bigint      not null,
--     created_by    varchar(64) not null,
--     updated_by    varchar(64) not null,
--     deleted_by    varchar(64) null,
--     created_at    timestamp   not null,
--     updated_at    timestamp   not null,
--     deleted_at    timestamp   null
-- );
-- create unique index if not exists uq_user_group_role_group_id_role_id on user_group_role (user_group_id, role_id);

-- =============================================

-- create table if not exists user_group_user
-- (
--     id            bigint      not null primary key,
--     user_group_id bigint      not null,
--     account_id    bigint      not null,
--     created_by    varchar(64) not null,
--     updated_by    varchar(64) not null,
--     deleted_by    varchar(64) null,
--     created_at    timestamp   not null,
--     updated_at    timestamp   not null,
--     deleted_at    timestamp   null
-- );
-- create unique index if not exists uq_user_group_user_group_id_account_id on user_group_user (user_group_id, account_id);

-- =============================================

create table if not exists user_role
(
    id         bigint      not null primary key,
    version    integer     null,
    account_id bigint      not null,
    role_id    bigint      not null,
    created_by varchar(64) not null,
    updated_by varchar(64) not null,
    deleted_by varchar(64) null,
    created_at timestamp   not null,
    updated_at timestamp   not null,
    deleted_at timestamp   null
);
create unique index if not exists uq_user_role_account_id_role_id on user_role (account_id, role_id);

