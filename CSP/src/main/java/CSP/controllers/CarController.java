package CSP.controllers;

import CSP.models.Car;
import CSP.repositories.CarRepository;
import CSP.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping("")
    @ResponseBody
    public List<Car> getCars(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + currentUser + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return carService.getAllCars();
    }

    @PostMapping("")
    public void addCar(@Validated @RequestBody Car car){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();

//        car.setOwner();
        carService.addCar(car);
    }
}
