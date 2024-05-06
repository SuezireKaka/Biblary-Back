package www.bible.library.bible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import www.bible.library.bible.model.language.LanguageVO;

@Getter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class BibleVO {
	private String name;
	private LanguageVO language;
	private boolean parsed;
}
