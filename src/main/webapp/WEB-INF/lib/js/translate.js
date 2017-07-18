/**
 *	for translate index javascript 
 */
var xhr = null;
var eng = "en";
var cht = "zh-TW";

// switch langurage
$("#switchLang").on('click', function(){
	var input = $("#input");
	
	var slv = input.attr("attr-sl");
	var tlv = input.attr("attr-tl");
	if(slv == eng){
		input.attr("attr-sl", cht);
		input.attr("attr-tl", eng);
		input.attr("placeholder", "Please type Chinese");
	}else{
		input.attr("attr-sl", eng);
		input.attr("attr-tl", cht);
		input.attr("placeholder", "Please type English");
	}	
})

// empty result 
$("#resultContent").on('click', function(){
	$("#input").val('');
	$(this).addClass('hidden').empty();
})

$("#input").on("keyup", function(){
	var data = $(this).val();
	var slv = $(this).attr("attr-sl");
	var tlv = $(this).attr("attr-tl");
	if(data.length !=0 && data.length > 1){
		goAjax(data, slv, tlv);		
	}else{
		$("#resultContent").addClass('hidden');
	}
})

function goAjax(data, slv, tlv){
	if(xhr!=null){
		xhr.abort();
		xhr = null;
	}
	
	xhr = $.ajax({
		type : "GET",
		url : "translate?t=" + data + "&sl=" + slv + "&tl=" + tlv,
		contentType : "application/json",
		dataType : "json",
		success : function(res){
			$("#resultContent").text(res.body.result);
			$("#resultContent").attr("data-clipboard-text", res.body.result);
		},
		complete : function(){
			$("#resultContent").removeClass('hidden');
		}
	})
	/* post method
	var jsonObject = JSON.stringify({
		target : data,
		sl : slv,
		tl : tlv
	});
	
	xhr = $.ajax({
		type : "POST",
		url : "translate",
		contentType : "application/json",
		data : jsonObject,
		dataType : "json",
		success : function(res){
			$("#resultContent").text(res.body.result);
			$("#resultContent").attr("data-clipboard-text", res.body.result);
		},
		complete : function(){
			$("#resultContent").removeClass('hidden');
		}
	})	
	*/
}