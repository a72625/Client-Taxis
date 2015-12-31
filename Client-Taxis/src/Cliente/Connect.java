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
public class Connect {

    private Socket socket;
    BufferedReader in;
    PrintWriter out;

    public Connect(Socket socket) throws IOException {
        this.socket = socket;
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        in = new BufferedReader(isr);

        OutputStreamWriter osr = new OutputStreamWriter(socket.getOutputStream());
        out = new PrintWriter(osr);
    }


    private String[] mySplit(String mensagem) {
        String[] str;
        str = mensagem.split(",");
        return str;
    }
    /*public boolean response(String mensagem) throws myException {
     String[] str = mySplit(mensagem);
     int codigo = Integer.parseInt(str[0]);
     boolean resposta = false;
     switch (codigo) {
     case 1:
     responseLogin(str);
     case 2:
     responseRegistarP(str);
     case 3:
     responseRegistarC(str);
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
    
     private boolean responseRegistarP(String[] mensagem) throws myException {
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

     private boolean responseRegistarC(String[] mensagem) throws myException {
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
     }*/

}
