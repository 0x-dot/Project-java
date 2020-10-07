# Project-java 

Si richiede di progettare, realizzare e documentare la collezione DataBoard<E extends Data>.
La collezione DataBoard<E extends Data> è un contenitore di oggetti generici che estendono il tipo di dato Data.
Intuitivamente la collezione si comporta come uno spazio per la memorizzazione e visualizzazioni di dati
che possono essere di vario tipo ma che implementano obbligatoriamente il metodo display().
La bacheca deve garantire la privacy dei dati fornendo un proprio meccanismo di gestione della condivisione
dei dati. Ogni dato presente nella bacheca ha associato la categoria del dato. 
Il proprietario della bacheca può definire le proprie categorie e stilare una lista di contatti (amici) 
a cui saranno visibili i dati per ogni tipologia di categoria. 
I dati condivisi sono visibili solamente in lettura: in particolare i dati possono essere
visualizzati dagli amici ma modificati solamente dal proprietario della bacheca. Gli amici possono associare
un “like” al contenuto condiviso.
