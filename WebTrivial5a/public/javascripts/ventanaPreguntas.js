var questionNumber;

			function answerCorrect(questionNumber, answer) {
			   // la variable 'correct' almacena el valor a devolver 
			   var correct = false;
			   
			   // si la respuesta (answer) proporcionada es la misma que la respuesta correcta
			   // asignada a la pregunta (questionNumber), entonces el valor de 'correct'es true
			   if (answer == answers[questionNumber])
			      correct = true;
			   
			   // se devuelve el valor de 'correct' que será true o false 
			   return correct;
			}
			
			function getQuestion() {
			   questionNumber = Math.floor(Math.random() * (questions.length));
			   var questionHTML = "<P>" + questions[questionNumber][0] + "</P>";
			   var questionLength = questions[questionNumber].length;
			   var questionChoice;
			
			   for (questionChoice = 1;questionChoice < questionLength;questionChoice++) {
			      questionHTML = questionHTML + "<INPUT TYPE=radio NAME=radQuestionChoice"
			      if (questionChoice == 1) {
			         questionHTML = questionHTML + " CHECKED";
			      }
			      questionHTML = questionHTML + ">";
			      questionHTML = questionHTML + questions[questionNumber][questionChoice];
			      questionHTML = questionHTML + "<BR>";
			   }
			
			   document.QuestionForm.txtQNumber.value = questionNumber + 1;
			   return questionHTML;
			}

			function buttonCheckQ_onclick() {
			   var answer = 0;
			
			   while (document.QuestionForm.radQuestionChoice[answer].checked != true)   {
			      answer++;
			   }
			
			   answer = String.fromCharCode(65 + answer);
			
			   if (answerCorrect(questionNumber,answer) == true) {
			      alert("Respuesta correcta");
			   }
			   else {
			      alert("Respuesta incorrecta");
			   }
			
			   window.location.reload();
			}
			
