package org.example;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        WeatherData weatherData = new WeatherData();
        CurrentWeatherDisplay currentWeatherDisplay1 = new CurrentWeatherDisplay();
        weatherData.registerObserver(currentWeatherDisplay1);

        weatherData.measurementChanged();
        System.out.println(weatherData);
        currentWeatherDisplay1.display();
    }
}


/*
public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        String apiKey = "d742e513fc70a7ac4d3ca66318b34bd4";
        String city = "Wroclaw,pl";

        String URL = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

        JsonObject main = jsonResponse.getAsJsonObject("main");
        double temperature = main.get("temp").getAsDouble();
        int pressure = main.get("pressure").getAsInt();
        int humidity = main.get("humidity").getAsInt();

        System.out.println("Temperature: " + temperature + " °C");
        System.out.println("Pressure: " + pressure + " hPa");
        System.out.println("Humidity: " + humidity + " %");
    }
}
*/
