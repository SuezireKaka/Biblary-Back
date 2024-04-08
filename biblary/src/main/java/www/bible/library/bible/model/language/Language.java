package www.bible.library.bible.model.language;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Language {
	Eng(false), Kor(false), Greek(true), Hebrew(true), Fail(false);
	
	private boolean isOriginal;
	
	public static Language findWithString(String name) {
		List<String> langList = Arrays.stream(Language.values())
				.map(lang -> lang.name())
				.collect(Collectors.toList());
		if (langList.contains(name)) {
			return Language.valueOf(Language.class, name);
		}
		return Fail;
	}
}
