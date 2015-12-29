package Cliente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Scanner;

/**
 *
 * @author Tomás Ferreira
 *
 */
public class Interface {///implements Facede{

    public  Menu menulogreg,menulogop,menuregop; 
    private String user;
    private Facade f;
            
    public Interface(Facade f){
        this.f = f;
    }
    
    public void start(){
        carregarMenus();
        
        do {
            try{
                menulogreg.executa();
                switch (menulogreg.getOpcao()) {
                    case 1: login();
                            break;
                    //case 2: registar();
                    //        break;
                }
            }catch(myException s){
                System.err.println(s.getMessage());
            }
        } while (menulogreg.getOpcao()!=0);
    }
    
    
    protected void login() throws myException{
        
        do {
            try{
                menulogop.executa();
                switch (menulogop.getOpcao()) {
                    case 1: loginPassageiro();
                            break;
                    case 2: loginCondutor();
                            break;
                }

            }catch(myException s){
                System.err.println(s.getMessage());
            }
        } while (menulogop.getOpcao()!=0);
    }
    
    protected void loginPassageiro() throws myException{
        Scanner is = new Scanner(System.in);
        String pass;
        
        do{
            System.out.print("Username: ");
            user = is.nextLine();
            System.out.print("Password: ");
            pass = is.nextLine();
        }while(!f.loginPassageiro(user, pass));
        System.out.println("login Feito");
        /*
        do{
            try{
                menumain.executa();
                switch (menumain.getOpcao()) {
                    case 1: menuuser(user);
                            break;
                    case 2: menuwareh();
                            break;
                    case 3: menuamigos(user);
                }

            }catch(SimpleExeption s){
                System.err.println(s.getMessage());
            }
        } while (menumain.getOpcao()!=0);*/
    }
    
    protected void loginCondutor() throws myException{
        Scanner is = new Scanner(System.in);
        String pass;
        String mat;
        String mod;
        
        do{
            System.out.print("Username: ");
            user = is.nextLine();
            System.out.print("Password: ");
            pass = is.nextLine();
            System.out.println("Matricula");
            mat = is.nextLine();
            System.out.println("Modelo");
            mod = is.nextLine();
        }while(!f.loginCondutor(user, pass));
        System.out.println("login Feito");
        /*
        do{
            try{
                menumain.executa();
                switch (menumain.getOpcao()) {
                    case 1: menuuser(user);
                            break;
                    case 2: menuwareh();
                            break;
                    case 3: menuamigos(user);
                }

            }catch(SimpleExeption s){
                System.err.println(s.getMessage());
            }
        } while (menumain.getOpcao()!=0);*/
    }
    
    /*protected void registar() throws myException{
        Scanner is = new Scanner(System.in);
        boolean sair=false;
        String usern, pass;
        
        do {
            System.out.print("Username: ");
            usern = is.nextLine();

            if (userExiste(usern)) {
                System.out.println("Nome de Utilizador ja existente!\n");
            }
            else sair=true;
        } while(!sair);
        
        System.out.print("Password: ");
        pass = is.nextLine();
        
        Client novo = new Client(usern, pass);  
        
        f.addUser(usern, pass);
    }*/
    
    
    protected  void carregarMenus() {
    
        String[] logreg = {"Login", //login
                       "Registar"}; //addUser
        
        String[] logop = {"Passageiro",
                        "Condutor"};
        
        String[] regop = {"Passageiro",
                        "Condutor"};
    
        menulogreg = new Menu(logreg);
        menulogop = new Menu(logop);
        menuregop = new Menu(regop);
    }
}