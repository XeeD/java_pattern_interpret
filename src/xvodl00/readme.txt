/*******************************************************************************
 *   Instance třídy <b><code>Překladač</code></b> předtavují 
 *   lexikální a syntaktické analyzátory, které převedou text 
 *   do stromové struktury vnitřní reprezentace programu.
 *   
 * + Program  ::= [DefiniceProcedury | DefiniceFunkce]... PosloupnostPříkazů
 * + DefiniceProcedury ::=  $Identifikátor PosloupnostPříkazů 
 * + Identifikátor ::= ZobPismeno [ZobPismeno | Číslice]...
 * - ZobPismeno ::= Pismeno | _
 * + PosloupnostPříkazů ::= { [Příkaz]... }
 * + Příkaz ::= Podmínka | Opakování | While | Volani | break | return |
 *              PosloupnostPříkazů
 * + Podmínka ::= ?Funkce PosloupnostPříkazů [ : PosloupnostPříkazů ] 
 * + Opakování ::= @Číslo PosloupnostPříkazů 
 * + While ::= ¤Funkce PosloupnostPříkazů 
 * + Volani ::= !Procedura 
 * + Procedura ::= DefinovanáProcedura | AtomickáProcedura
 * + AtomickáProcedura ::= krok | vlevoVbok | poloz | zvedni
 * + Funkce ::= DefinovanáFunkce | AtomickáFunkce
 * + AtomickáFunkce ::= jeZnačka | jeZed | jeRobot | jeVychod
 * + Break ::= >>>
 * + Return ::= ###
 * + ReturnHodnotu ::= +++  |  ---
 * + DefiniceFunkce ::= §Identifikator PosloupnostPříkazů
 */


$ Procedura

  ? podminka
  : else
  #?
  
  @ opakovani
  #@
  
  ¤ while
  #¤
  
  ! Volani procedury
  
  ## break
  
  ### return
  
#$  

§ Funkce boolean
  +++  return true
  ---  return false
#§


