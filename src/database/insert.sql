INSERT INTO autostrada(nome, tipo) values("Autostrada del sole",  0);
INSERT INTO autostrada(nome, tipo) values("Autostrada A3",  0);
INSERT INTO autostrada(nome, tipo) values("La Verdemare", 1);
INSERT INTO autostrada(nome, tipo) values("Autostrada Azzurra",  0);
INSERT INTO autostrada(nome, tipo) values("Autostrada Brebemi",  1);

INSERT INTO tariffa_autostradale(idautostrada, tariffaA, tariffaB, tariffa3, tariffa4, tariffa5) values(1, 0.1, 0.2, 0.3, 0.4, 0.5);
INSERT INTO tariffa_autostradale(idautostrada, tariffaA, tariffaB, tariffa3, tariffa4, tariffa5) values(2, 0.2, 0.3, 0.4, 0.5, 0.6);
INSERT INTO tariffa_autostradale(idautostrada, tariffaA, tariffaB, tariffa3, tariffa4, tariffa5) values(3, 0.3, 0.4, 0.5, 0.6, 0.7);
INSERT INTO tariffa_autostradale(idautostrada, tariffaA, tariffaB, tariffa3, tariffa4, tariffa5) values(4, 0.4, 0.5, 0.6, 0.7, 0.8);
INSERT INTO tariffa_autostradale(idautostrada, tariffaA, tariffaB, tariffa3, tariffa4, tariffa5) values(5, 0.5, 0.6, 0.7, 0.8, 0.9);

INSERT INTO casello(idautostrada, nome, chilometro) values(1, "Fiorenzuola", 74);
INSERT INTO casello(idautostrada, nome, chilometro) values(1, "Lodi", 22.3);
INSERT INTO casello(idautostrada, nome, chilometro) values(1, "Sasso Marconi", 210);
INSERT INTO casello(idautostrada, nome, chilometro) values(2, "Ercolano",  8.5);
INSERT INTO casello(idautostrada, nome, chilometro) values(2, "Portici", 8);
INSERT INTO casello(idautostrada, nome, chilometro) values(2, "Angri", 29.7);
INSERT INTO casello(idautostrada, nome, chilometro) values(3, "Ceva", 81);
INSERT INTO casello(idautostrada, nome, chilometro) values(3, "Carmagnola", 13.1);
INSERT INTO casello(idautostrada, nome, chilometro) values(3, "Millesimo", 97.1);
INSERT INTO casello(idautostrada, nome, chilometro) values(4, "Lavagna", 41.1);
INSERT INTO casello(idautostrada, nome, chilometro) values(4, "Cecina Nord", 251.6);
INSERT INTO casello(idautostrada, nome, chilometro) values(4, "Recco", 22.8);
INSERT INTO casello(idautostrada, nome, chilometro) values(5, "Castrezzato", 30.4);
INSERT INTO casello(idautostrada, nome, chilometro) values(5, "Bariano",  40.1);
INSERT INTO casello(idautostrada, nome, chilometro) values(5, "Milano", 62.1);

INSERT INTO veicolo(targa, modello, marca, anno, peso, altezza, n_assi, carrello, n_assi_carrello  ) values("AA 000 AA", "kuga", "ford" , "2014", "1600", "1,60", "2", "0","null");
INSERT INTO veicolo(targa, modello, marca, anno, peso, altezza, n_assi, carrello, n_assi_carrello  ) values("AA 001 AA", "panda", "fiat" , "2010", "1300", "1,63", "2", "1","1");
INSERT INTO veicolo(targa, modello, marca, anno, peso, altezza, n_assi, carrello, n_assi_carrello  ) values("AA 011 AA", "q3", "audi" , "2017", "1700", "1,68", "2", "0","null");
INSERT INTO veicolo(targa, modello, marca, anno, peso, altezza, n_assi, carrello, n_assi_carrello  ) values("AA 111 AA", "model s", "tesla" , "2018", "2500", "1,50", "2", "0","null");
INSERT INTO veicolo(targa, modello, marca, anno, peso, altezza, n_assi, carrello, n_assi_carrello  ) values("AA 112 AA", "fiesta", "ford" , "2000", "1500", "1,58", "2", "0","null");
INSERT INTO veicolo(targa, modello, marca, anno, peso, altezza, n_assi, carrello, n_assi_carrello  ) values("AA 122 AA", "tipo", "fiat" , "2018", "1800", "1,63", "2", "0","null");
INSERT INTO veicolo(targa, modello, marca, anno, peso, altezza, n_assi, carrello, n_assi_carrello  ) values("AA 222 AA", "vitara", "suzuki" , "1999", "2200", "1,90", "2", "1","1");