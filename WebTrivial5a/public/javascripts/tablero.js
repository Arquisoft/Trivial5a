function rollDice(posactual){
	if(posactual==0) posactual = posactual+1;
    //var status = document.getElementById("status");
    var numdado = Math.floor(Math.random() * 6) + 1;
    var numcasillas = 30;
    //status.innerHTML = numdado;
    $("#mover").html(numdado);
    $("#dice").attr('onclick','').unbind('click');
    
    	dplus= (posactual+numdado)
    	if(dplus!=30)   	dplus = (posactual+numdado)%numcasillas;
    	dmin = posactual-numdado;
    	if(dmin==0) dmin=30;
    	else if(dmin<0) dmin=numcasillas+dmin;
	
	    $( "#"+dplus ).prop('disabled', false);
	    $( "#circle-"+dplus ).addClass("circle-enabled");
	    $( "#"+dmin ).prop('disabled', false);
	    $( "#circle-"+dmin ).addClass("circle-enabled");
	    $( "#dice" ).prop('disabled', true);
	}