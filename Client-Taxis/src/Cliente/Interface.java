package Cliente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static java.lang.Math.floor;

/**
 *
 * @author Diogo Duarte
 *
 */
public class Interface {

    public Menu menulogreg, menuregop, menumain;
    private String user;
    private Client c;

    public Interface(Client c) {
        this.c = c;
    }

    public void start() {
        carregarMenus();

        do {
            menulogreg.executa();
            switch (menulogreg.getOpcao()) {
                case 1:
                    login();
                    break;
                case 2:
                    registar();
                    break;
                default:
                    break;

            }
        } while (menulogreg.getOpcao() != 0);
    }

    protected void login() {

        String pass;
        boolean login = false;

        try {
            System.out.print("Username: ");
            user = Input.lerString();
            System.out.print("Password: ");
            pass = Input.lerString();
            login = c.login(user, pass);

        } catch (myException s) {
            System.err.println(s.getMessage());
        }

        if (login) {
            System.out.println("\nLogin realizado com sucesso");
            do {
                try {
                    menumain.executa();
                    switch (menumain.getOpcao()) {
                        case 1:
                            solViagem();
                            break;
                        case 2:
                            anunDisp();
                            break;
                        default:
                            break;
                    }

                } catch (myException s) {
                    System.err.println(s.getMessage());
                }
            } while (menumain.getOpcao() != 0);
        }
    }

    protected void registar() {

        String pass;
        boolean registar = false;

        try {
            System.out.print("Username: ");
            user = Input.lerString();
            System.out.print("Password: ");
            pass = Input.lerString();
            registar = c.registar(user, pass);

        } catch (myException s) {
            System.err.println(s.getMessage());
        }

        if (registar) {
            System.out.println("\nRegisto efectuado com sucesso");
            start();
        } else {
            System.err.println("Não foi possivel registar");
        }
    }

    public String secToFormat(long segundos) {
        String startTime = "00:00:00";
        int h = (int) floor(segundos / 3600);
        int m = (int) floor(segundos / 60) - h * 60;
        int s = (int) (segundos - h * 3600 - m * 60);
        String newtime = h + ":" + m + ":" + s;

        return newtime;
    }

    protected void solViagem() throws myException {

        int x_0, y_0, x, y;
        String[] resposta = null;

        System.out.print("Insira a posição em que se encontra: \n");
        System.out.print("x = ");
        x_0 = Input.lerInt();
        System.out.print("y = ");
        y_0 = Input.lerInt();
        System.out.print("Insira a posição do destino: \n");
        System.out.print("x = ");
        x = Input.lerInt();
        System.out.print("y = ");
        y = Input.lerInt();
        int codViagem;
        String chegou;
        resposta = c.solViagem(user, x_0, y_0, x, y);

        if (resposta != null) {
            codViagem = Integer.parseInt(resposta[2]);

            String newtime = secToFormat(Integer.parseInt(resposta[5]));
            String newtime2 = secToFormat(Integer.parseInt(resposta[6]));
            System.out.println("Solicitação de viagem realizada com sucesso.\n");
            System.out.println("Código da Viagem: " + resposta[2] + "\nMatricula: " + resposta[3] + "\nModelo: " + resposta[4]
                    + "\nTempo estimado de espera(HH:MM:SS): " + newtime + "\nDuração estimada da viagem(HH:MM:SS): "
                    + newtime2 + "\nPreço: " + resposta[7]);

            if (c.chegouPartidaPassageiro(codViagem)) {
                System.out.println("Chegou ao local de partida, para confirmar pressione \"ok\" \n");
                do {
                    chegou = Input.lerString();
                } while (!chegou.equals("ok"));
                c.chegouPartidaRespostaPassageiro(codViagem);
            }

            if (c.chegouDestinoPassageiro(codViagem)) {
                System.out.println("Chegou ao local de destino, para confirmar pressione \"ok\" \n");
                do {
                    chegou = Input.lerString();
                } while (!chegou.equals("ok"));
                c.chegouDestinoRespostaPassageiro(codViagem);
            }
        } else {
            System.err.println("Não foi possivel solicitar viagem");
        }
        start();

    }

    protected void anunDisp() throws myException {

        int x, y;
        float preco;
        String chegou;
        String mat, mod;
        int codigoViagem;

        String[] anunDispMostra = null;

        System.out.print("Insira a posição em que se encontra o condutor: \n");
        System.out.print("x = ");
        x = Input.lerInt();
        System.out.print("y = ");
        y = Input.lerInt();
        System.out.println("Insira a matricula do veiculo");
        mat = Input.lerString();
        System.out.println("Insira o modelo do veiculo");
        mod = Input.lerString();
        anunDispMostra = c.anunDisp1(user, mat, mod, x, y);
        if (anunDispMostra != null) {
            codigoViagem = Integer.parseInt(anunDispMostra[2]);
            System.out.println("Anuncio de disponibilidade realizado com sucesso\n");
            System.out.println("Já foi atribuida uma viagem!\nCódigo de Viagem: " + anunDispMostra[2] + "\nNome do Passageiro: "
                    + anunDispMostra[3] + "\nCoordenadas do local de partida: (" + anunDispMostra[4] + "," + anunDispMostra[5]
                    + ")\nCoordenadas da do local de destino : (" + anunDispMostra[6] + "," + anunDispMostra[7] + ")");

            System.out.println("Quando o condutor tiver chegado ao local de partida escreva \"cheguei\"\n");
            do {
                chegou = Input.lerString();
            } while (!chegou.equals("cheguei"));
            c.chegouPartidaCondutor(codigoViagem);
            if (c.chegouPartidaRespostaCondutor(codigoViagem)) {
                System.out.println("Quando o condutor tiver chegado ao local de destino escreva \"cheguei\"\n");
                do {
                    chegou = Input.lerString();
                } while (!chegou.equals("cheguei"));

                System.out.print("Insira o preco do transporte: \n");
                preco = Input.lerFloat();
                c.chegouDestinoCondutor(codigoViagem, preco);
                if (!c.chegouDestinoRespostaCondutor(codigoViagem)) {
                    System.err.println("Ocorreu um erro!");
                    this.start();
                }
            } else {
                System.err.println("Ocorreu um erro!");
                this.start();
            }
        } else {
            System.err.println("Ocorreu um erro!");
            this.start();
        }
        this.start();
    }

    protected void carregarMenus() {

        String[] logreg = {"Login",
            "Registar"};

        String[] main = {"Solicitar viagem",
            "Anunciar disponibilidade"};

        menulogreg = new Menu(logreg);
        menumain = new Menu(main);
    }

    
}
