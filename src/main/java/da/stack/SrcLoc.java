package da.stack;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.Int64;
import com.daml.ledger.javaapi.data.Text;
import com.daml.ledger.javaapi.data.Value;
import java.lang.IllegalArgumentException;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SrcLoc {
  public static final String _packageId = "9de3ae0b7b4e753f7ce2a5dcaa8d49ab4b92c4d8a8fb9589b5f8c893fbea5bd1";

  public final String srcLocPackage;

  public final String srcLocModule;

  public final String srcLocFile;

  public final Long srcLocStartLine;

  public final Long srcLocStartCol;

  public final Long srcLocEndLine;

  public final Long srcLocEndCol;

  public SrcLoc(String srcLocPackage, String srcLocModule, String srcLocFile, Long srcLocStartLine,
      Long srcLocStartCol, Long srcLocEndLine, Long srcLocEndCol) {
    this.srcLocPackage = srcLocPackage;
    this.srcLocModule = srcLocModule;
    this.srcLocFile = srcLocFile;
    this.srcLocStartLine = srcLocStartLine;
    this.srcLocStartCol = srcLocStartCol;
    this.srcLocEndLine = srcLocEndLine;
    this.srcLocEndCol = srcLocEndCol;
  }

  public static SrcLoc fromValue(Value value$) throws IllegalArgumentException {
    Value recordValue$ = value$;
    DamlRecord record$ = recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    List<DamlRecord.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 7) {
      throw new IllegalArgumentException("Expected 7 arguments, got " + numberOfFields);
    }
    String srcLocPackage = fields$.get(0).getValue().asText().orElseThrow(() -> new IllegalArgumentException("Expected srcLocPackage to be of type com.daml.ledger.javaapi.data.Text")).getValue();
    String srcLocModule = fields$.get(1).getValue().asText().orElseThrow(() -> new IllegalArgumentException("Expected srcLocModule to be of type com.daml.ledger.javaapi.data.Text")).getValue();
    String srcLocFile = fields$.get(2).getValue().asText().orElseThrow(() -> new IllegalArgumentException("Expected srcLocFile to be of type com.daml.ledger.javaapi.data.Text")).getValue();
    Long srcLocStartLine = fields$.get(3).getValue().asInt64().orElseThrow(() -> new IllegalArgumentException("Expected srcLocStartLine to be of type com.daml.ledger.javaapi.data.Int64")).getValue();
    Long srcLocStartCol = fields$.get(4).getValue().asInt64().orElseThrow(() -> new IllegalArgumentException("Expected srcLocStartCol to be of type com.daml.ledger.javaapi.data.Int64")).getValue();
    Long srcLocEndLine = fields$.get(5).getValue().asInt64().orElseThrow(() -> new IllegalArgumentException("Expected srcLocEndLine to be of type com.daml.ledger.javaapi.data.Int64")).getValue();
    Long srcLocEndCol = fields$.get(6).getValue().asInt64().orElseThrow(() -> new IllegalArgumentException("Expected srcLocEndCol to be of type com.daml.ledger.javaapi.data.Int64")).getValue();
    return new SrcLoc(srcLocPackage, srcLocModule, srcLocFile, srcLocStartLine, srcLocStartCol, srcLocEndLine, srcLocEndCol);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(7);
    fields.add(new DamlRecord.Field("srcLocPackage", new Text(this.srcLocPackage)));
    fields.add(new DamlRecord.Field("srcLocModule", new Text(this.srcLocModule)));
    fields.add(new DamlRecord.Field("srcLocFile", new Text(this.srcLocFile)));
    fields.add(new DamlRecord.Field("srcLocStartLine", new Int64(this.srcLocStartLine)));
    fields.add(new DamlRecord.Field("srcLocStartCol", new Int64(this.srcLocStartCol)));
    fields.add(new DamlRecord.Field("srcLocEndLine", new Int64(this.srcLocEndLine)));
    fields.add(new DamlRecord.Field("srcLocEndCol", new Int64(this.srcLocEndCol)));
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
    if (!(object instanceof SrcLoc)) {
      return false;
    }
    SrcLoc other = (SrcLoc) object;
    return this.srcLocPackage.equals(other.srcLocPackage) && this.srcLocModule.equals(other.srcLocModule) && this.srcLocFile.equals(other.srcLocFile) && this.srcLocStartLine.equals(other.srcLocStartLine) && this.srcLocStartCol.equals(other.srcLocStartCol) && this.srcLocEndLine.equals(other.srcLocEndLine) && this.srcLocEndCol.equals(other.srcLocEndCol);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.srcLocPackage, this.srcLocModule, this.srcLocFile, this.srcLocStartLine, this.srcLocStartCol, this.srcLocEndLine, this.srcLocEndCol);
  }

  @Override
  public String toString() {
    return String.format("da.stack.SrcLoc(%s, %s, %s, %s, %s, %s, %s)", this.srcLocPackage, this.srcLocModule, this.srcLocFile, this.srcLocStartLine, this.srcLocStartCol, this.srcLocEndLine, this.srcLocEndCol);
  }
}
