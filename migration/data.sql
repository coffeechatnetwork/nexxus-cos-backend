-- =============================================

INSERT INTO public.cos_role (version, name, description, status, created_by, updated_by) VALUES (0, 'CCS', 'Senior Client Contact', 'active', 'system', 'system');
INSERT INTO public.cos_role (version, name, description, status, created_by, updated_by) VALUES (0, 'EV', 'External Viewer', 'active', 'system', 'system');
INSERT INTO public.cos_role (version, name, description, status, created_by, updated_by) VALUES (0, 'PM', 'Project Manager', 'active', 'system', 'system');
INSERT INTO public.cos_role (version, name, description, status, created_by, updated_by) VALUES (0, 'CCA', 'Active Client Contact', 'active', 'system', 'system');
INSERT INTO public.cos_role (version, name, description, status, created_by, updated_by) VALUES (0, 'PCE', 'External Project Consultant', 'active', 'system', 'system');
INSERT INTO public.cos_role (version, name, description, status, created_by, updated_by) VALUES (0, 'ADMIN', 'Super Admin', 'active', 'system', 'system');
INSERT INTO public.cos_role (version, name, description, status, created_by, updated_by) VALUES (0, 'PCI', 'Internal Project Consultant', 'active', 'system', 'system');
INSERT INTO public.cos_role (version, name, description, status, created_by, updated_by) VALUES (0, 'CM', 'Client Manager', 'active', 'system', 'system');

-- =============================================

INSERT INTO public.cos_permission (version, code, type, module_name, module_code, feature_name, feature_code, operation_code, url_pattern, http_method, created_by, updated_by) VALUES (0, 'project:dashboard:view', 'api', 'Project', 'project', 'Dashboard', 'dashboard', 'view', '/api/v1/projects/*/dashboard', 'post', 'system', 'system');
INSERT INTO public.cos_permission (version, code, type, module_name, module_code, feature_name, feature_code, operation_code, url_pattern, http_method, created_by, updated_by) VALUES (0, 'deliverable:list:view', 'api', 'Deliverable', 'deliverable', 'List', 'list', 'view', '/api/v1/projects/*/deliverables/list', 'post', 'system', 'system');
INSERT INTO public.cos_permission (version, code, type, module_name, module_code, feature_name, feature_code, operation_code, url_pattern, http_method, created_by, updated_by) VALUES (0, 'deliverable:detail:view', 'api', 'Deliverable', 'deliverable', 'Detail', 'detail', 'view', '/api/v1/projects/*/deliverables/*', 'get', 'system', 'system');
INSERT INTO public.cos_permission (version, code, type, module_name, module_code, feature_name, feature_code, operation_code, url_pattern, http_method, created_by, updated_by) VALUES (0, 'deliverable:detail:edit', 'api', 'Deliverable', 'deliverable', 'Detail', 'detail', 'edit', '/api/v1/projects/*/deliverables/*/edit', 'post', 'system', 'system');
INSERT INTO public.cos_permission (version, code, type, module_name, module_code, feature_name, feature_code, operation_code, url_pattern, http_method, created_by, updated_by) VALUES (0, 'deliverable:create:create', 'api', 'Deliverable', 'deliverable', 'Create', 'create', 'create', '/api/v1/projects/*/deliverables', 'post', 'system', 'system');
INSERT INTO public.cos_permission (version, code, type, module_name, module_code, feature_name, feature_code, operation_code, url_pattern, http_method, created_by, updated_by) VALUES (0, 'checklist:create:create', 'api', 'Checklist', 'checklist', 'Create', 'create', 'create', '/api/v1/projects/*/checklists', 'post', 'system', 'system');
INSERT INTO public.cos_permission (version, code, type, module_name, module_code, feature_name, feature_code, operation_code, url_pattern, http_method, created_by, updated_by) VALUES (0, 'checklist:detail:view', 'api', 'Checklist', 'checklist', 'Detail', 'detail', 'view', '/api/v1/projects/*/checklists/*', 'get', 'system', 'system');
INSERT INTO public.cos_permission (version, code, type, module_name, module_code, feature_name, feature_code, operation_code, url_pattern, http_method, created_by, updated_by) VALUES (0, 'checklist:detail:edit', 'api', 'Checklist', 'checklist', 'Detail', 'detail', 'edit', '/api/v1/projects/*/checklists/*/edit', 'post', 'system', 'system');
INSERT INTO public.cos_permission (version, code, type, module_name, module_code, feature_name, feature_code, operation_code, url_pattern, http_method, created_by, updated_by) VALUES (0, 'checklist:list:view', 'api', 'Checklist', 'checklist', 'List', 'list', 'view', '/api/v1/projects/*/checklists/list', 'post', 'system', 'system');
INSERT INTO public.cos_permission (version, code, type, module_name, module_code, feature_name, feature_code, operation_code, url_pattern, http_method, created_by, updated_by) VALUES (0, 'checklist:summary:view', 'api', 'Checklist', 'checklist', 'Summary', 'summary', 'view', '/api/v1/projects/*/checklists/summary', 'post', 'system', 'system');

-- =============================================
