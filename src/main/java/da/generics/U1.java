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

public class U1<p_a2Fw> {
  public static final String _packageId = "ed2beae6b3bc8a8804ef43a7add61c87689c18e7b4a2ed45ec2f866df40d16f0";

  public U1() {
  }

  public static <p_a2Fw> U1<p_a2Fw> fromValue(Value value$) throws IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = (Record) recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    @NonNull List<DamlRecord.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 0) {
      throw new IllegalArgumentException("Expected 0 arguments, got " + numberOfFields);
    }
    return new da.generics.U1<p_a2Fw>();
  }

  public Record toValue() {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(0);
    return new Record(fields);
  }

  public static <p_a2Fw> U1<p_a2Fw> fromValue(Value value$, Function<Value, p_a2Fw> fromValuep_a2Fw)
      throws IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = (Record) recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    @NonNull List<DamlRecord.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 0) {
      throw new IllegalArgumentException("Expected 0 arguments, got " + numberOfFields);
    }
    return new da.generics.U1<p_a2Fw>();
  }

  public Record toValue(Function<p_a2Fw, Value> toValuep_a2Fw) {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(0);
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
    if (!(object instanceof U1<?>)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash();
  }

  @Override
  public String toString() {
    return "da.generics.U1";
  }
}
