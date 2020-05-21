insert into granted_user
(id, created_at, updated_at, activation_code, email, is_active, password, username)
values ('63609a2d-d046-4a31-baf9-100e2d5e3a95',
        '2020-05-21T05:47:08.644', null, '77109a2d-d046-4a31-baf9-100e2d5e3b53',
        'dmitrijg934@gmail.com', true,
        'root123', 'admin');

insert into user_role (user_id, roles) values ('63609a2d-d046-4a31-baf9-100e2d5e3a95', 'USER'), ('63609a2d-d046-4a31-baf9-100e2d5e3a95', 'ADMIN');
