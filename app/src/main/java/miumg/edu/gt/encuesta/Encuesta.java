package miumg.edu.gt.encuesta;

/**
 * Created by ALLAN GARRIDO on 25/07/2017.
 */

public class Encuesta {
    public boolean getPregunta1() { return pregunta1; }

    public void setPregunta1(boolean pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public boolean isPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(boolean pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public int getPregunta3() {
        return pregunta3;
    }

    public void setPregunta3(int pregunta3) {
        this.pregunta3 = pregunta3;
    }

    public boolean isPregunta4() {
        return pregunta4;
    }

    public void setPregunta4(boolean pregunta4) {
        this.pregunta4 = pregunta4;
    }

    public boolean isPregunta5() {
        return pregunta5;
    }

    public void setPregunta5(boolean pregunta5) {
        this.pregunta5 = pregunta5;
    }

    public String getPregunta6() {
        return pregunta6;
    }

    public void setPregunta6(String pregunta6) {
        this.pregunta6 = pregunta6;
    }

    public Encuesta(boolean pregunta1, boolean pregunta2, int pregunta3, boolean pregunta4, boolean pregunta5, String pregunta6) {

        this.pregunta1 = pregunta1;
        this.pregunta2 = pregunta2;
        this.pregunta3 = pregunta3;
        this.pregunta4 = pregunta4;
        this.pregunta5 = pregunta5;
        this.pregunta6 = pregunta6;
    }

    private boolean pregunta1;
    private  boolean pregunta2;
    private int pregunta3;
    private boolean pregunta4;
    private boolean pregunta5;
    private String pregunta6;

    public Encuesta() {
    }

    @Override
    public String toString() {
        return "Pregunta 1: " + String.valueOf(pregunta1) +
               " Pregunta 2: " + String.valueOf(pregunta2) +
               " Pregunta 3: " + String.valueOf(pregunta2) +
               " Pregunta 4: " + String.valueOf(pregunta2) +
               " Pregunta 5: " + String.valueOf(pregunta2) +
               " Pregunta 6: " + String.valueOf(pregunta2);
    }
}
