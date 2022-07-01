package sdfsubmission.task2;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        boolean stop = false;
        InputStream is = null;
        OutputStream os = null;
        ObjectInputStream dis;
        ObjectOutputStream dos;
        List<Float> numList = new LinkedList<>();
        try{
            while(!stop){
                Socket sock = new Socket("68.183.239.26",80); //IP 68.183.239.26
                System.out.println("connected");
                os = sock.getOutputStream();
                dos = new ObjectOutputStream(os);
                is = sock.getInputStream();
                dis = new ObjectInputStream(is);
                //TODO create objectoutputstream/inputstream ???????
                System.out.println("asdasd");
                String inputfromServer = dis.readUTF();
                System.out.println("asdasd");
                System.out.println(inputfromServer);
                String[] inputList = inputfromServer.split(" ");
                String reqID = inputList[0];
                System.out.println(reqID);
                String[] numListString = inputList[1].split(",");
                for (int i=0;i<numListString.length;i++){
                    numList.add(Float.parseFloat(numListString[i]));
                }
                float sumofnum = 0;
                for (int i=0; i<numList.size();i++){
                    sumofnum += numList.get(i);
                }
                float averagenum = sumofnum/(numList.size());
                dos.writeUTF(reqID);
                dos.writeUTF("Lim Wei Shun");
                dos.writeUTF("limw0187@e.ntu.edu.sg");
                dos.writeFloat(averagenum);
                dos.flush();
                Boolean responseBoolTrue = dis.readBoolean();
                if (responseBoolTrue){
                    System.out.println("SUCCESS");
                    sock.close();
                    stop = true;
                }
                if (!responseBoolTrue){
                    String responseFail = dis.readUTF();
                    System.out.println("FAILED");
                    System.out.println(responseFail);
                    sock.close();
                    stop = true;
                }
            }
        }catch(Exception e){
            System.out.println("exception met");
            e.printStackTrace();
        }
    }
}
