/*
 * ClientServerCommApp.java
 */

package clientservercomm;
import java.awt.Dialog;
import java.net.*;
import java.io.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;


/**
 * The main class of the application.
 */
public class ClientServerCommApp extends SingleFrameApplication {
int gcd(int x,int y)
{
    return y==0? x:gcd(y,x%y); 
}
static int multi(int txt, int ed, int n)
{ int i,rem=1;
for(i=1; i<=ed; i++)
rem=(rem*txt)%n;
return rem;
}
short prime(int no)
{ int i;
for(i=2; i<=no/2; i++)
if(no%i==0) return 1;
return 0;
}

int p=101,q=103;
public static int n=101*103;
int z=(p-1)*(q-1);
public static int e=107;
/**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new ClientServerCommView(this));
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
     * @return the instance of ClientServerCommApp
     */
    public static ClientServerCommApp getApplication() {
        return Application.getInstance(ClientServerCommApp.class);
    }

    /**
     * Main method launching the application.
     */
    
    public static byte [] mybytearray;
    public static void main(String[] args) throws IOException {
        launch(ClientServerCommApp.class, args);
//        ServerSocket s=null;
//        try {
//            s=new ServerSocket(5432);
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
//        while(true) {
//            try {
//                Socket s1=s.accept();
//                OutputStream s1out=s1.getOutputStream();
//                BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(s1out));
//                //bw.write("Hello!");
//                JOptionPane.showMessageDialog(null,"Client Connected!");
//                bw.close();
//                s1.close();
//                final JFileChooser jfc1=new JFileChooser();
//            } catch(IOException e) {
//                e.printStackTrace();
//            }
//        }
        
        
        
        FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    ServerSocket servsock = null;
    Socket sock = null;
    try {
      servsock = new ServerSocket(5432);
      while (true) {
        System.out.println("Waiting...");
        try {
          sock = servsock.accept();
          System.out.println("Accepted connection : " + sock);
          // send file
          File myFile = new File ("C:\\Users\\Sourabh Shenoy\\Desktop\\abc.txt");
          File cipherfile=new File("C:\\Users\\Sourabh Shenoy\\Desktop\\cipher.txt");
          FileReader fr=null;
          FileWriter fw=null;
          
          
          
                fr = new FileReader(myFile);
                fw = new FileWriter(cipherfile);

               int ch;
               while((ch=fr.read())!=-1){
                   ch++;
                   fw.write(ch);
               }
                      fr.close();
                      fw.close();
             
          
          
          //File myFile = new File (ClientServerCommView.pubfile);
         // JOptionPane.showMessageDialog(null,"Title","Hey",JOptionPane.PLAIN_MESSAGE);
          mybytearray  = new byte [(int)cipherfile.length()];
          fis = new FileInputStream(cipherfile);
          bis = new BufferedInputStream(fis);
          bis.read(mybytearray,0,mybytearray.length);
          
            //String nss=null;   
          //BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Sourabh Shenoy\\Desktop\\abc.txt")); 
            //String ns=in.readLine();

            //while (ns != null) {
              //  int k=multi(Integer.parseInt(ns),e,n);
          //  System.out.println(n);
              //  nss=mybytearray.toString();
                //int k=multi(Integer.parseInt(nss),e,n);
                //ns=in.readLine();
        //}
     
          

          
          
          
          
          
          //pass param and Encrypt here and then send and also check in pcap file with and without encryption
          
          os = sock.getOutputStream();
          System.out.println("Sending " + "abc.txt" + "(" + mybytearray.length + " bytes)");
         // System.out.println("Sending " + ClientServerCommView.pubfile + "(" + mybytearray.length + " bytes)");
          //os.write(k);
          os.write(mybytearray,0,mybytearray.length);
          os.flush();
          System.out.println("Done.");
        } catch (IOException ex) {
          System.out.println(ex.getMessage()+": An Inbound Connection Was Not Resolved");
        }
         finally {
          if (bis != null) bis.close();
          if (os != null) os.close();
          if (sock!=null) sock.close();
        }
      }
    }
    finally {
      if (servsock != null)
        servsock.close();
    }
  }
}
