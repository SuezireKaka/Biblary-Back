package www.bible.library.bible.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookVO {
	private int pos;
	private String shortName;
	private String fullName;
	private String chapterSuffix;
	private int chapterNumber;
	private boolean isNewTestament;
}
