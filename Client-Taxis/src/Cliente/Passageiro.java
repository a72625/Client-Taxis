/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author rcamposinhos
 */
public class Passageiro implements autoClose{
    private String user;
    private String pass;
    private Local posicao;
    private Local destino;
    private final ReentrantLock lock = new ReentrantLock();
    private Condition cond;
    

    public Passageiro(String u, String p) {
        this.user = u;
        this.pass = p;
    }
    
    public Passageiro(Passageiro p){
        this.user = p.getUser();
        this.pass = p.getPass();
        this.posicao = p.getPosicao();
        this.destino = p.getDestino();
        //this.lock = p.getLock();
        //this.cond
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public Local getPosicao() {
        return new Local(posicao);
    }
    
    public Local getDestino() {
        return new Local(destino);
    }
    
    public ReentrantLock getLock(){
        return this.lock;
    }
    
    public void block() throws InterruptedException {
        cond.await();
    }

    public void unblock() {
        cond.signal();
    }
    
    @Override
    public String toString() {
        return "Passageiro{" + "nome=" + this.getUser() + ", posicao=" +
                this.getPosicao() + '}';
    }

    @Override
    public void close() throws myException {
        lock.unlock();
    }

    
    
    
    
    
}
