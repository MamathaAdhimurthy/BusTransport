package bustransport.admin.service;

import bustransport.admin.model.Admin;

public interface IAdminService {
	public Admin addAdmin(Admin a);
	public Admin loginAdmin(String name,String password);
	public Admin getAdmin(String name);
	public Admin updateAdmin(Admin a);
	public Admin deleteAdmin(String adminId);
}