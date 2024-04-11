package www.bible.library.framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import www.bible.library.bible.service.BibleService;

@Service
public class SchedulerService {
	@Autowired
	private BibleService bibleService;
	
	@Scheduled(cron = "0 0 0 * * *")
	public void syncBibleSchedule() {
		bibleService.syncBiblesFromFiles();
	}
}
