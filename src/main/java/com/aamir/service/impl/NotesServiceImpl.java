package com.aamir.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aamir.dto.NotesDto;
import com.aamir.dto.NotesDto.CategoryDto;
import com.aamir.dto.NotesDto.FilesDto;
import com.aamir.dto.NotesResponse;
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
		ObjectMapper ob=new ObjectMapper();
		NotesDto notesDto=ob.readValue(notes, NotesDto.class);
		
		notesDto.setDeleted(false);
		notesDto.setDeletedOn(null);
		
		//start update ke liye agr id milega to update ho jayega wrna save hoga
		if(!ObjectUtils.isEmpty(notesDto.getId())) {
			updateNotes(notesDto,file);
		}
		
		//end update ke liye agr id milega to update ho jayega
		
		
		  checkCategoryExist(notesDto.getCategory());
		  
		  Notes notesMap = modelMapper.map(notesDto, Notes.class);
			FileDetails fileDetails=saveFileDetails(file);
			if(!ObjectUtils.isEmpty(fileDetails)) {
				notesMap.setFileDetails(fileDetails);
			}else {
				//start update ke liye agr id milega to update ho jayega, agr user file kiya hai to
				if(ObjectUtils.isEmpty(notesDto.getId())) {
					notesMap.setFileDetails(null);
				}
				//end update ke liye agr id milega to update ho jayega
			}
		  Notes savenotes = notesRepository.save(notesMap);
		  if(!ObjectUtils.isEmpty(savenotes)) 
		  { 
			  return true; 
			  }
		 
		return false;
	}

	private void updateNotes(NotesDto notesDto, MultipartFile file) throws Exception {
		//user jo id de rha hai wo id db me h ya nhi check krke update krenge
		Notes existnotes = notesRepository.findById(notesDto.getId()).orElseThrow(()->new ResourceNotFoundException("invalid notes id"));
		//file dena optional hai ager diya to update kr denge otherwise previos wala rhne denge
		if(ObjectUtils.isEmpty(file)) {
			//user jab file nhi deya
			notesDto.setFileDetails(modelMapper.map(existnotes.getFileDetails(), FilesDto.class));
		}
	}

	private FileDetails saveFileDetails(MultipartFile file) throws IOException {
		if(!ObjectUtils.isEmpty(file) && !file.isEmpty()) {
		
			String originalFilename = file.getOriginalFilename();
			String extension = FilenameUtils.getExtension(originalFilename);
			List<String> extentionAllow = Arrays.asList("pdf","xlsx","jpg","dock","jpeg");
			if(!extentionAllow.contains(extension)) {
				throw new IllegalArgumentException("invalid file formate ,u can upload only pdf,xlsx,jpg ");
			}
		 String rndString = UUID.randomUUID().toString();
		 String uploadfilename = rndString+"."+extension;
		 File saveeFile=new File(uploadpath);
		 
		 if(!saveeFile.exists()) {
			 saveeFile.mkdir();
		 }
		 String storepath = uploadpath.concat(uploadfilename);
		 
		 long uploaded = Files.copy(file.getInputStream(), Paths.get(storepath));
		 if(uploaded!=0) {
				FileDetails fileDetails=new FileDetails();
				fileDetails.setOriginalFileName(originalFilename);
				fileDetails.setDisplayFileName(getDisplatFileName(originalFilename));
				 fileDetails.setUploadFileName(uploadfilename);
				 fileDetails.setPath(storepath);
				 fileDetails.setFileSize(file.getSize());
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

	@Override
	public byte[] downloadFile(FileDetails fileDetails) throws Exception {
		
		InputStream io= new FileInputStream(fileDetails.getPath());
		//inputstream ka object ko bye array me convert kr liya , filenotfoundexception aa skta hai ,
		//filenotfoundexception handle kernge globalexception handler me
	
		byte[] byteArray = StreamUtils.copyToByteArray(io);
		//file ke naam ke sath get krna hai to getFileDetails naam ka ek or method bnayege
		return byteArray;
	}

	@Override
	public FileDetails getFileDetails(Integer id) throws Exception {
		FileDetails fileDetails = fileRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("file is not available"));
		return fileDetails;
	}

	
	@Override
	public NotesResponse getAllNotesByUser(Integer userId , Integer pageNo, Integer pageSize) {
		// 0 index se page no statr hota hai to humne (1,5) means 2nd page pe 5 data ko dikhana hai
		//1,5 static diya hai lekin hume dynamic krna hai controller se krenge
		//Pageable  pageable = PageRequest.of(1, 2);
		Pageable  pageable = PageRequest.of(pageNo, pageSize);
		//NotesResponse class bnake dto me response denge jab pagination krte h to response me dete hai
		Page<Notes> pagenotes =notesRepository.findByCreatedByAndIsDeletedFalse(userId,pageable);
		//ab all notes get kr skta hu lekin pagination lgana hai to start krte h upper se qki findByCreatedBy method me pageble ka obj denge
		//notesDto me covert krenge notes ko
		List<NotesDto> notesDto = pagenotes.get().map(n->modelMapper.map(n, NotesDto.class)).toList();
		//response me set krna hai
		NotesResponse notes =NotesResponse.builder()
		.notes(notesDto)
		.pageNo(pagenotes.getNumber())
		.pageSize(pagenotes.getSize())
		.totalElements(pagenotes.getTotalElements())
		.totalPages(pagenotes.getTotalPages())
		.isFirst(pagenotes.isFirst())
		.isLast(pagenotes.isLast())
		.build();
		
		return notes;
	}

	@Override
	public void softDeleteNotes(Integer id) throws Exception {
		// id ke based pr notes get kiya jo user de rha hai
		Notes notes = notesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("notes id is invalid ! not found"));
		//isDeleted jo bydefault false hai usse true kr denge
		notes.setDeleted(true);
		notes.setDeletedOn(LocalDateTime.now());
		notesRepository.save(notes);
	}

	@Override
	public void restoreNotes(Integer id) throws Exception {
		Notes notes = notesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("notes id is invalid ! not found"));
		notes.setDeleted(false);
		notes.setDeletedOn(null); 
		notesRepository.save(notes);
	
		
	}

	@Override
	public List<NotesDto> getUserRecyclyBinNotes(Integer userId) {
		List<Notes> recycleNotes = notesRepository.findByCreatedByAndIsDeletedTrue(userId);
		//Notes entity ko --->notesDto me convert kro,jo deleted h wo
		List<NotesDto> notesList = recycleNotes.stream().map(note->modelMapper.map(note, NotesDto.class)).toList();
		return notesList;
	}

	@Override
	public void hardDeleteNotes(Integer id) throws Exception {
		Notes notes = notesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("notes not found"));
		//recycle bin me ka hi delete hoga ,bina soft dele kiye direct delete nhi kr skte
		if(notes.isDeleted()) {
			notesRepository.delete(notes);
		}else {
			throw new IllegalArgumentException("sorry you cant hard delete directly");
		}
	}

	@Override
	public void emptyRecycleBin(Integer userId) {
		//jo user login h uska all notes milega recycle bin me
		List<Notes> recycleNotes = notesRepository.findByCreatedByAndIsDeletedTrue(userId);
		if(!CollectionUtils.isEmpty(recycleNotes))
		{
			notesRepository.deleteAll(recycleNotes);	
		}
		
	}

	
}
