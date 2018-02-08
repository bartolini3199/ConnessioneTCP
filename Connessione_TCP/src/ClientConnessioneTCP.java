/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 *
 * @author Luca Bartolini
 */


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;

public class ClientConnessioneTCP {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        //oggetto da usare per realizzare la connessione TCP
        Socket connection = null;
        //nome o IP del server
        String serverAddress = "localhost";
        //porta del server in ascolto
        int port = 2000;
        
        DataOutputStream dataOut;
        DataInputStream dataIn;
        //apertura della connessione al server sulla porta specificata
        try{
            connection = new Socket(serverAddress, port);
            System.out.println("Connessione aperta");
            dataOut=new DataOutputStream(connection.getOutputStream());
            dataOut.writeUTF("Dammi la data");
            dataOut.flush();
            //dataOut.close();
            dataIn=new DataInputStream(connection.getInputStream());
            System.out.println(dataIn.readUTF());
            //dataIn.close();
        }
        catch(ConnectException e){
            System.err.println("Server non disponibile!");
        }
        catch(UnknownHostException e1){
            System.err.println("Errore DNS!");
        }

        catch(IOException e2){//
            System.err.println(e2);
            e2.printStackTrace();
        }
    
        
        
        //chiusura della connnessione
        finally{
                try {
            if (connection!=null)
                {
                    connection.close();
                    System.out.println("Connessione chiusa!");
                }
            }
            catch(IOException e){
                System.err.println("Errore nella chiusura della connessione!");
            }
        }
    }
}