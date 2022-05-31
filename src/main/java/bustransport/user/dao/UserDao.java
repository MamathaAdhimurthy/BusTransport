package bustransport.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bustransport.user.model.User;

public interface UserDao extends JpaRepository<User,String> {

	User findByUserIdAndPassword(String string, String string2);

}
