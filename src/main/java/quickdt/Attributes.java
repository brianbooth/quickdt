package quickdt;

import java.io.Serializable;
import java.util.HashMap;

public final class Attributes extends HashMap<String, Serializable> {

	private static final long serialVersionUID = -1165915887065742633L;

	public static Attributes create(final Serializable... inputs) {
		final Attributes a = new Attributes();
		for (int x = 0; x < inputs.length; x += 2) {
			a.put((String) inputs[x], inputs[x + 1]);
		}
		return a;
	}


}
