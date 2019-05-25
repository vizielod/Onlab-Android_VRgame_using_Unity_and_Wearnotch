# Android and Unity communication for VR games using Wearnotch motion tracking system
This is the pilot project of a Fitness VR game which should use the wearnotch motion tracking system to track our movements and motions. For the 3D side of the game I will use Unity. Tha game will run on Android phones first.

Sorry but for now this documentation is part of a university project at BME, so the documentation is in hungarian, but planning to write one in english, somewhere in the near future.

Feel free to use this project for now. If you have any further question PM me at elod.vizi@yahoo.com.

# Android és Unity közötti valós idejű kommunikáció megvalósítása Wearnotch mozgáskövető rendszert használó VR játékhoz

A Repository Wiki oldalán egy részletesebb leírás található, az alábbiakban ezekből kivágott fontosabb részletek, egy lerövidített változat olvasható. Ha bármilyen kérdésed akad a projekttel, működéssel kapcsolatban, keress fel bátran az elod.vizi@yahoo.com címen.

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

A **VisualiserActivity** osztály **CalculateAngles()** függvényében történik a varázslat.

![](https://github.com/vizielod/Onlab-Android_VRgame_using_Unity_and_Wearnotch/blob/master/img/LogCat_1.JPG)

![](https://github.com/vizielod/Onlab-Android_VRgame_using_Unity_and_Wearnotch/blob/master/img/LogCat_2.JPG)

Azt még itt megjegyezném, hogy amennyiben csak egy szenzort használunk a méréshez annak mindenképp a mellkasra kell kerülnie az lesz a Chest nevű, ami a master bone-nak számít és ha több szenzort is használunk, akkor a többi szenzort mindig a chest-hez képest helyezi el a térben és ahhoz viszonyítja az elmozdulásukat. Így kritikusan fontos annak a helyes rögzítése és testre való felhelyezése.

## 5. Unity Projekt és Android app közötti kommunikáció felépítése

Következő lépésként arra jutottam, hogy szükség lesz valamilyen valós idejű kommunikációra a meglévő alkalmazás és a Unity projekt között. Kis brainstormingolás, utánaolvasgatás, fórum és youtube videótár böngészés után arra jutottam, hogy ezt a kommunikációt az alábbi módszerekkel lehetne megvalósítani:
* **WebSocket segítségével**
  * valószínűleg járható út
  * de nem egyszerű
  * több mint valószínű, hogy overkill lenne (legalábbis nekem annak tűnt)
  * utánanéztem a technológiának, de nem próbáltam megvalósítani, túl bonyolultnak tűnt ahhoz, hogy ez legyen a legoptimálisabb megoldás

* **Plugin használatával**
  * sok jól érthető, jól követhető és részletes tutorialt találtam 
  * számomra az alábbi trailer tűnt az egyik legérthetőbbnek:
  * https://www.youtube.com/watch?v=EElBAGkjPt4
  * de sajnos bárhogy próbálkoztam nem sikerült ugyan azt az eredményt elérjem amit a videóban is látunk

* **Unity projekt gradle exportálás majd importálás az Android Projektbe.**
  * végül ezt az utat próbáltam bejárni és ebből próbáltam volna megszülni a megoldást, de sajnos a real-time kommunikációt még ezzel sem sikerült megoldani mivel amikor egy gombnyomás hatására meghívom a beimportált UnityPlayerActivity-t, akkor ezzel egy új Activity indul, ami Android környezetben azt jelenti, hogy a korábban futó Activity Pause() állapotba kerül így sajnos a notch-tól való mintavételezés is leáll.
  * ennek a problémának a kiküszöbölésére próbáltam valamilyen Fragment-es megközelítést, mivel attól az Activity továbbra is fut a háttérben, de ezzel sem sikerült az amit szerettem volna. Az, hogy pontosan mit próbáltam és mi sült ki belőle részletesen bemutatom a következő fejezetbe.
  * tehát itt arra a következtetésre jutottam és jutottunk a konzulensemmel, hogy valószínűleg a Demo App-ot valahogy egy háttérben futó service-ként kéne kezelni, ezt a megközelítést szeretném a nyáron megvalósítani, hogy szeptembertől tudjam folytatni a projektet Szakdolgozatként és már tudjak a konkrét játék/alkalmazás Unityben való megtervezésével foglalkozni.
  
A következő pontokban lássuk az említett 3. opcióval való próbálkozás részletes bemutatását.

## 6. Demo App működése és kommunikációja a beimportált Unity Projekttel

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
3. Nevezzük át az alap Scene-t **SampleScene**-re (ha más neve van)
4. Adjunk hozzá egy Canvas-t (**Create -> UI -> Canvas**)
5. A Canvas-ra pedig vegyünk fel egy Text-et (**UI -> Text**)
6. A Text mező **default** értékének **“New Text”**-et adtam, a **Font Size**-t pedig **24**-re állítóttam. valamint elhelyeztem úgy, hogy a Game nézetben körülbelül a kijelző közepére kerüljön (positioned to **center**).
7. A vászonra felvettem egy **TextMesh**-t is, aminek Default értékként **“Hello World”**-ot állítottam be, de lényegében ezt nem használom a későbbiekben.
8. Amit én még itt csináltam a letisztultság kedvéért a **Camera Background** színét átállítóttam erre a kékeszöldre, hogy a megjelenő szöveget könnyen lehessen olvasni.
9. Létrehoztam egy **Empty Game Object**-et **PluginScript** néven, amihez hozzáadtam egy **Script Component**-et (**Add Component -> New Script**) **Plugin Wrapper** néven. Ebbe írjuk az üzenetváltáshoz szükséges C# kódot.
10. Először a kódban csak felvettem a Text típusú mezőt **myText** néven és implementáltam egy **DefaultText()** illetve egy **SetText(string)** függvényt. Amik közül az előbbi egy Default értéket állít be a myText mezőre, az utóbbi pedig a paraméterként átadott szót jeleníti meg a vásznon.

    ```C#
    public Text myText;
    ```
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
11. Itt már végrehajtottam a Unity Projekt Importálását a Demo App-ba, hogy ki tudjam próbálni az eddigieket. Ezt a folyamatot pedig a KÖVETKEZŐ (**7. fejezet**) fejezetben leírtak alapján tudjuk megtenni!
12. Majd tesztelésképp annyit csináltam első körben, hogy a Demo App **MainFragment** osztályában található **getSteadyData()** függvényhez tartozó **OnClick** eseményre elindítottam a **UnityPlayerActivity** és üzenetként átadtam egy tetszőleges stringet (‘**notchposition**’). Ezt a UnityPlayerActivity **onStart()** állapotában felfogtam és a **UnitySendMessage** függvény segítségével meghívtam az imént Unity-ben implementált PluginScript objektumban található PluginWrapper C# osztály **SetText(string)** függvényét.

```Java
    @OnClick(R.id.btn_get_steady)
    void getSteadyData() {
        inProgress();

        mNotchService.getSteadyData(new EmptyCallback<Void>());
        /*Intent intent = new Intent(getBaseActivity(), UnityPlayerActivity.class);
        intent.putExtra("message", "notchposition");
        startActivity(intent);*/

        Log.i(LOGTAG, "getSteadyData");
        Log.i(LOGTAG, mNotchService.getSteadyData(new EmptyCallback<Void>()).toString());
    }
```
```Java
    @Override protected void onStart()
    {
        super.onStart();
        mUnityPlayer.start();
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");
        UnityPlayer.UnitySendMessage("PluginScript", "SetText", message);
        Log.i(LOGTAG, "UnityProject Intent Started");
    }
```

 * * Itt megemlíteném, hogy ahhoz, hogy a tényleges szenzoroktól kapott adatokat tudjam továbbítani az adott Text mezőbe ezt a funkciót áthelyeztem a Start Real-Time hatására elinduló **VisualiserActivity**-be. Ahol a teszteléshez csak rögtönzötten a jobb felső sarokban lévő felül nézetre átváltó gomb (button_top_view) eseménykezelőjét , az **onTopViewClicked()** függvényt írtam át, hogy ez hívja a **UnityPlayerActivity**-t és indítsa el a Unity-ből exportált projektet.

```Java
    @OnClick(R.id.button_top_view)
    void onTopViewClicked() {
        /*if (mRenderer != null) {
            mRenderer.setCameraBeta(0.0f);
            mRenderer.setCameraAlpha((float) Math.PI/2.0f);
        }
        refreshUI();*/
        Intent intent = new Intent(this, UnityPlayerActivity.class);
        //intent.putExtra("message", "notchposition");
        intent.putExtra("message", RelativeNotchPosition);
        startActivity(intent);
        //startService(intent);
    }
```

Itt a RelativeNotchPosition egy sztring változó, ami értékét a korábban említett **calculateAngles(int)** függvényben kapja és a szenzortól kapott adatok alapján feldolgozott Notch pozicióját tartalmazza.

```Java
private String RelativeNotchPosition;

private void calculateAngles(int frameIndex) {
...
	fvec3 chestAngles = new fvec3();
...
	mData.calculateRelativeAngle(chest, root, frameIndex, chestAngles);
	RelativeNotchPosition = chestAngles.toString();
...
}
```

13. Miután ezzel a módszerrel sikerrel jártam átültettem a konzulensem által mutatott GitHub projekt (https://github.com/inbgche/Unity-Android-Communication) RocketLuncher Unity projekt **Assets -> Scripts -> AndroidManager** osztályában található kódot a PluginWrapper osztályomba.
14. Majd ennek megfelelően az Android Studio-ba importalt UnityPlayerActivity osztályban implementáltam a **javaTestFunc(string)** függvényt amit az Activity elindításakor a C#-ban található **Start()** függvény hív meg.
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
```C#
void Start () {
        //SetText("START");
        PluginWrapper.GetInstance().CallJavaFunc("javaTestFunc", "UnityJavaJarTet");
        Debug.Log("GO");
	}
```
15. Annyit módosítottam a kódon, hogy én a **javaTestFunc(string)** függvényből nem a paraméterként kapott stringet íratom ki, hanem ez lehetne akár paraméter nélküli függvény is, mivel én itt a híváskor átadott “message” névvel jelölt kulcs értékét adom át és azzal hívom a C# kód **SetJavaLog(string)** függvényét, ami a **myText** mező értékének átadja ezt a karakterláncot.
16. Itt végül még, hogy meggyőződjek arról, hogy a C# kódban található frissítés működik (és a probléma valóban az, hogy csak az utolsó NotchPosition sztringet veszi át) a **Start()**-ban hívott **InvokeRepeating(key, int, int)** függvény segítségével próbáltam ki, ami aminek megadtam, hogy az indulástól számított 0,1. másodperctől 0,1 másodpercenként hívja meg a **repeatCall()** függvényt, ami ugyanazt csinálja mint korábban a Start() maga tette, azaz elsüti a Java-ban található **JavaTestFunc()** függvényt.

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

## 7. Unity projekt importálása meglévő Android Studio projektbe.

Felsorolom és leírom a lépéseket, hogy én pontosan hogy és mit csináltam, de mindezeket az alábbi tutoriálok alapján csináltam amik elég szépen leírják lépésről lépésre, hogy mit kell tenni:

https://stackoverflow.com/questions/35535310/how-to-import-unity-project-into-existing-android-studios-project

https://www.youtube.com/watch?v=GEQSaF4LPgk

https://stackoverflow.com/questions/51959096/no-implementation-found-for-void-com-unity3d-player-unityplayer-nativerestartact

### Exportálás előkészítése:

1. **Edit -> Preferences -> External Tools** menüpontnál megnézni, hogy be van-e húzva a megfelelő SDK, JDK illetve esetünkben arra jutottam, hogy szükség lesz az NDK-ra is.
1. Ha van már Android Studio a gépen akkor valamilyen JDK-nak is lennie kéne. Ez esetemben a Program Files/Java mappában volt megtalálható és a **jdk1.8.0_201**-et tallóztam ide. Ha nincs JDK a gépen akkor a fenti videóban be van mutatva, hogy mi a tenedő.
1. Ugyan így betallóztam az SDK-t is ami esetemben a Users/<user>/.android mappában található, így elég ezt a mappát tallózni.
1. NDK viszont nem volt a gépen, azt letöltöttem egyet a Download gomb használatával (**android-ndk-r13b**), majd kicsomagoltam úgyszintén a 3. pontban is látott **.android** mappába és innen betallóztam.
1. File -> Build Settings kiválasztani az Android platformot és Switch Platform.
1. Player Settings megnyitása, oldalt a Package name-t átírni, például ‘com.unity3d.plugintest’-re
1. Scripting Backend-et átállítani IL2CPP-re és az ARM64 checkboxot bepipálni (3. link alapján)
1. Valamint ha az Auto Graphics API ki van választva, akkor vegyük ki onnan a pipát és a Graphics APIs alatt a Vulkan-t húzzuk az OpenGLES3 fölé. Az alapértelmezett beállítással nekem a Buildelés és exportálás főleg az első alkalommal egy teljes éjszakát felvett. Ezen beálításokkal viszont emberi időben lefut.
1. Győződjünk meg arról, hogy a Build Settings-nél a Build System Gradle-re van állíva, illetve a Development Build-et is válasszuk ki, ha alapból nincs.

### Exportálás:

1. Ha mindez megvan akkor mehet az Export. Az exportálást pedig az Android Studio projektjének mappájába tegyük meg, ami esetünkben a Demo App Tutorial mappája lesz. Itt létre fog jönni egy UnityProject nevű mappa ami tartalmazza a kiexportált projektet.

### Importálás a Demo app-ba:

1. Az Android Studio itt kicsit elszórakoztatja magát, frissít egyet és bekerül a UnityProjekt mappa a Project nézetbe.
1. A **build.gradle**-ben (Module:UnityProject) törölni a **buildToolsVersion** sort és az **applicationID**-t
1. **apply plugin: ‘com.android.application’** sort átírni **apply plugin: ‘com.android.library’** -re
1. Az **AndroidManifest.xml** fájlból törölni:
	1. az **application** tag-hez tartozó attribútumokat 
	1. az **intent-filter** alól a **.MAIN** és a **.LAUNCHER** végű sorokat
	1. valamint a **uses-permission** tag-el kezdődő sorok **.INTERNET**, **.WRITE_EXTERNAL_STORAGE** és **.READ_EXTERNAL_STORAGE** utáni részeket.
1. A **settings.gradle**-ben include-hoz felvenni a **‘:UnityProject’**-et (vesszővel elválasztva)
1. Végül pedig a **build.gradle(Module:app)** -ban a **dependencies**-hez felvenni a következő sort: **implementation project(‘:UnityProject’)**
1. Ha sikerült edig eljutni, akkor Android Studioban nyomjunk egy **Clean Project**-et, majd **Rebuild Project**.
1. Az eddigi lépéseket követve én itt sikerrel jártam és az Androidos eszközömön sikeresen tudtam futtatni az alkalmazást ami a myText mezőbe kiírta a kívánt üzenetet. Első próbálkozásaimra csak egy egyszerűen átadott “notchposition” sztringet, később pedig a szenzortól kapott adatokat (csak az Activity meghívásának pillanatában kapott utolsó adatot.


	## **Próbálkozás Fragmenttel**

Szomorúan kellett tapasztaljam, hogy az előzőekben leírtak alapján csak a UnityPlayerActivity meghívásának pillanatában kapott utolsó adatot írja ki a képernyőre. Ami nem is olyan meglepő mivel egy újonnan induló a korábbi Activity-t Pause() állapotba kényszeríti.
Fragment hívás hatására nem történik ilyen probléma így elkezdtem ezzel is próbálkozni. De sajnos nem túl sok sikerrel így ezt nem részletezném túlzottan. Talán ha meg lehetne azt oldani, hogy a UnityPlayerActivity osztályból Fragmentet csinálni akkor az még jó megoldás lenne. De ez vagy nem is valósítható meg, vagy ha igen akkor nagyon macerás és nagyon sok és fárasztó meló árán.

### Tehát amit itt csináltam:

1. Felvettem egy üres Fragmentet BlankFragment2 néven a UnityProject mappába a UnityPlayerActivity mellé.
1. A VisualiserActivity osztályban implementáltam egy startFragment() függvényt ami nem csinál mást mint elindítja a BlankFragment2 fragmentet és átad neki egy üzenetet, ami ez esetben a RelativeNotchPosition változóban tárolt Szenzor pozíciót leíró dőlésszögek.
1. Ezt a függvényt pedig meghívom a VisualiserActivity refreshAngles() függvényből ami a már eddig is implementált valós idejű kiíratásért volt felelős. Vagyis minden frame-ben meghívta a calculateAngles() függvényt ami a megfelelő helyre kiírta a szenzortól kapott pozíció adatait.
1. Mostmár a Fragmentem hívásának hatására a képernyő felső sorába bekerült egy sor pirossal, ami ugyan ezeket az adatokat írja ki, de ez a teszt fázis volt, mivel azt gondoltam, hogy akkor ezt így a UnityPlayerActivity-ből is ki tudom majd íratni, de sajnos ez nem sikerült.

![](https://github.com/vizielod/Onlab-Android_VRgame_using_Unity_and_Wearnotch/blob/master/img/61126917_359408791353761_5281296768770768896_n.png)


## 8. Rögzített mozgás 3D karakterre való ráhelyezése Unityben

Igazából ez volt az a dolog amivel a félév elején foglalkozni kezdtem miután megismerkedtem a WearNotch rendszer illetve az alkalmazás működésével. Viszont ez az előző gondolatmenetbe nem illett volna bele ezért inkább mostanra hagytam ennek a bemutatását.

A projektem ezen része igazából főképp az alábbi Youtube tutorial videókon nyugszik:

* https://www.youtube.com/watch?v=dCwNaE_eVsM
* https://www.youtube.com/watch?v=UeUgfA6ZWNs
* https://www.youtube.com/watch?v=nGdCPpWyjM8

### Lépések:

1. Unity 2018-as Projekt létrehozása (Wearnotch_test nevet adtam neki)
1. Szükség volt egy karakterre, mivel a Notch avatárhoz még nem tartozik default mesh. Tehát az Assett store-ban kezdtem keresgélni és én végül egy ingyenes Knight prefab letöltése mellett döntöttem.
1. Az első két videó alapján a Knight prefab-ot felhasználva létrehoztam egy ragdoll-t.
1. A wearnotch honlapjára belépve a Library-ból letöltöttem egy korábban felvett mozdulatsorom .FBX és .BVH fájlját, majd ezt bemásoltam a Unity projekt Assets mappájába.
1. Innentől követtem a harmadik videóban látható lépéseket a 10. perc 40. másodpercétől és az alapján sikerült életre kelteni a lovagomat.

	## **Futtatás Android eszközön**

Innen kíváncsi voltam, hogy nézne ki ez Android eszközön futtatva. Ehhez a már korábbiakban bemutatott lépésekhez hasonlókat kellett elvégezzem. Vagyis a Build Settings-nél a Platformot átállítani Android-ra, a Player Settings-nél a a Package Name-t átírni (com.bme.Wearnotch_test). Valamint ez esetben a Build Settings-nél nem Exportálni szeretnénk, így Build System-nek Internal-t válasszuk és a Development Build-et sem kell kiválasztani. Viszont figyeljünk itt is arra, hogy a Scene hozzá legyen adva a Scenes in Build listához.

![](https://github.com/vizielod/Onlab-Android_VRgame_using_Unity_and_Wearnotch/blob/master/img/60633367_435723150560375_4710838130184290304_n.png)

Ha ez megvan és csatlakoztattuk a telefonunkat a számítógéphez, akkor a Build and Run-ra kattintva, először egy Build mappába mentsük ki a .apk-t, majd ez automatikusan lefordul a telefonunkra és lefut az eszközön.

## 	**Futtatás Cardboard segítségével**

Ezek után szerettem volna azt is megnézni, hogy hogyan nézhet ki ez az Androidos VR opció használatával, vagyis Google Cardboard-al, ami házilag is elkészíthető de nekem egy korábbi állásbörzéről pont került itthon egy Graphisoft-os nagyon jól használható keret.

Az alábbi videót követtem:

https://www.youtube.com/watch?v=G1jR6q59iLg

Nagyon letisztul és könnyen érthető így sikerült is ezt probléma nélkül megvalósítsam és kipróbáljam, ahogy a képek is szemléltetik. Először csak 3D-s nézetben, majd az elképzelt játékhoz hűen 1st person nézetben is kipróbáltam. Amihez nemtudom, hogy a legjobb megoldást választottam-e, de egyszerűen fogtam és a kamerát a karakter feje helyén helyeztem el. (Lehet van ennél kifinomultabb módszer is). 

### 3rd Person nézet:

![](https://github.com/vizielod/Onlab-Android_VRgame_using_Unity_and_Wearnotch/blob/master/img/55835889_2026001794372170_3505759015181221888_n.png)

### 1st Person nézet:

![](https://github.com/vizielod/Onlab-Android_VRgame_using_Unity_and_Wearnotch/blob/master/img/60808513_434346517141511_6002070943365595136_n.png)
![](https://github.com/vizielod/Onlab-Android_VRgame_using_Unity_and_Wearnotch/blob/master/img/60805149_424105764840705_1364593427282395136_n.png)


<footer> Vizi Előd - BME - Szoftverfejlesztés/AUT - Önlabor - 2019 </footer>
