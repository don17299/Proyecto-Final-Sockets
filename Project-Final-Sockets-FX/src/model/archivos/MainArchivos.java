package model.archivos;

import java.io.BufferedReader;
import java.io.FileReader;

public class MainArchivos {

    public static void main(String[] args) {
        leerTxt("");
    }

    public static String leerTxt(String direccion){
        String texto="";
        try{
            BufferedReader bf= new BufferedReader(new FileReader(direccion));
            String temp="";
            String bfRead;
            while ((bfRead=bf.readLine())!=null){
                //temp= temp+bfRead;
                System.out.println(bfRead);
            }
            texto=temp;

        }catch (Exception e){
            e.printStackTrace();
        }


        return texto;
    }
}
