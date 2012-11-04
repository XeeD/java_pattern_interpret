package xvodl00;

/**
 * Výjimka, která slouží pro řízení běhu programu. Programy vyhazují instance
 * jejích potomků, které jsou pak zachyceny na správných místech programu, nebo
 * probublají až na povrch a jsou reportovány jako chyby.
 *
 * @author XeeD
 */
abstract public class Skok extends Exception {
}
