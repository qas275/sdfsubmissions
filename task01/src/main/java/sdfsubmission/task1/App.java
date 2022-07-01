package sdfsubmission.task1;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
        String csvFileName = args[0];
        String templateFileName = args[1];
        System.out.println(templateFileName);
        String csvline  = "";
        String templateline = "";
        List<String> csvLinesList = new LinkedList<>();
        String templateText = "";
        File csvFile = new File(csvFileName);
        File templatFile = new File(templateFileName);
        ArrayList<ArrayList<String>> allInfo = new ArrayList<ArrayList<String>>();
        try{
            FileReader frcsv = new FileReader(csvFile);
            BufferedReader brcsv = new BufferedReader(frcsv);
            while((csvline=brcsv.readLine())!=null){
                csvLinesList.add(csvline);
            }
            FileReader frTemplate = new FileReader(templatFile);
            BufferedReader brTemplate = new BufferedReader(frTemplate);
            while((templateline=brTemplate.readLine())!=null){
                templateText+=templateline+"\n";
            }
            frTemplate.close();
            brTemplate.close();
            frcsv.close();
            brcsv.close();
            System.out.println(templateText);
        }catch(IOException e){
            System.out.println("IO error met");
            e.printStackTrace();
        }
        for (String item:csvLinesList){
            System.out.println(item);
            String[] splitItem = item.split(",");
            ArrayList<String> tempList = new ArrayList<String>();
            for(int i=0;i<splitItem.length;i++){
                tempList.add(splitItem[i]);
            }
            allInfo.add(tempList);
            
        }
        System.out.println(allInfo);
        String result = "";
        for (int i=1;i<(allInfo.size());i++){
            result = templateText;
            ArrayList<String> varNames = allInfo.get(0);
            for (int j=0; j<varNames.size();j++){
                if (!varNames.get(j).equals("years")){
                    result = result.replace(varNames.get(j), allInfo.get(i).get(j));
                }else{
                    result = result.replaceFirst(varNames.get(j), allInfo.get(i).get(j));
                }
            }
            result = result.replace("__", "");
            result = result.replace("\\n", "\n");
            System.out.println(result);
        }
        
        //TODO write to txt file????
    }
}

