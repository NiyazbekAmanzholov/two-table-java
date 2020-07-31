package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
   public User getUserByCarNameAndSeries(String name, int series){
      /*Это вариант с помощью цикла forEach и AtomicReference*/

      /*AtomicReference<User> result = new AtomicReference<>();
      listUsers().forEach(user -> {
         if(user.getCar().getName().equals(name)
                 && user.getCar().getSeries() == series) {
            result.set(user);
         }
      });*/

      /*Это вариант с помощью стримов*/
      return listUsers().stream()
              .filter(user ->
                      user.getCar().getName().equals(name)
              && user.getCar().getSeries() == series).findFirst().get();
   }



}
