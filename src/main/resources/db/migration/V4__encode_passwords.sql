create extension if not exists pgcrypto;

update granted_user set password = crypt(password, gen_salt('bf', 8));