package uf4.lordsandsteel;

public class Arma {
    private String arma;
    private int WPOW;
    private int WVEL;

    public Arma(String arma, int WPOW, int WVEL) {
        this.arma = arma;
        this.WPOW = WPOW;
        this.WVEL = WVEL;
    }

    public String getArma() {
        return this.arma;
    }

    public int getWPOW() {
        return WPOW;
    }

    public int getWVEL() {
        return WVEL;
    }

}
