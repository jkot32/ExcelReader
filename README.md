# ExcelReader
Program pro čtení prvočísel z xlsx souborů.
Soubory jsou postupně procházeny po řádcích, program přečte nanejvýš jedno prvočíslo na řádek.

Vzhledem k rozdílným velikostem vstupu jsou pro rozpoznání prvočísel použity 2 algoritmy, pomalejší (exaktní) je použit pouze pro vstupy menší než hraniční hodnota, pro větší čísla je použit aproximační algoritmus.

Spustitelná verze (.jar) je ke stažení [zde](https://github.com/jkot32/ExcelReader/releases/tag/Release)

## Rozhraní
### Validator
Slouží pro jednodušší testování průchodu xlsx souboru, řetězce převádí na generický Optional
## Třídy

### PrimeChecker (impl. Validator)
Převádí hodnoty z typu String na BigInteger, pak testuje prvočíselnost vybraným algoritmem.

### ExcelRowIterator
Otevře xslx soubor jako stream (pomocí knihovny xlsx-streamer) a postupně prochází řádky s daty (do nalezení první validní hodnoty).

## Testy
Testy se nachází ve třídách **ExcelRowIteratorTest** a **PrimeCheckerTest**, otestována je jak práce s xlsx soubory, tak i hledání prvočísel.


