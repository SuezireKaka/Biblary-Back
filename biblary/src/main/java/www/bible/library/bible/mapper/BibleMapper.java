package www.bible.library.bible.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import www.bible.library.bible.model.BibleVO;
import www.bible.library.bible.model.BookVO;
import www.bible.library.bible.model.ChapterDTO;
import www.bible.library.bible.model.ChapterVO;
import www.bible.library.bible.model.VerseDAO;
import www.bible.library.bible.model.language.LanguageVO;

@Mapper
public interface BibleMapper {
	public List<BibleVO> listAllBibles();
	public List<BookVO> listAllBooks();
	
	public List<BookVO> listAllBooksOf(String bible);
	
	
	public LanguageVO getLanguage(String language);
	
	public ChapterVO getChapterByAddress(@Param("chapter") ChapterDTO chapter);
	
	
	public boolean insertBiblesToSync(List<BibleVO> insertList);
	
	public boolean insertVerses(@Param("bible") BibleVO bible,
			@Param("insertList") List<VerseDAO> insertList);
	
	
	public boolean deleteBiblesToSync(List<BibleVO> deleteList);
	
	
}
