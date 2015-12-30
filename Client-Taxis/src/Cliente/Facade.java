/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

/** O objectivo desta interface é definir o que uma UI pode precisar para
 * imprimir ao longo da sua execução
 * Dai tentar retornar sempre tipos simples.
 * Esta interface será tambem usada por as camadas de comunicação
 *
 * @author Diogo Duarte
 */
public interface Facade {
    
    public Boolean login(String username, String password)throws myException;
   
    public Boolean addPassageiro(String username, String password)throws myException;
   
    public Boolean addCondutor(String username, String password, String mat, String mod)throws myException;

    public Boolean passageiroExiste(String usern)throws myException;

    public Boolean condutorExiste(String usern)throws myException;
}
