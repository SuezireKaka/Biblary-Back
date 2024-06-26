package www.bible.library.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.bible.library.framework.exception.BusinessException;
import www.bible.library.security.model.SignInDTO;
import www.bible.library.security.model.SignInResultDto;
import www.bible.library.security.service.SignService;

// 예제 13.28
@RestController
@RequestMapping("/party")
public class SignController {

	private final Logger LOGGER = LoggerFactory.getLogger(SignController.class);
	private final SignService signService;

	public SignController(SignService signService) {
		this.signService = signService;
	}

	@PostMapping(value = "/anonymous/sign-in")
	public SignInResultDto signIn(@RequestBody SignInDTO signInDTO) throws BusinessException {
		LOGGER.info("[signIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", signInDTO.getLoginId());
		SignInResultDto signInResultDto = signService.signIn(signInDTO);

		if (signInResultDto.getCode() == 0) {
			LOGGER.info("[signIn] 정상적으로 로그인되었습니다. id : {}, token : {}", signInDTO.getLoginId(), signInResultDto.getToken());
		}
		return signInResultDto;
	}

	/** CustomAccessDeniedHandler */
	@GetMapping(value = "/anonymous/exception")
	public void exceptionTest() throws RuntimeException {
		throw new RuntimeException("접근이 금지되었습니다.");
	}

}