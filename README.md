# MandelbrotSetViewer
Aplikacja desktopowa stowrzona w ramach zaliczenia wykładu z przedmiotu Programowanie równoległe i rozproszone

## Technologie
* Java 11
* JavaFx 14
* MySQL
* Wielowątkowość

## Screenshots
![Example screenshot](./screenshot.png)

## Funkcjonalności
* Wizualizacja zbioru mandelbrota
* Kolorowanie zbioru
* Możliwość ustawienia parametrów
* Możliwość poruszania się po zbiorze

## Sterowanie
### Poruszanie się
W/A/S/D - poruszanie się po zbiorze odpowiednio góra/lewo/dół/prawo<br>
LPM - zbliżenie i wyśrodkowanie zbioru w miejscu kliknięcia<br>
PPM - oddalenie zbioru<br>

### Pola
Ilość wątków - ilość wątków używana do wykonywania obliczeń<br>
Ilość iteracji - określa szczegółowość zbioru<br>
Zoom - określa krotność przybliżenia obrazu<br>
Przesunięcie - określa o ile pixeli wykonany będzie ruch na zbiorze<br>

### Przyciski
Odśwież - generuje zbiór<br>
Restart - przywraca do ustawień początkowych<br>
Skupienie - przycisk pozwalający skupić program na nasłuchiwaniu klawiatury do poruszania się po zbiorze<br>
