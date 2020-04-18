(function($) {
	"use strict"; 

	const CANDIDATES_CONTAINER = document.querySelector('.candidates__list');
	$(window).on('load', function() {
		getCandidates();
	});

	let getCandidates = () => {
		$.ajax({
			url: "/api/v1/candidate",
			success: (candidates)=> {
				
				candidates.forEach((candidate)=> {
					let id = candidate.candidateId;
					let name = candidate.candidateName || 'N/A';
					let votes = candidate.totalVoteCount;
					let image = candidate.candidateImage || '/images/candidate.svg';
					const CANDIDATE_CARD = `<div class="candidate__card">
				                            <div class="candidate__image">
				                            	<img alt="Candidate Picture" class="card-image" src="${image}">
				                            </div>
				                            <div class="candidate__name">
				                            	<h3>${name}</h3>
				                            </div>
				                            <div class="candidate__votes">
				                            	<div class="counter-value number-count">${votes}</div>
				                            </div>
				                            <div class="candidate__button">
				                            	 <a class="btn-solid-lg page-scroll" href="/candidate/${id}">EDIT INFO</a>
				                            </div>
				                        </div> `;					
					CANDIDATES_CONTAINER.insertAdjacentHTML('afterbegin', CANDIDATE_CARD);
				});
				
				if (candidates.length === 1) {
					$('.candidates__list').removeClass('candidates__list--multiple').addClass('candidates__list--single');
					$('.candidates__image').find('img').removeClass('candidate__image--35').addClass('candidate__image--45');
				}
				else {
					$('.candidates__list').removeClass('candidates__list--single').addClass('candidates__list--multiple');
					$('.candidates__image').find('img').removeClass('candidate__image--45').addClass('candidate__image--35');
				}
			},
			error: ()=> {
				console.log("Error...")
			}
		});
	};
	
})(jQuery);