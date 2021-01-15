package CSP.controllers;

import CSP.models.Role;
import CSP.models.Transaction;
import CSP.services.CarService;
import CSP.services.TransactionService;
import CSP.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @RequestMapping("")
    @ResponseBody
    public List<Transaction> getTransactions(){
        return transactionService.getAllTransactions();
    }

    @PostMapping("/registration/{carId}")
    public ResponseEntity<Transaction> addTransaction(@PathVariable Long carId) {

        if(userService.loggedUser().isAdmin() && carService.doesCarExist(carId)){
            transactionService.addTransaction(carService.getCar(carId));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
