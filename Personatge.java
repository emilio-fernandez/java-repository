package uf4.lordsandsteel;

public abstract class Personatge {
    private String nom;
    private Arma arma;
    protected String raca;
    protected String devocio;
    private int nivell;
    private int punts_experiencia;
    /* Estadístiques primàries. */
    private int forca, constitucio, velocitat, inteligencia, sort;
    /* Estadístiques secundàries. */
    /* Dues variables de salut, per no perdre la salut base del personatge. */
    private int salutBase;
    protected int salut;
    protected int puntsDany;
    protected int pAtac;
    protected int pEsquivar;

    public Personatge(String nom, Arma arma, int forca, int constitucio, int velocitat, int inteligencia, int sort) {
        this.nom = nom;
        this.arma = arma;
        this.nivell = 1;
        this.punts_experiencia = 0;
        this.forca = forca;
        this.constitucio = constitucio;
        this.velocitat = velocitat;
        this.inteligencia = inteligencia;
        this.sort = sort;
        this.salutBase = this.constitucio + this.forca;
        this.salut = this.salutBase;
        this.puntsDany = (this.forca + this.arma.getWPOW()) / 4;
        this.pAtac = this.inteligencia + this.sort + this.arma.getWVEL();
        this.pEsquivar = this.velocitat + this.sort + this.inteligencia;
    }

    public String getNom() {
        return nom;
    }

    public int getForca() {
        return forca;
    }

    public int getConstitucio() {
        return constitucio;
    }

    public int getVelocitat() {
        return velocitat;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public int getSort() {
        return sort;
    }

    public int getSalut() {
        return salut;
    }

    public int getDany() {
        return puntsDany;
    }

    public int getProbAtac() {
        return pAtac;
    }

    public int getProbEsquivar() {
        return pEsquivar;
    }

    public Arma getArma() {
        return arma;
    }

    public String getNomArma() {
        return arma.getArma();
    }

    public int getSalutBase() {
        return salutBase;
    }

    public String getRaca() {
        return raca;
    }

    public String getDevocio() {
        return devocio;
    }

    public int getNivell() {
        return nivell;
    }

    public void setSalut(int a) {
        this.salut = a;
        if (this.salut > this.salutBase) {
            this.salut = this.salutBase;
        }
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setArma(Arma a) {
        this.arma = a;
        calculaEstadistiques();
    }

    public void setForca(int a) {
        this.forca = a;
    }

    public void setConstitucio(int a) {
        this.constitucio = a;
    }

    public void setVelocitat(int a) {
        this.velocitat = a;
    }

    public void setInteligencia(int a) {
        this.inteligencia = a;
    }

    @Override
    public String toString() {
        return String.format(
                "Personatge: %s\nArma: %s\nPunts d'experiencia: %d\nForça: %d, Constitucio: %d, Velocitat: %d, Sort: %d\nSalut: %d, Dany: %d, Probabilitat de Atac: %d, Probabilitat de Esquivar: ",
                this.nom, arma.getArma(), this.punts_experiencia, this.forca, this.constitucio, this.velocitat,
                this.inteligencia, this.sort, this.salut, this.puntsDany, this.pAtac,
                this.pEsquivar);

    }

    /*
     * Método abstracto para que luego mas tarde, en la clase Categoria se
     * especifique la forma de calcular.
     */
    public abstract void calculaEstadistiques();

    public void rebDany(Personatge rival) {
        this.salut -= rival.getDany();
    }

    public void proxNivell() {
        switch (this.nivell) {
            case 0:
                if (this.punts_experiencia >= 100) {
                    this.punts_experiencia -= 100;
                    System.out.printf("%s puja a nivell 1\n", this.nom);
                }
                break;
            case 1:
                if (this.punts_experiencia >= 200) {
                    this.punts_experiencia -= 200;
                    System.out.printf("%s puja a nivell 2\n", this.nom);
                }
                break;
            case 2:
                if (this.punts_experiencia >= 500) {
                    this.punts_experiencia -= 500;
                    System.out.printf("%s puja a nivell 3\n", this.nom);
                }
                break;
            case 3:
                if (this.punts_experiencia >= 1000) {
                    this.punts_experiencia -= 1000;
                    System.out.printf("%s puja a nivell 4\n", this.nom);
                }
                break;
            case 4:
                if (this.punts_experiencia >= 2000) {
                    this.punts_experiencia -= 2000;
                    System.out.printf("%s puja a nivell 5\n", this.nom);
                }
                break;
        }
    }

    public void rebExp(Personatge rival) {
        System.out.printf("%s reb %d punts d'experiencia\n", this.nom, rival.getSalutBase());
        this.punts_experiencia += rival.getSalutBase();
        proxNivell();
    }
}
