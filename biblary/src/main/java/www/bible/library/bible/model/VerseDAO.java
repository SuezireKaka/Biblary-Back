package www.bible.library.bible.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
public class VerseDAO {	
	@Setter
	BibleVO bible;
	String book;
	int chapter;
	int verse;
	String contents;

	public VerseDAO(String verse, List<String> bookNamesList) throws Exception {
		String address = verse.split(" ")[0];
		this.contents = verse.substring(address.length() + 1);
		
		String[] splitedAddress = address.split(":");
		this.verse = Integer.valueOf(splitedAddress[1]);
		
		String mayBookOneName = address.substring(0, 1);
		String mayBookTwoName = address.substring(0, 2);
		
		if (bookNamesList.contains(mayBookTwoName)) {
			this.book = mayBookTwoName;
			this.chapter = Integer.valueOf(splitedAddress[0].substring(2));
		}
		else if (bookNamesList.contains(mayBookOneName)) {
			this.book = mayBookOneName;
			this.chapter = Integer.valueOf(splitedAddress[0].substring(1));
		}
		else {
			throw new Exception("Can't find book name");
		}
	}
}
