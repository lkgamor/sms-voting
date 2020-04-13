package com.twilio.voting.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.stereotype.Service;

import com.twilio.voting.interfaces.CandidateService;
import com.twilio.voting.model.Candidate;
import com.twilio.voting.repository.CandidateRepository;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateServiceImplementation implements CandidateService{

	private final CandidateRepository candidateRepository;

	@Override
	public List<Candidate> FetchAllCandidates() {
		return candidateRepository.findAll();
	}

	@Override
	public Candidate FetchCandidateDetailsById(String candidateId) {
		
		Optional<Candidate> retrievedCandidate = candidateRepository.findByCandidateId(candidateId);
		//Check whether retrievedCandidate has an image attachment
		if(retrievedCandidate.get().getCandidateImage() != null) {
			System.out.println(retrievedCandidate.get().getCandidateName() + " has a picture");
			System.out.println("BEFORE DECOMPRESSION => " + retrievedCandidate.get().getCandidateImage());
			byte[] candidateImage = decompressImage(retrievedCandidate.get().getCandidateImage());
			System.out.println("AFTER DECOMPRESSION => " + candidateImage);
			retrievedCandidate.get().setCandidateImage(candidateImage);
		}
		System.out.println(retrievedCandidate.get());
		return retrievedCandidate.get();
	}
	
	@Override
	public Candidate FetchCandidateDetailsByName(String candidateName) {
		return candidateRepository.findByCandidateName(candidateName).get();
	}

	@Override
	public void RegisterCandidate(Candidate candidate) {

		//Generate Random UUIDv4 for Candidate
		UUID candidateUUID = UUID.randomUUID(); 
		
		candidate.setCandidateId(candidateUUID.toString());
		candidate.setTotalVoteCount(0);
		
		//Check whether user supplied an image attachment
		if(candidate.getCandidateImage() != null) {
			System.out.println("BEFORE COMPRESSION => " + candidate.getCandidateImage());
			byte[] candidateImage = compressImage(candidate.getCandidateImage());
			System.out.println("AFTER COMPRESSION => " + candidateImage);
			candidate.setCandidateImage(candidateImage);
		}
		candidateRepository.save(candidate);
	}

	private byte[] compressImage(byte[] data) {

		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];

		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}

		log.info("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}
	
	private byte[] decompressImage(byte[] data) {

		Inflater inflater = new Inflater();		
		inflater.setInput(data);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];		
		try {		
			while (!inflater.finished()) {		
				int count = inflater.inflate(buffer);		
				outputStream.write(buffer, 0, count);		
			}		
			outputStream.close();		
		} catch (IOException ioe) {

		} catch (DataFormatException e) {

		}

		return outputStream.toByteArray();
	}

	@Override
	public void UpdateCandidate(String candidateId, Candidate candidate) throws NotFoundException {
		
		Optional<Candidate> candidateToUpdate = candidateRepository.findByCandidateId(candidateId);
		
		//Check whether candidateToUpdate exists in our DB
		if(candidateToUpdate.isPresent()) {
			//Check whether new candidate data from UI has an image attachment
			if(candidate.getCandidateImage() != null) {
				String candidateName = candidate.getCandidateName();
				String candidateEmail = candidate.getCandidateEmail();
				byte[] candidateImage = compressImage(candidate.getCandidateImage());
				candidateRepository.updateCandidateInfo(candidateId, candidateName, candidateEmail, candidateImage);
			}
			else {
				String candidateName = candidate.getCandidateName();
				String candidateEmail = candidate.getCandidateEmail();
				candidateRepository.updateCandidateNameAndEmail(candidateId, candidateName, candidateEmail);
			}
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
	
}
