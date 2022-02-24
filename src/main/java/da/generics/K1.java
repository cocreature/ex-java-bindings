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

public class K1<i_a2Fq, c_a2Fr, p_a2Fs> {
  public static final String _packageId = "ed2beae6b3bc8a8804ef43a7add61c87689c18e7b4a2ed45ec2f866df40d16f0";

  public final c_a2Fr unK1;

  public K1(c_a2Fr unK1) {
    this.unK1 = unK1;
  }

  public static <i_a2Fq, c_a2Fr, p_a2Fs> K1<i_a2Fq, c_a2Fr, p_a2Fs> fromValue(Value value$,
      Function<Value, c_a2Fr> fromValuec_a2Fr) throws IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = (Record) recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    @NonNull List<DamlRecord.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 1) {
      throw new IllegalArgumentException("Expected 1 arguments, got " + numberOfFields);
    }
    c_a2Fr unK1 = fromValuec_a2Fr.apply(fields$.get(0).getValue());
    return new da.generics.K1<i_a2Fq, c_a2Fr, p_a2Fs>(unK1);
  }

  public Record toValue(Function<c_a2Fr, Value> toValuec_a2Fr) {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(1);
    fields.add(new Record.Field("unK1", toValuec_a2Fr.apply(this.unK1)));
    return new Record(fields);
  }

  public static <i_a2Fq, c_a2Fr, p_a2Fs> K1<i_a2Fq, c_a2Fr, p_a2Fs> fromValue(Value value$,
      Function<Value, i_a2Fq> fromValuei_a2Fq, Function<Value, c_a2Fr> fromValuec_a2Fr,
      Function<Value, p_a2Fs> fromValuep_a2Fs) throws IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = (Record) recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    @NonNull List<DamlRecord.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 1) {
      throw new IllegalArgumentException("Expected 1 arguments, got " + numberOfFields);
    }
    c_a2Fr unK1 = fromValuec_a2Fr.apply(fields$.get(0).getValue());
    return new da.generics.K1<i_a2Fq, c_a2Fr, p_a2Fs>(unK1);
  }

  public Record toValue(Function<i_a2Fq, Value> toValuei_a2Fq,
      Function<c_a2Fr, Value> toValuec_a2Fr, Function<p_a2Fs, Value> toValuep_a2Fs) {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(1);
    fields.add(new Record.Field("unK1", toValuec_a2Fr.apply(this.unK1)));
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
    if (!(object instanceof K1<?, ?, ?>)) {
      return false;
    }
    K1<?, ?, ?> other = (K1<?, ?, ?>) object;
    return this.unK1.equals(other.unK1);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.unK1);
  }

  @Override
  public String toString() {
    return String.format("da.generics.K1(%s)", this.unK1);
  }
}
