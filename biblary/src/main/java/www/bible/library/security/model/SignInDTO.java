package www.bible.library.security.model;

import lombok.Data;

@Data
public class SignInDTO {
	private String loginId;
	private String passWord;
}
