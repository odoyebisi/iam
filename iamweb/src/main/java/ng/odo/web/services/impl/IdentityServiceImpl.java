package ng.odo.web.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.odo.iamcore.datamodel.Identity;
import ng.odo.iamcore.services.dao.impl.IdentityHibernateDAO;
import ng.odo.web.services.IdentityService;

/**
 * @author Oluwabusayo
 *
 */
@Service("identityService")
public class IdentityServiceImpl implements IdentityService {
	
	IdentityHibernateDAO identityDAO;

	@Autowired
	public void setUserDao(IdentityHibernateDAO identityDAO) {
		this.identityDAO = identityDAO;
	}
	
	
	/* (non-Javadoc)
	 * @see ng.odo.web.services.IdentityService#addIdentity(ng.odo.iamcore.datamodel.Identity)
	 */
	@Override
	public void addIdentity(Identity identity) {
		identityDAO.write(identity);
	}
	
	/* (non-Javadoc)
	 * @see ng.odo.web.services.IdentityService#getIdentities()
	 */
	@Override
	public List<Identity> getIdentities() {
		return identityDAO.readAll();
	}
	
	/* (non-Javadoc)
	 * @see ng.odo.web.services.IdentityService#searchIdentities(ng.odo.iamcore.datamodel.Identity)
	 */
	@Override
	public List<Identity> searchIdentity(Identity identity) {
		
		//set empty values to null
		if (identity.getFirstName().trim().length() == 0){
			identity.setFirstName(null);			
		} else {
			identity.setFirstName(identity.getFirstName().toLowerCase().trim());
		}
		if (identity.getLastName().trim().length() == 0){
			identity.setLastName(null);			
		} else {
			identity.setLastName(identity.getLastName().toLowerCase().trim());
		}
		
		
		return identityDAO.search(identity);
	}
	
	/* (non-Javadoc)
	 * @see ng.odo.web.services.IdentityService#findIdentityByID(int)
	 */
	@Override
	public Identity findIdentityByID(int id) {

		List<Identity> identities = getIdentities();
		if (!identities.isEmpty()) {
			for (Identity i : identities) {
				if (id == i.getId()) {
					return i;
				}
			}
		}
		return new Identity();
	}

	/* (non-Javadoc)
	 * @see ng.odo.web.services.IdentityService#deleteIdentity(int)
	 */
	@Override
	public void deleteIdentity(int id) {
		Identity identity = new Identity();
		identity.setId(id);
		identityDAO.delete(identity);
	}
	
	
	/* (non-Javadoc)
	 * @see ng.odo.web.services.IdentityService#updateIdentity(int)
	 */
	public void updateIdentity(Identity identity) {
		identityDAO.update(identity);
	}
}
