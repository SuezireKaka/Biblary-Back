package www.bible.library.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.bible.library.framework.mapper.CodeMapper;
import www.bible.library.framework.model.CodeVO;
import www.bible.library.framework.model.RemoconVO;

@Service
public class CodeService {
	@Autowired
	private CodeMapper codeMapper;

	public List<CodeVO> listAll() {
		return codeMapper.listAll();
	}

	public RemoconVO getRemoconByName(String name) {
		return codeMapper.getRemoconByName(name);
	}
}
