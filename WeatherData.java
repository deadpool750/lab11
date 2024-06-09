package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {

    private float temperature;
    private float humidity;
    private float pressure;

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        if (!observers.contains(o)) observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        for (Observer o : observers) {
            o.update(temperature, humidity, pressure);
        }
    }

    public void measurementChanged() throws URISyntaxException, IOException, InterruptedException {
        String apiKey = "";
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
        this.temperature = main.get("temp").getAsFloat(); // Assign to instance variable
        this.pressure = main.get("pressure").getAsFloat(); // Assign to instance variable
        this.humidity = main.get("humidity").getAsFloat(); // Assign to instance variable
        notifyObserver();
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", observers=" + observers +
                '}';
    }
}
