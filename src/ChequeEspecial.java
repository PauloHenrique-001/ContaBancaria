public class ChequeEspecial {

    private double limite;
    private double saldoCheque;
    private double divida;

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public double getSaldoCheque() {
        return saldoCheque;
    }

    public void setSaldoCheque(double saldoCheque) {
        this.saldoCheque = saldoCheque;
    }

    public double getDivida() {
        return divida;
    }

    public void setDivida(double divida) {
        this.divida = divida;
    }

    public double getDividaTotal() {
        return (limite - saldoCheque) + getDivida();
    }

    public boolean usandoChequeEspecial(){
        return limite > saldoCheque;
    }

}
