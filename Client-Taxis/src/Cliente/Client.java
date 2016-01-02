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

    private boolean responseSolViagem(String mensagem) throws myException {
        boolean resposta = false;
        switch (mensagem) {
            case "condutor atribuido":
                resposta = true;
                break;
            case "nao foi possivel estabelecer viagem":
                throw new myException("Não foi possível efectuar a operação. Tente Novamente");
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
            case "nao foi possivel estabelecer viagem":
                throw new myException("Não foi possível efectuar a operação. Tente Novamente");
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

    /*public boolean solViagem(String username, int x_0, int y_0, int x, int y) throws myException {
     String sResposta = "";
     out.println(3 + "," + username + "," + x_0 + "," + y_0 + "," + x + "," + y);
     try {
     //condutor atribuido
     sResposta = in.readLine();
     } catch (IOException ex) {
     Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
     }
     if (response(sResposta)) {
     try {
     //veiculo ja se encontra no local de partida
     sResposta = in.readLine();
     } catch (IOException ex) {
     Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
     }
     if (response(sResposta)) {
     try {
     //veiculo ja chegou ao local de destino
     sResposta = in.readLine();
     } catch (IOException ex) {
     Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
     }
     return response(sResposta);
     }
     }
     return false;
     }*/
    public String[] SolViagem(String username, int x_0, int y_0, int x, int y) {
        try {
            String sResposta = "";
            out.println(3 + "," + username + "," + x_0 + "," + y_0 + "," + x + "," + y);
            try {
                sResposta = in.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (response(sResposta)) {
                return sResposta.split(",");
            } else {
                return null;
            }
        } catch (myException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean SolViagem2(int codViagem) {
        String sResposta = "";
        try {
            sResposta = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            return response(sResposta);
        } catch (myException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String[] anunDisp1(String username, String mat, String mod, int x, int y) {
        try {
            String sResposta = "";
            out.println(4 + "," + username + "," + mat + "," + mod + "," + x + "," + y);
            try {
                sResposta = in.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (response(sResposta)) {
                return sResposta.split(",");
            } else {
                return null;
            }
        } catch (myException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean anunDisp2(int codigoViagem) throws myException {
        String sResposta = "";
        out.println(5 + "chegou ao local de partida" + codigoViagem);
        try {
            sResposta = in.readLine();
        } catch (IOException ex) {
            throw new myException("Não foi possível obter resposta do servidor");
        } finally {
            return response(sResposta);
        }
    }

    public boolean anunDisp3(float preco, int codigoViagem) throws myException {

        String sResposta = "";
        out.println(7 + "chegou ao local de destino" + "," + preco + codigoViagem);
        try {
            sResposta = in.readLine();
        } catch (IOException ex) {
            throw new myException("Não foi possível obter resposta do servidor");
        } finally {
            return response(sResposta);
        }
    }
}
