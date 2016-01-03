package Cliente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import static java.lang.Math.floor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diogo Duarte
 *
 */
public class Interface {

    public Menu menuLogReg, menuMain, menuAnunDisp;
    private String user, mod, mat;
    private int x, y;
    private Client c;

    public Interface(Client c) {
        this.c = c;
    }

    public void start() {
        carregarMenus();

        do {
            menuLogReg.executa();
            switch (menuLogReg.getOpcao()) {
                case 1:
                    login();
                    break;
                case 2:
                    registar();
                    break;
            }
        } while (menuLogReg.getOpcao() != 0);
    }

    protected void login() {

        boolean login = false;
        String pass;
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
            this.menuPrincipal();
        }
    }

    protected void menuPrincipal() {
        do {
            try {
                menuMain.executa();
                switch (menuMain.getOpcao()) {
                    case 1:
                        solViagem();
                        break;
                    case 2:
                        anunDisp();
                        break;
                }
            } catch (myException s) {
                System.err.println(s.getMessage());
            }
        } while (menuMain.getOpcao() != 0);
        try {
            c.logout(user);
        } catch (myException ex) {
            System.err.println(ex.getMessage());
        }
        this.start();
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
                    + newtime2 + "\nPreço Estimado: " + resposta[7] + "€");

            if (c.chegouPartidaPassageiro(codViagem)) {
                System.out.println("Chegou ao local de partida, para confirmar pressione enter");
                do {
                    chegou = Input.lerString();
                } while (!chegou.equals(""));
                c.chegouPartidaRespostaPassageiro(codViagem);
            }
            String[] chegouDestino = c.chegouDestinoPassageiro(codViagem);

            if (chegouDestino != null) {
                float preco = Float.parseFloat(chegouDestino[2]);
                System.out.println("Viagem concluída. Custo: " + preco);
                System.out.println("Para confirmar pressione enter");

                do {
                    chegou = Input.lerString();
                } while (!chegou.equals(""));
                c.chegouDestinoRespostaPassageiro(codViagem);
            }
        } else {
            System.err.println("Não foi possivel solicitar viagem");
        }
        //menuPrincipal();
    }

    protected void anunDisp() throws myException {

        //int x, y;
        float preco;
        String chegou;
        //String mat, mod;
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

            System.out.println("Quando o condutor tiver chegado ao local de partida pressione enter");
            do {
                chegou = Input.lerString();
            } while (!chegou.equals(""));
            c.chegouPartidaCondutor(codigoViagem);
            if (c.chegouPartidaRespostaCondutor(codigoViagem)) {
                System.out.println("Quando o condutor tiver chegado ao local de destino pressione enter");
                do {
                    chegou = Input.lerString();
                } while (!chegou.equals(""));

                System.out.print("Insira o preco do transporte: \n");
                preco = Input.lerFloat();
                c.chegouDestinoCondutor(codigoViagem, preco);
                if (!c.chegouDestinoRespostaCondutor(codigoViagem)) {
                    System.err.println("Ocorreu um erro!");
                    menuPrincipal();
                } else {
                    x = Integer.parseInt(anunDispMostra[6]);
                    y = Integer.parseInt(anunDispMostra[7]);
                    //this.menuAnunDisp();
                }
            } else {
                System.err.println("Ocorreu um erro!");
                menuPrincipal();
            }
        } else {
            System.err.println("Ocorreu um erro!");
            menuPrincipal();
        }
        this.menuAnunDisp();
    }

    protected void anunDispMesmoCarro() throws myException {
        //int x, y;
        float preco;
        String chegou;
        //String mat, mod;
        int codigoViagem;

        String[] anunDispMostra = null;

        System.out.print("Insira a posição em que se encontra o condutor: \n");
        System.out.print("x = ");
        x = Input.lerInt();
        System.out.print("y = ");
        y = Input.lerInt();

        anunDispMostra = c.anunDisp1(user, mat, mod, x, y);
        if (anunDispMostra != null) {
            codigoViagem = Integer.parseInt(anunDispMostra[2]);
            System.out.println("Anuncio de disponibilidade realizado com sucesso\n");
            System.out.println("Já foi atribuida uma viagem!\nCódigo de Viagem: " + anunDispMostra[2] + "\nNome do Passageiro: "
                    + anunDispMostra[3] + "\nCoordenadas do local de partida: (" + anunDispMostra[4] + "," + anunDispMostra[5]
                    + ")\nCoordenadas da do local de destino : (" + anunDispMostra[6] + "," + anunDispMostra[7] + ")");

            System.out.println("Quando o condutor tiver chegado ao local de partida pressione enter\n");
            do {
                chegou = Input.lerString();
            } while (!chegou.equals(""));
            c.chegouPartidaCondutor(codigoViagem);
            if (c.chegouPartidaRespostaCondutor(codigoViagem)) {
                System.out.println("Quando o condutor tiver chegado ao local de destino pressione enter\n");
                do {
                    chegou = Input.lerString();
                } while (!chegou.equals(""));

                System.out.print("Insira o preco do transporte: \n");
                preco = Input.lerFloat();
                c.chegouDestinoCondutor(codigoViagem, preco);
                if (!c.chegouDestinoRespostaCondutor(codigoViagem)) {
                    System.err.println("Ocorreu um erro!");
                    menuPrincipal();
                }else{
                    x = Integer.parseInt(anunDispMostra[6]);
                    y = Integer.parseInt(anunDispMostra[7]);
                }
                //this.menuAnunDisp();
            } else {
                System.err.println("Ocorreu um erro!");
                menuPrincipal();
            }
        } else {
            System.err.println("Ocorreu um erro!");
            menuPrincipal();
        }
        this.menuAnunDisp();
    }

    protected void anunDispMesmoLocal() throws myException {
        //int x, y;
        float preco;
        String chegou;
        //String mat, mod;
        int codigoViagem;

        String[] anunDispMostra = null;

        anunDispMostra = c.anunDisp1(user, mat, mod, x, y);
        if (anunDispMostra != null) {
            codigoViagem = Integer.parseInt(anunDispMostra[2]);
            System.out.println("Anuncio de disponibilidade realizado com sucesso\n");
            System.out.println("Já foi atribuida uma viagem!\nCódigo de Viagem: " + anunDispMostra[2] + "\nNome do Passageiro: "
                    + anunDispMostra[3] + "\nCoordenadas do local de partida: (" + anunDispMostra[4] + "," + anunDispMostra[5]
                    + ")\nCoordenadas da do local de destino : (" + anunDispMostra[6] + "," + anunDispMostra[7] + ")");

            System.out.println("Quando o condutor tiver chegado ao local de partida pressione enter\n");
            do {
                chegou = Input.lerString();
            } while (!chegou.equals(""));
            c.chegouPartidaCondutor(codigoViagem);
            if (c.chegouPartidaRespostaCondutor(codigoViagem)) {
                System.out.println("Quando o condutor tiver chegado ao local de destino pressione enter\n");
                do {
                    chegou = Input.lerString();
                } while (!chegou.equals(""));

                System.out.print("Insira o preco do transporte: \n");
                preco = Input.lerFloat();
                c.chegouDestinoCondutor(codigoViagem, preco);
                if (!c.chegouDestinoRespostaCondutor(codigoViagem)) {
                    System.err.println("Ocorreu um erro!");
                    menuPrincipal();
                }else{
                    x = Integer.parseInt(anunDispMostra[6]);
                    y = Integer.parseInt(anunDispMostra[7]);
                }
                //this.menuAnunDisp();
            } else {
                System.err.println("Ocorreu um erro!");
                menuPrincipal();
            }
        } else {
            System.err.println("Ocorreu um erro!");
            menuPrincipal();
        }
        this.menuAnunDisp();
        //menuPrincipal();
    }

    protected void menuAnunDisp() throws myException {
        do {
            menuAnunDisp.executa();
            switch (menuAnunDisp.getOpcao()) {
                case 1:
                    anunDispMesmoLocal();
                    break;
                case 2:
                    anunDispMesmoCarro();
                    break;
            }
        } while (menuAnunDisp.getOpcao() != 0);
        //menuPrincipal();
    }

    protected void carregarMenus() {

        String[] logReg = {"Login",
            "Registar"};

        String[] main = {"Solicitar viagem",
            "Anunciar disponibilidade"};

        String[] anunDisp = {"Voltar a anunciar disponibilidade neste local",
            "Voltar a anunciar disponibilidade noutro local"};

        menuLogReg = new Menu(logReg);
        menuMain = new Menu(main);
        menuAnunDisp = new Menu(anunDisp);
    }

}
