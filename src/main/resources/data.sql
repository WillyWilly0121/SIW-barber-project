insert into tipo_prestazione
values (1, 'Un taglio di capelli bellissimo', 'Taglio', 1550),
       (2, 'Una messa in piega fantastica', 'Piega', 580),
       (3, 'Una barba degna di Zeus', 'Barba', 1020);


insert into utente
values (1, 'utente@siw.it', 'Mario', 'Rossi'),
       (2, 'barbiere@siw.it', 'Aldo', 'Capellino'),
       (9, 'utente2@siw.it', 'Michele', 'Chioma'),
       (52, 'admin@siw.it', 'Giovanni', 'Tagliaboschi'),
       (102, 'barbiere2@siw.it', 'Matteo', 'Delle Siepi'),
       (7, 'barbiere3@siw.it', 'Laura', 'Forbici'),
       (8, 'barbiere4@siw.it', 'Lorenzo', 'Barbanera');

insert into credentials
values (1, '$2a$10$9rhNJH2rDNQT4a9yNvK/c.LLAMMpknc03JkY1v/YP5wyPFf/36cr2', 'DEFAULT', 'utente', 1),
       (2, '$2a$10$9rhNJH2rDNQT4a9yNvK/c.LLAMMpknc03JkY1v/YP5wyPFf/36cr2', 'BARBER', 'barbiere', 2),
       (52, '$2a$10$9rhNJH2rDNQT4a9yNvK/c.LLAMMpknc03JkY1v/YP5wyPFf/36cr2', 'ADMIN', 'admin', 52),
       (102, '$2a$10$9rhNJH2rDNQT4a9yNvK/c.LLAMMpknc03JkY1v/YP5wyPFf/36cr2', 'BARBER', 'barbiere2', 102),
       (7, '$2a$10$9rhNJH2rDNQT4a9yNvK/c.LLAMMpknc03JkY1v/YP5wyPFf/36cr2', 'BARBER', 'barbiere3', 7),
       (9, '$2a$10$9rhNJH2rDNQT4a9yNvK/c.LLAMMpknc03JkY1v/YP5wyPFf/36cr2', 'DEFAULT', 'utente2', 7),
       (8, '$2a$10$9rhNJH2rDNQT4a9yNvK/c.LLAMMpknc03JkY1v/YP5wyPFf/36cr2', 'BARBER', 'barbiere4', 8);


insert into prenotazione
values (551, '2023-06-02', '2023-06-07', 7, 1, 1),
       (552, '2023-06-02', '2023-06-07', 2, 3, 9),
       (554, '2023-06-02', '2023-06-07', 102, 2, 1),
       (555, '2023-06-02', '2023-06-08', 8, 1, 9),
       (556, '2023-06-02', '2023-06-09', 8, 3, 1),
       (557, '2023-06-02', '2023-06-10', 7, 2, 9),
       (558, '2023-06-02', '2023-06-11', 102, 1, 1);

insert into prestazione_effettuata
values (1, '2023-06-02', 2, 1, 1),
       (2, '2023-06-02', 102, 2, 9),
       (3, '2023-06-04', 8, 3, 9),
       (4, '2023-06-08', 7, 1, 1),
       (5, '2023-06-08', 2, 2, 1),
       (6, '2023-06-08', 102, 3, 9);
