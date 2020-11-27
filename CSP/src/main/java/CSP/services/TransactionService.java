package CSP.services;

import CSP.models.Transaction;
import CSP.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void addTransaction(Transaction transaction){
        transactionRepository.addTransaction(transaction);
    }

    public void removeTransaction(Transaction transaction){
        transactionRepository.removeTransaction(transaction);
    }

    public void updateTransaction(Transaction oldTransaction, Transaction newTransaction){
        transactionRepository.updateTransaction(oldTransaction, newTransaction);
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.getAllTransactions();
    }
}
