/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diogo Duarte
 */
public class Client implements Facade {

    private Socket clientSck;
    private Connect c;

    public Client(String ip, int port) throws IOException {
        try {
            clientSck = new Socket(ip, port);
        } catch (java.net.ConnectException a) {
            throw new IOException("Servidor não disponível");
        }
        c = new Connect(clientSck);
    }

    private String[] mySplit(String mensagem) {
        String[] str;
        str = mensagem.split(",");
        return str;
    }
    
    public boolean response(String mensagem) throws myException {
        String[] str = mySplit(mensagem);
        int codigo = Integer.parseInt(str[0]);
        boolean resposta = false;
        switch (codigo) {
            case 1:
                responseLogin(str);
            case 2:
                responseRegistar(str);
        }
        return;
    }

    private boolean responseLogin(String[] mensagem) throws myException {
        boolean resposta = false;
        switch (mensagem[1]) {
            case "password errada":
                resposta = false;
                throw new myException(mensagem[1]);
            case "user nao existe":
                resposta = false;
                throw new myException(mensagem[1]);
            case "ok":
                resposta = true;
                break;
        }
        return resposta;
    }

    private boolean responseRegistar(String[] mensagem) throws myException {
        boolean resposta = false;
        switch (mensagem[1]) {
            case "user ja existe":
                resposta = false;
                throw new myException(mensagem[1]);
            case "impossivel registar":
                resposta = false;
                throw new myException(mensagem[1]);
        }
        return resposta;
    }

    @Override
    public Boolean login(String username, String password) throws myException {
        boolean resposta = false;
        c.out.println(1 + "," + 2 + "," + username + "," + password);
        return true;
        try {
            resposta = c.response(c.in.readLine());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Boolean addPassageiro(String username, String password) throws myException {
        //c.out.println(3 + "," + username + "," + password);
        return true;
    }

    @Override
    public Boolean addCondutor(String username, String password, String mat, String mod) throws myException {
        //c.out.println(3 + "," + username + "," + password + "," + mat + "," + mod);
        return true;
    }

    @Override
    public Boolean passageiroExiste(String username) throws myException {
        //out.print(5 + " ");
        //out.print(username);
        //out.flush();
        return true;
    }

    @Override
    public Boolean condutorExiste(String username) throws myException {
        //out.print(6 + " ");
        //out.print(username);
        //out.flush();
        return true;
    }
}
