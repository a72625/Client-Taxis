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
    
    public static void main(String[] args) throws IOException, myException{
        Client u = new Client();
        Interface ui = new Interface(u);
        ui.start();
    }
}
