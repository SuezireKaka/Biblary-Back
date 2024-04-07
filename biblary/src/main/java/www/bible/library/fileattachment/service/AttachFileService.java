package www.bible.library.fileattachment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.bible.library.fileattachment.model.AttachFileDTO;
import www.bible.library.fileattachment.repository.AttachFileRepository;
import www.bible.library.framework.model.MappedTableDef;

@Service
public class AttachFileService {
    
	@Autowired
	private AttachFileRepository attachFileRepository;

	public List<AttachFileDTO> getAttachFileList(MappedTableDef owner) {
		List<AttachFileDTO> ret = attachFileRepository.findByOwnerTypeAndOwnerId(owner.getMappedTableName(), owner.getId());
		return ret;
	}
	
	public void createAttachFiles(MappedTableDef owner) {
		List<AttachFileDTO> list = owner.getListAttachFile();
		if (list == null)
			return;
		list.forEach(e->{
			e.setOwnerType(owner.getMappedTableName());
			e.setOwnerId(owner.getId());
		});
		
		attachFileRepository.saveAll(list);
	}

	public void deleteAttachFiles(MappedTableDef owner) {
		attachFileRepository.deleteAllByOwnerTypeAndOwnerId(owner.getMappedTableName(), owner.getId());
	}

}
