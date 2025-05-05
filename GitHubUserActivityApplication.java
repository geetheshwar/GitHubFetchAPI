import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class GitHubUserActivityApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("GitHub user activity application started");
        System.out.print("please enter user name : ");
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        String userName = inputReader.readLine();
        System.out.println("calling Github FetchAPI to get activity for user : " + userName);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://api.github.com/users/" + userName + "/events"))
                .GET().setHeader("accept", "application/json").build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response fetched : " + response.statusCode());
        if(response.statusCode() == 404){
            System.out.println("username : " + userName + " not found.");
        }
        else{
        JsonElement jsonObj =  JsonParser.parseString(response.body());
        //uncomment below line to get required objects from the response.
        //System.out.println("response : " + jsonObj.getAsJsonArray().get(0).getAsJsonObject().get("actor"));
        }
    }
}
