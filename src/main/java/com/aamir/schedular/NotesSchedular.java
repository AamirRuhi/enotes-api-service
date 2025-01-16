package com.aamir.schedular;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aamir.entity.Notes;
import com.aamir.repository.NotesRepository;

@Component
public class NotesSchedular {
// schedulling is process of setting up tasks to run at specific interval ,notes me private LocalDateTime deletedOn;
	//abhi hum custom kr rhe h delele kr rhe h to recyle bin me aa rha hai ,ager fir use restore kr rhe h to recycle bin se restore hoja rha hai
	//recycle bin se scheduling ke through delete krenge
	
	@Autowired
	private NotesRepository notesRepository;
	
	@Scheduled(cron="0 0 0 * * ?") //0 0 0 * * ?  means->12 am se start hoga schedular
	//@Scheduled(cron="* * * ? * *") // every second
public void deleteNotesSchedular()
{
//20 -nov--14-nov tak 7 din hote hai inke bich dino me jo delete hua wo 7 day baad authomatic delete ho jayega  e.g., finding records older than 7 days
		
		System.out.println("i");
		LocalDateTime cutOffDate = LocalDateTime.now().minusDays(7);
		List<Notes> deleteNotes=notesRepository.findAllByIsDeletedAndDeletedOnBefore(true,cutOffDate);
		notesRepository.deleteAll(deleteNotes);
		
}
}
