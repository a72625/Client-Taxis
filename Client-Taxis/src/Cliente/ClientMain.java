/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.IOException;

/**
 *
 * @author Diogo Duarte
 */
public class ClientMain{
    
    public static void main(String[] args){
        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        try{
            Client u = new Client(ip,port);
            Interface ui = new Interface(u);
            ui.start();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
