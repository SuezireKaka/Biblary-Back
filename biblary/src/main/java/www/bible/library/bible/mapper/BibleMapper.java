package www.bible.library.bible.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import www.bible.library.bible.model.BibleVO;

@Mapper
public interface BibleMapper {
	public List<BibleVO> listAllBibles();
}
