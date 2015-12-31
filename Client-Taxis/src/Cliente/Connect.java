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
    private String[] args;
    private int argsIndex;

    public Connect(Socket socket) throws IOException {
        this.socket = socket;
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        in = new BufferedReader(isr);

        OutputStreamWriter osr = new OutputStreamWriter(socket.getOutputStream());
        out = new PrintWriter(osr);
    }

    public int readMessage() throws myException {
        try {
            String aux = in.readLine();

            if (aux == null) {
                socket.shutdownOutput();
                socket.shutdownInput();
                return -1;
            }

            String[] str = this.mySplit(aux);

            int codigo, size;

            codigo = Integer.parseInt(str[0]);
            size = Integer.parseInt(str[1]);
            args = new String[size];

            for (int i = 0; i < size && i + 2 < str.length; i++) {
                args[i] = str[i + 2];
            }
            argsIndex = 0;
            return codigo;

        } catch (IOException | NumberFormatException ex) {
            throw new myException("Erro a ler");
        }
    }

    public String getString(int index, String str) throws myException {
        if (index < args.length && index >= 0) {
            return args[index];
        }
        throw new myException("Numero de argumentos inválido");
    }

    public String pop(String str) throws myException {
        return getString(argsIndex++, str);
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
