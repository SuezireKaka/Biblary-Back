package www.bible.library.bible.model.language;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = Shape.OBJECT)
@Getter
@AllArgsConstructor
public enum Language {
	Eng("영어", false), Kor("한국어", false),
	Greek("헬라어", true), Hebrew("히브리어", true),
	Fail("", false);
	
	private String korean;
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
