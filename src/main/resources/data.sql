INSERT INTO app_users (id, username, email, created_at) VALUES
    (1, 'author', 'author@example.com', CURRENT_TIMESTAMP),
    (2, 'respondent', 'respondent@example.com', CURRENT_TIMESTAMP);

INSERT INTO surveys (id, title, author_id, start_date, end_date, status, created_at, updated_at) VALUES
    (1, 'Качество обучения', 1, NULL, NULL, 'DRAFT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Обратная связь', 1, CURRENT_TIMESTAMP - INTERVAL '1 day',
        CURRENT_TIMESTAMP + INTERVAL '30 days', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO survey_questions (id, survey_id, text, required) VALUES
    (1, 1, 'Удобно ли расписание?', TRUE),
    (2, 2, 'Вам понравилось занятие?', TRUE);
INSERT INTO survey_question_options (question_id, option_order, option_value) VALUES
    (1, 0, 'Да'), (1, 1, 'Нет'), (2, 0, 'Да'), (2, 1, 'Нет');
INSERT INTO survey_participations (id, survey_id, respondent_id, status, created_at, submitted_at)
    VALUES (1, 2, 2, 'IN_PROGRESS', CURRENT_TIMESTAMP, NULL);
INSERT INTO survey_answers (id, survey_id, participation_id, question_id, selected_value)
    VALUES (1, 2, 1, 2, 'Да');
