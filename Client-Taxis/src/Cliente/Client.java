/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diogo Duarte
 */
public class Client {

    private Socket clientSck;
    private BufferedReader in;
    private PrintWriter out;

    public Client() throws IOException {
        try {
            clientSck = new Socket("localhost", 2000);
        } catch (java.net.ConnectException a) {
            throw new IOException("Servidor não disponível");
        }
        out = new PrintWriter(clientSck.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSck.getInputStream()));
    }

    private String[] mySplit(String mensagem) {
        String[] str;
        str = mensagem.split(",");
        return str;
    }

    public boolean response(String mensagem) throws myException {
        String[] str = mySplit(mensagem);
        //int codigo = Integer.parseInt(str[0]);
        char codigo = str[0].charAt(0);
        boolean resposta = false;
        switch (codigo) {
            case '1':
                resposta = responseLogin(str[1]);
                break;
            case '2':
                resposta = responseRegistar(str);
                break;
        }
        return resposta;
    }

    private boolean responseLogin(String mensagem) throws myException {
        boolean resposta = false;
        switch (mensagem) {
            case "ok":
                resposta = true;
                break;   
            case "user nao existe":
                throw new myException("user nao existe");
            case "password errada":
                throw new myException("Password errada");
            default:
                throw new myException("Não foi possível efectuar a operação. Tente Novamente");
       }
        return resposta;
    }

    private boolean responseRegistar(String[] mensagem) throws myException {
        boolean resposta = false;
        switch (mensagem[1]) {
            case "ok":
                resposta = true;
                break;
            case "user ja existe":
                resposta = false;
                //throw new myException(mensagem[1]);
                break;
            case "impossivel registar":
                resposta = false;
                //throw new myException(mensagem[1]);
                break;
            default:
        }
        return resposta;
    }

    public boolean login(String username, String password) throws myException {
        String sResposta = "";
        out.println(1 + "," + username + "," + password);
        try {
            sResposta = in.readLine();
        } catch (IOException ex) {
            throw new myException("Não foi possível obter resposta do servidor");
        } finally {
            return response(sResposta);
        }
    }

    public boolean registar(String username, String password) throws myException {
        String sResposta = "";
        out.println(2 + "," + username + "," + password);
        try {
            sResposta = in.readLine();
        } catch (IOException ex) {
            throw new myException("Não foi possível obter resposta do servidor");
        } finally {
            System.out.println(response(sResposta));
            return response(sResposta);
        }
    }
}
