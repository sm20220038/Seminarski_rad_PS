/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package db;

import domain.ApstraktniDomenskiObjekat;
import java.util.List;

/**
 *
 * @author stefa
 */
public interface DBBroker {
    //True ako je uspostavljena konekcija, false ako nije.
    public abstract boolean createConnection();
    //True ako je uspesno stvoren, false ako nije.
    public abstract boolean createZapis(ApstraktniDomenskiObjekat ado);
    //True ako je uspesno azuriran, false ako nije.
    public abstract boolean updateZapis(ApstraktniDomenskiObjekat stariAdo, ApstraktniDomenskiObjekat noviAdo);
    //True ako je obrisan zapisa, false ako nije.
    public abstract boolean deleteZapis(ApstraktniDomenskiObjekat ado);
    //Brise vise zapisa gde je ispunjen zadati WHERE uslov.
    public abstract boolean deleteZapisi(ApstraktniDomenskiObjekat ado, String where);
    //Vraca zapis iz baze podataka.
    public abstract ApstraktniDomenskiObjekat nadjiZapis(ApstraktniDomenskiObjekat ado);
    //Vraca listu zapisa koji ispunjavaju zadati WHERE uslov.
    public abstract List<ApstraktniDomenskiObjekat> nadjiZapise(ApstraktniDomenskiObjekat ado, String where);
    //Commituje trenutnu transakciju
    public abstract boolean commitTransaction();
    //Rollbackuje trenutnu transakciju
    public abstract boolean rollbackTransaction();
    //True ako zatvori konekciju, false ako ne uspe.
    public abstract boolean closeConnection();
    //Vraca zapis iz kolekcije na datom indeksu.
    public abstract ApstraktniDomenskiObjekat getZapis(ApstraktniDomenskiObjekat ado, int index);
    //Vraca broj zapisa koji su datog tipa.
    public abstract int getBrojZapisa(ApstraktniDomenskiObjekat ado);
    //Vraca indeks datog zapisa u kolekciji.
    public abstract int getPozicijuZapisa(ApstraktniDomenskiObjekat ado);
    //Vraca true ako je konekcija trenutno otvorena, false ako je zatvorena.
    public abstract boolean isDBConnected();
}
