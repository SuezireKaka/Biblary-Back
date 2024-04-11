package www.bible.library.bible.service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import www.bible.library.bible.mapper.BibleMapper;
import www.bible.library.bible.model.BibleVO;
import www.bible.library.bible.model.language.Language;

@Service
public class BibleService {
	@Value("#{'${bible.file.address}'}")
	private String bibleAddress;
	
	private static final String NAME_SEPERATOR = "_";
	private static final String VALID_EXTENSION = ".txt";
	
	@Autowired
	private BibleMapper bibleMapper;

	public List<BibleVO> listAllBibles() {
		return bibleMapper.listAllBibles();
	}
	
	public void syncBiblesFromFiles() {
		File directory = new File(bibleAddress);
		
		FilenameFilter validBibleFilter = new FilenameFilter() {
			public boolean accept(File f, String fileName) {
				String[] splitedName = fileName.split(NAME_SEPERATOR);
				// A_B.txt꼴일 것
				return fileName.endsWith(VALID_EXTENSION)
					&& splitedName.length == 2;
			}
		};
		
		List<BibleVO> fileBiblesList = Arrays.stream(directory.list(validBibleFilter))
				.map(this::saltName)
				.collect(Collectors.toList());
		List<BibleVO> oldBiblesList = listAllBibles();
		
		boolean success = true;
		
		if (! (oldBiblesList.containsAll(fileBiblesList))) {
			List<BibleVO> insertList = fileBiblesList.stream()
					.filter(bible -> ! oldBiblesList.contains(bible))
					.collect(Collectors.toList());
			
			success &= bibleMapper.insertBiblesToSync(insertList);
		}
		if (! (fileBiblesList.containsAll(oldBiblesList))) {
			List<BibleVO> deleteList = oldBiblesList.stream()
					.filter(bible -> ! fileBiblesList.contains(bible))
					.collect(Collectors.toList());
			
			success &= bibleMapper.deleteBiblesToSync(deleteList);
		}
		
		System.out.println("처리 " + (success ? "성공" : "실패"));
	}
	
	private BibleVO saltName(String fileName) {
		String[] splitedName = fileName.split(NAME_SEPERATOR);
		String fullName = splitedName[splitedName.length - 1];
		String bibleName = fullName
				.substring(0, fullName.length() - VALID_EXTENSION.length());
		BibleVO result = BibleVO.builder()
				.name(bibleName)
				.language(Language.findWithString(splitedName[0]))
				.build();
		
		return result;
	}
}














