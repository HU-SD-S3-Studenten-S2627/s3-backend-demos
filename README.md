# Assorti Demos

## 1. Tests per laag

## 2. Identity vs. Sequence vs. NonGenerated

De startupRunner gebruikt de demorunner om 5000 players te inserten. Zie de performance-verschillen per soort ID-generation.

## 3. N+1 queries

De repository van Player Join-fetcht de preferences nu. Dit is niet exact wat ik qua lazy loading wou laten zien, maar komt in de buurt (work-in-progress I guess)

## 4. Bidirectionele relaties

Wat doet mappedBy nou precies? Kijk eens kritisch naar de gekke demo-situatie die we opzetten, met en zonder mappedBy.
