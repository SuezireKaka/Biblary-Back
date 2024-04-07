package www.bible.library.framework.model;

import java.util.List;

import www.bible.library.fileattachment.model.AttachFileDTO;

public interface MappedTableDef {
	public String getMappedTableName();
	public String getId();
	public String getKSuspectType();
	public List<AttachFileDTO> getListAttachFile();
}
