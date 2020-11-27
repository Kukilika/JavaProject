package CSP.services;

import CSP.models.Car;
import CSP.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public void addCar(Car car){
        carRepository.addCar(car);
    }

    public void removeCar(Car car){
        carRepository.removeCar(car);
    }

    public void updateCar(Car oldCar, Car newCar){
        carRepository.updateCar(oldCar, newCar);
    }

    public List<Car> getAllCars(){
        return carRepository.getAllCars();
    }
}
