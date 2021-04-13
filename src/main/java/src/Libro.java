/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.Serializable;

/**
 *
 * @author Royer
 */
public class Libro extends MaterialBibliografico  implements Serializable{
     static final long serialVersionUID=43L;
    private int edicion;
    private String area;
    
    @Override
    public void mostrar(){
        super.mostrar();
        System.out.println("Edicion:"+edicion);
        System.out.println("Area: "+area);
    }
   


    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
    
}
