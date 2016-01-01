package Cliente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Diogo Duarte
 *
 */
public class Interface {

    public  Menu menulogreg,menuregop,menumain; 
    private String user;
    private Client c;
            
    public Interface(Client c){
        this.c = c;
    }
    
    public void start(){
        carregarMenus();
        
        do {
//            try{
                menulogreg.executa();
                switch (menulogreg.getOpcao()) {
                    case 1: login();
                            break;
                    case 2: registar();
                            break;
                    default:
                        
                }
//            }catch(myException s){
//                System.err.println(s.getMessage());
//            }
        } while (menulogreg.getOpcao()!=0);
    }
    
    
    protected void login(){
        
        Scanner is = new Scanner(System.in);
        String pass;
        boolean login = false;
        
        try{
            System.out.print("Username: ");
            user = is.nextLine();
            System.out.print("Password: ");
            pass = is.nextLine();
            login = c.login(user, pass);

        }catch(myException s){
            System.err.println(s.getMessage());
        }
            
        if(login){
            do{
                try{
                    menumain.executa();
                    switch (menumain.getOpcao()) {
                        case 1: solViagem(user);
                                break;
                        case 2: anunDisp(user);
                                break;
                    }

                }catch(myException s){
                    System.err.println(s.getMessage());
                }
            } while (menumain.getOpcao()!=0);
        }
    }
    
     protected void registar() {
         
        Scanner is = new Scanner(System.in);
        String pass;
        boolean registar = false;
        
        try{
            System.out.print("Username: ");
            user = is.nextLine();
            System.out.print("Password: ");
            pass = is.nextLine();
            registar = c.registar(user, pass);

        }catch(myException s){
            System.err.println(s.getMessage());
        }
            
        if(registar){
            start();
        }
     }
    
    protected void solViagem(String user) throws myException{
        
    }
    
    protected void anunDisp(String user) throws myException{
        
    }
    
    
    protected  void carregarMenus() {
    
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