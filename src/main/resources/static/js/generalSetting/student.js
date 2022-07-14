

$("#studentForm").validate({
	rules : {
		name : "required"
	},

	messages : {
		name : "Please fill the student Name"
	}
});

$('table#student')
		.DataTable(
				{
					responsive : true,
					ajax : {
						'contentType' : 'application/json',
						'url' : '/generalSetting/student/dataTable',
						'type' : 'POST',
						'data' : function(d) {
							return JSON.stringify(d);
						}
					},
					lengthMenu : [ [ 10, 25, 50, 100, -1 ],
							[ 10, 25, 50, 100, "All" ] ],
					serverSide : true,
					order : [ 0, 'desc' ],
					columns : [
							{
								data : 'id'
							},
							{
								data : 'name'
							},
							{
								data : 'id',
								render : function(data, type, row) {
									return '<button class="btn btn-edit-datatable" id="btnEditStudent" name="btnEdit" onclick="showCreateForm('
											+ data
											+ ')"><i class="fas fa-edit"></i></button>';
								},
								orderable : false,
							},
							{
								data : 'id',
								render : function(data, type, row) {
									return '<button class="btn btn-edit-datatable eBtn" id="btnDeleteStudent" name="btnDelete" onclick="deleteConfirm('
											+ data
											+ ')"><i class="fas fa-trash-alt"></i></button>';
								},
								orderable : false,
							} ],
					columnDefs : [ {
						"targets" : [ 0 ],
						"visible" : false,
						"searchable" : false
					} ]
				});
function showCreateForm(id) {
	if (id != null) {
		$.ajax({
			type : 'POST',
			url : "/generalSetting/student/findById",
			data : {
				id : id
			},
			success : function(data) {
				$('.createForm #name').val(data.name);
				$('.createForm #id').val(data.id);
				$('.createForm #updatedDateTime').val(data.updatedDateTime);
				$('.createForm #studentModal').modal();
			},
			error : function(xhr, status, error) {
				$('.messageForm #displayMessage').text(
						'Exeption:' + xhr.responseJSON.message);
				$('.messageForm #messageModal').modal();
			}
		});
	} else {
		$('.createForm #name').val(null);
		$('.createForm #id').val(null);
		$('.createForm #updatedDateTime').val(null);
		$('.createForm #studentModal').modal();
	}
}

function deleteConfirm(id) {
	$('.deleteConfirmForm #id').val(id);
	$('.deleteConfirmForm #deleteConfirmModal').modal();
}

function deleteStudent() {
	var id = $('.deleteConfirmForm #id').val();
	$.ajax({
		type : 'POST',
		url : "/generalSetting/student/delete",
		data : {
			id : id
		},
		success : function(data) {
			$('.deleteConfirmForm #deleteConfirmModal').modal('toggle');
			$('.messageForm #messageModal').modal();
			$('.messageForm #displayMessage').text(data);
		},
		error : function(xhr, status, error) {
			$('.messageForm #displayMessage').text(
					'Exeption:' + xhr.responseJSON.message);
			$('.messageForm #messageModal').modal();
		}
	});
}

function save() {

	if ($("#studentForm").valid() !== false) {
		if ($('.createForm #updatedDateTime').val() != "") {
			var student = {
				id : $('.createForm #id').val(),
				name : $('.createForm #name').val(),
				updatedDateTime : new Date(DateFormat.format.date($(
						'.createForm #updatedDateTime').val(),
						"yyyy-MM-dd HH:mm:ss.SSS"))
			};
		} else {
			var student = {
				id : $('.createForm #id').val(),
				name : $('.createForm #name').val(),
			};
		}
		$.ajax({
			type : 'POST',
			url : "/generalSetting/student/save",
			data : student,
			success : function(data) {
				if (data === "There is no change!") {
					$('.successForm #messageText').text(data);
					$('.successForm #successModal').modal();
				} else {
					$('.successForm #messageText').text(data);
					$('.successForm #successModal').modal();
					$('#successModal').on(
							'hidden.bs.modal',
							function() {
								$('#studentModal').find("input,textarea,select")
										.val('').end();
							})
				}

			},
			error : function(xhr, status, error) {
				$('.messageForm #displayMessage').text(
						'Exeption:' + xhr.responseJSON.message);
				$('.messageForm #messageModal').modal();
			}
		});
	}
}

$('#messageModal').on('hidden.bs.modal', function() {
	location.reload();
})
$('#studentModal').on('hidden.bs.modal', function() {
	location.reload();
})

$('studentModal').on('shown.bs.modal', function() {
	$('#imoNo').focus();
})
