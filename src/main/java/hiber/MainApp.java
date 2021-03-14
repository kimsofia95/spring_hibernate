package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      Car car = new Car("lada", 56);
      Car car1 = new Car("mersedes", 567);
      Car car4 = new Car("bmv", 5657);
      User user5 = new User("User2", "Lastname244", "user2@mail.ru");
      User user1 = new User("User3", "Lastname22", "user2@mail.ru");
      User user2 = new User("User4", "Lastname2ad", "user2@mail.ru");
      user5.setCar(car);
      user2.setCar(car1);
      userService.add(user5);
      userService.add(user1);
      userService.add(user2);
      List<User> list = userService.getUserByCar(car1.getSeries(), car1.getModel());


      List<User> users = userService.listUsers();
      for (User user : list) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
