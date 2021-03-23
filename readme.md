# Meteo Api (meteoapi)



L'Api rappresenta un micro-servizio che sfrutta le api presenti su [openweathermap.org](https://openweathermap.org/) per ottenere informazioni di ordine meteorologico nonché per generare delle statistiche sulla base dei dati raccolti.

È stato creato sfruttando il linguaggio Java attraverso il framework Spring con l'ausilio del Spring Boot.

Per la persistenza dei dati è stato utilizzato il DBMS MySQL (nella variante opensource MariaDb).

Per poter utilizzare si devono impostare alcuni parametri nel file **src\\main\\resources\\application.properties** che elenchiamo di seguito:

<font size="5">**server.port**</font>

+ indica la porta su cui sta operando il nostro microservice. Nel nostro caso impostato di default sul valore **8083**.

<font size="5">**meteoapi.pwd**</font>

-   deve contenere una stringa che rappresenta la password per accedere ad alcuni URL del micro‑servizio. Di default è impostata come "secret" e se viene reimpostata dev'essere corretta anche negli URL che ne fanno uso; URL che elencheremo di seguito.

<font size="5">**open_weather.api_key**</font>

-   rappresenta la API Key fornita da [openweathermap.org](https://openweathermap.org/) per accedere ai suoi servizi. Per ottenere la suddetta chiave ci si deve iscrivere sul sito del provider di servizi.

<font size="5">**spring.datasource.url**</font>

-   deve contenere la stringa di connessione al database ed il valore implicito è: **jdbc:mysql://localhost:3306/meteoapi?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false**, dove **meteoapi** rappresenta il nome del database in cui si salveranno i dati. Se il nome del vostro database è o sarà diverso andrà cambiato di conseguenza.

<font size="5">**spring.datasource.username**</font>

-   rappresenta il nome utente con cui si accede al database (es. root)

<font size="5">**spring.datasource.password**</font>

-   deve contenere la password di accesso al database. Può essere nullo
    se non c'è una password impostato per l'utente usato.

**Di seguito saranno elencati gli URL di accesso al micro-servizio con la descrizione afferente:**



| Metodo |        Indirizzo        |
|:------:|:-----------------------|
|   GET  | [http://localhost:8083](#home) |
|   GET  |<http://localhost:8083/cities?country=CodiceDelPaese&city=NomeCittà>                     |
|   GET  |<http://localhost:8083/cities/load/{{secret}}>                    |
|   GET  |<http://localhost:8083/cities/stop/{{secret}}>                         |
|   GET  |<http://localhost:8083/forecast?country=CodiceDelPaese&city=NomeCittà>             |
|   GET  |<http://localhost:8083/forecast/lookup/{{secret}}/?sleep=ValoreIntero&type=IntervalloDiTempo&country=CodiceDelPaese&city=NomeCittà>                         |
|   GET  |<http://localhost:8083/forecast/lookup/{{secret}}/stop>                        |
|   GET  |<http://localhost:8083/forecast/seed/{{secret}}/?sleep=ValoreIntero&type=IntervalloDiTempo&country=CodiceDelPaese&city=NomeCittà>                        |
|   GET  |<http://localhost:8083/forecast/seed/{{secret}}/stop>                         |
|   GET  |<http://localhost:8083/forecast?country=CodiceDelPaese&city=NomeCittà>                         |
|   GET  |<http://localhost:8083/forecast/statistics?start=DataInizio&end=DataFine&country=CodiceDelPaese&city=NomeCittà>|


<span id+"home"></span>
| Metodo |        Indirizzo        |
|:------:|:-----------------------|
|   GET  | <http://localhost:8083> |

- Rappresenta l'home page del micro-servizio e contiene il nome ed il numero di città presenti nel database, estratti da un elenco messo a disposizione da [openweathermap.org](https://openweathermap.org/).

GET: [http://localhost:8083/forecast?country=\[codice del
paese](http://localhost:8083/forecast?country=%5bcodice%20del%20paese)[\]&city=\[nome
città\]]{.ul}

> Permette l'accesso diretto ai serizzi del
> [openweathermap.org](https://openweathermap.org/) per ottenere
> informazioni meteorologiche complete.
>
> **Parametri:**
>
> ***country***: codice del paese nel formato a due lettere. Si possono
> indicare più paesi separati da virgola e senza spazzi.
>
> ***city***: Nome della città o elenco dei nomi delle città separate da
> virgola e senza spazzi.
>
> **Nota:** In tutti gli URL in cui compaiono i parametri country e city
> si possono usare i seguenti caratteri sostitutivi:
>
> **!** -- il punto esclamativo sostituisce un carattere nella posizione
> corrente.
>
> \* - l'asterisco sostituisce uno o più caratteri cominciando dalla
> posizione corrente.
>
> L'uso di tali caratteri permette di realizzare ricerche generiche
> come, per esempio, A\* nel parametro city andrà a ricercare tutte le
> città che iniziano con la lettera A.

GET:
[http://localhost:8083/forecast/statistics?start=\[data](http://localhost:8083/forecast/statistics?start=%5bdata)
[d'inizio\]&end=\[data della fine\]&country=\[codice]{.ul}

[del paese\]&city=\[nome della città\]]{.ul}
