/*
 * Copyright (c) 2021 Marketify
 * Author: Marketify
 * This file is made for CURRENT TEMPLATE
*/

jQuery(document).ready(function(){

	"use strict";
	
	// here all ready functions
	
	iknow_tm_down();
	iknow_tm_content_height();
	iknow_tm_menu_width();
	iknow_tm_portfolio();
	iknow_tm_portfolio_popup();
	iknow_tm_service_popup();
	iknow_tm_news_popup();
	iknow_tm_cursor();
	iknow_tm_imgtosvg();
	iknow_tm_popup();
	iknow_tm_data_images();
	iknow_tm_contact_form();
	iknow_tm_menubar();
	iknow_tm_hero_height();
	iknow_tm_page_transition();
	dodo_parallax_animation();
	if(jQuery('.iknow_tm_hero .main_menu, .iknow_tm_fixed_content .menubar').length ){
		iknow_tm_circular_progress();
	}
	
	// used for index-7.html (without click action)
	if(jQuery('.iknow_tm_extra_demo_2,.iknow_tm_extra_demo').length ){
		iknow_tm_circular_progress();
		iknow_tm_owl_carousel();
	}
	iknow_tm_extra_menu();
	iknow_tm_vanta_effects();
	iknow_tm_trigger_opener();
	iknow_tm_trigger_menu();
	iknow_tm_menu_width_new();
	hashtag();
	hashtag2();
	iknow_tm_scrollable();
	iknow_tm_menu_image_animation();
	
	
	jQuery(window).load('body', function(){
		iknow_tm_my_load();
	});
	
	jQuery(window).on('resize', function(){
		iknow_tm_hero_height();
	});
	
});

// -----------------------------------------------------
// ---------------   FUNCTIONS    ----------------------
// -----------------------------------------------------

// -----------------------------------------------------
// -----------------    DOWN    ------------------------
// -----------------------------------------------------

function iknow_tm_down(){
	
	"use strict";
		
	jQuery('.anchor').on('click',function(){
		
		if($.attr(this, 'href') !== '#'){
			$('html, body').animate({
				scrollTop: $($.attr(this, 'href')).offset().top-110
			}, 800);
		}
		
		return false;
	});	
}

// ------------------------------------------------
// -------------------  ANCHOR --------------------
// ------------------------------------------------

jQuery('.anchor_nav').onePageNav();

// -----------------------------------------------------
// ---------------   HERO HEIGHT    --------------------
// -----------------------------------------------------

function iknow_tm_hero_height(){
	
	"use strict";
	
	var H 		= $('.iknow_tm_hero .main_menu').outerHeight();
	var WW		= jQuery(window).width();
	var topbarH = $('.iknow_tm_topbar').height();
	if(WW >= 1040){
		$('.iknow_tm_hero .main_info').css({minHeight: 'calc(100vh - '+H+'px)',paddingTop:topbarH+'px'});
	}
}

// -------------------------------------------------
// ------------    CONTENT HEIGHT    ---------------
// -------------------------------------------------

function iknow_tm_content_height(){
	
	"use strict";
	
	var ww				= jQuery(window).width();
	var wh				= jQuery(window).height();
	var topbarH			= jQuery('.iknow_tm_topbar').outerHeight();
	var footerH			= jQuery('.iknow_tm_copyright.fixed').outerHeight();
	var topPosition		= topbarH+62;
	var topPosition2	= topbarH+35;
	var topPosition3	= topbarH+25;
	var total			= wh-topbarH-footerH-62;
	var total2			= wh-topbarH-footerH-40;
	var total3			= wh-topbarH-footerH;
	
	if(ww > 1400){
		jQuery('.iknow_tm_fixed_content .fixed_content_inner').css({height:total+'px',top:topPosition+'px'});
	}
	else if(ww >= 1040){
		jQuery('.iknow_tm_fixed_content .fixed_content_inner').css({height:total3+'px',top:topPosition3+'px'});
	}else{
		jQuery('.iknow_tm_fixed_content .fixed_content_inner').css({height:total2+'px',top:topPosition2+'px'});
	}
}

// -------------------------------------------------
// -----------------    PORTFOLIO    ---------------
// -------------------------------------------------

function iknow_tm_portfolio(){

	"use strict";

	if(jQuery().isotope) {

		// Needed variables
		var filter		 = jQuery('.iknow_tm_portfolio .portfolio_filter ul');

		if(filter.length){
			// Isotope Filter 
			filter.find('a').on('click', function(){
				var element		= jQuery(this);
				var selector 	= element.attr('data-filter');
				var list		= element.closest('.iknow_tm_portfolio').find('.portfolio_list').children('ul');
				list.isotope({ 
					filter				: selector,
					animationOptions	: {
						duration			: 750,
						easing				: 'linear',
						queue				: false
					}
				});
				
				filter.find('a').removeClass('current');
				element.addClass('current');
				return false;
			});	
		}
	}
}

// -------------------------------------------------
// -----------  PORTFOLIO POPUP  -------------------
// -------------------------------------------------

function iknow_tm_portfolio_popup(){
	
	"use strict";
	
	var modalBox		= jQuery('.iknow_tm_modalbox');
	var button			= jQuery('.iknow_tm_portfolio .portfolio_popup');
	var closePopup		= modalBox.find('.close');
	
	button.off().on('click',function(){
		var element = jQuery(this);
		var parent 	= element.closest('.list_inner');
		var content = parent.find('.hidden_content').html();
		var image	= parent.find('.image .main').data('img-url');
		var details = parent.find('.details').html();
		modalBox.addClass('opened');
		modalBox.find('.description_wrap').html(content);
		modalBox.find('.popup_details').prepend('<div class="top_image"><img src="img/thumbs/4-2.jpg" alt="" /><div class="main" data-img-url="'+image+'"></div></div>');
		modalBox.find('.popup_details .top_image').after('<div class="portfolio_main_title">'+details+'<div>');
		iknow_tm_data_images();
		return false;
	});
	closePopup.on('click',function(){
		modalBox.removeClass('opened');
		modalBox.find('.description_wrap').html('');
		return false;
	});
}

// -------------------------------------------------
// -------------  SERVICE POPUP  -------------------
// -------------------------------------------------

function iknow_tm_service_popup(){
	
	"use strict";
	
	var modalBox		= jQuery('.iknow_tm_modalbox');
	var button			= jQuery('.iknow_tm_services .iknow_tm_full_link');
	var closePopup		= modalBox.find('.close');
	
	button.on('click',function(){
		var element = jQuery(this);
		var parent  = element.closest('.list_inner');
		var title	= parent.find('.title').text();
		var content = parent.find('.hidden_content').html();
		modalBox.addClass('opened');
		modalBox.find('.description_wrap').html(content);
		iknow_tm_data_images();
		modalBox.find('.service_informations .image').after('<div class="title"><h3>'+title+'</h3></div>');
		return false;
	});
	closePopup.on('click',function(){
		modalBox.removeClass('opened');
		modalBox.find('.description_wrap').html('');
		return false;
	});
}

// -------------------------------------------------
// ----------------  NEWS POPUP  -------------------
// -------------------------------------------------

function iknow_tm_news_popup(){
	
	"use strict";
	
	var modalBox		= jQuery('.iknow_tm_modalbox');
	var button			= jQuery('.iknow_tm_news .iknow_tm_full_link,.iknow_tm_news .news_list ul li .details .title a');
	var closePopup		= modalBox.find('.close');
	
	button.on('click',function(){
		var element  = jQuery(this);
		var parent 	 = element.closest('.list_inner');
		var content  = parent.find('.hidden_content').html();
		var image	 = parent.find('.image .main').data('img-url');
		var category = parent.find('.details .category a').text();
		var title	 = parent.find('.details .title a').text();
		modalBox.addClass('opened');
		modalBox.find('.description_wrap').html(content);
		modalBox.find('.news_informations').prepend('<div class="image"><img src="img/thumbs/4-2.jpg" alt="" /><div class="main" data-img-url="'+image+'"></div></div>');
		modalBox.find('.news_informations .image').after('<div class="details"><span>'+category+'</span><h3>'+title+'</h3><div>');
		iknow_tm_data_images();
		return false;
	});
	closePopup.on('click',function(){
		modalBox.removeClass('opened');
		modalBox.find('.description_wrap').html('');
		return false;
	});
}

// -----------------------------------------------------
// ---------------   MENU WIDTH   ----------------------
// -----------------------------------------------------

function iknow_tm_menu_width(){
	
	"use strict";
	
	var ww	= jQuery(window).width();
	var btn	= jQuery('.iknow_tm_hero .main_menu ul li a');
	
	btn.on('mouseenter',function(){
		var element 	= jQuery(this);
		var li			= element.closest('li');
		if(li.hasClass('active') || li.hasClass('entered')){return false;}
		li.closest('ul').children().removeClass('entered');
		li.addClass('entered');
		var elWidth		= li.outerWidth();
		var textWidth	= li.find('span').outerWidth();
		li.css({width: elWidth+textWidth+'px'});
	}).on('mouseleave',function(){
		var element 	= jQuery(this);
		var li			= element.closest('li');
		var allLi		= li.closest('ul').children();
		var fixedLi		= li.closest('ul').children('.fixed');
		var activeLi	= li.closest('ul').children('.active');
		allLi.removeClass('entered').css({width:'135px'});
		if(ww <= 1400){
			allLi.removeClass('entered').css({width:'95px'});
		}
		
		if(activeLi.length){
			var elWidth		= 135;
			if(ww <= 1400){
				elWidth = 95;
			}
			var textWidth	= activeLi.find('span').outerWidth();
			activeLi.removeClass('fixed').addClass('active').css({width: elWidth+textWidth+'px'});
		}
	});
	
	btn.on('click',function(){
		var element 	= jQuery(this);
		var li			= element.closest('li');
		var href		= element.attr('href');
		if(li.hasClass('active') && !li.hasClass('entered')){
			return false;
		}
		li.addClass('active fixed');
		var mainMenu	= element.closest('.main_menu');
		
		if(ww <= 1040){
			$('html, body').animate({
				scrollTop: $('.iknow_tm_mainpart').offset().top - 30
			}, 1000);
		}else{
			$('html, body').animate({
				scrollTop: mainMenu.offset().top - 30
			}, 1000);
		}
		
		
		li.siblings().removeClass('active').css({width:'135px'});
		if(ww <= 1400){
			li.siblings().removeClass('active').css({width:'95px'});
		}
		$('.iknow_tm_main_section.active').removeClass('active');
		jQuery('.iknow_tm_mainpart').addClass('opened');
		jQuery('.iknow_tm_hero .background_shape').addClass('opened');
		jQuery('.iknow_tm_copyright.hidden').addClass('visible');
		$(href).addClass('active');
		
		if(!li.hasClass('entered')){
			var elWidth		= li.outerWidth();
			var textWidth	= li.find('span').outerWidth();
			li.css({width: elWidth+textWidth+'px'});
		}
		iknow_tm_owl_carousel();
		
		return false;
	});
	
	jQuery('.iknow_tm_hero .main_menu ul li.active').each(function(){
		var element 	= jQuery(this);
		var elWidth		= element.outerWidth();
		var textWidth	= element.find('span').outerWidth();
		var href		= element.find('a').attr('href');
		element.css({width: elWidth+textWidth+'px'});
		$(href).addClass('active');
	});
}

// -------------------------------------------------
// -------------  PROGRESS BAR  --------------------
// -------------------------------------------------

function tdProgress(container){
	
	"use strict";
		
	container.find('.progress_inner').each(function() {
		var progress 		= jQuery(this);
		var pValue 			= parseInt(progress.data('value'), 10);
		var pColor			= progress.data('color');
		var pBarWrap 		= progress.find('.bar');
		var pBar 			= progress.find('.bar_in');
		var number 			= progress.find('.number');
		var label 			= progress.find('.label');
		number.css({right:(100 - pValue)+'%'});
		setTimeout(function(){label.addClass('opened');},500);
		pBar.css({width:pValue+'%', backgroundColor:pColor});
		setTimeout(function(){pBarWrap.addClass('open');});
	});
}
function progress_by_frenify(wrapper){
	
	"use strict";
	
	var element;
	if(wrapper){
		element = wrapper.find('.dodo_progress');
	}else{
		element = jQuery('.dodo_progress');
	}
	element.each(function() {
		var pWrap = jQuery(this);
		pWrap.find('.number').css({right:'100%'});
		console.log(pWrap.find('.number').length);
		pWrap.waypoint({handler: function(){tdProgress(pWrap);},offset:'90%'});	
	});
}
if(!$('.iknow_tm_extra_menu').length){
	progress_by_frenify();
}

// -----------------------------------------------------
// ---------------   PRELOADER   -----------------------
// -----------------------------------------------------

function iknow_tm_preloader(){
	
	"use strict";
	
	var isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) ? true : false;
	var preloader = $('#preloader');
	
	if (!isMobile) {
		setTimeout(function() {
			preloader.addClass('preloaded');
		}, 800);
		setTimeout(function() {
			preloader.remove();
		}, 2000);

	} else {
		preloader.remove();
	}
}

// -----------------------------------------------------
// -----------------   MY LOAD    ----------------------
// -----------------------------------------------------

function iknow_tm_my_load(){
	
	"use strict";
	
	var speed	= 500;
	setTimeout(function(){iknow_tm_preloader();},speed);
	setTimeout(function(){jQuery('body').addClass('opened');},speed+2000);
}

// -----------------------------------------------------
// ------------------   CURSOR    ----------------------
// -----------------------------------------------------

function iknow_tm_cursor(){
	
    "use strict";
	
	var myCursor	= jQuery('.mouse-cursor');
	
	if(myCursor.length){
		if ($("body")) {
        const e = document.querySelector(".cursor-inner"),
            t = document.querySelector(".cursor-outer");
        let n, i = 0,
            o = !1;
        window.onmousemove = function (s) {
            o || (t.style.transform = "translate(" + s.clientX + "px, " + s.clientY + "px)"), e.style.transform = "translate(" + s.clientX + "px, " + s.clientY + "px)", n = s.clientY, i = s.clientX
        }, $("body").on("mouseenter", "a,.iknow_tm_topbar .trigger, .cursor-pointer", function () {
            e.classList.add("cursor-hover"), t.classList.add("cursor-hover")
        }), $("body").on("mouseleave", "a,.iknow_tm_topbar .trigger, .cursor-pointer", function () {
            $(this).is("a") && $(this).closest(".cursor-pointer").length || (e.classList.remove("cursor-hover"), t.classList.remove("cursor-hover"))
        }), e.style.visibility = "visible", t.style.visibility = "visible"
    }
	}
};

// -----------------------------------------------------
// ---------------    IMAGE TO SVG    ------------------
// -----------------------------------------------------

function iknow_tm_imgtosvg(){
	
	"use strict";
	
	jQuery('img.svg').each(function(){
		
		var jQueryimg 		= jQuery(this);
		var imgClass		= jQueryimg.attr('class');
		var imgURL			= jQueryimg.attr('src');

		jQuery.get(imgURL, function(data) {
			// Get the SVG tag, ignore the rest
			var jQuerysvg = jQuery(data).find('svg');

			// Add replaced image's classes to the new SVG
			if(typeof imgClass !== 'undefined') {
				jQuerysvg = jQuerysvg.attr('class', imgClass+' replaced-svg');
			}

			// Remove any invalid XML tags as per http://validator.w3.org
			jQuerysvg = jQuerysvg.removeAttr('xmlns:a');

			// Replace image with new SVG
			jQueryimg.replaceWith(jQuerysvg);

		}, 'xml');

	});
}

// -----------------------------------------------------
// --------------------   POPUP    ---------------------
// -----------------------------------------------------

function iknow_tm_popup(){
	
	"use strict";

	jQuery('.gallery_zoom').each(function() { // the containers for all your galleries
		jQuery(this).magnificPopup({
			delegate: 'a.zoom', // the selector for gallery item
			type: 'image',
			gallery: {
			  enabled:true
			},
			removalDelay: 300,
			mainClass: 'mfp-fade'
		});

	});
	jQuery('.popup-youtube, .popup-vimeo').each(function() { // the containers for all your galleries
		jQuery(this).magnificPopup({
			disableOn: 700,
			type: 'iframe',
			mainClass: 'mfp-fade',
			removalDelay: 160,
			preloader: false,
			fixedContentPos: false
		});
	});
	
	jQuery('.soundcloude_link').magnificPopup({
	  type : 'image',
	   gallery: {
		   enabled: true, 
	   },
	});
}

// -----------------------------------------------------
// ---------------   DATA IMAGES    --------------------
// -----------------------------------------------------

function iknow_tm_data_images(){
	
	"use strict";
	
	var data			= jQuery('*[data-img-url]');
	
	data.each(function(){
		var element			= jQuery(this);
		var url				= element.data('img-url');
		element.css({backgroundImage: 'url('+url+')'});
	});
}

// -----------------------------------------------------
// ----------------    CONTACT FORM    -----------------
// -----------------------------------------------------

function iknow_tm_contact_form(){
	
	"use strict";
	
	jQuery(".contact_form #send_message").off().on('click', function(){
		
		var name 		= jQuery(".contact_form #name").val();
		var email 		= jQuery(".contact_form #email").val();
		var message 	= jQuery(".contact_form #message").val();
		var subject 	= jQuery(".contact_form #subject").val();
		var success     = jQuery(".contact_form .returnmessage").data('success');
	
		jQuery(".contact_form .returnmessage").empty(); //To empty previous error/success message.
		//checking for blank fields	
		if(name===''||email===''||message===''){
			
			jQuery('div.empty_notice').slideDown(500).delay(2000).slideUp(500);
		}
		else{
			// Returns successful data submission message when the entered information is stored in database.
			jQuery.post("modal/contact.php",{ ajax_name: name, ajax_email: email, ajax_message:message, ajax_subject: subject}, function(data) {
				
				jQuery(".contact_form .returnmessage").append(data);//Append returned message to message paragraph
				
				
				if(jQuery(".contact_form .returnmessage span.contact_error").length){
					jQuery(".contact_form .returnmessage").slideDown(500).delay(2000).slideUp(500);		
				}else{
					jQuery(".contact_form .returnmessage").append("<span class='contact_success'>"+ success +"</span>");
					jQuery(".contact_form .returnmessage").slideDown(500).delay(4000).slideUp(500);
				}
				
				if(data===""){
					jQuery("#contact_form")[0].reset();//To reset form fields on success
				}
				
			});
		}
		return false; 
	});
}

// -----------------------------------------------------
// --------------------    WOW JS    -------------------
// -----------------------------------------------------

 new WOW().init();

// -----------------------------------------------------
// ----------------    OWL CAROUSEL    -----------------
// -----------------------------------------------------

function iknow_tm_owl_carousel(){

	"use strict";
	
	var carousel			= jQuery('.iknow_tm_testimonials .owl-carousel');
	var carousel2			= jQuery('.iknow_tm_fixed_content .iknow_tm_testimonials .owl-carousel');
	
	var rtlMode		= false;

	if(jQuery('body').hasClass('rtl')){
		rtlMode = 'true';
	}
	
	carousel2.owlCarousel({
		items: 1,
	});
	
	carousel.owlCarousel({
		loop: true,
		items: 2,
		lazyLoad: false,
		margin: 25,
		autoplay: true,
		autoplayTimeout: 7000,
		rtl: rtlMode,
		dots: true,
		nav: false,
		navSpeed: false,
		responsive : {
				0 : {
					items: 1
				},
				768 : {
					items: 2
				}
			}
	});
	iknow_tm_imgtosvg();
}

function iknow_tm_circular_progress(){
	"use strict";
	
	var ww		= jQuery(window).width();
	var circVal;
	
	if(ww > 1400){
		circVal = 213;
	}
	else if(ww >= 768){
		circVal = 170;
	}
	else{
		circVal = 120;
	}
	
	jQuery('.circular_progress_bar .myCircle').each(function(){
		var element	= jQuery(this);
		element.append('<h3 class="number"></h3>');
		var value	= element.data('value');
		element.circleProgress({
			size: circVal,
			value: 0,
			animation: {duration: 1400},
			thickness: 5,
			fill: "#ff451b",
			emptyFill: 'rgba(0,0,0,0)',
			startAngle: -Math.PI/2
		  }).on('circle-animation-progress', function(event, progress, stepValue) {
				element.find('h3').text(parseInt(stepValue.toFixed(2)*100) + '%');
		  });
		  element.circleProgress('value', 1.0);
		  setTimeout(function() { element.circleProgress('value', value); }, 1400);
	});
}

function by_frenify(wrapper,speed){
	"use strict";
	
	var ww		= jQuery(window).width();
	var circVal;
	
	if(ww > 1400){
		circVal = 213;
	}
	else if(ww >= 768){
		circVal = 170;
	}
	else{
		circVal = 120;
	}
	
	var element = wrapper.find('.myCircle');
	
	if(element.length > 0){
		
		element.each(function(){
			var e		= jQuery(this);
			var value	= e.data('value');
			var speed2 	= 1400;
			
			if(!e.find('.number').length){
				e.append('<h3 class="number"></h3>');
			}
			
			e.circleProgress({
				size: circVal,
				value: 0,
				animation: {duration: speed2},
				thickness: 5,
				fill: "#ff451b",
				emptyFill: 'rgba(0,0,0,0)',
				startAngle: -Math.PI/2
			}).on('circle-animation-progress', function(event, progress, stepValue) {
				e.find('h3').text(parseInt(stepValue.toFixed(2)*100) + '%');
			});
			
			setTimeout(function() { e.circleProgress('value', 1.0); }, speed);
			setTimeout(function() { e.circleProgress('value', value); }, speed2+speed);
		});
		
	}
}

// -----------------------------------------------------
// ---------------   EXTRA MENU    ---------------------
// -----------------------------------------------------

function iknow_tm_extra_menu(){
	
	"use strict";
	
	var item	= jQuery('.iknow_tm_extra_menu .unorderest_li');
	var speed	= 1000;
	item.each(function(){
		var element			= jQuery(this);
		var content			= jQuery(element.find('.iknow_tm_full_link').attr('href')).html();
		element.append('<div class="details_wrapper"></div>').find('.details_wrapper').html(content);
	});
	
	
	var button  = jQuery('.iknow_tm_extra_menu .iknow_tm_full_link');
	
	button.on('click',function(){
		var element			= jQuery(this);
		var li				= element.closest('li');
		
		var details			= li.find('.details_wrapper');
		var detailsH		= details.outerHeight() * 0.7;
		var min = 500, max = 1000;
		speed = detailsH < min ? min:detailsH;
		speed = speed > max ? max:speed;
		speed = parseInt(speed);
		
		if(li.hasClass('opened')){
			li.removeClass('opened').find('.details_wrapper').slideUp(speed);return false;
		}else{
			li.siblings().removeClass('opened');
			li.addClass('opened');
		}
		
		var index			= li.index();
		var marginBottom 	= parseInt(li.css("margin-bottom"));
		var liHeight		= li.find('.list_in').outerHeight();
		var parent			= li.closest('.menu_list');
		var top				= parent.offset().top + (liHeight + marginBottom) * index;
		
		/* Scroll the window to the selected element */
		$('html, body').animate({
			scrollTop: top - marginBottom
		},speed);
		
		li.siblings().find('.details_wrapper').slideUp(speed);
		li.find('.details_wrapper').slideDown(speed);
		by_frenify(li,speed);
		
		// reinitialization functions
		iknow_tm_owl_carousel();
		progress_by_frenify(li);
		iknow_tm_portfolio_popup();
		iknow_tm_service_popup();
		iknow_tm_news_popup();
		iknow_tm_popup();
		iknow_tm_portfolio();
		iknow_tm_contact_form();
		return false;
	});
}

// -----------------------------------------------------
// -------------------   MENUBAR    --------------------
// -----------------------------------------------------

function iknow_tm_menubar(){
	
	"use strict";
	
	var li		= jQuery('.iknow_tm_fixed_content .menubar ul li, .transition_link a');
	var button	= li.find('.iknow_tm_full_link');
	
	button.on('click',function(){
		var element = jQuery(this);	
		var parent	= element.closest('li');
		var href	= element.attr('href');
		if(!parent.hasClass('active')){
			li.removeClass('active');
			parent.addClass('active');
			$('.iknow_tm_main_section').removeClass('active');
			$(href).addClass('active');
			$('.mainbar').animate({
				scrollTop: 0
			},300);
			iknow_tm_owl_carousel();
		}
	});
}

// -----------------------------------------------------
// -------------   PAGE TRANSITION    ------------------
// -----------------------------------------------------

function iknow_tm_page_transition(){
	
	"use strict";
	
	var section 		= jQuery('.iknow_tm_animate_section');
	var allLi 			= jQuery('.transition_link li');
	var button			= jQuery('.transition_link a');
	var wrapper 		= jQuery('.iknow_tm_all_wrap');
	var enter	 		= wrapper.data('enter');
	var exit		 	= wrapper.data('exit');
	
	button.on('click',function(){
		var element 	= jQuery(this);
		var href		= element.attr('href');
		if(element.parent().hasClass('iknow_tm_button')){
			jQuery('.menu.transition_link a[href="'+href+'"]').trigger('click');
			hashtag2();
			return false;
		}
		var sectionID 	= jQuery(href);
		var parent	 	= element.closest('li');
			if(!parent.hasClass('active')) {
				allLi.removeClass('active');
				wrapper.find(section).removeClass('animated '+enter);
				if(wrapper.hasClass('opened')) {
					wrapper.find(section).addClass('animated '+exit);
				}
				parent.addClass('active');
				wrapper.addClass('opened');
				wrapper.find(sectionID).removeClass('animated '+exit).addClass('animated '+enter);
				jQuery(section).addClass('hidden');
				jQuery(sectionID).removeClass('hidden').addClass('active');
				iknow_tm_owl_carousel();
				iknow_tm_circular_progress();
			}
		return false;
	});
}

// -----------------------------------------------------
// -------------    PARALLAX ANIMATION    --------------
// -----------------------------------------------------

function dodo_parallax_animation(){

	"use strict";

	jQuery('.parallax').each(function(){
		var element = jQuery(this);
		var scene = element.get(0);
		var parallax = new Parallax(scene, { 
			relativeInput: true,
			onReady: function() { console.log('ready!');
			} });
		});
}

// -----------------------------------------------------
// -------------------    VANTA EFFECT    --------------
// -----------------------------------------------------

function iknow_tm_vanta_effects(){
	
	"use strict";
	if($('.iknow_tm_hero_extra .background .image').length){
		VANTA.WAVES({
			el: ".iknow_tm_hero_extra .background .image",
			mouseControls: true,
			touchControls: true,
			gyroControls: false,
			minHeight: 0,
			minWidth: 0,
			scale: 1.00,
			scaleMobile: 1.00,
			color: 0xb65634
		})
	}
}


// -----------------------------------------------------
// ---------------   TRIGGER MENU    -------------------
// -----------------------------------------------------

function iknow_tm_trigger_opener(){
	
	"use strict";

	var audio1			= jQuery('#audio1');
	var audio2			= jQuery('#audio2');
	var hamburger 		= jQuery('.iknow_tm_extra_demo .iknow_tm_topbar .trigger .hamburger');
	var menu			= jQuery('.iknow_tm_topbar .wrapper .menu');

	hamburger.on('click',function(){
		var element 	= jQuery(this);

		if(element.hasClass('is-active')){
			element.removeClass('is-active');
			audio1[0].play();
			menu.removeClass('opened');
		}else{
			element.addClass('is-active');
			audio2[0].play();
			menu.addClass('opened');
		}
		return false;
	});
}

// -----------------------------------------------------
// ---------------   TRIGGER MENU    -------------------
// -----------------------------------------------------

function iknow_tm_trigger_menu(){
	
	"use strict";

	var hamburger 		= jQuery('.iknow_tm_mobile_menu .trigger .hamburger');
	var mobileMenu		= jQuery('.iknow_tm_mobile_menu .dropdown');
	var mobileMenuList	= jQuery('.iknow_tm_mobile_menu .dropdown .dropdown_inner ul li a');

	hamburger.on('click',function(){
		var element 	= jQuery(this);

		if(element.hasClass('is-active')){
			element.removeClass('is-active');
			mobileMenu.slideUp();
		}else{
			element.addClass('is-active');
			mobileMenu.slideDown();
		}
		return false;
	});
	
	mobileMenuList.on('click',function(){
		jQuery('.iknow_tm_mobile_menu .trigger .hamburger').removeClass('is-active');
		mobileMenu.slideUp();
		return false;
	});
}

// -----------------------------------------------------
// ---------------   MENU WIDTH NEW   ------------------
// -----------------------------------------------------

function iknow_tm_menu_width_action(element,action){
	"use strict";
	var ww	= jQuery(window).width();
	
	
	// action active is mouseenter and was used in onepagenav for current item (on scroll)
	
	if(action === 'active'){
		var li			= element.closest('li');
		if(li.hasClass('current') || li.hasClass('entered')){return false;}
		li.closest('ul').children().removeClass('entered');
		li.addClass('entered');
		var elWidth		= li.outerWidth();
		var textWidth	= li.find('span').outerWidth();
		li.css({width: elWidth+textWidth+'px'});
	}else{
		var li			= element.closest('li');
		var allLi		= li.closest('ul').children();
		var fixedLi		= li.closest('ul').children('.fixed');
		var activeLi	= li.closest('ul').children('.current');
		allLi.removeClass('entered').css({width:'70px'});
		if(ww <= 1400){
			allLi.removeClass('entered').css({width:'50px'});
		}
		
		if(activeLi.length){
			var elWidth		= 70;
			if(ww <= 1400){
				elWidth = 50;
			}
			var textWidth	= activeLi.find('span').outerWidth();
			activeLi.removeClass('fixed').addClass('current').css({width: elWidth+textWidth+'px'});
		}
	}
	
}

function iknow_tm_menu_width_new(){
	
	"use strict";
	
	var ww	= jQuery(window).width();
	var btn	= jQuery('.iknow_tm_mainpart_new_2 .main_menu ul li a');
	
	btn.on('mouseenter',function(){
		iknow_tm_menu_width_action(jQuery(this),'active');
	}).on('mouseleave',function(){
		iknow_tm_menu_width_action(jQuery(this),'');
	});
	
	btn.on('click',function(){
		var element 	= jQuery(this);
		var li			= element.closest('li');
		if(li.hasClass('current') && !li.hasClass('entered')){
			return false;
		}
		li.addClass('current fixed');
		
		
		li.siblings().removeClass('current').css({width:'70px'});
		if(ww <= 1400){
			li.siblings().removeClass('current').css({width:'50px'});
		}
		
		if(!li.hasClass('entered')){
			var elWidth		= li.outerWidth();
			var textWidth	= li.find('span').outerWidth();
			li.css({width: elWidth+textWidth+'px'});
		}
		
		return false;
	});
	
	jQuery('.iknow_tm_mainpart_new_2 .main_menu ul li.current').each(function(){
		var element 	= jQuery(this);
		var elWidth		= element.outerWidth();
		var textWidth	= element.find('span').outerWidth();
		element.css({width: elWidth+textWidth+'px'});
	});
}


function hashtag(){
	"use strict";
	var ccc 			= $('.iknow_tm_topbar .wrapper .menu .ccc');
	var element 		= $('.iknow_tm_topbar .wrapper .menu .active a');
	$('.iknow_tm_topbar .wrapper .menu a').on('mouseenter',function(){
		var e 			= $(this);
		currentLink(ccc,e);
	});
	$('.iknow_tm_topbar .wrapper .menu').on('mouseleave',function(){
		element 		= $('.iknow_tm_topbar .wrapper .menu .active a');
		currentLink(ccc,element);
		element.parent().siblings().removeClass('mleave');
	});
	currentLink(ccc,element);
	
}

function currentLink(ccc,e){
	"use strict";
	if(!e.length){return false;}
	var left 		= e.offset().left;
	var width		= e.outerWidth();
	var menuleft 	= $('.iknow_tm_topbar .wrapper .menu').offset().left;
	e.parent().removeClass('mleave');
	e.parent().siblings().addClass('mleave');
	ccc.css({left: (left-menuleft) + 'px',width: width + 'px'});
	
}

function hashtag2(){
	"use strict";
	var ccc 			= $('.iknow_tm_sidebar_2 .ccc');
	var element 		= $('.iknow_tm_sidebar_2 .active a');
	$('.iknow_tm_sidebar_2 .menu a').on('mouseenter',function(){
		var e 			= $(this);
		currentLink2(ccc,e);
	});
	$('.iknow_tm_sidebar_2 .menu').on('mouseleave',function(){
		element 		= $('.iknow_tm_sidebar_2 .menu .active a');
		currentLink2(ccc,element);
		element.parent().siblings().removeClass('mleave');
	});
	currentLink2(ccc,element);
	
}

function currentLink2(ccc,e){
	"use strict";
	if(!e.length){return false;}
	var left 		= e.offset().top;
	var width		= e.outerWidth();
	var menuleft 	= $('.iknow_tm_sidebar_2 .menu').offset().top;
	e.parent().removeClass('mleave');
	e.parent().siblings().addClass('mleave');
	ccc.css({top: (left-menuleft) + 'px'});
	
}


function iknow_tm_scrollable(){
	
	"use strict";
	
	var WW				= jQuery(window).width();
	var H				= jQuery(window).height();
	var scrollable		= jQuery('.iknow_tm_sidebar_3 .menu.scrollable');
	var verMenu			= jQuery('.iknow_tm_sidebar_3 .menu');
	var topbar			= jQuery('.iknow_tm_extra_demo .iknow_tm_topbar').outerHeight();
	var image			= jQuery('.iknow_tm_sidebar_3 .image').outerHeight()+45;
	var footerHeight	= jQuery('.iknow_tm_extra_demo .iknow_tm_copyright').outerHeight();

	if(WW<=1400){
		verMenu.css({height:H-topbar-image-footerHeight});
	}
	
	verMenu.css({height:H-topbar-image-footerHeight-90});
	
	if(WW<=1400){
		scrollable.each(function(){
			var element		= jQuery(this);

			element.css({height:H-topbar-image-footerHeight}).niceScroll({
				touchbehavior:false,
				cursorwidth:0,
				autohidemode:true,
				cursorborder:"0px solid #eee"
			});
		});
	}
	
	scrollable.each(function(){
		var element		= jQuery(this);
		
		element.css({height:H-topbar-image-footerHeight-90}).niceScroll({
			touchbehavior:false,
			cursorwidth:0,
			autohidemode:true,
			cursorborder:"0px solid #eee"
		});
	});
}

// -------------------------------------------------
// ---------   MENU IMAGE ANIMATION  ---------------
// -------------------------------------------------

function iknow_tm_menu_image_animation(){
	
	"use strict";
	
	jQuery(window).on('scroll',function(){
		var menu	 		= jQuery('.iknow_tm_sidebar_3');
		var WinOffset		= jQuery(window).scrollTop();
		
		if(WinOffset >= 100){
			menu.addClass('animate');
		}else{
			menu.removeClass('animate');
		}
	});
}