#include <WiFi.h>
#include <HTTPClient.h>
#include <Arduino_JSON.h>
#include <DHT.h>
#include <string.h>

const char* ssid = "P208";
const char* password = "23456789";

// DHT sensor configuration
const char* dhtServer = "http://192.168.28.109:8080/api/v1/dhts";
#define DHTPIN 5
DHT dht(DHTPIN, DHT11);

// Light sensor configuration
const char* lightServer = "http://192.168.28.109:8080/api/v1/lights";
const int LIGHTPIN = 19;

// Lamp control configuration
const char* lampServerControlMode = "http://192.168.28.109:8080/api/v1/control-modes/lamp";
const char* lampServerLight = "http://192.168.28.109:8080/api/v1/lights";
const char* lampServerSound = "http://192.168.28.109:8080/api/v1/sounds";
const int LAMPPIN = 21;
String lampControlReadings;
String lampLightReadings;
String lampSoundReadings;

// Fan control configuration
const char* fanServerControlMode = "http://192.168.28.109:8080/api/v1/control-modes/fan";
const char* fanServerDHT = "http://192.168.28.109:8080/api/v1/dhts";
const char* fanServerSound = "http://192.168.28.109:8080/api/v1/sounds";
const int RELAYPIN = 22;
String fanControlReadings;
String fanDHTReadings;
String fanSoundReadings;



void setup() {
  Serial.begin(115200);
  // DHT sensor setup
  dht.begin();
  // LIGHT control setup
  pinMode(LIGHTPIN, INPUT);
  // Lamp control setup
  pinMode(LAMPPIN, OUTPUT);
  // Fan control setup
  pinMode(RELAYPIN, OUTPUT);
  // wifi setup
  WiFi.begin(ssid, password);
  Serial.println("Connecting to WiFi");
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to WiFi network with IP Address: ");
  Serial.println(WiFi.localIP());
}

void loop() {
  // Fan control based on operation modes
  if(WiFi.status()== WL_CONNECTED){            
    fanControlReadings = httpGETRequest(fanServerControlMode);
    JSONVar control = JSON.parse(fanControlReadings);
    Serial.println(JSON.stringify(control["operationMode"]));
    Serial.println(JSON.stringify(control["manualMode"]));
    if(JSON.stringify(control["operationMode"]).equals("\"MANUAL\"")) {
        Serial.println(JSON.stringify(control["manualMode"]));
        if(JSON.stringify(control["manualMode"]).equals("\"OFF\"")) {
          Serial.println("HIGH");
          digitalWrite(RELAYPIN,HIGH);
        } else {
          Serial.println("LOW");
          digitalWrite(RELAYPIN,LOW);
        }
    } else if (JSON.stringify(control["operationMode"]).equals("\"SOUND\"")) {
      fanSoundReadings = httpGETRequest(fanServerSound);
      JSONVar sound = JSON.parse(fanSoundReadings);
      Serial.println(JSON.stringify(sound["soundMode"]));
      if(JSON.stringify(sound["soundMode"]).equals("\"OFF\"")) {
        Serial.println("HIGH");
        digitalWrite(RELAYPIN,HIGH);
      } else {
        Serial.println("LOW");
        digitalWrite(RELAYPIN,LOW);
      }
    } else {
      // DHT sensor readings and upload
      float h = dht.readHumidity();
      float t = dht.readTemperature();
      if (!isnan(h) && !isnan(t)) {
        Serial.print(F("Humidity: "));
        Serial.print(h);
        Serial.println(" %");
        Serial.print(F("Temperature: "));
        Serial.print(t);
        Serial.println(" oC");
        if (WiFi.status() == WL_CONNECTED) {
        HTTPClient http;
        http.begin(dhtServer);
        http.addHeader("Content-Type", "application/json");
        String json = "{\"temperature\":\"" + String(t) + "\",\"humidity\":\"" + String(h) + "\"}";
        http.POST(json);
        http.end();
        }else{
          Serial.println("WiFi Disconnected");
        }
      } else {
        Serial.println(F("Failed to read from DHT sensor!"));
      }

      delay(2000);
      fanDHTReadings = httpGETRequest(dhtServer);
      JSONVar DHT = JSON.parse(fanDHTReadings);
      Serial.println(JSON.stringify(DHT["temperature"]));
      if(JSON.stringify(DHT["temperature"]).toFloat() < 30) {
        digitalWrite(RELAYPIN,HIGH);
      } else {
        digitalWrite(RELAYPIN,LOW);
      }
    }
  }
  else {
    Serial.println("WiFi Disconnected");
  }

  // Lamp control based on operation modes
  if (WiFi.status() == WL_CONNECTED) {
    lampControlReadings = httpGETRequest(lampServerControlMode);
    JSONVar control = JSON.parse(lampControlReadings);
    Serial.println(JSON.stringify(control["operationMode"]));
    Serial.println(JSON.stringify(control["manualMode"]));
    if (JSON.stringify(control["operationMode"]).equals("\"MANUAL\"")) {
      if (JSON.stringify(control["manualMode"]).equals("\"OFF\"")) {
        Serial.println("HIGH");
        digitalWrite(LAMPPIN, HIGH);
      } else {
        Serial.println("LOW");
        digitalWrite(LAMPPIN, LOW);
      }
    } else if (JSON.stringify(control["operationMode"]).equals("\"SOUND\"")) {
      // Light control based on digital readings
      lampSoundReadings = httpGETRequest(lampServerSound);
      JSONVar sound = JSON.parse(lampSoundReadings);
      Serial.println(JSON.stringify(sound["soundMode"]));
      if(JSON.stringify(sound["soundMode"]).equals("\"OFF\"")) {
          Serial.println("HIGH");
          digitalWrite(LAMPPIN,HIGH);
      } else {
          Serial.println("LOW");
          digitalWrite(LAMPPIN,LOW);
        }
    } else {
      // Light sensor readings and upload
      int value = digitalRead(LIGHTPIN);
      Serial.print("Light value: ");
      Serial.println(value);
      if (WiFi.status() == WL_CONNECTED) {
        HTTPClient http;
        http.begin(lightServer);
        http.addHeader("Content-Type", "application/json");
        http.POST("{\"digitalRead\" :" + String(value) + "}");
        http.end();
      } else {
        Serial.println("WiFi Disconnected");
      }
      delay(2000);
      // Light control based on digital readings
      lampLightReadings = httpGETRequest(lampServerLight);
      JSONVar light = JSON.parse(lampLightReadings);
      Serial.println(JSON.stringify(light["digitalRead"]));
      if (JSON.stringify(light["digitalRead"]).equals("1")) {
        digitalWrite(LAMPPIN, HIGH);
      } else {
        digitalWrite(LAMPPIN, LOW);
      }
    }
  } else {
    Serial.println("WiFi Disconnected");
  }
}

String httpGETRequest(const char* serverName) {
  WiFiClient client;
  HTTPClient http;
  http.begin(client, serverName);
  int httpResponseCode = http.GET();
  String payload = "{}";

  if (httpResponseCode > 0) {
    Serial.print("HTTP Response code: ");
    Serial.println(httpResponseCode);
    payload = http.getString();
  } else {
    Serial.print("Error code: ");
    Serial.println(httpResponseCode);
  }
  http.end();
  return payload;
}