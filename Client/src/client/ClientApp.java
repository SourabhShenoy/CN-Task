/*
 * ClientApp.java
 */

package client;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.*;
import java.io.*;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class ClientApp extends SingleFrameApplication {
static int multi(int txt, int ed, int n)
{ int i,rem=1;
for(i=1; i<=ed; i++)
rem=(rem*txt)%n;
return rem;
}
int p=101,q=103;
public static int n=101*103;
int z=(p-1)*(q-1);
public static int e=107;
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new ClientView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ClientApp
     */
    public static ClientApp getApplication() {
        return Application.getInstance(ClientApp.class);
    }

    /**
     * Main method launching the application.
     */
    
    public final static int FILE_SIZE = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        launch(ClientApp.class, args);
//        try {
//            Socket s1=new Socket("127.0.0.1",5432);
//            InputStream is=s1.getInputStream();
//            DataInputStream dis=new DataInputStream(is);
//            System.out.println(dis.readUTF());
//            //JOptionPane.showMessageDialog(null, dis.readUTF());
//            is.close();
//            s1.close();
//        } catch (ConnectException connExc) {
//            JOptionPane.showMessageDialog(null, "Couldn't connect");
//        } catch (IOException e) {
//         //Do Nothing
//        }
int bytesRead;
    int current = 0;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    Socket sock = null;
    try {
      sock = new Socket("127.0.0.1", 5432);
      System.out.println("Connecting...");

      // receive file
      byte [] mybytearray  = new byte [2000];
      InputStream is = sock.getInputStream();
      fos = new FileOutputStream("C:\\Users\\Sourabh Shenoy\\Desktop\\File.txt");
      bos = new BufferedOutputStream(fos);
      bytesRead = is.read(mybytearray,0,mybytearray.length);
      current = bytesRead;
      //System.out.println(mybytearray);
      do {
         bytesRead =
            is.read(mybytearray, current, (mybytearray.length-current));
         if(bytesRead >= 0) current += bytesRead;
      } while(bytesRead > -1);

      bos.write(mybytearray, 0 , current);
      bos.flush();
      System.out.println("File " + "abc.txt"
          + " downloaded (" + current + " bytes read)");
      
      //Write func to decrypt here

      File myFile = new File ("C:\\Users\\Sourabh Shenoy\\Desktop\\File.txt");
          File cipherfile=new File("C:\\Users\\Sourabh Shenoy\\Desktop\\finalresult.txt");
          FileReader fr=null;
          FileWriter fw=null;
          
          
          
                fr = new FileReader(myFile);
                fw = new FileWriter(cipherfile);

               int ch;
               while((ch=fr.read())!=-1){
                   ch--;
                   fw.write(ch);
               }
                      fr.close();
                      fw.close();
             
          
      
      
      
      
      //display file contents
      BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Sourabh Shenoy\\Desktop\\finalresult.txt")); 
      String a=in.readLine();
        while (a != null) {
      System.out.println(a);
      a=in.readLine();
}         
in.close();
      
    }
    finally {
      if (fos != null) fos.close();
      if (bos != null) bos.close();
      if (sock != null) sock.close();
    }
  }    
}
