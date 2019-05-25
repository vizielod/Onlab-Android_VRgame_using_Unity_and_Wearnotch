# Onlab-Android_VRgame_using_Unity_and_Wearnotch
This is the pilot project of a Fitness VR game which should use the wearnotch motion tracking system to track our movements and motions. For the 3D side of the game I will use Unity. Tha game will run on Android phones first.

## 1. Projekt célja, motiváció, eredeti ötlet

Az elmúlt időszakban, ami körülbelül 1-1,5 évet ölel fel egyre jobban kezdtek érdekelni a különböző Virtuális valósággal (VR - Virtual Reality), Kiterjesztett valósággal (AR - Augmented Reality) és Kevert valósággal (MR - Mixed Reality) kapcsolatos témák, projektek és fejlesztések.

Tehát így adott volt, hogy a 6. féléves Önálló laboratórium tárgy keretein belül valamilyen ezekhez a technológiákhoz tartozó témán szeretnék dolgozni.

Mivel ezen technológiák és az egyetem mellett az elsődleges dolog ami leginkább foglalkoztat, ami a hobbim és egyben a szenvedélyem is az a Fitness, testépítés, izom-, erő- és állóképesség fejlesztésének világa. Innen jött az ötlet, hogy valami ehhez alkalmazás fejlesztésébe szeretnék belefogni ami emberek százait vagy akár ezreit tudja mozgásra bírni.

Úgy a sportokban mint az egészségügyben, gyógypedagógiában, rehabilitációban hatalmas szerepet fognak játszani ezek a technológiák közel 10 éven belül. Különböző VR és AR alkalmazások segíthetnek embereknek a félelmeik leküzdésében, traumákon való túllendülésben vagy akár egy komolyabb sérülés után újra tanulni a végtagok mozgatását, a járást és megannyi más fantasztikus dolgot fogunk ezeknek a technológiáknak köszönni. Az oktatási rendszer reformját nem is említve.

Még a félév megkezdése előtt felvázoltam ötleteimet a konzulensemnek és mint kiderült pont a félév elején volt esedékes, hogy kapjon a tanszék egy Motion Tracking rendszert a Wearnotch cég jóvoltából. Így kapva kaptam az alkalmon és jelentkeztem erre a témára, hogy megismerkedjek a rendszer működésével és fejlesztői hátterével.

## 2. Wearnotch rendszer és A Notch Pioneer Telefonos alkalmazás bemutatása

A hétköznapi felhasználók számára a rendszer működését jól szemlélteti az alábbi videó: https://www.youtube.com/watch?time_continue=12&v=_D3teoFb0ls amiből az is kiderül, hogy az eszközök vízállók.

Lássunk néhány alap adatot az eszközről:

![Wearnotch_1](https://github.com/vizielod/Onlab-Android_VRgame_using_Unity_and_Wearnotch/blob/master/img/Wearnotch_1.JPG)

Az alkalmazáson keresztül egyelőre maximum 18 szenzort, vagyis 3 szett Notch-ot tudunk párosítani és ezekkel végzett mozgásokat rögzíteni.

![Wearnotch_2](https://github.com/vizielod/Onlab-Android_VRgame_using_Unity_and_Wearnotch/blob/master/img/Wearnotch_2.JPG)

A szükséges beállítások, párosítások és kalibrálás után a notch-ok különböző színben kezdenek világítani az alapján, hogy melyik testrészünkre kell őket felhelyezzük. Ezek alapján felhelyeztem magamra a pántok segítségével a szenzorokat és kipróbáltam a működésüket, rögzítettem pár mozgást (előrehajlás, karok oldalra emelése stb), amiket a rendszer először a szenzorok memóriájába tárol el, majd onnan átkerülnek az alkalmazásba ahonnan elmenthetjük őket a honlapon regisztrált fiókunkban, majd a Library fül alatt ezt később visszanézhetjük illetve letölthetjük a szükséges CSV, BVH vagy FBX formátumokban, attól függően, hogy mire szeretnénk ezt felhasználni.

![](https://github.com/vizielod/Onlab-Android_VRgame_using_Unity_and_Wearnotch/blob/master/img/IMG_20190220_145829.jpg)

## 3. Android SDK és Demo App (Template) megismerése

Tehát mostanra már mezei felhasználóként sikerült megismerjem a rendszer és a Notch app működését, ideje volt viharosabb vizekre evezni és megismerkedni a rendszerhez tartozó fejlesztői környezettel, ami esetemben mivel nem rendelkezem iOS-es készülékkel, az Android SDK volt.

Ezt az Demo app-ot a honlapra való belépés után a Developer menüpont alatt érjük el, ahol először egy rövid Áttekintő (Overview) fogad minket. Az oldalsó menü sávban viszont kiválaszthatjuk a fejlesztői környezetünkhöz tartozó átfogóbb, részletesebb leírást, dokumentációt. (https://wearnotch.com/developers/docs/sdk/android/)

Ez a dokumentáció leírja, hogy hogyan is tudjuk letölteni és beüzemelni az SDK-t a megfelelő fejlesztői környezetben (esetemben Android Studio):

![Wearnotch Android SDK Setup steps](https://github.com/vizielod/Onlab-Android_VRgame_using_Unity_and_Wearnotch/blob/master/img/AndroidSDK_Setup_steps.JPG)

Mivel az itt található lépések elég érthetően és egyértelműen leírják a Template app source menüpont alól letölthető app beüzemelését, ezért ezt itt nem részletezem. A Wiki oldalon egy hosszabb leírás található lépésről lépésre bemutatva az App beüzemelését.

## 4. Notch-ok Demo alkalmazással való kommunikációja

LogCat üzenetek segítségével sikerült kiderítenem, hogy hogyan is valósul meg az eszköz és az alkalmazás közötti kommunikáció, vagyis pontosabban az, hogy az alkalmazás, hogyan dolgozza fel ezeket az adatokat.

A VisualiserActivity osztály CalculateAngles() függvényében történik a varázslat.

![](https://github.com/vizielod/Onlab-Android_VRgame_using_Unity_and_Wearnotch/blob/master/img/LogCat_1.JPG)

![](https://github.com/vizielod/Onlab-Android_VRgame_using_Unity_and_Wearnotch/blob/master/img/LogCat_2.JPG)

Azt még itt megjegyezném, hogy amennyiben csak egy szenzort használunk a méréshez annak mindenképp a mellkasra kell kerülnie az lesz a Chest nevű, ami a master bone-nak számít és ha több szenzort is használunk, akkor a többi szenzort mindig a chest-hez képest helyezi el a térben és ahhoz viszonyítja az elmozdulásukat. Így kritikusan fontos annak a helyes rögzítése és testre való felhelyezése.

## 5. Unity Projekt és Android app közötti kommunikáció felépítése

Következő lépésként arra jutottam, hogy szükség lesz valamilyen valós idejű kommunikációra a meglévő alkalmazás és a Unity projekt között. Kis brainstormingolás, utánaolvasgatás, fórum és youtube videótár böngészés után arra jutottam, hogy ezt a kommunikációt az alábbi módszerekkel lehetne megvalósítani:
* WebSocket segítségével
  * valószínűleg járható út
  * de nem egyszerű
  * több mint valószínű, hogy overkill lenne (legalábbis nekem annak tűnt)
  * utánanéztem a technológiának, de nem próbáltam megvalósítani, túl bonyolultnak tűnt ahhoz, hogy ez legyen a legoptimálisabb megoldás

* Plugin használatával
  * sok jól érthető, jól követhető és részletes tutorialt találtam 
  * számomra az alábbi trailer tűnt az egyik legérthetőbbnek:
  * https://www.youtube.com/watch?v=EElBAGkjPt4
  * de sajnos bárhogy próbálkoztam nem sikerült ugyan azt az eredményt elérjem amit a videóban is látunk

* Unity projekt gradle exportálás majd importálás az Android Projektbe.
  * végül ezt az utat próbáltam bejárni és ebből próbáltam volna megszülni a megoldást, de sajnos a real-time kommunikációt még ezzel sem sikerült megoldani mivel amikor egy gombnyomás hatására meghívom a beimportált UnityPlayerActivity-t, akkor ezzel egy új Activity indul, ami Android környezetben azt jelenti, hogy a korábban futó Activity Pause() állapotba kerül így sajnos a notch-tól való mintavételezés is leáll.
  * ennek a problémának a kiküszöbölésére próbáltam valamilyen Fragment-es megközelítést, mivel attól az Activity továbbra is fut a háttérben, de ezzel sem sikerült az amit szerettem volna. Az, hogy pontosan mit próbáltam és mi sült ki belőle részletesen bemutatom a következő fejezetbe.
  * tehát itt arra a következtetésre jutottam és jutottunk a konzulensemmel, hogy valószínűleg a Demo App-ot valahogy egy háttérben futó service-ként kéne kezelni, ezt a megközelítést szeretném a nyáron megvalósítani, hogy szeptembertől tudjam folytatni a projektet Szakdolgozatként és már tudjak a konkrét játék/alkalmazás Unityben való megtervezésével foglalkozni.
  
A következő pontokban lássuk az említett 3. opcióval való próbálkozás részletes bemutatását.



Tehát itt szeretném részletesen lépésről lépésre bemutatni azt, hogy jelenleg hol is tartok és hogyan jutottam ide az alkalmazással.

### Használt fejlesztőkörnyezet, keretrendszer verziók és eszközök:

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

### Szükséges lépések:


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

    ```C#
    void DefaultText()
    {
        SetText("DEFAULT");
    }
    ```
    ```C#
    public void SetText(string text)
    {
        myText.text = text;
    }
    ```
11. Itt már végrehajtottam a Unity Projekt Importálását a Demo App-ba, hogy ki tudjam próbálni az eddigieket. Ezt a folyamatot pedig a KÖVETKEZŐ (7. fejezet) fejezetben leírtak alapján tudjuk megtenni!
12. Majd tesztelésképp annyit csináltam első körben, hogy a Demo App MainFragment osztályában található getSteadyData() függvényhez tartozó OnClick eseményre elindítottam a UnityPlayerActivity és üzenetként átadtam egy tetszőleges stringet (‘notchposition’). Ezt a UnityPlayerActivity onStart() állapotában felfogtam és a UnitySendMessage függvény segítségével meghívtam az imént Unity-ben implementált PluginScript objektumban található PluginWrapper C# osztály SetText(string) függvényét.

```Java
Intent intent = new Intent(getBaseActivity(), UnityPlayerActivity.class);
intent.putExtra("message", "notchposition");
startActivity(intent);
```

 * * Itt megemlíteném, hogy ahhoz, hogy a tényleges szenzoroktól kapott adatokat tudjam továbbítani az adott Text mezőbe ezt a funkciót áthelyeztem a Start Real-Time hatására elinduló VisualiserActivity-be. Ahol a teszteléshez csak rögtönzötten a jobb felső sarokban lévő felül nézetre átváltó gomb (button_top_view) eseménykezelőjét , az onTopViewClicked() függvényt írtam át, hogy ez hívja a UnityPlayerActivity-t és indítsa el a Unity-ből exportált projektet.
13. Miután ezzel a módszerrel sikerrel jártam átültettem a konzulensem által mutatott GitHub projekt (https://github.com/inbgche/Unity-Android-Communication) RocketLuncher Unity projekt Assets -> Scripts -> AndroidManager osztályában található kódot a PluginWrapper osztályomba.
14. Majd ennek megfelelően az Android Studio-ba importalt UnityPlayerActivity osztályban implementáltam a javaTestFunc(string) függvényt amit az Activity elindításakor a C#-ban található Start() függvény hív meg.
```Java
public void javaTestFunc(String strFromUnity){
        Bundle bundle = getIntent().getExtras();
        i++;
        String message = i + "" + bundle.getString("message");
        //UnityPlayer.UnitySendMessage("PluginScript", "SetText", message);
        //UnityPlayer.UnitySendMessage("PluginScript", "SetJavaLog", strFromUnity + "HelloWorld");
        UnityPlayer.UnitySendMessage("PluginScript", "SetJavaLog", message);
    }
```
15. Annyit módosítottam a kódon, hogy én a javaTestFunc(string) függvényből nem a paraméterként kapott stringet íratom ki, hanem ez lehetne akár paraméter nélküli függvény is, mivel én itt a híváskor átadott “message” névvel jelölt kulcs értékét adom át és azzal hívom a C# kód SetJavaLog(string) függvényét, ami a myText mező értékének átadja ezt a karakterláncot.
16. Itt végül még, hogy meggyőződjek arról, hogy a C# kódban található frissítés működik (és a probléma valóban az, hogy csak az utolsó NotchPosition sztringet veszi át) a Start()-ban hívott InvokeRepeating() függvény segítségével próbáltam ki, ami aminek megadtam, hogy az indulástól számított 0,1. másodperctől 0,1 másodpercenként hívja meg a repeatCall() függvényt, ami ugyanazt csinálja mint korábban a Start() maga tette, azaz elsüti a Java-ban található JavaTestFunc() függvényt.

```C#
void Start () {
        //SetText("START");
        InvokeRepeating("repeatCall", 0.1f, 0.1f);
        //PluginWrapper.GetInstance().CallJavaFunc("javaTestFunc", "UnityJavaJarTet");
        Debug.Log("GO");
	}
 ```
 ```C#
 void repeatCall()
    {
        PluginWrapper.GetInstance().CallJavaFunc("javaTestFunc", "UnityJavaJarTet");
    }
 ```

17. Ezek után a UnityPlayerActivity osztály javaTestFunc() fügvényéhez hozzávettem egy sima integer változót amit minden függvényhíváskor növelek egyel, így azt látom a képernyőre kiírt üzeneten, hogy valóban minden 0,1. másodpercben ismét meghívódik ez a függvény és a kiírás is újra megtörténik, mivel ez a számláló pörög felfele, viszont a szenzor pozíciójának adatának továbbra is csak az utolsó Activity indításkor átadott paraméter íródik ki. (A képen látható 281-es szám ez a számláló)

![](https://github.com/vizielod/Onlab-Android_VRgame_using_Unity_and_Wearnotch/blob/master/img/60761539_2226615617651572_5465133429307736064_n.png)
