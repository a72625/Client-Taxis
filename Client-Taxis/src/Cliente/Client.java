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
public class Client{

    private Socket clientSck;
    private BufferedReader in;
    private PrintWriter out;

    public Client(String ip, int port) throws IOException {
        try {
            clientSck = new Socket(ip, port);
        } catch (java.net.ConnectException a) {
            throw new IOException("Servidor não disponível");
        }
        InputStreamReader isr = new InputStreamReader(clientSck.getInputStream());
        in = new BufferedReader(isr);

        OutputStreamWriter osr = new OutputStreamWriter(clientSck.getOutputStream());
        out = new PrintWriter(osr);
    }

    private String[] mySplit(String mensagem) {
        String[] str;
        str = mensagem.split(",");
        return str;
    }
    
    public boolean response(String mensagem) throws myException {
        System.out.println(mensagem);
        String[] str = mySplit(mensagem);
        int codigo = Integer.parseInt(str[0]);
        boolean resposta = false;
        switch (codigo) {
            case 1:
                resposta = responseLogin(str[1]);
            case 2:
                resposta = responseRegistar(str);
        }
        return resposta;
    }

    private boolean responseLogin(String mensagem) throws myException {
        boolean resposta = false;
        if(mensagem.equals("ok")){
            resposta = true;
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

    public Boolean login(String username, String password) throws myException{
        boolean resposta = false;
        out.println(1 + "," + username + "," + password);
        try {
            resposta = response(in.readLine());
        } catch (IOException ex) {
            //throw new myException("Impossivel obter resposta do servidor");
        }
        return resposta;
    }
}
