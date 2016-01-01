package Cliente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            start();
        }
    }

    protected void solViagem(String user) throws myException {
        Scanner is = new Scanner(System.in);
        int x_0, y_0, x, y;
        boolean solViagem = false;

        try {
            System.out.print("Insira a posição em que se encontra: \n");
            x_0 = Input.lerInt();
            y_0 = Input.lerInt();
            System.out.print("Insira a posição do destino: \n");
            x = Input.lerInt();
            y = Input.lerInt();
            solViagem = c.solViagem(user, x_0, y_0, x, y);
        } catch (myException s) {
            System.err.println(s.getMessage());
        }

        if (solViagem) {
            start();
        }
    }

    protected void anunDisp(String user) throws myException {
        
        int x, y;
        float preco;
        String chegou;
        String mat, mod;

        try {
            System.out.print("Insira a posição em que se encontra o condutor: \n");
            x = Input.lerInt();
            y = Input.lerInt();
            System.out.println("Insira a matricula do veiculo");
            mat = Input.lerString();
            System.out.println("Insira o modelo do veiculo");
            mod = Input.lerString();
            if (c.anunDisp1(user, mat, mod, x, y)) {
                System.out.println("Quando o condutor tiver chegado ao local de partida escreva \"chegou\"\n");
                do {
                    chegou = Input.lerString();
                } while (!chegou.equals("chegou"));
                c.anunDisp2();
                System.out.println("Quando o condutor tiver chegado ao local de destino escreva \"chegou\"\n");
                do {
                    chegou = Input.lerString();
                } while (!chegou.equals("chegou"));
                System.out.println("Quanto custou a viagem ?\n");
                do {
                    preco = Input.lerFloat();
                } while (preco < 0);
                c.anunDisp3(preco);
            }else System.err.println("Ocorreu um erro!");
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
