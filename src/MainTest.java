import java.util.Iterator;
/**
 * @autore Giuseppe Giordano  
 * */

public class MainTest {
    public static void main ( String[] args ) {
        MainTest.test1();
       //MainTest.test2 ();
    }

    public static void test1 () {

        try {
            System.out.println ( "\n===> Batteria di test di Board" );

            User gianni = new User ( "gianni", "8sas8" );
            User pino = new User ( "pino", "pass" );
            User gino = new User ( "gino", "pass" );
            User lino = new User ( "lino", "pass" );
            User nino = new User ( "nino", "pass" );
            User luigino = new User ( "luigino", "pass" );


            Board3<Data> bachecagianni = new Board3<> ( gianni, "8sas8" );
            Board3<Data> bachecadipino = new Board3<> ( pino, "pass" );

            bachecadipino.createCategory ( "cane", "pass" );

            bachecagianni.createCategory ( "gatto", "8sas8" );
            bachecagianni.createCategory ( "cane", "8sas8" );
            bachecagianni.createCategory ( "topo", "8sas8" );
            bachecagianni.createCategory ( "uovo", "8sas8" );

            System.out.println ( "\n===> Controlli utenti" );
            try {
                System.out.println ( "\n===> Inserisco username non conforme. Mi aspetto FormatException" );
                User prova = new User ( "_787_", "8sas8" );
            } catch (FormatException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }
            try {
                System.out.println ( "\n===> Inserisco un username vuoto. Mi aspetto FormatException" );
                User prova1 = new User ( "", "8sas8" );
            } catch (FormatException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }

            try {
                System.out.println ( "\n===> Inserisco una password invalida. Mi aspetto FormatException" );
                User prova2 = new User ( "gin", "c" );
            } catch (FormatException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }
            try {
                System.out.println ( "\n===> Inserisco una password vuota. Mi aspetto FormatException" );
                User prova2 = new User ( "lin", "" );
            } catch (FormatException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }


            System.out.println ( "\n===> Controlli Categoria" );

            try {
                System.out.println ( "\n===> Inserisco una password errata. Mi aspetto LoginException" );
                bachecagianni.createCategory ( "gatto", "passnoncorretta" );
            } catch (LoginException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }
            try {
                System.out.println ( "\n===> Inserisco una categoria già esistente. Mi aspetto DuplicateDataException" );
                bachecagianni.createCategory ( "gatto", "8sas8" );
            } catch (DuplicateException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }
            try {
                System.out.println ( "\n===> Rimuovo una categoria non esistente. Mi aspetto DataNotFoundException" );
                bachecagianni.removeCategory ( "nonesisto", "8sas8" );
            } catch (DataNotFoundException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }
            System.out.println ( "\n===> Controlli Board" );

            System.out.println ( "\n===> Aggiungo un dato alla bacheca di gianni" );
            Data x = new Data ( gianni, "questo è un post" );
            x.setCategoria ( "gatto" );
            System.out.println ( x.display () );
            bachecagianni.put ( "8sas8", x, "gatto" );
            System.out.println ( "\n===> Rimuovo il dato alla bacheca di gianni" );
            bachecagianni.remove ( "8sas8", x );

            try {
                System.out.println ( "\n===> Provo ad accedere al dato rimosso. Mi aspetto DataNotFoundException" );
                bachecagianni.get ( "8sas8", x );
                System.out.println ( x.display () );
            } catch (DataNotFoundException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }

            try {
                System.out.println ( "\n===> assegno ad un dato ad una categoria non esistente. Mi aspetto DataNotFoundException" );
                Data z = new Data ( gianni, "questo annuncio ha la categoria non esistente" );
                z.setCategoria ( "uovo" );
                bachecagianni.put ( "8sas8", z, "nonesiste" );
            } catch (DataNotFoundException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }
            try {
                System.out.println ( "\n===> Inserisco un post duplicato. Mi aspetto DuplicateDataException" );
                Data a = new Data ( gianni, "postduplicato" );
                a.setCategoria ( "topo" );
                bachecagianni.put ( "8sas8", a, "topo" );
                bachecagianni.put ( "8sas8", a, "topo" );
            } catch (DuplicateException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }

            System.out.println ( "\n===> Controlli Amici" );
            System.out.println ( "\n===> Aggiungo amici alla bacheca di gianni" );
            bachecagianni.addFriend ( "cane", "8sas8", "pino" );
            bachecagianni.addFriend ( "cane", "8sas8", "lino" );
            bachecagianni.addFriend ( "cane", "8sas8", "gino" );
            bachecagianni.removeFriend ( "cane", "8sas8", "gino" );

            try {
                System.out.println ( "\n===> Rimuovo un amico non esistente dalla categoria. Mi aspetto DataNotFoundException" );
                bachecagianni.removeFriend ( "cane", "8sas8", "gino" );
            } catch (DataNotFoundException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }

            try {
                System.out.println ( "\n===> Aggiungo un amico ad una categoria non esistente. Mi aspetto DataNotFoundException" );
                bachecagianni.addFriend ( "nonesisto", "8sas8", "lino" );
            } catch (DataNotFoundException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }

            try {
                System.out.println ( "\n===> Aggiungo un amico duplicato alla categoria. Mi aspetto DuplicateException" );
                bachecagianni.addFriend ( "cane", "8sas8", "pino" );
            } catch (DuplicateException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }


            System.out.println ( "\n===> Controlli Like" );
            System.out.println ( "\n===> Aggiungo like a post nella bacheca \"gatto\" di pino" );
            System.out.println ( "Il post a cui metto like è\n" + x.display () );
            Data a = new Data ( gianni, "postdigianni" );
            Data t = new Data ( pino , "post di pinooooo");
            Data p = new Data ( pino, " post di pino secondo ");
            t.setCategoria ( "cane" );
            p.setCategoria ( "cane" );


            bachecadipino.addFriend ( "cane", "pass", "gianni" );

            bachecagianni.addFriend ( "cane", "8sas8", "luigino" );



            bachecagianni.insertLike ( "pino", x );
            bachecagianni.insertLike ( "pino", x );
            bachecagianni.insertLike ( "pino", x );
            bachecagianni.insertLike ( "pino", t );

            bachecadipino.put ( "pass", x, "cane" );
            bachecadipino.put("pass",t,"cane");
            bachecadipino.put("pass",p,"cane");

            System.out.println ( x.display () );


            try {
                System.out.println ( "\n===> Aggiungo un like da un utente non presente negli amici della categoria. Mi aspetto DataNotFoundException" );
                bachecagianni.insertLike ( "nino", x );
            } catch (DataNotFoundException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }

            //Test Iterator
            System.out.println ( "\n===> Test dell'iteratore della bacheca di pino" );

                Iterator<Data> itr = bachecadipino.getIterator ( "pass" );
                while ( itr.hasNext ( ) ) {

                    System.out.println ( itr.next ( ) );
                }


            System.out.println ( "\n===> Test dell'iteratore friends della bacheca di gianni" );
            try {
                itr = bachecagianni.getFriendIterator ( "pino" );
                while ( itr.hasNext ( ) ) {
                    System.out.println ( itr.next ( ) );
                }
            }catch (DataNotFoundException e){
                System.err.println("PASS");
            }
            System.out.println ( "\n===> Test dell'iteratore friends di un amico non presente negli amici della bacheca di gianni : mi aspetto un DataNotFoundException \n");
            try {

            itr = bachecagianni.getFriendIterator ( "nino" );
            while ( itr.hasNext () ) {
                System.out.println ( itr.next () );
            }
            }catch (DataNotFoundException e){
                System.err.println("PASS");
                System.out.println ( e );
            }

        } catch (Exception e) {
            System.err.println ( "FATAL ERROR - THIS SHOULD NOT HAVE HAPPENED:\n" + e );
        }

    }

    public static void test2 () {

        try {
            User gianni = new User ( "gianni", "8sas8" );
            User pino = new User ( "pino", "pass" );
            User gino = new User ( "gino", "pass" );
            User lino = new User( "lino", "pass" );
            User nino = new User ( "nino", "pass" );
            User luigino = new User( "luigino", "pass" );


            Board2<Data> bachecagianni = new Board2<> ( gianni, "8sas8" );
            Board2<Data> bachecadipino = new Board2<> ( pino, "pass" );

            bachecadipino.createCategory ( "cane", "pass" );

            bachecagianni.createCategory ( "gatto", "8sas8" );
            bachecagianni.createCategory ( "cane", "8sas8" );
            bachecagianni.createCategory ( "topo", "8sas8" );
            bachecagianni.createCategory ( "uovo", "8sas8" );

            System.out.println ( "\n===> Controlli utenti" );
            try {
                System.out.println ( "\n===> Inserisco username non conforme. Mi aspetto FormatException" );
                User prova = new User ( "_787_", "8sas8" );
            } catch (FormatException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }
            try {
                System.out.println ( "\n===> Inserisco un username vuoto. Mi aspetto FormatException" );
                User prova1 = new User ( "", "8sas8" );
            } catch (FormatException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }

            try {
                System.out.println ( "\n===> Inserisco una password invalida. Mi aspetto FormatException" );
                User prova2 = new User ( "gin", "c" );
            } catch (FormatException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }
            try {
                System.out.println ( "\n===> Inserisco una password vuota. Mi aspetto FormatException" );
                User prova2 = new User ( "lin", "" );
            } catch (FormatException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }


            System.out.println ( "\n===> Controlli Categoria" );

            try {
                System.out.println ( "\n===> Inserisco una password errata. Mi aspetto LoginException" );
                bachecagianni.createCategory ( "gatto", "passnoncorretta" );
            } catch (LoginException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }
            try {
                System.out.println ( "\n===> Inserisco una categoria già esistente. Mi aspetto DuplicateDataException" );
                bachecagianni.createCategory ( "gatto", "8sas8" );
            } catch (DuplicateException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }
            try {
                System.out.println ( "\n===> Rimuovo una categoria non esistente. Mi aspetto DataNotFoundException" );
                bachecagianni.removeCategory ( "nonesisto", "8sas8" );
            } catch (DataNotFoundException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }
            System.out.println ( "\n===> Controlli Board" );

            System.out.println ( "\n===> Aggiungo un dato alla bacheca di gianni" );
            Data x = new Data ( gianni, "questo è un post" );
            //x.setCategoria ( "gatto" );
            System.out.println ( x.display () );
            bachecagianni.put( "8sas8", x, "gatto" );
            System.out.println ( "\n===> Rimuovo il dato alla bacheca di gianni" );
            bachecagianni.remove ( "8sas8", x );

            try {
                System.out.println ( "\n===> Provo ad accedere al dato rimosso. Mi aspetto DataNotFoundException" );
                bachecagianni.get ( "8sas8", x );
                System.out.println ( x.display () );
            } catch (DataNotFoundException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }

            try {
                System.out.println ( "\n===> assegno ad un dato ad una categoria non esistente. Mi aspetto DataNotFoundException" );
                Data z = new Data ( gianni, "questo annuncio ha la categoria non esistente" );
                z.setCategoria ( "uovo" );
                bachecagianni.put ( "8sas8", z, "nonesiste" );
            } catch (DataNotFoundException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }
            try {
                System.out.println ( "\n===> Inserisco un post duplicato. Mi aspetto DuplicateDataException" );
                Data a = new Data ( gianni, "postduplicato" );
                a.setCategoria ( "topo" );
                bachecagianni.put ( "8sas8", a, "topo" );
                bachecagianni.put ( "8sas8", a, "topo" );
            } catch (DuplicateException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }

            System.out.println ( "\n===> Controlli Amici" );
            System.out.println ( "\n===> Aggiungo amici alla bacheca di gianni" );
            bachecagianni.createCategory("cane","8sas8");
            bachecagianni.addFriend ( "cane", "8sas8", "pino" );
            bachecagianni.addFriend ( "cane", "8sas8", "lino" );
            bachecagianni.addFriend ( "cane", "8sas8", "gino" );
            bachecagianni.removeFriend ( "cane", "8sas8", "gino" );

            try {
                System.out.println ( "\n===> Rimuovo un amico non esistente dalla categoria. Mi aspetto DataNotFoundException" );
                bachecagianni.removeFriend ( "cane", "8sas8", "gino" );
            } catch (DataNotFoundException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }

            try {
                System.out.println ( "\n===> Aggiungo un amico ad una categoria non esistente. Mi aspetto DataNotFoundException" );
                bachecagianni.addFriend ( "nonesisto", "8sas8", "lino" );
            } catch (DataNotFoundException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }


            System.out.println ( "\n===> Controlli Like" );
            System.out.println ( "\n===> Aggiungo like a post nella bacheca \"gatto\" di pino" );
            System.out.println ( "Il post a cui metto like è\n" + x.display () );
            Data a = new Data ( pino, "postdipino" );

            bachecadipino.addFriend ( "cane", "pass", "gianni" );

            bachecagianni.addFriend ( "cane", "8sas8", "luigino" );

           x.addLike ();
           x.addLike ();
           x.addLike ();
           x.setCategoria ( "cane" );



            bachecadipino.put ( "pass", x, "cane" );


            System.out.println ( x.display () );


            try {
                System.out.println ( "\n===> Aggiungo un like da un utente non presente negli amici della categoria. Mi aspetto DataNotFoundException" );
                bachecagianni.insertLike ( "nino", x );
            } catch (DataNotFoundException e) {
                System.err.println ( "PASS" );
                System.out.println ( e );
            } catch (Exception e) {
                System.err.println ( "FAIL:\n" + e );
            }

            //Test Iterator
            System.out.println ( "\n===> Test dell'iteratore della bacheca di pino" );
            //x.setCategoria ( "cane" );
            Iterator<Data> itr = bachecadipino.getIterator ( "pass" );
            while ( itr.hasNext () ) {
                System.out.println ( itr.next () );
            }

            System.out.println ( "\n===> Test dell'iteratore friends della bacheca di gianni" );
            itr = bachecagianni.getFriendIterator ( "pino" );
            while ( itr.hasNext () ) {
                System.out.println ( itr.next () );
            }

            System.out.println ( "\n===> Test dell'iteratore friends di un amico non presente negli amici della bacheca di gianni\n" );
            itr = bachecagianni.getFriendIterator ( "nino" );
            while ( itr.hasNext () ) {
                System.out.println ( itr.next () );
            }
        } catch (Exception e) {
            System.err.println ( "FATAL ERROR - THIS SHOULD NOT HAVE HAPPENED:\n" + e );
        }

    }

}
