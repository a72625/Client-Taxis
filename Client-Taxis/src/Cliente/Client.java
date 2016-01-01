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
                resposta = responseRegistar(str[1]);
                break;
            case '3':
                resposta = responseSolViagem(str[1]);
                break;
            case '4':
                resposta = resposnseAnunDisp(str[1]);
                break;
            default:
                 throw new myException("Não foi possível efectuar a operação. Tente Novamente");
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
                throw new myException(mensagem);
            case "password errada":
                throw new myException(mensagem);
            default:
                throw new myException("Não foi possível efectuar a operação. Tente Novamente");
       }
        return resposta;
    }

    private boolean responseRegistar(String mensagem) throws myException {
        boolean resposta = false;
        switch (mensagem) {
            case "ok":
                resposta = true;
                break;
            case "user ja existe":
                throw new myException(mensagem);            
            case "impossivel registar":
                throw new myException(mensagem);    
            default:
                throw new myException("Não foi possível efectuar a operação. Tente Novamente");   
        }
        return resposta;
    }

    private boolean responseSolViagem(String mensagem) throws myException{
        boolean resposta = false;
        switch (mensagem) {
            case "condutor atribuido":
                resposta = true;
                break;
            case "veiculo ja se encontra no local de partida":
                resposta = true;
                break;      
            case "veiculo ja chegou ao local de destino":
                resposta = true;
                break;   
            default:
                throw new myException("Não foi possível efectuar a operação. Tente Novamente");   
        }
        return resposta;
    }
    
    private boolean resposnseAnunDisp(String mensagem) throws myException{
        boolean resposta = false;
        switch (mensagem) {
            case "ja foi atribuida uma deslocacao":
                resposta = true;
                break;
            default:
                throw new myException("Não foi possível efectuar a operação. Tente Novamente");   
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
            return response(sResposta);
        }
    }
    
    public boolean solViagem(String username, int x_0, int y_0, int x, int y) throws myException{
        String sResposta = "";
        out.println(3 + "," + username + "," + x_0+ "," + y_0 + "," + x + "," + y);
        try {
            //condutor atribuido
            sResposta = in.readLine();
            response(sResposta);
            //veiculo ja se encontra no local de partida
            sResposta = in.readLine();
            response(sResposta);
            //veiculo ja chegou ao local de destino
            sResposta = in.readLine();
        } catch (IOException ex){
            throw new myException("Não foi possível obter resposta do servidor");
        }finally {
            return response(sResposta);
        }
    }
    
    public boolean anunDisp1(String username, String mat, String mod, int x, int y) throws myException{
        String sResposta = "";
        out.println(4 + "," + username + "," + mat + "," + mod + "," + x + "," + y);
        try {
            sResposta = in.readLine();
        } catch (IOException ex){
            throw new myException("Não foi possível obter resposta do servidor");
        }finally {
            return response(sResposta);
        }
    }
    
    public void anunDisp2() throws myException{
        out.println(4 + "chegou ao local de partida");
    }
    
    public void anunDisp3(float preco) throws myException{
        out.println(4 + "chegou ao local de destino" + "," + preco);
    }
}
