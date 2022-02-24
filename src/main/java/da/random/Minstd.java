package da.random;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import java.lang.String;

public abstract class Minstd {
  public static final String _packageId = "ed2beae6b3bc8a8804ef43a7add61c87689c18e7b4a2ed45ec2f866df40d16f0";

  public Minstd() {
  }

  public abstract Value toValue();

  public static Minstd fromValue(Value value$) {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant da.random.Minstd"));
    if ("Minstd".equals(variant$.getConstructor())) {
      return da.random.minstd.Minstd.fromValue(variant$);
    }
    throw new IllegalArgumentException("Found unknown constructor variant$.getConstructor() for variant da.random.Minstd, expected one of [Minstd]");
  }
}
