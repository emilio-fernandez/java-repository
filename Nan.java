package uf4.lordsandsteel;

public class Nan extends Personatge {
    public Nan(String nom, Arma arma, int forca, int constitucio, int velocitat, int inteligencia, int sort) {
        super(nom, arma, forca, constitucio, velocitat, inteligencia, sort);
        super.raca = "Nan";
    }

    @Override
    public void calculaEstadistiques() {
        super.puntsDany = (super.getForca() + super.getArma().getWPOW() + super.getConstitucio()) / 4;
    }

}
