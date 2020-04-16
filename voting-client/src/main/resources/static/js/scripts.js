/* Template: Leno - Mobile App HTML Landing Page Template
   Author: Inovatik
   Created: Mar 2019
   Description: Custom JS file
*/

(function($) {
    "use strict"; 
	
	/* Preloader */
	$(window).on('load', function() {
		var preloaderFadeOutTime = 500;
		function hidePreloader() {
			var preloader = $('.spinner-wrapper');
			setTimeout(function() {
				preloader.fadeOut(preloaderFadeOutTime);
			}, 500);
		}
		hidePreloader();
	});

	
	/* Navbar Scripts */
	// jQuery to collapse the navbar on scroll
    $(window).on('scroll load', function() {
		if ($(".navbar").offset().top > 20) {
			$(".fixed-top").addClass("top-nav-collapse");
		} else {
			$(".fixed-top").removeClass("top-nav-collapse");
		}
    });

	// jQuery for page scrolling feature - requires jQuery Easing plugin
	$(function() {
		$(document).on('click', 'a.page-scroll', function(event) {
			var $anchor = $(this);
			$('html, body').stop().animate({
				scrollTop: $($anchor.attr('href')).offset().top
			}, 600, 'easeInOutExpo');
			event.preventDefault();
		});
	});

    // closes the responsive menu on menu item click
    $(".navbar-nav li a").on("click", function(event) {
    if (!$(this).parent().hasClass('dropdown'))
        $(".navbar-collapse").collapse('hide');
    });


    /* Rotating Text - Morphtext */
	$("#js-rotating").Morphext({
		// The [in] animation type. Refer to Animate.css for a list of available animations.
		animation: "fadeIn",
		// An array of phrases to rotate are created based on this separator. Change it if you wish to separate the phrases differently (e.g. So Simple | Very Doge | Much Wow | Such Cool).
		separator: ",",
		// The delay between the changing of each phrase in milliseconds.
		speed: 2000,
		complete: function () {
			// Called after the entrance animation is executed.
		}
    });
    

    /* Card Slider - Swiper */
	var cardSlider = new Swiper('.card-slider', {
		autoplay: {
            delay: 4000,
            disableOnInteraction: false
		},
        loop: true,
        navigation: {
			nextEl: '.swiper-button-next',
			prevEl: '.swiper-button-prev'
		},
		slidesPerView: 3,
		spaceBetween: 20,
        breakpoints: {
            // when window is <= 992px
            992: {
                slidesPerView: 2
            },
            // when window is <= 768px
            768: {
                slidesPerView: 1
            } 
        }
    });

    
    /* Image Slider - Swiper */
    var imageSlider = new Swiper('.image-slider', {
        autoplay: {
            delay: 2000,
            disableOnInteraction: false
		},
        loop: false,
        navigation: {
			nextEl: '.swiper-button-next',
			prevEl: '.swiper-button-prev',
		},
        spaceBetween: 30,
        slidesPerView: 5,
		breakpoints: {
            // when window is <= 380px
            380: {
                slidesPerView: 1,
                spaceBetween: 10
            },
            // when window is <= 516px
            516: {
                slidesPerView: 2,
                spaceBetween: 10
            },
            // when window is <= 768px
            768: {
                slidesPerView: 3,
                spaceBetween: 20
            },
            // when window is <= 992px
            992: {
                slidesPerView: 4,
                spaceBetween: 30
            },
            // when window is <= 1200px
            1200: {
                slidesPerView: 5,
                spaceBetween: 30
            },
        }
    });


    /* Image Slider - Magnific Popup */
	$('.popup-link').magnificPopup({
		removalDelay: 300,
		type: 'image',
		callbacks: {
			beforeOpen: function() {
				this.st.image.markup = this.st.image.markup.replace('mfp-figure', 'mfp-figure ' + this.st.el.attr('data-effect'));
			},
			beforeClose: function() {
				$('.mfp-figure').addClass('fadeOut');
			}
		},
		gallery:{
			enabled:true //enable gallery mode
		}
    });


    /* Move Form Fields Label When User Types */
    // for input and textarea fields
    $("input, textarea").keyup(function(){
		if ($(this).val() != '') {
			$(this).addClass('notEmpty');
		} else {
			$(this).removeClass('notEmpty');
		}
    });

    /* Candidate Form */
    $("#candidateForm").validator().on("submit", function(event) {
    	if (event.isDefaultPrevented()) {
            // handle the invalid form...
            candidateFormError();
            $("#candidateId").val() === "" && candidateSubmitSaveMSG(false, "Please fill all fields!");
            $("#candidateId").val() !== "" && candidateSubmitUpdateMSG(false, "Please fill all fields!");
        } else {
            // everything looks good!
            event.preventDefault();
            $("#candidateId").val() === "" && candidateSaveForm();
            $("#candidateId").val() !== "" && candidateUpdateForm();        
        }
    });
    
    function candidateSaveForm() {
        // initiate variables with form content
    	let byteImage = null;
		let name = $("#candidateName").val();
		let email = $("#candidateEmail").val();
		let image = $('#cpicture').prop('files')[0];   

        if(image !== undefined)	{
        	let reader = new FileReader();
        	reader.readAsArrayBuffer(image);
        	reader.onload = function (evt) {  
        		let imageByte = new Uint8Array(evt.target.result);            
        		byteImage = Object.values(imageByte);
        		submitSaveForm(name, email, byteImage);
        	};
        }
        else submitSaveForm(name, email, byteImage);
	}

    function candidateUpdateForm() {
        // initiate variables with form content
    	let byteImage = null;
		let name = $("#candidateName").val();
		let email = $("#candidateEmail").val();
		let image = $('#cpicture').prop('files')[0];   

        if(image !== undefined)	{
        	let reader = new FileReader();
        	reader.readAsArrayBuffer(image);
        	reader.onload = function (evt) {  
        		let imageByte = new Uint8Array(evt.target.result);            
        		byteImage = Object.values(imageByte);
        		submitUpdateForm(name, email, byteImage);
        	};
        }
        else submitUpdateForm(name, email, byteImage);
	}
    
    function submitSaveForm(...params) {
        let newCandidate = {
        	"candidateName": params[0],
        	"candidateEmail": params[1],
        	"candidateImage": params[2]
        };
        
        $.ajax({
            type: "POST",
            url: "/api/v1/candidate",
            contentType: "application/json",
            data: JSON.stringify(newCandidate), 
            success: function(text) {
            	candidateFormSaveSuccess();
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	candidateFormError();
            	candidateSubmitSaveMSG(false, jqXHR.responseJSON.error);
            }
        });
    }
    
    
    function submitUpdateForm(...params) {
        let existingCandidate = {
        	"candidateName": params[0],
        	"candidateEmail": params[1],
        	"candidateImage": params[2]
        };
        
        $.ajax({
            type: "PUT",
            url: "/api/v1/candidate/" + $("#candidateId").val(),
            contentType: "application/json",
            data: JSON.stringify(existingCandidate), 
            success: function(text) {
            	candidateFormUpdateSuccess();
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	candidateFormError();
            	candidateSubmitUpdateMSG(false, jqXHR.responseJSON.error);
            }
        });
    }
    
    function candidateFormSaveSuccess() {
        $("#candidateForm")[0].reset();
        $("#candidateForm").addClass('w-50 m-auto');
        candidateSubmitSaveMSG(true, "Candidate Registered!");
        $("input").removeClass('notEmpty'); // resets the field label after submission
    }
    
    function candidateFormUpdateSuccess() {
        $("#candidateForm")[0].reset();
        $("#candidateForm").addClass('w-50 m-auto');
        candidateSubmitUpdateMSG(true, "Candidate Details Updated!");
        $("input").removeClass('notEmpty'); // resets the field label after submission
    }

    function candidateFormError() {
        $("#candidateForm").removeClass().addClass('shake animated w-50 m-auto').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
            $(this).removeClass();
        });
	}

    function candidateSubmitSaveMSG(valid, msg) {
    	if (valid) {
            swal({type: 'success', title: 'Success', text: 'Candidate Registered', showConfirmButton: false, timer: 2000});
        } else {
            swal({type: 'error', title: 'Error..', text: msg, showConfirmButton: false, timer: 2000});
        }
    }
    
    function candidateSubmitUpdateMSG(valid, msg) {
        if (valid) {
            swal({type: 'success', title: 'Success', text: 'Candidate Updated', showConfirmButton: false, timer: 2000});
        } else {
            swal({type: 'error', title: 'Error..', text: msg, showConfirmButton: false, timer: 2000});
        }
    }


	/* Removes Long Focus On Buttons */
	$(".button, a, button").mouseup(function() {
		$(this).blur();
	});

})(jQuery);