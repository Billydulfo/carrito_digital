/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.Random;
/**
 *
 * @author Jhon Nash
 */
/*Constructor de Clase catalogo*/
public class Catalogo {
    private Pelicula pelicula;/*Variable pelicula*/
    /*Costructor de la clase catalogo*/
    public Catalogo() {
        pelicula = new Pelicula();
    }   
    /*metodo que genera el codigo de barras*/
    public String generaCodigodeBarras(){
        String codigo;
        Random rnd;
        rnd =new Random();
        int rndX;
        int rndY;
        int rndW;    
        rndX =rnd.nextInt(9999);
        rndY =rnd.nextInt(9999);
        rndW =rnd.nextInt(9999);
        codigo =rndX +"-" +rndY +"-" +rndW +"";   
        return codigo;
    } 
    /*Metodo que registra una pelicula*/
    public boolean adicionarPelicula(String codigo ,String nombre 
            ,String actorPrincipal ,String genero ,String precio){
        boolean bandera;
        bandera =false;
        pelicula.setNombre(nombre);
        pelicula.setActorPrincipal(actorPrincipal);
        pelicula.setGenero(genero);
        pelicula.setPrecio(Double.parseDouble(precio));
        pelicula.setCodigo(this.generaCodigodeBarras());
        return bandera;
    }
    public Pelicula desplegarInfoPelicula(Pelicula pelicula){
        return pelicula;
    }

    /**
     *
     * @return pelicula
     */
    public Pelicula getPelicula(){
        return pelicula;
    }
}
