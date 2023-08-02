package servise;

public class GlobalStateApp {
    private static GlobalStateApp INSTANCE;
    private String city;
    private  final String API_KEY = "Ezx3FC9oeIIOB1V0WeZEBAQcTtpWK3le";

    private GlobalStateApp(){}

    public static GlobalStateApp getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GlobalStateApp();
        }
        return INSTANCE;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getAPI_KEY(){
        return this.API_KEY;
    }
}
