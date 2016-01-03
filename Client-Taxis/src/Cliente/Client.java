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

    public void close() throws IOException {
        in.close();
        out.close();
        clientSck.close();
    }

    private String[] mySplit(String mensagem) {
        String[] str;
        str = mensagem.split(",");
        return str;
    }

    //public int[] newResponse(String mensagem) throws myException {
    //}
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
                resposta = responseAnunDisp1(str[1]);
                break;
            case '5':
                resposta = responseAnunDisp2(str[1]);
                break;
            case '6':
                resposta = responseSolViagem2(str[1]);
                break;
            case '7':
                resposta = responseAnunDisp3(str[1]);
                break;
            case '8':
                resposta = responseSolViagem3(str[1]);
                break;
            case '9':
                resposta = responseLogout(str[1]);
                break;
            default:
                throw new myException("Não foi possível efectuar a operação. Tente Novamente");
        }
        return resposta;
    }

    /* public String[] response2(String mensagem) throws myException {
     String[] str = mySplit(mensagem);
     if (Integer.parseInt(str[0]) == 3 && str[1].equals("condutor atribuido")) {
     return str;
     }
     return null;
     }*/
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
            case "user ja esta autenticado":
                throw new myException(mensagem);
            default:
                throw new myException("Não foi possível efectuar a operação. Tente Novamente");
        }
        return resposta;
    }

    private boolean responseLogout(String mensagem) throws myException {
        boolean resposta = false;
        switch (mensagem) {
            case "ok":
                resposta = true;
                break;
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
            default:
                throw new myException("Não foi possível efectuar a operação. Tente Novamente");
        }
        return resposta;
    }

    private boolean responseSolViagem(String mensagem) throws myException {
        boolean resposta = false;
        switch (mensagem) {
            case "condutor atribuido":
                resposta = true;
                break;
            default:
                throw new myException("Não foi possível efectuar a operação. Tente Novamente");
        }
        return resposta;
    }

    private boolean responseAnunDisp1(String mensagem) throws myException {
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

    private boolean responseAnunDisp2(String mensagem) throws myException {
        boolean resposta = false;
        switch (mensagem) {
            case "ok":
                resposta = true;
                break;
            default:
                throw new myException("Não foi possível efectuar a operação. Tente Novamente");
        }
        return resposta;
    }

    private boolean responseAnunDisp3(String mensagem) throws myException {
        boolean resposta = false;
        switch (mensagem) {
            case "ok":
                resposta = true;
                break;
            default:
                throw new myException("Não foi possível efectuar a operação. Tente Novamente");
        }
        return resposta;
    }

    private boolean responseSolViagem2(String mensagem) throws myException {
        boolean resposta = false;
        switch (mensagem) {
            case "chegou ao local de partida":
                resposta = true;
                break;
            default:
                throw new myException("Não foi possível efectuar a operação. Tente Novamente");
        }
        return resposta;
    }

    private boolean responseSolViagem3(String mensagem) throws myException {
        boolean resposta = false;
        switch (mensagem) {
            case "chegou ao local de destino":
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

    public String[] solViagem(String username, int x_0, int y_0, int x, int y) throws myException {
        String sResposta = "";
        out.println(3 + "," + username + "," + x_0 + "," + y_0 + "," + x + "," + y);
        try {
            sResposta = in.readLine();
        } catch (IOException ex) {
            throw new myException("Não foi possível obter resposta do servidor");
        } finally {
            if (response(sResposta)) {
                return sResposta.split(",");
            } else {
                return null;
            }
        }
    }

    public boolean chegouPartidaPassageiro(int codViagem) throws myException {
        String sResposta = "";
        try {
            sResposta = in.readLine();   // chegou ao local de partida
        } catch (IOException ex) {
            throw new myException("Não foi possível obter resposta do servidor");
        } finally {
            return response(sResposta);
        }
    }

    public void chegouPartidaRespostaPassageiro(int codViagem) throws myException {
        out.println(6 + "," + "ok" + "," + codViagem);
    }

    public String[] chegouDestinoPassageiro(int codViagem) throws myException {
        String sResposta = "";
        try {
            sResposta = in.readLine();   // chegou ao local de destino,preco,codigoviagem
        } catch (IOException ex) {
            throw new myException("Não foi possível obter resposta do servidor");
        } finally {
            if (response(sResposta)) {
                return sResposta.split(",");
            } else {
                return null;
            }
        }
    }

    public void chegouDestinoRespostaPassageiro(int codViagem) throws myException {
        out.println(8 + "," + "ok" + "," + codViagem);
    }

    public void chegouPartidaCondutor(int codViagem) throws myException {

        out.println(5 + "," + "chegou ao local de partida" + "," + codViagem);
    }

    public boolean chegouPartidaRespostaCondutor(int codViagem) throws myException {
        String sResposta = "";
        try {
            sResposta = in.readLine();   // ok
        } catch (IOException ex) {
            throw new myException("Não foi possível obter resposta do servidor");
        } finally {
            return response(sResposta);
        }
    }

    public void chegouDestinoCondutor(int codViagem, float preco) throws myException {
        out.println(7 + "," + "chegou ao local de destino" + "," + preco + "," + codViagem);
    }

    public boolean chegouDestinoRespostaCondutor(int codViagem) throws myException {
        String sResposta = "";
        try {
            sResposta = in.readLine();   // ok
        } catch (IOException ex) {
            throw new myException("Não foi possível obter resposta do servidor");
        } finally {
            return response(sResposta);
        }
    }

    public String[] anunDisp1(String username, String mat, String mod, int x, int y) throws myException {
        String sResposta = "";
        out.println(4 + "," + username + "," + mat + "," + mod + "," + x + "," + y);
        try {
            sResposta = in.readLine();
        } catch (IOException ex) {
            throw new myException("Não foi possível obter resposta do servidor");
        } finally {
            if (response(sResposta)) {
                return sResposta.split(",");
            } else {
                return null;
            }
        }
    }

    public boolean logout(String username) throws myException {
        String sResposta = "";
        out.println(9 + "," + username);
        try {
            sResposta = in.readLine();
        } catch (IOException ex) {
            throw new myException("Não foi possível obter resposta do servidor");
        } finally {
            boolean flag = response(sResposta);
            return flag;
        }
    }
}
