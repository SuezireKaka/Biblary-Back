package www.bible.library.bible.model;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VerseDAO {
	private String book;
	private int chapter;
	private int verse;
	private String contents;

	public VerseDAO(String verse, List<BookVO> dbBooksList) throws Exception {
		String address = verse.split(" ")[0];
		this.contents = verse.substring(address.length() + 1);
		
		String[] splitedAddress = address.split(":");
		this.verse = Integer.valueOf(splitedAddress[1]);
		
		List<String> strNamesList = dbBooksList.stream()
				.map(book -> book.getShortName())
				.collect(Collectors.toList());
		List<Integer> intNamesList = dbBooksList.stream()
				.map(book -> book.getPos())
				.collect(Collectors.toList());
		
		String mayBookOneName = address.substring(0, 1);
		String mayBookTwoName = address.substring(0, 2);
		
		if (strNamesList.contains(mayBookTwoName)) {
			this.book = mayBookTwoName;
			this.chapter = Integer.valueOf(splitedAddress[0].substring(2));
		}
		else if (strNamesList.contains(mayBookOneName)) {
			this.book = mayBookOneName;
			this.chapter = Integer.valueOf(splitedAddress[0].substring(1));
		}
		else {
			int mayBookIntName = Integer.valueOf(mayBookTwoName);
			if (intNamesList.contains(mayBookIntName)) {
				BookVO findBook = dbBooksList.get(mayBookIntName - 1);
				this.book = findBook.getShortName();
				this.chapter = Integer.valueOf(splitedAddress[0].substring(2));
			}
			else {
				throw new Exception("Can't find book name");
			}
		}
	}
}
