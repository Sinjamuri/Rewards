package com.example.demo.service;

import com.example.demo.model.Summary;
import com.example.demo.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class ApiService {

    public List<Summary> getSummary(Transaction[] transactions){
        List<Summary> summaryList = new ArrayList<>();
        try {
            // read the input json file which has transactions for each customer in three month period
            //Transaction[] transactions = readInputFile();
            // Calculate reward amount for each transaction
            transactions =processRewards(transactions);
            //Calculate Rewards by month  and for three month totals
            List<Transaction> transactionList = new ArrayList<Transaction>(Arrays.asList(transactions));
            Map<String, List<Transaction>> nameMap = transactionList.stream().collect(Collectors.groupingBy(Transaction::getName));
            summaryList = calculateTotals(nameMap);
            //Create file with Summary result
           // writeOutputFile(summaryList);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return summaryList;
    }

    public  Transaction[] processRewards(Transaction[] transactions){
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

    public  List<Summary> calculateTotals(Map<String, List<Transaction>> nameMap){
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
