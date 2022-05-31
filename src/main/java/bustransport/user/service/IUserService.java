package bustransport.user.service;

import java.util.List;

import bustransport.user.model.User;

public interface IUserService  {


	public User addUser (User u);
	public User loginUser (String userID,String password);
	public User getUser (String userID);
	public User updateUser (User u);
	public User deleteUser (String userId);
	public List<User> getUsers();

}
