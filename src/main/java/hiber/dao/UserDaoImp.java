package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getUserByCar(Car car) {
      int series = car.getSeries();
      String model = car.getModel();
      String hql = "SELECT e.id "
              +      "FROM User e "
              +      "LEFT OUTER JOIN Car a ON e.car = a.user "
              +       "where (a.series = "  + series + ") "
              +       "AND (a.model = '"  + model + "') ";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
      List results = query.getResultList();
      return null;
   }

}
