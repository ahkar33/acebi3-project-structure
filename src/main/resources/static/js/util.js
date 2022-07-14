var voyages = [];

$('.modal').on('shown.bs.modal', function() {
	$("body").css({
		"padding-right" : 0
	});
})

$('.modal').on('hidden.bs.modal', function() {
	$("body").css({
		"padding-right" : 0
	});
})

function filterVessel() {
	$
			.ajax({
				type : 'POST',
				url : "/common/findAllVoyage",
				success : function(data) {
					voyages = data;
					var selectedVessel = null;
					if ($('#selectedVessel').length) {
						selectedVessel = $('#selectedVessel').val();
					}

					if (selectedVessel !== null && selectedVessel !== '') {
						$('.frmSearch #vesselId').val(selectedVessel).trigger(
								'change');
						if ($("#btnQuery").length
								&& $('#selectedVoyage').val() !== null
								&& $('#selectedVoyage').val() !== ""
								&& $('#selectedTerminal').val() !== null
								&& $('#selectedTerminal').val() !== "") {
							$("#btnQuery").trigger('click');
						}

					} else {
						$('.frmSearch #vesselId').trigger('change');
					}

				},
				error : function(xhr, status, error) {
					$('.messageForm #displayMessage').text(
							'Exeption:' + xhr.responseJSON.message);
					$('.messageForm #messageModal').modal();
				}
			});
}

$('.frmSearch #vesselId')
		.on(
				'change',
				function(e) {
					if ($('.frmSearch #voyageName').length) {
						$('.frmSearch #voyageName').empty();
						var vesselId = this.value;
						var selectedVoyageName = null;
						var filterVoyages = [];
						var optionString = [];

						var selectedVessel = $('#selectedVessel').val();

						if ($('#selectedVoyage').length
								&& selectedVessel === vesselId) {
							selectedVoyageName = $('#selectedVoyage').val();
						}

						filterVoyages = _.unionBy(_.filter(voyages, [
								'vesselId', parseInt(vesselId) ]), 'name');

						$.each(filterVoyages, function(ind) {
							var string = '<option value="'
									+ filterVoyages[ind].name.trim().toString()
									+ '">'
									+ filterVoyages[ind].name.trim().toString()
									+ '</option>'
							optionString.push(string);
						});
						$('.frmSearch #voyageName').html(optionString.join(''));

						if (selectedVoyageName !== null
								&& selectedVoyageName !== '') {
							$('.frmSearch #voyageName').val(selectedVoyageName)
									.trigger('change');
						} else {
							$('.frmSearch #voyageName').trigger('change');
						}
					}

				});

$('.frmSearch #voyageName').on(
		'change',
		function(e) {
			if ($('.frmSearch #terminalID').length) {
				$('.frmSearch #terminalID').empty();
				var selectedVessel = $('.frmSearch #vesselId').val();
				var voyageName = this.value;
				var selectedTerminalId = null;
				var filterTerminals = [];
				var optionString = [];

				var selectedVoyageName = $('#selectedVoyage').val();

				if ($('#selectedTerminal').length
						&& voyageName === selectedVoyageName) {
					selectedTerminalId = $('#selectedTerminal').val();
				}

				filterTerminals = _.unionBy(_.filter(voyages, {
					'name' : voyageName,
					'vesselId' : parseInt(selectedVessel)
				}), 'facility.facilityName');

				optionString.push('<option value="0">---</option>');

				$.each(filterTerminals, function(ind) {
					var string = '<option value="'
							+ filterTerminals[ind].facility.id
							+ '">'
							+ filterTerminals[ind].facility.facilityName.trim()
									.toString() + '</option>'
					optionString.push(string);
				});

				$('.frmSearch #terminalID').html(optionString.join(''));
				if (selectedTerminalId !== null && selectedTerminalId !== ''
						&& selectedTerminalId !== "0") {
					$('.frmSearch #terminalID').val(selectedTerminalId)
							.trigger('change');
				}
			} else if ($('.frmSearch #terminalIDAll').length) {
				$('.frmSearch #terminalIDAll').empty();
				var selectedVessel = $('.frmSearch #vesselId').val();
				var voyageName = this.value;
				var selectedTerminalId = null;
				var filterTerminals = [];
				var optionString = [];

				var selectedVoyageName = $('#selectedVoyage').val();

				if ($('#selectedTerminal').length
						&& voyageName === selectedVoyageName) {
					selectedTerminalId = $('#selectedTerminal').val();
				}

				if (voyageName === null || voyageName === "") {
					filterTerminals = _.unionBy(voyages,
							'facility.facilityName');
				} else {
					filterTerminals = _.unionBy(_.filter(voyages, {
						'name' : voyageName,
						'vesselId' : parseInt(selectedVessel)
					}), 'facility.facilityName');
				}

				optionString.push('<option value="0">---</option>');

				$.each(filterTerminals, function(ind) {
					var string = '<option value="'
							+ filterTerminals[ind].facility.id
							+ '">'
							+ filterTerminals[ind].facility.facilityName.trim()
									.toString() + '</option>'
					optionString.push(string);
				});

				$('.frmSearch #terminalIDAll').html(optionString.join(''));
				if (selectedTerminalId !== null && selectedTerminalId !== ''
						&& selectedTerminalId !== "0") {
					$('.frmSearch #terminalIDAll').val(selectedTerminalId)
							.trigger('change');
				}
			}

		});
