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
    first_name varchar(64)                         not null,
    last_name  varchar(64)                         not null,
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

-- =============================================

create table if not exists cos_deliverable
(
    id            bigserial                                    not null primary key,
    version       integer                                      null,
    org_id        bigint                                       not null,
    project_id    bigint                                       not null,
    display_id    varchar(64)                                  not null,
    title         varchar(128)                                 not null,
    short_desc    varchar(64)                                  null,
    long_desc     text                                         null,
    assignee      varchar(64)                                  not null,
    participants  jsonb     default JSON_ARRAY(RETURNING json) not null,
    deadline      timestamp                                    null,
    status        varchar(32)                                  not null,
    attachments   jsonb     default JSON_ARRAY(RETURNING json) not null,
    related_tasks jsonb     default JSON_ARRAY(RETURNING json) not null,
    created_by    varchar(64)                                  not null,
    updated_by    varchar(64)                                  not null,
    deleted_by    varchar(64)                                  null,
    created_at    timestamp DEFAULT CURRENT_TIMESTAMP          not null,
    updated_at    timestamp DEFAULT CURRENT_TIMESTAMP          not null,
    deleted_at    timestamp                                    null
);

create unique index if not exists uq_deliverable_display_id on cos_deliverable (display_id);
create unique index if not exists uq_deliverable_project_title on cos_deliverable (project_id, title);

-- =============================================

create table if not exists cos_task
(
    id                   bigserial                                    not null primary key,
    version              integer                                      null,
    org_id               bigint                                       not null,
    project_id           bigint                                       not null,
    display_id           varchar(64)                                  not null,
    title                varchar(128)                                 not null,
    short_desc           varchar(64)                                  null,
    long_desc            text                                         null,
    assignee             varchar(64)                                  not null,
    participants         jsonb     default JSON_ARRAY(RETURNING json) not null,
    deadline             timestamp                                    null,
    status               varchar(32)                                  not null,
    attachments          jsonb     default JSON_ARRAY(RETURNING json) not null,
    related_deliverables jsonb     default JSON_ARRAY(RETURNING json) not null,
    created_by           varchar(64)                                  not null,
    updated_by           varchar(64)                                  not null,
    deleted_by           varchar(64)                                  null,
    created_at           timestamp DEFAULT CURRENT_TIMESTAMP          not null,
    updated_at           timestamp DEFAULT CURRENT_TIMESTAMP          not null,
    deleted_at           timestamp                                    null
);

create unique index if not exists uq_task_display_id on cos_task (display_id);
create unique index if not exists uq_task_project_title on cos_task (project_id, title);

-- =============================================

create table if not exists cos_document_folder
(
    id         bigserial                           not null primary key,
    version    integer                             null,
    org_id     bigint                              not null,
    project_id bigint                              not null,
    name       varchar(64)                         not null,
    created_by varchar(64)                         not null,
    updated_by varchar(64)                         not null,
    deleted_by varchar(64)                         null,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP not null,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP not null,
    deleted_at timestamp                           null
);

create unique index if not exists uq_doc_folder_project_name on cos_document_folder (project_id, name) where deleted_at is null;

-- =============================================

create table if not exists cos_document_file
(
    id         bigserial                           not null primary key,
    version    integer                             null,
    org_id     bigint                              not null,
    project_id bigint                              not null,
    folder_id  bigint                              not null,
    name       varchar(64)                         not null,
    doc_url    text                                not null,
    created_by varchar(64)                         not null,
    updated_by varchar(64)                         not null,
    deleted_by varchar(64)                         null,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP not null,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP not null,
    deleted_at timestamp                           null
);

create unique index if not exists uq_doc_file_project_folder_id on cos_document_file (project_id, folder_id, name) where deleted_at is null;

-- =============================================

create table if not exists cos_key_date
(
    id             bigserial                                    not null primary key,
    version        integer                                      null,
    org_id         bigint                                       not null,
    project_id     bigint                                       not null,
    display_id     varchar(64)                                  not null,
    title          varchar(128)                                 not null,
    short_desc     varchar(64)                                  null,
    long_desc      text                                         null,
    category       varchar(32)                                  not null,
    reference_date timestamp                                    null,
    attachments    jsonb     default JSON_ARRAY(RETURNING json) not null,
    created_by     varchar(64)                                  not null,
    updated_by     varchar(64)                                  not null,
    deleted_by     varchar(64)                                  null,
    created_at     timestamp DEFAULT CURRENT_TIMESTAMP          not null,
    updated_at     timestamp DEFAULT CURRENT_TIMESTAMP          not null,
    deleted_at     timestamp                                    null
);

create unique index if not exists uq_key_date_display_id on cos_key_date (display_id);
create unique index if not exists uq_key_date_project_title on cos_key_date (project_id, title);

-- =============================================

create table if not exists cos_dev_checklist
(
    id          bigserial                                    not null primary key,
    version     integer                                      null,
    org_id      bigint                                       not null,
    project_id  bigint                                       not null,
    display_id  varchar(64)                                  not null,
    title       varchar(64)                                  not null,
    description text                                         null,
    category    varchar(32)                                  not null,
    status      varchar(32)                                  not null,
    waiting_on  varchar(128)                                 null,
    attachments jsonb     default JSON_ARRAY(RETURNING json) not null,
    created_by  varchar(64)                                  not null,
    updated_by  varchar(64)                                  not null,
    deleted_by  varchar(64)                                  null,
    created_at  timestamp DEFAULT CURRENT_TIMESTAMP          not null,
    updated_at  timestamp DEFAULT CURRENT_TIMESTAMP          not null,
    deleted_at  timestamp                                    null
);

create unique index if not exists uq_dev_checklist_display_id on cos_dev_checklist (display_id);
create unique index if not exists uq_dev_checklist_project_name on cos_dev_checklist (project_id, title);

-- =============================================

create table if not exists cos_question
(
    id           bigserial                                    not null primary key,
    version      integer                                      null,
    org_id       bigint                                       not null,
    project_id   bigint                                       not null,
    display_id   varchar(64)                                  not null,
    content      text                                         not null,
    priority     varchar(32)                                  not null,
    category     varchar(32)                                  not null,
    follow_up_id bigint                                       null,
    status       varchar(32)                                  not null,
    assignees    jsonb     default JSON_ARRAY(RETURNING json) not null,
    attachments  jsonb     default JSON_ARRAY(RETURNING json) not null,
    created_by   varchar(64)                                  not null,
    updated_by   varchar(64)                                  not null,
    deleted_by   varchar(64)                                  null,
    created_at   timestamp DEFAULT CURRENT_TIMESTAMP          not null,
    updated_at   timestamp DEFAULT CURRENT_TIMESTAMP          not null,
    deleted_at   timestamp                                    null
);

create unique index if not exists uq_question_display_id on cos_question (display_id);
create index if not exists idx_question_project_id on cos_question (project_id);

-- =============================================

create table if not exists cos_question_resp
(
    id          bigserial                           not null primary key,
    version     integer                             null,
    org_id      bigint                              not null,
    question_id bigint                              not null,
    content     text                                not null,
    status      varchar(32)                         not null,
    created_by  varchar(64)                         not null,
    updated_by  varchar(64)                         not null,
    deleted_by  varchar(64)                         null,
    created_at  timestamp DEFAULT CURRENT_TIMESTAMP not null,
    updated_at  timestamp DEFAULT CURRENT_TIMESTAMP not null,
    deleted_at  timestamp                           null
);

create index if not exists idx_question_resp_question_id on cos_question_resp (question_id);

-- =============================================

create table if not exists cos_risk_log
(
    id                 bigserial                           not null primary key,
    version            integer                             null,
    org_id             bigint                              not null,
    project_id         bigint                              not null,
    display_id         varchar(64)                         not null,
    topic              varchar(64)                         not null,
    description        text                                null,
    risk               text                                null,
    mitigation_of_risk text                                null,
    category           varchar(32)                         not null,
    level              varchar(32)                         not null,
    created_by         varchar(64)                         not null,
    updated_by         varchar(64)                         not null,
    deleted_by         varchar(64)                         null,
    created_at         timestamp DEFAULT CURRENT_TIMESTAMP not null,
    updated_at         timestamp DEFAULT CURRENT_TIMESTAMP not null,
    deleted_at         timestamp                           null
);

create unique index if not exists uq_risk_log_display_id on cos_risk_log (display_id);
create index if not exists idx_risk_log_project_category_topic on cos_risk_log (project_id, category, topic);

-- =============================================

create table if not exists cos_role
(
    id          bigserial                           not null primary key,
    version     integer                             null,
    name        varchar(64)                         not null,
    description varchar(255)                        null,
    status      varchar(16)                         not null,
    created_by  varchar(64)                         not null,
    updated_by  varchar(64)                         not null,
    deleted_by  varchar(64)                         null,
    created_at  timestamp DEFAULT CURRENT_TIMESTAMP not null,
    updated_at  timestamp DEFAULT CURRENT_TIMESTAMP not null,
    deleted_at  timestamp                           null
);
create unique index if not exists uq_role_name on cos_role (name);

-- =============================================

create table if not exists cos_user_role
(
    id         bigserial                           not null primary key,
    version    integer                             null,
    account_id bigint                              not null,
    project_id bigint                              null,
    role_id    bigint                              not null,
    created_by varchar(64)                         not null,
    updated_by varchar(64)                         not null,
    deleted_by varchar(64)                         null,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP not null,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP not null,
    deleted_at timestamp                           null
);
create unique index if not exists uq_user_role_account_id_project_id_role_id on cos_user_role (account_id, project_id, role_id);

-- =============================================

create table if not exists cos_permission
(
    id             bigint                              not null primary key,
    version        integer                             null,
    code           varchar(128)                        not null,
    type           varchar(16)                         not null,
    module_name    varchar(64)                         not null,
    module_code    varchar(64)                         not null,
    feature_name   varchar(64)                         not null,
    feature_code   varchar(64)                         not null,
    operation_code varchar(32)                         not null,
    url_pattern    varchar(255)                        null,
    http_method    varchar(10)                         null,
    created_by     varchar(64)                         not null,
    updated_by     varchar(64)                         not null,
    deleted_by     varchar(64)                         null,
    created_at     timestamp DEFAULT CURRENT_TIMESTAMP not null,
    updated_at     timestamp DEFAULT CURRENT_TIMESTAMP not null,
    deleted_at     timestamp                           null
);
create unique index if not exists uq_permission_code on cos_permission (code);
create unique index if not exists uq_permission_module_feature_operation on cos_permission (module_code, feature_code, operation_code, deleted_at);

-- =============================================

create table if not exists cos_role_permission
(
    id            bigserial                           not null primary key,
    version       integer                             null,
    role_id       bigint                              not null,
    permission_id bigint                              not null,
    created_by    varchar(64)                         not null,
    updated_by    varchar(64)                         not null,
    deleted_by    varchar(64)                         null,
    created_at    timestamp DEFAULT CURRENT_TIMESTAMP not null,
    updated_at    timestamp DEFAULT CURRENT_TIMESTAMP not null,
    deleted_at    timestamp                           null
    );
create unique index if not exists uq_role_permission_role_id_permission_id on cos_role_permission (role_id, permission_id);
