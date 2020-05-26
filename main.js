$(document).ready(function(){
    
  $("#submit").click(function(){
      return getForecast();
  });
  
  $("#buttonSave").click(function(){
    return citySave();
});

});

function citySave() {
  let cityName = $("#tcountry").val();
  let citySaveDiv = document.createElement("div");
  let textNode = document.createTextNode(cityName);
  citySaveDiv.appendChild(textNode);
  document.getElementById("listCity").appendChild(citySaveDiv);
}
  
function getForecast(){
  // Country
  var cityName = document.querySelector('#country');
  
  // Credentials
  var key = "f8ce8f293a8a46918997ab5a76760e11";
  var city = $("#tcountry").val();
  var url = "https://api.openweathermap.org/data/2.5/forecast"; 

  // Get current date
	var check = false;
  if(city != ''){
      
      $.ajax({
          url: url,
          type: "GET",
          dataType: "jsonp",
          data: {
            q: city,
            appid: key,
            units: "metric",
            cnt: "7"
          },
          success: function(data){
              check = true;
        
              const forecast = document.getElementsByClassName('days')[0];

              for(var i = 0; i < data.list.length; i++){
                var dayImg = "<img src='http://openweathermap.org/img/w/" + data.list[i].weather[0].icon + ".png'";
                let dayDesc = data.list[i].weather[0].description;
                let dayTemp = Math.trunc(data.list[i].main.temp) + "&deg;C";
                let date = new Date();
                let days = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
                let name = days[date.getDay()];
                let dayBlock = document.createElement("div");
                // console.log(name);
                dayBlock.className = 'forecast__item';
                dayBlock.innerHTML = `<div class="week-day">${name}</div>
                <div class="forecast-item_img">${dayImg}</div><div class="forecast-item_temp">${dayTemp}</div><div class="forecast-item_desc">${dayDesc}</div>`;
                forecast.appendChild(dayBlock);
                }
                
                /*
                var request = new XMLHttpRequest();
                request.open('GET',url,true);
                request.onload = function(){
                var obj = JSON.parse(this.response);
                if (request.status == 404) {
                    console.log("1");
                  }
                  else{
                    console.log("2");
                  }
                 }
                request.send();
                */
            /*
            for (let z = 0; z < 7; z++) {
              let date = new Date();
              let daysweek = ['SAT', 'FRI', 'THU', 'WED', 'TUE', 'MON', 'SUN'];
              let name = daysweek[date.getDay() + z];
              let weekBlock = document.createElement("div");
              weekBlock.className = 'weekdays';
              weekBlock.innerHTML = `<div class="week-day">${name}</div>`;
              forecast.appendChild(weekBlock);
              console.log(name + " - " + z);
            }
            */
              // Display city and country
			  
              var cityNameValue= data.city.name;
              cityName.innerHTML = cityNameValue + ", " + data.city.country;

              // Temperature
              let currentTemperature = document.querySelector('#temperature-value');
              let currentTemperatureValue = data.list[0].main.temp;
              currentTemperature.innerHTML = currentTemperatureValue + "&deg;C";

              // Humidity 
              let currentHumidity = document.querySelector('#humidity-value');
              let currentHumidityValue = data.list[0].main.humidity;
              currentHumidity.innerHTML = currentHumidityValue + "%";

              // Wind
              let currentWind = document.querySelector('#wind-value');
              let currentWindValue = data.list[0].wind.speed;
              currentWind.innerHTML = currentWindValue + " miles/h";
              
              
          } ,complete: function () {
			   if(check==false){
					alert("error");
				}	
         }   
			//if(ta==false){
			//	alert("ero");
			//}		  
          
      });
      
	  		
  //}else{
    //  $("#error").html("<div class='alert alert-danger' id='errorCity'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>City field cannot be empty</div>");
  }
  
 
}
