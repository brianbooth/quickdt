package quickdt;

import java.io.Serializable;

public class Instance {

	private Instance() {

	}

	public static Instance create(final String classification, final Serializable... inputs) {
		final Attributes a = Attributes.create(inputs);
		return new Instance(a, classification);
	}

	public Instance(final Attributes attributes, final Serializable classification) {
		this.attributes = attributes;
		this.classification = classification;
	}

	public Attributes attributes;
	public Serializable classification;

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("[attributes=");
		builder.append(attributes);
		builder.append(", classification=");
		builder.append(classification);
		builder.append("]");
		return builder.toString();
	}
}
