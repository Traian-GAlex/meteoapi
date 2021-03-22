**Meteo Api (meteoapi)**

L'Api rappresenta un micro-servizio che sfrutta le api presenti su
[openweathermap.org](https://openweathermap.org/) per ottenere
informazioni di ordine meteorologico nonché per generare delle
statistiche sulla base dei dati raccolti.

È stato creato sfruttando il linguaggio Java attraverso il framework
Spring con l'ausilio del Spring Boot.

Per la persistenza dei dati è stato utilizzato il DBMS MySQL (nella
variante opensource MariaDb).

Per poter utilizzare si devono impostare alcuni parametri nel file
**src\\main\\resources\\application.properties** che elenchiamo di
seguito:

**server.port**

-   indica la porta su cui sta operando il nostro microservice. Nel
    nostro caso impostato di default sul valore **8083**.

**meteoapi.pwd**

-   deve contenere una stringa che rappresenta la password per accedere
    ad alcuni URL del micro‑servizio. Di default è impostata come
    "secret" e se viene reimpostata dev'essere corretta anche negli URL
    che ne fanno uso; URL che elencheremo di seguito.

**open_weather.api_key**

-   rappresenta la API Key fornita da
    [openweathermap.org](https://openweathermap.org/) per accedere ai
    suoi servizi. Per ottenere la suddetta chiave ci si deve iscrivere
    sul sito del provider di servizi.

**spring.datasource.url**

-   deve contenere la stringa di connessione al database ed il valore
    implicito è:
    **jdbc:mysql://localhost:3306/meteoapi?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false**,
    dove **meteoapi** rappresenta il nome del database in cui si
    salveranno i dati. Se il nome del vostro database è o sarà diverso
    andrà cambiato di conseguenza.

**spring.datasource.username**

-   rappresenta il nome utente con cui si accede al database (es. root)

**spring.datasource.password**

-   deve contenere la password di accesso al database. Può essere nullo
    se non c'è una password impostato per l'utente usato.

Di seguito saranno elencati gli URL di accesso al micro-servizio con la
loro descrizione.

GET: <http://localhost:8083>

> Rappresenta l'home page del micro-servizio e contiene il nome ed il
> numero di città presenti nel database, estratti da un elenco messo a
> disposizione da [openweathermap.org](https://openweathermap.org/).

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
