package CSP.services;

import CSP.models.Car;
import CSP.models.Transaction;
import CSP.models.User;
import CSP.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;


    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void addTransaction(Car car){
        Transaction transaction = new Transaction();
        transaction.createReceipt(userService.loggedUser().toString(), car.getOwner().toString(), car.toString());
        carService.removeCar(car);
        transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }
}
