Mousetrap.bind([ 'g' ], function(e) {
	$('#generalSetting').dropdown('toggle');
});

Mousetrap.bind([ 'c' ], function(e) {
	$('#customer').dropdown('toggle');
});

Mousetrap.bind([ 'm' ], function(e) {
	$('#import_manifest').dropdown('toggle');
});

Mousetrap.bind([ 'a' ], function(e) {
	$('#arrivalnotice').dropdown('toggle');
});

Mousetrap.bind([ 'd' ], function(e) {
	$('#detention').dropdown('toggle');
});

Mousetrap.bind([ 't' ], function(e) {
	$('#containerActivityMovement').dropdown('toggle');
});

Mousetrap.bind([ 'r' ], function(e) {
	$('#userManagement').dropdown('toggle');
});

Mousetrap.bind([ 'l' ], function(e) {
	$('#importLocalCharges').dropdown('toggle');
});

Mousetrap.bind([ 'alt+q' ], function(e) {
	location.href = "/importManifest/generalEnquiry/";
});
