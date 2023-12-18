#include <WiFi.h>
#include <HTTPClient.h>
#include <string.h>

const char* ssid = "P208";
const char* password = "23456789";
const char* serverName = "http://192.168.28.109:8080/api/v1/sounds";
const int SOUNDPIN  = 23;

int clap = 0;
long detection_range_start = 0;
long detection_range = 0;
String soundMode = "OFF";
void setup() {
  Serial.begin(115200);
  pinMode(SOUNDPIN, INPUT);
  //setup wifi
  WiFi.begin(ssid,password);
  Serial.println("conecting");
  while(WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to WiFi network with IP Address: ");
  Serial.println(WiFi.localIP());
}
 
void loop() {
  // Wait a few seconds between measurements.
  int status_sensor = digitalRead(SOUNDPIN);
  if (status_sensor == 0)
  {
    if (clap == 0)
    {
      detection_range_start = detection_range = millis();
      clap++;
    }
    else if (clap > 0 && millis() -detection_range >= 50)
    {
      detection_range = millis();
      clap++;
    }
  }
  if (millis() - detection_range_start >= 1000)
  {
    if (clap == 3)
    {
      if (soundMode == "OFF")
        {
          soundMode = "ON";
          POSTAPI(soundMode);
        }
        else if (soundMode == "ON")
        {
          soundMode = "OFF";
          POSTAPI(soundMode);
        }
    }
    clap = 0;
  }
}
void POSTAPI(String soundMode) {
  if(WiFi.status() == WL_CONNECTED) {
    WiFiClient client;
    HTTPClient http;
    http.begin(client, serverName);
    http.addHeader("Content-Type", "application/json");
    http.POST("{\"soundMode\" :\"" +  String(soundMode) + "\"}");
    Serial.print("SOUND: ");
    Serial.println(soundMode);
      // Free resources
      http.end();
  } else {
    Serial.println("WiFi Disconnected");
  }
}
