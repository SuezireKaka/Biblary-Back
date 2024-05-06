package www.bible.library.bible.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookVO {
	public static final int OLD_TESTAMENT = 0;
	public static final int NEW_TESTAMENT = 1;
	
	private int pos;
	private String shortName;
	private String fullName;
	private String chapterSuffix;
	private int chapterNumber;
	private boolean isNewTestament;
}
