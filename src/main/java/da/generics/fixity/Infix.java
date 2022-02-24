package da.generics.fixity;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import da.generics.Fixity;
import da.generics.Infix0;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;

public class Infix extends Fixity {
  public static final String _packageId = "ed2beae6b3bc8a8804ef43a7add61c87689c18e7b4a2ed45ec2f866df40d16f0";

  public final Infix0 infix0Value;

  public Infix(Infix0 infix0Value) {
    this.infix0Value = infix0Value;
  }

  public Variant toValue() {
    return new Variant("Infix", this.infix0Value.toValue());
  }

  public static Infix fromValue(Value value$) throws IllegalArgumentException {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected: Variant. Actual: " + value$.getClass().getName()));
    if (!"Infix".equals(variant$.getConstructor())) throw new IllegalArgumentException("Invalid constructor. Expected: Infix. Actual: " + variant$.getConstructor());
    Value variantValue$ = variant$.getValue();
    Infix0 body = Infix0.fromValue(variantValue$);
    return new Infix(body);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof Infix)) {
      return false;
    }
    Infix other = (Infix) object;
    return this.infix0Value.equals(other.infix0Value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.infix0Value);
  }

  @Override
  public String toString() {
    return String.format("Infix(%s)", this.infix0Value);
  }
}
