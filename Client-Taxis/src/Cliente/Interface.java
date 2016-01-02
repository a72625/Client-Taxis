package Cliente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static java.lang.Math.floor;
import java.util.Scanner;

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
//            try{
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
//            }catch(myException s){
//                System.err.println(s.getMessage());
//            }
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
                            solViagem(user);
                            break;
                        case 2:
                            anunDisp(user);
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

    protected void solViagem(String user) throws myException {

        int x_0, y_0, x, y;
        String[] solViagemMostra = null;

        System.out.print("Insira a posição em que se encontra: \n");
        x_0 = Input.lerInt();
        y_0 = Input.lerInt();
        System.out.print("Insira a posição do destino: \n");
        x = Input.lerInt();
        y = Input.lerInt();
        int codViagem;
        solViagemMostra = c.SolViagem(user, x_0, y_0, x, y);

        if (solViagemMostra != null) {
            codViagem=Integer.parseInt(solViagemMostra[2]);
            String startTime = "00:00:00";
            int segundos = Integer.parseInt(solViagemMostra[5]);
            int h = (int) floor(segundos / 3600);
            int m = (int) floor(segundos / 60) - h * 60;
            int s = segundos - h * 3600 - m * 60;
            String newtime = h + ":" + m + ":" + s;

            segundos = Integer.parseInt(solViagemMostra[6]);
            int h2 = (int) floor(segundos / 3600);
            int m2 = (int) floor(segundos / 60) - (h2 * 60);
            int s2 = segundos - h2 * 3600 - m2 * 60;
            String newtime2 = h2 + ":" + m2 + ":" + s2;

            System.out.println("Solicitação de viagem realizada com sucesso.\n");
            System.out.println("Código da Viagem: " + solViagemMostra[2] + "\nMatricula: " + solViagemMostra[3] + "\nModelo: " + solViagemMostra[4]
                    + "\nTempo estimado de espera(HH:MM:SS): " + newtime + "\nDuração estimada da viagem(HH:MM:SS): "
                    + newtime2 + "\nPreço: " + solViagemMostra[7]);
            
            if(c.SolViagem2(codViagem)){
                System.out.println("Quando pretender continuar "\n");
                do {
                    chegou = Input.lerString();
                } while (!chegou.equals("chegou"));
                
            }
                
            
            start();
        } else {
            System.err.println("Não foi possivel solicitar viagem");
        }

    }

    protected void anunDisp(String user) throws myException {

        int x, y;
        float preco;
        String chegou;
        String mat, mod;
        int codigoViagem;

        String[] anunDispMostra = null;

        try {
            System.out.print("Insira a posição em que se encontra o condutor: \n");
            x = Input.lerInt();
            y = Input.lerInt();
            System.out.println("Insira a matricula do veiculo");
            mat = Input.lerString();
            System.out.println("Insira o modelo do veiculo");
            mod = Input.lerString();
            anunDispMostra = c.anunDisp1(user, mat, mod, x, y);
            if (anunDispMostra != null) {
                codigoViagem =Integer.parseInt(anunDispMostra[2]);
                System.out.println("Anuncio de disponibilidade realizado com sucesso\n");
                System.out.println("Já foi atribuida uma viagem!\nCódigo de Viagem: " +anunDispMostra[2]+ "\nNome do Passageiro: "
                        +anunDispMostra[3] + "\nCoordenadas do local de partida: ("+anunDispMostra[4]+ ","+anunDispMostra[5]+ 
                        ")\nCoordenadas da do local de destino : ("+anunDispMostra[6]+ ","+anunDispMostra[7]+ ")");
                
                
                System.out.println("Quando o condutor tiver chegado ao local de partida escreva \"chegou\"\n");
                do {
                    chegou = Input.lerString();
                } while (!chegou.equals("chegou"));
                if (c.anunDisp2(codigoViagem)) {
                    System.out.println("Quando o condutor tiver chegado ao local de destino escreva \"chegou\"\n");
                    do {
                        chegou = Input.lerString();
                    } while (!chegou.equals("chegou"));
                    System.out.println("Quanto custou a viagem ?\n");
                    do {
                        preco = Input.lerFloat();
                    } while (preco < 0);
                    if (!c.anunDisp3(preco, codigoViagem)) {
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
        } catch (myException s) {
            System.err.println(s.getMessage());
        }
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
