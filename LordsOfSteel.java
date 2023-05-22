package uf4.lordsandsteel;

import java.io.IOException;
import java.util.Scanner;

public class LordsOfSteel {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        /* Incialitzem les armes per defecte del joc. */
        Arma daga = new Arma("Daga", 5, 15);
        Arma espasa = new Arma("Espasa", 10, 10);
        Arma martell = new Arma("Martell de combat", 15, 5);
        /*
         * Declaració del Array de personatges on s'emmagatzemen els personatges creats.
         */
        Personatge[] personatges = new Personatge[10];
        int menuOption = 0, quantitatPersonatges = 4;

        personatges[0] = new NanCaos("Cigala", martell, 18, 11, 18, 3, 10);
        personatges[1] = new HumaOrdre("Goku", espasa, 18, 9, 18, 12, 3);
        personatges[2] = new MaiaCaos("Juanet", daga, 5, 15, 10, 12, 18);
        personatges[3] = new MitjaOrdre("Superman", espasa, 10, 18, 18, 11, 3);

        netejaPantalla();
        System.out.printf("Benvingut al joc Lords and Steel\n");
        pressEnterToContinue();

        /* Iniciem el bucle do while on s'executarà el joc. */
        do {
            /* Menu principal amb control d'errors. */
            netejaPantalla();
            System.out.printf(
                    "Menu:\n1. Crear Personatge\n2. Esborrar Personatge\n3. Editar Personatge\n4. Llista de personatges\n5. Iniciar Combat\n6. Exit\n");
            boolean exit = false;
            while (!exit) {
                if (in.hasNextInt()) {
                    menuOption = in.nextInt();
                    if (menuOption >= 1 && menuOption <= 6) {
                        exit = true;
                    }
                } else {
                    in.next();
                }
            }

            netejaPantalla();
            switch (menuOption) {
                /* Submenu de creació de personatge. */
                case 1:
                    /* Mètode que genera els personatges, amb el menu contextual corresponent. */
                    menuGeneraPersonatge(personatges, quantitatPersonatges, daga, espasa, martell);
                    System.out.println("\nPersonatge creat correctament!\n");
                    /*
                     * Incrementem la quantitat de personatges per saber-ne quants hi ha a l'array.
                     */
                    quantitatPersonatges++;
                    pressEnterToContinue();
                    break;

                case 2:
                    if (quantitatPersonatges > 0) {
                        System.out.print("Escull quin personatge vols eliminar.\n\n");
                        for (int i = 0; i < quantitatPersonatges; i++) {
                            /*
                             * Cridem al mètode mostraPersonatges() per mostrar per pantalla el personatge
                             * de la posicio de l'array corresponent.
                             */
                            System.out.printf("%s\n\n\n", mostraPersonatge(i, personatges));
                        }

                        /* Control d'errors */
                        exit = false;
                        int temp = 0;
                        while (!exit) {
                            if (in.hasNextInt()) {
                                temp = in.nextInt();
                                if (temp - 1 >= 0 && temp - 1 < quantitatPersonatges) {
                                    exit = true;
                                }
                            } else {
                                in.next();
                            }
                        }

                        esborraPersonatge(personatges, temp);
                        quantitatPersonatges--;
                        netejaPantalla();
                        reordenaPersonatges(personatges, temp, quantitatPersonatges);

                        System.out.println("Personatge eliminar correctament.");
                        pressEnterToContinue();
                    } else {
                        System.out.println("No hi ha cap personatge.");
                        pressEnterToContinue();
                    }

                    break;
                case 3:
                    if (quantitatPersonatges == 0) {
                        System.out.println("No hi ha cap personatge.");
                    } else {
                        System.out.println("Escull el personatge que vols editar:");
                        for (int i = 0; i < quantitatPersonatges; i++) {
                            if (personatges[i] != null) {
                                /*
                                 * Cridem al mètode mostraPersonatges() per mostrar per pantalla el personatge
                                 * de la posicio de l'array corresponent.
                                 */
                                System.out.printf("%s\n\n", mostraPersonatge(i, personatges));
                            }
                        }

                        exit = false;
                        int varTemp = 0;
                        while (!exit) {
                            if (in.hasNextInt()) {
                                varTemp = in.nextInt();
                                if (varTemp - 1 >= 0 && varTemp - 1 < quantitatPersonatges) {
                                    exit = true;
                                }
                            } else {
                                in.next();
                            }
                        }
                        netejaPantalla();
                        editaPersonatge(personatges[varTemp - 1], daga, espasa, martell);
                    }
                    pressEnterToContinue();
                    break;
                case 4:
                    if (quantitatPersonatges == 0) {
                        System.out.println("No hi ha cap personatge.");
                    } else {
                        for (int i = 0; i < quantitatPersonatges; i++) {
                            /*
                             * Cridem al mètode mostraPersonatges() per mostrar per pantalla el personatge
                             * de la posicio de l'array corresponent.
                             */
                            System.out.printf("%s\n\n\n", mostraPersonatge(i, personatges));
                        }
                    }
                    pressEnterToContinue();
                    break;
                case 5:
                    if (quantitatPersonatges == 0) {
                        System.out.println("No hi ha cap personatge.");
                    } else if (quantitatPersonatges == 1) {
                        System.out.println("Nomes hi ha un personatge.");
                    } else {
                        System.out.println("Escull dos personatges que vols que lluitin");
                        for (int i = 0; i < quantitatPersonatges; i++) {
                            /*
                             * Cridem al mètode mostraPersonatges() per mostrar per pantalla el personatge
                             * de la posicio de l'array corresponent.
                             */
                            System.out.printf("%s\n\n\n", mostraPersonatge(i, personatges));
                        }

                        exit = false;
                        int[] personatgesCombat = new int[2];

                        System.out.print("Escull el primer personatge: ");

                        for (int i = 0; i < 2; i++) {
                            if (i == 1) {
                                System.out.print("Escull el segon personatge: ");
                            }

                            while (!exit) {
                                if (in.hasNextInt()) {
                                    personatgesCombat[i] = in.nextInt() - 1;
                                    if (personatgesCombat[i] >= 0
                                            && personatgesCombat[i] < quantitatPersonatges) {
                                        exit = true;
                                        if (i == 1 && personatgesCombat[0] == personatgesCombat[1]) {
                                            exit = false;
                                        }
                                    }
                                } else {
                                    in.next();
                                }
                            }
                            exit = false;
                        }

                        iniciaCombat(personatges[personatgesCombat[0]], personatges[personatgesCombat[1]]);

                        /* Reiniciem la salut dels personatges un cop termina el combat. */
                        personatges[personatgesCombat[0]].setSalut(personatges[personatgesCombat[0]].getSalutBase());
                        personatges[personatgesCombat[1]].setSalut(personatges[personatgesCombat[1]].getSalutBase());

                    }

                    pressEnterToContinue();

                    break;
            }
        } while (menuOption != 6);
    }

    /* Mètode per a la creació dels personatges amb el menu corresponent. */
    public static void menuGeneraPersonatge(Personatge[] personatges, int quantitatPersonatges, Arma daga,
            Arma espasa, Arma martell) {
        /*
         * Declaració de variables necessàries per instanciar més tard el personatge
         * corresponent.
         */
        String nom;
        int racaOption = 0, devocio = 0;
        in.nextLine();
        System.out.print("Nom de personatge: ");
        nom = in.nextLine();

        /* Selecció de la raça del personatge. */
        System.out.printf(
                "\nEscull una raça:\n1. Nan (Especialista en DANY)\n2. Huma (Especialista en RESISTENCIA)\n3. Mitja (Espcialista en ESQUIVAR)\n4. Maia (Especialista en ATACAR)\n");
        boolean exit = false;
        /* Control d'errors. */
        while (!exit) {
            if (in.hasNextInt()) {
                racaOption = in.nextInt();
                if (racaOption >= 1 && racaOption <= 4) {
                    exit = true;
                }
            } else {
                in.next();
            }
        }

        /* Selecció de la devoció del personatge. */
        System.out.print(
                "\nEscull una devocio:\n1. Ordre: Recuperes el 10% de salut si infligeixes dany a l'enemic\n2. Caos: Capacitat de contraatacar a l'enemic amb un 50% menys de probabilitat d'atac.\n");
        exit = false;
        /* Control d'errors. */
        while (!exit) {
            if (in.hasNextInt()) {
                devocio = in.nextInt();
                if (devocio >= 1 && devocio <= 2) {
                    exit = true;
                }
            } else {
                in.next();
            }
        }

        /* Variable auxiliar de type Arma */
        Arma a = seleccionaArma(daga, espasa, martell);

        /*
         * Amb el mètode clone() conseguim que es copiï el return del mètode
         * seleccionaEstadistiques aconseguint que només l'executem una vegada.
         */
        int[] estadistiques = seleccionaEstadistiques().clone();

        /*
         * Creació del personatge amb les dades inserides i inserit dins l'Array de
         * personatges.
         */
        switch (devocio) {
            case 1:
                switch (racaOption) {
                    case 1:
                        personatges[quantitatPersonatges] = new NanOrdre(nom, a, estadistiques[0], estadistiques[1],
                                estadistiques[2], estadistiques[3], estadistiques[4]);
                        break;
                    case 2:
                        personatges[quantitatPersonatges] = new HumaOrdre(nom, a, estadistiques[0], estadistiques[1],
                                estadistiques[2], estadistiques[3], estadistiques[4]);
                        break;
                    case 3:
                        personatges[quantitatPersonatges] = new MitjaOrdre(nom, a, estadistiques[0], estadistiques[1],
                                estadistiques[2], estadistiques[3], estadistiques[4]);
                        break;
                    case 4:
                        personatges[quantitatPersonatges] = new MaiaOrdre(nom, a, estadistiques[0], estadistiques[1],
                                estadistiques[2], estadistiques[3], estadistiques[4]);
                        break;
                }
                break;

            case 2:
                switch (racaOption) {
                    case 1:
                        personatges[quantitatPersonatges] = new NanCaos(nom, a, estadistiques[0], estadistiques[1],
                                estadistiques[2], estadistiques[3], estadistiques[4]);
                        break;
                    case 2:
                        personatges[quantitatPersonatges] = new HumaCaos(nom, a, estadistiques[0], estadistiques[1],
                                estadistiques[2], estadistiques[3], estadistiques[4]);
                        break;
                    case 3:
                        personatges[quantitatPersonatges] = new MitjaCaos(nom, a, estadistiques[0], estadistiques[1],
                                estadistiques[2], estadistiques[3], estadistiques[4]);
                        break;
                    case 4:
                        personatges[quantitatPersonatges] = new MaiaCaos(nom, a, estadistiques[0], estadistiques[1],
                                estadistiques[2], estadistiques[3], estadistiques[4]);
                        break;
                }
                break;
        }
    }

    /*
     * Mètode per calcular si un personatge encerta o falla tant un atac como una
     * esquiva, retorna la suma dels tres daus.
     */
    static int numProbabilitat() {
        return (int) ((Math.random() * 25 + 1) + (Math.random() * 25 + 1) + (Math.random() * 25 + 1));
    }

    /*
     * Mètode que retorna un String amb les dades del personatge de la posició de
     * l'array indicada.
     */
    static String mostraPersonatge(int i, Personatge[] personatges) {
        return String.format(
                "%d. %s - %s - %s\nForça (%d) - Constitucio (%d) - Velocitat (%d) - Inteligència (%d) - Sort (%d)\nSalut (%d) - Dany (%d) - PA (%d) - PE (%d)",
                i + 1, personatges[i].getNom(), personatges[i].getRaca(), personatges[i].getNomArma(),
                personatges[i].getForca(), personatges[i].getConstitucio(), personatges[i].getVelocitat(),
                personatges[i].getInteligencia(), personatges[i].getSort(), personatges[i].getSalutBase(),
                personatges[i].getDany(), personatges[i].getProbAtac(), personatges[i].getProbEsquivar());
    }

    /* Mètode per seleccionar un arma que retorna un valor de tipus Arma. */
    static Arma seleccionaArma(Arma daga, Arma espasa, Arma martell) {
        /* Selecció de l'arma del personatge. */
        System.out.print(
                "\nEscull un arma: \n1. Daga (Dany: 5, Velocitat: 15)\n2. Espasa (Dany: 10, Velocitat: 10)\n3. Martell de combat (Dany: 15, Velocitat: 5)\n");
        int menuArma = 0;
        boolean exit = false;
        /* Control d'errors. */
        while (!exit) {
            if (in.hasNextInt()) {
                menuArma = in.nextInt();
                if (menuArma >= 1 && menuArma <= 3) {
                    exit = true;
                }
            } else {
                in.next();
            }
        }
        switch (menuArma) {
            case 1:
                return daga;
            case 2:
                return espasa;
            case 3:
                return martell;
        }
        return null;
    }

    /*
     * Mètode per seleccionar les estadístiques primàries, que retorna un array amb
     * els valors (forca, constitucio, velocitat, inteligencia).
     */
    static int[] seleccionaEstadistiques() {
        /*
         * Distribució dels punts a les estadístiques, amb control d'errors a cadascuna.
         */
        int forca = 0, constitucio = 0, velocitat = 0, inteligencia = 0, sort = 0, puntsDisponibles = 60;
        boolean exit = false;
        System.out.print(
                "\nA continuacio distribueix 60 punts a les estadistiques del personatge, amb un minim de 3 i un maxim de 18 punts per estadistica.\n");
        System.out.print("\nForça: ");

        while (!exit) {
            if (in.hasNextInt()) {
                forca = in.nextInt();
                if (forca >= 3 && forca <= 18) {
                    exit = true;
                }
            } else {
                in.next();
            }
        }
        puntsDisponibles -= forca;

        System.out.print("\nConstitucio: ");
        exit = false;
        while (!exit) {
            if (in.hasNextInt()) {
                constitucio = in.nextInt();
                if (constitucio >= 3 && constitucio <= 18) {
                    exit = true;
                }
            } else {
                in.next();
            }
        }
        puntsDisponibles -= constitucio;

        System.out.print("\nVelocitat: ");
        exit = false;
        while (!exit) {
            if (in.hasNextInt()) {
                velocitat = in.nextInt();
                if (velocitat >= 3 && velocitat <= 18) {
                    exit = true;
                }
            } else {
                in.next();
            }
        }
        puntsDisponibles -= velocitat;

        System.out.print("\nInteligencia: ");
        exit = false;
        while (!exit && puntsDisponibles - inteligencia >= 3) {
            if (in.hasNextInt()) {
                inteligencia = in.nextInt();
                if ((inteligencia >= 3 && inteligencia <= 18) && puntsDisponibles >= inteligencia) {
                    exit = true;
                }
            } else {
                in.next();
            }
        }
        puntsDisponibles -= inteligencia;

        System.out.print("\nSort: ");
        exit = false;
        while (!exit) {
            if (in.hasNextInt()) {
                sort = in.nextInt();
                if ((sort >= 3 && sort <= 18) && puntsDisponibles >= sort) {
                    exit = true;
                }
            } else {
                in.next();
            }
        }
        puntsDisponibles -= sort;

        /*
         * Sistema per repartir els punts sobrants a la selecció anterior, es
         * reparteixen els punts sobrants a les estadístiques que no estàn al màxim.
         */
        if (puntsDisponibles != 0) {
            do {
                if (forca < 18) {
                    forca += (puntsDisponibles - (puntsDisponibles - 1));
                    puntsDisponibles--;
                }

                if (constitucio < 18) {
                    constitucio += (puntsDisponibles - (puntsDisponibles - 1));
                    puntsDisponibles--;
                }

                if (velocitat < 18) {
                    velocitat += (puntsDisponibles - (puntsDisponibles - 1));
                    puntsDisponibles--;
                }

                if (inteligencia < 18) {
                    inteligencia += (puntsDisponibles - (puntsDisponibles - 1));
                    puntsDisponibles--;
                }

                if (sort < 18) {
                    sort += (puntsDisponibles - (puntsDisponibles - 1));
                    puntsDisponibles--;
                }

            } while (puntsDisponibles > 0);
        }
        int[] array = { forca, constitucio, velocitat, inteligencia, sort };
        return array;
    }

    /*
     * Mètode que esborra de l'array de personatges un personatge en funcio de la
     * seva posicio.
     */
    static void esborraPersonatge(Personatge[] personatges, int i) {
        personatges[i - 1] = null;
    }

    /* Mètode per reordenar l'Array de personatges quan un personatge s'elimina. */
    static void reordenaPersonatges(Personatge[] personatges, int personatgeEliminat, int quantitatPersonatges) {
        for (int i = personatgeEliminat - 1; i < quantitatPersonatges; i++) {
            if (personatges[i + 1] != null) {
                personatges[i] = personatges[i + 1];
            }
        }
    }

    /* Mètode on es porta a terme el combat, amb tot el que comporta */
    static void iniciaCombat(Personatge a, Personatge b) {
        boolean mort = false;
        int torn = 1;
        Personatge[] combat = new Personatge[2];
        /*
         * Amb aquest if else determinem quin es el personatge més ràpid i per tant, el
         * primer en començar el torn.
         */
        if (a.getVelocitat() >= b.getVelocitat()) {
            combat[0] = a;
            combat[1] = b;
        } else {
            combat[0] = b;
            combat[1] = a;
        }

        do {

            netejaPantalla();

            /* Amb això anem variant entre el personatge 0 i 1 de l'array combat. */
            torn = (torn - 1) * -1;

            System.out.printf("%s PS: %d\n", a.getNom(), a.getSalut());
            System.out.printf("%s PS: %d\n\n", b.getNom(), b.getSalut());

            System.out.printf("Torn de %s\n\n", combat[torn].getNom());

            if (encertaAtac(combat[torn])) {
                System.out.printf("%s ataca", combat[torn].getNom());
                pressEnterToContinueNoInterface();

                if (encertaEsquiva(combat[(torn - 1) * -1])) {
                    System.out.printf("Pero %s ho esquiva", combat[(torn - 1) * -1].getNom());
                    pressEnterToContinueNoInterface();

                    if (combat[(torn - 1) * -1].getDevocio().equals("Caos")) {
                        System.out.printf("%s contraataca\n", combat[(torn - 1) * -1].getNom());
                        if (encertaContraatac(combat[(torn - 1) * -1])) {
                            System.out.printf("%s encerta el contraatac", combat[(torn - 1) * -1].getNom());
                            combat[torn].rebDany(combat[(torn - 1) * -1]);

                            pressEnterToContinueNoInterface();

                            if (combat[torn].getSalut() <= 0) {
                                mort = true;
                                pressEnterToContinueNoInterface();
                                netejaPantalla();
                                System.out.printf("%s es el guanyador\n", combat[(torn - 1) * -1].getNom());
                                combat[(torn - 1) * -1].rebExp(combat[torn]);
                            }
                        } else {
                            System.out.printf("%s no encerta el contraatac\n", combat[(torn - 1) * -1].getNom());
                            pressEnterToContinueNoInterface();
                        }
                    }
                } else {
                    System.out.printf("%s no aconsegueix esquiva-ho\n", combat[(torn - 1) * -1].getNom());
                    combat[((torn - 1) * -1)].rebDany(combat[torn]);
                    System.out.printf("%s ha perdut %d punts de vida\n", combat[(torn - 1) * -1].getNom(),
                            combat[torn].getDany(), combat[torn].getSalut());

                    if (combat[(torn - 1) * -1].getSalut() <= 0) {
                        mort = true;
                        pressEnterToContinueNoInterface();
                        netejaPantalla();
                        System.out.printf("%s es el guanyador\n", combat[torn].getNom());
                        combat[torn].rebExp(combat[torn]);

                    } else if (combat[torn].getDevocio().equals("Ordre")) {
                        System.out.printf("%s recupera %d punts de salut", combat[torn].getNom(),
                                (int) (combat[torn].getSalut() * 1.10) - (combat[torn].getSalut()));
                        recuperaVida(combat[torn]);
                    }

                    pressEnterToContinueNoInterface();
                }
            } else {
                System.out.printf("%s no aconsigueix atacar", combat[torn].getNom());
                pressEnterToContinueNoInterface();
            }
            System.out.println("\n");
        } while (!mort);
    }

    /* Mètode per determinar si s'encerta o no l'atac. */
    static boolean encertaAtac(Personatge a) {
        int atac = numProbabilitat();
        boolean success = false;
        if (atac >= a.getProbAtac()) {
            success = true;
        }
        return success;
    }

    /*
     * Fem un mètode diferent per a l'esquiva per si en cas de que el personatge es
     * de devocio Caos, pugui contraatacar.
     */
    static boolean encertaEsquiva(Personatge a) {
        int esquiva = numProbabilitat();
        boolean success = false;
        if (esquiva >= a.getProbEsquivar()) {
            success = true;
        }
        return success;
    }

    /* Mètode per comprobar si un Personatge de devocio Caos pot contraatacar. */
    static boolean encertaContraatac(Personatge a) {
        int atac = numProbabilitat();
        boolean success = false;
        if ((atac / 2) >= a.getProbEsquivar()) {
            success = true;
        }
        return success;
    }

    static void recuperaVida(Personatge a) {
        a.setSalut((int) (a.getSalutBase() * 1.10));
    }

    /* Mètode que permet modificar dades dels personatges ja creats. */
    static void editaPersonatge(Personatge a, Arma daga, Arma espasa, Arma martell) {
        boolean exit = false;
        int temp = 0;

        System.out.printf("Que vols editar?\n1. Nom\n2. Arma\n3. Estadístiques\n");
        while (!exit) {
            if (in.hasNextInt()) {
                temp = in.nextInt();
                if (temp >= 1 && temp <= 3) {
                    exit = true;
                }
            } else {
                in.next();
            }
        }
        in.nextLine();

        switch (temp) {
            case 1:
                System.out.printf("Nou nom: ");
                String nom = in.nextLine();
                a.setNom(nom);
                break;
            case 2:
                System.out.print(
                        "\nEscull un arma: \n1. Daga (Dany: 5, Velocitat: 15)\n2. Espasa (Dany: 10, Velocitat: 10)\n3. Martell de combat (Dany: 15, Velocitat: 5)\n");
                int menuArma = 0;
                exit = false;
                /* Control d'errors. */
                while (!exit) {
                    if (in.hasNextInt()) {
                        menuArma = in.nextInt();
                        if (menuArma >= 1 && menuArma <= 3) {
                            exit = true;
                        }
                    } else {
                        in.next();
                    }
                }
                switch (menuArma) {
                    case 1:
                        a.setArma(daga);
                    case 2:
                        a.setArma(espasa);
                    case 3:
                        a.setArma(martell);
                }
                break;
            case 3:
                int[] tempEst = new int[4];
                tempEst = seleccionaEstadistiques();

                a.setForca(tempEst[0]);
                a.setConstitucio(tempEst[1]);
                a.setVelocitat(tempEst[2]);
                a.setInteligencia(tempEst[3]);

                if (a.getNivell() > 1) {
                    a.setForca(a.getForca() + a.getNivell() - 1);
                    a.setConstitucio(a.getConstitucio() + a.getNivell() - 1);
                    a.setVelocitat(a.getVelocitat() + a.getNivell() - 1);
                    a.setInteligencia(a.getInteligencia() + a.getNivell() - 1);
                }
                break;
        }
    }

    /* Mètode per buidar la consola de comandes. */
    static void netejaPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
        }
    }

    /* Mètode per demanar a l'usuari que presioni la tecla Enter. */
    static void pressEnterToContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
            in.nextLine();
        } catch (Exception e) {
        }
    }

    /*
     * Mètode per pausar l'execució del joc permetent a l'usuari llegir el que està
     * passant. S'ha de premer ENTER.
     */
    static void pressEnterToContinueNoInterface() {
        try {
            System.in.read();
            in.nextLine();
        } catch (Exception e) {
        }
    }
}
