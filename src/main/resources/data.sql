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

       (0, TRUE, 80, 0, 2, TRUE,  '2026-10-20', 'Business networking',      'Munich',   'Business Connect',   'Name badges required'),

       (1, FALSE, 40, 2, 1, TRUE,  '2026-11-12', 'Training session',         'Cologne',  'Leadership Training', 'Internal event');


INSERT INTO EVENT_TEAM (TEAMNAME)
VALUES ('Event Service Team'),
       ('Self Service Team'),
         ( 'Catering Team'),
         ( 'Logistics Team'),
         ( 'Technical Support Team');


INSERT INTO HISTORICAL_EVENT
(
    ID,
    OWNER_ID,
    RESPONSIBLE_TEAM_ID,
    NAME,
    DESCRIPTION,
    DATE,
    PARTICIPANTS,
    LOCATION,
    INTERNATIONAL_GUESTS,
    EVENT_TYPE,
    CATERING,
    SPECIAL_NOTES,
    STATUS,
    FEEDBACK
)
VALUES
    (
        1,
        1,
        1,
        'Annual Technology Summit 2025',
        'A conference focused on emerging technologies and digital transformation.',
        '2025-03-15',
        250,
        'Berlin',
        TRUE,
        1,
        'FULL_MEALS',
        'Printed agendas and translation services were provided.',
        2,
        'Participants rated the event highly and praised the keynote speakers.'
    ),
    (
        2,
        1,
        2,
        'Quarterly Team Meeting Q1 2025',
        'A meeting to discuss project updates and team performance for the first quarter.',
        '2025-01-20',
        30,
        'Bremen',
        FALSE,
        0,
        'SNACKS',
        'The meeting was productive, but some participants suggested more interactive sessions.',
        2,
        'Overall positive feedback with suggestions for improvement in future meetings.'
    ),
    (
        3,
        1,
        3,
        'Developer Workshop: Java Best Practices',
        'A workshop focused on best practices for Java development, including code quality and performance optimization.',
        '2025-02-10',
        20,
        'Hamburg',
        FALSE,
        2,
        'NONE',
        'Participants appreciated the hands-on approach but requested more time for Q&A.',
        2,
        'The workshop was well-received, with many participants expressing interest in similar future events.'
    );



/*Hotels einfügen*/

INSERT INTO HOTEL_SIMULATIONS  ( AVAILABLE_ROOMS,NUMBER_OF_ROOMS, PRICE_PER_NIGHT, CITY, HOTEL_NAME) values
                                     (8, 10, 150, 'Berlin', 'Hotel Berlin Central'),
                                        (20, 20, 120, 'Hamburg', 'Hamburg Grand Hotel'),
                                        (14, 15, 100, 'Munich', 'Munich City Hotel'),
                                        (3, 8, 200, 'Cologne', 'Cologne Luxury Suites'),
                                        (7, 12, 80, 'Bremen', 'Bremen Budget Inn');
