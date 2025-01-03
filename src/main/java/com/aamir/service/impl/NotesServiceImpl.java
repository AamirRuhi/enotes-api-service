package com.aamir.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aamir.dto.NotesDto;
import com.aamir.dto.NotesDto.CategoryDto;
import com.aamir.entity.FileDetails;
import com.aamir.entity.Notes;
import com.aamir.exception.ResourceNotFoundException;
import com.aamir.repository.CategoryRepository;
import com.aamir.repository.FileRepository;
import com.aamir.repository.NotesRepository;
import com.aamir.service.NotesService;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class NotesServiceImpl implements NotesService{
	
	@Autowired
private NotesRepository notesRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Value("${file.upload.path}")
	private String uploadpath;
	
	@Autowired
	private FileRepository fileRepository;
	
	
	@Override
	
	public boolean saveNotes(String notes,MultipartFile file) throws Exception {
		//notes string me mila h ,pr save krna h notes object me by objectmapper ke help se
		//add jdk in class part instead of jre
		ObjectMapper ob=new ObjectMapper();
		NotesDto notesDto=ob.readValue(notes, NotesDto.class);
		//notes string me mila h ,pr save krna h notes object me by objectmapper ke help se
		
		  checkCategoryExist(notesDto.getCategory());
		  
		  // ye upper wala last me 
		  Notes notesMap = modelMapper.map(notesDto, Notes.class);
			//ab file upload ke liye ek method bna  ke liye 
			FileDetails fileDetails=saveFileDetails(file);
		//ager user file nhi deta hai ye hum last me kr rhe hai dene wala krke , fir inner class filedto bhi bnalenge ,kya dikhana hai response me
			if(!ObjectUtils.isEmpty(fileDetails)) {
				notesMap.setFileDetails(fileDetails);
			}else {
				//user file nhi diya to null mil jayega 
				notesMap.setFileDetails(null);
				//ab file ke sath json send krenge
			}
			
		  
		  Notes savenotes = notesRepository.save(notesMap);
		  if(!ObjectUtils.isEmpty(savenotes)) 
		  { 
			  return true; 
			  }
		 
		return false;
	}

	private FileDetails saveFileDetails(MultipartFile file) throws IOException {
		//agr user file diya hai
		//ager nhi diya hai to !ObjectUtils.isEmpty(file)&&
		if(!ObjectUtils.isEmpty(file) && !file.isEmpty()) {
			//start file ke extention pr bhi condition lga stke hai

			String originalFilename = file.getOriginalFilename();
			String extension = FilenameUtils.getExtension(originalFilename);
			//file ke extention pr bhi condition lga stke hai
			List<String> extentionAllow = Arrays.asList("pdf","xlsx","jpg");
			if(!extentionAllow.contains(extension)) {
				throw new IllegalArgumentException("invalid file formate ,u can upload only pdf,xlsx,jpg ");
			}
			//end file ke extention pr bhi condition lga stke hai
			
			//String originalFilename = file.getOriginalFilename(); extention  (bhi) pr condition dene ke liye comment kiya or upper likha
			//fileDetails.setOriginalFileName(originalFilename);
			
			
			//getDisplatFileName complete ke bbad ,random no generate krenge file ko uniiiiiique dikhane ke liye
		 String rndString = UUID.randomUUID().toString();
		// String extension = FilenameUtils.getExtension(originalFilename);
		 //ab new filename aya 7 character ka + extention, uploadfilename,displayfilename,originalfilename 3no ho chuka parth or filesize bacha hai
		 String uploadfilename = rndString+"."+extension;
		// fileDetails.setUploadFileName(uploadfilename);
		 //filesize set kr liya
		// fileDetails.setFileSize(file.getSize());
		 //path me ab kis path me store hoga file, application.property me parh declare kr diya
		 File saveeFile=new File(uploadpath);
		 //file ka object bnaunga uske parameter me uploadpath dunga
		 
		 if(!saveeFile.exists()) {
			//check file create hua ki nhi notes folder ager exist nhi h to notes naam ka foldet create krna hai
			 saveeFile.mkdir();
		 }
		 //path:enotesapiservice/notes/java.pdf
		 String storepath = uploadpath.concat(uploadfilename);
		 //fileDetails.setPath(storepath);
		 
		 //upload file locally
		 long uploaded = Files.copy(file.getInputStream(), Paths.get(storepath));
		 if(uploaded!=0) {
			//fileDetails ka object chahiye or useme detail set krenge 
				FileDetails fileDetails=new FileDetails();
				fileDetails.setOriginalFileName(originalFilename);
				//getDisplatFileName naam ka ek method bna lete hai
				fileDetails.setDisplayFileName(getDisplatFileName(originalFilename));
				 fileDetails.setUploadFileName(uploadfilename);
				 fileDetails.setPath(storepath);
				 //filesize set kr liya
				 fileDetails.setFileSize(file.getSize());
			 // db me store krna h to repository chahiye bnalo FileRepository 
			 FileDetails saveFileDtls = fileRepository.save(fileDetails);
			 return saveFileDtls;
		 }
		}
		return null;
	}

	private String getDisplatFileName(String originalFilename) {
		//stotre file ka pura naam krenge but show krenge 7-8 charector.extention like .jpg,png,
		//pehle extention remove krenge apache common io ke help se ye ek library hai
		String extension = FilenameUtils.getExtension(originalFilename);
		//extention get kiya
		String filename = FilenameUtils.removeExtension(originalFilename);
		//file name mil gya pura bina extention ke ab condition lgaunga length me fir filename me assign kr diya
		if(filename.length()>8) {
			filename=filename.substring(0,7);
		}
		//ab filename ke sath extention bhi lgana hai
		filename=filename+"."+extension;
		
		return filename;
	}

	private void checkCategoryExist(CategoryDto category) throws Exception {
		// custom bnaye the resourcenotfoundexception ussi ko call krna hai
		categoryRepository.findById(category.getId()).orElseThrow(()->new ResourceNotFoundException("category id is invalid"));
		
	}

	@Override
	public List<NotesDto> getAllNotes() {
		//advance way
		return notesRepository.findAll().stream().map(note->modelMapper.map(note, NotesDto.class)).toList();	
	}

}
