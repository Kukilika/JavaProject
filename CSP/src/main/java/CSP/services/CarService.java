package CSP.services;

import CSP.models.Car;
import CSP.models.Comment;
import CSP.models.User;
import CSP.repositories.CarRepository;
import CSP.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentService commentService;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public void addCar(Car car){

        Car newCar = new Car();
        newCar.setAccident(car.getAccident());
        newCar.setDescription(car.getDescription());
        newCar.setMake(car.getMake());
        newCar.setModel(car.getModel());
        newCar.setMileage(car.getMileage());
        newCar.setPrice(car.getPrice());
        newCar.setOwner(userService.loggedUser());
        carRepository.save(newCar);
    }

    public Boolean doesCarExist(Long id){
        return !carRepository.findAllById(id).isEmpty();
    }

    public Car getCar(Long id){
        Car car = new Car(carRepository.findById(id));
        car.setId(id);
        return car;
    }

    public void removeCar(Car car){
        commentService.deleteAllCommentsRelatedToACar(car.getId());
        carRepository.delete(car);
    }

    public void deleteAllCarsRelatedToAnUser(Long userId){
        List<Car> carList= carRepository.findAll();
        for(Car c:carList){
            if(c.getOwner().getId() == userId){
                removeCar(c);
            }
        }
    }

    public void updateCar(Long id, Car newCar){
        Car car = getCar(id);
        car.setId(id);
        car.setModel(newCar.getModel());
        car.setPrice(newCar.getPrice());
        car.setMake(newCar.getMake());
        car.setAccident(newCar.getAccident());
        car.setMileage(newCar.getMileage());
        car.setDescription(newCar.getDescription());
        carRepository.save(car);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }
}
