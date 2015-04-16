xmlhttp.open("GET","localhost:9000/partida/1/1",true);
xmlhttp.send();












			
			var questions = new Array(); // el array questions almacena las preguntas 
			var answers = new Array(); // el array answers almacena las respuestas  
	
			// Se define la pregunta 1 
			questions[0] = new Array();
			questions[0][0] = "Los beatles fueron:";
			questions[0][1] = "Un grupo de rock de los 70 procedente de Liverpool";
			questions[0][2] = "Unos insectos ya extinguidos denominados escarabajos";
			questions[0][3] = "Un modelo de coche popular en los 70 de la marca Wolswagen";
			// Asignar la respuesta válida para la pregunta 1 
			answers[0] = "A";

			// Se define la pregunta 2 
			questions[1] = new Array();
			questions[1][0] = "La comida favorita de Homer Simpson es";
			questions[1][1] = "Ensaladas";
			questions[1][2] = "Donuts";
			questions[1][3] = "Pan y Agua";
			questions[1][4] = "Manzanas";
			// Asignar la respuesta válida para la pregunta 2 
			answers[1] = "B";

			// Se define la pregunta 3 
			questions[2] = new Array();
			questions[2][0] = "¿Qué instrumento musical toca Lisa Simpson?";
			questions[2][1] = "Clarinete";
			questions[2][2] = "Oboe";
			questions[2][3] = "Saxofón";
			questions[2][4] = "Xilofono"; 
			
			// Asignar la respuesta válida para la pregunta 3 
			answers[2] = "C";