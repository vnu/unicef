function walkFinnAndJake() {
	console.log('start walk');
	var icon = $('.icon-finn-jake');
	icon.addClass('walkUp');
	icon.on('animationend webkitAnimationEnd', function() {
		console.log('end up');
		icon.removeClass('walkUp');
		icon.addClass('walkDown');
		console.log('start down');
	});
}

$(document).ready(function() {
	walkFinnAndJake();
});