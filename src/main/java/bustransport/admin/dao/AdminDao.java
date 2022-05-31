package bustransport.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bustransport.admin.model.Admin;

public interface AdminDao extends JpaRepository<Admin, String> {

	Admin findByAdminIdAndPassword(String string, String string2);

}
