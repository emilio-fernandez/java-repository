package uf4.lordsandsteel;

public class Maia extends Personatge {
    public Maia(String nom, Arma arma, int forca, int constitucio, int velocitat, int inteligencia, int sort) {
        super(nom, arma, forca, constitucio, velocitat, inteligencia, sort);
        super.raca = "Maia";
    }

    @Override
    public void calculaEstadistiques() {
        super.pAtac = (super.getInteligencia() + super.getSort() + super.getArma().getWVEL()
                + super.getVelocitat());
    }

}
