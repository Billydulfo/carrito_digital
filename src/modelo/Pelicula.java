/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Jhon Nash
 */
public class Pelicula {
    private String codigo;
    private String nombre;
    private String actorPrincipal;
    private String genero;
    private double precio;
    
    /*Es un constructor de clase Pelicula*/
    public Pelicula(){
        this.codigo =null;
        this.nombre =null;
        this.actorPrincipal =null;
        this.genero =null;
        this.precio =0.0;        
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getActorPrincipal() {
        return actorPrincipal;
    }

    public void setActorPrincipal(String actorPrincipal) {
        this.actorPrincipal = actorPrincipal;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     *
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    @Override
    public String toString() {
        return "Pelicula{" +"codigo=" +codigo +", nombre=" +nombre 
                +", actorPrincipal=" +actorPrincipal 
                +", genero=" + genero + ", precio=" 
                +precio +'}';
    }   
}
