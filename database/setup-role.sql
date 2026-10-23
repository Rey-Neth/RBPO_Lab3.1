-- psql variables: database_name and app_password. Run as postgres.
SELECT 'CREATE ROLE survey_app LOGIN NOSUPERUSER NOCREATEDB NOCREATEROLE NOREPLICATION'
WHERE NOT EXISTS (SELECT FROM pg_roles WHERE rolname = 'survey_app') \gexec
ALTER ROLE survey_app PASSWORD :'app_password';
SELECT format('CREATE DATABASE %I OWNER postgres ENCODING %L TEMPLATE template0', :'database_name', 'UTF8')
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = :'database_name') \gexec
