/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Toshiba
 */
public class ClientHandler implements Runnable {

    //private Server server; --- o socket e a porta do servidor devem ser passados como argumento da main do cliente
    private Socket socket;
    private Facade data;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(/*Server server,*/ Socket socket, Facade facade) throws IOException {
        //this.server = server;
        this.socket = socket;
        this.data = facade;
    }

    private void init() throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            String str = "string";
            try {
                init();
            } catch (IOException ex) {
                return;
            }
            int op = -27;
            do {
                switch (op = in.read()) {
                    case 1:
                        data.loginPassageiro(str,str);
                        break;

                    case 2:
                        data.loginCondutor(str,str);
                        break;
                }
            } while (op != 0);
        } catch (IOException | myException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
