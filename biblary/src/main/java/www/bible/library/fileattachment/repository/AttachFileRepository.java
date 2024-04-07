package www.bible.library.fileattachment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import www.bible.library.fileattachment.model.AttachFileDTO;

public interface AttachFileRepository extends JpaRepository<AttachFileDTO, String>{
	/** list up
	select *
	from t_attach
	where Owner_Type = #{ownerType}
	And Owner_Id = #{ownerId}
	 * */
	List<AttachFileDTO> findByOwnerTypeAndOwnerId(String ownerType, String ownerId);

	List<AttachFileDTO> findByPathNameIn(List<String> listDay);

	/** create 
	@Query(nativeQuery = true,
			value="insert into t_attach(owner_type, owner_id, uuid, path, name, type_ordinal) "
					+ "values(:obj.ownerType, :obj.ownerId, :obj.uuid, :obj.uploadPath, :obj.originalFilePureName, :obj.typeOrdinal)")
	int saveAttachFileDTO(@Param("obj") AttachFileDTO obj);
*/
	
	/** delete */
	/** delete all */
	@Transactional
	int deleteAllByOwnerTypeAndOwnerId(String ownerType, String ownerId);
}
