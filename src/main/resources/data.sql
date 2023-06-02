insert into tipo_prestazione
values (1, 'Un taglio di capelli bellissimo', 20, 'Taglio', 1550),
       (2, 'Una messa in piega fantastica', 10, 'Piega', 580),
       (3, 'Una barba degna di Zeus', 10, 'Barba', 1020);

insert into credentials
values (1, '$2a$10$9rhNJH2rDNQT4a9yNvK/c.LLAMMpknc03JkY1v/YP5wyPFf/36cr2', 'DEFAULT', 'a', 1),
       (2, '$2a$10$aOJ3sMhzsIlxPVaq92YPdez6.khdtgMFkWdpl6YIAviYPxs2bdmZ6', 'BARBER', 'b', 2),
       (52, '$2a$10$Gbp4xurOIYmCx2KC8iftm.KbhLMQvwLvJ0J0.ABMSQ/e5c/NhdTdO', 'ADMIN', 'c', 52),
       (102, '$2a$10$ritNDIeSg7g5P.LL9aYgVezjTWTPjQfo9AUk.L136O.ty01iWiWH6', 'BARBER', 'd', 102);

insert into utente
values (1, 'a', 'a', 'a'),
       (2, 'b', 'b', 'b'),
       (52, 'c', 'c', 'c'),
       (102, 'd', 'd', 'd');