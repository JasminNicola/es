insert into user_employee (username, password, email) values ('a', 'a','a@a.a');
insert into user_employee (username, password, email) values ('testuser', '123','test@user.com');

INSERT INTO EVENT (
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

INSERT INTO EVENT (
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
    SPECIAL_NOTES,FEEDBACK,ResPONSIBLE_TEAM_ID
)
VALUES
    (0, TRUE, 150, 4, 1, TRUE, '2026-12-01', 'End of Year Celebration', 'Berlin', 'Year-End Party', 'Great event with excellent organization.', 'Feedback was overwhelmingly positive, with many attendees praising the venue and catering.', 1),
    (1, FALSE, 200, 4, 1, TRUE, '2026-12-15', 'Annual Review Meeting', 'Munich', 'Review 2026', 'Participants appreciated the detailed insights and planning for the next year.', 'The meeting was well-received, with constructive feedback on improving the presentation format.', 2),
    (2, TRUE, 50, 4, 1, FALSE, '2026-12-20', 'Technical Training Workshop', 'Hamburg', 'Tech Training', 'Attendees found the workshop informative and engaging.', 'Feedback highlighted the need for more hands-on exercises in future sessions.', 3);





/*Hotels einfügen*/

INSERT INTO HOTEL_SIMULATIONS  ( AVAILABLE_ROOMS,NUMBER_OF_ROOMS, PRICE_PER_NIGHT, CITY, HOTEL_NAME) values
                                     (8, 10, 150, 'Berlin', 'Hotel Berlin Central'),
                                        (20, 20, 120, 'Hamburg', 'Hamburg Grand Hotel'),
                                        (14, 15, 100, 'Munich', 'Munich City Hotel'),
                                        (3, 8, 200, 'Cologne', 'Cologne Luxury Suites'),
                                        (7, 12, 80, 'Bremen', 'Bremen Budget Inn');
