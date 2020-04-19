package com.twilio.voting.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.twilio.voting.interfaces.CandidateService;
import com.twilio.voting.model.Candidate;
import com.twilio.voting.model.CandidateImage;
import com.twilio.voting.model.CandidateSave;
import com.twilio.voting.repository.CandidateRepository;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateServiceImplementation implements CandidateService{

	private final CandidateRepository candidateRepository;
	private static final String FILE_TYPE_PNG = "image/png";
	private static final String FILE_TYPE_JPG = "image/jpg";
	private static final String FILE_TYPE_JPEG = "image/jpeg";
	
	
	@Override
	public List<Candidate> FetchAllCandidates() {
		return candidateRepository.findAll();
	}
	

	@Override
	public Candidate FetchCandidateDetailsById(String candidateId) {
		
		Optional<Candidate> retrievedCandidate = candidateRepository.findByCandidateId(candidateId);
		return retrievedCandidate.get();
	}
	
	
	@Override
	public Candidate FetchCandidateDetailsByName(String candidateName) {
		return candidateRepository.findByCandidateName(candidateName).get();
	}

	
	@Override
	public void RegisterCandidate(CandidateSave candidateToSave) {

		//Create New Candidate Instance
		Candidate candidate = new Candidate();
		
		//Generate Random UUIDv4 for Candidate
		UUID candidateUUID = UUID.randomUUID(); 
		
		//Set Values For Candidate's Fields
		candidate.setCandidateId(candidateUUID.toString());
		candidate.setCandidateName(candidateToSave.getCandidateName());
		candidate.setCandidateEmail(candidateToSave.getCandidateEmail());
		candidate.setTotalVoteCount(0);
		
		//Get Image byte Data
		byte[] candidateImageData = candidateToSave.getCandidateImage().getData();
		
		//Check Whether User Supplied An Image
		if(candidateImageData != null) {

			String candidateImageName = candidateToSave.getCandidateImage().getName();
			if(storeImage(candidateToSave.getCandidateImage())) 
				candidate.setCandidateImage("/images/candidates/" + candidateImageName.replace(" ", "-"));
			else 
				candidate.setCandidateImage("");
		}
		candidateRepository.save(candidate);
	}

	
	//Store Candidate's Image In Application's Images Folder
	private Boolean storeImage(CandidateImage candidateImage) {		

		//Get Image Info
		byte[] candidateImageData = candidateImage.getData();
		String candidateImageName = candidateImage.getName();
		String candidateImageType = candidateImage.getType();
		//Integer candidateImageSize = candidateImage.getSize();
		
		//Check if image is of expected format
		if(candidateImageType.contains(FILE_TYPE_PNG) || candidateImageType.contains(FILE_TYPE_JPG) || candidateImageType.contains(FILE_TYPE_JPEG)) {

			try { 
				Resource resource = new ClassPathResource("/static/images/candidates/"); 
				System.out.println(resource.getInputStream());
				System.out.println(resource.getInputStream().toString());
				System.out.println(resource.exists());
				System.out.println(resource.getURL());
				System.out.println(resource.getURL().getPath());
				System.out.println(resource.getURI());
				System.out.println(resource.getURI().getPath());
				System.out.println(resource.getURI().getRawPath());
				FileCopyUtils.copy(candidateImageData, new FileOutputStream(resource.getInputStream() + "/" + candidateImageName.replace(" ", "-")));
				return true;
			} 
			catch (FileNotFoundException e) { 
				e.printStackTrace();
				return false;
			} 
			catch (IOException e) {
				//TODO Auto-generated catch block 
				e.printStackTrace(); 
				return false;
			}
		}
		else {
			try {
				throw new FileUploadException("INVALID IMAGE FORMAT. Please upload a '.png' or '.jpeg' image");
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			return false;
		}
	}
	

	@Override
	public Boolean UpdateCandidate(String candidateId, CandidateSave candidateToUpdate) throws NotFoundException {
		
		Optional<Candidate> candidate = candidateRepository.findByCandidateId(candidateId);
		
		//Check whether candidateToUpdate exists in our DB
		if(candidate.isPresent()) {

			String candidateImage = "";
			String candidateName = candidateToUpdate.getCandidateName();
			String candidateEmail = candidateToUpdate.getCandidateEmail();

			//Get Image byte Data
			byte[] candidateImageData = candidateToUpdate.getCandidateImage().getData();
			
			//Check whether new candidate data from UI has an image attachment
			if(candidateImageData != null) {
				
				String candidateImageName = candidateToUpdate.getCandidateImage().getName();
				if(storeImage(candidateToUpdate.getCandidateImage())) {
					candidateImage = "/images/candidates/" + candidateImageName.replace(" ", "-");
					candidateRepository.updateCandidateInfo(candidateId, candidateName, candidateEmail, candidateImage);
					return true;
				} else {
					candidateRepository.updateCandidateInfo(candidateId, candidateName, candidateEmail, candidateImage);
					return false;
				}				
			}
			else {
				candidateRepository.updateCandidateNameAndEmail(candidateId, candidateName, candidateEmail);
			}
			return true;
		}
		else {			
			throw new NotFoundException("No Candidate with ID [" + candidateId + "] to UPDATE!");
		}
	}

	
	@Override
	public void UpdateCandidateVotes(String candidateId, Integer votes) {
		candidateRepository.updateCandidateVotes(candidateId, votes);		
	}
	
	
	@Override
	public void RemoveCandidate(String candidateId) throws NotFoundException {
		
		Optional<Candidate> candidateToRemove = candidateRepository.findByCandidateId(candidateId);
		//Check whether candidateToRemove exists in our DB
		if(candidateToRemove.isPresent()) {
			candidateRepository.deleteByCandidateId(candidateId);
		}
		else {
			throw new NotFoundException("No Candidate with ID [" + candidateId + "] to DELETE!");
		}		
	}


	@Override
	public void RemoveCandidateImage(String candidateId) {
		candidateRepository.deleteCandidateImage(candidateId);
	}
	
}
