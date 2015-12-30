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

/**
 *
 * @author Diogo Duarte
 */
public class Client implements Facade {

    private Socket user;
    BufferedReader in;
    PrintWriter out;

    public Client() throws IOException, myException {
        try {
            user = new Socket("localhost", 2000);
        } catch (java.net.ConnectException a) {
            throw new myException("Servidor não está disponivel");
        }
        in = new BufferedReader(new InputStreamReader(user.getInputStream()));
        out = new PrintWriter(user.getOutputStream(), true);
    }

    @Override
    public Boolean loginPassageiro(String username, String password) throws myException {
        out.print(1 + " ");
        out.print(username + " ");
        out.print(password);
        out.flush();
        return true;
    }

    @Override
    public Boolean loginCondutor(String username, String password) throws myException {
        out.print(2);
        return true;
    }

    @Override
    public Boolean addPassageiro() throws myException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean addCondutor(String username, String password, String mat, String mod) throws myException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
