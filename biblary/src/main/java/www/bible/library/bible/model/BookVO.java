package www.bible.library.bible.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookVO {
	private int pos;
	private String shortName;
	private String fullName;
	private int chapterNumber;
	private boolean isNewTestament;
}
