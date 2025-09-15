import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número da conta a ser criada");
        String nroConta = scanner.next();
        System.out.println("Digite o valor inicial que deseja depositar na conta");
        double valor = scanner.nextDouble();
        ContaBancaria contaBancaria = new ContaBancaria(nroConta, valor);

        while(true){

            System.out.println("=====================================");
            System.out.println("1 - Consultar saldo");
            System.out.println("2 - Consultar cheque especial");
            System.out.println("3 - Depositar dinheiro");
            System.out.println("4 - Sacar dinheiro");
            System.out.println("5 - Pagar um boleto");
            System.out.println("6 - Verificar se a conta está usando cheque especial");
            System.out.println("7 - Sair");
            System.out.println("=====================================");

            int opcaoSelecionada = scanner.nextInt();
            Menu opcao = Menu.values()[--opcaoSelecionada];

            switch (opcao){
                case CONSULTARSALDO -> contaBancaria.consultarSaldoConta();
                case CONSULTARCHEQUE -> contaBancaria.consultarSaldoCheque();
                case DEPOSITAR -> {
                    System.out.println("Digite o valor que deseja depositar");
                    valor = scanner.nextDouble();
                    contaBancaria.depositar(valor);
                }
                case SACAR -> {
                    System.out.println("Digite o valor que deseja sacar");
                    valor = scanner.nextDouble();
                    contaBancaria.sacar(valor, false);
                }
                case PAGARBOLETO -> {
                    System.out.println("Digite o código de barras");
                    String codigoBarras = scanner.next();
                    System.out.println("Digite o valor que deseja pagar");
                    valor = scanner.nextDouble();
                    contaBancaria.pagarBoleto(codigoBarras, valor);
                }
                case USANDOCHEQUE ->
                    System.out.println(contaBancaria.usandoChequeEspecial() ? "A conta está usando cheque especial" :
                            "A conta não está usando cheque especial");
                case SAIR -> System.exit(0);
            }
        }

    }
}
