package www.bible.library.bible.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.bible.library.bible.model.BibleVO;
import www.bible.library.bible.model.ChapterVO;
import www.bible.library.bible.service.BibleService;

@RestController		//Container에 담기도록 지정
@RequestMapping("/bible")
public class BibleController {
	@Autowired
	private BibleService bibleService;
	
	// /bible/anonymous/listAllBibles
	@GetMapping("/anonymous/listAllBibles")
	public ResponseEntity<List<BibleVO>> listAllBibles() {
		List<BibleVO> result = bibleService.listAllBibles();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// /bible/anonymous/getChapter/NIV/마/5
	@GetMapping("/anonymous/getChapter/{bible}/{book}/{chapter}")
	public ResponseEntity<ChapterVO> listAllBibles(
			@PathVariable String bible,
			@PathVariable String book,
			@PathVariable int chapter) {
		ChapterVO result = bibleService.getChapterByAddress(bible, book, chapter);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
