package www.bible.library.bible.service;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import www.bible.library.bible.mapper.BibleMapper;
import www.bible.library.bible.model.BibleVO;
import www.bible.library.bible.model.BookVO;
import www.bible.library.bible.model.ChapterDTO;
import www.bible.library.bible.model.ChapterVO;
import www.bible.library.bible.model.VerseDAO;

@Service
public class BibleService {
	@Value("#{'${bible.file.address}'}")
	private String bibleAddress;
	
	private static final String NAME_SEPERATOR = "_";
	private static final String VALID_EXTENSION = ".txt";
	
	@Autowired
	private BibleMapper bibleMapper;
	
	private List<BookVO> dbBooksList;

	public List<BibleVO> listAllBibles() {
		return bibleMapper.listAllBibles();
	}
	
	public List<BookVO> listAllBooksOf(String bible) {
		return bibleMapper.listAllBooksOf(bible);
	}
	
	public ChapterVO getChapterByAddress(String bible, String book, int chapter) {
		ChapterDTO dto = ChapterDTO.builder()
				.bible(bible)
				.book(book)
				.chapter(chapter)
				.build();
		return bibleMapper.getChapterByAddress(dto);
	}
	
	
	
	public void syncBiblesFromFiles() {
		File directory = new File(bibleAddress);
		
		FilenameFilter validBibleFilter = new FilenameFilter() {
			public boolean accept(File f, String fileName) {
				String[] splitedName = fileName.split(NAME_SEPERATOR);
				// A_B_C.txt꼴일 것
				return fileName.endsWith(VALID_EXTENSION)
					&& splitedName.length == 3;
			}
		};
		
		List<File> bibleFilesList = Arrays.stream(directory.listFiles(validBibleFilter))
				.collect(Collectors.toList());
		
		List<BibleVO> newBiblesList = Arrays.stream(directory.list(validBibleFilter))
				.map(this::saltName)
				.collect(Collectors.toList());
		List<BibleVO> oldBiblesList = listAllBibles();
		
		boolean success = true;
		
		if (! (oldBiblesList.containsAll(newBiblesList))) {
			List<BibleVO> insertList = newBiblesList.stream()
					.filter(bible -> ! oldBiblesList.contains(bible))
					.collect(Collectors.toList());
			
			List<File> insertFilesList = bibleFilesList.stream()
					.filter(file -> insertList.contains(saltName(file.getName())))
					.collect(Collectors.toList());
			
			dbBooksList = bibleMapper.listAllBooks();
			
			for (int i = 0; i < insertFilesList.size(); i++) {
				File bibleFile = insertFilesList.get(i);
				BibleVO bibleVo = newBiblesList.get(i);
				
				success &= readBible(bibleFile, bibleVo);
			}
			
			success &= bibleMapper.insertBiblesToSync(insertList);
		}
		if (! (newBiblesList.containsAll(oldBiblesList))) {
			List<BibleVO> deleteList = oldBiblesList.stream()
					.filter(bible -> ! newBiblesList.contains(bible))
					.collect(Collectors.toList());
			
			success &= bibleMapper.deleteBiblesToSync(deleteList);
		}
		
		System.out.println("처리 " + (success ? "성공" : "실패"));
	}
	
	private boolean readBible(File bibleFile, BibleVO bibleVo) {
		try {
			List<String> contents = Files.readAllLines(bibleFile.toPath());
			
			List<VerseDAO> insertVersesList = contents.stream()
					.filter(verse -> verse.length() > 7)
					.map(verse -> {
						try {
							VerseDAO dao = new VerseDAO(verse, dbBooksList);
							return dao;
						}
						catch (Exception e) {
							System.out.println(verse);
							e.printStackTrace();
						}
						return null;
					})
					.collect(Collectors.toList());
			
			return bibleMapper.insertVerses(bibleVo, insertVersesList);
		} catch (Exception e) {
			System.out.println(bibleFile.getName());
			e.printStackTrace();
		}
		return false;
	}

	private BibleVO saltName(String fileName) {
		String[] splitedName = fileName.split(NAME_SEPERATOR);
		String bibleName = splitedName[splitedName.length - 2];
		
		String parseString = splitedName[splitedName.length - 1];
		
		int parsed = Integer.valueOf(
				parseString.substring(0, parseString.length() - VALID_EXTENSION.length()));
		
		
		BibleVO result = BibleVO.builder()
				.name(bibleName)
				.language(bibleMapper.getLanguage(splitedName[0]))
				.parsed(parsed % 2 != 0)
				.build();
		
		return result;
	}
}














