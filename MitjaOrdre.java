package uf4.lordsandsteel;

public class MitjaOrdre extends Mitja implements Ordre {
    public MitjaOrdre(String nom, Arma arma, int forca, int constitucio, int velocitat, int inteligencia, int sort) {
        super(nom, arma, forca, constitucio, velocitat, inteligencia, sort);
        super.devocio = "Ordre";
    }

    @Override
    public void ordre(boolean rivalFallaEsquiva) {
        if (rivalFallaEsquiva) {
            super.salut *= 1.10;
            if (super.salut > super.getSalutBase()) {
                super.salut = super.getSalutBase();
            }
        }
    }

}
