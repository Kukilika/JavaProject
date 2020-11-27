package CSP.repositories;

import CSP.models.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {
    private List<Transaction> transactionList = new ArrayList<>();

    public void addTransaction(Transaction transaction){
        transactionList.add(transaction);
    }

    public void removeTransaction(Transaction transaction){
        transactionList.remove(transaction);
    }

    public List<Transaction> getAllTransactions(){
        return transactionList;
    }

    public void updateTransaction(Transaction oldTransaction, Transaction newTransaction){
        transactionList.set(transactionList.indexOf(oldTransaction) , newTransaction);
    }
}
