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
 * @author Diogo Duarte
 */
public class Client implements Facade {

    private Socket server;
    BufferedReader in;
    PrintWriter out;

    public Client(String ip,int port) throws IOException, myException {
        try {
            server = new Socket("localhost", 2000);
        } catch (java.net.ConnectException a) {
            throw new myException("Servidor não está disponivel");
        }
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        out = new PrintWriter(server.getOutputStream(), true);
    }

    @Override
    public Boolean loginPassageiro(String username,String password) throws myException {
        out.print(1+","+username+","+password+"");
        out.flush();
        try {
            in.read();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Boolean loginCondutor(String username,String password) throws myException {
        out.print(2+","+username+","+password+"");
        out.flush();
        return true;
    }

    @Override
    public Boolean addPassageiro(String username, String password) throws myException {
        out.print(3+","+username+","+password+"");
        out.flush();
        return true;
    }

    @Override
    public Boolean addCondutor(String username, String password, String mat, String mod) throws myException {
        out.print(4+" ");
        out.print(username+" ");
        out.print(password+" ");
        out.print(mat+" ");
        out.print(mod);
        out.flush();
        return true;    }

    @Override
    public Boolean passageiroExiste(String username) throws myException {
        out.print(5+" ");
        out.print(username);
        out.flush();
        return true;    }

    @Override
    public Boolean condutorExiste(String username) throws myException {
        out.print(6+" ");
        out.print(username);
        out.flush();
        return true;    
    }
}
