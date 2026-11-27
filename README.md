# Assorti Demos

## 1. Tests per laag

## 2. Identity vs. Sequence vs. NonGenerated

De startupRunner gebruikt de demorunner om 5000 players te inserten. Zie de performance-verschillen per soort ID-generation.

## 3. N+1 queries

De repository van Player Join-fetcht de preferences nu. Dit is niet exact wat ik qua lazy loading wou laten zien, maar komt in de buurt (work-in-progress I guess)

## 4. Bidirectionele relaties

Wat doet mappedBy nou precies? Kijk eens kritisch naar de gekke demo-situatie die we opzetten, met en zonder mappedBy.

## 5. Booch & invarianten: weg van getters/setters

Booch: een object heeft **state**, **behaviour** en **identity**. Een **invariant** is een regel die altijd waar is, voor
en na elke publieke methode. Het object bewaakt die regel zelf, en dat lukt alleen als niemand van buitenaf de state kan
overschrijven.

Het voorbeeld is één game tennis, in `backend/src/main/java/nl/hu/s3/project/booch`.

|                    | `bad`                                    |
|--------------------|------------------------------------------|
| State              | Kopie van het scorebord, plus twee flags |
| Behaviour          | In `TennisService`                       |
| Buitenkant         | Getters en setters per veld              |
| Ongeldige toestand | Eenvoudig te maken                       |

# Bonus: Logging

Je ziet dat we expliciet Loggers ipv. System.out gebruiken, zodat je het log-level kan tweaken.
Vergelijk maar eens de performance van de massa-import met/zonder logging. Logging is niet gratis, en het is
fijn om dit 'tweakbaar' te hebben op productie.