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
   public List<User> getUserByCar(int series, String model) {
      String hql = "SELECT e.user "
              +      "FROM Car e "
              +      "LEFT OUTER JOIN User a ON a.car = e.user "
              +       "where (e.series = "  + series + ") "
              +       "AND (e.model = '"  + model + "') ";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
      List <User> users = query.getResultList();
      return users;
   }

}
