package da.next.map;

import com.daml.ledger.javaapi.data.DamlCollectors;
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

public class Map<k_a9we, v_a9wf> {
  public static final String _packageId = "ed2beae6b3bc8a8804ef43a7add61c87689c18e7b4a2ed45ec2f866df40d16f0";

  public final java.util.Map<String, v_a9wf> textMap;

  public Map(java.util.Map<String, v_a9wf> textMap) {
    this.textMap = textMap;
  }

  public static <k_a9we, v_a9wf> Map<k_a9we, v_a9wf> fromValue(Value value$,
      Function<Value, v_a9wf> fromValuev_a9wf) throws IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = (Record) recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    @NonNull List<DamlRecord.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 1) {
      throw new IllegalArgumentException("Expected 1 arguments, got " + numberOfFields);
    }
    java.util.Map<String, v_a9wf> textMap = fields$.get(0).getValue().asTextMap()
            .map(v$0 -> v$0.toMap(v$1 ->
                fromValuev_a9wf.apply(v$1)
            ))
            .orElseThrow(() -> new IllegalArgumentException("Expected textMap to be of type com.daml.ledger.javaapi.data.DamlTextMap"))
                  ;
    return new da.next.map.Map<k_a9we, v_a9wf>(textMap);
  }

  public Record toValue(Function<v_a9wf, Value> toValuev_a9wf) {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(1);
    fields.add(new Record.Field("textMap", this.textMap.entrySet().stream().collect(DamlCollectors.toDamlTextMap(java.util.Map.Entry::getKey, v$0 -> toValuev_a9wf.apply(v$0.getValue()))) ));
    return new Record(fields);
  }

  public static <k_a9we, v_a9wf> Map<k_a9we, v_a9wf> fromValue(Value value$,
      Function<Value, k_a9we> fromValuek_a9we, Function<Value, v_a9wf> fromValuev_a9wf) throws
      IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = (Record) recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    @NonNull List<DamlRecord.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 1) {
      throw new IllegalArgumentException("Expected 1 arguments, got " + numberOfFields);
    }
    java.util.Map<String, v_a9wf> textMap = fields$.get(0).getValue().asTextMap()
            .map(v$0 -> v$0.toMap(v$1 ->
                fromValuev_a9wf.apply(v$1)
            ))
            .orElseThrow(() -> new IllegalArgumentException("Expected textMap to be of type com.daml.ledger.javaapi.data.DamlTextMap"))
                  ;
    return new da.next.map.Map<k_a9we, v_a9wf>(textMap);
  }

  public Record toValue(Function<k_a9we, Value> toValuek_a9we,
      Function<v_a9wf, Value> toValuev_a9wf) {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(1);
    fields.add(new Record.Field("textMap", this.textMap.entrySet().stream().collect(DamlCollectors.toDamlTextMap(java.util.Map.Entry::getKey, v$0 -> toValuev_a9wf.apply(v$0.getValue()))) ));
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
    if (!(object instanceof Map<?, ?>)) {
      return false;
    }
    Map<?, ?> other = (Map<?, ?>) object;
    return this.textMap.equals(other.textMap);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.textMap);
  }

  @Override
  public String toString() {
    return String.format("da.next.map.Map(%s)", this.textMap);
  }
}
