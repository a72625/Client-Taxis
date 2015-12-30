/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

/**
 *
 * @author rcamposinhos
 */
public class Local {
    private int x;//em metros
    private int y;//em metros
    
    public Local(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Local(){
        this.x = 0;
        this.y = 0;
    }
    
    public Local(Local l){
        this.x = l.getX();
        this.y = l.getY();
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.x;
        hash = 41 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Local other = (Local) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public Local clone() {
        return new Local(this);
    }

    @Override
    public String toString() {
        return ("<x = " + this.x + ", y = " + this.y + ">"); 
    }
    
    /**
    * Manhatan distance
    * @return distancia a percorrer em metros 
    */
    public int manDist(Local l){
        return (Math.abs(l.getX() - this.x) + Math.abs(l.getY() - this.y));
    }
    
    
    
}
