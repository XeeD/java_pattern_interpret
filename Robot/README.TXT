Program ::= [ DefiniceProcedury | DefiniceFunkce ]... Volani

DefiniceProcedury ::= $Identifikátor Příkaz

DefiniceFunkce ::= §Identifikator Příkaz

Identifikátor ::= ZobecněnéPismeno [ ZobecněnéPismeno | Číslice ]...

ZobecněnéPismeno ::= Pismeno  |  _	

Komentář ::= « Text » 

Podmínka ::= ?Funkce Příkaz [ : Příkaz ]

Volani ::= !Procedura 

Opakování ::= @Číslo Příkaz

While ::= ¤Funkce Příkaz

Příkaz ::= Podmínka | Opakování | While | Volani | Break | Return | { [ Prikaz ]... }

Procedura ::= IdentifikátorDefinovanéProcedury | IdentifikátorAtomickéProcedury

IdentifikátorAtomickéProcedury ::= krok  |  vlevoVbok  |  poloz  |  zvedni

Funkce ::= IdentifikátorDefinovanéFunkce | IdentifikátorAtomickéFunkce

IdentifikátorAtomickéFunkce ::= jeZnacka  |  jeZed  |  jeRobot  |  jeVychod

Break ::= >>>

Return ::= ###

ReturnHodnotu ::= +++  |  ---
