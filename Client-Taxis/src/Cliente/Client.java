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

/**
 *
 * @author Diogo Duarte
 */
public class Client{

    private Socket clientSck;
    private BufferedReader in;
    private PrintWriter out;

    public Client() throws IOException {
        try {
            clientSck = new Socket("localhost",2000);
        } catch (java.net.ConnectException a) {
            throw new IOException("Servidor não disponível");
        }
        out = new PrintWriter(clientSck.getOutputStream(),true);
        in = new BufferedReader(new InputStreamReader(clientSck.getInputStream()));
    }

    private String[] mySplit(String mensagem) {
        String[] str;
        str = mensagem.split(",");
        return str;
    }
    
    public boolean response(String mensagem){
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

    private boolean responseLogin(String mensagem){
        boolean resposta = false;
        if(mensagem.equals("ok")){
            resposta = true;
        }
        return resposta;
    }

    private boolean responseRegistar(String[] mensagem){
        boolean resposta = false;
        switch (mensagem[1]) {
            case "user ja existe":
                resposta = false;
                //throw new myException(mensagem[1]);
            case "impossivel registar":
                resposta = false;
                //throw new myException(mensagem[1]);
        }
        return resposta;
    }

    public boolean login(String username, String password) throws IOException{
        System.out.println("asd");
        boolean resposta = false;
        out.println(1 + "," + username + "," + password+","+"");
        
        resposta = response(in.readLine());
        return resposta;
    }
}
