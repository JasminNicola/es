insert into user_employee (username, password, email) values ('a', 'a','a@a.a');
insert into user_employee (username, password, email) values ('testuser', '123','test@user.com');

INSERT INTO EVENT_REQUEST (
    EVENT_TYPE,
    INTERNATIONAL_GUESTS,
    PARTICIPANTS,
    STATUS,
    OWNER_ID,
    CATERING,
    DATE,
    DESCRIPTION,
    LOCATION,
    NAME,
    SPECIAL_NOTES
)
VALUES (0, FALSE, 100,   0,    1,     TRUE,    '2026-08-15',           'Annual technology conference',           'Berlin',           'Tech Conference 2026', 'VIP guests expected'),
       (0, FALSE, 50, 0, 1, TRUE,  '2026-07-10', 'Team meeting',             'Bremen',   'Team Sync',          'Project updates'),

       (1, TRUE, 120, 0, 1, TRUE,  '2026-08-15', 'Technology conference',    'Hamburg',  'Tech Conference',    'International guests'),

       (2, FALSE, 25, 1, 1, FALSE, '2026-09-05', 'Developer workshop',       'Berlin',   'Java Workshop',      'Bring laptop'),

       (0, TRUE, 80, 0, 1, TRUE,  '2026-10-20', 'Business networking',      'Munich',   'Business Connect',   'Name badges required'),

       (1, FALSE, 40, 2, 1, TRUE,  '2026-11-12', 'Training session',         'Cologne',  'Leadership Training', 'Internal event');
