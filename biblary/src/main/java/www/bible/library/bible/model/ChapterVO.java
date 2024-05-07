package www.bible.library.bible.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChapterVO {
	private BibleVO bible;
	private BookVO book;
	private int chapter;
	
	private List<VerseVO> versesList = new ArrayList<>();
}
