package com.aamir.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.util.StreamUtils;
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
		ObjectMapper ob=new ObjectMapper();
		NotesDto notesDto=ob.readValue(notes, NotesDto.class);
		
		  checkCategoryExist(notesDto.getCategory());
		  
		  Notes notesMap = modelMapper.map(notesDto, Notes.class);
			FileDetails fileDetails=saveFileDetails(file);
			if(!ObjectUtils.isEmpty(fileDetails)) {
				notesMap.setFileDetails(fileDetails);
			}else {
				notesMap.setFileDetails(null);
			}
			
		  
		  Notes savenotes = notesRepository.save(notesMap);
		  if(!ObjectUtils.isEmpty(savenotes)) 
		  { 
			  return true; 
			  }
		 
		return false;
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

	
}
