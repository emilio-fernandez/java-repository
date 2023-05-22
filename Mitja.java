package uf4.lordsandsteel;

public class Mitja extends Personatge {
    public Mitja(String nom, Arma arma, int forca, int constitucio, int velocitat, int inteligencia, int sort) {
        super(nom, arma, forca, constitucio, velocitat, inteligencia, sort);
        super.raca = "Mitja";
    }

    @Override
    public void calculaEstadistiques() {
        super.pEsquivar = (super.getVelocitat() + super.getSort() + super.getInteligencia()
                + super.getForca());
    }

}
