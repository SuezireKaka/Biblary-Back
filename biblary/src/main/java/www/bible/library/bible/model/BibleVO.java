package www.bible.library.bible.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import www.bible.library.bible.model.language.Language;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class BibleVO {
	private String name;
	private Language language;
}
