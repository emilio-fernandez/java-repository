package uf4.lordsandsteel;

public class NanCaos extends Nan implements Caos {
    public NanCaos(String nom, Arma arma, int forca, int constitucio, int velocitat, int inteligencia, int sort) {
        super(nom, arma, forca, constitucio, velocitat, inteligencia, sort);
        super.devocio = "Caos";
    }

    @Override
    public boolean caos(boolean encertaEsquiva) {
        int num = (int) ((Math.random() * 24 + 1) + (Math.random() * 24 + 1) + (Math.random() * 24 + 1));
        return (num <= super.pAtac / 2);
    }

}
