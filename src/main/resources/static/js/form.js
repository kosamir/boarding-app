$(document).ready(function() {
	//refactor
	var candidateNewUrl = "/candidate/new";
	var collectResponseUrl = "/candidate/collect/response";
	var recognizeUrl = "/candidate/recognize";
	
	function modifyLink(link, id){
		var split = link.split('/')
		split[6]=id;
		return split.join('/')
	}
	// pozivanje mblink apija + spremanje vracenih podataka u candidate tablicu
	$( "#uploadForm" ).submit(function( event ) {
		 event.preventDefault();
		 var form = $('#uploadForm')[0];
		 var data = new FormData(form);
		 console.log(data + form);
		 var $parent =  $(this).parent().parent();
		 $.ajax({
		         url: recognizeUrl,
		         type: "POST",
		         enctype: 'multipart/form-data',
		         data:  data,
		         contentType: false,
		         cache: false,
		         processData:false,
		   beforeSend : function(){
			   $("#progresBar").attr('style','display:block');
			   $("#error").text("")
		   },
		   success: function(data){
			   // refactor!
			  delete data.id;
			  switch(data.gender){
			  case 'M/M':
				  data.gender='Male';
				  break;
			  case 'F/F':
				  data.gender='Female';
				  break;
			  default:
				  data.gender='Other';
			  }
			  data.date=data.date.split('.').reverse().join('-')
			  console.log(data);
			  
			   $.ajax({
		            type        : 'POST', 
		            url         : candidateNewUrl, 
		            data        : data, 
		            dataType    : 'json', 
		            encode          : true,
		            success: function(data){
		 			   $("#sucess").text("SAVED!!")
		 			   $("*").prop('disabled',true);
		 			   $("#submitGeneric").hide();
		 			   $("#file").hide();
		 			   $("#submit").hide();
		 			   $("#progresBar").hide();
		 			   var link = $("#next").attr('href');
		 			   var new_link = modifyLink(link, data.id);
		 			   $("#next").attr('href',new_link)
		 			   console.log($("#next").attr('href'));
		 		    },
		 		    error: function(event){ 
		 		    	 $("#progresBar").hide();
		 		    	 $("#error").text(event.responseText)
		 		    	 console.log(event.responseText);
		 		    }
		        })
		    },
		    error: function(event){ 
		    	 $("#progresBar").attr('style','display:none');
		    	 $("#error").text(event.responseText)
		    	 console.log(event.responseText);
		    }
		          
		  });
	});
		
	// submit generirane forme na server..
	$( "#genericForm" ).submit(function( event ) {
		  event.preventDefault();
		  var formData = $( this ).serialize();
		  console.log(formData );
	
		  var $parent = $(this).parent().parent();
		  var completedSteps = $parent.find("#completedSteps").text();
		  console.log('completedSteps:'+completedSteps);
		 // prvi korak u wizardu je spremanje u tablicu candidate
		  var apiUrl = completedSteps == 1?candidateNewUrl:collectResponseUrl;
		  console.log(apiUrl);

	        // process the form
	        $.ajax({
	            type        : 'POST', 
	            url         : apiUrl, 
	            data        : formData, 
	            dataType    : 'json', 
	            encode      : true,
	            success: function(data){
	 			   $("#sucess").text("SAVED!!")
	 			   $("*").prop('disabled',true);
	 			   $("#submitGeneric").hide();
	 			   var link = $("#next").attr('href');
	 			   var new_link =completedSteps==1? modifyLink(link, data.id):link
	 			   $("#next").attr('href',new_link)
	 			   console.log($("#next").attr('href'));
	 			   $parent.find('#file').hide();
	 			   $parent.find('#submit').hide();
	 		    },
	 		    error: function(event){ 
	 		    	 $("#progresBar").attr('style','display:none');
	 		    	 $("#error").text(event.responseText)
	 		    	 console.log(event.responseText);
	 		    }
	        })
		  
		});
    
});