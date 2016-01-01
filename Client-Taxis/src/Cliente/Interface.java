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

        Scanner is = new Scanner(System.in);
        String pass;
        boolean login = false;

        try {
            System.out.print("Username: ");
            user = is.nextLine();
            System.out.print("Password: ");
            pass = is.nextLine();
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

        Scanner is = new Scanner(System.in);
        String pass;
        boolean registar = false;

        try {
            System.out.print("Username: ");
            user = is.nextLine();
            System.out.print("Password: ");
            pass = is.nextLine();
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
        Scanner is = new Scanner(System.in);
        int x, y;
        String mat, mod;
        boolean solViagem = false;

        try {
            System.out.print("Insira a posição em que se encontra o condutor: \n");
            x = Input.lerInt();
            y = Input.lerInt();
            System.out.println("Insira a matricula do veiculo");
            mat = Input.lerString();
            System.out.println("Insira o modelo do veiculo");
            mod = Input.lerString();
            if(c.anunDisp1(user, mat, mod, x, y)){
                System.out.println("Quando o condutor tiver chegado ao local de partida escreva \"chegou\"");
                String a = Input.lerString();
                do{
                    c.anunDisp2();
                    System.out.println("Quando o condutor tiver chegado ao local de destino escreva \"chegou\"");
                    String b = Input.lerString();
                    do{
                        c.anunDisp3(preco);
                    }while(b.equals("chegou"));
                }while(!a.equals("chegou"));
            }       
            } catch (myException s) {
                System.err.println(s.getMessage());
            }
        

        if (solViagem) {
            start();
        }
    }

    protected void carregarMenus() {

        String[] logreg = {"Login",
            "Registar"};

        String[] regop = {"Passageiro",
            "Condutor"};

        String[] main = {"Solicitar viagem",
            "Anunciar disponibilidade"};

        menulogreg = new Menu(logreg);
        menuregop = new Menu(regop);
        menumain = new Menu(main);
    }

}
