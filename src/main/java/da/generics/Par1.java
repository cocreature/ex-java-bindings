package da.generics;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.Record;
import com.daml.ledger.javaapi.data.Value;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Par1<p_a2Fv> {
  public static final String _packageId = "ed2beae6b3bc8a8804ef43a7add61c87689c18e7b4a2ed45ec2f866df40d16f0";

  public final p_a2Fv unPar1;

  public Par1(p_a2Fv unPar1) {
    this.unPar1 = unPar1;
  }

  public static <p_a2Fv> Par1<p_a2Fv> fromValue(Value value$,
      Function<Value, p_a2Fv> fromValuep_a2Fv) throws IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = (Record) recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    @NonNull List<DamlRecord.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 1) {
      throw new IllegalArgumentException("Expected 1 arguments, got " + numberOfFields);
    }
    p_a2Fv unPar1 = fromValuep_a2Fv.apply(fields$.get(0).getValue());
    return new da.generics.Par1<p_a2Fv>(unPar1);
  }

  public Record toValue(Function<p_a2Fv, Value> toValuep_a2Fv) {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(1);
    fields.add(new Record.Field("unPar1", toValuep_a2Fv.apply(this.unPar1)));
    return new Record(fields);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof Par1<?>)) {
      return false;
    }
    Par1<?> other = (Par1<?>) object;
    return this.unPar1.equals(other.unPar1);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.unPar1);
  }

  @Override
  public String toString() {
    return String.format("da.generics.Par1(%s)", this.unPar1);
  }
}
