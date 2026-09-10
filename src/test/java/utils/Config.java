package utils;

public class Config {

    public static final String BASE_URL =
            System.getenv("BASE_URL") != null
                    ? System.getenv("BASE_URL")
                    : "https://api.anarabbas.com";
}