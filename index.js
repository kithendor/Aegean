var activeplayer = 1;
var sum = 0;

function newgame() {

	document.getElementById("p1tp").innerHTML = 0;
	document.getElementById("p1cp").innerHTML = 0;
	document.getElementById("p2tp").innerHTML =0;
	document.getElementById("p2cp").innerHTML =0;

	document.getElementById("pl1").classList.add("actp");
	document.getElementById("pl2").classList.remove("actp");

	activeplayer = 1;
}

function rolldice() {
	var dice1 = Math.floor(Math.random()*(6)+1);
	var dice2 = Math.floor(Math.random()*(6)+1);
	document.getElementById("img1").src = "img/"+dice1+".jpg";
	document.getElementById("img2").src = "img/"+dice2+".jpg";

	if(dice1==1||dice2==1)
	{
		if(activeplayer==1)
		{
			document.getElementById("p1cp").innerHTML = 0;
			document.getElementById("p1tp").innerHTML = 0;
			document.getElementById("pl2").classList.add("actp");
			document.getElementById("pl1").classList.remove("actp");			
			activeplayer=2;
			sum = 0;
		}
		else{
			document.getElementById("p2cp").innerHTML = 0;
			document.getElementById("p2tp").innerHTML = 0;
			document.getElementById("pl1").classList.add("actp");
			document.getElementById("pl2").classList.remove("actp");
			activeplayer=1;
			sum = 0;
		}
	}
	else
	{
		sum += dice2 + dice1;
		if(activeplayer==1)
		{
			document.getElementById("p1cp").innerHTML = sum;
		}else
		{
			document.getElementById("p2cp").innerHTML = sum;
		}
	}
}

function hold()
{
	if(activeplayer==1)
	{
		document.getElementById("p1tp").innerHTML = Number(document.getElementById("p1tp").innerHTML)+ sum;
		document.getElementById("pl2").classList.add("actp");
		document.getElementById("pl1").classList.remove("actp");
		document.getElementById("p1cp").innerHTML = 0;
		activeplayer=2;
	}else
	{
		document.getElementById("p2tp").innerHTML = Number(document.getElementById("p2tp").innerHTML) + sum;
		document.getElementById("pl1").classList.add("actp");
		document.getElementById("pl2").classList.remove("actp");
		document.getElementById("p2cp").innerHTML = 0;
		activeplayer=1;
	}

	sum=0;
}