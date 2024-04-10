package www.bible.library.bible.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import www.bible.library.bible.mapper.BibleMapper;
import www.bible.library.bible.model.BibleVO;

@Service
public class BibleService {
	@Value("#{'${bible.file.address}'}")
	private String bibleAddress;
	
	@Autowired
	private BibleMapper bibleMapper;

	public List<BibleVO> listAllBibles() {
		return bibleMapper.listAllBibles();
	}
	
}
