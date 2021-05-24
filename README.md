# mtgjson5-client

This project helps you use the JSON files from project [https://mtgjson.com](https://mtgjson.com)


## 1. Installation

You need JDK `11.0.9+` and a dependency management tool such as Maven or Gradle. Please change the `sdk-version` with the latest version.

- Compile the library from the source

```bash
git clone https://github.com/clun/mtgjson-sdk.git
mvn clean install
```

- Import the project in your maven project

```xml
<dependency>
 <groupId>com.mtgjson</groupId>
 <artifactId>mtgjson-sdk</artifactId>
 <version>${sdk-version}</version>
</dependency>
```

## 2. How to use it

✅ **[Set List](https://mtgjson.com/downloads/all-files/#setlist)**

File containing a list of meta data for all sets.

The file is pretty small (<600kB) as such the client use an Http request to read the data. There is no need to download `SetList.json` locally

```java
// use htpp
List<Expansion> setList = MtgJsonClient.setList().findAll();

// use local file
MtgJsonClient.setList().downloadJson("/tmp/SetList.jsoon");
List<Expansion> setList2 = MtgJsonClient.setList().findAll("/tmp/SetList.jsoon");
```

✅ **[Working with a single Set](https://mtgjson.com/downloads/all-files/#setlist)**

Each set got a dedicated file `<set_code>.json`. Files are pretty small (<1MB) as such the client use an Http request to read the data.

```java
Optional<Expansion> expansion10e = MtgJsonClient.set("10e").find();
Optional<Expansion> expansion11e = MtgJsonClient.set("11e").find();

// use htpp
expansion10e.get().getCards()
            .stream().map(Card::getName)
            .forEach(System.out::println);
```

✅ **[All Prices](https://mtgjson.com/downloads/all-files/#allprices)**

File containing all prices of cards in various formats. 

This file is quite big and we should download the file locally and then work with a JSON Streaming library.


```java
// Download filed locally providing the destination
MtgJsonClient.allPrices().downloadJson("/tmp/allPrices.json");

// You can simplyfy the code
AllPricesClient allPrices = MtgJsonClient.allPrices();

// You can doanload the file you need...
allPrices.downloadBZ2("/tmp/allPrices.json.bz2");
allPrices.downloadGZ("/tmp/allPrices.json.gz");
allPrices.downloadXZ("/tmp/allPrices.json.xz");
allPrices.downloadZip("/tmp/allPrices.json.zip");

// ...or Download all in one go
allPrices.downloadAll("/tmp")

// Download checksums (useful to check if files have been resfreshed since last time)
String sha256    = allPrices.getChecksum();
String sha256bz2 = allPrices.getChecksumBZ2();
String sha256gz  = allPrices.getChecksumGZ();
String sha256xz  = allPrices.getChecksumXZ();
String sha256zip = allPrices.getChecksumZIP();

// Parsing file with providing callback implementation (Consumer) invoked for each entry
allPrices.parse("/tmp/allPrices.json", new Consumer<CardPrice>() {
  public void accept(CardPrice cardPrice) {
    System.out.println("Processing card : " + cardPrice.getCardId());
  } 
});
```

✅ **[All Identifiers](https://mtgjson.com/downloads/all-files/#allidentifiers)**

File containing all cards organized by UUID.

This file is quite big and we should download the file locally and then work with a JSON Streaming library.


```java
// Download filed locally providing the destination
MtgJsonClient.allIdentifiers().downloadJson("/tmp/allIdentifiers.json");

// You can simplyfy the code
AllPricesClient allIdentifiers = MtgJsonClient.allIdentifiers();

// You can doanload the file you need...
allIdentifiers.downloadBZ2("/tmp/allPrices.json.bz2");
allIdentifiers.downloadGZ("/tmp/allPrices.json.gz");
allIdentifiers.downloadXZ("/tmp/allPrices.json.xz");
allIdentifiers.downloadZip("/tmp/allPrices.json.zip");

// ...or Download all in one go
allIdentifiers.downloadAll("/tmp")

// Download checksums (useful to check if files have been resfreshed since last time)
String sha256    = allIdentifiers.getChecksum();
String sha256bz2 = allIdentifiers.getChecksumBZ2();
String sha256gz  = allIdentifiers.getChecksumGZ();
String sha256xz  = allIdentifiers.getChecksumXZ();
String sha256zip = allIdentifiers.getChecksumZIP();

// Parsing file with providing callback implementation (Consumer) invoked for each entry
allIdentifiers.parse("/tmp/allIdentifiers.json", new Consumer<Card>() {
  public void accept(Card card) {
    System.out.println("Processing card : " + card.getCardId());
  } 
});
```

✅ **[All Printings](https://mtgjson.com/downloads/all-files/#allprintings)**

File containing all cards, including all printings and variations, categorized by set. 

This file is quite big and we should download the file locally and then work with a JSON Streaming library.

```java
// Download files you need locally providing the destination files
MtgJsonClient.allPrintings().downloadJson("/tmp/allPrintings.json");
MtgJsonClient.allPrintings().downloadBZ2("/tmp/allPrintings.json.bz2");
MtgJsonClient.allPrintings().downloadGZ("/tmp/allPrintings.json.gz");
MtgJsonClient.allPrintings().downloadXZ("/tmp/allPrintings.json.xz");
MtgJsonClient.allPrintings().downloadZip("/tmp/allPrintings.json.zip");

// Download all in one go
MtgJsonClient.allPrintings().downloadAll("/tmp")

// Download checksums (useful to check if files have been resfreshed since last time)
String sha256    = MtgJsonClient.allPrintings().getChecksum();
String sha256bz2 = MtgJsonClient.allPrintings().getChecksumBZ2();
String sha256gz  = MtgJsonClient.allPrintings().getChecksumGZ();
String sha256xz  = MtgJsonClient.allPrintings().getChecksumXZ();
String sha256zip = MtgJsonClient.allPrintings().getChecksumZIP();

//todo
```

