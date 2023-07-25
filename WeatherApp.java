package QA_Engineer_Intern_Assignment_Round;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApp {

    private static final String API_BASE_URL = "https://api.weather.com";

    private static HttpURLConnection createConnection(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        return (HttpURLConnection) url.openConnection();
    }

    private static String getWeatherData(String date) throws IOException {
        String apiKey = "YOUR_API_KEY";
        String endpoint = API_BASE_URL + "/data/weather/v1/forecast?date=" + date + "&apiKey=" + apiKey;

        HttpURLConnection connection = createConnection(endpoint);
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return response.toString();
        } else {
            System.out.println("Failed to get weather data. Response code: " + responseCode);
            return null;
        }
    }

    private static void getTempByDate() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the date (DD-MM-YYYY): ");
        String date = reader.readLine();

        String weatherData = getWeatherData(date);
        
        System.out.println("Temperature on " + date + ": " + weatherData);
    }

    private static void getWindSpeedByDate() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the date (DD-MM-YYYY): ");
        String date = reader.readLine();

        String weatherData = getWeatherData(date);
                System.out.println("Wind Speed on " + date + ": " + weatherData);
    }

    private static void getPressureByDate() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the date (DD-MM-YYYY): ");
        String date = reader.readLine();

        String weatherData = getWeatherData(date);
                System.out.println("Pressure on " + date + ": " + weatherData);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("1. Get weather\n2. Get Wind Speed\n3. Get Pressure\n0. Exit");
            System.out.print("Enter your choice: ");
            String option = reader.readLine();

            switch (option) {
                case "1":
                    getTempByDate();
                    break;
                case "2":
                    getWindSpeedByDate();
                    break;
                case "3":
                    getPressureByDate();
                    break;
                case "0":
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
