package www.bible.library.bible.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ChapterDTO {
	private String bible;
	private String book;
	private int chapter;
}
