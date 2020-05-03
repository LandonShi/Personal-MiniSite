$(document).ready(function(){
	$('#select_btn li:first').css('border','none');
	if ($('#zSlider').length) {
		zSlider();
		$('#h_sns').find('img').click(function(){
			$(this).fadeTo(200,0.5);
		}, function(){
			$(this).fadeTo(100,1);
		});
	}
	function zSlider(ID, delay){
		var ID=ID?ID:'#zSlider';
		var delay=delay?delay:5000;
		var currentEQ=0, picnum=$('#picshow_img li').size(), autoScrollFUN;
		$('#select_btn li').eq(currentEQ).addClass('current');
		$('#picshow_img li').eq(currentEQ).show();
		$('#picshow_tx li').eq(currentEQ).show();
		autoScrollFUN=setTimeout(autoScroll, delay);
		function autoScroll(){
			clearTimeout(autoScrollFUN);
			currentEQ++;
			if (currentEQ>picnum-1) currentEQ=0;
			$('#select_btn li').removeClass('current');
			$('#picshow_img li').hide();
			$('#picshow_tx li').hide().eq(currentEQ).slideDown(400);
			$('#select_btn li').eq(currentEQ).addClass('current');
			$('#picshow_img li').eq(currentEQ).show();
			autoScrollFUN = setTimeout(autoScroll, delay);
		}
		$('#picshow').click(function(){
			clearTimeout(autoScrollFUN);
		}, function(){
			autoScrollFUN = setTimeout(autoScroll, delay);
		});
		$('#select_btn li').click(function(){
			var picEQ=$('#select_btn li').index($(this));
			if (picEQ==currentEQ) return false;
			currentEQ = picEQ;
			$('#select_btn li').removeClass('current');
			$('#picshow_img li').hide();
			$('#picshow_tx li').hide().eq(currentEQ).slideDown(100);
			$('#select_btn li').eq(currentEQ).addClass('current');
			$('#picshow_img li').eq(currentEQ).show();
			return false;
		});
	};
})
