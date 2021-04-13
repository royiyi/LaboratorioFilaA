/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Royer
 */
public class Biblioteca {

    private String nombre;
    private String direccion;
    Scanner leer = new Scanner(System.in);

    private List<MaterialBibliografico> library;

    public Biblioteca() {
        library = new ArrayList<>();
    }

    public void create() {
        System.out.println("Digite Nombre:");
        nombre = leer.nextLine();

        System.out.println("Digite la Direccion:");
        nombre = leer.nextLine();

        //***************************
        //String opc;
        boolean next = true;
        do {
            System.out.println("######  Sub Menu  #######");
            System.out.println("1 Registrar LIBRO");
            System.out.println("2 Registrar PROYECTO DE GRADO");
            System.out.println("3 Exit");
            System.out.println("Digite one opcion");
            int opc = leer.nextInt();
            leer.nextLine();

            switch (opc) {
                case 1:
                    Libro book = new Libro();
                    System.out.println("Digite el TItulo");
                    book.setTitulo(leer.nextLine());
                    System.out.println("Digite el Autor");
                    book.setAutor(leer.nextLine());
                    System.out.println("Digite La edicion (one number)");
                    book.setEdicion(leer.nextInt());
                    leer.nextLine();
                    System.out.println("Digite el area");
                    book.setArea(leer.nextLine());
                    library.add(book);
                    break;
                case 2:
                    //op.buscarCan();
                    ProyectoGrado pro = new ProyectoGrado();
                    System.out.println("Digite el TItulo");
                    pro.setTitulo(leer.nextLine());
                    System.out.println("Digite el Autor");
                    pro.setAutor(leer.nextLine());
                    System.out.println("Digite el tutor");
                    pro.setTutor(leer.nextLine());
                    System.out.println("Digite el revisor");
                    pro.setRevisor(leer.nextLine());
                    library.add(pro);
                    break;
                default:
                    next = false;
                    break;
            }
        } while (next);
        //**********************

    }

    public void show() {
        if (!library.isEmpty()) {
            System.out.println("------LISTA DEMATERIAL BIBLIOGRAFICO------");
            for (MaterialBibliografico emp : library) {
                if (emp instanceof Libro) {

                    Libro bo = (Libro) emp;
                    bo.mostrar();
                    System.out.println("-------------");
                } else {

                    ProyectoGrado pr = (ProyectoGrado) emp;
                    pr.mostrar();
                    System.out.println("------------------");
                }
            }
        }
    }

    public void searchBook() {
        //Buscar un determinado libro por titulo
        System.out.println("DIGITE EL TITULO DEL LIBRO:");
        String title = leer.nextLine();
        boolean ss = false;
        //*************
        for (MaterialBibliografico emp : library) {
            if (emp.getTitulo().equalsIgnoreCase(title)) {
                ss = true;
                System.out.println("++++ENCONTRADO++++");
                emp.mostrar();
            }
        }
        if (!ss) {
            System.out.println("NO EXISTE");
        }

    }

    public void RevisorSearch() {

        System.out.println("DIGITE EL REVISOR:");
        String revisor = leer.nextLine();

        boolean ff = false;
        //*************
        for (MaterialBibliografico emp : library) {

            if (emp instanceof ProyectoGrado) {
                ProyectoGrado pr = (ProyectoGrado) emp;
                if (pr.getRevisor().equalsIgnoreCase(revisor)) {
                    System.out.println("++++ENCONTRADO++++");
                    pr.mostrar();
                    ff = true;

                    break;
                }
            }

        }
        /*  if (!ff) {
            System.out.print("NO EXISTE");
        }*/
        System.out.print((!ff) ? "\nNO EXISTE" : "");

    }

    public void DropBookProject() {
        System.out.println("DIGITE EL Projecto o libro a Eliminar:(titulo)");
        String re = leer.nextLine();
        boolean ff = false;

        for (MaterialBibliografico emp : library) {
            if (emp instanceof Libro) {

                Libro bo = (Libro) emp;

                if (bo.getTitulo().equalsIgnoreCase(re)) {
                    //bo.mostrar();
                    ff = true;
                    library.remove(bo);

                    System.out.println("------ELIMINADO-------");
                    break;
                }

            } else {
                ProyectoGrado pr = (ProyectoGrado) emp;
                if (pr.getTitulo().equalsIgnoreCase(re)) {
                    //pr.mostrar();
                    library.remove(pr);
                    ff = true;
                    System.out.println("------ELIMINADO-------");
                    break;
                }

            }

        }
        System.out.print((!ff) ? "\nNO EXISTE" : "");

    }

    /*++++++++++++MANEJO DE ARCHIVOS++++++++++++++++*/

    //CREANDO UN ARCHIVO
    public void crearArchivo() {
        //ya debe estar creado el directorio D:\\programacionIII
        Path path = Paths.get("D:\\programacionIII\\archivoBiblio.txt");
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("El archivo se creo correctamente");
            } else {
                System.out.println("El archivo ya existe");
            }
        } catch (Exception e) {
        }
    }
    
    
    //guardando la listacliente dentro del archivo creado con anterioridad
    public void guardarObjetos() {
        String ruta = "D:\\programacionIII\\archivoBiblio.txt";
        try {
            //****SERIALIZAN(BITS) PARA QUE SE GUARDE DENTRO EL ARCHIVO*****
            FileOutputStream archivo = new FileOutputStream(ruta);//INIALIZAMOS EL ARCHIVO
            ObjectOutputStream oos = new ObjectOutputStream(archivo);//podamos guardar dentro del archivo
            //*******
            oos.writeObject(library);
            oos.close();
            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    //cargamos los datos del archivo a un list<Cliente>
    public void leerObjetos() {
        String ruta = "D:\\programacionIII\\archivoBiblio.txt";
        try {

            FileInputStream archivo = new FileInputStream(ruta);
            ObjectInputStream ois = new ObjectInputStream(archivo);

            if (ois != null) {
                library = (List<MaterialBibliografico>) ois.readObject();//realizamos un casteo
            } else {
                System.out.println("El objeto es nulo");
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
