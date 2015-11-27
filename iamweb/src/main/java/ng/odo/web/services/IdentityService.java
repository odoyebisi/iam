package ng.odo.web.services;

import java.util.List;

import ng.odo.iamcore.datamodel.Identity;


/**
 * @author Oluwabusayo
 *
 */
public interface IdentityService {
	
	/**
	 * Add identity to data source
	 * @param identity
	 */
	void addIdentity(Identity identity);
	
	/**
	 * Delete identity to data source
	 * @param identity
	 */
	void deleteIdentity(int id);
	
	/**
	 * Update identity to data source
	 * @param identity
	 */
	void updateIdentity(Identity identity);
	
	/**
	 * Read all identities in the data source
	 * @return list of Identity objects
	 */
	List<Identity> getIdentities();
	
	/**
	 * Search an identity in the data source
	 * @param Identity to be searched
	 * @return list of Identity objects
	 */
	List<Identity> searchIdentity(Identity identity);
	
	/**
	 * Find an identity in the data source using its ID
	 * @param id
	 * @return identity matching the id
	 */
	Identity findIdentityByID(int id);
	
}
