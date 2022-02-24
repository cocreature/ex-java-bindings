package da.exception.preconditionfailed;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.Text;
import com.daml.ledger.javaapi.data.Value;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PreconditionFailed {
  public static final String _packageId = "f20de1e4e37b92280264c08bf15eca0be0bc5babd7a7b5e574997f154c00cb78";

  public final String message;

  public PreconditionFailed(String message) {
    this.message = message;
  }

  public static PreconditionFailed fromValue(Value value$) throws IllegalArgumentException {
    Value recordValue$ = value$;
    DamlRecord record$ = recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    List<DamlRecord.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 1) {
      throw new IllegalArgumentException("Expected 1 arguments, got " + numberOfFields);
    }
    String message = fields$.get(0).getValue().asText().orElseThrow(() -> new IllegalArgumentException("Expected message to be of type com.daml.ledger.javaapi.data.Text")).getValue();
    return new PreconditionFailed(message);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(1);
    fields.add(new DamlRecord.Field("message", new Text(this.message)));
    return new DamlRecord(fields);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof PreconditionFailed)) {
      return false;
    }
    PreconditionFailed other = (PreconditionFailed) object;
    return this.message.equals(other.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.message);
  }

  @Override
  public String toString() {
    return String.format("da.exception.preconditionfailed.PreconditionFailed(%s)", this.message);
  }
}
