$(document).ready(function() { 
  // $("#team").owlCarousel();

  var owl = $("#team");
  
  owl.owlCarousel({
      items : 8, //8 items above 1000px browser width
      itemsDesktop : [1000,5], //5 items between 1000px and 901px
      itemsDesktopSmall : [900,3], // betweem 900px and 601px
      itemsTablet: [600,2], //2 items between 600 and 0
      itemsMobile : false, // itemsMobile disabled - inherit from itemsTablet option
      dots: false,
  });
  owl.trigger('owl.play',2000);
});
 
