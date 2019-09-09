Hallo,
die beiliegende Lösung ist als eigenständig laufende Konsolenanwendung realisiert. 
Als IDE wurde Eclipse 2019-06 verwendet.
Die Anwendung bedient sich einer externen JSoup-Bibliothek, die entweder als .jar-Datei im Projekt oder als Maven-Dependency
über die pom.xml.-Datei hinzugefügt werden muss.
Die entsprechende Jar-Datei liegt dem Projekt bei.

Zur Funktionsweise:
Nach dem Start der Anwendung wird der Anwender gebeten eine gültige URL mit Protokoll einzugeben. 
Über die Jsoup-Bibliothek wird dannn das entsprechende Dokument angefordert, die Links innerhalb der <a>-tags werden herausgelesen und in eine 
Elementenliste gesichert.
Danach werden alle Elemente innerhalb der Liste mit Hilfe eines regulären Ausdrucks überprüft und beim Treffer in eine HashMap geschrieben.
Die HashMap beinhaltet die Domains, sowie ihre Anzahl.
Danach erfolgt die Ausgabe in der Konsole.

Viel Spaß beim Testen!
