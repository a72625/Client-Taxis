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
public class ClientMain {

    public static void main(String[] args) {
        int port;
        if (args.length == 0) {
            port = 2000;//2000 por omissao
            System.out.println("Atribuída porta 2000.");
        } else {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Erro a ler a porta. Atribuída porta 2000.");
                port = 2000;
            }
        }
        try {
            Client u = new Client(port);
            Interface ui = new Interface(u);
            ui.start();
            u.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
