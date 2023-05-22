package uf4.lordsandsteel;

public class Huma extends Personatge {
    public Huma(String nom, Arma arma, int forca, int constitucio, int velocitat, int inteligencia, int sort) {
        super(nom, arma, forca, constitucio, velocitat, inteligencia, sort);
        super.raca = "Huma";
    }

    @Override
    public void calculaEstadistiques() {
        super.salut = (super.getConstitucio() + super.getForca() + super.getInteligencia());
    }

}
