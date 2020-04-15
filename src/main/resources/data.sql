
INSERT INTO users (first_name, last_name, username, email, role, password) VALUES (
    'Yezan', 'Rafed', 'yezanr', 'yezan.rafed@mail.com', 3, '$2a$10$jrlari33KpE5ocQf68ZQfuPMCOR93bTFGtx2nYZbDFmop9EwWSTGC'
    ),
    (
    'Ahmed', 'Rafed', 'ahmedr', 'ahmed.rafed@mail.com', 3, '$2a$10$jrlari33KpE5ocQf68ZQfuPMCOR93bTFGtx2nYZbDFmop9EwWSTGC'
    );

INSERT INTO boards (title, description, owner_id) VALUES
    ('Planche progressions', 'Planche steps and progressions to master the movement', 1),
    ('Human flag progressions', 'Progressions to master the human flag', 1),
    ('Pull-ups progressions', 'Progressions to master the pull-up technique', 2);