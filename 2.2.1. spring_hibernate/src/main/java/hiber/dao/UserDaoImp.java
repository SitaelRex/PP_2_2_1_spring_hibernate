package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    public User getUserByCar(String model, int series) {
        String hql = "FROM Car where model = :modelParam AND series = :seriesParam";
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("modelParam", model);
        query.setParameter("seriesParam", series);
        List<Car> resultCars = query.getResultList();
        return resultCars.get(0).getUser();
    }

}
