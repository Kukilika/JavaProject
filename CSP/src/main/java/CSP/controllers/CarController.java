package CSP.controllers;

import CSP.models.Car;
import CSP.models.User;
import CSP.services.CarService;
import CSP.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;


    @GetMapping("")
    @ResponseBody
    public List<Car> getCars(){
        return carService.getAllCars();
    }

    @GetMapping("/{make}/{model}")
    @ResponseBody
    public List<Car> getCars(@PathVariable String make, @PathVariable String model){
        List<Car> carList = carService.getAllCars();
        List<Car> carListAfterQuery = new ArrayList<>();
        for(Car c:carList){
            if(c.getMake().equals(make) && c.getModel().equals(model)){
                carListAfterQuery.add(c);
            }
        }
        return carListAfterQuery;
    }

    @PostMapping("/registration")
    public ResponseEntity<Car> addCar(@Validated @RequestBody Car car){
        carService.addCar(car);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car){
        if(carService.doesCarExist(id) && userService.hasUserPermissionsToEditCar(id)){
            carService.updateCar(id, car);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id){

        if(carService.doesCarExist(id) && userService.hasUserPermissionsToEditCar(id)){
            carService.removeCar(carService.getCar(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
