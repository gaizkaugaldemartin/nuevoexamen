package gaizka;

import java.util.Comparator;

public class ComparadorRevistas implements Comparator<Revista> {

	public int compare(Revista o1, Revista o2) {
		return Integer.parseInt(o1.getNumeroPaginas()) - Integer.parseInt(o2.getNumeroPaginas());
	}

}
