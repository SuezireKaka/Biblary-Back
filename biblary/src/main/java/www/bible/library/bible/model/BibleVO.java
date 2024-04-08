package www.bible.library.bible.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import www.bible.library.bible.model.language.Language;

@Getter
@AllArgsConstructor
public class BibleVO {
	private String name;
	private Language language;
}
