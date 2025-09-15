public class ContaBancaria extends ChequeEspecial {

    private String nroConta;
    private double saldo;

    public ContaBancaria(String nroConta, Double saldo) {
        this.nroConta = nroConta;
        this.saldo = saldo;

        setLimite(0d);

        if (saldo <= 500d){
            setLimite(50d);
        } else {
            setLimite(saldo / 2d);
        }

        setSaldoCheque(getLimite());

    }

    public String getNroConta() {
        return nroConta;
    }

    public void setNroConta(String nroConta) {
        this.nroConta = nroConta;
    }

    public void consultarSaldoConta(){
        System.out.printf("O saldo da conta %s é R$%s \n",nroConta,saldo);
    }

    public void consultarSaldoCheque(){
        System.out.printf("O limite de cheque especial da conta %s é R$%s \n",nroConta,getLimite());
        System.out.printf("O saldo em cheque especial da conta %s disponível é R$%s \n",nroConta,getSaldoCheque());
        System.out.printf("O saldo devedor de cheque especial da conta %s é R$%s \n",nroConta,getDividaTotal());
    }

    public void depositar(double valor){
        double valorOriginal = valor;
        if (usandoChequeEspecial()){
           if (valor < getDivida()){
                setDivida(getDivida()-valor);
                valor = 0;
           } else {
               valor -= getDivida();
               setDivida(0);
           }

           if (getSaldoCheque() < getLimite() && valor > 0){
               if(getSaldoCheque()+valor > getLimite()){
                   valor = (getSaldoCheque()+valor) - getLimite();
                   setSaldoCheque(getLimite());
               } else {
                   setSaldoCheque(getSaldoCheque()+valor);
                   valor = 0;
               }

           }
        }
        double valorDescontado = valorOriginal - valor;

        if (valorDescontado > 0){
            System.out.printf("O valor de R$%s foi descontado do cheque especial \n",valorDescontado);
        }

        if (valor > 0){
            saldo += valor;
            System.out.printf("O valor de R$%s foi depositado na conta %s \n",valor,nroConta);
            System.out.printf("Saldo total: R$%s \n",saldo);
        }

    }

    public boolean sacar(double valor, boolean boleto){
        final double saldoComCheque = saldo + getSaldoCheque();
        if (saldoComCheque >= valor){
            if ((saldoComCheque - valor) <= getLimite()){
                saldo = 0;
                setSaldoCheque(saldoComCheque - valor);

                setDivida((getLimite() - getSaldoCheque()) * 0.2);
            } else {
                saldo -= valor;
            }

            if (!boleto) {
                System.out.printf("O valor de R$%s foi sacado com sucesso da conta %s \n", valor, nroConta);
                System.out.printf("Valor disponível em conta R$%s \n", saldo);
                System.out.printf("Valor disponível em cheque especial R$%s \n", getSaldoCheque());
            }

            return true;

        } else {
            if (!boleto) {
                System.out.printf("A conta %s não tem saldo e cheque especial suficiente disponível para saque. \n", nroConta);
                System.out.printf("Valor disponível em conta R$%s \n", saldo);
                System.out.printf("Valor de cheque especial disponível: R$%s \n", getSaldoCheque());
            }

            return false;
        }
    }

    public void pagarBoleto(String codigoBarras, double valor){
        boolean pago = sacar(valor, true);

        if (pago) {
            System.out.printf("O boleto %s de valor R$%s foi pago com sucesso! \n", codigoBarras,valor);
            System.out.printf("Valor disponível em conta R$%s \n", saldo);
            System.out.printf("Valor disponível em cheque especial R$%s \n", getSaldoCheque());
        } else {
            System.out.printf("Não foi possível realizar o pagamento do boleto %s, pois a conta não possui saldo " +
                    "e cheque especial suficiente disponível \n", codigoBarras);
            System.out.printf("Valor de cheque especial disponível: R$%s \n", getSaldoCheque());
        }
    }

}
