/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Scanner;

/**
 *
 * @author Royer
 */
public class Main {
     public static void main(String[] args) {
        
           boolean next = true;
        int opc = 0;
        Scanner read = new Scanner(System.in);
        Biblioteca op= new Biblioteca();
        do {
            System.out.println("######  Menu  #######");
            System.out.println("1 Registrar Biblioteca y Adicionar materialBibilografica");
            System.out.println("2 Mostrar todos los libros o proyectos de la BIblioteca");
            System.out.println("3 Buscar un libro por determinado titulo");
            System.out.println("4 Buscar proyecto por determinado revisor");
            
            System.out.println("5 ELIMINAR proyecto o libro");
            System.out.println("---files---");
            System.out.println("6 Crear archivo");
          //  System.out.println("7 Guardar los registros");
           
            System.out.println("8 Exit");
            System.out.println("Digite one opcion");
            opc = read.nextInt();

            switch (opc) {
                case 1:
                    op.leerObjetos();
                    op.create();
                    op.guardarObjetos();
                    break;
                case 2:
                    op.leerObjetos();
                    op.show();
                    break;
                case 3:
                    op.leerObjetos();
                    op.searchBook();
                    break;
                case 4:
                    op.leerObjetos();
                    op.RevisorSearch();
                    break;
                case 5:
                    op.leerObjetos();
                    op.DropBookProject();
                    op.guardarObjetos();
                    break;
                case 6:
                    op.crearArchivo();
                    break;
                case 7:
                    op.guardarObjetos();
                    break;
                default:
                    next = false;
                    break;
            }
        } while (next);
    }
    
}
