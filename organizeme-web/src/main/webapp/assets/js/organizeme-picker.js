$(document).ready(
	function() {
	    /* activer datetimepicker */
	    $('div.datetimepicker').datetimepicker({
		language : 'fr'
	    });
	    /* activer adresspicker */
	    $(function() {
		var addressPicker = new AddressPicker();
		$('#address').typeahead(null, {
		    displayKey : 'description',
		    source : addressPicker.ttAdapter()
		});
//		addressPicker.bindDefaultTypeaheadEvent($('#address'));
//		$(addressPicker).on('addresspicker:selected',
//			function(event, result) {
//			    displayResults(result, $('#response1'));
//			});
		$(addressPicker).on('addresspicker:predictions',
			function(event, result) {
			    if (result && result.length > 0)
				$('#address').removeClass("empty");
			    else
				$('#address').addClass("empty");
			});
	    });

	});