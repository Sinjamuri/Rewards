package com.example.demo;

import com.example.model.Summary;
import com.example.model.Transaction;
import com.google.gson.Gson;
import com.sun.java.accessibility.util.Translator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
        try {
            // read the input json file which has transactions for each customer in three month period
            Transaction[] transactions = readInputFile();
            // Calculate reward amount for each transaction
            transactions =processRewards(transactions);
            //Calculate Rewards by month  and for three month totals
            List<Transaction> transactionList = new ArrayList<Transaction>(Arrays.asList(transactions));
            Map<String, List<Transaction>> nameMap = transactionList.stream().collect(Collectors.groupingBy(Transaction::getName));
            List<Summary> summaryList = calculateTotals(nameMap);
            //Create file with Summary result
            writeOutputFile(summaryList);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static Transaction[] readInputFile() throws Exception{
        String inputData = readFileIntoString("./src/main/resources/input.json");
        Transaction[] transactions = new Gson().fromJson(inputData,Transaction[].class);
        return transactions;
    }


    public static String readFileIntoString(String filePath) throws Exception {
        File file = new File(filePath);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            return new String(data, "UTF-8");
        } else {
            return null;
        }
    }

    public static Transaction[] processRewards(Transaction[] transactions){
        for (Transaction item:transactions) {

            int reward=0;
            int amount= item.getAmount();
            if(amount > 50){
                reward = reward+50;
            }
            if(amount>100){
                reward=reward+((amount-100)*2);
            }
            item.setReward(reward);
        }
        return transactions;
    }

    public static void writeOutputFile(List<Summary> summaryList) throws Exception{
        FileWriter  fw = new FileWriter("./src/main/resources/summary.json");
        try {
            fw.write(new Gson().toJson(summaryList));
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                fw.flush();
                fw.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public static List<Summary> calculateTotals(Map<String, List<Transaction>> nameMap){
        List<Summary> summaryList = new ArrayList<>();
        for(Map.Entry<String, List<Transaction>> kvPair : nameMap.entrySet()) {
            List<Transaction> nameTranList = kvPair.getValue();
            Map<String, List<Transaction>> monthMap = nameTranList.stream().collect(Collectors.groupingBy(Transaction::getMonth));
            Summary summary= new Summary();
            summary.setName(kvPair.getKey());
            int mthCount=1;
            int month1Total=0;
            int month2Total=0;
            int month3Total=0;
            for(Map.Entry<String, List<Transaction>> mkvPair : monthMap.entrySet()) {

                List<Transaction> monthTranList = mkvPair.getValue();
                for (Transaction mthTran:monthTranList) {
                    if(mthCount==1) {
                        month1Total = month1Total + mthTran.getReward();
                    }
                    if(mthCount==2) {
                        month2Total = month2Total + mthTran.getReward();
                    }
                    if(mthCount==3) {
                        month3Total = month3Total + mthTran.getReward();
                    }
                }
                mthCount=mthCount+1;
                summary.setMonth1Total(month1Total);
                summary.setMonth2Total(month2Total);
                summary.setMonth3Total(month3Total);
            }
            summary.setThreeMonthsTotal(month1Total+month2Total+month3Total);
            summaryList.add(summary);
        }

       return summaryList;
    }

}
