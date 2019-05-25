# Onlab-Android_VRgame_using_Unity_and_Wearnotch
This is the pilot project of a Fitness VR game which should use the wearnotch motion tracking system to track our movements and motions. For the 3D side of the game I will use Unity. Tha game will run on Android phones first.

Tehát itt szeretném részletesen lépésről lépésre bemutatni azt, hogy jelenleg hol is tartok és hogyan jutottam ide az alkalmazással.

## Használt fejlesztőkörnyezet, keretrendszer verziók és eszközök:

* Unity 2018.2.10f
* Android Studio 3.4
* Android SDK Tools: 26.1.1
* build.gradle(Module: app) - ban:
  * compileSdkVersion 28
  * buildToolsVersion ‘28.0.3’
  * targetSdkVersion 28
  * minSdkVersion 19
* dependencies -> implementation ‘com.notch.sdk:sdk-android:1.1.354’
* Huawei P10 Androidos készüléken futtatva
* Wearnotch

## Szükséges lépések:


1. Vegyük azt kiindulási alapnak, hogy megvan a működő és futtatható Demo app (3. pontban leírtak alapján)
2. Hozzunk létre egy Unity projektet.
3. Nevezzük át az alap Scene-t SampleScene-re (ha más neve van)
4. Adjunk hozzá egy Canvas-t (Create -> UI -> Canvas)
5. A Canvas-ra pedig vegyünk fel egy Text-et (UI -> Text)
6. A Text mező default értékének “New Text”-et adtam, a Font Size-t pedig 24-re állítóttam. valamint elhelyeztem úgy, hogy a Game nézetben körülbelül a kijelző közepére kerüljön.
7. A vászonra felvettem egy TextMesh-t is, aminek Default értékként “Hello World”-ot állítottam be, de lényegében ezt nem használom a későbbiekben.
8. Amit én még itt csináltam a letisztultság kedvéért a Camera Background színét átállítóttam erre a kékeszöldre, hogy a megjelenő szöveget könnyen lehessen olvasni.
9. Létrehoztam egy Empty Game Object-et PluginScript néven, amihez hozzáadtam egy Script Component-et (Add Component -> New Script) Plugin Wrapper néven. Ebbe írjuk az üzenetváltáshoz szükséges C# kódot.
10. Először a kódban csak felvettem a Text típusú mezőt myText néven és implementáltam egy DefaultText() illetve egy SetText(string) függvényt. Amik közül az előbbi egy Default értéket állít be a myText mezőre, az utóbbi pedig a paraméterként átadott szót jeleníti meg a vásznon.
11. Itt már végrehajtottam a Unity Projekt Importálását a Demo App-ba, hogy ki tudjam próbálni az eddigieket. Ezt a folyamatot pedig a KÖVETKEZŐ (7. fejezet) fejezetben leírtak alapján tudjuk megtenni!
12. Majd tesztelésképp annyit csináltam első körben, hogy a Demo App MainFragment osztályában található getSteadyData() függvényhez tartozó OnClick eseményre elindítottam a UnityPlayerActivity és üzenetként átadtam egy tetszőleges stringet (‘notchposition’). Ezt a UnityPlayerActivity onStart() állapotában felfogtam és a UnitySendMessage függvény segítségével meghívtam az imént Unity-ben implementált PluginScript objektumban található PluginWrapper C# osztály SetText(string) függvényét.

      * Itt megemlíteném, hogy ahhoz, hogy a tényleges szenzoroktól kapott adatokat tudjam továbbítani az adott Text mezőbe ezt a funkciót áthelyeztem a Start Real-Time hatására elinduló VisualiserActivity-be. Ahol a teszteléshez csak rögtönzötten a jobb felső sarokban lévő felül nézetre átváltó gomb (button_top_view) eseménykezelőjét , az onTopViewClicked() függvényt írtam át, hogy ez hívja a UnityPlayerActivity-t és indítsa el a Unity-ből exportált projektet.
13. Miután ezzel a módszerrel sikerrel jártam átültettem a konzulensem által mutatott GitHub projekt (https://github.com/inbgche/Unity-Android-Communication) RocketLuncher Unity projekt Assets -> Scripts -> AndroidManager osztályában található kódot.
14. Majd ennek megfelelően az Android Studio-ba importalt UnityPlayerActivity osztályban implementáltam a javaTestFunc(string) függvényt amit az Activity elindításakor a C#-ban található Start() függvény hív meg.
15. Annyit módosítottam a kódon, hogy én a javaTestFunc(string) függvényből nem a paraméterként kapott stringet íratom ki, hanem ez lehetne akár paraméter nélküli függvény is, mivel én itt a híváskor átadott “message” névvel jelölt kulcs értékét adom át és azzal hívom a C# kód SetJavaLog(string) függvényét, ami a myText mező értékének átadja ezt a karakterláncot.
16. Itt végül még, hogy meggyőződjek arról, hogy a C# kódban található frissítés működik (és a probléma valóban az, hogy csak az utolsó NotchPosition sztringet veszi át) a Start()-ban hívott InvokeRepeating() függvény segítségével próbáltam ki, ami aminek megadtam, hogy az indulástól számított 0,1. másodperctől 0,1 másodpercenként hívja meg a repeatCall() függvényt, ami ugyanazt csinálja mint korábban a Start() maga tette, azaz elsüti a Java-ban található JavaTestFunc() függvényt.
17. Ezek után a UnityPlayerActivity osztály javaTestFunc() fügvényéhez hozzávettem egy sima integer változót amit minden függvényhíváskor növelek egyel, így azt látom a képernyőre kiírt üzeneten, hogy valóban minden 0,1. másodpercben ismét meghívódik ez a függvény és a kiírás is újra megtörténik, mivel ez a számláló pörög felfele, viszont a szenzor pozíciójának adatának továbbra is csak az utolsó Activity indításkor átadott paraméter íródik ki. (A képen látható 281-es szám ez a számláló)

![](https://github.com/vizielod/Onlab-Android_VRgame_using_Unity_and_Wearnotch/blob/master/img/60761539_2226615617651572_5465133429307736064_n.png)
