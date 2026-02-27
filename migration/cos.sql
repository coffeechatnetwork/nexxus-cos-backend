create table if not exists cos_project
(
    id         bigserial                                    not null primary key,
    version    integer                                      null,
    display_id varchar(64)                                  not null,
    org_id     bigint                                       not null,
    name       varchar(64)                                  not null,
    slug       varchar(32)                                  null,
    logo_url   text                                         null,
    image_urls jsonb     default JSON_ARRAY(RETURNING json) not null,
    status     varchar(32)                                  not null,
    created_by varchar(64)                                  not null,
    updated_by varchar(64)                                  not null,
    deleted_by varchar(64)                                  null,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP          not null,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP          not null,
    deleted_at timestamp                                    null
);

create index if not exists idx_project_org_id on cos_project (org_id);
create unique index if not exists uq_project_display_id on cos_project (display_id);
create unique index if not exists uq_project_name on cos_project (name);

-- =============================================

create table if not exists cos_organization
(
    id         bigserial                           not null primary key,
    version    integer                             null,
    display_id varchar(64)                         not null,
    name       varchar(64)                         not null,
    code       varchar(32)                         not null,
    status     varchar(32)                         not null,
    created_by varchar(64)                         not null,
    updated_by varchar(64)                         not null,
    deleted_by varchar(64)                         null,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP not null,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP not null,
    deleted_at timestamp                           null
);
create unique index if not exists uq_organization_display_id on cos_organization (display_id);
create unique index if not exists uq_organization_code on cos_organization (code);

-- =============================================

create table if not exists cos_user
(
    id         bigserial                           not null primary key,
    version    integer                             null,
    account_id varchar(64)                         not null,
    org_id     bigint                              not null,
    email      varchar(128)                        not null,
    username   varchar(64)                         null,
    avatar_url text                                null,
    status     varchar(32)                         not null,
    created_by varchar(64)                         not null,
    updated_by varchar(64)                         not null,
    deleted_by varchar(64)                         null,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP not null,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP not null,
    deleted_at timestamp                           null
);
create unique index if not exists uq_cos_user_account_id on cos_user (account_id) where deleted_at is null;
create unique index if not exists uq_cos_user_org_id_email on cos_user (org_id, email) where deleted_at is null;

-- =============================================

create table if not exists cos_comment
(
    id          bigserial                                    not null primary key,
    version     integer                                      null,
    entity_id   varchar(64)                                  not null,
    entity_type varchar(32)                                  not null,
    content     text                                         not null,
    type        varchar(32)                                  not null,
    attachments jsonb     default JSON_ARRAY(RETURNING json) not null,
    created_by  varchar(64)                                  not null,
    updated_by  varchar(64)                                  not null,
    deleted_by  varchar(64)                                  null,
    created_at  timestamp DEFAULT CURRENT_TIMESTAMP          not null,
    updated_at  timestamp DEFAULT CURRENT_TIMESTAMP          not null,
    deleted_at  timestamp                                    null
);
create index if not exists idx_comment_entity on cos_comment (entity_id, entity_type);
